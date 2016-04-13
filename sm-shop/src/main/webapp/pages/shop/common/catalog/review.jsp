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
<script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>

					<div id="review" class="row-fluid container">

					<p class="lead"><s:message code="label.product.rate"/></p>
					<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
						<s:message code="message.productreview.created" text="You have successfully created a product review" />
					</div>
					
					<div class="span12 no_margin main col-md-12">
					<div class="span6 col-md-8">
						<table class="table" style="margin-bottom: 35px">
							<tbody>
								<tr>
								<c:if test="${product.image!=null}">
								<td>
									<img width="60" src="<c:url value="${product.image.imageUrl}"/>">
								</td>
								</c:if>
								<td>
									<table>
										<tr>
											<td style="border-top: none;"><a class="details" href="<c:url value="/shop/product/" /><c:out value="${product.description.friendlyUrl}"/>.html<sm:breadcrumbParam productId="${product.id}"/>"><c:out value="${product.description.name}" /></a></td>
										</tr>
										<tr>
											<td style="border-top: none;">
												<c:set var="HIDEACTION" value="TRUE" scope="request" />
												<!-- product rating -->
												<jsp:include page="/pages/shop/common/catalog/rating.jsp" />
											</td>
										</tr>
									</table>
								</td>
								<td>
									<c:out value="${product.finalPrice}"/>
								</td>
								</tr>
							</tbody>
						</table>
					
						<br/>


					<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						<c:choose>
							<c:when test="${customerReview!=null}">
								<p>
									<s:message code="label.product.reviews.evaluated" text="You have evaluated this product"/>
											<br/>
											<div class="stars" id="customerRating" style="width: 100px;"></div>
											<br/>
												<blockquote>
    												<p><c:out value="${customerReview.description}" escapeXml="false" /></p>
    												<small><c:out value="${customerReview.customer.firstName}" />&nbsp;<c:out value="${customerReview.customer.lastName}"/>&nbsp;<c:out value="${customerReview.date}" /></small>
   	 											</blockquote>
   	 											</p>
   	 											<script>
												  	$(function() {
														$('#customerRating').raty({ 
															readOnly: true, 
															half: true,
															path : '<c:url value="/resources/img/stars/"/>',
															score: <c:out value="${customerReview.rating}" />
														});
												  	});
								  			   </script>
								
								</p>
							</c:when>
							<c:otherwise>

						<c:url var="submitReview" value="/shop/customer/review/submit.html"/>
					    <form:form method="POST" commandName="review" action="${submitReview}">
					        <form:errors path="*" cssClass="alert alert-error" element="div" />
					    	<fieldset>
					    	<form:hidden id="rating" path="rating"/>
					    	<form:hidden path="productId"/>
							    
								<div class="control-group form-group">
								<label><s:message code="label.generic.youropinion" text="Your opinion" /></label>
									<div class="controls">
										<form:textarea name="" rows="6" class="span8 input form-control form-control-md" path="description"/>
										<label>&nbsp;</label>
										<span class="help-block"><s:message code="label.product.clickrate" text="Product rating (click on the stars to activate rating)" /></span>
										<div class="stars" id="rateMe" style="width: 100px;"></div>
													<script>
													$(function() {
														$('#rateMe').raty({ 
															readOnly: false, 
															half: true,
															path : '<c:url value="/resources/img/stars/"/>',
															score: 5,
															click: function(score, evt) {
																    $('#rating').val(score);
														    }
														});
													});	
													</script>
									</div>
								</div>
							</fieldset>
							<button type="submit" class="btn">
								<s:message code="button.label.submit2" text="Submit"/>
							</button>
					    </form:form>

						</c:otherwise>
						</c:choose>
					</sec:authorize>
						</div>
						<div class="span6 col-md-4">
							<c:if test="${requestScope.CONTENT['customerCare']!=null}">
								<c:out value="${requestScope.CONTENT['customerCare'].description}" escapeXml="false"/>
							</c:if>
						</div>
						</div>
						</div>
					<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
							<p class="muted"><s:message code="label.product.reviews.logon.write" text="You have to be authenticated to write a review" /></p>
					</sec:authorize>

