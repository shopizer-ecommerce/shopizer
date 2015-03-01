package com.salesmanager.web.admin.controller.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.image.ProductImage;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.image.ProductImageService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.content.ProductImages;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;
import com.salesmanager.web.utils.ImageFilePathUtils;
import com.salesmanager.web.utils.LabelUtils;

@Controller
public class ProductImagesController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductImagesController.class);
	
	

	@Autowired
	private ProductService productService;
	

	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	LabelUtils messages;
	

	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/images/list.html", method=RequestMethod.GET)
	public String displayProductImages(@RequestParam("id") long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		setMenu(model,request);
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		Product product = productService.getById(productId);
		
		if(product==null) {
			return "redirect:/admin/products/products.html";
		}
		
		if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		model.addAttribute("product",product);
		return ControllerConstants.Tiles.Product.productImages;
		
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/images/page.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageProductImages(HttpServletRequest request, HttpServletResponse response) {

		//String attribute = request.getParameter("attribute");
		String sProductId = request.getParameter("productId");
		
		
		AjaxResponse resp = new AjaxResponse();
		
		Long productId;
		Product product = null;
		
		try {
			productId = Long.parseLong(sProductId);
		} catch (Exception e) {
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString("Product id is not valid");
			String returnString = resp.toJSONString();
			return returnString;
		}

		
		try {
			
			
			product = productService.getById(productId);

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			//List<ProductAttribute> attributes = productAttributeService.getByProductId(store, product, language);
			
			
			Set<ProductImage> images = product.getImages();
			
			if(images!=null) {
				
				for(ProductImage image : images) {
					
					String imagePath = ImageFilePathUtils.buildProductImageFilePath(store, product, image.getProductImage());
					
					Map entry = new HashMap();
					entry.put("picture", new StringBuilder().append(request.getContextPath()).append(imagePath).toString());
					entry.put("name", image.getProductImage());
					entry.put("id",image.getId());
					
					resp.addDataEntry(entry);
				}
				
				
			}



			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return returnString;


	}


	
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/images/save.html", method=RequestMethod.POST)
	public String saveProductImages(@ModelAttribute(value="productImages") @Valid final ProductImages productImages, final BindingResult bindingResult,final Model model, final HttpServletRequest request,Locale locale) throws Exception{
	    
	    
		this.setMenu(model, request);

		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		Product product = productService.getById(productImages.getProductId());
		model.addAttribute("product",product);
		if(product==null) {
			FieldError error = new FieldError("productImages","image",messages.getMessage("message.error", locale));
			bindingResult.addError(error);
			return ControllerConstants.Tiles.Product.productImages;
		}
		
		if(product.getMerchantStore().getId()!=store.getId()) {
			FieldError error = new FieldError("productImages","image",messages.getMessage("message.error", locale));
			bindingResult.addError(error);
		}
		
		if (bindingResult.hasErrors()) {
	        LOGGER.info( "Found {} Validation errors", bindingResult.getErrorCount());
	       return ControllerConstants.Tiles.Product.productImages;
	       
        }
		
	    final List<ProductImage> contentImagesList=new ArrayList<ProductImage>();
        if(CollectionUtils.isNotEmpty( productImages.getFile() )){
            LOGGER.info("Saving {} content images for merchant {}",productImages.getFile().size(),store.getId());
            for(final MultipartFile multipartFile:productImages.getFile()){
                if(!multipartFile.isEmpty()){
                	ProductImage productImage = new ProductImage();

                	productImage.setImage(multipartFile.getInputStream());
                    productImage.setProductImage(multipartFile.getOriginalFilename() );
                    productImage.setProduct(product);
                    productImage.setDefaultImage(false);//default image is uploaded in the product details
                    
                    contentImagesList.add( productImage);
                }
            }
            
            if(CollectionUtils.isNotEmpty( contentImagesList )){
            	productImageService.addProductImages(product, contentImagesList);
            }
            
        }
		
/*		ProductImage productImage = new ProductImage();
		productImage.setProduct(product);
        
        
        InputStream inputStream = productImages.getFile().get(0).getInputStream();
        ImageContentFile cmsContentImage = new ImageContentFile();
        cmsContentImage.setFileName( productImages.getFile().get(0).getOriginalFilename() );
        cmsContentImage.setFile( inputStream );
        cmsContentImage.setFileContentType(FileContentType.PRODUCT);
        
        productImage.setProductImage(productImages.getFile().get(0).getOriginalFilename() );
        
        
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		cmsContentImage.setBufferedImage(bufferedImage);
		
		productImageService.addProductImage(product, productImage, cmsContentImage);*/
        
        
        
        //reload
        product = productService.getById(productImages.getProductId());
        model.addAttribute("product",product);
        
        return ControllerConstants.Tiles.Product.productImages;
	}
	

	
	

		
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/images/remove.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String deleteImage(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sImageId = request.getParameter("id");

		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		AjaxResponse resp = new AjaxResponse();

		
		try {
			
			
		
				
			Long imageId = Long.parseLong(sImageId);

			
			ProductImage productImage = productImageService.getById(imageId);
			if(productImage==null) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			if(productImage.getProduct().getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			}
			
			productImageService.removeProductImage(productImage);
			
			//Long attributeId = Long.parseLong(sAttributeid);
			//ProductAttribute attribute = productAttributeService.getById(attributeId);
			

/*			if(attribute==null || attribute.getProduct().getMerchantStore().getId().intValue()!=store.getId()) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				return resp.toJSONString();
			} 
			

			productAttributeService.delete(attribute);
			*/
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product price", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		
		return returnString;
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
