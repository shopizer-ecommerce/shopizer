<%
    response.setCharacterEncoding( "UTF-8" );
    response.setHeader( "Cache-Control", "no-cache" );
    response.setHeader( "Pragma", "no-cache" );
    response.setDateHeader( "Expires", -1 );
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


<!-- requires functions.jsp -->
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
<script src="<c:url value="/resources/js/address.js" />"></script>



<script type="text/javascript">

$(document).ready(function() {
	//triggers form validation
	isFormValid();
	$("input[type='text']").on("change keyup paste", function(){
		isFormValid();
	});
	//apply mask
	setCountrySettings('customer',$('#customer_country').val());
	//populate zones combo
	getZones($('#customer_country').val(),'<c:out value="${address.zone}" />',isFormValid);
	$("#customer_country").change(function() {
			getZones($(this).val(),'<c:out value="${address.zone}" />',isFormValid);
			setCountrySettings('customer',$('#customer_country').val());
	})
	
	
});

function isFormValid() {
	
	if($('.alert-error').is(":visible")) {
		return true;
	}
	
	if($('.alert-success').is(":visible")) {
		return true;
	}
	
	var msg = isCustomerFormValid($('#changeAddressForm'));

	if(msg!=null) {//disable submit button
		$('#submitAddress').addClass('btn-disabled');
		$('#submitAddress').prop('disabled', true);
		$('#formError').html(msg);
		$('#formError').show();
		return false;
	} else {
		$('#submitAddress').removeClass('btn-disabled');
		$('#submitAddress').prop('disabled', false);
		$('#formError').hide();
		return true;
	}
}

</script>

<c:url var="updateAddress" value="/shop/customer/updateAddress.html"/>
<div id="main-content" class="container clearfix row-fluid">
		<div class="span12 common-row">

			  <div class="span8 col-md-8 no-padding">

				<div class="checkout-box">
					<span class="box-title">
						<p class="p-title">
							
							  <c:choose>
							    <c:when test="${address.billingAddress eq true}">
							    	<s:message code="label.customer.edit.billingaddress" text="Edit Billing Address" />
							    </c:when>
							    <c:otherwise>
							    	<s:message code="label.customer.edit.shippinginformation" text="Edit Shipping Address" />
							    </c:otherwise>
							  </c:choose>

						</p>
					</span>
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>
					<div id="formError"  class="alert alert-warning" style="display:none;"></div>
					<form:form method="POST" id="changeAddressForm" commandName="address" action="${updateAddress}">
				         <!-- TODO REMOVE -->
				         <input type="hidden" name="customerId" value="${customerId}">
				         <input type="hidden" name="billingAddress" value="${address.billingAddress}">
				       
						<form:errors id="address.error" path="*" cssClass="alert alert-error" element="div" />
						<form:hidden path="${billingAddress}"/>
					
				        <div class="control-group form-group">
		                        <label><s:message code="label.customer.firstname" text="First Name"/></label>
		                        <div class="controls">
		                        		<s:message code="NotEmpty.customer.firstName" text="First name is required" var="msgFirstName"/>
		                        		<form:input  cssClass="input-large required form-control form-control-md"  maxlength="64"  path="firstName" title="${msgFirstName}"/>
		                                <span class="help-inline"><form:errors path="firstName" cssClass="error" /></span>
		                        </div>
		                </div>
		                <div class="control-group form-group">
		                        <label><s:message code="label.customer.lastname" text="Last Name"/></label>
		                        <div class="controls">
		                                <s:message code="NotEmpty.customer.lastName" text="Last name is required" var="msgLastName"/>
		                        		<form:input  cssClass="input-large required form-control form-control-md"  maxlength="64"  path="lastName" title="${msgLastName}"/>
		                                <span class="help-inline"><form:errors path="lastName" cssClass="error" /></span>
		                        </div>
		                </div>

					   <div class="control-group form-group">
						<label><s:message code="label.customer.companyname" text="Company"/></label>
						<div class="controls">
		              		
		              		<form:input  cssClass="input-large form-control form-control-md"  maxlength="100" path="company"/>	
			            </div>
			            </div>

						<div class="control-group form-group">
			            	<label><s:message code="label.customer.streetaddress" text="Street Address"/></label>
			            <div class="controls">
			            	
			            	<s:message code="NotEmpty.customer.address2" text="Address is required" var="msgAddress"/>
				 			<form:input  cssClass="input-large required form-control form-control-md"  maxlength="256"  path="address" title="${msgAddress}"/>		 				
			            </div>
			            </div>
			            
			            <div class="control-group form-group">
			            	<label><s:message code="label.customer.city" text="City"/></label>
			            <div class="controls">
			            	
			            	<s:message code="NotEmpty.customer.city" text="City is required" var="msgCity"/>
				 			<form:input  cssClass="input-large required form-control form-control-md"  maxlength="100" path="city" title="${msgCity}"/>
			            </div>
		            	</div>
		            
 	 		            <div class="control-group form-group">
	                        <label><s:message code="label.customer.country" text="Country"/></label>
	                        <div class="controls"> 				       							
	       							<form:select cssClass="billing-country-list form-control form-control-lg" path="country" id="customer_country">
		  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
	       							</form:select>
                                 	<span class="help-inline"><form:errors path="country" cssClass="error" /></span>
	                        </div>  
	                    </div> 
	                 
	                    <div class="control-group form-group"> 
	                        <label><s:message code="label.customer.zone" text="State / Province"/></label>
	                        <div class="controls">		       							
	       							<form:select cssClass="billing-zone-list form-control form-control-lg" path="zone" id="customer_zones"/>
	       							<s:message code="NotEmpty.customer.stateProvince" text="State / Province is required" var="msgStateProvince"/>
                      				<form:input  class="input-large required form-control form-control-md" id="hidden_zones" maxlength="100"  name="stateProvince" path="stateProvince" title="${msgStateProvince}" />		       							
                                 	<span class="help-inline"><form:errors path="zone" cssClass="error" /></span>
	                        </div>
	                    </div>  
	                  
	                  <div class="control-group form-group">
	                  	<label><s:message code="label.generic.postalcode" text="Postal code"/></label> 
	                    <div class="controls">
			 				<s:message code="NotEmpty.customer.billing.postalCode" text="Postal code is required" var="msgPostalCode"/>
			 				<form:input id="billingPostalCode" cssClass="input-large required customer-postalCode form-control form-control-md" maxlength="20"  path="postalCode" title="${msgPostalCode}"/>
			 				<span class="help-inline"><form:errors path="postalCode" cssClass="error" /></span>
			            </div>	     
		            </div>
	                  
	                   <div class="control-group form-group">
	                        <label><s:message code="label.customer.telephone" text="Phone"/></label>
	                        <div class="controls">
	                        			<s:message code="NotEmpty.customer.billing.phone" text="Phone number is required" var="msgPhone"/>
	                                    <form:input cssClass="input-large required customer-phone form-control form-control-md"  maxlength="32" path="phone" title="${msgPhone}"/>
	                                    <span class="help-inline"><form:errors path="phone" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	               

					 <div class="form-actions">
							<input id="submitAddress" class="btn btn-large btn-disabled template-btn" type="submit" name="submitAddress" value="<s:message code="menu.editaddress" text="Change address"/>" disabled="">
					  </div>



      					
				</form:form>
				</div>
				</div>
			 	<div class="span4 col-md-4">
			 		<jsp:include page="/pages/shop/common/customer/customerProfileMenu.jsp" />
			 		<jsp:include page="/pages/shop/common/customer/customerOrdersMenu.jsp" />
			 	</div>
			</div>
		</div>
