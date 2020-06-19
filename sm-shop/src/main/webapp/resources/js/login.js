    $(function() {
    	    	
    	$("#signinDrop").click(function(e){
    		//log('Signin drop down');
    		$("#loginError").hide();
    		e.preventDefault();
    	});
    	
        $('.dropdown-menu').click(function(e) {
        	//log('Drop down menu clicked');
        	//e.preventDefault();
        	//e.stopPropagation();
        });
        
        $('#registerLink').click(function(e) {
        	e.preventDefault();
        	e.stopPropagation();
        });
        
        $("#login-button").click(function(e) {
        	//log('Calling login');
        	e.preventDefault();
        	e.stopPropagation();
        	login();
        });
        
        $("#genericLogin-button").click(function(e) {
        	//log('Calling genericLogin');
        	e.preventDefault();
        	e.stopPropagation();
        	genericLogin();
        });

    });
    
    function login() {
        	$("#loginError").hide();
        	
        	var userName = $('#signin_userName').val();
        	var password = $('#signin_password').val();
        	var storeCode = $('#signin_storeCode').val();
        	if(userName=='' || password=='') {
        		 $("#loginError").html(getLoginErrorLabel());
        		 $("#loginError").show();
        		 return;
        	}
        	
        	//log('Before showLoading');
        	
        	//Need to have the logon table id signinPane
        	//showSMLoading('#pageContainer');
        	$('#signinPane').showLoading();
        	
        	//log('username ' + userName + ' password ' + password + ' storeCode ' + storeCode);

            $.ajax({
                 type: "POST",
                 //my version
                 url: getContextPath() + "/shop/customer/logon.html",
                 data: "userName=" + userName + "&password=" + password + "&storeCode=" + storeCode,
                 cache:false,
              	 dataType:'json',
                 'success': function(response) {
                     $('#signinPane').hideLoading();
                	 //hideSMLoading('#pageContainer');
					//log(response);
                    if (response.response.status==0) {//success
                	   //SHOPPING_CART
                	   //log(response.response.SHOPPING_CART);
                	   if(response.response.SHOPPING_CART!=null && response.response.SHOPPING_CART != ""){
       					  //log('saving cart ' + response.response.SHOPPING_CART);
                		  /** save cart in cookie **/
       					  var cartCode = buildCartCode(response.response.SHOPPING_CART);
       					  $.cookie('cart',cartCode, { expires: 1024, path:'/' });
          			      
                	   }
                	   //redirect to the same url
                	   log('Before redirection');
                	   location.href=  $(location).attr('href');
                    } else {
                        $("#loginError").html(getLoginErrorLabel());
                        $("#loginError").show();
                    }
                }
            });
            return false;
        //});
        }
    
    function genericLogin() {
    	
    	//error message
    	$("#loginError").remove();
    	var errMessageDiv = '<div id="loginError" class="alert alert-danger" role="alert">';

    	var userName = $('#signin_userName').val();
    	var password = $('#signin_password').val();
    	var storeCode = $('#signin_storeCode').val();
    	//log('username ' + userName + ' password ' + password + ' storeCode ' + storeCode);
    	if(userName=='' || password=='') {
    		 errorMessage = errMessageDiv + getLoginErrorLabel() + '</div>';
    		 $(errorMessage).prependTo('#login-form');
    		 return;
    	}

    	//Need to have the logon table id signinPane
    	showSMLoading('#pageContainer');

        $.ajax({
             type: "POST",
             //my version
             url: getContextPath() + "/shop/customer/logon.html",
             data: "userName=" + userName + "&password=" + password + "&storeCode=" + storeCode,
             cache:false,
          	 dataType:'json',
             'success': function(response) {
            	 hideSMLoading('#pageContainer');
				//log(response);
                if (response.response.status==0) {//success
            	   //SHOPPING_CART
            	   //log(response.response.SHOPPING_CART);
            	   if(response.response.SHOPPING_CART!=null && response.response.SHOPPING_CART != ""){
   					  //log('saving cart ' + response.response.SHOPPING_CART);
            		  /** save cart in cookie **/
   					  var cartCode = buildCartCode(response.response.SHOPPING_CART);
   					  $.cookie('cart',cartCode, { expires: 1024, path:'/' });
   					  //cookie requires to be saved again
      			      
            	   }
            	   //redirect to the same url
            	   //log('Before redirection');
            	   location.href= getContextPath() + '/shop/customer/dashboard.html';
                } else {
           		   errorMessage = errMessageDiv + getLoginErrorLabel() + '</div>';
        		   $(errorMessage).prependTo('#login-form');
                }
            }
        });
        return false;
    }