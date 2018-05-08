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

<link href="<c:url value="/resources/css/assets/bootstrap-social.css" />" rel="stylesheet">
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


			<div class="login-area ptb-80">
				<div class="container">
					<div class="row">
						<div class=" col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="login-title">
								<h3><s:message code="label.customer.registered" text="Registered customer"/></h3>
								<span><s:message code="label.customer.registered.signinemail" text="If you have an account, sign in with your email address"/>.</span>
							</div>
							<div id="login-form" class="login-form">
								<form>									
									<div class="form-group login-page">
										<label for="inputEmail"><s:message code="label.customer.email" text="Customer email address"/> <span>*</span></label>
										<input type="text" class="form-control" id="signin_userName" name="signin_userName">
									</div>								
									<div class="form-group login-page">
										<label for="password"><s:message code="label.generic.password" text="Password"/> <span>*</span></label>
										<input type="Password" class="form-control" id="signin_password" name="signin_password">
									</div>
									<input type="hidden" id="signin_storeCode" name="signin_storeCode" value="<c:out value="${requestScope.MERCHANT_STORE.code}"/>"/>	
									<button type="submit" id="genericLogin-button" class="btn btn-default login-btn"><s:message code="button.label.signin" text="button.label.signin"/></button>
								</form>
					
							</div>
							<!-- 
							<a href="#" class="back">Forgot Your Password?</a>
							 -->
							 <!-- 
							<br/>
							<div class="login-title">
								
								<span><s:message code="label.customer.signin.social" arguments="Facebook" text="Make it easier and sign in with your Facebook account!" /></span>
							</div>

								  <ass="btn btn-block btn-social btn-lg btn-facebook">
    								<span class="fa fa-facebook"></span> <s:message code="label.customer.signin.social.system" arguments="Facebook" text="Sign in with Facebook" />
  								  </a>
  								  -->
						</div>
						<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
							<div class="login-title">
								<h3><s:message code="label.customer.new" text="New customer"/></h3>
								<span><s:message code="label.customer.faster" text="Creating an account has many benefits: check out faster, keep more than one address, track orders and more."/></span>
							</div>
							<a class="btn btn-default login-btn" href="<s:url value="/shop/customer/registration.html"/>"><s:message code="button.label.register" text="Register" /></a>
						</div>
					</div>
				</div>
			</div>


		