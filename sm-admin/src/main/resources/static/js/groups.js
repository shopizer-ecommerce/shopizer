
	  var groupsComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

    
      //fetch permissions
	  function groups() {
    	  	log('Load group');
  			showLoader();
  			var url = "/admin/references/groups";

  			$.getJSON(url, function (data) {
  				$('#groups').empty();
  				groups = data;
		        $.each(groups, function (i, group) {             
		        	 $('<div class="checkbox"><label><input type="checkbox" class="groups" name="groups[]" id="groups" value="' + group.name + '">' + group.name + '</label></div>').appendTo('#groups'); 
		        });
		        log('Load group success');
		        hideLoader();
		    })
	  }
