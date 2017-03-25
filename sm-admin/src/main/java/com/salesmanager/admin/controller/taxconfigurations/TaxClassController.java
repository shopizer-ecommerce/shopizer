package com.salesmanager.admin.controller.taxconfigurations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.salesmanager.admin.common.utils.LabelUtils;
import com.salesmanager.admin.constants.Constants;
import com.salesmanager.admin.controller.ControllerConstants;
import com.salesmanager.admin.controller.pages.AbstractAdminController;
import com.salesmanager.admin.data.Menu;
import com.salesmanager.admin.data.response.ResponseData;
import com.salesmanager.admin.data.tax.TaxClassData;
import com.salesmanager.core.business.services.catalog.product.ProductService;
import com.salesmanager.core.business.services.tax.TaxClassService;
import com.salesmanager.core.business.utils.ajax.AjaxDataTableResponse;
import com.salesmanager.core.business.utils.ajax.AjaxResponse;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.tax.taxclass.TaxClass;

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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by umesh on 3/23/17.
 */
@Controller
public class TaxClassController extends AbstractAdminController {

    @Inject
    private TaxClassService taxClassService = null;

    @Inject
    private ProductService productService = null;

    @Inject
    LabelUtils messages;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxClassController.class);


    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = {"/admin/tax/taxclass/list.html"}, method = RequestMethod.GET)
    public String displayTaxClasses(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {


        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxclass", "taxclass");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);
        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
        TaxClass taxClass = new TaxClass();
        taxClass.setMerchantStore(store);

        model.addAttribute("taxClass", taxClass);

        return ControllerConstants.Tiles.Tax.taxClasses;
    }


    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = "/admin/tax/taxclass/paging.html", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    ResponseEntity<String> pageTaxClasses(HttpServletRequest request,
                                          HttpServletResponse response, Locale locale) throws JsonProcessingException {

        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);

        final HttpHeaders httpHeaders = new HttpHeaders();
        AjaxDataTableResponse ajaxDataTableResponse = new AjaxDataTableResponse();
        List<TaxClassData> taxDataList = new ArrayList<TaxClassData>();
        try {

            List<TaxClass> taxClasses = taxClassService.listByStore(store);

            if (CollectionUtils.isNotEmpty(taxClasses)) {
                ajaxDataTableResponse.setiTotalRecords(taxClasses.size());
                ajaxDataTableResponse.setiTotalDisplayRecords(taxClasses.size());
                for (TaxClass tax : taxClasses) {
                    if (!tax.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
                        TaxClassData taxClassData = new TaxClassData();
                        taxClassData.setId(tax.getId());
                        taxClassData.setCode(tax.getCode());
                        taxClassData.setTitle(tax.getTitle());

                        taxDataList.add(taxClassData);
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error while paging permissions", e);
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
            return new ResponseEntity<String>("", httpHeaders, HttpStatus.OK);
        }


        ajaxDataTableResponse.setData(taxDataList);
        ObjectMapper mapper = new ObjectMapper();
        String returnString = mapper.writeValueAsString(ajaxDataTableResponse);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<String>(returnString, httpHeaders, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = "/admin/tax/taxclass/save.html", method = RequestMethod.POST)
    public String saveTaxClass(@Valid @ModelAttribute("taxClass") TaxClass taxClass, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {


        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxclass", "taxclass");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);

        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
        ResponseData responseData = new ResponseData();

        //requires code and name
        if (taxClass.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
            ObjectError error = new ObjectError("code", messages.getMessage("message.taxclass.alreadyexist", locale));
            result.addError(error);
        }


        //check if the code already exist
        TaxClass taxClassDb = taxClassService.getByCode(taxClass.getCode(), store);

        if (taxClassDb != null) {
            ObjectError error = new ObjectError("code", messages.getMessage("message.taxclass.alreadyexist", locale));
            result.addError(error);
        }

        if (result.hasErrors()) {
            return ControllerConstants.Tiles.Tax.taxClasses;
        }

        taxClassService.create(taxClass);

        responseData.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        responseData.setMessage("Request completed successfully");
        model.addAttribute("response", responseData);

        return ControllerConstants.Tiles.Tax.taxClasses;

    }


    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = "/admin/tax/taxclass/update.html", method = RequestMethod.POST)
    public String updateTaxClass(@Valid @ModelAttribute("taxClass") TaxClass taxClass, BindingResult result, Model model, HttpServletRequest request, Locale locale) throws Exception {


        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxclass", "taxclass");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);

        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
        ResponseData responseData= new ResponseData();

        //requires code and name
        if (taxClass.getCode().equals(TaxClass.DEFAULT_TAX_CLASS)) {
            ObjectError error = new ObjectError("code", messages.getMessage("message.taxclass.alreadyexist", locale));
            result.addError(error);
        }


        //check if the code already exist
        TaxClass taxClassDb = taxClassService.getByCode(taxClass.getCode(), store);

        if (taxClassDb != null && taxClassDb.getId().longValue() != taxClass.getId().longValue()) {
            ObjectError error = new ObjectError("code", messages.getMessage("message.taxclass.alreadyexist", locale));
            result.addError(error);
        }

        if (result.hasErrors()) {
            return ControllerConstants.Tiles.Tax.taxClass;
        }

        taxClassService.update(taxClass);

        responseData.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        responseData.setMessage("Request completed successfully");
        model.addAttribute("response", AjaxResponse.RESPONSE_STATUS_SUCCESS);

        return ControllerConstants.Tiles.Tax.taxClass;

    }


    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = "/admin/tax/taxclass/remove.html", method = {RequestMethod.POST, RequestMethod.GET})
    public String removeTaxClass(HttpServletRequest request, Locale locale, final Model model) throws Exception {

        //do not remove super admin

        long taxClassId = Long.parseLong(request.getParameter("taxClassId"));
        ResponseData response = new ResponseData();

        try {

            /**
             * In order to remove a User the logged in ser must be STORE_ADMIN
             * or SUPER_USER
             */

            TaxClass taxClass = taxClassService.getById(taxClassId);

            if (taxClass == null) {
                LOGGER.error("Invalid taxClassId {} ", taxClassId);
                response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
                response.setMessage(messages.getMessage("message.unauthorized", locale));
                model.addAttribute("response", response);
                return ControllerConstants.Tiles.Tax.taxClasses;
            }

            //look if the taxclass is used for products
            List<Product> products = productService.listByTaxClass(taxClass);

            if (products != null && products.size() > 0) {
                response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
                response.setMessage(messages.getMessage("message.product.association", locale));
                model.addAttribute("response", response);
                return ControllerConstants.Tiles.Tax.taxClasses;
            }

            taxClassService.delete(taxClass);

        } catch (Exception e) {
            LOGGER.error("Error while deleting tax class {} ", e);
            response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
            response.setMessage("Error while deleting tax class");
        }

        // this needs to be moved to common method

        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);
        TaxClass taxClass = new TaxClass();
        taxClass.setMerchantStore(store);

        model.addAttribute("taxClass", taxClass);

        response.setStatus(AjaxResponse.RESPONSE_STATUS_SUCCESS);
        response.setMessage("Request completed successfully");
        model.addAttribute("response", response);  // default status
        return ControllerConstants.Tiles.Tax.taxClasses;

    }

    @PreAuthorize("hasRole('TAX')")
    @RequestMapping(value = "/admin/tax/taxclass/edit.html", method = RequestMethod.GET)
    public String editTaxClass(@ModelAttribute("id") String id, Model model, HttpServletRequest request, HttpServletResponse response, Locale locale) throws Exception {

        Map<String, String> activeMenus = new HashMap<String, String>();
        activeMenus.put("tax", "tax");
        activeMenus.put("taxclass", "taxclass");
        setMenu(model, (Map<String, Menu>) request.getAttribute("MENUMAP"), "tax", activeMenus);

        MerchantStore store = (MerchantStore) request.getAttribute(Constants.ADMIN_STORE);

        TaxClass taxClass = null;
        try {
            Long taxClassId = Long.parseLong(id);
            taxClass = taxClassService.getById(taxClassId);
        } catch (Exception e) {
            LOGGER.error("Cannot parse taxclassid " + id);
            return "redirect:/admin/tax/taxclass/list.html";
        }

        if (taxClass == null || taxClass.getMerchantStore().getId() != store.getId()) {
            return "redirect:/admin/tax/taxclass/list.html";
        }


        model.addAttribute("taxClass", taxClass);

        return ControllerConstants.Tiles.Tax.taxClass;


    }
}
