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
<div class="row-exoticamobilia row">
										<c:forEach items="${requestScope.ITEMS}" var="product">
											<div class="col-sm-4" data-id="${product.id}" item-price="${product.price}" item-name="${product.description.name}" item-order="${product.sortOrder}">
												<div class="box-style-1 white-bg object-non-visible animated object-visible">
												    <c:if test="${product.description.highlights!=null && product.description.highlights!=''}">
												    <div class="ribbon-wrapper-green">
												    	<div class="ribbon-green">
												    		<c:out value="${product.description.highlights}" />
												    	</div>
											    	    </div>
												    </c:if> 
												    <c:if test="${!product.canBePurchased }">
													    <div class="sold-out-box">
	    															<span class="sold-out-text"><s:message code="label.soldout" text="Sold out" /></span>
	  													</div> 
												    </c:if>                                    
													<div class="product-image"><c:if test="${product.image!=null}"><a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html"><img src="<sm:shopProductImage imageName="${product.image.imageName}"  sku="${product.sku}"/>" class="product-img" /></a></c:if></div>
													
													<!--  *** Product Name & Price Starts *** -->
													<div class="caption">
													<div class="product-details">
													<div class="clearfix">
														<!--<a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>">-->
														<h3 class="product-heading product-name" itemprop="name"><c:out value="${product.description.name}"/></h3>
														<!--</a>-->
														<h4 class="price">
														<h4>
														<c:choose>
															<c:when test="${product.discounted}">
																<del><c:out value="${product.originalPrice}" /></del>&nbsp;<span class="specialPrice" itemprop="price"><c:out value="${product.finalPrice}" /></span>
															</c:when>
															<c:otherwise>
																<span itemprop="price"><c:out value="${product.finalPrice}" /></span>
															</c:otherwise>
														</c:choose>
														</h4>
														</h4>
													</div>
													<!-- Product Name & Price Ends -->
													<!-- Product Buttons Starts -->
														<div class="clearfix">
															<a class="btn btn-default pull-left" href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>"><s:message code="button.label.view" text="Details" /></a>
															<c:choose>
																<c:when test="${requestScope.FEATURED==true}">
																	<c:if test="${requestScope.CONFIGS['displayAddToCartOnFeaturedItems']==true && requestScope.CONFIGS['allowPurchaseItems']==true && product.canBePurchased}"><a class="btn btn-buy pull-right addToCart" href="javascript:void(0);" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a>
																	</c:if>
																</c:when>
																<c:otherwise>
																	<c:if test="${requestScope.CONFIGS['allowPurchaseItems']==true  && product.canBePurchased}">
																 		<a class="btn btn-buy pull-right addToCart" href="javascript:void(0);" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a>
																	</c:if>
																</c:otherwise>
															</c:choose>
														</div>
													<!-- Product Buttons Ends -->
													</div>
													</div>
													</div>
										    </div>
										</c:forEach>
</div>  