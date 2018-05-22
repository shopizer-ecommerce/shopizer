
	function initProduct(productId, div) {
		
		calculatePrice(productId, div);

		$(div + ' select').change(function(e) { // select element changed
			calculatePrice(productId, div);
		});

		$(div + ' :radio').change(function(e) { // radio changed
			calculatePrice(productId, div);
		});
		
		$(div + ' :checkbox').change(function(e) { // radio changed
			calculatePrice(productId, div);
		});
		
	}


    function calculatePrice(productId, div) {

		
		var values = new Array();
		i = 0;
		$(div).find(':input').each(function(){
			if($(this).hasClass('attribute')) {
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
		
	if(values.length==0) {
		return;
	}	
	
	$(div).showLoading();
		
	$.ajax({  
		 type: 'POST',  
		 url: getContextPath() + '/shop/product/' + productId + '/calculatePrice.json',  
		 dataType: 'json', 
		 data:{"attributeIds":values},
		 cache:false,
		 error: function(e) { 
			$(div).hideLoading();
			console.log('Error while loading price');
			 
		 },
		 success: function(price) {
			 $(div).hideLoading();
			 //console.log('product price ' + price.finalPrice);
			 var displayPrice = '<span itemprop="price">' + price.finalPrice + '</span>';
			 if(price.discounted) {
				 displayPrice = '<del>' + price.originalPrice + '</del>&nbsp;<span class="specialPrice"><span itemprop="price">' + price.finalPrice + '</span></span>';
			 }
			 $('#productPrice').html(displayPrice);
		 } 
	});

	
}