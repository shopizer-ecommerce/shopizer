<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>


<script>




$(document).ready(function() {
	
	
	$('.textAttribute').alphanumeric({ichars:'&=?'});
	
	$('#attributes').on('submit',function (event) {
		$('#attributesBox').showLoading();
		$("#attributesError").hide();
		$("#attributesSuccess").hide();
		var data = $('#attributes').serialize();

	    $.ajax({
	        url: '<c:url value="/admin/customers/attributes/save.html"/>',
	        cache: false,
	        type: 'POST',
	        data : data,
	        success: function(result) {
	            $('#attributesBox').hideLoading();
	               var response = result.response;
                   if (response.status==0) {
                        $("#attributesSuccess").show();
                   } else {
                        $("#attributesError").html(response.message);
                        $("#attributesError").show();
                   }
	        },
			error: function(jqXHR,textStatus,errorThrown) { 
					$('#attributesBox').hideLoading();
					alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
	    });
	    
	    event.preventDefault();
	});
	
	
	
		if($("#code").val()=="") {
			$('.btn').addClass('disabled');
		}


		
		<c:if test="${customer.delivery.state!=null && customer.delivery.state!=''}">  
			$('.delivery-zone-list').hide();  
			$('#delstateOther').show(); 
			$("input[name='showDeliveryStateList']").val('no');
			$('#delstateOther').val('<c:out value="${customer.delivery.state}"/>');
		</c:if>
		<c:if test="${customer.delivery.state==null || customer.delivery.state==''}"> 
			$('.delivery-zone-list').show();			
			$('#delstateOther').hide();
			$("input[name='showDeliveryStateList']").val('yes');
			getDeliveryZones('<c:out value="${customer.delivery.country.isoCode}" />'); 
		</c:if>
	
		<c:if test="${customer.billing.state!=null && customer.billing.state!=''}">
			$('.billing-zone-list').hide();          
			$('#bilstateOther').show(); 
			$("input[name='showBillingStateList']").val('no');
			$('#bilstateOther').val('<c:out value="${customer.billing.state}"/>');
		</c:if>
	
		<c:if test="${customer.billing.state==null || customer.billing.state==''}">  
			$('.billing.zone-list').show();           
			$('#bilstateOther').hide();
			$("input[name='showBillingStateList']").val('yes');
			getBillingZones('<c:out value="${customer.billing.country.isoCode}" />'); 
		</c:if>
	
		
	
		$(".country-list").change(function() {
			getZones($(this).val());
	    })
	
	    $(".billing-country-list").change(function() {
			getBillingZones($(this).val());
	    })
	
	    $(".delivery-country-list").change(function() {
	    	getDeliveryZones($(this).val());
	    })
	    
	    
	    //reset password link
	    $('a[href="#resetPassword"]').click(function(){
	    	var customerId = this.id;
			$('#confirmModal').modal();
		});
		
	    //set credentials link
	    $('a[href="#setCredentials"]').click(function(){
	    	var customerId = this.id;
			$('#credentialsModal').modal();
		});
	    
});

$.fn.addItems = function(data) {
    $(".zone-list > option").remove();
        return this.each(function() {
            var list = this;
            $.each(data, function(index, itemData) {
                var option = new Option(itemData.name, itemData.code);
                list.add(option);
            });
     });
};

function getZones(countryCode){
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/admin/reference/provinces.html"/>',
	  data: 'countryCode=' + countryCode,
	  dataType: 'json',
	  success: function(response){

			var status = isc.XMLTools.selectObjects(response, "/response/status");
			if(status==0 || status ==9999) {
				
				var data = isc.XMLTools.selectObjects(response, "/response/data");
				if(data && data.length>0) {
					$("input[name='showCustomerStateList']").val('yes');
					$('.zone-list').show();  
					$('#stateOther').hide();
					$(".zone-list").addItems(data);					
				} else {
					$("input[name='showCustomerStateList']").val('no');
					$('.zone-list').hide();             
					$('#stateOther').show();
					<c:if test="${stateOther!=null}">
						$('#stateOther').val('<c:out value="${customer.state}"/>');
					</c:if>
				}
			} else {
				$('.zone-list').hide();             
				$('#stateOther').show();
			}

	  
	  },
	  error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }
	  
	});
}															

