
	  var groupsComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

    
      //fetch permissions
	  function groups() {
    	  
  			showLoader();
  			var url = "/admin/references/groups";
		    
  			var langs;
  			$.getJSON(url, function (data) {
  				$('#groups').empty();
  				groups = data;
		        $.each(group, function (i, groups) {             
		        	 $('<div class="checkbox"><label><input type="checkbox" name="groups[]" id="groups" value="' + group.name + '">' + group.name + '</label></div>').appendTo('#groups'); 
		        });	        
		        hideLoader();
		    })
	  }
