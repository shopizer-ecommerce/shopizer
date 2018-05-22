<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 

<%@ page session="false" %>

/**
* Builds the product container div from the product list
**/
function buildProductsList(productList, divProductsContainer) {


		for (var i = 0; i < productList.products.length; i++) {
			var productHtml = '<li itemscope itemtype="http://schema.org/Enumeration" class="item listing-item" data-id="' + productList.products[i].id  + '" item-price="' +  productList.products[i].price +'" item-name="' +  productList.products[i].description.name +'" item-order="' +  productList.products[i].sortOrder +'">';
			productHtml = productHtml + '<div class="product-box"><a href="<c:url value="/shop/product/" />' + productList.products[i].description.friendlyUrl + '.html<sm:breadcrumbParam/>">';
			productHtml = productHtml + '<h4 class="name" itemprop="name">' + productList.products[i].description.name +'</h4></a>';
			if(productList.products[i].discounted) {
					   productHtml = productHtml + '<h3><del>' + productList.products[i].originalPrice +'</del>&nbsp;<span class="specialPrice">' + productList.products[i].finalPrice + '</span></h3>';
			} else {
					    productHtml = productHtml + '<h3>' + productList.products[i].finalPrice +'</h3>';
			}
			var productUrl = '<c:url value="/shop/product/" />' + productList.products[i].description.friendlyUrl + '.html<sm:breadcrumbParam/>';
			//if(ref!=null) {
			//productUrl = productUrl + '/ref=' + ref;
			//}
			if(productList.products[i].image!=null) {
					    productHtml = productHtml + '<a href="' + productUrl + '"><img src="<c:url value=""/>' + productList.products[i].image.imageUrl +'" itemprop="image"></a>';
			}
			productHtml = productHtml + '<div class="bottom"><a href="' + productUrl + '" class="view"><s:message code="button.label.view" text="View" /></a> / <a productid="' + productList.products[i].id + '" href="#" class="addToCart"><s:message code="button.label.addToCart" text="Add to cart" /></a></div>';
			productHtml = productHtml + '</div>'
			productHtml = productHtml + '</li>'
			$(divProductsContainer).append(productHtml);

		}
		
		initBindings();

}