$.fn.addDeliveryItems = function(data) {
    $(".delivery-zone-list > option").remove();
        return this.each(function() {
            var list = this;
            $.each(data, function(index, itemData) {
                var option = new Option(itemData.name, itemData.code);
                list.add(option);
            });
     });
};

function getDeliveryZones(countryCode){
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/admin/reference/provinces.html"/>',
	  data: 'countryCode=' + countryCode,
	  dataType: 'json',
	  success: function(response){

			var status = isc.XMLTools.selectObjects(response, "/response/status");  
			if(status==0 || status ==9999) {
				
				var data = isc.XMLTools.selectObjects(response, "/response/data");
				if(data && data.length>0) {
					$("input[name='showDeliveryStateList']").val('yes');
					$('.delivery-zone-list').show();  
					$('#delstateOther').hide();
					$(".delivery-zone-list").addDeliveryItems(data);					
					<c:if test="${customer.delivery.zone!=null}">
						$('.delivery-zone-list').val('<c:out value="${customer.delivery.zone.code}"/>');
					</c:if>
				} else {
					$("input[name='showDeliveryStateList']").val('no');
					$('.delivery-zone-list').hide();             
					$('#delstateOther').show();
					<c:if test="${delstateOther!=null}">
						$('#delstateOther').val('<c:out value="${customer.delivery.state}"/>');
					</c:if>
				}
			} else {
				$('.delivery-zone-list').hide();             
				$('#delstateOther').show();
			}

	  
	  },
	  error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }
	});
}

$.fn.addBillingItems = function(data) {
	    $(".billing-zone-list > option").remove();
	        return this.each(function() {
	            var list = this;
	            $.each(data, function(index, itemData) {
	                var option = new Option(itemData.name, itemData.code);
	                list.add(option);
	            });
	     });
};

function getBillingZones(countryCode){
		$.ajax({
		  type: 'POST',
		  url: '<c:url value="/admin/reference/provinces.html"/>',
		  data: 'countryCode=' + countryCode,
		  dataType: 'json',
		  success: function(response){

				var status = isc.XMLTools.selectObjects(response, "/response/status");
				if(status==0 || status ==9999) {
					
					var data = isc.XMLTools.selectObjects(response, "/response/data");
					if(data && data.length>0) {
						$("input[name='showBillingStateList']").val('yes');
						$('.billing-zone-list').show();  
						$('#bilstateOther').hide();
						$(".billing-zone-list").addBillingItems(data);					
						<c:if test="${customer.billing.zone!=null}">
							$('.billing-zone-list').val('<c:out value="${customer.billing.zone.code}"/>');
						</c:if>
					} else {
						$("input[name='showBillingStateList']").val('no');
						$('.billing-zone-list').hide();             
						$('#bilstateOther').show();
						<c:if test="${bilstateOther!=null}">
							$('#bilstateOther').val('<c:out value="${customer.billing.state}"/>');
						</c:if>
					}
				} else {
					$('.billing-zone-list').hide();             
					$('#bilstateOther').show();
				}

		  
		  },
		  error: function(xhr, textStatus, errorThrown) {
		  	alert('error ' + errorThrown);
		  }
		  
		});
}


function resetCustomerPassword(customerId){
		$('#customerError').hide();
		$('#customerSuccess').hide();
		$('#confirmationInnerBox').showLoading({
                'indicatorZIndex' : 1000001,
                'overlayZIndex': 1000000
		})
		$.ajax({
		  type: 'POST',
		  url: '<c:url value="/admin/customers/resetPassword.html"/>',
		  data: 'customerId=' + customerId,
		  dataType: 'json',
		  success: function(response){
			   $('#confirmationInnerBox').hideLoading();
			   $('#confirmModal').modal('hide');
				var status = isc.XMLTools.selectObjects(response, "/response/status");
				if(status==0 || status ==9999) {
					
					$('#customerSuccess').html('<s:message code="message.password.reset" text="Password has been reset" />');
					$('#customerSuccess').show();
					
				} else {
					$('#customerError').html('<s:message code="message.error" text="An error occured" />');
					$('#customerError').show();
				}


		  
		  },
		  error: function(xhr, textStatus, errorThrown) {
		  	//alert('error ' + errorThrown);
		  	$('#confirmationInnerBox').hideLoading();
			$('#confirmModal').modal('hide');
		  	$('#customerError').html('<s:message code="message.error" text="An error occured" />');
			$('#customerError').show();
		  }
		  
		});
}


