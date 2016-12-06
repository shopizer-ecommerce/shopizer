package com.salesmanager.shop.admin.controller.products;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.file.DigitalProductService;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import com.salesmanager.core.model.content.FileContentType;
import com.salesmanager.core.model.content.InputContentFile;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.admin.controller.ControllerConstants;
import com.salesmanager.shop.admin.model.digital.ProductFiles;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.*;

@Controller
public class DigitalProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DigitalProductController.class);
	
	@Inject
	private ProductService productService;
	
	@Inject
	private DigitalProductService digitalProductService;
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value={"/admin/products/digitalProduct.html"}, method=RequestMethod.GET)
	public String getDigitalProduct(@RequestParam("id") long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {

		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Product product = productService.getById(productId);
		
		if(product==null || product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		model.addAttribute("product", product);

		DigitalProduct digitalProduct = digitalProductService.getByProduct(store, product);

		model.addAttribute("digitalProduct", digitalProduct);
		return ControllerConstants.Tiles.Product.digitalProduct;
		
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/product/saveDigitalProduct.html", method=RequestMethod.POST)
	public String saveFile(@ModelAttribute(value="productFiles") @Valid final ProductFiles productFiles, final BindingResult bindingResult,final Model model, final HttpServletRequest request) throws Exception{
	    
		this.setMenu(model, request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Product product = productService.getById(productFiles.getProduct().getId());
		DigitalProduct digitalProduct = new DigitalProduct();
		if(product==null || product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		digitalProduct.setProduct(product);
		model.addAttribute("product", product);
		model.addAttribute("digitalProduct", digitalProduct);
	    
		if (bindingResult.hasErrors()) {
	        LOGGER.info( "Found {} Validation errors", bindingResult.getErrorCount());
	        return ControllerConstants.Tiles.Product.digitalProduct;
        }

	    
	    final List<InputContentFile> contentFilesList=new ArrayList<InputContentFile>();
        if(CollectionUtils.isNotEmpty( productFiles.getFile() )){
            LOGGER.info("Saving {} product files for merchant {}",productFiles.getFile().size(),store.getId());
            for(final MultipartFile multipartFile:productFiles.getFile()){
                if(!multipartFile.isEmpty()){
                    ByteArrayInputStream inputStream = new ByteArrayInputStream( multipartFile.getBytes() );
                    InputContentFile cmsContentImage = new InputContentFile();
                    cmsContentImage.setFileName(multipartFile.getOriginalFilename() );
                    cmsContentImage.setFileContentType( FileContentType.PRODUCT_DIGITAL );
                    cmsContentImage.setFile( inputStream );
                    contentFilesList.add( cmsContentImage);
                }
            }
            
            if(CollectionUtils.isNotEmpty( contentFilesList )){

            	digitalProduct.setProductFileName(contentFilesList.get(0).getFileName());
            	digitalProductService.addProductFile(product, digitalProduct, contentFilesList.get(0));
            	
            	//refresh digital product
            	digitalProduct = digitalProductService.getByProduct(store, product);
   
            }
        }
        
        
        model.addAttribute("success","success");
        return ControllerConstants.Tiles.Product.digitalProduct;
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/product/removeDigitalProduct.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> removeFile(@RequestParam("fileId") long fileId, HttpServletRequest request, HttpServletResponse response, Locale locale) {

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		AjaxResponse resp = new AjaxResponse();
		
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		
		try {
			
			DigitalProduct digitalProduct = digitalProductService.getById(fileId);
			
			//validate store
			if(digitalProduct==null) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			Product product = digitalProduct.getProduct();
			if(product.getMerchantStore().getId().intValue()!= store.getId().intValue()) {
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			digitalProductService.delete(digitalProduct);
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);
		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	private void setMenu(Model model, HttpServletRequest request) throws Exception {
		
		//display menu
		Map<String,String> activeMenus = new HashMap<String,String>();
		activeMenus.put("catalogue", "catalogue");
		activeMenus.put("catalogue-products", "catalogue-products");
		
		@SuppressWarnings("unchecked")
		Map<String, Menu> menus = (Map<String, Menu>)request.getAttribute("MENUMAP");
		
		Menu currentMenu = (Menu)menus.get("catalogue");
		model.addAttribute("currentMenu",currentMenu);
		model.addAttribute("activeMenus",activeMenus);
		//
		
	}

}
