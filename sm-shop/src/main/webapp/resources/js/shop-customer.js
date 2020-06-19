 function isCustomerFormValid(formId) {
		var $inputs = $(formId).find(':input');
		var errorMessage = null;
		$inputs.each(function() {
			if($(this).hasClass('required')) {
				
				//console.log($(this).attr('id') + ' - ' + $(this).css('display'));

				var fieldValid = isFieldValid($(this));
				if(!fieldValid) {
					if(errorMessage==null) {
						if($(this).attr('title')) {
							errorMessage = $(this).attr('title');
						}
					}
				}
			}
			
			//if has class email
			if($(this).hasClass('email')) {	
				var emailValid = validateEmail($(this).val());
				//console.log('Email is valid ? ' + emailValid);
				if(!emailValid) {
					if(errorMessage==null) {
						errorMessage = getInvalidEmailMessage();
					}
				}
			}
			
			//user name
			if($(this).hasClass('userName')) {	
				if($(this).val().length<6) {
					if(errorMessage==null) {
						errorMessage = getInvalidUserNameMessage();
					}
				}
			}
			
			//password rules
			if($(this).hasClass('password')) {	
				//console.log('check password ' + $(this).val().length);
				if($(this).val().length<6) {
					if(errorMessage==null) {
						errorMessage = getInvalidPasswordMessage();
					}
				}
			}
			
			//repeat password
			if($(this).hasClass('checkPassword')) {	
				    //console.log('In check p[assword ' + + $(this).val().length)
					var pass = $('.password').val();
					if($(this).val().length<6 || ($(this).val()!=pass)) {
						if(errorMessage==null) {
							errorMessage = getInvalidCheckPasswordMessage();
						}
					}
			}
		});
		
		return errorMessage;
 }
 
 
 function isFieldValid(field) {
		if(field.is(":hidden")) {
			return true;
		}
		var value = field.val();
		if(!emptyString(value)) {
			field.css('background-color', '#FFF');
			return true;
		} else {
			field.css('background-color', '#FFC');
			return false;
		} 
}
 
 function setCountrySettings(prefix, countryCode) {


		
}