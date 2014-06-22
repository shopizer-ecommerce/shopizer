    $(function() {
    	    	
    	$("#signinDrop").click(function(e){
    		$("#loginError").hide();
    		e.preventDefault();
    	});

        $("#login").submit(function(e) {
        	e.preventDefault();//do not submit form
        	
        	$("#loginError").hide();
        	
        	var userName = $(this).find('#userName').val();
        	var password = $(this).find('#password').val();
        	var storeCode = $(this).find('#storeCode').val();
        	if(userName=='' || password=='') {
        		 $("#loginError").html(getLoginErrorLabel());
        		 $("#loginError").show();
        		 return;
        	}
        	
        	$('#signinPane').showLoading();

            $.ajax({
                 type: "POST",
                 //my version
                 url: getContextPath() + "/shop/customer/logon.html",
                 data: "userName=" + userName + "&password=" + password + "&storeCode=" + storeCode,
                 cache:false,
              	 dataType:'json',
                 'success': function(response) {
                    $('#signinPane').hideLoading();
					//console.log(response);
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
        });
    });