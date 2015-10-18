package com.salesmanager.web.admin.controller.products;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesmanager.core.business.catalog.category.model.Category;
import com.salesmanager.core.business.catalog.category.service.CategoryService;
import com.salesmanager.core.business.catalog.product.model.Product;
import com.salesmanager.core.business.catalog.product.model.description.ProductDescription;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationship;
import com.salesmanager.core.business.catalog.product.model.relationship.ProductRelationshipType;
import com.salesmanager.core.business.catalog.product.service.ProductService;
import com.salesmanager.core.business.catalog.product.service.relationship.ProductRelationshipService;
import com.salesmanager.core.business.merchant.model.MerchantStore;
import com.salesmanager.core.business.reference.language.model.Language;
import com.salesmanager.core.utils.ajax.AjaxPageableResponse;
import com.salesmanager.core.utils.ajax.AjaxResponse;
import com.salesmanager.web.admin.controller.ControllerConstants;
import com.salesmanager.web.admin.entity.web.Menu;
import com.salesmanager.web.constants.Constants;


@Controller
public class RelatedItemsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RelatedItemsController.class);
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRelationshipService productRelationshipService;
	
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/catalogue/related/list.html", method=RequestMethod.GET)
	public String displayRelatedItems(@RequestParam("id") long productId, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		setMenu(model,request);
		
		Language language = (Language)request.getAttribute("LANGUAGE");
		MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
		
		//get the product and validate it belongs to the current merchant
		Product product = productService.getById(productId);
		
		if(product==null) {
			return "redirect:/admin/products/products.html";
		}
		
		if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
			return "redirect:/admin/products/products.html";
		}
		
		
		List<Category> categories = categoryService.listByStore(store,language);
		
		model.addAttribute("categories", categories);
		model.addAttribute("product", product);
		return ControllerConstants.Tiles.Product.relatedItems;
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasRole('PRODUCTS')")
	@RequestMapping(value="/admin/catalogue/related/paging.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String pageRelatedItems(HttpServletRequest request, HttpServletResponse response) {
		
		String sProductId = request.getParameter("productId");
		AjaxResponse resp = new AjaxResponse();
		
		try {
			

			
			Long productId = Long.parseLong(sProductId);
			Product product = productService.getById(productId);
			
			Language language = (Language)request.getAttribute("LANGUAGE");
			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			
			if(product==null || product.getMerchantStore().getId().intValue()!= store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				resp.setErrorString("Product id is not valid");
				String returnString = resp.toJSONString();
				return returnString;
			}
			
			

			List<ProductRelationship> relationships = productRelationshipService.getByType(store, product, ProductRelationshipType.RELATED_ITEM, language);
			
			for(ProductRelationship relationship : relationships) {
				
				Product relatedProduct = relationship.getRelatedProduct();
				Map entry = new HashMap();
				entry.put("relationshipId", relationship.getId());
				entry.put("productId", relatedProduct.getId());
				
				ProductDescription description = relatedProduct.getDescriptions().iterator().next();
				Set<ProductDescription> descriptions = relatedProduct.getDescriptions();
				for(ProductDescription desc : descriptions) {
					if(desc.getLanguage().getId().intValue()==language.getId().intValue()) {
						description = desc;
					}
				}
				

				entry.put("name", description.getName());
				entry.put("sku", relatedProduct.getSku());
				entry.put("available", relatedProduct.isAvailable());
				resp.addDataEntry(entry);
				
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
	@RequestMapping(value="/admin/catalogue/related/addItem.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String addItem(HttpServletRequest request, HttpServletResponse response) {
		
		String productId = request.getParameter("productId");
		String baseProductId = request.getParameter("baseProductId");
		AjaxResponse resp = new AjaxResponse();
		
		try {
			

			Long lProductId = Long.parseLong(productId);
			Long lBaseProductId = Long.parseLong(baseProductId);

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			Product product = productService.getById(lProductId);
			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			Product baseProduct = productService.getById(lBaseProductId);
			
			if(baseProduct==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(baseProduct.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}


			ProductRelationship relationship = new ProductRelationship();
			relationship.setActive(true);
			relationship.setProduct(baseProduct);
			relationship.setCode(ProductRelationshipType.RELATED_ITEM.name());
			relationship.setStore(store);
			relationship.setRelatedProduct(product);
			
			productRelationshipService.saveOrUpdate(relationship);
			

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
	@RequestMapping(value="/admin/catalogue/related/removeItem.html", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String removeItem(HttpServletRequest request, HttpServletResponse response) {
		
		String productId = request.getParameter("productId");
		String baseProductId = request.getParameter("baseProductId");
		AjaxResponse resp = new AjaxResponse();
		
		try {
			

			Long lproductId = Long.parseLong(productId);
			Long lBaseProductId = Long.parseLong(baseProductId);

			MerchantStore store = (MerchantStore)request.getAttribute(Constants.ADMIN_STORE);
			
			Product product = productService.getById(lproductId);
			
			if(product==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(product.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			Product baseProduct = productService.getById(lBaseProductId);
			
			if(baseProduct==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(baseProduct.getMerchantStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			ProductRelationship relationship = null;
			List<ProductRelationship> relationships = productRelationshipService.getByType(store, baseProduct, ProductRelationshipType.RELATED_ITEM);
			
			for(ProductRelationship r : relationships) {
				if(r.getRelatedProduct().getId().longValue()==lproductId.longValue()) {
					relationship = r;
					break;
				}
			}
			
			if(relationship==null) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}
			
			if(relationship.getStore().getId().intValue()!=store.getId().intValue()) {
				resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
				return resp.toJSONString();
			}


			
			
			productRelationshipService.delete(relationship);
			

			resp.setStatus(AjaxPageableResponse.RESPONSE_OPERATION_COMPLETED);
		
		} catch (Exception e) {
			LOGGER.error("Error while paging products", e);
			resp.setStatus(AjaxPageableResponse.RESPONSE_STATUS_FAIURE);
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
