<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



        	{title:"<s:message code="label.entity.id" text="Id"/>", name: "productId"},
        	{title:"<s:message code="label.entity.name" text="Name"/>", name: "name", width: "70%"},
        	{title:"<s:message code="label.product.sku" text="Sku"/>", name: "sku"}