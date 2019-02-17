
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
      $('#loader').removeClass('fadeOut');
   }; 
   function hideLoader() {
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
   
   function populateLanguagesCache(code) {
			showLoader();
  			var url = "/admin/references/languages?lang=" + code;
		    
  			var langs;
  			$.getJSON(url, function (langs) {
		        $.each(langs, function (i, langs) { 
		        	var key = 'language_' + code + '_' + langs.code;
		        	localStorage.setItem(key,langs.name);
		        });

		        hideLoader();
		    })
   }
   
   function languageLabel(lang, langCode) {
	    var key = 'language_' + lang + '_' + langCode;
	    var langCache = localStorage.getItem(key);
	    if(!langCache) {
	    	populateLanguagesCache(LANGUAGE);   	  
	    } 
	    var returnLang = langCode
	    if(langCache) {
	    	returnLang = langCache;
	    } 
	    return returnLang;
   }
