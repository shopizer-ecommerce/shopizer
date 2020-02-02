

var placeSearch, autocomplete;

var componentForm = {
	 route: 'customer.billing.address',
	 street_number: 'customer.billing.address',
	 locality: 'customer.billing.city',
	 administrative_area_level_1: 'billingStateProvince',
	 country: 'customer.billing.country',
	 postal_code: 'billingPostalCode'
};
  
var placeForm = {
	 route: 'long_name',
	 street_number: 'short_name',
	 locality: 'long_name',
	 city: 'long_name',
	 administrative_area_level_1: 'short_name',
	 country: 'short_name',
	 postal_code: 'short_name'
 };

function initAutocomplete() {
    // Create the autocomplete object, restricting the search to geographical
    // location types.
    autocomplete = new google.maps.places.Autocomplete(
        /** @type {!HTMLInputElement} */(document.getElementById('addressAutocomplete')),
        {types: ['geocode']});

    // When the user selects an address from the dropdown, populate the address
    // fields in the form.
    autocomplete.addListener('place_changed', fillInAddress);
}

function geolocate() {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(function(position) {
        var geolocation = {
          lat: position.coords.latitude,
          lng: position.coords.longitude
        };
        var circle = new google.maps.Circle({
          center: geolocation,
          radius: position.coords.accuracy
        });
        autocomplete.setBounds(circle.getBounds());
      });
    }
  }

function fillInAddress() {
    // Get the place details from the autocomplete object.
    var place = autocomplete.getPlace();

    for (var component in componentForm) {
      console.log(componentForm[component]);
      document.getElementById(componentForm[component]).value = '';
      document.getElementById(componentForm[component]).disabled = false;
    }

    // Get each component of the address from the place details
    // and fill the corresponding field on the form.
    var streetNumber = '';
    var street = '';
    var streetField = '';
    var country = '';
    var zone = '';
    var postal = '';
    var longProvinceName = '';
    if(place.address_components!=null) {
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
          if (addressType == 'administrative_area_level_1') {
        	  var newVal = place.address_components[i]['long_name'];
        	  longProvinceName = newVal;
          }
          //content can vary from geolocation
          if (placeForm[addressType]) {
            var val = place.address_components[i][placeForm[addressType]];
            var field = componentForm[addressType];
            if (typeof field != "undefined") {
            	if(addressType == 'street_number') {
            		streetNumber = val;
            		continue;
            	} 
            	if(addressType == 'route') {
            		street = val;
            		continue;
            	} 
            	if(addressType == 'postal_code') {
            		postal = val;
            		continue;
            	} 
            	if(addressType == 'country') {
            		country = val;
            	}
            	if(addressType == 'administrative_area_level_1') {
            		zone = val;
            		continue;
            	} 
            	document.getElementById(componentForm[addressType]).value = val;
            }
          }
        }
    }

    if(streetNumber != '') {
  	  streetField = streetNumber + ' ';
    }
    if(street != '') {
  	  streetField = streetField + street;
    }
    
    document.getElementById('customer.billing.address').value = streetField;
    
    getZones('#billingStateList','#billingStateProvince',country,zone,longProvinceName,getLanguageCode(),null);

    if(postal != '') {
    	 //new quote
    	document.getElementById('billingPostalCode').value = postal;
    	if (typeof shippingQuote === "function") {
    	    shippingQuote();
    	}
    }

  }



  	
  	//function selectZone(code) {
    //	  if(code == null) {
    //		  var address = JSON.parse($.cookie('default-address'));
    //		  code = address.stateProvince;
    //	  }
    //	  var ctrl = $('#stateProvince');
    //	  if( !ctrl.is('input') ) {
    //	  	$("select[id=stateProvince] option[value="+code+"]").attr('selected','selected');
  	//	  }
    // }
