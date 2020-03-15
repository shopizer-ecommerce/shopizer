<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



					{title:"<s:message code="label.entity.code" text="Code"/>", name:"code", primaryKey:true, canEdit:false, canFilter:false},
        			{title:"<s:message code="label.entity.name" text="Name"/>", name:"name", canEdit:false, canFilter:true},
        			{title:"<s:message code="label.entity.enabled" text="Enabled"/>", name:"supported", type:"boolean", canEdit:true, canFilter:true}
