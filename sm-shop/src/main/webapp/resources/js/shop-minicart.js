
 

	$(function(){
		initBindings();
		initMiniCart();

	});
	
	function initMiniCart() {
		var cartCode = getCartCode();
		//log('Check for cart code ' + cartCode);
		if(cartCode!=null) {
			miniCart(cartCode);
		} else {
			//display empty cart
			var emptyCart = emptyCartObject();
			miniCartSummary(emptyCart);//mini cart summary empty
			fullCart(emptyCart);//mini cart empty dropdown
		}
		
	}
	
	function removeCart() {
		
		var cartCode = getCartCode();
		if(cartCode!=null) {
			$.cookie('cart',null, { expires: 1, path:'/' });
		}
		
	}
	
	function initBindings() {
				
		/** add to cart **/
		$(".addToCart").click(function(){
			addToCart($(this).attr("productId"));
	    });
		
    	$("#open-cart").click(function(e) {
    		//nothing required
    	});
		
	}
	
	/**
	 * Function used for adding a product to the Shopping Cart
	 */
	function addToCart(sku) {
		showSMLoading('#pageContainer');
		var qty = '#qty-productId-'+ sku;
		var quantity = $(qty).val();
		if(!quantity || quantity==null || quantity==0) {
			quantity = 1;
		}

		var formId = '#input-' + sku;
		//var $inputs = $(formId); 
		var $inputs = $(formId).find(':input');
		
		var values = new Array();
		if($inputs.length>0) {//check for attributes
			i = 0;
			$inputs.each(function() { //attributes
				if($(this).hasClass('attribute')) {
				    //if($(this).hasClass('required') && !$(this).is(':checked')) {
					//   		$(this).parent().css('border', '1px solid red'); 
				    //}
			        if($(this).is(':checkbox')) {
			        	var checkboxSelected = $(this).is(':checked');
			        	if(checkboxSelected==true) {
							values[i] = $(this).val();
							//console.log('checkbox ' + values[i]);
							i++;
						}
			        	
					} else if ($(this).is(':radio')) {
						var radioChecked = $(this).is(':checked');
						if(radioChecked==true) {
							values[i] = $(this).val(); 
							//console.log('radio ' + values[i]);
							i++;
						}
					} else {
					   if($(this).val()) {
					       values[i] = $(this).val(); 
					       //console.log('select ' + values[i]);
					       i++;
				       }
					}
				}
			});
		}

		var cartCode = getCartCode();

		
		/**
		 * shopping cart code identifier is <cart>_<storeId>
		 * need to check if the cookie is for the appropriate store
		 */
		
		//cart item
		var prefix = "{";
		var suffix = "}";
		var shoppingCartItem = '';

		if(cartCode!=null && cartCode != '') {
			shoppingCartItem = '"code":' + '"' + cartCode + '"'+',';
		}
		var shoppingCartItem = shoppingCartItem + '"quantity":' + quantity + ',';
		var shoppingCartItem = shoppingCartItem + '"productId":' + sku;
		
		
		var attributes = null;
		//cart attributes
		if(values.length>0) {
			attributes = '[';
			for (var i = 0; i < values.length; i++) {
				var shoppingAttribute= prefix + '"attributeId":' + values[i] + suffix ;
				if(values.length>1 && i < values.length-1){
					shoppingAttribute = shoppingAttribute + ',';
				}
				attributes = attributes + shoppingAttribute;
			}
			attributes = attributes + ']';
		}
		
		if(attributes!=null) {
			shoppingCartItem = shoppingCartItem + ',"shoppingCartAttributes":' + attributes;
		}
		
		var scItem = prefix + shoppingCartItem + suffix;

		/** debug add to cart **/
		//console.log(scItem);

		
		$.ajax({  
			 type: 'POST',  
			 url: getContextPath() + '/shop/cart/addShoppingCartItem',  
			 data: scItem, 
			 contentType: 'application/json;charset=utf-8',
			 dataType: 'json', 
			 cache:false,
			 error: function(e) { 
				log('Error while adding to cart');
				//$('#pageContainer').hideLoading();
				hideSMLoading('#pageContainer');
				 
			 },
			 success: function(cart) {

			     saveCart(cart.code);
			     
			     if(cart.message!=null) { 
			    	 //TODO error message
			    	 log('Error while adding to cart ' + cart.message);
			     }
				 
			     cleanupMiniCart();
			     miniCartSummary(cart);
			     fullCart(cart);

				 //$('#pageContainer').hideLoading();
				 hideSMLoading('#pageContainer');
			 } 
		});
		
	}
	
function removeLineItem(lineItemId){
	$( "#shoppingCartRemoveLineitem_"+lineItemId).submit();		
}

function updateLineItem(lineItemId,actionURL){
	$("#shoppingCartLineitem_"+lineItemId).attr('action', actionURL);
	$( "#shoppingCartLineitem_"+lineItemId).submit();	
}

