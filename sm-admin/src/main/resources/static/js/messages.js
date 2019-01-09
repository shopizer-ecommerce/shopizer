

    
      //display success message
	  function success(message) {
		  
		  $(".successMessage").html(message);
		  $(".successCallout").show();
    	  

	  }
	  
	  //display error message
	  function error(message) {
    	  
		  $(".errorMessage").html(message);
		  $(".errorCallout").show();

	  }
	  
	  function resetMessages() {
		  $(".errorCallout").hide();
		  $(".successCallout").hide();
		  //$(".callout-warning").hide();
	  }
