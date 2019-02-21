
	  var permissionsComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

    
      //fetch permissions
	  function permissions() {
    	  
  			showLoader();
  			var url = "/admin/references/permissions";
		    
  			var langs;
  			$.getJSON(url, function (data) {
  				$('#permissions').empty();
  				permissions = data;
		        $.each(permissions, function (i, permissions) {             
		        	 $('<div class="checkbox"><label><input type="checkbox" name="permissions[]" id="permissions" value="' + langs.code + '">' + langs.name + '</label></div>').appendTo('#languages'); 
		        });	        
		        hideLoader();
		    })
	  }
