/**
* Javascripts for the checkout
*
**/

//apply form masks
function setCountrySettings(prefix, countryCode) {
	//add masks to a country
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

//get zones

/** 
 * Specify 
 * div list container
 * text div (shown or not)
 * selected countryCode
 * preselected short zone value
 * preselected long zone value
 * language (en|fr...)
 * callback to invoke
 */
function getZones(listDiv, textDiv, countryCode, defaultValue, defaultLongValue, lang, callBackFunction){
	$.ajax({
	  type: 'POST',
	  url: getContextPath() + '/shop/reference/provinces.html',
	  data: 'countryCode=' + countryCode + '&lang=' + lang,
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
				$(textDiv).val(defaultLongValue);
			}
			
			if(callBackFunction!=null) {
				if (typeof callBackFunction === "function") { 
					callBackFunction();
				}
			}
	  },
	    error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }

	});
}



function shippingQuotes(url,useDistanceWindow){

	resetErrorMessage();
	showSMLoading('#pageContainer');
	var data = $(checkoutFormId).serialize();
	//log(data);
	
	//log('Checked ? ' + $('#shipToDeliveryAddress').is(':checked'));
	if ($('#shipToDeliveryAddress').is(':checked')) {
		//log('It is checked');
		data.shipToBillingAdress = false;
		log(data);
	}
	
	formValid = false;

	
	$.ajax({
	  type: 'POST',
	  url: url,
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){
		  
		    hideSMLoading('#pageContainer');
		  	if(response.errorMessage!=null && response.errorMessage!='') {

		  		showErrorMessage(response.errorMessage);
		  		//reset shipping options
		  		$('#shippingSection').html('');
		  		$('#shippingSection').html(response.errorMessage);
		  		
		  		$('#confirm_address').remove();
		  		$("#confirmShippingAddress").hide();
		  		
		  		return;
		  	}

			//log(response);
			
		  	//remove subtotal
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
			validateConfirmShipping(response,useDistanceWindow);
			//}
			
	  },
	    error: function(xhr, textStatus, errorThrown) {
	    	hideSMLoading('#pageContainer');
	  		alert('error ' + errorThrown);
	  }

	});
	
}

function validateConfirmShipping(shopOrder,useDistanceWindow) {

	postalCode = null;
	shippingDistance = null;
	shippingMethod = null;
	
	//console.log('vaidateConfirmShipping');

	if(shopOrder!=null) {
	
		if(shopOrder.shippingSummary != null && shopOrder.shippingSummary.selectedShippingOption!=null) {
			shippingMethod = shopOrder.shippingSummary.selectedShippingOption.shippingModuleCode;
		}

		//build address object

		//displayConfirmShipping(shippingDistance,postalCode,shippingMethod);
		if(shopOrder.shippingSummary != null) {
			var delivery = shopOrder.shippingSummary.delivery;
			displayConfirmShipping(delivery,shippingMethod,useDistanceWindow);
		}
	
	}
	
}

function displayConfirmShipping(delivery,shippingMethod,useDistanceWindow) {
	
	/**
	* Requires this div in the form
	* <div id="confirmShippingAddress" style="height:250px;"></div>
	*
	**/

	var $form = $('#checkoutForm');
	$('#confirm_address').remove();
	$("#confirmShippingAddress").hide();
	var deliveryAddress = buildMailAddress(delivery);
	//console.log('Use distance: ' + useDistanceWindow + ' lat: ' + delivery.latitude + ' lon: ' + delivery.longitude + ' postal code: ' + delivery.postalCode + ' shipping method: ' + shippingMethod);
	
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

function bindCalculateShipping() {
	
    $(".shippingOption").click(function() {
    	console.log('shipping module code ' + $(this).attr('code'));
    	$('#shippingModule').val($(this).attr('code'));
    	var useDistance = $('#useDistanceWindow').val();
        console.log('Use distance ' + useDistance);
    	calculateTotal(calculateTotalUrl,useDistance);
    });
	
}

function calculateTotal(url,useDistanceWindow){
	console.log('URL' + url);
	console.log('Distance window ' + useDistanceWindow);
	resetErrorMessage();
	showSMLoading('#pageContainer');
	var data = $(checkoutFormId).serialize();
	//console.log(data);
	formValid = false;
	
	$.ajax({
	  type: 'POST',
	  url: url,
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){

		    hideSMLoading('#pageContainer');
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
			validateConfirmShipping(response,useDistanceWindow);
	  },
	    error: function(xhr, textStatus, errorThrown) {
	    	hideSMLoading('#pageContainer');
	  		console.log('error ' + errorThrown);
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

function setPaymentModule(module) {
	console.log('Module - ' + module);
	$('#paymentModule').val(module);
	var pType = module;
	
	if(module.indexOf('paypal') >= 0) {
		//$('#paymentMethodType').val('PAYPAL');
		$('#paymentMethodType').attr("value", 'PAYPAL');
	}
	else if(module.indexOf('stripe') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
		$('#paymentMethodType').attr("value", 'CREDITCARD');
	}		
	else if(module.indexOf('beanstream') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
		$('#paymentMethodType').attr("value", 'CREDITCARD');
		
	}
	else if(module.indexOf('braintree') >= 0) {
		$('#paymentMethodType').val('CREDITCARD');
		$('#paymentMethodType').attr("value", 'CREDITCARD');
		
	}
	else {
		pType = pType.toUpperCase();
		console.log('Other type - ' + pType);
		$('#paymentMethodType').val(pType);
	}
	
	//TODO set the TAB to the payment type
	
}