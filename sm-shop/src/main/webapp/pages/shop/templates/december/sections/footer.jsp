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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

		<!-- footer-area-start -->
		<div class="footer-area ptb-80">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mar_b-30">
						<div class="footer-wrapper">
							<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}"> 
							<ul>
								<jsp:include page="/pages/shop/common/preBuiltBlocks/storeAddress.jsp"/>
								<c:if test="${requestScope.CONTENT['contactUsDetails']!=null}">
									 <br/>
									 <sm:pageContent contentCode="contactUsDetails"/>
								</c:if>
							</ul>
							</c:if>
							
							<c:if test="${requestScope.CONFIGS['facebook_page_url'] != null || requestScope.CONFIGS['twitter_handle'] != null || requestScope.CONFIGS['pinterest'] != null || requestScope.CONFIGS['instagram'] != null}">
							   <ul class="footer-social">
							       <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
								   <li class="twitter"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><i class="fa fa-twitter"></i></a></li>
								   </c:if>
								   <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
								   <li class="facebook"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><i class="fa fa-facebook"></i></a></li>
								   </c:if>
								   <c:if test="${requestScope.CONFIGS['pinterest'] != null}">
								   <li class="pinterest"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['pinterest']}"/>"><i class="fa fa-pinterest"></i></a></li>
								   </c:if>
								   <c:if test="${requestScope.CONFIGS['instagram'] != null}">
							   	   <li class="instagram"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['instagram']}"/>"><i class="fa fa-instagram"></i></a></li>
							       </c:if>
							   </ul>
						   </c:if>
						</div>
					</div>
					<div class="col-lg-2 col-md-3 hidden-sm col-xs-12 mar_b-30">
						<div class="footer-wrapper">
							<div class="footer-wrapper">
								<ul class="usefull-link">
									<li class="<sm:activeLink linkCode="HOME" activeReturnCode="active"/>">
										<a href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a>
									</li>
								    <c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
											   <li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current">${content.name}</a></li>
									</c:forEach>
									<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
										<li class="<sm:activeLink linkCode="CONTACT" activeReturnCode="active"/>"><a href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
									</c:if>
									<c:if test="${fn:length(requestScope.MERCHANT_STORE.languages) > 1}">
										 <c:forEach items="${requestScope.MERCHANT_STORE.languages}" var="language">
										 <c:if test="${requestScope.LANGUAGE.code ne language.code}">
											  <li><a href="<c:url value="/shop?locale=${language.code}"/>"><s:message code="lang.${language.code}" text="${language.code}" /></a></li>
										</c:if>
										</c:forEach>
									</c:if>
									<c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
						                     <sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						                        		<li><a href="<c:url value="/shop/customer/account.html"/>"><s:message code="label.customer.myaccount" text="My account"/></a></li>
						                        		<li><a href="<c:url value="/shop/customer/logout"/>"><s:message code="button.label.logout" text="Logout"/></a></li>
						                       </sec:authorize>
						                       <sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						                        	<li>
														<s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. <s:message code="label.security.nologinacces.store" text="We can't display store logon box"/>
													</li>
						                        </sec:authorize>
						                        <sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
														<li><a href="<c:url value="/shop/customer/registration.html"/>"><s:message code="label.generic.register" text="Register" /></a></li>
														<li><a href="<c:url value="/shop/customer/customLogon.html"/>"><s:message code="button.label.signin" text="Signin" /></a></li>
												</sec:authorize>
									</c:if>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-lg-2 col-md-3 hidden-sm col-xs-12 mar_b-30">
						<div class="footer-wrapper">
							<div class="footer-title">
								<a href="#"><h3><s:message code="menu.catalogue-products" text="Products"/></h3></a>
							</div>
							<div class="footer-wrapper">
								<c:if test="${not empty  requestScope.TOP_CATEGORIES}">
								<ul class="usefull-link">
									<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
									<c:if test="${category.visible}">
								   <li>
	    								<a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
	    									<span class="name">${category.description.name}</span>
	    								</a>
	    							</li> 
	    							</c:if>
									</c:forEach>
								</ul>
								</c:if>
							</div>
						</div>
					</div>
					
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="footer-wrapper">
						<c:if test="${requestScope.CONTENT['footerImage']!=null}">
						<div class="footer-logo">
							<sm:pageContent contentCode="footerImage"/>
						</div>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- footer-area-end -->
		<!-- .copyright-area-start -->
		<div class="copyright-area">
			<div class="container">
				<div class="row">
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 mar_b-30">
						<div class="copyright text-left">
							<p><sm:storeCopy/>&nbsp;-&nbsp;<s:message code="label.generic.providedby" /> <a href="http://www.shopizer.com" class="footer-href" target="_blank">Shopizer</a></p>
						</div>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
						<c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">
						<div class="copyright-img text-right">
							<img src="/resources/img/payment/icons/MasterCard.png" width="50" alt="" />&nbsp;<img src="/resources/img/payment/icons/Visa.png" width="50" alt="" />&nbsp;<img src="/resources/img/payment/icons/AmericanExpress.png" width="50" alt="" />&nbsp;<img src="/resources/img/payment/icons/PayPal.png" width="50" alt="" />
						</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<!-- .copyright-area-end -->