
	  var addressComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

	  //autocomplete address bar

      var placeSearch, autocomplete;
      
      var componentForm = {
    	 route: 'address_address',
    	 street_number: 'address_address',
    	 locality: 'city',
    	 administrative_area_level_1: 'stateProvince',
    	 country: 'country',
    	 postal_code: 'postalCode'
      };
      
      var placeForm = {
    	 route: 'long_name',
    	 street_number: 'short_name',
    	 locality: 'long_name',
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

      function fillInAddress() {
        // Get the place details from the autocomplete object.
        var place = autocomplete.getPlace();

        for (var component in componentForm) {
          //log(componentForm[component]);
          document.getElementById(componentForm[component]).value = '';
          document.getElementById(componentForm[component]).disabled = false;
        }

        // Get each component of the address from the place details
        // and fill the corresponding field on the form.
        var streetNumber = '';
        var street = '';
        var streetField = '';
        var country;
        var zone;
        for (var i = 0; i < place.address_components.length; i++) {
          var addressType = place.address_components[i].types[0];
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
        zones(country,zone);
        if(streetNumber != '') {
      	  streetField = streetNumber + ' ';
        }
        if(street != '') {
      	  streetField = streetField + street;
        }
        document.getElementById('address_address').value = streetField;
       
      }

      // Bias the autocomplete object to the user's geographical location,
      // as supplied by the browser's 'navigator.geolocation' object.
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

  
      
      //country province dropdowns
	  function address() {
    	  
  			showLoader();
  			var countryCacheUrl = "/admin/references/country?lang=" + addressComponent.LANGUAGE;
		    
  			$.getJSON(countryCacheUrl, function (data) {

  				$('#country').empty();
		        $.each(data, function (i, data) {      // bind the dropdown list using json result              
                    $("#country").append($("<option></option>").val(data.code).html(data.name)); 
		        });
		        selectCountryLocation();
		        hideLoader();
		    })

	  }
      
      //get zones for a specific country code
	  function zones(code,selected) {
    	  
			showLoader();

			var zonesCacheUrl = "/admin/references/zones?code="+ code  + "&lang=" + addressComponent.LANGUAGE;
			$('#stateProvince').replaceWith("<select id='stateProvince' name='address[stateProvince]' class='form-control' style='border-radius:0px !important;'></select>");
			$.getJSON(zonesCacheUrl, function(data) {
					$('#stateProvince').empty();
			        $.each(data, function (i, data) {      // bind the dropdown list using json result              
			        	$("#stateProvince").append($("<option></option>").val(data.code).html(data.name)); 
			        });
				})
				.fail(function() {
					//change select to plain text field
					$('#stateProvince').replaceWith("<input class='form-control' name='address[stateProvince]' id='stateProvince'>");
				 })
				.always(function() {
					selectZone(selected);  
					hideLoader();
				});

		}
      
        function selectCountryLocation() {
		    if('CREATE' == addressComponent.MODE) {
		    	var address = JSON.parse($.cookie('default-address'));
  				selectCountry(address.country);
  				zones(address.country,address.stateProvince);
		    }
        }
      
      	function selectCountry(code) {
	    	  $("select[id=country] option[value="+code+"]").attr('selected','selected');
	     }
      	
      	function selectZone(code) {
	    	  if(code == null) {
	    		  var address = JSON.parse($.cookie('default-address'));
	    		  code = address.stateProvince;
	    	  }
	    	  $("select[id=stateProvince] option[value="+code+"]").attr('selected','selected');
	     }

