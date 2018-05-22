<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

					<div id="review" class="review">
								<div>
									<div class="stars" id="productRating" style="width: 100px;">
									</div>
									<script>
									$(function() {
										$('#productRating').raty({ 
											readOnly: true, 
											half: true,
											path : '<c:url value="/resources/img/stars/"/>',
											score: <c:out value="${product.rating}" />
										});
									});	
									</script>
									<c:choose>
									   <c:when test="${product.ratingCount>0}">
											<a href="#" onclick="$('a[href=\'#reviews\']').trigger('click');"><c:out value="${product.ratingCount}"/>&nbsp;<s:message code="label.product.customer.reviews" text="Customer reviews" /></a>
									   </c:when>
									   <c:otherwise>
									   		0&nbsp;<s:message code="label.product.customer.reviews" text="Customer reviews" />
									   </c:otherwise>
									</c:choose>
									<c:if test="${requestScope.HIDEACTION==null}">
									<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
  										|  
									<a href="<c:url value="/shop/customer/review.html"/>?productId=${product.id}"><s:message code="label.product.reviews.write" text="Write a review" /></a>
									</sec:authorize>
									</c:if>
								</div>
							</div>

