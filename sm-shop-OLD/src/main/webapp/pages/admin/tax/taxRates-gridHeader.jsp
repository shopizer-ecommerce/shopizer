<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>	



			      				{title:"<s:message code="label.entity.id" text="Id"/>", name:"taxRateId", canFilter:false},
			      				{title:"<s:message code="label.generic.country.code" text="Country dode"/>", name:"country", canFilter:false},	
			      				{title:"<s:message code="label.generic.stateprovince" text="State / province"/>", name:"zone", canFilter:true},
			      				{title:"<s:message code="label.entity.code" text="Code"/>", name:"code", canFilter:false},	
			      				{title:"<s:message code="label.entity.name" text="Name"/>", name:"name", canFilter:false},
			      				{title:"<s:message code="menu.taxrates.rate" text="Rate"/>", name:"rate", canFilter:false},
			      				{title:"<s:message code="label.entity.order" text="Order"/>", name:"priority", canFilter:false},
			      				{title:"<s:message code="label.tax.compound" text="Compound"/>", name:"piggyback", type:"boolean", canFilter:false},
			      				{title:"<s:message code="label.taxclass" text="Tax class"/>", name:"taxClass", canFilter:false},
			      				{title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}
