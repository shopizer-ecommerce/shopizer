<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 
 <script src="<c:url value="/resources/js/jquery.easing.1.3.js" />"></script>
 <script src="<c:url value="/resources/js/jquery.quicksand.js" />"></script>
 <script src="<c:url value="/resources/js/jquery-sort-filter-plugin.js" />"></script>
 <script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
 
 
 <script type="text/html" id="productBoxTemplate">
{{#products}}
<div itemscope itemtype="http://schema.org/Enumeration" class="col-md-4 productItem" item-order="{{sortOrder}}" item-name="{{description.name}}" item-price="{{price}}" data-id="{{id}}" class="col-sm-4">
<div class="box-style-4 white-bg object-non-visible animated object-visible">
 	{{#description.highlights}}  
    <div class="ribbon-wrapper-green">
   		<div class="ribbon-green">
   			{{description.highlights}} 
   		</div>
   	</div>
    {{/description.highlights}}
	{{^canBePurchased}}
		<div class="sold-out-box">
	    			<span class="sold-out-text"><s:message code="label.soldout" text="Sold out" /></span>
	  	</div> 
	{{/canBePurchased}}
	<div class="product-image">
    {{#image}}                              
	<img class="product-img" src="<c:url value=""/>{{image.imageUrl}}"><a class="overlay" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html<sm:breadcrumbParam/>"><img class="product-img" src="<c:url value="/"/>{{image.imageUrl}}"></a>
    {{/image}}
    </div>
	<!--  *** Product Name & Price Starts *** -->
	<div class="caption">
	<div class="product-details">
	<div class="clearfix">
		<h3 class="product-heading product-name" itemprop="name">{{description.name}}</h3>
		<h4 class="price">
			{{#discounted}}<del>{{originalPrice}}</del>&nbsp;<span itemprop="price" class="specialPrice">{{finalPrice}}</span>{{/discounted}}
			{{^discounted}}<span itemprop="price" class="specialPrice">{{finalPrice}}</span>{{/discounted}}
		</h4>
		<!-- Product Name & Price Ends -->
		<!-- Product Buttons Starts -->
		<div class="clearfix">
			<a class="btn btn-default pull-left" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html<sm:breadcrumbParam/>" class="details"><s:message code="button.label.view" text="Details" /></a>
		<c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">
		{{#canBePurchased}}<a class="btn btn-buy pull-right addToCart" href="javascript:void(0);" class="addToCart" productId="{{id}}"><s:message code="button.label.addToCart" text="Add to cart" /></a>{{/canBePurchased}}
		</c:if>
		</div>
	</div>
	</div>
	</div>
</div>
</div>
{{/products}}
</script>


 <!-- don't change that script except max_oroducts -->
 <script>
 
 var START_COUNT_PRODUCTS = 0;
 var MAX_PRODUCTS = 30;
 var filter = null;
 var filterValue = null;

 $(function(){
	 
    //price minimum/maximum
	$('.numeric').numeric();
    
    
	$('#filter').on('change', function() {
		visualize();
	});
	
	$('#priceFilterMinimum').on('blur', function() {
		visualize();
	});
	
	$('#priceFilterMaximum').on('blur', function() {
		visualize()	
	});
	
	 
	loadCategoryProducts();

 });
 
 
 	function visualize() {
 		var orderBy = $("#filter").val();
		var minimumPrice = $('#priceFilterMinimum').val();
		var maximumPrice = $('#priceFilterMaximum').val();
		
		//orderProducts(orderBy);
		orderProducts(orderBy, minimumPrice, maximumPrice);
 	}
 
	/** used for ordering and filtering **/
	//function orderProducts(attribute, minimum, maximum) {
	function orderProducts(attribute, minimumPrice, maximumPrice) {
		
		  if(minimumPrice==undefined) {
			  minimumPrice = '';
		  }
		  
		  if(maximumPrice==undefined) {
			  maximumPrice = ''; 
		  }
		
		  //log('Attribute ' + attribute + ' Minimum price ' + minimumPrice + ' Maximum price ' + maximumPrice);
		
		  if(minimumPrice == '' && maximumPrice == '') {
		  
			  if(attribute=='item-order') {	  
				  return;
			  }
		  }
		
		  // get the first collection
		  var $prods = $('#productsContainer');
		  

		  // clone applications to get a second collection
		  data = $('#hiddenProductsContainer').clone();
		  
		  //console.log('Data');
		  //console.log(data);
		  
		  
		  listedData = data.find('.productItem');
		  
		  //console.log('Listed Data');
		  //console.log(listedData);

		  filteredData = listedData;
		  var $sortedData = null;
	      
		  if(minimumPrice != '' || maximumPrice != '') {
			  //filter filteredData
			  if(minimumPrice == '') {
				  minimumPrice = '0';
			  }
			  filteredData = listedData.filter(function() {
				 
				   //log('Item price ' + $(this).attr('item-price'));
			  
				   var price = parseFloat($(this).attr('item-price'));
				   if(maximumPrice != '') {
					   return price >= parseFloat(minimumPrice) && price <= parseFloat(maximumPrice); 
				   } else {
					   return price >= parseFloat(minimumPrice);
				   }
				   
			  }); 
		  } 
		  
		  //console.log('After filtered Data');
		  //console.log(filteredData);

		  
		  if(attribute!='item-order') {	
		  
		  	$sortedData = filteredData.sorted({
		        by: function(v) {
		        	if(attribute=='item-price') {
		        		return parseFloat($(v).attr(attribute));
		        	} else {
		        		return $(v).attr(attribute);
		        	}
		        }
		 	 });
		  
		  } else {
			  $sortedData =  filteredData; 
		  }

		  // finally, call quicksand
		  $prods.quicksand($sortedData, {
		      duration: 800,
		      easing: 'easeInOutQuad'
		  });
		
		
	}
 
 	function loadCategoryProducts() {
 		var url = '<%=request.getContextPath()%>/services/public/products/page/' + START_COUNT_PRODUCTS + '/' + MAX_PRODUCTS + '/<c:out value="${requestScope.MERCHANT_STORE.code}"/>/<c:out value="${requestScope.LANGUAGE.code}"/>/<c:out value="${category.description.friendlyUrl}"/>';
	 	
 		if(filter!=null) {
 			url = url + '/filter=' + filter + '/filter-value=' + filterValue +'';
 		}
 		loadProducts(url,'#productsContainer');

 	}
 	
 	
 	function filterCategory(filterType,filterVal) {
	 		//reset product section
	 		$('#productsContainer').html('');
	 		$('#hiddenProductsContainer').html('');
	 		START_COUNT_PRODUCTS = 0;
	 		filter = filterType;
	 		filterValue = filterVal;
	 		loadCategoryProducts();
 	}
 	
 	function buildProductsList(productList, divProductsContainer) {
 		log('Products-> ' + productList.products.length);
		var productsTemplate = Hogan.compile(document.getElementById("productBoxTemplate").innerHTML);
		var productsRendred = productsTemplate.render(productList);
		$('#productsContainer').append(productsRendred);
		$('#hiddenProductsContainer').append(productsRendred);
		initBindings();
 	}
 
	function callBackLoadProducts(productList) {
			totalCount = productList.productCount;
			START_COUNT_PRODUCTS = START_COUNT_PRODUCTS + MAX_PRODUCTS;
			if(START_COUNT_PRODUCTS < totalCount && START_COUNT_PRODUCTS <= productList.productCount) {
					$("#button_nav").show();
			} else {
					$("#button_nav").hide();
			}
			$('#productsContainer').hideLoading();

			visualize();
			
			var productQty = productList.productCount + ' <s:message code="label.search.items.found" text="item(s) found" />';
			$('#products-qty').html(productQty);


	}
	
 
 

</script>



<div id="mainContent" class="container">
			
			  <header class="page-header row">
			  <c:if test="${category.description.name!=null}">
			  <div class="fixed-image section dark-translucent-bg parallax-bg-3">
					<div class="container">
					<h2 class="shop-banner-title lead"><c:out value="${category.description.name}"/></h2>
					</div>
			  </div>
			  </c:if>
			  <jsp:include page="/pages/shop/templates/exoticamobilia/sections/breadcrumb.jsp" />
			  </header>

			  
			  <c:if test="${category.description.description!=null}">
			  <div class="row">
			  	<p class="lead"><c:out value="${category.description.description}" escapeXml="false"/></p>
			  </div>
			  </c:if>
			  


			   <div id="shop" class="row">
                  <div class="sorting-filters">
                     <form class="form-inline">
	                     <div class="form-group">
	                      <label><s:message code="label.generic.sortby" text="Sort by" />:</label>
										<select id="filter" class="form-control">
											<option value="item-order"><s:message code="label.generic.default" text="Default" /></option>
											<option value="item-name"><s:message code="label.generic.name" text="Name" /></option>
											<option value="item-price"><s:message code="label.generic.price" text="Price" /></option>
										</select>
						 </div>
						 <div class="form-group">
						    <label><s:message code="label.generic.price" text="Price" /> (<s:message code="label.entity.minimum" text="Minimum"/>/<s:message code="label.entity.maximum" text="Maximum"/>):</label>
						    <div class="row grid-space-10">
						 		<div class="col-sm-6">
						 		    <input id="priceFilterMinimum" name="priceFilterMinimum" class="form-control numeric filterByField" type="text">
						 		</div>
						 		<div class="col-sm-6">
						 		    <input id="priceFilterMaximum" name="priceFilterMaximum" class="form-control numeric filterByField" type="text">
						 		</div>
						    </div>
						 </div>
						 <div class="form-group">
						 	<div class="col-sm-6">
						 	</div>
						 </div>
                     </form>
                  </div>
						<div class="col-md-9">
						
							<div class="row product-list">
							
							
							<!-- just copy that block for havimg products displayed -->
							<!-- products are loaded by ajax -->
        					<div id="productsContainer" class="list-unstyled"></div>
			
							<nav id="button_nav" style="text-align:center;display:none;">
								<button id="moreProductsButton" class="btn btn-primary btn-large" style="width:400px;" onClick="loadCategoryProducts();"><s:message code="label.product.moreitems" text="Display more items" />...</button>
							</nav>
							<span id="end_nav" style="display:none;"><s:message code="label.product.nomoreitems" text="No more items to be displayed" /></span>
          					<!-- end block -->

							</div>
							
							<!-- hidden -->
							<div id="hiddenProductsContainer" style="display:none;"></div>

						</div><!-- /col-md-9 -->

						<sidebar class="col-md-3">
							<!-- categories -->
							<c:if test="${parent!=null}">
              					<h3><c:out value="${parent.description.name}" /></h3>
             				</c:if>
							<ul class="nav nav-list">
							<c:forEach items="${subCategories}" var="subCategory">
								<c:if test="${subCategory.visible}">
              					<li>
              					<a href="<c:url value="/shop/category/${subCategory.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${subCategory.id}"/>"><i class="fa fa-angle-right"></i> <c:out value="${subCategory.description.name}" />
              						<c:if test="${subCategory.productCount>0}">&nbsp;<span class="countItems">(<c:out value="${subCategory.productCount}" />)</span></c:if></a>
              					</li>
              					</c:if>
              				</c:forEach>
							</ul>
							<br/>
							<!-- manufacturers -->
							<c:if test="${fn:length(manufacturers) > 0}">
					          	<h3><s:message code="label.manufacturer.collection" text="Collection" /></h3>
					            <ul class="nav nav-list">
					              <li class="nav-header"></li>
					              <c:forEach items="${manufacturers}" var="manufacturer">
					              	<li>
					              		<a href="javascript:filterCategory('BRAND','${manufacturer.id}')"><i class="fa fa-angle-right"></i>&nbsp;<c:out value="${manufacturer.description.name}" /></a></li>
					              </c:forEach>
					            </ul>
					          </div>          
          					</c:if>



						</sidebar>



			</div>
		</div>