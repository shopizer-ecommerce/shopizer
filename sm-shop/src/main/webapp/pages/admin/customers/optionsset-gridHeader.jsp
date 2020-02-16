<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



				{title:"<s:message code="label.entity.id" text="Id"/>", name:"id", canFilter:false},
				{title:"<s:message code="label.customer.option.code" text="Option code"/>", name:"optionCode",  canFilter:false},
        		{title:"<s:message code="label.customer.option.name" text="Option name"/>", name:"optionName",  canFilter:false},
        		{title:"<s:message code="label.customer.optionvalue.code" text="Option value code"/>", name:"optionValueCode",  canFilter:false},
        		{title:"<s:message code="label.customer.optionvalue.name" text="Option value name"/>", name:"optionValueName",  canFilter:false},
        		{title:"<s:message code="label.entity.order" text="Sort order"/>", name:"order",  type: "integer", canEdit:true, canFilter:false}
