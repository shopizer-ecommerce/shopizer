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
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

			<!-- header -->
			<div id="mainmenu" class="row-fluid">
				
					<ul class="nav nav-pills pull-left" id="linkMenuLinks">
						<li class="active"><a href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a></li>
						<c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
    							<li class="">
    								<a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current"> 
    									<span class="name">${content.name}</span> 
    								</a>
    							</li>
						</c:forEach>
						<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
						<li><a href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
						</c:if>
					</ul>
					

					<c:if test="${not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'order') && not fn:contains(requestScope['javax.servlet.forward.servlet_path'], 'cart')}">
					<!-- not displayed in checkout (order) and cart -->
 					<div id="miniCart" style="padding-top: 8px;padding-bottom:10px;" class="btn-group pull-right">
            					&nbsp;&nbsp;&nbsp;
            					<i class="icon-shopping-cart icon-black"></i>
            					<a style="box-shadow:none;color:FF8C00;" href="#" data-toggle="dropdown" class="open noboxshadow dropdown-toggle" id="open-cart"><s:message code="label.mycart" text="My cart"/></a>
								<jsp:include page="/pages/shop/common/cart/minicartinfo.jsp" />
            				
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
							<ul class="logon-box pull-right">
							<li id="fat-menu" class="dropdown">
							<a class="dropdown-toggle noboxshadow" data-toggle="dropdown" href="#">
							   <s:message code="label.generic.welcome" text="Welcome" /> 
							   <c:if test="${not empty requestScope.CUSTOMER.billing.firstName}">
							       <c:out value="${sessionScope.CUSTOMER.billing.firstName}"/>
							   </c:if><b class="caret"></b>
							 </a>
								<ul class="dropdown-menu">
									<li>
										<a onClick="javascript:location.href='<c:url value="/shop/customer/dashboard.html" />';" href="#"><i class="fa fa-user"></i><s:message code="label.customer.myaccount" text="My account"/></a>
									</li>
									<li class="divider"></li>
									<li>
										<a onClick="javascript:location.href='<c:url value="/shop/customer/logout" />';" href="#"><i class="fa fa-power-off"></i><s:message code="button.label.logout" text="Logout"/></a>
									</li>
								</ul>
							</li>
							</ul>
						</c:if>
					</sec:authorize>
					<sec:authorize access="!hasRole('AUTH_CUSTOMER') and fullyAuthenticated">
						<!-- no dual login -->
						<ul class="logon-box pull-right">
							<li>
								<s:message code="label.security.loggedinas" text="You are logged in as"/> [<sec:authentication property="principal.username"/>]. <s:message code="label.security.nologinacces.store" text="We can't display store logon box"/>
							</li>
						</ul>
					</sec:authorize>
					<sec:authorize access="!hasRole('AUTH_CUSTOMER') and !fullyAuthenticated">
					<!-- login box -->
					<ul class="pull-right" style="list-style-type: none;padding-top: 8px;z-index:500000;">
					  <li id="fat-menu" class="dropdown">
					    <a href="#" id="signinDrop" role="button" class="dropdown-toggle noboxshadow" data-toggle="dropdown"><s:message code="button.label.signin" text="Signin" /><b class="caret"></b></a>
					
					
							<div id="signinPane" class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
								<div id="loginError" class="alert alert-error" style="display:none;"></div>
								<!-- form id must be login, form fields must be userName, password and storeCode -->
								<form id="login" method="post" accept-charset="UTF-8">
									<div class="control-group">
	                        				<label><s:message code="label.username" text="Username" /></label>
					                        <div class="controls">
												<input id="signin_userName" style="margin-bottom: 15px;" type="text" name="userName" size="30" />
											</div>
									</div>
									<div class="control-group">
	                        				<label><s:message code="label.password" text="Password" /></label>
					                        <div class="controls">
												<input id="signin_password" style="margin-bottom: 15px;" type="password" name="password" size="30" />
											</div>
									</div>
									<input id="signin_storeCode" name="storeCode" type="hidden" value="<c:out value="${requestScope.MERCHANT_STORE.code}"/>"/>					 
									<button type="submit" style="width:100%" class="btn btn-large" id="login-button"><s:message code="button.label.login" text="Login" /></button>
									
								</form>
								<a onClick="javascript:location.href='<c:url value="/shop/customer/registration.html" />';" href="" role="button" class="" data-toggle="modal"><s:message code="label.register.notyetregistered" text="Not yet registered ?" /></a>
							</div>
					  </li>
					</ul>
					</sec:authorize>
					</c:if>


			</div>
			<!-- End main menu -->
			