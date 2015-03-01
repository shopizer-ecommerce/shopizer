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
										<c:forEach items="${requestScope.ITEMS}" var="product">
											<div class="col-md-4" data-id="${product.id}" item-price="${product.price}" item-name="${product.description.name}" item-order="${product.sortOrder}">
												<div class="product-box">                                        
													<div class="product-image"><c:if test="${product.image!=null}"><a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html"><img src="<sm:shopProductImage imageName="${product.image.imageName}" sku="${product.sku}"/>"/></a></c:if></div>
													<div class="product-info">
														<a href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>"><h4 class="product-name" itemprop="name"><c:out value="${product.description.name}"/></h4></a>
													</div>
													<div class="product-price">
														<h3>
														<c:choose>
															<c:when test="${product.discounted}">
																<del><c:out value="${product.originalPrice}" /></del>&nbsp;<span class="specialPrice" itemprop="price"><c:out value="${product.finalPrice}" /></span>
															</c:when>
															<c:otherwise>
																<span itemprop="price"><c:out value="${product.finalPrice}" /></span>
															</c:otherwise>
														</c:choose>
														</h3>
													</div>
													<div class="product-actions">
													
															<a class="details" href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>"><s:message code="button.label.view" text="Details" /></a> 
															<c:choose><c:when test="${requestScope.FEATURED==true}"><c:if test="${requestScope.CONFIGS['displayAddToCartOnFeaturedItems']==true}"> | <a class="addToCart" href="#" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a></c:if></c:when><c:otherwise> | <a class="addToCart" href="#" productId="${product.id}"><s:message code="button.label.addToCart" text="Add to cart" /></a></c:otherwise></c:choose>

													</div>
												</div>
										    </div>
										</c:forEach>   