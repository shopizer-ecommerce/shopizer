<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>


			  <!-- Additional menu -->
              <div class="btn-group" style="z-index:400000;">
                    <button class="btn btn-info dropdown-toggle" data-toggle="dropdown"><s:message code="label.product.configure" text="Product definition"/> ... <span class="caret"></span></button>
                     <ul class="dropdown-menu">
				    	<li><a href="<c:url value="/admin/products/editProduct.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.details" text="Product details" /></a></li>
				    	<li><a href="<c:url value="/admin/products/prices.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.prices" text="Product prices" /></a></li>
				    	<li><a href="<c:url value="/admin/products/attributes/list.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.attributes" text="Attributes" /></a></li>
				    	<li><a href="<c:url value="/admin/products/images/list.html" />?id=<c:out value="${productId}"/>"><s:message code="menu.catalogue-products-images" text="Product images" /></a></li>
				    	<li><a href="<c:url value="/admin/products/images/url/list.html" />?id=<c:out value="${productId}"/>"><s:message code="menu.catalogue-products-images-url" text="Product images url" /></a></li>
				    	<li><a href="<c:url value="/admin/products/reviews.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.customer.reviews" text="Reviews" /></a></li>
				    	<li><a href="<c:url value="/admin/catalogue/related/list.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.related.title" text="Related items" /></a></li>
				    	<li><a href="<c:url value="/admin/products/product/keywords.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.searchkeywords" text="Search keywords" /></a></li>
				    	<li><a href="<c:url value="/admin/products/digitalProduct.html" />?id=<c:out value="${productId}"/>"><s:message code="label.product.digitalproduct" text="Digital product" /></a></li>
				    	<li><a href="<c:url value="/admin/products/displayProductToCategories.html" />?id=<c:out value="${productId}"/>"><s:message code="menu.product.category" text="Associate to categories" /></a></li>
                     </ul>
              </div><!-- /btn-group -->
			  <br/>