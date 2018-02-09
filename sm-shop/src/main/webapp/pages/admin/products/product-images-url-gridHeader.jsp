<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	


				{title:"<s:message code="label.product.image" text="Image"/>", name:"image", type:"image", canEdit:false, imageWidth:75, canFilter:false},
				{title:"<s:message code="label.generic.url" text="URL"/>", name:"url", canEdit:false, canFilter:false},
        		{title:"<s:message code="label.generic.default" text="Default"/>", name:"default", canEdit:false, type:"boolean", canFilter:false},
        		{title:"<s:message code="label.entity.id" text="ID"/>", name:"id", canEdit:false, canFilter:false}
        		