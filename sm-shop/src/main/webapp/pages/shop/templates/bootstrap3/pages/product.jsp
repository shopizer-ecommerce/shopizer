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

<script src="<c:url value="/resources/js/jquery.elevateZoom-3.0.8.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>



			
            
            <div id="shop" class="container">
            
            
            	<jsp:include page="/pages/shop/templates/bootstrap3/sections/breadcrumb.jsp" />
            
				<header class="page-header">
					<h1>${product.description.name}</h1>
				</header>

                <div itemscope class="col-sm-12 col-md-12 mainProductColumn" itemtype="http://data-vocabulary.org/Product">
                    	<!-- Image column -->
						<div id="img" class="col-sm-6 col-md-6 productMainImage">
							<c:if test="${product.image!=null}">
							<span id="mainImg"><img id="im-<c:out value="${product.image.id}"/>" alt="<c:out value="${product.description.name}"/>" src="<c:url value="${product.image.imageUrl}"/>" data-zoom-image="<sm:shopProductImage imageName="${product.image.imageName}" sku="${product.sku}" size="LARGE"/>"></span>												
							<script>
								$(function() {
									setImageZoom('im-<c:out value="${product.image.id}"/>');
								});	
							</script>
							<c:if test="${product.images!=null && fn:length(product.images)>1}">
								<div id="imageGallery" class="row">
									<c:forEach items="${product.images}" var="thumbnail">								
									<div class="col-xs-6 col-md-3">
										<a href="#img" class="thumbImg thumbnail" title="<c:out value="${thumbnail.imageName}"/>"><img id="im-<c:out value="${thumbnail.id}"/>" src="<c:url value="${thumbnail.imageUrl}"/>" data-zoom-image="<sm:shopProductImage imageName="${thumbnail.imageName}" sku="${product.sku}" size="LARGE"/>" alt="<c:url value="${thumbnail.imageName}"/>" ></a>
									</div>
									</c:forEach>								
								</div>
							</c:if>
							</c:if>
						</div>
						
						<!-- Google rich snippets (http://blog.hubspot.com/power-google-rich-snippets-ecommerce-seo-ht) -->
						<!-- Product description column -->
						<div class="col-sm-6 col-md-6">

							<!-- product rating -->
							<jsp:include page="/pages/shop/common/catalog/rating.jsp" />
							
							
							<address>
								<strong><s:message code="label.product.brand" text="Brand"/></strong> <span itemprop="brand"><c:out value="${product.manufacturer.description.name}" /></span><br>
								<strong><s:message code="label.product.code" text="Product code"/></strong> <span itemprop="identifier" content="mpn:${product.sku}">${product.sku}</span><br>								
							</address>
							<span itemprop="offerDetails" itemscope itemtype="http://data-vocabulary.org/Offer">
							<meta itemprop="seller" content="${requestScope.MERCHANT_STORE.storename}"/>
							<meta itemprop="currency" content="<c:out value="${requestScope.MERCHANT_STORE.currency.code}" />" />
							<h3 id="productPrice">
									<c:choose>
										<c:when test="${product.discounted}">
												<del><c:out value="${product.originalPrice}" /></del>&nbsp;<span class="specialPrice"><span itemprop="price"><c:out value="${product.finalPrice}" /></span></span>
										</c:when>
										<c:otherwise>
												<span itemprop="price"><c:out value="${product.finalPrice}" /></span>
										</c:otherwise>
									</c:choose>
							</h3>
							<c:if test="${not product.productVirtual}">
							<address>
								<strong><s:message code="label.product.available" text="Availability"/></strong> <span><c:choose><c:when test="${product.quantity>0}"><span itemprop="availability" content="in_stock">${product.quantity}</span></c:when><c:otherwise><span itemprop="availability" content="out_of_stock"><s:message code="label.product.outofstock" text="Out of stock" /></c:otherwise></c:choose></span><br>								
							</address>
							</c:if>
							</span>
							<p>
								<jsp:include page="/pages/shop/common/catalog/addToCartProduct.jsp" />
							</p>
						</div>

					</div>
			 
                    <div class="row">

							<ul class="nav nav-tabs" id="productTabs">
								<li class="active"><a href="#description"><s:message code="label.productedit.productdesc" text="Product description" /></a></li>
								<c:if test="${attributes!=null}"><li><a href="#specifications"><s:message code="label.product.attribute.specifications" text="Specifications" /></a></li></c:if>
								<li><a href="#reviews"><s:message code="label.product.customer.reviews" text="Customer reviews" /></a></li>
							</ul>							 
							<div class="tab-content">
								<div class="tab-pane active productDescription" id="description">
									<c:out value="${product.description.description}" escapeXml="false"/>
								</div>	
								<div class="tab-pane productDescription" id="specifications">
									<!--  read only properties -->
									<c:if test="${attributes!=null}">
										<table>
										<c:forEach items="${attributes}" var="attribute" varStatus="status">
										<tr>
	                        				<td><p><c:out value="${attribute.name}"/>: </p></td>
											<td><p>&nbsp;<c:out value="${attribute.readOnlyValue.description}" /></p></td>
										</tr>
									</c:forEach>
									</table>
								  </c:if>
								</div>
								<div class="tab-pane productDescription" id="reviews">

									<!-- reviews -->
									<jsp:include page="/pages/shop/common/catalog/reviews.jsp" />


								</div>						
                        </div>	
                        <br/>
                        <br/>
                        <!-- Related items -->
                        <c:if test="${relatedProducts!=null}">	
                        			<h1><s:message code="label.product.related.title" text="Related items"/></h1>				
									<ul class="thumbnails product-list">
										<!-- Iterate over featuredItems -->
                         				<c:set var="ITEMS" value="${relatedProducts}" scope="request" />
	                         			<jsp:include page="/pages/shop/templates/bootstrap3/sections/productBox.jsp" />
									</ul>
						</c:if>
						
						
                    </div>
                </div>

            </div>

		<script>
			
			$(function () {
				$('#productTabs a:first').tab('show');
				$('#productTabs a').click(function (e) {
					e.preventDefault();
					$(this).tab('show');
				})
				$('.thumbImg').click(function (e) {
					img = $(this).find('img').clone();
					$('#mainImg').html(img);
					setImageZoom(img.attr('id'));
				})
			})

			<!-- lens plugin -->
			function setImageZoom(id) {
			    $('#' + id).elevateZoom({
		    			zoomType	: "lens",
		    			lensShape : "square",
		    			lensSize    : 240
		   		}); 
			}
			
			
		</script>
    
