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


<c:if test="${googleMapsKey != ''}">
	<script src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${googleMapsKey}"/>&libraries=places&callback=googleInitialize"
		        async defer></script>
</c:if>



<c:set var="creditCardInformationsPage" value="creditCardInformations" scope="request" />

<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>


<!-- subtotals template -->
<script type="text/html" id="subTotalsTemplate">
		{{#subTotals}}
			<tr class="subt"> 
				<td colspan="2">{{#discounted}}<s:message code="label.generic.rebate" text="Rebate" />&nbsp;-&nbsp;{{text}}{{/discounted}}{{^discounted}}{{title}}{{/discounted}}</td> 
				<td><strong>{{#discounted}}<font color="red">-{{total}}</font>{{/discounted}}{{^discounted}}{{total}}{{/discounted}}</strong></td> 
			</tr>
		{{/subTotals}}
</script>

<!-- total template -->
<script type="text/html" id="totalTemplate">
		<span class="total-box-grand-total">
			<font class="total-box-label">
			  <s:message code="order.total.total" text="Total"/>
			  <font class="total-box-price grand-total">{{grandTotal}}</font>
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
		<div id="shippingOptions" class="controls">
			{{#shippingOptions}}	
				<label class="radio"> 
					<input type="radio" name="selectedShippingOption.optionId" code="{{shippingModuleCode}}" class="shippingOption" id="{{optionId}}" value="{{optionId}}" {{#checked}} checked="checked"{{/checked}}> 
					{{description}} - {{optionPriceText}}
					<br/>
					{{#note}}<small>{{note}}</small>{{/note}}
				</label>
			{{/shippingOptions}}						
		</div>

</script>


<script>

//populate provinces drop down list
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

<!-- creates a json representation of the form -->
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

<!-- checkout form id -->
var checkoutFormId = '#checkoutForm';

<!-- checkout field id -->
var formErrorMessageId = '#formErrorMessage';
var useDistanceWindow = <c:out value="${shippingMetaData.useDistanceModule}"/>;


function isFormValid() {
	$(formErrorMessageId).hide();//reset error message
	var $inputs = $(checkoutFormId).find(':input');
	var valid = true;
	var firstErrorMessage = null;
	$inputs.each(function() {
		if($(this).hasClass('required')) {
			console.log('Before ischecout field valid');
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
		
		//validate basic card at the end
		if(valid) {
			if ( typeof basicCardValidation == 'function' ) { 
				firstErrorMessage = '<s:message code="message.creditcard.invalid" text="Invalid credit card details"/>';
				valid = basicCardValidation();
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

function setPaymentModule(module) {
	console.log('Module - ' + module);
	$('#paymentModule').val(module);
	var pType = module;
	
	if(module.indexOf('paypal') >= 0) {
		$('#paymentMethodType').val('PAYPAL');
	}
	else if(module.indexOf('stripe') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
	}
	else if(module.indexOf('stripe3') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
	}
	else if(module.indexOf('beanstream') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
	}
	else if(module.indexOf('braintree') >= 0) {
			$('#paymentMethodType').val('CREDITCARD');
			console.log('TYPE ' + $('#paymentMethodType').val());
	} else {
		pType = pType.toUpperCase();
		console.log('Other type - ' + pType);
		$('#paymentMethodType').val(pType);
	}
	
	//TODO set the TAB to the payment type
	isFormValid();
	
}

function isCheckoutFieldValid(field) {
	
	//console.log('Entering is checkout valid');
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
		//var paymentMethod = $('input[name=paymentMethodType]:checked', checkoutFormId).val();
		var paymentType = $('input[name=paymentMethodType]').val();
		if(!paymentType) {
			paymentType = '${order.paymentMethodType}';
		}
		console.log('Payment Method Type ' + paymentType);
		if(paymentType=='CREDITCARD') {
			console.log(paymentType);
			console.log(fieldId);
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
					$(listDiv).addClass('required');
					$(listDiv).show();  
					$(textDiv).removeClass('required');
					$(textDiv).hide();
					$(listDiv).addItems(listDiv, data, defaultValue);		
				} else {
					$(listDiv).removeClass('required');
					$(listDiv).hide();  
					$(textDiv).addClass('required');
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


	
}

function bindCalculateShipping() {
	
    $(".shippingOption").click(function() {
    	console.log('shipping module code ' + $(this).attr('code'));
    	$('#shippingModule').val($(this).attr('code'));
    	calculateTotal();
    });
	
}



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
    	shippingQuotes();	
    })
    
    $("#shippingStateList").change(function() {
    	shippingQuotes();		
    })
    
    $("input[id=billingPostalCode]").on('blur input', function() {
		if ($('#shipToBillingAdress').is(':checked')) {
			shippingQuotes();
		}
     });
	
     $("input[id=deliveryPostalCode]").on('blur input', function() {
		if (!$('#shipToBillingAdress').is(':checked')) {
			shippingQuotes();
		}
     });
    
    //shipping / billing decision checkbox
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
		
		
		$('#pageContainer').showLoading();
		//var paymentSelection = $('input[name=paymentMethodType]:checked', checkoutFormId).val();
		var paymentSelection = $('#paymentModule').val();
		console.log('Selection ' + paymentSelection);
		if(paymentSelection.indexOf('paypal') >= 0) {
			
			//console.log('PP ');
			$('#paymentMethodType').val('PAYPAL');
			initPayment('PAYPAL');
		}
		else if(paymentSelection.indexOf('stripe3') >= 0) {
			//console.log('Stripe ');
			$('#paymentMethodType').val('CREDITCARD');
			initStripePayment3();
		}
		else if(paymentSelection.indexOf('stripe') >= 0) {
			//console.log('Stripe ');
			$('#paymentMethodType').val('CREDITCARD');
			initStripePayment();
		}
		else if(paymentSelection.indexOf('braintree') >= 0) {
			//console.log('Braintree ');
			$('#paymentMethodType').val('CREDITCARD');
			log('Set payment method type ' + $('#paymentMethodType').val());
			initBraintreePayment();
		}
		else if(paymentSelection.indexOf('moneyorder') >= 0) {
			log('Money order ' + $('input[name=paymentMethodType]').val());
			$('#paymentMethodType').attr("value", 'MONEYORDER');
			log('Payment method type -> ' + $('input[name=paymentMethodType]').val());
			submitForm()
		}
		else if(paymentSelection.indexOf('beanstream') >= 0) {
			//console.log('Beanstream ');
			$('#paymentMethodType').val('CREDITCARD');
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


/** 
 * Calculates a shipping quote based on customer address
 */
function shippingQuotes(){

	resetErrorMessage();
	$('#pageContainer').showLoading();
	var data = $(checkoutFormId).serialize();
	//log(data);
	
	formValid = false;

	
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/order/shippingQuotes.json"/>',
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){
		  
		    $('#pageContainer').hideLoading();
		  	if(response.errorMessage!=null && response.errorMessage!='') {

		  		//log('Error message !!! ' + response.errorMessage);
		  		showErrorMessage(response.errorMessage);
		  		//reset shipping options
		  		$('#shippingSection').html('');
		  		$('#shippingSection').html(response.errorMessage);
		  		
		  		$('#confirm_address').remove();
		  		$("#confirmShippingAddress").hide();
		  		
		  		return;
		  	}

			//log(response);
			
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
				bindCalculateShipping();
				///bindActions();//TODO NO NO NO

			} 
			$('#summaryRows').append(subTotalsRendered);
			$('#totalRow').html(totalRendred);
			formValid = isFormValid();
			
			//if(formValid && response.shippingSummary!=null) {
			validateConfirmShipping(response);
			//}
			
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
			  	//$('#submitOrder').enable();
			    $('#pageContainer').hideLoading();
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
		    	$('#pageContainer').hideLoading();
		  		alert('error ' + errorThrown);
		  }

	});
	
}

function buildMailAddress(address) {
	
	if(address==null) {
		return null;
	}
	
	var addr = '';
	
	//civic address
	addr = address.address + '\r\n';
	
	
	//municipality/town state/province postal code
	addr = addr + address.zone + ' ' + address.postalCode + '\r\n';
	
	//country
	addr = addr + address.country;
	
	return addr;
}

function validateConfirmShipping(shopOrder) {

	postalCode = null;
	shippingDistance = null;
	shippingMethod = null;

	if(shopOrder!=null) {
	
		if(shopOrder.shippingSummary.selectedShippingOption!=null) {
			shippingMethod = shopOrder.shippingSummary.selectedShippingOption.shippingModuleCode;
		}

		//build address object

		//displayConfirmShipping(shippingDistance,postalCode,shippingMethod);
		if(shopOrder.shippingSummary != null) {
			var delivery = shopOrder.shippingSummary.delivery;
			displayConfirmShipping(delivery,shippingMethod);
		}
	
	}
	
}

function displayConfirmShipping(delivery,shippingMethod) {
	
	/**
	* Requires this div in the form
	* <div class="checkout-box" id="confirmShippingAddress" style="height:250px;"></div>
	*
	**/

	var $form = $('#checkoutForm');
	$('#confirm_address').remove();
	$("#confirmShippingAddress").hide();
	var deliveryAddress = buildMailAddress(delivery);

	/**
	* quote =! storePickup
	* postal code
	* latitude
	**/
	
	/**
	* If distance is configured and has been set in the quote
	* confirm the shipping address on a Map
	**/
	if(useDistanceWindow) {
		/**
		* Requires a shipping quote (response.shippingSummary)
		* and no order form validation error
		* then display the shipping confirmation window
		**/
		if(delivery!=null && delivery.latitude!=null && delivery.longitude && delivery.postalCode!=null && shippingMethod!=null) {
			if(shippingMethod!='storePickUp') {


				$("#confirmShippingAddress").show();
				
				var distanceField = '<input type="hidden" id="confirm_address" name="confirm_address" value="true" />';
				$form.append(distanceField);
				//longitude
				//latitude
				//google maps
				var lat = Number(delivery.latitude);
				var lon = Number(delivery.longitude);
					
				var myLatlng = new google.maps.LatLng(lat,lon);
				var mapOptions = {
				  zoom: 18,
				  center: myLatlng
				}
				var map = new google.maps.Map(document.getElementById("confirmShippingAddress"), mapOptions);

				var marker = new google.maps.Marker({
				    position: myLatlng,
				    title:deliveryAddress
				});

				// To add the marker to the map, call setMap();
				marker.setMap(map);

				$("#confirmShippingAddress").show();
			}
		}


	}
	
}

//when changing a shipping method
function calculateTotal(){
	resetErrorMessage();
	$('#pageContainer').showLoading();
	var data = $(checkoutFormId).serialize();
	//console.log(data);
	formValid = false;
	
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/order/calculateOrderTotal.json"/>',
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
			formValid = isFormValid();
			validateConfirmShipping(response);
	  },
	    error: function(xhr, textStatus, errorThrown) {
	    	$('#pageContainer').hideLoading();
	  		console.log('error ' + errorThrown);
	  }

	});
}



$(document).ready(function() {
	
		$("#confirmShippingAddress").hide();
	
        formValid = false;	
	
		<!-- 
			//can use masked input for phone (USA - CANADA)
		-->
		formValid = isFormValid();
		
		paymentModule = '${order.defaultPaymentMethodCode}';
		if(!paymentModule) {
			paymentModule = '${order.paymentModule}';
		}
		
		setPaymentModule(paymentModule);

		bindActions();
		
	
		//$("input[type='text']").on("change keyup paste", function(){
		//	isFormValid();
		//});
		
		//$("input[type='checkbox']").on("change click", function(){
		//	isFormValid();
		//});
		
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
	    
	    //if($('#billingPostalCode').val()!=null || $('#deliveryPostalCode').val()!=null) {
	    	//console.log('billing or delivery set');
	    	//shippingQuotes();//TODO issue NPE
	    //}
	    

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
		displayConfirmShipping(address,shippingMethod);


});




</script>

	<div id="main-content" class="container row-fluid">
	<h1 class="checkout-title"><s:message code="label.checkout" text="Checkout" /></h1>
	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
				<p class="muted common-row"><s:message code="label.checkout.logon" text="Logon or signup to simplify the online purchase process!"/></p>
	</sec:authorize>

   <c:set var="commitUrl" value="${pageContext.request.contextPath}/shop/order/commitOrder.html"/>
   <form:form id="checkoutForm" method="POST" enctype="multipart/form-data" modelAttribute="order" action="${commitUrl}">
	   

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
										<div id="shippingBox" class="checkout-box">
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
										      					<form:input id="customer.firstName" cssClass="input-large required form-control form-control-lg" path="customer.billing.firstName" autofocus="autofocus" title="${msgFirstName}"/>
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
									<div id="deliveryBox" class="checkout-box">
											<span class="box-title">
												<p class="p-title"><s:message code="label.customer.shippinginformation" text="Shipping information"/></p>
											</span>
					
											<!-- First name - Last name -->
											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
														<label><s:message code="label.customer.shipping.firstname" text="Shipping first name"/></label>
									    					<div class="controls"> 
									    					<s:message code="NotEmpty.customer.shipping.firstName" text="Shipping first name should not be empty" var="msgShippingFirstName"/>
									      					<form:input id="customer.delivery.name" cssClass="input-xxlarge required form-control form-control-lg" path="customer.delivery.firstName" title="${msgShippingFirstName}"/>
									    					</div> 
									  				   </div> 
													</div>
											</div>

											<div class="row-fluid common-row row">
													<div class="span4 col-md-4">
									  				   <div class="control-group form-group"> 
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
									  				   <div class="control-group form-group"> 
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
										  			<div class="control-group form-group"> 
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
											  			<div class="control-group form-group"> 
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
											    				    <s:message code="NotEmpty.customer.shipping.postalCode" text="Shipping postal code should not be empty" var="msgShippingPostal"/>
											      					<form:input id="deliveryPostalCode" cssClass="input-large required delivery-postalCode form-control form-control-lg" path="customer.delivery.postalCode" title="${msgShippingPostal}"/>
											    				</div> 
											  			</div>
													</div>
										   </div>
										   
										   <!-- state province -->
										   <div class="row-fluid common-row row">
										   			<div class="span8 col-md-8">
										   			<div class="control-group form-group"> 
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
									 <!--<br/>--> 
									<!-- Shipping -->
									<div class="checkout-box">
										<span class="box-title">
												<p class="p-title"><s:message code="label.shipping.fees" text="Shipping fees" /> </p>
										</span>

								        <c:choose>
								        <c:when test="${fn:length(shippingQuote.shippingOptions)>0}">
								        	<input type="hidden" id="shippingModule" name="shippingModule" value="${shippingQuote.shippingModuleCode}">
									        <div id="shippingSection" class="control-group"> 
							 					<label class="control-label">
							 						<s:message code="label.shipping.options" text="Shipping options"/>
							 						<c:if test="${shippingQuote.handlingFees!=null && shippingQuote.handlingFees>0}">
								       					&nbsp;(<s:message code="label.shipping.handlingfees" text="Handling fees" />&nbsp;<sm:monetary value="${shippingQuote.handlingFees}"/>)
								       				</c:if>
							 					</label> 
							 					<div id="shippingOptions" class="controls"> 
							 						<c:if test="${shippingQuote.shippingReturnCode=='NO_POSTAL_CODE'}">
							 								<strong>
									       						<s:message code="label.shipping.nopostalcode" text="A shipping quote will be available after filling the postal code"/>
									       					</strong>
							 						</c:if>
							 						<c:forEach items="${shippingQuote.shippingOptions}" var="option" varStatus="status">
														<label class="radio">
															<input type="radio" name="selectedShippingOption.optionId" class="shippingOption" code="${option.shippingModuleCode}" id="${option.optionId}" value="${option.optionId}" <c:if test="${shippingQuote.selectedShippingOption!=null && shippingQuote.selectedShippingOption.optionId==option.optionId}">checked="checked"</c:if>> 
															<s:message code="module.shipping.${option.shippingModuleCode}" arguments="${requestScope.MERCHANT_STORE.storename}" text="${option.shippingModuleCode}"/> - ${option.optionPriceText}
															<c:if test="${option.note!=null}">
																<br/><small><c:out value="${option.note}"/></small>
															</c:if>
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
									       						<c:choose>
									       							<c:when test="${shippingQuote.shippingReturnCode=='NO_POSTAL_CODE'}">
									       								<div id="shippingSection" class="control-group"> 
									       								<strong>
									       									<s:message code="label.shipping.nopostalcode" text="A shipping quote will be available after filling the postal code"/>
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
									</div>
									<!-- end shipping box -->
									</c:if>
									<!-- Confirm address box box -->
									<!-- Shipping -->
									<div class="checkout-box" id="confirmShippingAddress" style="height:250px;">
									</div>
									<!-- end confirm shipping box -->
									<br/>
														
									
									<c:if test="${fn:length(paymentMethods)>0}">
									<!-- payment box -->
									<div class="checkout-box">
										<span class="box-title">
											<p class="p-title"><s:message code="label.payment.module.title" text="Payment method" /></p>
										</span>

									    		<div class="tabbable"> 
												    	<ul class="nav nav-tabs nav-tabs-checkout">
												    		<c:forEach items="${paymentMethods}" var="paymentMethod">
												    			<li class="<c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>">
												    				<a href="#${paymentMethod.paymentType}" data-toggle="tab" class="paymentTab" onClick="setPaymentModule('${paymentMethod.paymentMethodCode}');">
												    					<c:choose>
												    						<c:when test="${paymentMethod.paymentType=='CREDITCARD' || paymentMethod.paymentType=='PAYPAL'}">
												    							<c:if test="${paymentMethod.paymentType=='CREDITCARD'}">
												    								<p id="cc-img-container">
												    									<img src="<c:url value="/resources/img/payment/icons/visa-straight-64px.png"/>" width="40" style="display:inline-block;">
												    									<img src="<c:url value="/resources/img/payment/icons/mastercard-straight-64px.png"/>" width="40" style="display:inline-block;">
												    								    <img src="<c:url value="/resources/img/payment/icons/american-express-straight-64px.png"/>" width="40" style="display:inline-block;">
												    								</p>
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
													    		<div class="payment-tab tab-pane <c:choose><c:when test="${order.paymentMethodType!=null && order.paymentMethodType==paymentMethod.paymentType}">active</c:when><c:otherwise><c:if test="${order.paymentMethodType==null && paymentMethod.defaultSelected==true}">active</c:if></c:otherwise></c:choose>" id="${paymentMethod.paymentType}">
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
													    			
													    			<!-- exception for stripe which has it's own page -->
													    			<c:choose>
													    				<c:when test="${(paymentMethod.paymentMethodCode=='stripe') or (paymentMethod.paymentMethodCode=='braintree') or (paymentMethod.paymentMethodCode=='stripe3')}">
													    					<c:set var="pageName" value="${fn:toLowerCase(paymentMethod.paymentMethodCode)}" />
													    				</c:when>
													    				<c:otherwise>
													    					<c:set var="pageName" value="${fn:toLowerCase(paymentMethod.paymentType)}" />
													    				</c:otherwise>
													    			</c:choose>
													    			<jsp:include page="/pages/shop/common/checkout/${pageName}.jsp" />
													    			
													    		</div>
									    				</c:forEach>
									    				<input type="hidden" id="paymentMethodType" name="paymentMethodType" value="<c:if test="${order.paymentMethodType!=null}"><c:out value="${order.paymentMethodType}"/></c:if>"/>
									    				<input type="hidden" id="paymentModule" name="paymentModule" value="<c:choose><c:when test="${order.paymentModule!=null}"><c:out value="${order.paymentModule}"/></c:when><c:otherwise><c:out value="${paymentModule}" /></c:otherwise></c:choose>"/>
									    				</div>
									 			</div>
									</div>
									<br/>
									<!-- end payment box -->
									</c:if>			
					</div>
					<!-- end left column -->


					<!-- Order summary right column -->
					<div class="span4 col-md-4 no-padding">
					
										<!-- order summary box -->
										<div class="checkout-box">
											<span id="summaryBox" class="box-title">
												<p class="p-title"><s:message code="label.order.summary" text="Order summary" /></p>
											</span>

											<table id="summary-table" class="table table-condensed table-hover">
												<thead> 
													<tr> 
														<th width="45%"><s:message code="label.order.item" text="Item" /></th> 
														<!--<th width="15%"><s:message code="label.quantity" text="Quantity" /></th>--> 
														<th width="20%"><s:message code="label.order.price" text="Price" /></th>
														<th width="20%"><s:message code="label.order.total" text="Total" /></th>  
													</tr> 
												</thead> 
									
												<tbody id="summaryRows"> 
													<c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem">
													<tr class="item"> 
														<td width="38%">
															${shoppingCartItem.quantity} x ${shoppingCartItem.name}
															<c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
															<br/>
																<ul>
																	<c:forEach items="${shoppingCartItem.shoppingCartAttributes}" var="option">
																	<li>${option.optionName} - ${option.optionValue}</li>
																	</c:forEach>
																</ul>
															</c:if>
														</td> 
														<!--<td width="15%">${shoppingCartItem.quantity}</td>--> 
														<td width="31%"><strong>${shoppingCartItem.price}</strong></td> 
														<td width="31%"><strong>${shoppingCartItem.subTotal}</strong></td> 
													</tr>
													</c:forEach>
													<!-- subtotals -->
													<c:forEach items="${order.orderTotalSummary.totals}" var="total">
													<c:if test="${total.orderTotalCode!='order.total.total'}">


													<tr class="subt"> 
														<td colspan="2">
														<c:choose>
																<c:when test="${total.orderTotalCode=='order.total.discount'}">
														<s:message code="label.generic.rebate" text="Rebate"/>&nbsp;-&nbsp;<s:message code="${total.text}" text="${total.text}"/>
																</c:when>
																<c:otherwise>
																	<s:message code="${total.orderTotalCode}" text="${total.orderTotalCode}"/>
																</c:otherwise>
														</c:choose>
														</td> 
														<td><strong><c:choose><c:when test="${total.orderTotalCode=='order.total.discount'}"><font color="red">- <sm:monetary value="${total.value}" /></font></c:when><c:otherwise><sm:monetary value="${total.value}" /></c:otherwise></c:choose></strong></td> 
													</tr> 

 
													</c:if>
													</c:forEach>
												</tbody> 
											</table>
					
					
											<div id="totalRow" class="total-box">
												<span class="total-box-grand-total">
													<font class="total-box-label">
													<s:message code="order.total.total" text="Total"/>
													<font class="total-box-price grand-total"><sm:monetary value="${order.orderTotalSummary.total}"/></font>
													</font>
												</span>
											</div>					
										</div>
										<!--  end order summary box -->
										<c:if test="${requestScope.CONFIGS['displayCustomerAgreement']==true}">
										<!-- customer agreement -->
										<div class="checkout-box" id="customerAgreementSection" class="">
											<label id="customerAgreement" class="checkbox"> 
											<s:message code="NotEmpty.customer.agreement" text="Please make sure you agree with terms and conditions" var="msgAgreement"/>
											<form:checkbox path="customerAgreed" id="customerAgreed" cssClass="required" title="${msgAgreement}"/>
											<a href="javascript:return false;" id="clickAgreement"><s:message code="label.customer.order.agreement" text="I agree with the terms and conditions" /></a>
											</label>
											<div id="customer-agreement-area">
														<c:choose>
															<c:when test="${requestScope.CONTENT['agreement']!=null}">
																<sm:pageContent contentCode="agreement"/>
															</c:when>
															<c:otherwise>
																<s:message code="message.content.missing.agreement" text="Content with code 'agreement' does not exist" />
															</c:otherwise>
														</c:choose>
											</div>
										</div>
										</c:if>
										
										<div id="formErrorMessage" class="alert">
										</div>
										<!-- Submit -->
										<div class="form-actions">
											<div class="pull-right"> 
												<button id="submitOrder" type="button" class="btn btn-large btn-success 
												<c:if test="${errorMessages!=null}"> btn-disabled</c:if>" 
												<c:if test="${errorMessages!=null}"> disabled="true"</c:if>
												><s:message code="button.label.submitorder" text="Submit order"/></button>
			
												<!-- submit can be a post or a pre ajax query -->
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