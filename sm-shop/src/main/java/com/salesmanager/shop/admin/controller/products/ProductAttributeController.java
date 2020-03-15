package com.salesmanager.shop.admin.controller.products;

import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductAttributeService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionService;
import com.salesmanager.core.business.services.catalog.product.attribute.ProductOptionValueService;
import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.business.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.attribute.*;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.language.Language;
import com.salesmanager.shop.admin.model.web.Menu;
import com.salesmanager.shop.constants.Constants;
import com.salesmanager.shop.utils.LabelUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.*;

@Controller
public class ProductAttributeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductAttributeController.class);
	
	private final static String TEXT_OPTION = "text";
	
	@Inject
	private ProductAttributeService productAttributeService;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private ProductPriceUtils priceUtil;
	
	@Inject
	ProductOptionService productOptionService;
	
	@Inject
	ProductOptionValueService productOptionValueService;
	
	@Inject
	LabelUtils messages;
	

	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/attributes/list.html", method=RequestMethod.GET)
	public String displayProductAttributes(@RequestParam("id") long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
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
		return "admin-products-attributes";
		
	}
	
	

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/attributes/page.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> pageAttributes(HttpServletRequest request, HttpServletResponse response) {

		//String attribute = request.getParameter("attribute");
		String sProductId = request.getParameter("productId");
		
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		Long productId;
		Product product = null;
		
		try {
			productId = Long.parseLong(sProductId);
		} catch (Exception e) {
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString("Product id is not valid");
			String returnString = resp.toJSONString();
			return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		}

		
		try {
			
			
			product = productService.getById(productId);
			


			Language language = (Language)request.getAttribute("LANGUAGE");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			//List<ProductAttribute> attributes = productAttributeService.getByProductId(store, product, language);
			
			for(ProductAttribute attr : product.getAttributes()) {
				
				Map entry = new HashMap();
				entry.put("attributeId", attr.getId());
				
				List<ProductOptionDescription> optionsDescriptions = attr.getProductOption().getDescriptionsSettoList();
			    ProductOptionDescription optionDescription = attr.getProductOption().getDescriptionsSettoList().get(0);
				for(ProductOptionDescription desc : optionsDescriptions) {
					if(desc.getLanguage().getId().intValue()==language.getId().intValue()) {
						optionDescription = desc;
					}
				}
				
				List<ProductOptionValueDescription> optionValuesDescriptions = attr.getProductOptionValue().getDescriptionsSettoList();
			    ProductOptionValueDescription optionValueDescription = attr.getProductOptionValue().getDescriptionsSettoList().get(0);
				for(ProductOptionValueDescription desc : optionValuesDescriptions) {
					if(desc.getLanguage().getId().intValue()==language.getId().intValue()) {
						optionValueDescription = desc;
					}
				}
				entry.put("attribute", optionDescription.getName());
				entry.put("display", attr.getAttributeDisplayOnly());
				entry.put("value", optionValueDescription.getName());
				entry.put("order", attr.getProductOptionSortOrder());
				entry.put("price", priceUtil.getAdminFormatedAmountWithCurrency(store,attr.getProductAttributePrice()));

				resp.addDataEntry(entry);
				
				
				
			}

			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);


	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/attributes/editAttribute.html", method=RequestMethod.GET)
	public String displayAttributeEdit(@RequestParam("productId") Long productId, @RequestParam("id") Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayAttribute(productId, id,model,request,response);

	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/attribute/createAttribute.html", method=RequestMethod.GET)
	public String displayAttributeCreate(@RequestParam("productId") Long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return displayAttribute(productId, null,model,request,response);

	}
	
	private String displayAttribute(Long productId, Long id, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		//get product
		Product product =  productService.getById(productId);
		if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		List<Language> languages = store.getLanguages();
		
		ProductAttribute attribute = null;
		
		//get Options
		List<ProductOption> options = productOptionService.listByStore(store, language);
		//get OptionsValues
		List<ProductOptionValue> optionsValues = productOptionValueService.listByStoreNoReadOnly(store, language);
		
		if(id!=null && id.intValue()!=0) {//edit mode
			
			attribute = productAttributeService.getById(id);
			attribute.setAttributePrice(priceUtil.getAdminFormatedAmount(store, attribute.getProductAttributePrice()));
			attribute.setAttributeAdditionalWeight(String.valueOf(attribute.getProductAttributeWeight().intValue()));
			attribute.setAttributeSortOrder(String.valueOf(attribute.getProductOptionSortOrder()));
			
		} else {
			
			attribute = new ProductAttribute();
			attribute.setProduct(product);
			ProductOptionValue value = new ProductOptionValue();
			Set<ProductOptionValueDescription> descriptions = new HashSet<ProductOptionValueDescription>();
			for(Language l : languages) {
				
				ProductOptionValueDescription desc = new ProductOptionValueDescription();
				desc.setLanguage(l);
				descriptions.add(desc);
				
				
			}
			
			value.setDescriptions(descriptions);
			attribute.setProductOptionValue(value);
		}
		
		model.addAttribute("optionsValues",optionsValues);
		model.addAttribute("options",options);
		model.addAttribute("attribute",attribute);
		model.addAttribute("product",product);
		return "admin-products-attribute-details";
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/attributes/attribute/save.html", method=RequestMethod.POST)
	public String saveAttribute(@Valid @ModelAttribute("attribute") ProductAttribute attribute, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {
		

		//display menu
		setMenu(model,request);
		
		Product product = productService.getById(attribute.getProduct().getId());
		
		model.addAttribute("product",product);
		
		
		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		Language language = (Language)request.getAttribute("LANGUAGE");
		
		
		//get Options
		List<ProductOption> options = productOptionService.listByStore(store, language);
		//get OptionsValues
		List<ProductOptionValue> optionsValues = productOptionValueService.listByStoreNoReadOnly(store, language);
		
		model.addAttribute("optionsValues",optionsValues);
		model.addAttribute("options",options);
		
		ProductAttribute dbEntity =	null;	

		if(attribute.getId() != null && attribute.getId() >0) { //edit entry
			
			//get from DB
			dbEntity = productAttributeService.getById(attribute.getId());
			
			if(dbEntity==null) {
				return "redirect:/admin/products/attributes/list.html";
			}
			
			if(dbEntity.getProductOption().getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				return "redirect:/admin/products/attributes/list.html";
			}
		}
		
		//validate price
		BigDecimal submitedPrice = null;
		try {
			submitedPrice = priceUtil.getAmount(attribute.getAttributePrice());
			attribute.setProductAttributePrice(submitedPrice);
		} catch (Exception e) {
			ObjectError error = new ObjectError("attributePrice",messages.getMessage("NotEmpty.product.productPrice", locale));
			result.addError(error);
		}
		
		//validate sort order
		try {
			Integer sortOrder = Integer.parseInt(attribute.getAttributeSortOrder());
			attribute.setProductOptionSortOrder(sortOrder);
		} catch(Exception e) {
			ObjectError error = new ObjectError("attributeSortOrder",messages.getMessage("message.number.invalid", locale));
			result.addError(error);
		}
		
		//validate weight
		try {
			Integer weight = Integer.parseInt(attribute.getAttributeAdditionalWeight());
			attribute.setProductAttributeWeight(new BigDecimal(weight));
		} catch(Exception e) {
			ObjectError error = new ObjectError("attributeAdditionalWeight",messages.getMessage("message.number.invalid", locale));
			result.addError(error);
		}	
		
		if(attribute.getProductOption()==null) {
			ObjectError error = new ObjectError("productOption.id",messages.getMessage("message.productoption.required", locale));
			result.addError(error);
			return "admin-products-attribute-details";
		}

		
		//check type
		ProductOption option = attribute.getProductOption();
		option = productOptionService.getById(option.getId());
		attribute.setProductOption(option);
		
		if(option.getProductOptionType().equals(TEXT_OPTION)) {
			
			if(dbEntity!=null && dbEntity.getProductOption().getProductOptionType().equals(TEXT_OPTION)) {//bcz it is overwrited by hidden product option value list
				if(dbEntity.getProductOptionValue()!=null) {
					ProductOptionValue optVal = dbEntity.getProductOptionValue();
					List<ProductOptionValueDescription> descriptions = attribute.getProductOptionValue().getDescriptionsList();
					Set<ProductOptionValueDescription> descriptionsSet = new HashSet<ProductOptionValueDescription>();
					for(ProductOptionValueDescription description : descriptions) {
						description.setProductOptionValue(optVal);
						description.setName(description.getDescription().length()<15 ? description.getDescription() : description.getDescription().substring(0,15));
						descriptionsSet.add(description);
					}
					optVal.setDescriptions(descriptionsSet);
					optVal.setProductOptionDisplayOnly(true);
					productOptionValueService.saveOrUpdate(optVal);
					attribute.setProductOptionValue(optVal);
				}
			} else {//create a new value
			
				//create new option value
				List<ProductOptionValueDescription> descriptions = attribute.getProductOptionValue().getDescriptionsList();
				Set<ProductOptionValueDescription> newDescriptions = new HashSet<ProductOptionValueDescription>();
				ProductOptionValue newValue = new ProductOptionValue();
				for(ProductOptionValueDescription description : descriptions) {
					ProductOptionValueDescription optionValueDescription = new ProductOptionValueDescription();
					optionValueDescription.setAuditSection(description.getAuditSection());
					optionValueDescription.setLanguage(description.getLanguage());
					optionValueDescription.setName(description.getDescription().length()<15 ? description.getDescription() : description.getDescription().substring(0,15));
					optionValueDescription.setLanguage(description.getLanguage());
					optionValueDescription.setDescription(description.getDescription());
					optionValueDescription.setProductOptionValue(newValue);
					newDescriptions.add(optionValueDescription);
				}
				
				//code generation
				String code = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
				newValue.setCode(code);
				newValue.setMerchantStore(store);
				newValue.setProductOptionValueSortOrder(attribute.getProductOptionValue().getProductOptionValueSortOrder());
				newValue.setDescriptions(newDescriptions);
				newValue.setProductOptionDisplayOnly(true);
				productOptionValueService.save(newValue);
				attribute.setProductOptionValue(newValue);
				attribute.setAttributeDisplayOnly(true);
			
			}
			
		}
		

		
		if(attribute.getProductOptionValue().getId()==null) {
			ObjectError error = new ObjectError("productOptionValue.id",messages.getMessage("message.productoptionvalue.required", locale));
			result.addError(error);
		}
		
		model.addAttribute("attribute",attribute);

		
		if (result.hasErrors()) {
			return "admin-products-attribute-details";
		}
		
		productAttributeService.saveOrUpdate(attribute);

		model.addAttribute("success","success");
		return "admin-products-attribute-details";
	}
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/attributes/attribute/remove.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> deleteProductPrice(HttpServletRequest request, HttpServletResponse response, Locale locale) {
		String sAttributeid = request.getParameter("attributeId");

		
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

		
		try {
			
			Long attributeId = Long.parseLong(sAttributeid);
			ProductAttribute attribute = productAttributeService.getById(attributeId);
			

			if(attribute==null || attribute.getProduct().getMerchantStore().getId().intValue()!=store.getId()) {

				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			} 
			

			productAttributeService.delete(attribute);
			
			
			resp.setStatus(AjaxResponse.RESPONSE_OPERATION_COMPLETED);

		
		
		} catch (Exception e) {
			LOGGER.error("Error while deleting product price", e);
			resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorMessage(e);
		}
		
		String returnString = resp.toJSONString();
		return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/products/attributes/getAttributeType.html", method=RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> checkAttributeType(HttpServletRequest request, HttpServletResponse response, Locale locale) {

		String sOptionId = request.getParameter("optionId");

		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);

		
		
		AjaxResponse resp = new AjaxResponse();
		final HttpHeaders httpHeaders= new HttpHeaders();
	    httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		
		
		Long prodoptionId;
		ProductOption productOption = null;
		
		try {
			prodoptionId = Long.parseLong(sOptionId);
		} catch (Exception e) {
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
			resp.setErrorString("Product Option id is not valid");
			String returnString = resp.toJSONString();
			return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
		}

		
		try {
			
			
			productOption = productOptionService.getById(prodoptionId);
			
			if(productOption==null) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			
			if(productOption.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatusMessage(messages.getMessage("message.unauthorized", locale));
				resp.setStatus(AjaxResponse.RESPONSE_STATUS_FAIURE);			
				String returnString = resp.toJSONString();
				return new ResponseEntity<String>(returnString,httpHeaders,HttpStatus.OK);
			}
			



			Map entry = new HashMap();
			

			
			entry.put("type", productOption.getProductOptionType());
			resp.addDataEntry(entry);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_SUCCESS);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
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
