package com.salesmanager.shop.utils;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import com.salesmanager.core.model.common.Criteria;
import com.salesmanager.core.model.common.CriteriaOrderBy;
import com.salesmanager.core.model.merchant.MerchantStoreCriteria;

public class ServiceRequestCriteriaBuilderUtils {
  
  public static Criteria buildRequest(Map<String, String> mappingFields, HttpServletRequest request) {
    
    /**
     * Works assuming datatable sends query data
     */
    MerchantStoreCriteria criteria = new MerchantStoreCriteria();

    String searchParam = request.getParameter("search[value]");
    String orderColums = request.getParameter("order[0][column]");

    if (!StringUtils.isBlank(orderColums)) {
      String columnName = request.getParameter("columns[" + orderColums + "][data]");
      String overwriteField = columnName;
      if (mappingFields != null && mappingFields.get(columnName) != null) {
        overwriteField = mappingFields.get(columnName);
      }
      criteria.setCriteriaOrderByField(overwriteField);
      criteria.setOrderBy(
          CriteriaOrderBy.valueOf(request.getParameter("order[0][dir]").toUpperCase()));
    }
    
    criteria.setSearch(searchParam);

    return criteria;
    
  }

}
