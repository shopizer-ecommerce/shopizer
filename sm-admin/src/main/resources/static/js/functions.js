
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
   
   //display phone number mask
   function maskPhone(countryCode, fieldInstance) {
       if(countryCode==='CA' || countryCode==='USD') {
    	   fieldInstance.attr('data-inputmask', '"mask": "(999) 999-9999"');
       } else {
    	   fieldInstance.attr('data-inputmask', "'mask': ['999-999-9999 [x99999]', '+099 99 99 9999[9]-9999']");
       }
       $('[data-mask]').inputmask();
   }
