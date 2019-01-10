

window.Parsley
  .addValidator('uniquecode', {
    validateString: function(value, uniqueCodeUrl) {
	        var xhr = $.ajax({
	            url: BACKEND + uniqueCodeUrl + "/unique?code=" + value,
	            headers: {"Authorization": "Bearer " + TOKEN}
	        });
	        return xhrResponse =  xhr.then(function(data) {
	            if(!data.exists)
	                return true;
	            return $.Deferred().reject(UNIQUEMESSAGE);
	         });
    }
});