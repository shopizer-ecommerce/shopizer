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

function loadProducts(url,divProductsContainer) {
	$(divProductsContainer).showLoading();

	$.ajax({
			type: 'POST',
			dataType: "json",
			url: url,
			success: function(productList) {

				buildProductsList(productList,divProductsContainer);
				callBackLoadProducts(productList);


			},
			error: function(jqXHR,textStatus,errorThrown) { 
				$(divProductsContainer).hideLoading();
				alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
			
	});
	
	
	
}


function searchProducts(url,divProductsContainer,q,filter) {
	
	log(q);
	
	
	
	if(q==null || q=='') {
		return;
	}

    //category facets
	var facets = '\"facets\" : { \"categories\" : { \"terms\" : {\"field\" : \"categories\"}}}';
    var highlights = null;
	var queryStart = '{';

	//curl -XGET 'http://localhost:9200/product_en_default/_search' -d '{"query":{"multi_match":{"query": "buddha","fields": [ "name^3", "description", "tags" ]}},"facets" : { "categories" : { "terms" : {"field" : "categories"}}}}'
	
	//var query = '\"query\":{\"query_string\" : {\"fields\" : [\"name^3\", \"description\", \"tags\"], \"query\" : \"*' + q + '*", \"use_dis_max\" : true }}';
	var query = '\"query\":{\"multi_match\" : {\"fields\" : [\"name^3\", \"description\", \"tags\"], \"query\" : \"*' + q + '*"}}';
	if(filter!=null && filter!='') {
		//query = '\"query\":{\"filtered\":{\"query\":{\"text\":{\"_all\":\"' + q + '\"}},' + filter + '}}';
		query = query + ',' + filter + '}}';
	}

	if(facets!=null && facets!='') {
		query = query + ',' + facets;
	}

	var queryEnd = '}';
	
	query = queryStart + query + queryEnd;

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

