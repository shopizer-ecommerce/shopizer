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


 <!-- don't change that script except max_oroducts -->
 <script>
 
 var START_COUNT_PRODUCTS = 0;
 var MAX_PRODUCTS = 500;
 var filter = null;
 var filterValue = null;

 $(function(){

	<c:if test="${not empty productGroup}">
	loadItemsProducts();
	</c:if>

 });

 
 	function loadItemsProducts() {
 		
 		//services/public/{store}/products/group/{code}
 		var url = '<%=request.getContextPath()%>/services/public/<c:out value="${requestScope.MERCHANT_STORE.code}"/>/products/group/<c:out value="${productGroup}"/>';
 		loadProducts(url,'#productsContainer');

 	}

 	
 	function buildProductsList(productList, divProductsContainer) {
 		hideSMLoading('#productsContainer');
 		log('Products-> ' + productList.products.length);
		var productsTemplate = Hogan.compile(document.getElementById("productBoxTemplate").innerHTML);
		var productsRendred = productsTemplate.render(productList);
		$('#productsContainer').append(productsRendred);
		initBindings();
 	}
 
	function callBackLoadProducts(productList) {
		    hideSMLoading('#productsContainer');
			var productQty = productList.productCount + ' <s:message code="label.search.items.found" text="item(s) found" />';
			$('#products-qty').html(productQty);
			

	}

</script>


            <div class="container">
            	<div class="row">
						<div id="shop" class="col-md-12">
							<c:out value="${content.description}" escapeXml="false"/>
						</div>
				</div>
				<c:if test="${productGroup!=null}">
						<div class="col-md-12">
							<div class="product-list">
							
							
							<!-- just copy that block for havimg products displayed -->
							<!-- products are loaded by ajax -->
        					<div id="productsContainer" class="list-unstyled"></div>

          					<!-- end block -->

							</div>

						</div>
			    </c:if>
			</div>