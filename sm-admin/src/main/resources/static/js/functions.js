
   /**
    * Global functions
    * @returns
    */

	//enable tracing
	var trace = 1;

	function log(value) {
		if(trace==1) {
			console.log(value);
		}
	}

   function showLoader() {
	  //$.LoadingOverlay("show");
      //return this;
      $('#loader').removeClass('fadeOut');
   }; 
   function hideLoader() {
	  // $.LoadingOverlay("hide");
	  // return this;
	   $('#loader').addClass('fadeOut');
   }; 
   
   //displays success message on a given componentId
   function success(message, componentId) {
	   
   }
   
   function error(message, componentId) {
	   
   }
