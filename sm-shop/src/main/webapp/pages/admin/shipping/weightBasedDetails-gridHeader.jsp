<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



					{title:"<s:message code="label.shipping.maximumWeight" text="Maximum weight"/>", name:"weight", primaryKey:true, canEdit:false, canFilter:false},
        			{title:"<s:message code="label.generic.price" text="Price"/>", name:"price", canEdit:false, canFilter:false}
