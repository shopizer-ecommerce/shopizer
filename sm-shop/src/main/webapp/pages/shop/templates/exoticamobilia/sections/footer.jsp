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
      <footer id="footer">
            
            <div class="footer">
            
        		<div id="footer-section" class="container">
        
			        <div class="row">
			             <div class="col-md-6">
			                 <div class="logo">
						          <a class="store-name" href="<c:url value="/shop/"/>"><c:out value="${requestScope.MERCHANT_STORE.storename}"/></a>  
			                 </div>
			             </div>
			        </div>
           
           			<br/>
           			<div class="row">
          
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
				

						   <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null || requestScope.CONFIGS['twitter_handle'] != null || requestScope.CONFIGS['pinterest'] != null || requestScope.CONFIGS['instagram'] != null}">
							   <ul class="social-links circle">
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
						   
						   <c:if test="${requestScope.CONTENT['paymentDetails']!=null}">
                                  <sm:pageContent contentCode="paymentDetails"/>
						   </c:if>

							<hr class="hidden-md hidden-lg hidden-sm">
				
					</div><!-- /.col-md-3 -->

		    		<div class="col-md-3 col-sm-6">
		    
				   		<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">  
								<ul class="list-icons">
										<jsp:include page="/pages/shop/common/preBuiltBlocks/storeAddress.jsp"/>
										<c:if test="${requestScope.CONTENT['contactUsDetails']!=null}">
									 		<br/>
									 		<sm:pageContent contentCode="contactUsDetails"/>
									 	</c:if>
								</ul>
		                 </c:if>


						<hr class="hidden-md hidden-lg">

		    		</div><!-- /.col-md-3 -->

		    		<div class="col-md-2 col-sm-6">

             <div class="footer-content">
            <c:if test="${not empty  requestScope.TOP_CATEGORIES}">
            <%-- a verifier top categories EN/FR --%>
			<%-- <p class="lead">Top categories</p> --%>
			<ul class="nav nav-pills nav-stacked">  
			                <li class="<sm:activeLink linkCode="HOME" activeReturnCode="active"/>">
										<a href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a>
							</li>
			<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
	    					<li>
	    						<a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
	    							<span class="name">${category.description.name}</span>
	    						</a>
	    					</li> 
			</c:forEach>
			                <c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
										<li class="<sm:activeLink linkCode="CONTACT" activeReturnCode="active"/>"><a href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
							</c:if>
			</ul>
			</c:if> 
			<c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
 			<!--
 			<ul class="nav nav-pills nav-stacked">  
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
			-->
			</c:if>


			<hr class="hidden-md hidden-lg hidden-sm">
			</div>

		    </div><!-- /.col-md-2 -->



		    <div class="col-md-4 col-sm-6">
              	<c:if test="${requestScope.CONTENT['footerMessage']!=null}">
			    <sm:pageContent contentCode="footerMessage"/>
		        </c:if>
		    </div><!-- /.col-md-3 -->
	    </div>
            
         </div>   
       </div>
       </div>
  
		<div class="subfooter">
				<div class="container">
				   <div class="row">
					<div class="col-md-6"><sm:storeCopy/>&nbsp;-&nbsp;<s:message code="label.generic.providedby" /> <a href="http://www.shopizer.com" class="footer-href" target="_blank">Shopizer</a></div>
				    <div class="col-md-6">
				          <div id="navbar-collapse-2" class="collapse navbar-collapse">
				                <ul class="nav navbar-nav">
				                    <li class="<sm:activeLink linkCode="HOME" activeReturnCode="active"/>">
										<a href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a>
							        </li>
							        <c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
										<li class="<sm:activeLink linkCode="CONTACT" activeReturnCode="active"/>"><a  href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
							        </c:if>
				                </ul>
				          </div>	   
				    </div>
				 </div>
		    </div>
        </footer>