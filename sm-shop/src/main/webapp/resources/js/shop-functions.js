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
	if ($.isFunction(showTemplateLoading)) {
		showTemplateLoading(element);
	} else {
		$(element).showLoading();
	}
	
}

function hideSMLoading(element) {
	if ($.isFunction(hideTemplateLoading)) {
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
	
	//log(q);

	if(q==null || q=='') {
		return;
	}

    //category aggregations
	var aggregations = '\"aggregations\" : { \"categories\" : { \"terms\" : {\"field\" : \"categories\"}}}';
    var highlights = null;
	var queryStart = '{';

	var query = '\"query\":{\"query_string\" : {\"fields\" : [\"name^3\", \"description\", \"tags\"], \"query\" : \"' + q + '", \"use_dis_max\" : true }}';
	if(filter!=null && filter!='') {
		query = query + ',' + filter + '}}';
	}

	if(aggregations!=null && aggregations!='') {
		query = query + ',' + aggregations;
	}

	var queryEnd = '}';
	
	query = queryStart + query + queryEnd;
	
	log(query);

	$.ajax({
			cache: false,
  			type:"POST",
  			dataType:"json",
  			url:url,
  			data:query,
  			contentType:"application/json;charset=UTF-8",
			success: function(productList) {
				callBackSearchProducts(productList);
			},
			error: function(jqXHR,textStatus,errorThrown) { 
				$(divProductsContainer).hideLoading();
				alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
			
	});
	
	
	
}

