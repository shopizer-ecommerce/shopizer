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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ taglib uri="/WEB-INF/shopizer-functions.tld" prefix="display" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>




<section class="header">
            <div class="container no-padding-right">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="sitelogo-block">
							<c:choose>
			                		<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
			                			<img class="logoImage" src="<sm:storeLogo/>"/>
			                		</c:when>
			                		<c:otherwise>
			                			<a class="store-name" href="<c:url value="/shop/"/>">
			                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
			                			</a>  
			                		</c:otherwise>
			                </c:choose>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="topinfo-block">
                        
                           <!-- CART -->
						   <c:if test="${not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'order') && not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'cart')}">
				 				<!-- not displayed in checkout (order) and cart -->
				 				<div id="miniCart" class="btn-group pull-right">
				 					
									<button id="open-cart" class="cartbutton dropdown-toggle" type="button" data-toggle="dropdown">
										<span class="carticon fa fa-shopping-cart fa-2x icon"></span>
										<jsp:include page="/pages/shop/common/cart/minicartinfo.jsp" />
									</button>
				
									<ul class="dropdown-menu minicart" id="minicartComponent">
						              		<li>
												<jsp:include page="/pages/shop/common/cart/minicart.jsp" />
						              		</li>	
						            </ul>
								</div>
							</c:if>

							<!-- If display customer section -->
							<c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
							<sec:authorize access="hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
									<!-- logged in user -->
									<c:if test="${requestScope.CUSTOMER!=null}">
									<div id="signin" class="btn-group pull-right" style="color:#000;">
			
									<button id="open-cart" class="profileButton dropdown-toggle" type="button" data-toggle="dropdown">
										<span class="normal-label">
											<s:message code="label.generic.welcome" text="Welcome" />
												<c:if test="${not empty requestScope.CUSTOMER.billing.firstName}">
										       		<c:out value="${sessionScope.CUSTOMER.billing.firstName}"/>
										   		</c:if>
										
										</span>
										<span class="caret icon signincaret"></span>
									</button>
											<ul id="signinComponent" class="dropdown-menu">
												<li>
													<a onClick="javascript:location.href='<c:url value="/shop/customer/dashboard.html" />';" href="#"><i class="fa fa-user"></i><s:message code="label.customer.myaccount" text="My account"/></a>
												</li>
												<li class="divider"></li>
												<li>
													<a onClick="javascript:location.href='<c:url value="/shop/customer/j_spring_security_logout" />';" href="#"><i class="fa fa-power-off"></i><s:message code="button.label.logout" text="Logout"/></a>
												</li>
											</ul>	
									</div>
									</c:if>
							</sec:authorize>
							<sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
									<!-- no dual login -->
									<div id="signin" class="pull-right">
										<p class="loggedInMessage"><s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. <s:message code="label.security.nologinacces.store" text="We can't display store logon box"/></p>
									</div>
							</sec:authorize>
							<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
								<!-- login box -->
								<div id="signin" class="btn-group pull-right">
								<button id="signinDrop" class="dropdown-toggle" type="button" data-toggle="dropdown">
									<span class="signininfo normal-label"><s:message code="button.label.signin" text="Signin" /></span> <span class="caret icon signincaret"></span>
								</button>
								<!-- form id must be login, form fields must be userName, password and storeCode -->
								<ul id="signinComponent" class="dropdown-menu">
									<li>		
										<div id="signinPane" >
												<div id="loginError" class="alert alert-error bg-danger" style="display:none;"></div>
												<form id="login" method="post" accept-charset="UTF-8">
												<div class="control-group">
				                        				<label><s:message code="label.username" text="Username" /></label>
								                        <div class="controls">
								                        	<!-- important keep signin_userName -->
															<input id="signin_userName" style="margin-bottom: 15px;" type="text" name="userName" size="30" />
														</div>
												</div>
												<div class="control-group">
				                        				<label><s:message code="label.password" text="Password" /></label>
								                        <div class="controls">
								                        	<!-- important keep signin_password -->
															<input id="signin_password" style="margin-bottom: 15px;" type="password" name="password" size="30" />
														</div>
												</div>
												<!-- important keep signin_storeCode -->
												<input id="signin_storeCode" name="storeCode" type="hidden" value="<c:out value="${requestScope.MERCHANT_STORE.code}"/>"/>					 
												<button type="submit" style="width:100%" class="btn btn-large" id="login-button"><s:message code="button.label.login" text="Login" /></button>
												
											</form>
											<br/>
											<a id="registerLink" onClick="javascript:location.href='<c:url value="/shop/customer/registration.html" />';" href="" role="button" class="" data-toggle="modal"><s:message code="label.register.notyetregistered" text="Not yet registered ?" /></a>
										</div>
									</li>
								</div>
								</sec:authorize>
								</c:if>								
								
                        </div>
                    </div>
                </div>
            </div>
            <!--/.container -->
 </section>
 
 



			