function setCredentials(customerId, userName, password){
	$('#customerError').hide();
	$('#customerSuccess').hide();
	$('#crConfirmationInnerBox').showLoading({
            'indicatorZIndex' : 1000001,
            'overlayZIndex': 1000000
	})
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/admin/customers/setCredentials.html"/>',
	  data: 'customerId=' + customerId + '&userName=' + userName + '&password=' + password,
	  dataType: 'json',
	  success: function(response){
		   $('#crConfirmationInnerBox').hideLoading();
		   $('#confirmModal').modal('hide');
			var status = isc.XMLTools.selectObjects(response, "/response/status");
			if(status==0 || status ==9999) {
				
				$('#customerSuccess').html('<s:message code="message.credentials.reset" text="Credentials have been changed" />');
				$('#customerSuccess').show();
				
			} else {
				$('#customerError').html('<s:message code="message.error" text="An error occured" />');
				$('#customerError').show();
			}


	  
	  },
	  error: function(xhr, textStatus, errorThrown) {
	  	//alert('error ' + errorThrown);
	  	$('#confirmationInnerBox').hideLoading();
		$('#confirmModal').modal('hide');
	  	$('#customerError').html('<s:message code="message.error" text="An error occured" />');
		$('#customerError').show();
	  }
	  
	});
}


</script>


