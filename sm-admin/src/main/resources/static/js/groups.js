
	  var groupsComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

    
      //fetch permissions
	  function groups() {
    	  
  			showLoader();
  			var url = "/admin/references/groups";

  			$.getJSON(url, function (data) {
  				$('#groups').empty();
  				groups = data;
		        $.each(groups, function (i, group) {             
		        	 $('<div class="checkbox"><label><input type="checkbox" name="groups[]" id="groups" value="' + group.name + '">' + group.name + '</label></div>').appendTo('#groups'); 
		        });	        
		        hideLoader();
		    })
	  }
