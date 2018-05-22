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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<c:set var="ordersAction" value="${pageContext.request.contextPath}/shop/customer/orders.html"/>
<c:set var="customerOrder" value="${pageContext.request.contextPath}/shop/customer/order.html"/>

<div id="main-content" class="container clearfix row-fluid">
		<div class="span12 col-md-12 common-row">

			  <div class="span8 col-md-8 no-padding">
				
			<div class="checkout-box">
					<span class="box-title">
						<p class="p-title">
							<s:message
									code="menu.order-list" text="List of orders" />
							&nbsp;
							<span class="p-title-text">
							<c:if test="${not empty customerOrders.orders}">
							
								<s:message code="label.entitylist.paging"
							       arguments="${(paginationData.offset)};${paginationData.countByPage};${paginationData.totalCount}"
							       htmlEscape="false"
							       argumentSeparator=";" text=""/>
							
							</c:if>
						</p>
					</span>
               <c:choose>
                 <c:when test="${not empty customerOrders.orders}">
                 	<div id="shop">

					<!-- HISTORY TABLE -->
					<table class="table table-striped">
						<!-- table head -->
						<thead>
							<tr>
								<th><s:message code="label.entity.id" text="Id"/></th>
								<th><s:message code="label.customer.order.date" text="Order date"/></th>
								<th><s:message code="label.generic.amount" text="Amount"/></th>
								<th><s:message code="label.entity.status" text="Status"/></th>
							</tr>
						</thead>
						
						<!-- /HISTORY TABLE -->
						<tbody>
						<c:forEach items="${customerOrders.orders}" var="order" varStatus="orderStatus">
							<tr><!-- item -->
								<td><a href="${customerOrder}?orderId=${order.id}">${order.id}</a></td>
								<td><fmt:formatDate type="both" value="${order.datePurchased}" pattern="yyyy-MM-dd" /></td>
								<td><sm:monetary value="${order.total.value}" />&nbsp;<small>(${fn:length(order.products)} <c:choose><c:when test="${fn:length(order.products)==1}"><s:message code="label.generic.item" text="item"/></c:when><c:otherwise><s:message code="label.generic.items" text="items"/></c:otherwise></c:choose>)</small></td>
								<td>${order.orderStatus}</td>
								
							</tr>
						</c:forEach>
							
						</tbody>
					</table>
					


					
					<!-- PAGINATION -->
					<br/>
					<ul class="pagination">
						
								<c:forEach begin="1" end="${paginationData.totalPages}" varStatus="paginationDataStatus">
								    <li class="${paginationData.currentPage eq (paginationDataStatus.index) ? 'active' : ''}"><a href="${ordersAction}?page=${paginationDataStatus.index}">${paginationDataStatus.index}</a></li>
								</c:forEach>
						
					</ul>
					<!-- /PAGINATION -->
				

				</div>
                 </c:when>
                 <c:otherwise>
                 
                 </c:otherwise>
               
               </c:choose>
			   </div>	

			 </div>
			 <div class="span4 col-md-4 no-padding">
			 	<jsp:include page="/pages/shop/common/customer/customerProfileMenu.jsp" />
			 	<jsp:include page="/pages/shop/common/customer/customerOrdersMenu.jsp" />
			 </div>





		</div>
		<!-- close row-fluid--> 
	</div>
	<!--close .container "main-content" -->