<div id="tabbable" class="tabbable">


				<jsp:include page="/common/adminTabs.jsp" />
				
				<h3>
				
				

				
				
					<c:choose>
						<c:when test="${customer.id!=null && customer.id>0}">
								<s:message code="label.customer.editcustomer" text="Edit Customer" /> <c:out value="${category.code}"/>
						</c:when>
						<c:otherwise>
								<s:message code="label.customer.createcustomer" text="Create Customer" />
						</c:otherwise>
					</c:choose>
					
				</h3>	
				<br/>
				
				<c:if test="${customer.id!=null && customer.id>0}">
				<div class="btn-group" style="z-index:400000;">
                    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><s:message code="label.generic.moreoptions" text="More options"/> ... <span class="caret"></span></button>
                     <ul class="dropdown-menu">
				    	<li><a id="${customer.id}" href="#resetPassword"><s:message code="button.label.resetpassword" text="Reset password" /></a></li>
				    	<li><a id="${customer.id}" href="#setCredentials"><s:message code="button.label.setcredentials" text="Set credentials" /></a></li>
                     </ul>
                </div><!-- /btn-group -->
			    <br/>
				</c:if>
				
				<c:set var="customerAttr" value="${customer}"/>


				<c:url var="saveCustomer" value="/admin/customers/save.html"/>


				<form:form method="POST" commandName="customer" action="${saveCustomer}">
				
					<form:errors id="customer.error" path="*" cssClass="alert alert-error" element="div" />
					<div id="customerError" class="alert alert-error" style="display:none;"></div>
					<div id="customerSuccess" class="alert alert-success" 
							style="<c:choose>
								<c:when test="${success!=null}">display:block;</c:when>
								<c:otherwise>display:none;</c:otherwise></c:choose>">
								<s:message code="message.success" text="Request successful"/>
					</div>    
					
					<form:hidden id="customerId" path="id" /> 
					<form:hidden path="merchantStore.id" />	
					<form:hidden path="showCustomerStateList" />
					<form:hidden path="showBillingStateList" /> 
					<form:hidden path="showDeliveryStateList" />  	
						

			    	<h3><s:message code="label.customer.billinginformation" text="Billing information"/></h3>
			        <div class="control-group">
	                        <label><s:message code="label.customer.firstname" text="First Name"/></label>
	                        <div class="controls">
	                        		<form:input  cssClass="input-large highlight"  maxlength="64"  path="billing.firstName"/>
	                                <span class="help-inline"><form:errors path="billing.FirstName" cssClass="error" /></span>
	                        </div>
	                </div>
	                <div class="control-group">
	                        <label><s:message code="label.customer.lastname" text="Last Name"/></label>
	                        <div class="controls">
	                        		<form:input  cssClass="input-large highlight"  maxlength="64"  path="billing.lastName"/>
	                                <span class="help-inline"><form:errors path="billing.lastName" cssClass="error" /></span>
	                        </div>
	                </div>
	                <div class="control-group">
	                        <label><s:message code="label.generic.username" text="User Name"/></label>
	                        <div class="controls">
	                        		<span class="input-large uneditable-input">${customer.nick}</span><form:hidden path="nick" />
	                        </div>
	                </div>


					<address>
					
						<div class="controls">
		              		<label><s:message code="label.customer.billing.company" text="Company"/></label>
		              		<form:input  cssClass="input-large"  maxlength="100" path="billing.company"/>	
			            </div>

			            <div class="controls">
			            	<label><s:message code="label.customer.billing.streetaddress" text="Street Address"/></label>
				 			<form:input  cssClass="input-large highlight"  maxlength="256"  path="billing.address"/>		 				
			            </div>
			            <div class="controls">
			            	<label><s:message code="label.customer.billing.city" text="City"/></label>
				 			<form:input  cssClass="input-large highlight"  maxlength="100" path="billing.city"/>
			            </div>
		            
 	 		            <div class="control-group">
	                        <label><s:message code="label.customer.billing.country" text="Country"/></label>
	                        <div class="controls"> 				       							
	       							<form:select cssClass="billing-country-list" path="billing.country.isoCode">
		  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
	       							</form:select>
                                 	<span class="help-inline"><form:errors path="billing.country.isoCode" cssClass="error" /></span>
	                        </div>  
	                    </div> 
	                 
	                    <div class="control-group"> 
	                        <label><s:message code="label.customer.billing.zone" text="State / Province"/></label>
	                        <div class="controls">		       							
	       							<form:select cssClass="billing-zone-list" path="billing.zone.code"/>
                      				<form:input  class="input-large highlight" id="bilstateOther" maxlength="100"  name="bilstateOther" path="billing.state" /> 				       							
                                 	<span class="help-inline"><form:errors path="billing.zone.code" cssClass="error" /></span>
	                        </div>
	                    </div>  
	                  
	                    <div class="controls">
	                   		<label><s:message code="label.customer.billing.postalcode" text="Postal code"/></label>
			 				<form:input id="billingPostalCode" cssClass="input-large highlight" maxlength="20"  path="billing.postalCode"/>
			 				<span class="help-inline"><form:errors path="billing.postalCode" cssClass="error" /></span>
			            </div>	     
		              	            	            	            				
				  </address>		
					
					 <div class="control-group">
	                        <label><s:message code="label.customer.email" text="Email"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight"  maxlength="96" path="emailAddress" />
	                                    <span class="help-inline"><form:errors path="emailAddress" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                   <div class="control-group">
	                        <label><s:message code="label.customer.telephone" text="Phone"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight"  maxlength="32" path="billing.telephone" />
	                                    <span class="help-inline"><form:errors path="billing.telephone" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.defaultlanguage" text="Default language"/></label>
	                        <div class="controls">

	                        					<form:select class="input-large" items="${languages}" itemValue="id" itemLabel="code" path="defaultLanguage.id"/> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>

	 					
				<h3><s:message code="label.customer.shippinginformation" text="Shipping information"/></h3>
				<address>
			            <div class="controls">
		              		<label><s:message code="label.customer.shipping.company" text="Company"/></label>
		              		<form:input  cssClass="input-large"  maxlength="100" path="delivery.company"/>	
			            </div>
			            <div class="controls">
		              		<label><s:message code="label.customer.shipping.firstName" text="First name"/></label>
		              		<form:input  cssClass="input-large"  maxlength="64" path="delivery.firstName"/>	
			            </div>
			            <div class="controls">
		              		<label><s:message code="label.customer.shipping.lastName" text="Last name"/></label>
		              		<form:input  cssClass="input-large"  maxlength="64" path="delivery.lastName"/>	
			            </div>
			            <div class="controls">
			            	<label><s:message code="label.customer.shipping.streetaddress" text="Street Address"/></label>
				 			<form:input  cssClass="input-large"  maxlength="256" path="delivery.address"/>		 				
			            </div>
			            <div class="controls">
			            	<label><s:message code="label.customer.shipping.city" text="City"/></label>
				 			<form:input  cssClass="input-large"  maxlength="100" path="delivery.city"/>
			            </div>
	            
 	 		           <div class="control-group">
	                        <label><s:message code="label.customer.shipping.country" text="Country"/></label>
	                        <div class="controls"> 				       							
	       							<form:select cssClass="delivery-country-list" path="delivery.country.isoCode">
		  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
	       							</form:select>
                                 	<span class="help-inline"><form:errors path="delivery.country.isoCode" cssClass="error" /></span>
	                        </div>
	                    </div>  
     	  	         
	                    <div class="control-group"> 
	                        <label><s:message code="label.customer.shipping.zone" text="State / Province"/></label>
	                        <div class="controls">		       							
	       							<form:select cssClass="delivery-zone-list" path="delivery.zone.code"/>
                      				<form:input  class="input-large" id="delstateOther"  maxlength="100" name="delstateOther" path="delivery.state" /> 				       							
                                 	<span class="help-inline"><form:errors path="delivery.zone.code" cssClass="error" /></span>
	                        </div> 
	                    </div>  
	                    
	                    <div class="controls">
	                   		<label><s:message code="label.customer.shipping.postalcode" text="Postal code"/></label>
			 				<form:input id="deliveryPostalCode" cssClass="input-large" maxlength="20"  path="delivery.postalCode"/>
			 				<span class="help-inline"><form:errors path="delivery.postalCode" cssClass="error" /></span>
			            </div>	       	            	            	            				
				</address>
				
				<c:if test="${fn:length(groups)>0}">
				  <div class="control-group">
	                        <label><s:message code="label.groups.title" text="Groups"/></label>
	                        <div class="controls">
	                        	<form:checkboxes cssClass="highlight" items="${groups}" itemValue="id" itemLabel="groupName" path="groups" delimiter="<br/>" /> 
	                            <span class="help-inline"><form:errors path="groups" cssClass="error" /></span>
	                        </div>
	              </div>
	              
	             </c:if>


		        <div class="form-actions">
                 	  <div class="pull-right">
                 			<button type="submit" class="btn btn-success"><s:message code="button.label.save" text="Save"/></button>
                 	  </div> 
           	   </div>


      					
				</form:form>

				<c:if test="${customerAttr.id!=null && customerAttr.id>0}">
				
				
				<!-- properties -->
				<!--  @ModelAttribute("optionList") List<Option> options  -->

				<c:if test="${options!=null && fn:length(options)>0}">
				
				<div id="attributesSuccess" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
	            <div id="attributesError" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>
				<div id="attributesBox" class="box">
						<span class="box-title">
						<p><s:message code="label.customer.attributes" text="Customer attributes" /></p>
						</span>
				
				
					
					<form id="attributes">
					<input id="customer" type="hidden" value="<c:out value="${customerAttr.id}"/>" name="customer">
					<c:forEach items="${options}" var="option" varStatus="status">
						<div class="control-group"> 
	                        <label><c:out value="${option.name}"/></label>
	                        <div class="controls">	       							
									<c:choose>
										<c:when test="${option.type=='Select'}">
											<select id="<c:out value="${option.id}"/>" name="<c:out value="${option.id}"/>">
											<c:forEach items="${option.availableValues}" var="optionValue">
												<option value="${optionValue.id}" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> SELECTED</c:if>>${optionValue.name}</option>
											</c:forEach>
											</select>
										</c:when>
										<c:when test="${option.type=='Radio'}">
											<c:forEach items="${option.availableValues}" var="optionValue">
												<input type="radio" id="<c:out value="${option.id}"/>" name="<c:out value="${option.id}"/>" value="<c:out value="${optionValue.id}"/>" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> checked="checked" </c:if> />
												<c:out value="${optionValue.name}"/>
											</c:forEach>
										</c:when>
										<c:when test="${option.type=='Text'}">
											<input class="textAttribute" type="text" id="<c:out value="${option.id}"/>-<c:out value="${option.availableValues[0].id}"/>" name="<c:out value="${option.id}"/>-<c:out value="${option.availableValues[0].id}"/>" class="input-large" value="<c:if test="${option.defaultValue!=null}">${option.defaultValue.name}</c:if>">
										</c:when> 
										<c:when test="${option.type=='Checkbox'}">
											<c:forEach items="${option.availableValues}" var="optionValue">
												<input type="checkbox" id="<c:out value="${option.id}"/>-<c:out value="${optionValue.id}"/>" name="<c:out value="${option.id}"/>-<c:out value="${optionValue.id}"/>" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> checked="checked" </c:if>  />
												<c:out value="${optionValue.name}"/>
											</c:forEach>
										</c:when>										
										
										
									</c:choose>				       							
                                 	<span class="help-inline"></span>
	                        </div>
	                    </div> 

					
					</c:forEach>
					
					<div class="form-actions">
                 	  <div class="pull-right">
                 			<button type="submit" class="btn btn-success"><s:message code="button.label.save" text="Save"/></button>
                 	  </div> 
           	  		 </div>


      					
				</form>
				</div>
				</c:if>
				</c:if>
