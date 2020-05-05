var trace = 1;


function validateEmail($email) {
	  var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	  if ( $email.length > 0 && emailReg.test($email)) {
	    return true;
	  } else {
	    return false;
	  }
}

function emptyString($value) {
	return !$value || !/[^\s]+/.test($value);
}

function log(value) {
	if(trace==1) {
		console.log(value);
	}
}

function showSMLoading(element) {
	if ( typeof showTemplateLoading == 'function' ) { 
		showTemplateLoading(element);
	} else {
		$(element).showLoading();
	}
	
}

function hideSMLoading(element) {
	if ( typeof hideTemplateLoading == 'function' ) {
		hideTemplateLoading(element);
	} else {
		$(element).hideLoading();
	}
}

function loadProducts(url,divProductsContainer) {
	showSMLoading(divProductsContainer);
	$.ajax({
			type: 'POST',
			dataType: "json",
			url: url,
			success: function(productList) {

				buildProductsList(productList,divProductsContainer);
				callBackLoadProducts(productList);


			},
			error: function(jqXHR,textStatus,errorThrown) { 
				hideSMLoading(divProductsContainer);
				alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
			
	});
	
	
	
}



function searchProducts(url,divProductsContainer,q,filter) {

	if(q==null || q=='') {
		return;
	}

	var query = '{"query":"'+ q + '","start":0, "count":25}';


	$.ajax({
			cache: false,
  			type:"POST",
  			dataType:"json",
  			url:'/api/v1/search',
  			data:query,
  			contentType:"application/json;charset=UTF-8",
			success: function(productList) {
				callBackSearchProducts(productList);
			},
			error: function(jqXHR,textStatus,errorThrown) { 
				$(divProductsContainer).hideLoading();
				log('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
			
	});
	
	
	
}

