
 

	$(function(){
		log('Check for customer account');
		if(supportsCustomerLogin()) {
			if($('#customerNotLoggedInAccountTemplate')) {
				var customerNotLoggedInTemplate = Hogan.compile(document.getElementById("customerNotLoggedInAccountTemplate").innerHTML);
				var customerNotLoggedInRendered = customerNotLoggedInTemplate.render('');
				$('#customerAccount').html('');
				$('#customerAccount').append(customerNotLoggedInRendered);
			}
			initUserAccount();
		}

	});
	
	function initUserAccount() {
		var userName = getUserName();
		log('userName ' + userName);
		if(userName!=null) {
			displayUserAccount(userName);
		}
	}
	



function displayUserAccount(userName){
	url = getContextPath() + '/shop/customer/accountSummary.json?userName='+userName;
	$.ajax({  
		 type: 'GET',  
		 url: url,  
		 error: function(xhr) { 
			if(xhr.status==401) {//not authenticated
				removeUserName();
			}
			 
		 },
		 success: function(customer) {
			 log('From account summary');
			 if(customer!=null) {
				 //display user
				 //alert("Supports customer loggin " + supportsCustomerLogin());
				 if($('#customerLoggedInAccountTemplate')) {
					    $('#customerAccount').html('');
						var customerLoggedInTemplate = Hogan.compile(document.getElementById("customerLoggedInAccountTemplate").innerHTML);
						var customerLoggedInRendered = customerLoggedInTemplate.render(customer);
						$('#customerAccount').append(customerLoggedInRendered);
				 }
			 }
		} 
	});
}



/** returns the user name from the cookie **/
function getUserName() {
	
	var user = $.cookie('user'); //should be [storecode_userName]
	var code = new Array();
	
	if(user!=null) {
		user = user.replace(/['"]+/g, '');
		code = user.split('_');
		if(code[0]==getMerchantStoreCode()) {
			return code[1];
		}
	}
}

/** removes username from cookie **/
function removeUserName() {
	log('Removing user cookie');
	var userName = getUserName();
	if(userName!=null) {
		$.cookie('user',null, { expires: 1, path:'/' });
	}
	
}


