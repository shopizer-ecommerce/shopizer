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
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/product.js" />"></script>
<script>

$(function(){

	initProduct('<c:out value="${product.id}"/>','#input-<c:out value="${product.id}" />');//pass form div

});

</script>

								<c:set var="SKU" value="product_${product.sku}" scope="request" />
								<c:if test="${requestScope.CONTENT[SKU]!=null}">
									<sm:pageContent contentCode="${SKU}"/>
								</c:if>


								<!-- leave the form id as is -->
								<form id="input-<c:out value="${product.id}" />">
								<!-- select options -->
								<c:if test="${options!=null && not product.productVirtual}">
									<c:forEach items="${options}" var="option" varStatus="status">
										<div class="control-group form-group"> 
	                        				<label><strong><c:out value="${option.name}"/></strong></label>
	                        				<div class="controls">	       							
											<c:choose>
												<c:when test="${option.type=='select'}">
													<select id="${status.index}" name="${status.index}" class="attribute">
													<c:forEach items="${option.values}" var="optionValue">
														<option value="${optionValue.id}" <c:if test="${optionValue.defaultAttribute==true}"> SELECTED</c:if>>${optionValue.name}<c:if test="${optionValue.price!=null}">&nbsp;<c:out value="${optionValue.price}"/></c:if></option>
													</c:forEach>
													</select>
												</c:when>
												<c:when test="${option.type=='radio'}">
													<c:forEach items="${option.values}" var="optionValue">
														<c:if test="${optionValue.image!=null}">
															<img src="<c:url value="${optionValue.image}"/>" height="40">
														</c:if>
														<input type="radio" class="attribute" id="${status.index}" name="${status.index}" value="<c:out value="${optionValue.id}"/>" <c:if test="${optionValue.defaultAttribute==true}"> checked="checked" </c:if> />
														<c:out value="${optionValue.name}"/><c:if test="${optionValue.price!=null}">&nbsp; (<c:out value="${optionValue.price}"/>)</c:if><br/>
													</c:forEach>
												</c:when>
												<c:when test="${option.type=='text'}">
													<input type="text" class="attribute" id="${status.index}" name="${status.index}" class="input-large">
												</c:when>
												<c:when test="${option.type=='checkbox'}">
													<c:forEach items="${option.values}" var="optionValue">
														<c:if test="${optionValue.image!=null}">
															<img src="<c:url value="${optionValue.image}"/>" height="40">
														</c:if>
														<input type="checkbox" class="attribute" id="${status.index}" name="${status.index}" value="<c:out value="${optionValue.id}"/>"<c:if test="${optionValue.defaultAttribute==true}"> checked="checked" </c:if>  />
														<c:out value="${optionValue.name}"/><c:if test="${optionValue.price!=null}">&nbsp; (<c:out value="${optionValue.price}"/>)</c:if><br/>
													</c:forEach>
												</c:when>										
											</c:choose>				       							
		                                 	<span class="help-inline"></span>
	                        				</div>
	                    			</div>
									</c:forEach>
								</c:if>
								<br/>
								<c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">
								<c:if test="${product.quantity>0}">
								<div class="form-inline">
								<c:if test="${product.quantityOrderMaximum==-1 || product.quantityOrderMaximum>1 && not product.productVirtual}" >
									<div class="form-group product-qty">
										<input id="qty-productId-<c:out value="${product.id}" />" class="input-mini form-control form-control-sm" placeholder="1" type="text">
									</div>
								</c:if>
									<button class="btn addToCart addToCartButton btn-buy" type="button" productId="<c:out value="${product.id}" />"><s:message code="button.label.addToCart" text="Add to cart"/></button>
								</div>
								</c:if>
								</c:if>
								<!-- TODO quantity == 0 contact us for details on the product -->
							

							</form>