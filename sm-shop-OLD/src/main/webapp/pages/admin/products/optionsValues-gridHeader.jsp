<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	

<!-- {title:"<s:message code="label.product.image" text="Image"/>", name:"image", type:"image", imageWidth:75, imageURLPrefix:"<c:url value='/static/'/>", canFilter:false}, -->

	{title:"<s:message code="label.entity.id" text="Id"/>", name:"optionValueId", canFilter:false},
    {title:"<s:message code="label.entity.name" text="Name"/>", name:"name",  canFilter:false},
    {title:"<s:message code="label.product.image" text="Image"/>", name:"image", type:"image", imageWidth:75, imageURLPrefix:"<c:url value='/'/>", canFilter:false},
    {title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}
