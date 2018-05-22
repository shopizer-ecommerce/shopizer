<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
<script type="text/javascript">
var priceFormatMessage = '<s:message code="message.price.cents" text="Wrong format" />';
</script>				

<script src="<c:url value="/resources/js/jquery.formatCurrency-1.4.0.js" />"></script>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
<script src="<c:url value="/resources/js/adminFunctions.js" />"></script>

<script type="text/javascript">

$(document).ready(function() {

	$('#productPriceAmount').numeric({allow:"."});
	$('#order').numeric();
	$('#weight').numeric({allow:"."});
	$("#productOption").change(function() {
		if($('#displayOnly').attr('checked')) {
			checkReadOnlyAttribute($(this).val());
		}
	})
	
	var optionId = $('#productOption').find(":selected").val();
	checkReadOnlyAttribute(optionId);
	
	$( "#productOption" ).change(function() {
		checkReadOnlyAttribute($(this).val());
	});


});
	
function checkReadOnlyAttribute(optionId){
		//$('#displayOnly').removeAttr("disabled");
		$.ajax({
			  type: 'POST',
			  url: '<c:url value="/admin/products/attributes/getAttributeType.html"/>',
			  data: 'optionId=' + optionId,
			  dataType: 'json',
			  success: function(response){
		
					var status = isc.XMLTools.selectObjects(response, "/response/status");
					if(status==0 || status ==9999) {

						var type = isc.XMLTools.selectObjects(response, "/response/data/type");
						if(type=='text') {
							//$('#displayOnly').prop("checked", true);
							//$('#displayOnly').attr("disabled", true);
							$("#attributeValueText").show();
							$("#optionValue").hide();
						} else {
							$("#attributeValueText").hide();
							$("#optionValue").show();
						}
						
					} else {
						
						//display message
						$(".alert-error").show();
					}
		
			  
			  },
			  error: function(xhr, textStatus, errorThrown) {
			  	alert('error ' + errorThrown);
			  }
			  
		});
}
	
	
</script>



<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								<c:if test="${product.id!=null && product.id>0}">
									<c:set value="${product.id}" var="productId" scope="request"/>
									<jsp:include page="/pages/admin/products/product-menu.jsp" />
								</c:if>		
								
								
				<h3>
						<s:message code="label.product.attribute" text="Attribute" />
				</h3>
				<br/>
				<strong><c:out value="${product.sku}"/></strong>		
				

				<br/><br/>
								



				<c:url var="attributeSave" value="/admin/attributes/attribute/save.html"/>


				<form:form method="POST" commandName="attribute" action="${attributeSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
						
						
						
			    <div class="control-group">
                        <label><s:message code="label.product.attribute.option.name" text="Option / attribute name"/></label>
                        <div class="controls">    
	                        <div class="controls">		
	                        		<form:select cssClass="highlight" id="productOption" path="productOption.id">
					  					<form:options items="${options}" itemValue="id" itemLabel="descriptionsSettoList[0].name"/>
				       				</form:select>
	                                <span class="help-inline"><form:errors path="productOption.id" cssClass="error" /></span>
	                        </div>
                        </div>
                 </div>	

                 <div class="control-group">
                        <label><s:message code="label.product.attribute.display" text="Display only"/></label>
                        <div class="controls">
                                    <form:checkbox id="displayOnly" path="attributeDisplayOnly"/>
                                    <span class="help-inline"><form:errors path="attributeDisplayOnly" cssClass="error" /></span>
                        </div>
                  </div>

                  
                 <div class="control-group" id="optionValue" style="display:<c:choose><c:when test="${attribute.productOption.productOptionType=='text'}">none;</c:when><c:otherwise>block;</c:otherwise></c:choose>">
                        <label><s:message code="label.product.productoptiosvalue.title" text="Option value name"/></label>
                        <div class="controls">    
	                        <div class="controls">		
	                        		<form:select cssClass="highlight" path="productOptionValue.id">
					  					<form:options items="${optionsValues}" itemValue="id" itemLabel="descriptionsSettoList[0].name"/>
				       				</form:select>
	                                <span class="help-inline"><form:errors path="productOptionValue.id" cssClass="error" /></span>
	                        </div>
                        </div>
                 </div>		
                 
                 
                 <div class="control-group" id="attributeValueText" style="display:<c:choose><c:when test="${attribute.productOption.productOptionType=='text'}">block;</c:when><c:otherwise>none;</c:otherwise></c:choose>">
                 <c:forEach items="${attribute.productOptionValue.descriptionsSettoList}" var="description" varStatus="counter">
	                  
		                 
		                        <label class="required"><s:message code="label.product.attribute.value" text="Attribute value"/> (<c:out value="${description.language.code}"/>)</label>
		                        <div class="controls">
		                        			<form:input cssClass="input-xlarge" id="name${counter.index}" path="productOptionValue.descriptionsList[${counter.index}].description"/>
		                        			<span class="help-inline"><form:errors path="productOptionValue.descriptionsList[${counter.index}].description" cssClass="error" /></span>
		                        </div>
		                  
		                  		<form:hidden path="productOptionValue.descriptionsList[${counter.index}].language.code" />
		                  		<form:hidden path="productOptionValue.descriptionsList[${counter.index}].language.id" />
		                  		<form:hidden path="productOptionValue.descriptionsList[${counter.index}].id" />
		                  		<form:hidden path="productOptionValue.id" />
		                  		<form:hidden path="productOptionValue.code" />
	                  
	               </c:forEach>
                   </div>

						
				 <div class="control-group">
                        <label><s:message code="label.product.productoptions.price" text="Price"/></label>
                        <div class="controls">
                                    <form:input id="productPriceAmount" cssClass="highlight" path="attributePrice"/>
                                    <span id="help-price" class="help-inline"><form:errors path="attributePrice" cssClass="error" /></span>
                        </div>
                  </div>
                  
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.entity.order" text="Order"/></label>
                        <div class="controls">
                                    <form:input id="order" cssClass="highlight" path="attributeSortOrder"/>
                                    <span class="help-inline"><form:errors path="attributeSortOrder" cssClass="error" /></span>

                        </div>
                  </div>	



                  
                  <div class="control-group">
                        <label><s:message code="label.product.attribute.default" text="Default"/></label>
                        <div class="controls">
                                    <form:checkbox path="attributeDefault"/>	
                                    <span class="help-inline"><form:errors path="attributeDefault" cssClass="error" /></span>
                        </div>
                  </div>
                  
                 <div class="control-group">
                        <label><s:message code="label.product.attribute.required" text="Required"/></label>
                        <div class="controls">
                                   	<form:checkbox path="attributeRequired"/>
                                    <span class="help-inline"><form:errors path="attributeRequired" cssClass="error" /></span>
                        </div>
                  </div>
                  

                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.product.attribute.otherweight" text="Additional weight"/></label>
                        <div class="controls">
                                    <form:input id="weight" cssClass="highlight" path="attributeAdditionalWeight"/>
                                    <span class="help-inline"><form:errors path="attributeAdditionalWeight" cssClass="error" /></span>

                        </div>
                  </div>                 



                  <form:hidden path="id" />
                  <form:hidden path="product.id" />
			
			      <div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  			

                  		</div>

            	 </div>
 
            	 </form:form>
            	 
            	 
            	 <br/>
            	 

            	 
            	 
	      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     