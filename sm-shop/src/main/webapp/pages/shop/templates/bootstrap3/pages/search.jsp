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
 

 
 <script>
 
 var START_COUNT_PRODUCTS = 0;
 var MAX_PRODUCTS = 3;
 

 $(function(){
	 	 
	 search();

 });
 
 
	<jsp:include page="/pages/shop/templates/bootstrap3/sections/shop-listing.jsp" />
	 
 
 	function search() {
 		$('#productsContainer').showLoading();
 		var url = '<%=request.getContextPath()%>/services/public/search/<c:out value="${requestScope.MERCHANT_STORE.code}"/>/<c:out value="${requestScope.LANGUAGE.code}"/>/' + START_COUNT_PRODUCTS + '/' + MAX_PRODUCTS + '/search.json';
	 	searchProducts(url,'#productsContainer','<c:out value="${q}"/>',null);
 	}
 
	function callBackSearchProducts(productList) {
			buildProductsList(productList,'#productsContainer', null);//generic list function
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
								<div class="col-sm-6 col-md-6">
									<div id="products-qty"></div>
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