<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



				        			{title:"<s:message code="label.entity.id" text="Id"/>", name:"id",canFilter:false},
        							{title:"<s:message code="label.customer.firstname" text="First name"/>", name:"firstName",canFilter:false},
        							{title:"<s:message code="label.customer.lastname" text="Last name"/>", name:"lastName",canFilter:true},
        							{title:"<s:message code="label.generic.email" text="Email"/>", name:"email",canFilter:true},
        							{title:"<s:message code="label.customer.country" text="Customer country"/>", name:"country",canFilter:false},
        							{title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false}