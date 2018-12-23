
	  var languagesComponent = {
			  BACKEND:'',  
			  LANGUAGE:'', 
			  MODE:''
	  }

    
      //fetch languages
	  function languages() {
    	  
  			showLoader();
  			var url = "/admin/references/languages?lang=" + languagesComponent.LANGUAGE;
		    
  			var langs;
  			$.getJSON(url, function (data) {
  				$('#languages').empty();
  				langs = data;
		        $.each(langs, function (i, langs) {             
		        	 $('<div class="checkbox"><label><input type="checkbox" name="supportedLanguages[]" id="supportedLanguages" value="' + langs.code + '">' + langs.name + '</label></div>').appendTo('#languages'); 
		        });
		        $.each(langs, function (i, langs) {             
		        	 $('<option value='+ langs.code +'>' + langs.name + '</option>').appendTo('#defaultLanguage'); 
		        });		        
		        hideLoader();
		    })
	  }
