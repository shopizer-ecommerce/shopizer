<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<c:if test="${shippingMetaData.useDistanceModule==true}">
	<script src="https://maps.googleapis.com/maps/api/js?key=<sm:config configurationCode="shopizer.googlemaps_key" />"></script>
</c:if>

<!-- overrides with v2 page -->
<c:set var="creditCardInformationsPage" value="creditCardInformations-v2" scope="request"/>

<!-- phone number mask -->
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<!-- generic checkout script -->
<script src="<c:url value="/resources/js/shop-checkout.js" />"></script>

<!-- 
Templates definition
 -->
 
 <!-- subtotals template -->
<script type="text/html" id="subTotalsTemplate">
		{{#subTotals}}
			<tr id="cart-subtotal-{{code}}" class="cart-subtotal subt"> 									
				<td class="order-total-label">{{#discounted}}<s:message code="label.generic.rebate" text="Rebate" />&nbsp;-&nbsp;{{text}}{{/discounted}}{{^discounted}}{{title}}{{/discounted}}</td> 
				<td class="order-total-label"><strong>{{#discounted}}<font color="red">-{{total}}</font>{{/discounted}}{{^discounted}}{{total}}{{/discounted}}</strong></td> 
			</tr>
		{{/subTotals}}
</script>

<!-- total template -->
<script type="text/html" id="totalTemplate">
		<th><s:message code="order.total.total" text="Total"/></th>
		<td><strong><span class="amount grand-total">{{grandTotal}}</td>
</script>

											
<!-- shipping template -->
<script type="text/html" id="shippingTemplate">
			<table id="shippingOptions">
			{{#shippingOptions}}
				<tr>
				<td style="border: none !important;">	
				<input type="radio" name="selectedShippingOption.optionId" code="{{shippingModuleCode}}" class="shippingOption" id="{{optionId}}" value="{{optionId}}" {{#checked}} checked="checked"{{/checked}}>
				</td>
				<td style="border: none !important;">	
				<label> 
					{{description}} - {{optionPriceText}}
				</label>
				</td>
				</tr>
				{{#note}}
				<tr>
				<td colspan="2" style="border: none !important;">
					<span><small>{{note}}</small></span>
				</td>
				</tr>
				{{/note}}
			{{/shippingOptions}}
			</table>						
</script>
											
 
<script>

<!-- definitions -->
<!-- checkout form id -->
var checkoutFormId = '#checkoutForm';

<!-- get shipping quote url -->
var shippingQuotesUrl = '<c:url value="/shop/order/shippingQuotes.json"/>';

var calculateTotalUrl = '<c:url value="/shop/order/calculateOrderTotal.json"/>'

<!-- checkout field id -->
var formErrorMessageId = '#formErrorMessage';
var useDistanceWindow = <c:out value="${shippingMetaData.useDistanceModule}"/>;

$(document).ready(function() {
	
	
	//logic for initialyzing the form, needs to be maintained
	
	//form displaying shipping address
	$("#confirmShippingAddress").hide();

    formValid = false;	

	<!-- 
		//can use masked input for phone (USA - CANADA)
	-->

	paymentModule = '${order.defaultPaymentMethodCode}';
	log('PaymentModule ' + paymentModule);
	if(!paymentModule) {
		paymentModule = '${order.paymentModule}';
	}
	
	formValid = isFormValid(); //first validation

	bindActions();

	<c:if test="${order.customer.billing.country!=null}">
		$('.billing-country-list').val('${order.customer.billing.country}');
		//apply mask
		setCountrySettings('billing','${order.customer.billing.country}');
	</c:if>
	<c:if test="${order.customer.delivery.country!=null}">
		$('.shipping-country-list').val('${order.customer.delivery.country}');
		//apply mask
		setCountrySettings('delivery','${order.customer.delivery.country}');
	</c:if>

	<!-- customer state is text -->
	<c:if test="${order.customer.billing.stateProvince!=null && order.customer.billing.stateProvince!=null !=''}">
		$('#billingStateList').hide();          
		$('#billingStateProvince').show(); 
		$('#billingStateProvince').val('<c:out value="${order.customer.billing.stateProvince}"/>');
	</c:if>
	<!-- customer state is a know state -->
	<c:if test="${order.customer.billing.stateProvince==null || order.customer.billing.stateProvince==''}">
		$('#billingStateList').show();           
		$('#billingStateProvince').hide();
		getZones('#billingStateList','#billingStateProvince','<c:out value="${order.customer.billing.country}" />','<c:out value="${order.customer.billing.zone}" />', '${requestScope.LANGUAGE.code}', validateForm); 
	</c:if>
	
	<c:if test="${order.customer.delivery.stateProvince!=null && order.customer.delivery.stateProvince!=''}">  
		$('#deliveryStateList').hide();          
		$('#deliveryStateProvince').show(); 
		$('#deliveryStateProvince').val('<c:out value="${order.customer.delivery.stateProvince}"/>');
	</c:if>
	
	<c:if test="${order.customer.delivery.stateProvince==null || order.customer.delivery.stateProvince==''}">  
		$('#deliveryStateList').show();          
		$('#deliveryStateProvince').hide();
		//populate zones
		getZones('#deliveryStateList','#deliveryStateProvince','<c:out value="${order.customer.delivery.country}" />','<c:out value="${order.customer.billing.zone}" />', '${requestScope.LANGUAGE.code}', validateForm);
	</c:if>

	//when the list of country changes in the billing section
	$(".billing-country-list").change(function() {
		//populate zones
		getZones('#billingStateList','#billingStateProvince',$(this).val(),'<c:out value="${order.customer.billing.zone}" />', '${requestScope.LANGUAGE.code}', countryListChanged);
		setCountrySettings('billing',$(this).val());
    })
    
    //when the list of country changes in the shipping section
    $(".shipping-country-list").change(function() {
		getZones('#deliveryStateList','#deliveryStateProvince',$(this).val(),'<c:out value="${order.customer.delivery.zone}" />', '${requestScope.LANGUAGE.code}', countryListChanged);
		setCountrySettings('delivery',$(this).val());
    })


	//do we have a shipping address pre populated with a postal code
	//var deliveryPostalCode = null;
	var address = null;
	<c:if test="${deliveryAddress!=null}">
	//deliveryPostalCode='<c:out value="${shippingQuote.deliveryAddress.postalCode}"/>';
	address = {address:'<c:out value="${deliveryAddress.address}"/>',postalCode:'<c:out value="${deliveryAddress.postalCode}"/>',province:'<c:out value="${deliveryAddress.provinceName}"/>',country:'<c:out value="${deliveryAddress.countryName}"/>'};
	</c:if>

	<c:if test="${shippingQuote.deliveryAddress!=null}">
	address.latitude='<c:out value="${shippingQuote.deliveryAddress.latitude}"/>';
	address.longitude='<c:out value="${shippingQuote.deliveryAddress.longitude}"/>';
	</c:if>
	
	//selected quote is not pickup
	var shippingMethod = null;
	<c:if test="${shippingQuote.shippingModuleCode!=null}">
	shippingMethod='<c:out value="${shippingQuote.shippingModuleCode}"/>';
	</c:if>
	

	
	//console.log(address);
	displayConfirmShipping(address,shippingMethod,useDistanceWindow);
	

});

function countryListChanged() {
	validateForm();
	shippingQuotes(shippingQuotesUrl,useDistanceWindow);
}


function validateForm() {
	isFormValid(shippingQuotesUrl);	
}

function isFormValid() {
	$(formErrorMessageId).hide();//reset error message
	var $inputs = $(checkoutFormId).find(':input');
	var valid = true;
	var firstErrorMessage = null;
	$inputs.each(function() {
		if($(this).hasClass('required')) {
			var fieldValid = isCheckoutFieldValid($(this));
			//log($(this).attr('id') + ' Is valid ' + fieldValid);
			if(!fieldValid) {
				if(firstErrorMessage==null) {
					//log('Title ' + $(this).attr('title'));
					if($(this).attr('title')) {
						firstErrorMessage = $(this).attr('title');
					}
				}
				valid = false;
			}
		}
		if($(this).hasClass('email')) {	
			var emailValid = validateEmail($(this).val());
			//console.log('Email is valid ? ' + emailValid);
			if(!emailValid) {
				if(firstErrorMessage==null) {
					firstErrorMessage = '<s:message code="messages.invalid.email" text="Invalid email address"/>';
					valid = false;
				}
			}
		}
	});
	

	//console.log('Form is valid ? ' + valid);
	if(valid==false) {//disable submit button
		if(firstErrorMessage!=null) {
			$(formErrorMessageId).addClass('alert-error alert-danger');
			$(formErrorMessageId).removeClass('alert-success');
			$(formErrorMessageId).html('<!--<img src="<c:url value="/resources/img/icon_error.png"/>" width="40"/>&nbsp;--><strong><font color="red">' + firstErrorMessage + '</font></strong>');
			$(formErrorMessageId).show();
		}
		$('#submitOrder').addClass('btn-disabled');
		$('#submitOrder').prop('disabled', true);
	} else {
		$(formErrorMessageId).removeClass('alert-error alert-danger');
		$(formErrorMessageId).addClass('alert-success');
		$(formErrorMessageId).html('<!--<img src="<c:url value="/resources/img/icon_success.png"/>" width="40"/>&nbsp;--><strong><s:message code="message.order.canprocess" text="The order can be completed"/></strong>');
		$(formErrorMessageId).show();
		$('#submitOrder').removeClass('btn-disabled');
		$('#submitOrder').prop('disabled', false);
	}

	
	return valid;
}

function isCheckoutFieldValid(field) {

	var validateField = true;
	var fieldId = field.prop('id');
	var value = field.val();
	if (fieldId.indexOf("creditcard") >= 0) {
		validateField = false;	//ignore credit card number field
	}
	if(!field.is(':visible')) {
		validateField = false; //ignore invisible fields
	}
	

	//shipping information
	<c:if test="${shippingQuote!=null}">
	if (!$('#shipToDeliveryAddress').is(':checked')) {
		//validate shipping fields
		if (fieldId.indexOf("delivery") >= 0) {
			validateField = false; //ignore shipping fields when ship to billing
		}
	}
	</c:if>
	<c:if test="${fn:length(paymentMethods)>0}">
		//if any payment option need validation insert here
		//console.log($('input[name=paymentMethodType]:checked', checkoutFormId).val());
		//var paymentMethod = $('input[name=paymentMethodType]:checked', checkoutFormId).val();
		var paymentType = $('input[name=paymentMethodType]').val();
		log('PaymentType ' + paymentType);
		if(!paymentType) {
			paymentType = '${order.paymentMethodType}';
		}
		log('Payment Method Type ' + paymentType);
		if(paymentType=='CREDITCARD') {
			if (fieldId.indexOf("creditcard") >= 0) {
				if(fieldId!='creditcard_card_number' || fieldId!='creditcard-card-number') {
					validateField = true;// but validate credit card fields when credit card is selected
				}
				if(fieldId=='creditcard_card_number' || fieldId=='creditcard-card-number') {
					return isCreditCardValid();// validate credit card number differently
				}
			}
		}
	</c:if>
	

	
	if(!validateField) {
		return true;
	}
	
	if(field.attr('type')=='checkbox') {
		if(field.is(":checked")) {
			return true;
		} else {
			return false;
		}
	}
	
	if(!emptyString(value)) {
		field.css('background-color', '#FFF');
		return true;
	} else {
		field.css('background-color', '#FFC');
		return false;
	}
}

function showErrorMessage(message) {
	
	
	showResponseErrorMessage(message);
	$('#submitOrder').addClass('btn-disabled');
	$('#submitOrder').prop('disabled', true);
	
	$(formErrorMessageId).addClass('alert-error alert-danger');
	$(formErrorMessageId).removeClass('alert-success');
	$(formErrorMessageId).html('<!--<img src="<c:url value="/resources/img/icon_error.png"/>" width="40"/>&nbsp;--><strong><font color="red">' + message + '</font></strong>');
	$(formErrorMessageId).show();
	
}

function showResponseErrorMessage(message) {
	
	$('#checkoutError').addClass('alert');
	$('#checkoutError').addClass('alert-error alert-danger');
	$('#checkoutError').html(message);
	
}

function resetErrorMessage() {
	
	$('#checkoutError').html('');
	$('#checkoutError').removeClass('alert');
	$('#checkoutError').removeClass('alert-error alert-danger');
	$('.error').html('');
	
}



//different form actions
function bindActions() {
	
    $("#clickAgreement").click(function(){
    	$("#customer-agreement-area").slideToggle("slow");
	});
	
	bindCalculateShipping();
	
	$("input[type='text']").on("change keyup paste", function(){
		isFormValid();
	});
	
	$("input[type='checkbox']").on("change click", function(){
		isFormValid();
	});
	
   	$('input[name=paymentMethodType]', checkoutFormId).click(function() {
    	isFormValid();//change payment method
    });
    
    $("#billingStateList").change(function() {
    	shippingQuotes(shippingQuotesUrl,useDistanceWindow);	
    })
    
    $("#shippingStateList").change(function() {
    	shippingQuotes(shippingQuotesUrl,useDistanceWindow);		
    })
    
    $("input[id=billingPostalCode]").on('blur input', function() {
		if (!$('#shipToDeliveryAddress').is(':checked')) {
			shippingQuotes(shippingQuotesUrl,useDistanceWindow);
		}
     });
	
     $("input[id=deliveryPostalCode]").on('blur input', function() {
		if ($('#shipToDeliveryAddress').is(':checked')) {
			shippingQuotes(shippingQuotesUrl,useDistanceWindow);
		}
     });
     
     $(".paymentMethodSelected").click(function() { 
    	 var paymentClicked = $(this).attr("name");
    	 //console.log('Selected payment' + paymentClicked);
    	 setPaymentModule(paymentClicked);
    	 isFormValid();
     });
    
    //shipping / billing decision checkbox
    $("#shipToDeliveryAddress").click(function() {
    	shippingQuotes(shippingQuotesUrl,useDistanceWindow);
    	isFormValid();
    	$('#ship-box-info').slideToggle(1000);
    });
    
    //final order submission button
	$("#submitOrder").click(function(e) {
		e.preventDefault();//do not submit form
		formValid = isFormValid();
		resetErrorMessage();
		setCountrySettings('billing',$('.billing-country-list').val());
		setCountrySettings('delivery',$('.shipping-country-list').val());
		//$('#submitOrder').disable();
		
		//confirm shipping
		if(formValid) {
				//validateConfirmShipping(response);
				if($('#confirm_address')) {
					//add confirm address section to shipping
				}
		}
		
		showSMLoading('#pageContainer');
		var paymentSelection = $('#paymentModule').val();
		log('Selection-> ' + paymentSelection);
		if(paymentSelection.indexOf('paypal') >= 0 || paymentSelection.indexOf('PAYPAL') >= 0) {

			//$('#paymentMethodType').val('PAYPAL');
			$('#paymentMethodType').attr("value", 'PAYPAL');
			initPayment('PAYPAL');
		}
		else if(paymentSelection.indexOf('stripe') >= 0) {
			log('Stripe ');
			//$('#paymentMethodType').val('CREDITCARD');
			$('#paymentMethodType').attr("value", 'CREDITCARD');
			initStripePayment();
		}
		else if(paymentSelection.indexOf('braintree') >= 0) {
			log('Braintree ' + $('input[name=paymentMethodType]').val());
			//$('#paymentMethodType').val('CREDITCARD');
			$('#paymentMethodType').attr("value", 'CREDITCARD');
			log('Payment method type -> ' + $('input[name=paymentMethodType]').val());
			initBraintreePayment();
		}
		else if(paymentSelection.indexOf('moneyorder') >= 0) {
			log('Money order ' + $('input[name=paymentMethodType]').val());
			//$('#paymentMethodType').val('CREDITCARD');
			$('#paymentMethodType').attr("value", 'MONEYORDER');
			log('Payment method type -> ' + $('input[name=paymentMethodType]').val());
			submitForm();
		}
		else if(paymentSelection.indexOf('beanstream') >= 0) {
			//log('Beanstream ');
			//$('#paymentMethodType').val('CREDITCARD');
			$('#paymentMethodType').attr("value", 'CREDITCARD');
			submitForm();
		} else {
			//submit form
			submitForm();	
		}
    });
}

function submitForm() {
	log('Checkout ');
	$('#pageContainer').hideLoading();
	$('#checkoutForm').submit();
}

function initPayment(paymentSelection) {
	var url = '<c:url value="/shop/order/payment/init/"/>' + paymentSelection + '.html';
	var data = $(checkoutFormId).serialize();
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: data,
		  cache: false,
		  dataType: 'json',
		  success: function(response){
			  	//$('#submitOrder').enable();
			    hideSMLoading('#pageContainer');
				var resp = response.response;
				var status = resp.status;
				//console.log(status);
				if(status==0 || status ==9999) {
					
					var data = resp.url;
					//console.log(resp.url);
					location.href=resp.url;

				} else if(status==-2) {//validation issues
					
					//console.log(resp.validations);
				    var globalMessage = '';
					for(var i = 0; i< resp.validations.length; i++) {
						var fieldName = resp.validations[i].field;
						var message = resp.validations[i].message;
						//console.log(fieldName +  ' - ' + message);
						//query for the field
						var f = $(document.getElementById('error-'+fieldName));
						if(f) {
							f.html(message);
						}
						globalMessage = globalMessage + message + '<br/>';
						
					}
					
					showResponseErrorMessage(globalMessage);
					
					
				} else {
					//console.log('Wrong status ' + status);
					showResponseErrorMessage('<s:message code="error.code.99" text="An error message occured while trying to process the payment (99)"/>');
					
				}
		  },
		    error: function(xhr, textStatus, errorThrown) {
		    	hideSMLoading('#pageContainer');
		  		//alert('error ' + errorThrown);
		    	showResponseErrorMessage(errorThrown);
		  }

	});
	
}


</script>

		<!-- page-title-wrapper-end -->
		<!-- entry-header-area start -->
		<div class="entry-header-area ptb-40">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="entry-header">
							<h1 class="entry-title"><s:message code="label.checkout" text="Checkout" /></h1>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- entry-header-area end -->
		<!-- coupon-area start -->
		<div class="coupon-area">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="coupon-accordion">
							<!-- ACCORDION START -->
							<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
								<p class="muted common-row"><a href="<c:url value="/shop/customer/customLogon.html"/>"><s:message code="label.checkout.logon" text="Logon or signup to simplify the online purchase process!"/></a></p>
							</sec:authorize>					
						</div>
					</div>
				</div>
			</div>
		</div>
		

		
		<!-- coupon-area end -->		
		<!-- checkout-area start -->
		<div class="checkout-area pb-50">
			<div class="container">
				<div class="row">
				
				
					<!-- If error messages -->
					<div id="checkoutError"  class="<c:if test="${errorMessages!=null}">alert  alert-error alert-danger </c:if>">
						<c:if test="${errorMessages!=null}">
						<c:out value="${errorMessages}" />
						</c:if>
					</div>
					<!--alert-error-->
				
   					<c:set var="commitUrl" value="${pageContext.request.contextPath}/shop/order/commitOrder.html"/>
   					<form:form id="checkoutForm" method="POST" enctype="multipart/form-data" commandName="order" action="${commitUrl}">
						<input type="hidden" id="useDistanceWindow" name="useDistanceWindow" value="<c:out value="${shippingMetaData.useDistanceModule}"/>">
						<div class="col-lg-6 col-md-6">
							<div class="checkbox-form">						
								<h3><s:message code="label.customer.billinginformation" text="Billing information"/></h3>
								<div class="row">
									<div class="col-md-6">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.firstname" text="First Name"/><span class="required">*</span></label>										
											<s:message code="NotEmpty.customer.firstName" text="First name is required" var="msgFirstName"/>
											<form:input id="customer.firstName" cssClass="required" path="customer.billing.firstName" autofocus="autofocus" title="${msgFirstName}"/>
											<form:errors path="customer.billing.firstName" cssClass="error" />
										    <span id="error-customer.billing.firstName" class="error"></span>
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.lastname" text="Last Name"/><span class="required">*</span></label>										
										    <s:message code="NotEmpty.customer.lastName" text="Last name is required" var="msgLastName"/>
										    <form:input id="customer.lastName" cssClass="required"  maxlength="32" path="customer.billing.lastName" title="${msgLastName}" />
										    <form:errors path="customer.billing.lastName" cssClass="error" />
										    <span id="error-customer.billing.lastName" class="error"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="checkout-form-list">
											<label><s:message code="label.customer.billing.company" text="Billing company"/></label>
										    <form:input id="customer.billing.company" cssClass="" path="customer.billing.company"/>
										    <form:errors path="customer.billing.company" cssClass="error" />
											<span id="error-customer.billing.company" class="error"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.streetaddress" text="Street address"/> <span class="required">*</span></label>
										    <s:message code="NotEmpty.customer.billing.address" text="Address is required" var="msgAddress"/>
										    <form:input id="customer.billing.address" cssClass="required" path="customer.billing.address" title="${msgAddress}"/>
										    <form:errors path="customer.billing.address" cssClass="error" />
										    <span id="error-customer.billing.address" class="error"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.city" text="City"/> <span class="required">*</span></label>
											<s:message code="NotEmpty.customer.billing.city" text="City is required" var="msgCity"/>
											<form:input id="customer.billing.city" cssClass="required" path="customer.billing.city" title="${msgCity}"/>
											<form:errors path="customer.billing.city" cssClass="error" />
										    <span id="error-customer.billing.city" class="error"></span>
										</div>
									</div>
									<div class="col-md-12">
										<div class="country-select">
											<label><s:message code="label.generic.country" text="Country"/> <span class="required">*</span></label>
										    <form:select cssClass="billing-country-list" path="customer.billing.country" style="background-color: rgb(255, 255, 255);">
											  	<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
										     </form:select>										
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list zone-select">
											<label><s:message code="label.generic.stateprovince" text="State / Province"/> <span class="required">*</span></label>										
											<form:select cssClass="zone-list" id="billingStateList" path="customer.billing.zone"/>
											<s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
											<form:input  class="required" id="billingStateProvince"  maxlength="100" name="billingStateProvince" path="customer.billing.stateProvince" title="${msgStateProvince}"/>
											<form:errors path="customer.billing.stateProvince" cssClass="error" />
											<span id="error-customer.billing.stateProvince" class="error"></span>
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.postalcode" text="Postal code"/> <span class="required">*</span></label>										
											<s:message code="NotEmpty.customer.billing.postalCode" text="Postal code is required" var="msgPostalCode"/>
											<form:input id="billingPostalCode" cssClass="required billing-postalCode" path="customer.billing.postalCode" title="${msgPostalCode}"/>
											<form:errors path="customer.billing.postalCode" cssClass="error" />
											<span id="error-customer.billing.postalCode" class="error"></span>
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.email" text="Email address"/> <span class="required">*</span></label>										
											<s:message code="NotEmpty.customer.emailAddress" text="Email address is required" var="msgEmail"/> 
										    <form:input id="customer.emailAddress" cssClass="required" path="customer.emailAddress" title="${msgEmail}"/>
										    <form:errors path="customer.emailAddress" cssClass="error" />
											<span id="error-customer.emailAddress" class="error"></span>
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list">
											<label><s:message code="label.generic.phone" text="Phone number"/>  <span class="required">*</span></label>										
											<s:message code="NotEmpty.customer.billing.phone" text="Phone number is required" var="msgPhone"/>
										    <form:input id="customer.billing.phone" cssClass="required" path="customer.billing.phone" title="${msgPhone}"/>
										    <form:errors path="customer.billing.phone" cssClass="error" />
											<span id="error-customer.billing.phone" class="error"></span>
										</div>
									</div>
									<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
									<div class="col-md-12">
										<div class="checkout-form-list create-acc">	
											<input id="cbox" type="checkbox" />
											<label><s:message code="label.customer.createaccount" text="Create an account?"/></label>
										</div>
										<div id="cbox_info" class="checkout-form-list create-account">
											<p><s:message code="label.customer.createaccount.text" text="Create an account by entering the information below. If you are a returning customer please login using the link at the top of the page."/></p>
											<s:message code="message.password.required" text="Password is required" var="msgPassword"/>
											<label><s:message code="label.customer.accountpassword" text="Account password"/>  <span class="required">*</span></label>
											<form:input id="customer.clearPassword" cssClass="required" path="customer.clearPassword" title="${msgPassword}"/>	
										</div>
									</div>								
									</sec:authorize>
								</div>
								<c:if test="${shippingQuote!=null}">
								<div class="different-address">
										<div class="ship-different-title">
											<h3>
												<label><s:message code="label.customer.shipping.shipdifferentaddress" text="Ship to a different address?"/></label>
												<form:checkbox path="shipToDeliveryAddress" id="shipToDeliveryAddress"/>
											</h3>
										</div>
									<div id="ship-box-info" class="row">
										<div class="col-md-6">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.firstname" text="Shipping first name"/> <span class="required">*</span></label>										
												<s:message code="NotEmpty.customer.shipping.firstName" text="Shipping first name should not be empty" var="msgShippingFirstName"/>
									      		<form:input id="customer.delivery.name" cssClass="required" path="customer.delivery.firstName" title="${msgShippingFirstName}"/>
											</div>
										</div>
										<div class="col-md-6">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.lastname" text="Shipping last name"/> <span class="required">*</span></label>										
												<s:message code="NotEmpty.customer.shipping.lastName" text="Shipping last name should not be empty" var="msgShippingLastName"/>
									      		<form:input id="customer.delivery.name" cssClass="required" path="customer.delivery.lastName" title="${msgShippingLastName}"/>
											</div>
										</div>
										<div class="col-md-12">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.company" text="Shipping company"/></label>
												<form:input id="customer.delivery.company" cssClass="" path="customer.delivery.company"/>
											</div>
										</div>
										<div class="col-md-12">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.streetaddress" text="Shipping street address"/> <span class="required">*</span></label>
												<s:message code="NotEmpty.customer.shipping.address" text="Shipping street address should not be empty" var="msgShippingAddress"/>
										      	<form:input id="customer.delivery.address" cssClass="required" path="customer.delivery.address" title="${msgShippingAddress}"/>
											</div>
										</div>
										<div class="col-md-12">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.city" text="Shipping city"/> <span class="required">*</span></label>
												<s:message code="NotEmpty.customer.shipping.city" text="Shipping city should not be empty" var="msgShippingCity"/> 
											    <form:input id="customer.delivery.city" cssClass="required" path="customer.delivery.city" title="${msgShippingCity}"/>
											</div>
										</div>
									<div class="col-md-12">
										<div class="country-select">
											<label><s:message code="label.customer.shipping.country" text="Shipping country"/> <span class="required">*</span></label>	
										     <form:select cssClass="shipping-country-list" path="customer.delivery.country">
											  	<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
										     </form:select>									
										</div>
									</div>
									<div class="col-md-6">
										<div class="checkout-form-list zone-select">
											<label><s:message code="label.customer.shipping.zone" text="Shipping state / province"/> <span class="required">*</span></label>										
											<form:select cssClass="zone-list" id="deliveryStateList" path="customer.delivery.zone"/>
											<s:message code="NotEmpty.customer.shipping.stateProvince" text="Shipping State / Province is required" var="msgShippingState"/>
											<form:input  class="required" id="deliveryStateProvince"  maxlength="100" name="shippingStateProvince" path="customer.delivery.stateProvince" title="${msgShippingState}"/>
										</div>
									</div>
									<div class="col-md-6">
											<div class="checkout-form-list">
												<label><s:message code="label.customer.shipping.postalcode" text="Shipping postal code"/> <span class="required">*</span></label>										
												<s:message code="NotEmpty.customer.shipping.postalCode" text="Shipping postal code should not be empty" var="msgShippingPostal"/>
											    <form:input id="deliveryPostalCode" cssClass="required delivery-postalCode" path="customer.delivery.postalCode" title="${msgShippingPostal}"/>
											</div>
									</div>
								
								</div>
									<div class="order-notes">
										<div class="checkout-form-list">
											<label><s:message code="label.order.notes" text="Order notes"/></label>
											<s:message code="label.order.notes.eg" text="Notes for the order or delivery" var="msgNotes"/>
											<form:textarea id="comments" cols="30" rows="10" path="comments" placeholder="${msgNotes}" />
										</div>									
									</div>
								</div>
								</c:if>													
							</div>
						</div>	
						<div class="col-lg-6 col-md-6">
							<div class="your-order">
								<h3><s:message code="label.order.summary" text="Order summary" /></h3>
								<div class="your-order-table table-responsive">
									<table id="summary-table"><!-- requires summary-table -->
										<thead>
											<tr>
												<th class="product-name"><s:message code="label.order.item" text="Item" /></th>
												<th class="product-total"><s:message code="label.order.total" text="Total" /></th>
											</tr>							
										</thead>
										<tbody id="summaryRows">
											<c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem">
											<tr class="cart_item">
												<td class="product-name">
													${shoppingCartItem.name} <strong class="product-quantity"> x ${shoppingCartItem.quantity}</strong>
													<c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
													<br/>
														<ul>
														<c:forEach items="${shoppingCartItem.shoppingCartAttributes}" var="option">
															<li>${option.optionName} - ${option.optionValue}</li>
														</c:forEach>
														</ul>
													</c:if>
												</td>
												<td class="product-total">
													<span class="amount">${shoppingCartItem.subTotal}</span>
												</td>
											</tr>
											</c:forEach>
										</tbody>
										<tfoot>
											<!-- subtotals -->
											<c:forEach items="${order.orderTotalSummary.totals}" var="total">
												<c:if test="${total.orderTotalCode!='order.total.total'}">
												<tr id="cart-subtotal-${total.orderTotalCode}" class="cart-subtotal subt"> 
														<td class="order-total-label">
														<c:choose>
																<c:when test="${total.orderTotalCode=='order.total.discount'}">
																<s:message code="label.generic.rebate" text="Rebate"/>&nbsp;-&nbsp;<s:message code="${total.text}" text="${total.text}"/>
																</c:when>
																<c:otherwise>
																	<s:message code="${total.orderTotalCode}" text="${total.orderTotalCode}"/>
																</c:otherwise>
														</c:choose>
														</td> 
														<td><strong><c:choose><c:when test="${total.orderTotalCode=='order.total.discount'}"><font color="red">- <sm:monetary value="${total.value}" /></span></c:when><c:otherwise><sm:monetary value="${total.value}" /></c:otherwise></c:choose></strong></td> 
												</tr> 
												</c:if>
											</c:forEach>
											
											<!-- Shipping box THIS IS ALL BROKEN -->
											<c:if test="${shippingQuote!=null}">
											<tr class="shipping">
												<td class="order-total-label" style="border:none !important;">
													<s:message code="label.shipping.fees" text="Shipping fees" />
													<input type="hidden" id="shippingModule" name="shippingModule" value="${shippingQuote.shippingModuleCode}">
												</td>
												<td id="shippingSection" style="border:none !important;">
													<c:choose>
								        			<c:when test="${fn:length(shippingQuote.shippingOptions)>0}">
								       				<c:if test="${shippingQuote.shippingReturnCode=='NO_POSTAL_CODE'}">
							 							<br/>
							 							<font color="orange"><s:message code="label.shipping.nopostalcode" text="A shipping quote will be available after filling the postal code"/></font>
							 							<br/><br/>
							 						</c:if>	
													<table id="shippingOptions">
														<c:forEach items="${shippingQuote.shippingOptions}" var="option" varStatus="status">
															<tr>
															<td style="border: none !important;">
															<input type="radio" name="selectedShippingOption.optionId" class="shippingOption" code="${option.shippingModuleCode}" id="${option.optionId}" value="${option.optionId}" <c:if test="${shippingQuote.selectedShippingOption!=null && shippingQuote.selectedShippingOption.optionId==option.optionId}">checked="checked"</c:if>>
															</td>
															<td style="border: none !important;">
															<label>
																<s:message code="module.shipping.${option.shippingModuleCode}" arguments="${requestScope.MERCHANT_STORE.storename}" text="${option.shippingModuleCode}"/>: <span class="amount">${option.optionPriceText}</span>
															</label>
															</td>
															</tr>
															<c:if test="${option.note!=null}">
															<tr>
																<td colspan="2" style="border: none !important;"><span><small><c:out value="${option.note}"/></small></span></td>
															</tr>
															</c:if>
														</c:forEach>
													</table>						 													        			
								        			</c:when>
								        			<c:otherwise>
														<c:choose>
								       						<c:when test="${shippingQuote.freeShipping==true && shippingQuote.freeShippingAmount!=null}" >
								       							<s:message code="label.shipping.freeshipping.over" text="Free shipping for orders over"/>&nbsp;<strong><sm:monetary value="${shippingQuote.freeShippingAmount}"/></strong>
								       						</c:when>
								       						<c:otherwise>
										       				<c:choose>
										       				  <c:when test="${shippingQuote.shippingReturnCode=='ERROR'}">
										       					<font color="red"><c:out value="${shippingQuote.quoteError}" /></font>
										       				  </c:when>
										       				  <c:otherwise>
										       					<c:choose>
											       					<c:when test="${shippingQuote.shippingReturnCode=='NO_SHIPPING_MODULE_CONFIGURED'}">
											       						<font color="red"><s:message code="message.noshipping.configured" text="No shipping method configured"/></font>
											       					</c:when>
											       					<c:otherwise>
											       						<c:choose>
											       							<c:when test="${shippingQuote.shippingReturnCode=='NO_POSTAL_CODE'}">
											       								<div id="shippingSection" class="control-group"> 
											       								<strong>
											       									<font color="orange"><s:message code="label.shipping.nopostalcode" text="A shipping quote will be available after filling the postal code"/></font>
											       								</strong>
											       								</div>
											       							</c:when>
											       							<c:otherwise>
											       								<strong><s:message code="label.shipping.freeshipping" text="Free shipping!"/></strong>
											       							</c:otherwise>
											       						</c:choose>
											       					</c:otherwise>
										       					</c:choose>
										       				  </c:otherwise>
										       				</c:choose>
								       					  </c:otherwise>
								       					</c:choose>
													</c:otherwise>
								        			</c:choose>
												</td>
											</tr>
											<tr class="shipping">
											<td colspan="2">
												<!-- Confirm address box box -->
												<div id="confirmShippingAddress" style="height:250px;"></div>
											</td>
											</tr>
											</c:if>
											
											<tr id="totalRow" class="total-box order-total">
												<th><s:message code="order.total.total" text="Total"/></th>
												<td><strong><span class="amount grand-total"><sm:monetary value="${order.orderTotalSummary.total}"/></td>
											</tr>						
										</tfoot>
									</table>
								</div>
						<!-- Payments -->
						<c:if test="${fn:length(paymentMethods)>0}">
							<div class="payment-method">
								<ul class="nav nav-tabs" role="tablist">
									<c:forEach items="${paymentMethods}" var="paymentMethod">

										<li role="presentation"
											class="<c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>">
											<a href="#${paymentMethod.paymentType}"
											class="paymentMethodSelected"
											name="${paymentMethod.paymentMethodCode}"
											aria-controls="#${paymentMethod.paymentType}" role="tab"
											data-toggle="tab"> 

														<h5>
															<s:message
																code="payment.type.${paymentMethod.paymentType}"
																text="Payment method type [payment.type.${paymentMethod.paymentType}] not defined in payment.properties" />
														</h5>

										</a>
										</li>

									</c:forEach>
								</ul>
								<!--  redit card https://codepen.io/llgruff/pen/JdyJWR -->
								<div class="v-margin20">
								
								
								<div class="tab-content">
																
											<c:forEach items="${paymentMethods}" var="paymentMethod">
												<div
													class="payment-tab tab-pane <c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>"
													id="${paymentMethod.paymentType}">
													<c:choose>
														<c:when
															test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">
															<c:set var="paymentModule"
																value="${order.paymentMethodType}" scope="request" />
														</c:when>
														<c:otherwise>
															<c:if
																test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">
																<c:set var="paymentModule"
																	value="${paymentMethod.paymentMethodCode}"
																	scope="request" />
															</c:if>
														</c:otherwise>
													</c:choose>
													<c:set var="selectedPaymentMethod"
														value="${order.paymentMethodType}" scope="request" />
													<c:set var="paymentMethod" value="${paymentMethod}"
														scope="request" />

													<!-- exception for stripe, braintree... which has it's own page -->
													<c:choose>
														<c:when
															test="${(paymentMethod.paymentMethodCode=='stripe') or (paymentMethod.paymentMethodCode=='braintree')}">
															<c:set var="pageName"
																value="${fn:toLowerCase(paymentMethod.paymentMethodCode)}" />
														</c:when>
														<c:otherwise>
															<c:set var="pageName"
																value="${fn:toLowerCase(paymentMethod.paymentType)}" />
														</c:otherwise>
													</c:choose>
													<jsp:include
														page="/pages/shop/common/checkout/${pageName}.jsp" />

												</div>
											</c:forEach>
											<!-- values set by javascript -->
											<input type="hidden" id="paymentMethodType" name="paymentMethodType" value="<c:if test="${order.paymentMethodType!=null}"><c:out value="${order.paymentMethodType}"/></c:if>" />
											<input type="hidden" id="paymentModule" name="paymentModule"
												value="<c:choose><c:when test="${order.paymentModule!=null}"><c:out value="${order.paymentModule}"/></c:when><c:otherwise><c:out value="${paymentModule}" /></c:otherwise></c:choose>" />
										</div>
								
								</div>

								<c:if
									test="${requestScope.CONFIGS['displayCustomerAgreement']==true}">
									<!-- customer agreement -->
									<div class="checkout-box" id="customerAgreementSection"
										class="">
										<label id="customerAgreement" class="checkbox"> <s:message
												code="NotEmpty.customer.agreement"
												text="Please make sure you agree with terms and conditions"
												var="msgAgreement" /> <form:checkbox path="customerAgreed"
												id="customerAgreed" cssClass="required"
												title="${msgAgreement}" /> <a
											href="javascript:return false;" id="clickAgreement"><s:message
													code="label.customer.order.agreement"
													text="I agree with the terms and conditions" /></a>
										</label>
										<div id="customer-agreement-area">
											<c:choose>
												<c:when test="${requestScope.CONTENT['agreement']!=null}">
													<sm:pageContent contentCode="agreement" />
												</c:when>
												<c:otherwise>
													<s:message code="message.content.missing.agreement"
														text="Content with code 'agreement' does not exist" />
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</c:if>
								
								<div id="formErrorMessage" class="alert"></div>
								<div class="order-button-payment">
												<button id="submitOrder" type="button" class=" 
												<c:if test="${errorMessages!=null}"> btn-disabled</c:if>" 
												<c:if test="${errorMessages!=null}"> disabled="true"</c:if>
												><s:message code="button.label.submitorder" text="Submit order"/>
												</button>
			
												<!-- submit can be a post or a pre ajax query -->
								</div>
							</div>
						</c:if>
					</div>
						</div>
					</form:form>
				</div>
			</div>
		</div>
		<!-- checkout-area end -->	
