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
            
            
        <div id="footer-section" class="container">
        
        
        	<div class="col-md-12">
        
		    <div class="col-md-3 col-sm-6">
		    
		       <c:if test="${not empty requestScope.CONTENT_PAGE}">
						<p class="lead"><s:message code="label.store.information.title" text="Informations"/></p>
						<!-- Pages -->
                        <ul class="footerLiks">
                        	<c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
							   <li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current">${content.name}</a></li>
							</c:forEach>
							<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
								<li><a href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
							</c:if>
						</ul>
                 </c:if>
		    


			<hr>
			
			<c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
                 <p class="lead"><s:message code="label.customer.myaccount" text="My Account" /></p>
                 <ul class="footerLiks">
                        	<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
                        		<li><a href="<c:url value="/shop/customer/account.html"/>"><s:message code="menu.profile" text="Profile"/></a></li>
                        		<li><a href="<c:url value="/shop/customer/billing.html"/>"><s:message code="label.customer.billingshipping" text="Billing & shipping information"/></a></li>
                        		<li><s:message code="label.order.recent" text="Recent orders"/></li>
                        	</sec:authorize>
                        	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
                        		<li>
									<s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. <s:message code="label.security.nologinacces.store" text="We can't display store logon box"/>
								</li>
                        	</sec:authorize>
                        	<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
								<li><a href="#"><s:message code="button.label.signin" text="Signin" /></a></li>
							</sec:authorize>
				</ul>
			</c:if>
			

			<hr class="hidden-md hidden-lg hidden-sm">

		    </div><!-- /.col-md-3 -->

		    <div class="col-md-3 col-sm-6">

			<c:if test="${not empty  requestScope.TOP_CATEGORIES}">
			<p class="lead">Top categories</p>
			<ul>
			<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
	    					<li>
	    						<a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
	    							<span class="name">${category.description.name}</span>
	    						</a>
	    					</li> 
			</c:forEach>
			</ul>
			</c:if>


			<hr class="hidden-md hidden-lg">

		    </div><!-- /.col-md-3 -->

		    <div class="col-md-3 col-sm-6">
		    
		        <c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">  
		        
		        		<p class="lead"><s:message code="label.store.tofindus" text="Where to find us" /></p>             
						<div class="company">
							<p> 
								<jsp:include page="/pages/shop/common/preBuiltBlocks/storeAddress.jsp"/>
							</p>
						</div>
                 </c:if>


			<hr class="hidden-md hidden-lg hidden-sm">

		    </div><!-- /.col-md-3 -->



		    <div class="col-md-3 col-sm-6">


			
			
			<!-- Social links -->
            <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null || requestScope.CONFIGS['twitter_handle'] != null}">
	             <p class="lead"><s:message code="label.social.connect" text="Connect with us"/></p>
	             <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
	                     <a href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><i class="fa fa-facebook-square fa-3x"></i></a>
	             </c:if>
	             <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
	                      <a href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><i class="fa fa-twitter-square fa-3x"></i></a>
	             </c:if>
            </c:if>



		    </div><!-- /.col-md-3 -->
	    </div>
            
         </div>   
            
  
		<div id="footer" class="container">
				<div class="container">
				   <div class="row-fluid">
					 <div class="span12 text">&copy;&nbsp;<s:message code="label.generic.providedby" /> <a href="http://www.shopizer.com" class="footer-href" target="_blank">Shopizer</div>
				   </div>
				 </div>
		    </div>
        </footer>