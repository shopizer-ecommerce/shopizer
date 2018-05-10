
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
	  $.LoadingOverlay("show");
      return this;
   }; 
   function hideLoader() {
	   $.LoadingOverlay("hide");
	   return this;
   }; 
