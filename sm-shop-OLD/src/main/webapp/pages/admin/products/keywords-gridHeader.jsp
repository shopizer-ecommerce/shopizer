<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



	{title:"<s:message code="label.entity.id" text="Id"/>", name:"code", canFilter:false},
    {title:"<s:message code="label.generic.language" text="Language"/>", name:"language"},
    {title:"<s:message code="label.generic.keyword" text="Keyword"/>", name:"keyword"}
