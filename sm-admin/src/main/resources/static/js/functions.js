
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
   
   function changeMenuUrlParam(className, paramName, paramValue)
   {

	   $(className).each(function() {
		   var url = $(this).attr('href');
	       if (url.indexOf(paramName + "=") >= 0)
	       {
	           var prefix = url.substring(0, url.indexOf(paramName));
	           var suffix = url.substring(url.indexOf(paramName));
	           suffix = suffix.substring(suffix.indexOf("=") + 1);
	           suffix = (suffix.indexOf("&") >= 0) ? suffix.substring(suffix.indexOf("&")) : "";
	           url = prefix + paramName + "=" + paramValue + suffix;
	       }
	       else
	       {
	       if (url.indexOf("?") < 0)
	           url += "?" + paramName + "=" + paramValue;
	       else
	           url += "&" + paramName + "=" + paramValue;
	       }
	       $(this).attr('href',url);
	   })
   }
