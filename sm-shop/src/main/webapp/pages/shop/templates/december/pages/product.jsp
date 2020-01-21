
<%
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script
	src="<c:url value="/resources/js/jquery.elevateZoom-3.0.8.min.js" />"></script>
<script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>


<div id="shop" class="container">
	<!-- all-hyperion-page-start -->
	<div class="all-hyperion-page">
		<div class="container">
			<div class="row">
				<div class="col-lg-9 col-md-9 col-sm-12 col-xs-12">
					<!-- product-simple-area-start -->
					<div class="product-simple-area ptb-80 ptb-40-md ptb-20-xs"
						style="padding-top: 40px !important;">
						<div class="row">
							<div class="col-lg-5 col-md-7 col-sm-7 col-xs-12">
								<div class="tab-content">
									<div class="tab-pane active" id="view1">
										<a class="image-link" href="${product.image.imageUrl}"><img
											src="${product.image.imageUrl}" alt=""></a>
									</div>
									<c:if
										test="${product.images!=null && fn:length(product.images) gt 1}">
										<c:forEach items="${product.images}" var="thumbnail">
											<c:if test="${thumbnail.imageType==0}">
												<div class="tab-pane"
													id="view<c:out value="${thumbnail.id}"/>">
													<c:choose>
														<c:when test="${thumbnail.externalUrl==null}">
															<a href="<c:url value="${thumbnail.imageUrl}"/>"
																class="image-link"
																imgId="im-<c:out value="${thumbnail.id}"/>"
																title="<c:out value="${thumbnail.imageName}"/>"
																rel="<c:url value="${thumbnail.imageUrl}"/>"><img
																src="<c:url value="${thumbnail.imageUrl}"/>"
																alt="<c:url value="${thumbnail.imageName}"/>"></a>
														</c:when>
														<c:otherwise>
															<a href="javascript:;"
																" class="detailsThumbImg thumbImg thumbnail image-link" 
																imgId="im-<c:out value="${thumbnail.id}"/>"
																title="<c:out value="${product.description.name}"/>"
																rel="<c:url value="${thumbnail.externalUrl}"/>"><img
																src="${thumbnail.externalUrl}"
																alt="<c:url value="${product.description.name}"/>" style="border:0 !important;"></a>
														</c:otherwise>
													</c:choose>
												</div>
											</c:if>
										</c:forEach>
									</c:if>
								</div>
								<!-- Nav tabs -->
								<ul class="sinple-tab-menu" role="tablist">
									<c:if
										test="${product.images!=null && fn:length(product.images) gt 1}">
										<c:forEach items="${product.images}" var="thumbnail">
											<c:if test="${thumbnail.imageType==0}">
												<li><a href="#view<c:out value="${thumbnail.id}"/>"
													data-toggle="tab"><img
														src="<c:url value="${thumbnail.imageUrl}"/>"
														alt="<c:url value="${thumbnail.imageName}"/>" /></a></li>
											</c:if>
										</c:forEach>
									</c:if>
								</ul>
							</div>
							<!-- fin col-lg -->
							<div class="col-lg-7 col-md-5 col-sm-5 col-xs-12">
								<div class="product-simple-content">
									<div class="sinple-c-title">
										<h3>${product.description.name}</h3>
									</div>
									<div class="checkbox">
										<span><i class="fa fa-check-square" aria-hidden="true"></i>
											<c:if test="${not product.productVirtual}">
												<div class="checkbox">
													<!-- availability -->
													<strong><s:message code="label.product.available"
															text="Availability" /></strong>:&nbsp;<span><c:choose>
															<c:when test="${product.quantity>0}">
																<span itemprop="availability" content="in_stock">${product.quantity}</span>
															</c:when>
															<c:otherwise>
																<span itemprop="availability" content="out_of_stock"><s:message
																		code="label.product.outofstock" text="Out of stock" />
															</c:otherwise>
														</c:choose></span><br>
												</div>
											</c:if></span>
									</div>
									<!-- sku-->
									<span> <s:message code="label.product.code"
											text="Product code" /> ${product.sku}
									</span>
									<div class="product-price-star star-2">
										<!-- Review -->
										<jsp:include page="/pages/shop/common/catalog/rating.jsp" />
									</div>
									<!-- price -->
									<h4>
										<span itemprop="offerDetails" itemscope
											itemtype="http://data-vocabulary.org/Offer">
											<meta itemprop="seller"
												content="${requestScope.MERCHANT_STORE.storename}" />
											<meta itemprop="currency"
												content="<c:out value="${requestScope.MERCHANT_STORE.currency.code}" />" />
											<span id="productPrice" class="price"> <c:choose>
													<c:when test="${product.discounted}">
														<del>
															<c:out value="${product.originalPrice}" />
														</del>&nbsp;<span class="specialPrice"><span
															itemprop="price"><c:out
																	value="${product.finalPrice}" /></span></span>
													</c:when>
													<c:otherwise>
														<span itemprop="price"><c:out
																value="${product.finalPrice}" /></span>
													</c:otherwise>
												</c:choose>
										</span>
										</span>
									</h4>
									<jsp:include
										page="/pages/shop/common/catalog/addToCartProduct.jsp" />
									<!-- Facebook share button -->
									<!--
									<div>
										<div class="action-heiper">
											<iframe
												src="https://www.facebook.com/plugins/share_button.php?locale=${LOCALE.language}_${LOCALE.country}&href=<c:out value="${requestScope.CONFIGS['SHOP_SCHEME']}"/>%3A%2F%2F<c:out value="${requestScope.MERCHANT_STORE.domainName}"/><c:url value="/shop/${product.description.friendlyUrl}.html"/>&layout=button_count&size=large&mobile_iframe=true&appId=<c:out value="${requestScope.CONFIGS['shopizer.facebook-appid']}"/>&width=83&height=28"
												width="113" height="43" style="border: none; overflow: hidden margin=left:10px;"
												scrolling="no" frameborder="0" allowTransparency="true"></iframe>
										</div>
									</div>
									-->
								</div>
							</div>
						</div>
					</div>
					<!-- product-simple-area-end -->
					<div class="product-info-detailed pb-80 ptb-40-md ptb-20-xs">
						<div class="row">
							<div class="col-lg-12">
								<div class="product-info-tab">
									<!-- Nav tabs -->
									<ul class="product-info-tab-menu" role="tablist">
										<li class="active"><a href="#details" data-toggle="tab"><i
												class="fa fa-file-text-o pr-5"></i> <s:message
													code="label.productedit.productdesc"
													text="Product description" /></a></li>
										<li><a href="#reviews" data-toggle="tab"><i
												class="fa fa-star pr-5"></i> <s:message
													code="label.product.customer.reviews"
													text="Customer reviews" /></a></li>
									</ul>
									<!-- Tab panes -->
									<div class="tab-content">
										<div class="tab-pane active" id="details">
											<div class="product-info-tab-content">
												<c:out value="${product.description.description}"
													escapeXml="false" />
												<dl class="dl-horizontal">
													<dt>
														<s:message code="label.product.weight" text="Weight" />
														:
													</dt>
													<dd>
														<fmt:formatNumber value="${product.productSpecifications.weight}"
															maxFractionDigits="2" />
														&nbsp;
														<s:message
															code="label.generic.weightunit.${requestScope.MERCHANT_STORE.weightunitcode}"
															text="Pounds" />
													</dd>
													<dt>
														<s:message code="label.product.height" text="Height" />
														:
													</dt>
													<dd>
														<fmt:formatNumber value="${product.productSpecifications.height}"
															maxFractionDigits="2" />
														&nbsp;
														<s:message
															code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}"
															text="Inches" />
													</dd>
													<dt>
														<s:message code="label.product.width" text="Width" />
														:
													</dt>
													<dd>
														<fmt:formatNumber value="${product.productSpecifications.width}"
															maxFractionDigits="2" />
														&nbsp;
														<s:message
															code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}"
															text="Inches" />
													</dd>
													<dt>
														<s:message code="label.product.length" text="Length" />
														:
													</dt>
													<dd>
														<fmt:formatNumber value="${product.productSpecifications.length}"
															maxFractionDigits="2" />
														&nbsp;
														<s:message
															code="label.generic.sizeunit.${requestScope.MERCHANT_STORE.seizeunitcode}"
															text="Inches" />
													</dd>
												</dl>
											</div>
										</div>
										<div class="tab-pane" id="reviews">
											<div class="customer-review-top">
												<h4>
													<s:message code="label.product.customer.reviews"
														text="Customer reviews" />
												</h4>
												<!-- reviews -->
												<jsp:include page="/pages/shop/common/catalog/reviews.jsp" />
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- fin product info -->
						</div>
						<!--fin col 9 -->
					</div>
				</div>

				<!-- Related items -->

				<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
					<c:if test="${relatedProducts!=null}">
						<div
							class="feature-preduct-area hyperion home-page-2 pb-50 pb-50-md"
							style="padding-top: 40px !important;padding-bottom:10px !important;">
							<div class="row">
								<div class="col-lg-12">
									<div class="hyper-title">
										<h4 class="text-uppercase">
											<s:message code="label.product.related.title"
												text="Related items" />
										</h4>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<div>
										<div>
											<!-- Iterate over featuredItems -->
											<c:set var="ITEMS" value="${relatedProducts}" scope="request" />
											<jsp:include
												page="/pages/shop/templates/generic/sections/productBox.jsp" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<c:if test="${requestScope.CONTENT['sideBar']!=null}">
									<sm:pageContent contentCode="sideBar"/>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
</div>

<script>
		
		$(function () {
			
			$('.popup-img').magnificPopup({type:'image'});
		
		
		    $('.thumbImg').click(function(){
		    	var igId = $(this).attr('imgId');
		        var url = $(this).attr('rel');
		        var name = $(this).attr('title');
		        $("#largeImg").html("<img src='" + url + "' /><a href='" + url + "' data-mfp-src='" + url + "' class='popup-img overlay' title='" + name + "'><i class='fa fa-search-plus'></i></a>");
		        //re bind action
		        $('.popup-img').magnificPopup({type:'image'});
		    })
		    
		})
		

			
		</script>