</div>




<div id="confirmModal"  class="modal hide" style="z-index:600000" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <span id="confirmationInnerBox">
  <div class="modal-header">
          <button type="button" class="close close-modal" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 id="modalTitle"><s:message code="label.generic.confirm" text="Please confirm!" /></h3>
  </div>
  <div class="modal-body">
           <p id="modalMessage">
				<s:message code="label.customer.resetpasswor.confirm" text="Are you sure you want to reset the customer password?" />
           </p>
  </div>  
  <div class="modal-footer">
  
  		   <button class="btn btn-primary" aria-hidden="true"
	  		   	onClick="resetCustomerPassword( $('#customerId').val() );" >
	  		   	<s:message  code="button.label.ok" text="-" />
	  	   </button>
  		   	
  		   	
           <button class="btn cancel-modal" data-dismiss="modal" aria-hidden="true"><s:message code="button.label.cancel" text="Cancel" /></button>
           <button class="btn btn-success close-modal" id="closeModal" data-dismiss="modal" aria-hidden="true" style="display:none;"><s:message code="button.label.close" text="Close" /></button>
  </div>
  </span>
</div>


<div id="credentialsModal"  class="modal hide" style="z-index:650000" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <span id="crConfirmationInnerBox">
  <div class="modal-header">
          <button type="button" class="close close-modal" data-dismiss="modal" aria-hidden="true">X</button>
          <h3 id="modalTitle"><s:message code="button.label.setcredentials" text="Set credentials" /></h3>
  </div>
  <div class="modal-body">

           <p id="modalMessage">
           		    <label>
           		    	<s:message code="label.generic.username" text="User Name"/>
           		    </label>&nbsp;<input type="text" id="crUserName" name="crUserName" class="input-small">            		    
           </p>
           <p id="modalMessage">
                    <label>
           		    	<s:message code="label.generic.password" text="Password"/>
           		    </label>&nbsp;<input type="text" id="crPassword" name="crPassworde" class="input-small"> 
           </p>
  </div>  
  <div class="modal-footer">
  
  		   <button class="btn btn-primary" aria-hidden="true"
	  		   	onClick="setCredentials( $('#customerId').val(), $('#crUserName').val(), $('#crPassword').val() );" >
	  		   	<s:message  code="button.label.submit2" text="Submit" />
	  	   </button>
  		   	
  		   	
           <button class="btn cancel-modal" data-dismiss="modal" aria-hidden="true"><s:message code="button.label.cancel" text="Cancel" /></button>
           <button class="btn btn-success close-modal" id="closeModal" data-dismiss="modal" aria-hidden="true" style="display:none;"><s:message code="button.label.close" text="Close" /></button>
  </div>
  </span>
</div>

