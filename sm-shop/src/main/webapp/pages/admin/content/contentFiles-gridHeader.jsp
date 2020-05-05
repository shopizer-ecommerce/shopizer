<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



			
	
			 {title:"<s:message code="label.entity.name" text="Name"/>", name:"name", canFilter:true},
			 {title:"<s:message code="label.generic.url" text="URL"/>", name:"path", type: "link", canFilter:true},
			 {title:"<s:message code="label.entity.type" text="Type"/>", name:"mimeType", canFilter:false},
			  