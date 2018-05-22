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

	  <!-- footer -->
            <footer>
                <div id="footer" class="row-fluid">  
                	<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">                 
                    <div class="span3">
						<div class="company">
							<p> 
								<jsp:include page="/pages/shop/common/preBuiltBlocks/storeAddress.jsp"/>
							</p>
						</div>
                    </div>
                    </c:if>
                     <c:if test="${not empty requestScope.CONTENT_PAGE}">
					 <div class="span3 contentPages">   
						<p class="lead"><s:message code="label.store.information.title" text="Informations"/></p>
						<!-- Pages -->
                        <ul class="footerLiks">
                        	<c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
							   <li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current" style="color: #fff;">${content.name}</a></li>
							</c:forEach>
							<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
								<li><a href="<c:url value="/shop/store/contactus.html"/>" style="color: #fff;"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
							</c:if>
						</ul>
                    </div>
                    </c:if>
                    <div class="span3 customerSection">
                    	<c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
                        <p class="lead"><s:message code="label.customer.myaccount" text="My Account" /></p>
                        <ul class="footerLiks">
                        	<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
                        		<li><a href="<c:url value="/shop/customer/account.html"/>" style="color: #fff;"><s:message code="menu.profile" text="Profile"/></a></li>
                        		<li><a href="<c:url value="/shop/customer/billing.html"/>" style="color: #fff;"><s:message code="label.customer.billingshipping" text="Billing & shipping information"/></a></li>
                        		<li><s:message code="label.order.recent" text="Recent orders"/></li>
                        	</sec:authorize>
                        	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
                        		<li>
									<s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. <s:message code="label.security.nologinacces.store" text="We can't display store logon box"/>
								</li>
                        	</sec:authorize>
                        	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
								<li><a href="#" style="color: #fff;"><s:message code="button.label.login" text="Login" /></a></li>
							</sec:authorize>
						</ul>
						</c:if>
                    </div>
                    <div class="span3 socialLinksSection">
                    	<!-- Social links -->
                    	<c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
	                        <p class="lead"><s:message code="label.social.connect" text="Connect with us"/></p>
	                        <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
	                        	<a href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><img src="<c:url value="/resources/img/facebook-transparent.png" />" width="40"></a>
	                        </c:if>
	                        <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
	                        	<a href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><img src="<c:url value="/resources/img/twitter-transparent.png" />" width="50"></a>
	                        </c:if>
                        </c:if>
                    </div>				
                </div>
		    <div id="footer-bottom">
				<div class="container">
				   <div class="row-fluid">
					<div class="span12 text">&copy;&nbsp;<s:message code="label.generic.providedby" /> <a href="http://www.shopizer.com" class="footer-href" target="_blank">Shopizer</div>
				   </div>
				 </div>
		    </div>
            </footer>