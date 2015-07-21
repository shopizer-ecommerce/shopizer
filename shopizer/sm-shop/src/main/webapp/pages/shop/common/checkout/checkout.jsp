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

<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>

<!-- subtotals template -->
<script type="text/html" id="subTotalsTemplate">
		{{#subTotals}}
			<tr class="subt"> 
				<td colspan="3">{{title}}</td> 
				<td><strong>{{total}}</strong></td> 
			</tr>
		{{/subTotals}}
</script>

<!-- total template -->
<script type="text/html" id="totalTemplate">
		<span class="total-box-grand-total">
			<font class="total-box-label">
			  <s:message code="order.total.total" text="Total"/>
			  <font class="total-box-price">{{grandTotal}}</font>
			</font>
		</span>
</script>

<!-- shipping template -->
<script type="text/html" id="shippingTemplate">

		<label class="control-label">
			<s:message code="label.shipping.options" text="Shipping options"/>
			{{#showHandling}}
				&nbsp;(<s:message code="label.shipping.handlingfees" text="Handling fees" />&nbsp;{{handlingText}})
			{{/showHandling}}			       				
		</label> 
		<div class="controls">
			{{#shippingOptions}}	
				<label class="radio"> 
					<input type="radio" name="selectedShippingOption.optionId" class="shippingOption" id="{{optionId}}" value="{{optionId}}" {{#checked}} checked="checked"{{/checked}}> 
					{{optionName}} - {{optionPriceText}}
				</label>
			{{/shippingOptions}}						
		</div>

</script>


<script>

<!-- checkout form id -->
var checkoutFormId = '#checkoutForm';
var formErrorMessageId = '#formErrorMessage';


function isFormValid() {
	$(formErrorMessageId).hide();//reset error message
	var $inputs = $(checkoutFormId).find(':input');
	var valid = true;
	var firstErrorMessage = null;
	$inputs.each(function() {
		if($(this).hasClass('required')) {
			var fieldValid = isFieldValid($(this));
			if(!fieldValid) {
				if(firstErrorMessage==null) {
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
	
	//display - hide shipping
    if ($('#shipToBillingAdress').is(':checked')) {
	    $('#deliveryBox').hide();
    } else {
	    $('#deliveryBox').show();
    }
	
	//console.log('Form is valid ? ' + valid);
	if(valid==false) {//disable submit button
		if(firstErrorMessage!=null) {
			$(formErrorMessageId).addClass('alert-error alert-danger');
			$(formErrorMessageId).removeClass('alert-success');
			$(formErrorMessageId).html('<img src="<c:url value="/resources/img/icon_error.png"/>" width="40"/>&nbsp;<strong><font color="red">' + firstErrorMessage + '</font></strong>');
			$(formErrorMessageId).show();
		}
		$('#submitOrder').addClass('btn-disabled');
		$('#submitOrder').prop('disabled', true);
	} else {
		$(formErrorMessageId).removeClass('alert-error alert-danger');
		$(formErrorMessageId).addClass('alert-success');
		$(formErrorMessageId).html('<img src="<c:url value="/resources/img/icon_success.png"/>" width="40"/>&nbsp;<strong><s:message code="message.order.canprocess" text="The order can be completed"/></strong>');
		$(formErrorMessageId).show();
		$('#submitOrder').removeClass('btn-disabled');
		$('#submitOrder').prop('disabled', false);
	}
}

function setPaymentModule(module) {
	//console.log('Module - ' + module);
	$('#paymentModule').val(module);	
}

function isFieldValid(field) {
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
	if ($('#shipToBillingAdress').is(':checked')) {
		//validate shipping fields
		if (fieldId.indexOf("delivery") >= 0) {
			validateField = false; //ignore shipping fields when ship to billing
		}
	}
	</c:if>
	<c:if test="${fn:length(paymentMethods)>0}">
		//if any payment option need validation insert here
		//console.log($('input[name=paymentMethodType]:checked', checkoutFormId).val());
		if($('input[name=paymentMethodType]:checked', checkoutFormId).val()=='CREDITCARD') {
			if (fieldId.indexOf("creditcard") >= 0) {
				if(fieldId!='creditcard_card_number') {
					validateField = true;// but validate credit card fields when credit card is selected
				}
				if(fieldId=='creditcard_card_number') {
					return isCreditCardValid();// validate credit card number differently
				}
			}
		}
	</c:if>
	if(!validateField) {
		return true;
	}
	if(!emptyString(value)) {
		field.css('background-color', '#FFF');
		return true;
	} else {
		field.css('background-color', '#FFC');
		return false;
	}
}

$.fn.addItems = function(div, data, defaultValue) {
	//console.log('Populating div ' + div + ' defaultValue ' + defaultValue);
	var selector = div + ' > option';
	var defaultExist = false;
    $(selector).remove();
        return this.each(function() {
            var list = this;
            $.each(data, function(index, itemData) {
            	//console.log(itemData.code + ' ' + defaultValue);
            	if(itemData.code==defaultValue) {
            		defaultExist = true;
            	}
                var option = new Option(itemData.name, itemData.code);
                list.add(option);
            });
            if(defaultExist && (defaultValue!=null && defaultValue!='')) {
           	 	$(div).val(defaultValue);
            }
     });
};

$.fn.serializeObject = function()
{
   var o = {};
   var a = this.serializeArray();
   $.each(a, function() {
       if (o[this.name]) {
           if (!o[this.name].push) {
               o[this.name] = [o[this.name]];
           }
           o[this.name].push(this.value || '');
       } else {
           o[this.name] = this.value || '';
       }
   });
   return o;
};

function showErrorMessage(message) {
	
	
	showResponseErrorMessage(message);
	$('#submitOrder').addClass('btn-disabled');
	$('#submitOrder').prop('disabled', true);
	
	$(formErrorMessageId).addClass('alert-error alert-danger');
	$(formErrorMessageId).removeClass('alert-success');
	$(formErrorMessageId).html('<img src="<c:url value="/resources/img/icon_error.png"/>" width="40"/>&nbsp;<strong><font color="red">' + message + '</font></strong>');
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



/** 
 * Specify 
 * div list container
 * text div (shown or not)
 * selected countryCode
 * preselected value
 * callback to invoke
 */
function getZones(listDiv, textDiv, countryCode, defaultValue, callBackFunction){
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/reference/provinces.html"/>',
	  data: 'countryCode=' + countryCode + '&lang=${requestScope.LANGUAGE.code}',
	  dataType: 'json',
	  success: function(response){
			var status = response.response.status;
			var data = response.response.data;
			//console.log(status);
			if((status==0 || status ==9999) && data) {
				
				
				//console.log(data);
				if(data && data.length>0) {
					$(listDiv).show();  
					$(textDiv).hide();
					$(listDiv).addItems(listDiv, data, defaultValue);		
				} else {
					$(listDiv).hide();             
					$(textDiv).show();
					if(defaultValue!=null || defaultValue !='') {
						$(textDiv).val(defaultValue);
					}
				}
			} else {
				$(listDiv).hide();             
				$(textDiv).show();
			}
			isFormValid();
			if(callBackFunction!=null) {
				callBackFunction();
			}
	  },
	    error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }

	});
	
}


function setCountrySettings(prefix, countryCode) {
	//add masks to your country
	//console.log('Apply mask ' + countryCode);
	
	var phoneSelector = '.' + prefix + '-phone';
	var postalCodeSelector = '.' + prefix + '-postalCode';
	
	if(countryCode=='CA') {//mask for canada
		$(phoneSelector).mask("?(999) 999-9999");
		$(postalCodeSelector).mask("?*** ***");
		return;
	}
	if(countryCode=='US') {// mask for united states
		$(phoneSelector).mask("?(999) 999-9999");
		$(postalCodeSelector).mask("?99999");
		return;
	}
	
	$(phoneSelector).unmask();
	$(postalCodeSelector).unmask();

	
}



function bindActions() {
    $(".shippingOption").click(function() {
    	calculateTotal();
    });
    
    <!-- shipping / billing decision -->
    $("#shipToBillingAdress").click(function() {
    	shippingQuotes();	
    	if ($('#shipToBillingAdress').is(':checked')) {
    		$('#deliveryBox').hide();
    		isFormValid();
    	} else {
    		$('#deliveryBox').show();
    		isFormValid();
    	}
    });
    
	$("#submitOrder").click(function(e) {
		e.preventDefault();//do not submit form
		resetErrorMessage();
		setCountrySettings('billing',$('.billing-country-list').val());
		setCountrySettings('delivery',$('.shipping-country-list').val());
		$('#pageContainer').showLoading();
		var paymentSelection = $('input[name=paymentMethodType]:checked', checkoutFormId).val();
		if(paymentSelection.indexOf('PAYPAL')!=-1) {
			initPayment(paymentSelection);
		} else {
			//submit form
			$('#pageContainer').hideLoading();
			$('#checkoutForm').submit();
			
		}
    });
}



function shippingQuotes(){
	resetErrorMessage();
	$('#pageContainer').showLoading();
	var data = $(checkoutFormId).serialize();
	//console.log(data);
	
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/order/shippingQuotes.html"/>',
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){
		  
		    $('#pageContainer').hideLoading();
		  	if(response.errorMessage!=null && response.errorMessage!='') {
		  		showErrorMessage(response.errorMessage);
		  		return;
		  	}

			//console.log(response);
			
			$('#summary-table tr.subt').remove();
			$('#totalRow').html('');
			var subTotalsTemplate = Hogan.compile(document.getElementById("subTotalsTemplate").innerHTML);
			var totalTemplate = Hogan.compile(document.getElementById("totalTemplate").innerHTML);
			var quotesTemplate = Hogan.compile(document.getElementById("shippingTemplate").innerHTML);
			var subTotalsRendered = subTotalsTemplate.render(response);
			var totalRendred = totalTemplate.render(response);
			
			if(response.shippingSummary!=null) {
				//create extra fields
				summary = response.shippingSummary;
				for(var i = 0; i< summary.shippingOptions.length; i++) {
					if(summary.shippingOptions[i].optionId == summary.selectedShippingOption.optionId) {
						summary.shippingOptions[i].checked = true;
						break;
					}
				}
				if(summary.handling && summary.handling>0) {
					summary.showHandling = true;
				}
				
				//render summary
				$('#shippingSection').html('');
				var quotesRendered = quotesTemplate.render(response.shippingSummary);
				//console.log(quotesRendered);
				$('#shippingSection').html(quotesRendered);
				bindActions();
			} 
			$('#summaryRows').append(subTotalsRendered);
			$('#totalRow').html(totalRendred);
			isFormValid();
	  },
	    error: function(xhr, textStatus, errorThrown) {
	    	$('#pageContainer').hideLoading();
	  		alert('error ' + errorThrown);
	  }

	});
	
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
			    $('#pageContainer').hideLoading();
				var resp = response.response;
				var status = resp.status;
				console.log(status);
				if(status==0 || status ==9999) {
					
					var data = resp.url;
					console.log(resp.url);
					location.href=resp.url;

				} else if(status==-2) {//validation issues
					
					console.log(resp.validations);
				    var globalMessage = '';
					for(var i = 0; i< resp.validations.length; i++) {
						var fieldName = resp.validations[i].field;
						var message = resp.validations[i].message;
						console.log(fieldName +  ' - ' + message);
						//query for the field
						var f = $(document.getElementById('error-'+fieldName));
						if(f) {
							f.html(message);
						}
						globalMessage = globalMessage + message + '<br/>';
						
					}
					
					showResponseErrorMessage(globalMessage);
					
					
				} else {
					console.log('Wrong status ' + status);
					showResponseErrorMessage('<s:message code="error.code.99" text="An error message occured while trying to process the payment (99)"/>');
					
				}
		  },
		    error: function(xhr, textStatus, errorThrown) {
		    	$('#pageContainer').hideLoading();
		  		alert('error ' + errorThrown);
		  }

	});
	
}


function calculateTotal(){
	resetErrorMessage();
	$('#pageContainer').showLoading();
	var data = $(checkoutFormId).serialize();
	//console.log(data);
	
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/order/calculateOrderTotal.html"/>',
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){
		  
		    $('#pageContainer').hideLoading();
		  	if(response.errorMessage!==null && response.errorMessage!=='') {
		  		showErrorMessage(response.errorMessage);
		  		return;
		  	}

			//console.log(response);
			
			$('#summary-table tr.subt').remove();
			$('#totalRow').html('');
			var subTotalsTemplate = Hogan.compile(document.getElementById("subTotalsTemplate").innerHTML);
			var totalTemplate = Hogan.compile(document.getElementById("totalTemplate").innerHTML);
			var subTotalsRendered = subTotalsTemplate.render(response);
			var totalRendred = totalTemplate.render(response);
			

			//console.log(rendered);
			$('#summaryRows').append(subTotalsRendered);
			$('#totalRow').html(totalRendred);
			isFormValid();
	  },
	    error: function(xhr, textStatus, errorThrown) {
	    	$('#pageContainer').hideLoading();
	  		alert('error ' + errorThrown);
	  }

	});
}



$(document).ready(function() {

		<!-- 
			//can use masked input for phone (USA - CANADA)
		-->
		isFormValid();
		
		bindActions();

		$("input[type='text']").on("change keyup paste", function(){
			isFormValid();
		});
		
		<c:if test="${order.customer.billing.country!=null}">
			$('.billing-country-list').val('${order.customer.billing.country}');
			setCountrySettings('billing','${order.customer.billing.country}');
		</c:if>
		<c:if test="${order.customer.delivery.country!=null}">
			$('.shipping-country-list').val('${order.customer.delivery.country}');
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
			getZones('#billingStateList','#billingStateProvince','<c:out value="${order.customer.billing.country}" />','<c:out value="${order.customer.billing.zone}" />', null); 
		</c:if>
		
		<c:if test="${order.customer.delivery.stateProvince!=null && order.customer.delivery.stateProvince!=''}">  
			$('#deliveryStateList').hide();          
			$('#deliveryStateProvince').show(); 
			$('#deliveryStateProvince').val('<c:out value="${order.customer.delivery.stateProvince}"/>');
		</c:if>
		
		<c:if test="${order.customer.delivery.stateProvince==null || order.customer.delivery.stateProvince==''}">  
			$('#deliveryStateList').show();          
			$('#deliveryStateProvince').hide();
			getZones('#deliveryStateList','#deliveryStateProvince','<c:out value="${order.customer.delivery.country}" />','<c:out value="${order.customer.billing.zone}" />', null);
		</c:if>

		$(".billing-country-list").change(function() {
			getZones('#billingStateList','#billingStateProvince',$(this).val(),'<c:out value="${order.customer.billing.zone}" />', shippingQuotes);
			setCountrySettings('billing',$(this).val());
	    })
	    
	    $(".shipping-country-list").change(function() {
			getZones('#deliveryStateList','#deliveryStateProvince',$(this).val(),'<c:out value="${order.customer.delivery.zone}" />', shippingQuotes);
			setCountrySettings('delivery',$(this).val());
	    })
	    
	    $("#billingStateList").change(function() {
	    	shippingQuotes();	
	    })
	    
	    $("#shippingStateList").change(function() {
	    	shippingQuotes();		
	    })
	    
	    
	    

	    

	    
	    $('input[name=paymentMethodType]', checkoutFormId).click(function() {
	    	isFormValid();//change payment method
	    });
	    
		$("input[id=billingPostalCode]").blur(function() {
			shippingQuotes();
		});
		
		$("input[id=shippingPostalCode]").blur(function() {
			if (!$('#shipToBillingAdress').is(':checked')) {
				shippingQuotes();
			}
		});
		

		
});




</script>

	<div id="main-content" class="container row-fluid">
	<h1 class="checkout-title"><s:message code="label.checkout" text="Checkout" /></h1>
	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
				<p class="muted common-row"><s:message code="label.checkout.logon" text="Logon or signup to simplify the online purchase process!"/></p>
	</sec:authorize>

   <c:set var="commitUrl" value="${pageContext.request.contextPath}/shop/order/commitOrder.html"/>
   <form:form id="checkoutForm" method="POST" enctype="multipart/form-data" commandName="order" action="${commitUrl}">
	   

		<div class="row-fluid common-row" id="checkout">
				<div class="span12 col-md-12 no-padding">


					<!-- If error messages -->
					<div id="checkoutError"  class="<c:if test="${errorMessages!=null}">alert  alert-error alert-danger </c:if>">
						<c:if test="${errorMessages!=null}">
						<c:out value="${errorMessages}" />
						</c:if>
					</div>
					<!--alert-error-->

					<!-- row fluid span -->
					<div class="row-fluid">
					<!-- left column -->
					<div class="span8 col-md-8 no-padding-left">

										<!-- Billing box -->
										<div id="shippingBox" class="box">
											<span class="box-title">
												<p class="p-title"><s:message code="label.customer.billinginformation" text="Billing information"/></p>
											</span>
											
											
					
											<!-- First name - Last name -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
													
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.generic.firstname" text="First Name"/></label>
									    					<div class="controls"> 
										    					<s:message code="NotEmpty.customer.firstName" text="First name is required" var="msgFirstName"/>
										      					<form:input id="customer.firstName" cssClass="input-large required form-control form-control-lg" path="customer.billing.firstName" title="${msgFirstName}"/>
										    					<form:errors path="customer.billing.firstName" cssClass="error" />
										    					<span id="error-customer.billing.firstName" class="error"></span>
									    					</div> 
									  				   </div> 
													</div>
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.generic.lastname" text="Last Name"/></label>
									    					<div class="controls"> 
										    					<s:message code="NotEmpty.customer.lastName" text="Last name is required" var="msgLastName"/>
										    					<form:input id="customer.lastName" cssClass="input-large required form-control form-control-lg"  maxlength="32" path="customer.billing.lastName" title="${msgLastName}" />
										    					<form:errors path="customer.billing.lastName" cssClass="error" />
										    					<span id="error-customer.billing.lastName" class="error"></span>
									    					</div> 
									  				   </div> 
													</div>
											</div>
					
					
											<!-- email company -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.generic.email" text="Email address"/></label>
									    					<div class="controls">
										    					<s:message code="NotEmpty.customer.emailAddress" text="Email address is required" var="msgEmail"/> 
										    					<form:input id="customer.emailAddress" cssClass="input-large required email form-control form-control-lg" path="customer.emailAddress" title="${msgEmail}"/>
										    					<form:errors path="customer.emailAddress" cssClass="error" />
											    				<span id="error-customer.emailAddress" class="error"></span>
									    					</div> 
									  				   </div> 
													</div>
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.customer.billing.company" text="Billing company"/></label>
									    					<div class="controls"> 
										      					<form:input id="customer.billing.company" cssClass="input-large form-control form-control-lg" path="customer.billing.company"/>
										      					<form:errors path="customer.billing.company" cssClass="error" />
												    			<span id="error-customer.billing.company" class="error"></span>
									    					</div> 
									  				   </div> 
													</div>
											</div>
					
											<!--  street address -->
											<div class="row-fluid common-row row">
													<div class="span8 col-md-8">
										  			<div class="control-group form-group"> 
														<label><s:message code="label.generic.streetaddress" text="Street address"/></label>
										    				<div class="controls"> 
										    					<s:message code="NotEmpty.customer.billing.address" text="Address is required" var="msgAddress"/>
										      					<form:input id="customer.billing.address" cssClass="input-xxlarge required form-control form-control-lg" path="customer.billing.address" title="${msgAddress}"/>
										      					<form:errors path="customer.billing.address" cssClass="error" />
												    			<span id="error-customer.billing.address" class="error"></span>
										    				</div> 
										  			</div>
										  			</div> 
											</div>
					
											<!-- city - postal code -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
											  			<div class="control-group form-group"> 
															<label><s:message code="label.generic.city" text="City"/></label>
											    				<div class="controls"> 
											    					<s:message code="NotEmpty.customer.billing.city" text="City is required" var="msgCity"/>
											      					<form:input id="customer.billing.city" cssClass="input-large required form-control form-control-lg" path="customer.billing.city" title="${msgCity}"/>
											      					<form:errors path="customer.billing.city" cssClass="error" />
												    				<span id="error-customer.billing.city" class="error"></span>
											    				</div> 
											  			</div>
													</div>
													<div class="span4 col-md-4">
											  			<div class="control-group form-group"> 
															<label><s:message code="label.generic.postalcode" text="Postal code"/></label>
											    				<div class="controls"> 
											    					<s:message code="NotEmpty.customer.billing.postalCode" text="Postal code is required" var="msgPostalCode"/>
											      					<form:input id="billingPostalCode" cssClass="input-large required billing-postalCode form-control form-control-lg" path="customer.billing.postalCode" title="${msgPostalCode}"/>
																	<form:errors path="customer.billing.postalCode" cssClass="error" />
												    				<span id="error-customer.billing.postalCode" class="error"></span>
											    				</div> 
											  			</div>
													</div>
										   </div>
										   
										   <!-- state province -->
										   <div class="row-fluid common-row row">
										   			<div class="span8 col-md-8">
										   			<div class="control-group form-group"> 
														<label><s:message code="label.generic.stateprovince" text="State / Province"/></label>
											    		<div class="controls"> 
												       			<form:select cssClass="zone-list form-control form-control-lg" id="billingStateList" path="customer.billing.zone"/>
											                    <s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
											                    <form:input  class="input-large required form-control form-control-lg" id="billingStateProvince"  maxlength="100" name="billingStateProvince" path="customer.billing.stateProvince" title="${msgStateProvince}"/>
											                    <form:errors path="customer.billing.stateProvince" cssClass="error" />
												    			<span id="error-customer.billing.stateProvince" class="error"></span> 
											    		</div> 
											  		</div>
											  		</div>
										   </div>
								
										  <!-- country - phone - ship checkbox -->
									       <div class="row-fluid common-row row">
									       			<div class="span4 col-md-4">
										  			<div class="control-group form-group"> 
														<label><s:message code="label.generic.country" text="Country"/></label>
										    				<div class="controls"> 
										       					<form:select cssClass="billing-country-list form-control form-control-lg" path="customer.billing.country">
											  							<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
										       					</form:select>
										    				</div> 
										  			</div>
										  			</div>
											
													<div class="span4 col-md-4">
										  			<div class="control-group form-group"> 
														<label><s:message code="label.generic.phone" text="Phone number"/></label>
										    				<div class="controls"> 
										    					<s:message code="NotEmpty.customer.billing.phone" text="Phone number is required" var="msgPhone"/>
										      					<form:input id="customer.billing.phone" cssClass="input-large required billing-phone form-control form-control-lg" path="customer.billing.phone" title="${msgPhone}"/>
										      					<form:errors path="customer.billing.phone" cssClass="error" />
												    			<span id="error-customer.billing.phone" class="error"></span> 
										    				</div> 
										  			</div>
										  			</div>
													
									  	  </div>
									  	  
									  	  <c:if test="${shippingQuote!=null}">
											<!-- display only if a shipping quote exist -->
											<div class="row-fluid common-row row">
										   	<div class="span8 col-md-8">
											<label id="useAddress" class="checkbox"> 
											<form:checkbox path="shipToBillingAdress" id="shipToBillingAdress"/>
											<s:message code="label.customer.shipping.shipaddress" text="Ship to this address" /></label>
											</div>
											</div>
									  	  </c:if>
									</div>
									<!-- end billing box -->
					
									<c:if test="${shippingQuote!=null}">
									<br/>
									<!-- Shipping box -->
									<div id="deliveryBox" class="box">
											<span class="box-title">
												<p class="p-title"><s:message code="label.customer.shippinginformation" text="Shipping information"/></p>
											</span>
					
											<!-- First name - Last name -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.customer.shipping.firtsname" text="Shipping first name"/></label>
									    					<div class="controls"> 
									    					<s:message code="NotEmpty.customer.shipping.firstName" text="Shipping first name should not be empty" var="msgShippingFirstName"/>
									      					<form:input id="customer.delivery.name" cssClass="input-xxlarge required form-control form-control-lg" path="customer.delivery.firstName" title="${msgShippingFirstName}"/>
									    					</div> 
									  				   </div> 
													</div>
											</div>

											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
									  				   <div class="control-group"> 
														<label><s:message code="label.customer.shipping.lastname" text="Shipping last name"/></label>
									    					<div class="controls"> 
									    					<s:message code="NotEmpty.customer.shipping.lastName" text="Shipping last name should not be empty" var="msgShippingLastName"/>
									      					<form:input id="customer.delivery.name" cssClass="input-xxlarge required form-control form-control-lg" path="customer.delivery.lastName" title="${msgShippingLastName}"/>
									    					</div> 
									  				   </div> 
													</div>
											</div>					
					
											<!-- company -->
											<div class="row-fluid common-row row">
												<div class="span4 col-md-4">
									  				   <div class="control-group"> 
														<label><s:message code="label.customer.shipping.company" text="Shipping company"/></label>
									    					<div class="controls"> 
									      					<form:input id="customer.delivery.company" cssClass="input-large form-control form-control-lg" path="customer.delivery.company"/>
									    					</div> 
									  				   </div>
									  			</div> 
											</div>
					
											<!--  street address -->
											<div class="row-fluid common-row row">
												<div class="span8 col-md-8">
										  			<div class="control-group"> 
														<label><s:message code="label.customer.shipping.streetaddress" text="Shipping street address"/></label>
										    				<div class="controls"> 
										    					<s:message code="NotEmpty.customer.shipping.address" text="Shipping street address should not be empty" var="msgShippingAddress"/>
										      					<form:input id="customer.delivery.address" cssClass="input-xxlarge required form-control form-control-lg" path="customer.delivery.address" title="${msgShippingAddress}"/>
										    				</div> 
										  			</div> 
										  		</div>
											</div>
					
											<!-- city - postal code -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
											  			<div class="control-group"> 
															<label><s:message code="label.customer.shipping.city" text="Shipping city"/></label>
											    				<div class="controls">
											    					<s:message code="NotEmpty.customer.shipping.city" text="Shipping city should not be empty" var="msgShippingCity"/> 
											      					<form:input id="customer.delivery.city" cssClass="input-large required form-control form-control-lg" path="customer.delivery.city" title="${msgShippingCity}"/>
											    				</div> 
											  			</div>
													</div>
													<div class="span4 col-md-4">
											  			<div class="control-group form-group"> 
															<label><s:message code="label.customer.shipping.postalcode" text="Shipping postal code"/></label>
											    				<div class="controls"> 
											    				    <s:message code="NotEmpty.customer.shipping.postalcode" text="Shipping postal code should not be empty" var="msgShippingPostal"/>
											      					<form:input id="deliveryPostalCode" cssClass="input-large required delivery-postalCode form-control form-control-lg" path="customer.delivery.postalCode" title="${msgShippingPostal}"/>
											    				</div> 
											  			</div>
													</div>
										   </div>
										   
										   <!-- state province -->
										   <div class="row-fluid common-row row">
										   			<div class="span8 col-md-8">
										   			<div class="control-group form group"> 
														<label><s:message code="label.customer.shipping.zone" text="Shipping state / province"/></label>
											    		<div class="controls"> 
												       			<form:select cssClass="zone-list form-control" id="deliveryStateList" path="customer.delivery.zone"/>
											                    <s:message code="NotEmpty.customer.shipping.stateProvince" text="Shipping State / Province is required" var="msgShippingState"/>
											                    <form:input  class="input-large required form-control form-control-lg" id="deliveryStateProvince"  maxlength="100" name="shippingStateProvince" path="customer.delivery.stateProvince" title="${msgShippingState}"/> 
											    		</div> 
											  		</div>
											  		</div>
										   </div>
								
										  <!-- country -->
									       <div class="row-fluid common-row row">
									       			<div class="span8 col-md-8">
										  			<div class="control-group form-group"> 
														<label><s:message code="label.customer.shipping.country" text="Shipping country"/></label>
										    				<div class="controls"> 
										       					<form:select cssClass="shipping-country-list form-control" path="customer.delivery.country">
											  							<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
										       					</form:select>
										    				</div> 
										  			</div>
										  			</div>
									  	  </div>
									</div>
									</c:if>
									
									
									
									
									
									<!-- Shipping box -->
									<c:if test="${shippingQuote!=null}">
									 <br/> 
									<!-- Shipping -->
									<div class="box">
										<span class="box-title">
												<p class="p-title"><s:message code="label.shipping.fees" text="Shipping fees" /> </p>
										</span>
								
								        <c:choose>
								        <c:when test="${fn:length(shippingQuote.shippingOptions)>0}">
								        	<input type="hidden" name="shippingModule" value="${shippingQuote.shippingModuleCode}">
									        <div id="shippingSection" class="control-group"> 
							 					<label class="control-label">
							 						<s:message code="label.shipping.options" text="Shipping options"/>
							 						<c:if test="${shippingQuote.handlingFees!=null && shippingQuote.handlingFees>0}">
								       					&nbsp;(<s:message code="label.shipping.handlingfees" text="Handling fees" />&nbsp;<sm:monetary value="${shippingQuote.handlingFees}"/>)
								       				</c:if>
							 					</label> 
							 					<div class="controls"> 
							 						<c:forEach items="${shippingQuote.shippingOptions}" var="option" varStatus="status">
														<label class="radio">
															<input type="radio" name="selectedShippingOption.optionId" class="shippingOption" id="${option.optionId}" value="${option.optionId}" <c:if test="${order.selectedShippingOption!=null && order.selectedShippingOption.optionId==option.optionId}">checked="checked"</c:if>> 
															${option.optionName} - ${option.optionPriceText}
														</label> 
													</c:forEach>
												</div> 
									       	</div>
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
									       						<strong><s:message code="label.shipping.freeshipping" text="Free shipping!"/></strong>
									       					</c:otherwise>
								       					</c:choose>
								       				  </c:otherwise>
								       				</c:choose>
								       			</c:otherwise>								       	
								       		</c:choose>
								       	</c:otherwise>
								       	</c:choose> 
									</div>
									<!-- end shipping box -->
									</c:if>
									<br/>
									
									<c:if test="${fn:length(paymentMethods)>0}">
									<!-- payment box -->
									<div class="box">
										<span class="box-title">
											<p class="p-title"><s:message code="label.payment.module.title" text="Payment method" /></p>
										</span>

									    		<div class="tabbable"> 
												    	<ul class="nav nav-tabs">
												    		<c:forEach items="${paymentMethods}" var="paymentMethod">
												    			<li class="<c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>">
												    				<a href="#${paymentMethod.paymentType}" data-toggle="tab">
												    					<c:choose>
												    						<c:when test="${paymentMethod.paymentType=='CREDITCARD' || paymentMethod.paymentType=='PAYPAL'}">
												    							<c:if test="${paymentMethod.paymentType=='CREDITCARD'}">
												    								<img src="<c:url value="/resources/img/payment/icons/visa-straight-64px.png"/>" width="40">
												    								<img src="<c:url value="/resources/img/payment/icons/mastercard-straight-64px.png"/>" width="40">
												    								<img src="<c:url value="/resources/img/payment/icons/american-express-straight-64px.png"/>" width="40">
												    							</c:if>
												    							<c:if test="${paymentMethod.paymentType=='PAYPAL'}"><img src="<c:url value="/resources/img/payment/icons/paypal-straight-64px.png"/>" width="40"></c:if>
												    						</c:when>
												    						<c:otherwise>
												    							<h4><s:message code="payment.type.${paymentMethod.paymentType}" text="Payment method type [payment.type.${paymentMethod.paymentType}] not defined in payment.properties" /></h4>
												    						</c:otherwise>
												    					</c:choose>
												    				</a>
												    			</li>
												            </c:forEach>
												        </ul>
												        
											        		
												        
												        
									    				<div class="tab-content">
									    				<c:forEach items="${paymentMethods}" var="paymentMethod">
													    		<div class="tab-pane <c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>" id="${paymentMethod.paymentType}">
													    			<c:choose>
													    				<c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">
													    						<c:set var="paymentModule" value="${order.paymentMethodType}" scope="request"/>
													    				</c:when>
													    				<c:otherwise>
													    						<c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">
													    							<c:set var="paymentModule" value="${paymentMethod.paymentMethodCode}" scope="request"/>
													    						</c:if>
													    				</c:otherwise>
													    			</c:choose>
													    			<c:set var="selectedPaymentMethod" value="${order.paymentMethodType}" scope="request"/>
													    			<c:set var="paymentMethod" value="${paymentMethod}" scope="request"/>
													    			<c:set var="pageName" value="${fn:toLowerCase(paymentMethod.paymentType)}" />
													    			<jsp:include page="/pages/shop/common/checkout/${pageName}.jsp" />
													    		</div>
									    				</c:forEach>
									    				
									    				<input type="hidden" id="paymentModule" name="paymentModule" value="<c:choose><c:when test="${order.paymentModule!=null}"><c:out value="${order.paymentModule}"/></c:when><c:otherwise><c:out value="${paymentModule}" /></c:otherwise></c:choose>"/>
									    				</div>
									 			</div>
									</div>
									<!-- end payment box -->
									</c:if>
									
									
							
									
									
					</div>
					<!-- end left column -->


					<!-- Order summary right column -->
					<div class="span4 col-md-4 no-padding">
					
										<!-- order summary box -->
										<div class="box">
											<span id="summaryBox" class="box-title">
												<p class="p-title"><s:message code="label.order.summary" text="Order summary" /></p>
											</span>

											<table id="summary-table" class="table table-condensed table-hover">
												<thead> 
													<tr> 
														<th width="55%"><s:message code="label.order.item" text="Item" /></th> 
														<th width="15%"><s:message code="label.quantity" text="Quantity" /></th> 
														<th width="15%"><s:message code="label.order.price" text="Price" /></th>
														<th width="15%"><s:message code="label.order.total" text="Total" /></th>  
													</tr> 
												</thead> 
									
												<tbody id="summaryRows"> 
													<c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem">
													<tr class="item"> 
														<td>
															${shoppingCartItem.name}
															<c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
															<br/>
																<ul>
																	<c:forEach items="${shoppingCartItem.shoppingCartAttributes}" var="option">
																	<li>${option.optionName} - ${option.optionValue}</li>
																	</c:forEach>
																</ul>
															</c:if>
														</td> 
														<td >${shoppingCartItem.quantity}</td> 
														<td><strong>${shoppingCartItem.price}</strong></td> 
														<td><strong>${shoppingCartItem.subTotal}</strong></td> 
													</tr>
													</c:forEach>
													<!-- subtotals -->
													<c:forEach items="${order.orderTotalSummary.totals}" var="total">
													<c:if test="${total.orderTotalCode!='order.total.total'}">
													<tr class="subt"> 
														<td colspan="3"><s:message code="${total.orderTotalCode}" text="${total.orderTotalCode}"/></td> 
														<td><strong><sm:monetary value="${total.value}" /></strong></td> 
													</tr> 
													</c:if>
													</c:forEach>
												</tbody> 
											</table>
					
					
											<div id="totalRow" class="total-box">
												<span class="total-box-grand-total">
													<font class="total-box-label">
													<s:message code="order.total.total" text="Total"/>
													<font class="total-box-price"><sm:monetary value="${order.orderTotalSummary.total}"/></font>
													</font>
												</span>
											</div>					
										</div>
										<!--  end order summary box -->
										<br/>
										<div id="formErrorMessage" class="alert">
										</div>
										<!-- Submit -->
										<div class="form-actions">
											<div class="pull-right"> 
												<button id="submitOrder" type="submit" class="btn btn-large btn-success 
												<c:if test="${errorMessages!=null}"> btn-disabled</c:if>" 
												<c:if test="${errorMessages!=null}"> disabled="true"</c:if>
												><s:message code="button.label.submitorder" text="Submit order"/></button>

											</div>
										</div> 
			
					</div>
					<!-- end right column -->

			    </div>
			    <!-- end row fluid span -->
			    </div>
			    <!-- end span 12 -->

		</div>
		<!-- end row fluid -->
			
	</form:form>
	</div>