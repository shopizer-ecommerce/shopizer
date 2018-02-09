    /**
     * customer functionality for store front
     */


$.fn.addZoneItems = function(div, data, defaultValue) {
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


function getZones(countryCode, zoneCode, callBackFunction){

	//console.log('Zone code ' + zoneCode);
	var url = getContextPath() + '/shop/reference/provinces.html';
	var data = 'countryCode=' + countryCode + '&lang=' + getLanguageCode();
	
	$.ajax({
		  type: 'POST',
		  url: url,
		  data: data,
		  dataType: 'json',
		  success: function(responseObj){

			  if((responseObj.response.status == 0 || responseObj.response.status ==9999) && responseObj.response.data){
				//$("#registration_zones option").remove();
				$('#customer_zones').show();  
				$('#hidden_zones').hide();

					
				//var zone = $('#registration_zones');
				$('#customer_zones').addZoneItems('#customer_zones', responseObj.response.data, zoneCode);
			
			  } else {
				  $('#customer_zones').hide();  
				  $('#hidden_zones').show();

			  }
			  if(callBackFunction!=null) {
					callBackFunction();
			  }
		   },
			    error: function(xhr, textStatus, errorThrown) {
			  	alert('error ' + errorThrown);
		  }
		
		
	});

}




