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

								<c:set var="billing" value="${pageContext.request.contextPath}/shop/customer/billing.html"/>
								<ul class="nav nav-list">
									<li><a href="<c:url value="/shop/customer/dashboard.html"/>"><i class="fa fa-angle-right"></i> <s:message code="label.customer.myaccount" text="My account"/></a></li>
									<li>
									   <a href="${billing}">
									   		<i class="fa fa-angle-right"></i> <s:message code="label.customer.billingshipping" text="Billing & shipping information"/>
									    </a>
									 </li>
									 <li><a href="<c:url value="/shop/customer/password.html"/>"><i class="fa fa-angle-right"></i> <s:message code="menu.change-password" text="Change password"/></a></li>
									 <li><a href="<c:url value="/shop/customer/logout"/>"><i class="fa fa-angle-right"></i> <s:message code="button.label.logout" text="Logout"/></a></li>
								</ul>