//update full cart
function updateCart(cartDiv) {
	$('.alert-error').hide();
	$('.quantity').removeClass('required');
	//$('#mainCartTable').showLoading();
	hideSMLoading('#mainCartTable');
	var inputs = $(cartDiv).find('.quantity');
	var cartCode = getCartCode();
	if(inputs !=null && cartCode!=null) {
		var items = new Array();
		for(var i = 0; i< inputs.length; i++) {
			var item = new Object();
			var qty = inputs[i].value;
			if(qty =='' || qty<1) {
				$('#' + inputs[i].id).addClass('required');
				$('#mainCartTable').hideLoading();
				return;
			}
			var id = inputs[i].id;

			item.id = id;
			item.quantity = qty;
			item.code=cartCode;
			items[i] = item;
		}
		//update cart
		json_data = JSON.stringify(items);

		$.ajax({  
			 type: 'POST',  
			 url: getContextPath() + '/shop/cart/updateShoppingCartItem.html',
			 data: json_data,
			 contentType: 'application/json;charset=utf-8',
			 dataType: 'json', 
			 cache:false,
			 error: function(e) { 
				 console.log('error ' + e);
				 //$('#mainCartTable').hideLoading();
				 hideSMLoading('#mainCartTable');
			 },
			 success: function(response) {
				 //$('#mainCartTable').hideLoading();
				 hideSMLoading('#mainCartTable');
				 if(response.response.status==-1) {
					 $('.alert-error').show();
				 } else {
					 location.href= getContextPath() + '/shop/cart/shoppingCart.html';
				 }
			} 
		});
		
	}	
}

function miniCart(cartCode){

	log('Display cart content');


	$.ajax({  
		 type: 'GET',  
		 url: getContextPath() + '/shop/cart/displayMiniCartByCode?shoppingCartCode='+cartCode,  
		 cache:false,
		 error: function(e) { 
			 console.log('error ' + e);
			 //nothing
			 
		 },
		 success: function(miniCart) {
			 log('Success ' + miniCart.code);

			    cleanupMiniCart();
			 	miniCartSummary(miniCart);
			 	fullCart(miniCart);


		} 
	});
}



 /**
  * JS function responsible for removing give line item from
  * the Cart.
  * For more details see MiniCartController.
  * 
  * Controller will return JSON as response and it will be parsed to update
  * mini-cart section.
  * @param lineItemId
  */
function removeItemFromMinicart(lineItemId){
	
	log('Removing ' + lineItemId);
	
	shoppingCartCode = getCartCode();
	$.ajax({  
		 type: 'GET',
		 cache:false,
		 url: getContextPath() + '/shop/cart/removeMiniShoppingCartItem?lineItemId='+lineItemId + '&shoppingCartCode=' + shoppingCartCode,  
		 error: function(e) { 
			 console.log('error ' + e);
			 
		 },
		 success: function(miniCart) {
			    log('Success remove item ' + miniCart);
			 	miniCartSummary(miniCart);
			    fullCart(miniCart);

		} 
	});
}

function displayMiniCartSummary(code){
	$.ajax({  
		 type: 'GET',  
		 url: getContextPath() + '/shop/cart/displayMiniCartByCode?shoppingCartCode='+code,  
		 error: function(e) { 
			// do nothing
			console('error while getting cart');
			 
		 },
		 success: function(cart) {
			 if(cart==null || cart=='') {
					emptyCartLabel();
					$.cookie('cart',null, { expires: 1, path:'/' });
			 } else {
				 displayTotals(cart);
			 }
		} 
	});
}

function cleanupMiniCart() {
	 $('#miniCartDetails').html('');
}

function miniCartSummary(cart) {
	
	var miniCartSummaryTemplate = Hogan.compile(document.getElementById("miniCartSummaryTemplate").innerHTML);
    var miniCartSummaryRendered = miniCartSummaryTemplate.render(cart);
    $('#miniCartSummary').html(miniCartSummaryRendered);
		
}

function fullCart(cart) {

    var miniCartTemplate = Hogan.compile(document.getElementById("miniCartTemplate").innerHTML);
    var miniCartRendered = miniCartTemplate.render(cart);
    //log(miniCartRendered);
    $('#miniCartDetails').html(miniCartRendered);
	
}





function viewShoppingCartPage(){
	window.location.href=getContextPath() + '/shop/cart/shoppingCart.html';
	
}


function emptyCartObject() {
	var miniCart = new Object();
	miniCart.code=null;
	return miniCart
}


/** returns the cart code **/
function getCartCode() {
	
	var cart = $.cookie('cart'); //should be [storecode_cartid]
	var code = new Array();
	
	if(cart!=null) {
		code = cart.split('_');
		if(code[0]==getMerchantStoreCode()) {
			return code[1];
		}
	}
}

function buildCartCode(code) {
	var cartCode = getMerchantStoreCode() + '_' + code;
	return cartCode;
}

function saveCart(code) {
	var cartCode = buildCartCode(code);
	$.cookie('cart',cartCode, { expires: 1024, path:'/' });
}


