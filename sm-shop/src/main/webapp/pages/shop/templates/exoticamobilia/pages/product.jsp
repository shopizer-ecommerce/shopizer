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
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/jquery.elevateZoom-3.0.8.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>


     <div id="shop" class="container">
            
            
            <jsp:include page="/pages/shop/templates/exoticamobilia/sections/breadcrumb.jsp" />
            
            <section class="main-container">
					
					
				<div class="container no-left-padding no-right-padding">	
					<div class="row">
					
						<div class="main col-md-12">
						
						
							<!-- page-title start -->
							<h1 class="page-title margin-top-clear">${product.description.name}</h1>
							<!-- page-title end -->
							
							<div class="row">

							<div class="col-md-4">
								
								<ul role="tablist" class="nav nav-pills white space-top">
									<li class="active">
										<a title="images" data-toggle="tab" role="tab" href="#product-images">
											<i class="fa fa-camera pr-5"></i> <s:message code="label.generic.pictures" text="Pictures" />
										</a>
									</li>
									<li>
										<a title="video" data-toggle="tab" role="tab" href="#product-video">
											<i class="fa fa-video-camera pr-5"></i> <s:message code="label.generic.videos" text="Videos" />
										</a>
									</li>
								</ul>
								<div class="tab-content clear-style">
								    <c:if test="${product.image!=null}">
									<div id="product-images" class="tab-pane active">
										
										
											<div style="width: 360px;" class="owl-item">
													<div id="largeImg" class="overlay-container image-container">
																<img src="<c:url value="${product.image.imageUrl}"/>" alt="<c:out value="${product.description.name}"/>">
																<a href="<sm:shopProductImage imageName="${product.image.imageName}" sku="${product.sku}" size="LARGE"/>" class="popup-img overlay" title="<c:out value="${product.description.name}"/>"><i class="fa fa-search-plus"></i></a>
													</div>
											</div>

											<c:if test="${product.images!=null && fn:length(product.images)>1}">
											<div id="imageGallery" class="row">
												<c:forEach items="${product.images}" var="thumbnail">	
												<div class="col-xs-6 col-md-3">
													<a href="javascript:;"" class="thumbImg thumbnail" imgId="im-<c:out value="${thumbnail.id}"/>" title="<c:out value="${thumbnail.imageName}"/>" rel="<c:url value="${thumbnail.imageUrl}"/>"><img src="<c:url value="${thumbnail.imageUrl}"/>"  alt="<c:url value="${thumbnail.imageName}"/>" ></a>
												</div>
												</c:forEach>
											</div>
											</c:if>
									</div>

									
									
									</c:if>
									
									<div class="tab-pane" id="product-video">
										<div class="embed-responsive embed-responsive-16by9">
												<!-- TODO -->
											    <iframe class="embed-responsive-item" src="<c:url value="/resources/templates/exoticamobilia/img/vimeo.html" />"></iframe>
												<p><a href="http://vimeo.com/29198414">Test Video</a> from <a href="http://vimeo.com/staff">Vimeo</a> on <a href="https://vimeo.com/">Vimeo</a>.</p>
										</div>
									</div>
									<hr>
									<span itemprop="offerDetails" itemscope itemtype="http://data-vocabulary.org/Offer">
									<meta itemprop="seller" content="${requestScope.MERCHANT_STORE.storename}"/>
									<meta itemprop="currency" content="<c:out value="${requestScope.MERCHANT_STORE.currency.code}" />" />
									<span id="productPrice" class="price">
										<c:choose>
											<c:when test="${product.discounted}">
													<del><c:out value="${product.originalPrice}" /></del>&nbsp;<span class="specialPrice"><span itemprop="price"><c:out value="${product.finalPrice}" /></span></span>
											</c:when>
											<c:otherwise>
													<span itemprop="price"><c:out value="${product.finalPrice}" /></span>
											</c:otherwise>
										</c:choose>
									</span>
								   <c:if test="${not product.productVirtual}">
								   <address>
								   		<strong><s:message code="label.product.available" text="Availability"/></strong>:&nbsp;<span><c:choose><c:when test="${product.quantity>0}"><span itemprop="availability" content="in_stock">${product.quantity}</span></c:when><c:otherwise><span itemprop="availability" content="out_of_stock"><s:message code="label.product.outofstock" text="Out of stock" /></c:otherwise></c:choose></span><br>								
								   </address>
							      </c:if>
								  </span>
								  <jsp:include page="/pages/shop/common/catalog/addToCartProduct.jsp" />
									
								</div>
						</div>

					<!--</div>-->
					
					<aside class="col-md-8">
								<div class="sidebar">
										<div class="side product-item vertical-divider-left">
											<div class="productItem tabs-style-2">
												<!-- Nav tabs -->
												<ul role="tablist" class="nav nav-tabs">
													<li class="active"><a data-toggle="tab" role="tab" href="#h2tab1" aria-expanded="true"><i class="fa fa-file-text-o pr-5"></i><s:message code="label.productedit.productdesc" text="Product description" /></a></li>
													<li class=""><a data-toggle="tab" role="tab" href="#h2tab2" aria-expanded="false"><i class="fa fa-files-o pr-5"></i><s:message code="label.product.attribute.specifications" text="Specifications" /></a></li>
													<li class=""><a data-toggle="tab" role="tab" href="#h2tab3"><i class="fa fa-star pr-5"></i><s:message code="label.product.customer.reviews" text="Customer reviews" /></a></li>
												</ul>
												<!-- Tab panes -->
												<div class="tab-content padding-top-clear padding-bottom-clear">
													<div id="h2tab1" class="tab-pane fade  active in">
														<h4 class="space-top"></h4>
														
														<c:out value="${product.description.description}" escapeXml="false"/>
														
														<p>
															<dl class="dl-horizontal">
																<dt><s:message code="label.product.weight" text="Weight" />:</dt>
																<dd><fmt:formatNumber value="${product.productWeight}" maxFractionDigits="2"/>&nbsp;<s:message code="label.generic.weightunit.${requestScope.MERCHANT_STORE.weightunitcode}" text="Pounds" /></dd>
																<dt><s:message code="label.product.height" text="Height" />:</dt>
																<dd><fmt:formatNumber value="${product.productHeight}" maxFractionDigits="2"/>&nbsp;<s:message code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}" text="Inches" /></dd>
																<dt><s:message code="label.product.length" text="Length" />:</dt>
																<dd><fmt:formatNumber value="${product.productLength}" maxFractionDigits="2"/>&nbsp;<s:message code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}" text="Inches" /></dd>
																<dt><s:message code="label.product.width" text="Width" />:</dt>
																<dd><fmt:formatNumber value="${product.productWidth}" maxFractionDigits="2"/>&nbsp;<s:message code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}" text="Inches" /></dd>
															</dl>
														</p>
													</div>
													<div id="h2tab2" class="tab-pane fade">

														<!--  read only properties -->
														
															<h4 class="space-top"><s:message code="label.product.attribute.specifications" text="Specifications" /></h4>
														    
														
															<dl class="dl-horizontal">
															
															<c:if test="${attributes!=null}">
															<c:forEach items="${attributes}" var="attribute" varStatus="status">
						                        				<dt><c:out value="${attribute.name}"/></dt>
																<dd><c:out value="${attribute.readOnlyValue.description}" /></dd>
															</c:forEach>
															</c:if>
															</dl>
													  
														
														
													</div>
													<div id="h2tab3" class="tab-pane fade">
														<!-- comments start -->
															<h4 class="space-top">
																<s:message code="label.product.customer.reviews" text="Customer reviews" />
															</h4>

														<!-- reviews -->
														<jsp:include page="/pages/shop/common/catalog/reviews.jsp" />


													
												</div>
											</div>
										</div>
									</div>
				    </aside>
				</div>
			</div>
		</section>
		
		<!-- Related items -->
        <c:if test="${relatedProducts!=null}">	
		<div class="section clearfix main-container">
				<div class="container no-left-padding no-right-padding">
					<div class="row">
						<div class="col-md-12">
							<h2><s:message code="label.product.related.title" text="Related items"/></h2>
							<!--<p>Voici quelques suggestions de produits additionnels</p>-->
							<!-- Iterate over featuredItems -->
                         	<c:set var="ITEMS" value="${relatedProducts}" scope="request" />
	                        
						</div>
					</div>
				</div>
		</div>
		</c:if>
		
		</div>
					

	
		<script>
		
		$(function () {
		
		
		    $('.thumbImg').click(function(){
		    	var igId= $(this).attr('imgId');
		        var url = $(this).attr('rel');
		        var name = $(this).attr('title');
		        $("#largeImg").html("<img src='" + url + "' /><a href='" + url + "' data-mfp-src='" + url + "' class='popup-img overlay' title='" + name + "'><i class='fa fa-search-plus'></i></a>");
		        //$('#'+ igId).magnificPopup({type:'image'});
		    })
		    
		})
		
		
			/**
			
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
			
			**/
			
			
		</script>
    
