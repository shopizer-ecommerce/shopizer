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


 <!-- don't change that script except max_oroducts -->
 <script>
 
 var START_COUNT_PRODUCTS = 0;
 var MAX_PRODUCTS = 12;
 var filter = null;
 var filterValue = null;

 $(function(){
	
	$('#filter').on('change', function() {
	    var orderBy = getOrderBy();
	    orderProducts(orderBy);
	});
	 
	loadCategoryProducts();

 });
 
 
 
	<jsp:include page="/pages/shop/templates/bootstrap3/sections/shop-listing.jsp" />

	function orderProducts(attribute) {
		
		
		  if(attribute=='item-order') {
			  return;
		  }
		
		  // get the first collection
		  var $prods = $('#productsContainer');

		  // clone applications to get a second collection
		  var $data = $prods.clone();
		  
		  var $filteredData = $data.find('.productItem');
	      var $sortedData = $filteredData.sorted({
		        by: function(v) {
		        	if(attribute=='item-price') {
		        		return parseFloat($(v).attr(attribute));
		        	} else {
		        		return $(v).attr(attribute);
		        	}
		        }
		  });

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
	 		START_COUNT_PRODUCTS = 0;
	 		filter = filterType;
	 		filterValue = filterVal;
	 		loadCategoryProducts();
 	}
 
	function callBackLoadProducts(productList) {
			totalCount = productList.productCount;
			START_COUNT_PRODUCTS = START_COUNT_PRODUCTS + MAX_PRODUCTS;
			if(START_COUNT_PRODUCTS < totalCount) {
					$("#button_nav").show();
			} else {
					$("#button_nav").hide();
			}
			$('#productsContainer').hideLoading();
			
			//check option
			var orderBy = getOrderBy();
			orderProducts(orderBy);
			
			var productQty = productList.productCount + ' <s:message code="label.search.items.found" text="item(s) found" />';
			$('#products-qty').html(productQty);

	}
	
	function getOrderBy() {
		var orderBy = $("#filter").val();
		return orderBy;
	}
 
 

</script>



<div id="mainContent" class="container">
			
				<jsp:include page="/pages/shop/templates/bootstrap3/sections/breadcrumb.jsp" />


			   <c:if test="${category.description.description!=null}">
			   		<!-- category description -->
			   		<header class="page-header row">
						<h1><c:out value="${category.description.description}"/></h1>
					</header>			   
			   </c:if>



			   <div id="shop" class="row">

						<div class="col-md-9">

							<div class="row top-shop-option">
								<div class="col-sm-6 col-md-6">
									<div id="products-qty"></div>
								</div>
								<div class=" col-sm-6 col-md-6">
									<s:message code="label.generic.sortby" text="Sort by" />:
									<select id="filter">
										<option value="item-order"><s:message code="label.generic.default" text="Default" /></option>
										<option value="item-name"><s:message code="label.generic.name" text="Name" /></option>
										<option value="item-price"><s:message code="label.generic.price" text="Price" /></option>
									</select>
								</div>
							</div>

						
							<div class="row product-list">
							
							
							<!-- just copy that block for havimg products displayed -->
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
							<!-- categories -->
							<c:if test="${parent!=null}">
              					<h3><c:out value="${parent.description.name}" /></h3>
             				</c:if>
							<ul class="nav nav-list">
							<c:forEach items="${subCategories}" var="subCategory">
              					<li>
              					<a href="<c:url value="/shop/category/${subCategory.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${subCategory.id}"/>"><i class="fa fa-angle-right"></i> <c:out value="${subCategory.description.name}" />
              						<c:if test="${subCategory.productCount>0}">&nbsp;<span class="countItems">(<c:out value="${subCategory.productCount}" />)</span></c:if></a>
              					</li>
              				</c:forEach>
							</ul>
							<br/>
							<!-- manufacturers -->
							<c:if test="${fn:length(manufacturers) > 0}">
					          	<h3><s:message code="label.manufacturer.brand" text="Brands" /></h3>
					            <ul class="nav nav-list">
					              <li class="nav-header"></li>
					              <c:forEach items="${manufacturers}" var="manufacturer">
					              	<li>
					              		<a href="javascript:filterCategory('BRAND','${manufacturer.id}')"><i class="fa fa-angle-right"></i><c:out value="${manufacturer.description.name}" /></a></li>
					              </c:forEach>
					            </ul>
					          </div>          
          					</c:if>



						</sidebar>



			</div>
		</div>

