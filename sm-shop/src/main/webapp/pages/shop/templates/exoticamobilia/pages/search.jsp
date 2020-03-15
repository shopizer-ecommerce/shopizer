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
</script>
 

 
 <script>
 
 var START_COUNT_PRODUCTS = 0;
 var MAX_PRODUCTS = 18;
 

 $(function(){
	 	 
	 search();

 });
 

 
 	function search() {
 		//Invoke search service
 		$('#productsContainer').showLoading();
 		var url = '<%=request.getContextPath()%>/services/public/search/<c:out value="${requestScope.MERCHANT_STORE.code}"/>/<c:out value="${requestScope.LANGUAGE.code}"/>/' + START_COUNT_PRODUCTS + '/' + MAX_PRODUCTS + '/search.json';
	 	searchProducts(url,'#productsContainer','<c:out value="${q}"/>',null);
 	}
 	
 	//inviked from callback below
 	function buildProductsList(productList) {
 		log('Products-> ' + productList.products.length);
		var productsTemplate = Hogan.compile(document.getElementById("productBoxTemplate").innerHTML);
		var productsRendred = productsTemplate.render(productList);
		$('#productsContainer').append(productsRendred);
		//$('#hiddenProductsContainer').append(productsRendred);//used for filtering products but no filter in search
		initBindings();//add to cart etc...
 	}
 
	//once the list of product is retrieved
 	function callBackSearchProducts(productList) {
 			buildProductsList(productList);
			totalCount = productList.productCount;
			START_COUNT_PRODUCTS = START_COUNT_PRODUCTS + MAX_PRODUCTS;
			if(START_COUNT_PRODUCTS < totalCount) {
					$("#button_nav").show();
			} else {
					$("#button_nav").hide();
			}
			
			$('#productsContainer').hideLoading();
			
			
			var productQty = productList.productCount + ' <s:message code="label.search.items.found" text="item(s) found" />';
			$('#products-qty').html(productQty);
			
			//facets
			if(productList.categoryFacets!=null && productList.categoryFacets.length>0) {	
				$('#categoryLabel').show();
				for (var i = 0; i < productList.categoryFacets.length; i++) {
					var categoryFacets = '<li>';
					categoryFacets = categoryFacets + '<a href="<c:url value="/shop"/>/category/' + productList.categoryFacets[i].description.friendlyUrl + '.html">' + productList.categoryFacets[i].description.name;
					if(productList.categoryFacets[i].productCount>0) {
					   categoryFacets = categoryFacets + '&nbsp;<span class="countItems">(' + productList.categoryFacets[i].productCount + ')</span>'
					}
					categoryFacets = categoryFacets + '</a>';
					categoryFacets = categoryFacets + '</li>';
					$(categoriesFacets).append(categoryFacets);
				}
			} else {
				$('#categoryLabel').hide();
			}
			


	}
 
 
 
 
</script>


	<div id="mainContent" class="container">
	
		<div id="shop" class="row">


        
        			<div class="col-md-9">

							<div class="row top-shop-option">
								<div class="col-sm-9 col-md-9">
									<strong><div id="products-qty"></div></strong>
								</div>
							</div>

						
							<div class="row product-list">
							
							
							<!-- just copy that block for having products displayed -->
							<!-- products are loaded by ajax -->
        					<ul id="productsContainer" class="list-unstyled"></ul>
			
							<nav id="button_nav" style="text-align:center;display:none;">
								<button id="moreProductsButton" class="btn btn-large" style="width:400px;" onClick="loadCategoryProducts();"><s:message code="label.product.moreitems" text="Display more items" />...</button>
							</nav>
							<span id="end_nav" style="display:none;"><s:message code="label.product.nomoreitems" text="No more items to be displayed" /></span>
          					<!-- end block -->

							</div>

					</div><!-- /col-md-9 -->
        
        			<sidebar class="col-md-3">
        						<h3 id="categoryLabel"><s:message code="label.categories.title" text="Categories"/></h3>
        			            <ul id="categoriesFacets" class="nav nav-list"></ul>
        			</sidebar>
        

        
        </div><!-- row -->
        
      </div><!-- container -->