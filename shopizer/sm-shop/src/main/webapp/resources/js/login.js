    $(function() {
    	    	
    	$("#signinDrop").click(function(e){
    		log('Signin drop down');
    		$("#loginError").hide();
    		e.preventDefault();
    	});
    	
        $('.dropdown-menu').click(function(e) {
        	log('Drop down menu clicked');
        	e.preventDefault();
        	e.stopPropagation();
        });
        
        $('#registerLink').click(function(e) {
        	e.preventDefault();
        	e.stopPropagation();
        });
        
        $("#login-button").click(function(e) {
        	log('Login');
        	login();
        });

    });
    
    function login() {
        //$("#login").submit(function(e) {
        	//e.preventDefault();//do not submit form
        	log('Signin');
        	$("#loginError").hide();
        	
        	var userName = $('#signin_userName').val();
        	var password = $('#signin_password').val();
        	var storeCode = $('#signin_storeCode').val();
        	if(userName=='' || password=='') {
        		 $("#loginError").html(getLoginErrorLabel());
        		 $("#loginError").show();
        		 return;
        	}
        	
        	$('#signinPane').showLoading();
        	
        	log('Username ' + userName + ' password ' + password + ' storeCode ' + storeCode);

            $.ajax({
                 type: "POST",
                 //my version
                 url: getContextPath() + "/shop/customer/logon.html",
                 data: "userName=" + userName + "&password=" + password + "&storeCode=" + storeCode,
                 cache:false,
              	 dataType:'json',
                 'success': function(response) {
                    $('#signinPane').hideLoading();
					log(response);
                    if (response.response.status==0) {//success
                	   //SHOPPING_CART
                	   console.log(response.response.SHOPPING_CART);
                	   if(response.response.SHOPPING_CART!=null && response.response.SHOPPING_CART != ""){
       					  //console.log('saving cart ' + response.response.SHOPPING_CART);
                		  /** save cart in cookie **/
       					  var cartCode = buildCartCode(response.response.SHOPPING_CART);
       					  $.cookie('cart',cartCode, { expires: 1024, path:'/' });
          			      
                	   }
                	   //console.log('href -> ' + $(location).attr('href'));
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