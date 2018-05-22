<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	



				{title:"<s:message code="label.entity.id" text="Id"/>", name:"attributeId", canFilter:false},
        		{title:"<s:message code="label.product.productoptions.name" text="Attribute / option name"/>", name:"attribute", canFilter:false},
        		{title:"<s:message code="label.product.productoptiosvalue.title" text="Option value"/>", name:"value", canFilter:false},
        		{title:"<s:message code="label.generic.displayonly" text="Display only"/>", name:"display", type:"boolean", canFilter:false},
        		{title:"<s:message code="label.generic.price" text="Price"/>", name:"price", canFilter:false},
        		{title:"<s:message code="label.entity.order" text="Order"/>", name:"order", canFilter:false},
        		{title:"<s:message code="label.entity.details" text="Details"/>", name: "buttonField", align: "center",canFilter:false,canSort:false, canReorder:false}