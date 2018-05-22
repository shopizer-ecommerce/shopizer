<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



	{title:"<s:message code="label.entity.id" text="Id"/>", name:"reviewId", canFilter:false},
    {title:"<s:message code="label.entity.details" text="Name"/>", name:"description"},
    {title:"<s:message code="label.product.reviews.rating" text="Rating"/>", name:"rating"}
