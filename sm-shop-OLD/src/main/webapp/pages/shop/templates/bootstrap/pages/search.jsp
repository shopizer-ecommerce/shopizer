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
 var MAX_PRODUCTS = 12;
 

 $(function(){
	 	 
	 search();

 });
 
 
	<jsp:include page="/pages/shop/templates/bootstrap/sections/shop-listing.jsp" />
	 
 
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
			
			//facets
			if(productList.categoryFacets!=null) {
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
			}
			
			$('#productsContainer').hideLoading();
			
			
			var productQty = productList.productCount + ' <s:message code="label.search.items.found" text="item(s) found" />';
			$('#products-qty').html(productQty);

	}
 
 
 
 
</script>


	<div class="row-fluid">
	
	
	   <div class="span12">
      	
      	<!-- left column -->
      	<!--Search facets-->
        <div class="span3">
          <div class="sidebar-nav">
            <ul id="categoriesFacets" class="nav nav-list">
            </ul>
          </div>
        </div><!--/span-->
        
        <!-- right column -->
        <div class="span9">

        <div class="products-title row-fluid">
    		<div class="span6">
        		<p><div id="products-qty"></div></p>
    		</div>
		    <div class="span6">
		        <div class="pull-right">
		            <p>
		            <ul class="nav nav-list">
	            		<li class="widget-header"><s:message code="label.generic.sortby" text="Sort by" />:
						<select id="filter">
							<option value="item-order"><s:message code="label.generic.default" text="Default" /></option>
							<option value="item-name"><s:message code="label.generic.name" text="Name" /></option>
							<option value="item-price"><s:message code="label.generic.price" text="Price" /></option>
						</select>
						</li>
					</ul>
		            </p>
		        </div>
		    </div>
		 </div>

          
        	<ul id="productsContainer" class="thumbnails product-list">
				<!-- search ajax -->
			</ul>
			<nav id="button_nav" style="text-align:center;display:none;">
				<button class="btn btn-large" style="width:400px;" onClick="loadProducts();"><s:message code="label.product.moreitems" text="Display more items" />...</button>
			</nav>
			<span id="end_nav" style="display:none;"><s:message code="label.product.nomoreitems" text="No more items to be displayed" /></span>
          
          
        </div><!--/span-->
        
        </div><!-- 12 -->
        
      </div><!-- row fluid -->