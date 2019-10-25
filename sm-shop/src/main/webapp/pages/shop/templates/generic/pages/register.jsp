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


<!-- requires functions.jsp -->
<script src="<c:url value="/resources/js/jquery.maskedinput.min.js" />"></script>
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
<script src="<c:url value="/resources/js/address.js" />"></script>


<script type="text/javascript">


$(document).ready(function() {
	

	getZones($('#registration_country').val(),'<c:out value="${customer.billing.zone}" />',isFormValid);
	$("#hidden_zones").hide();
	$("#registration_country").change(function() {
			getZones($(this).val(),'<c:out value="${customer.billing.zone}" />',isFormValid);
	})
	
	isFormValid();
	$("input[type='text']").on("change keyup paste blur", function(){
		$("#userName").val($("#emailAddress").val());
		isFormValid();
	});
	
	$("input[type='password']").on("change keyup paste", function(){
		$("#userName").val($("#emailAddress").val());
		isFormValid();
	});

	$("#registration_country").change(function() {
		$("#userName").val($("#emailAddress").val());
		isFormValid();	
	});
	
});


function isFormValid() {
	
	if($('.alert-error').is(":visible")) {
		return true;
	}
	
	if($('.alert-success').is(":visible")) {
		return true;
	}
	
	$('#registrationError').hide();//reset error message
	var msg = isCustomerFormValid($('#registrationForm'));
	
	if(msg!=null) {//disable submit button
		$('#submitRegistration').addClass('btn-disabled');
		$('#submitRegistration').prop('disabled', true);
		$('#registrationError').html(msg);
		$('#registrationError').show();
		return false;
	} else {
		$('#submitRegistration').removeClass('btn-disabled');
		$('#submitRegistration').prop('disabled', false);
		$('#registrationError').hide();
		return true;
	}
}


 
 
 </script>

<c:set var="register_url" value="${pageContext.request.contextPath}/shop/customer/register.html"/>
<div id="registrationError"  class="alert alert-warning common-row" style="display:none;"></div>


		<!-- page-title-wrapper-end -->
		<!-- contuct-form-area-start -->
			<div class="login-area ptb-80">
				<div class="container">
					<div class="row">
						<div class=" col-lg-6 col-md-6 col-sm-8  col-xs-12">
							<div class="login-title">
								<h3><s:message code="label.register.personal.information" text="Personal information"/></h3>
							</div>
							<div class="login-form">
								<form:form method="post" action="${register_url}" id="registrationForm" modelAttribute="customer">
									<form:errors path="*" cssClass="alert alert-error alert-danger form-group" element="div" />
									<div class="form-group login-page">
										<label for="firstName"><s:message code="label.generic.firstname" text="First Name"/> <span>*</span></label>
										<s:message code="NotEmpty.customer.firstName" text="First name is required" var="msgFirstName"/>
							   			<form:input path="billing.firstName" cssClass="span8 required input form-control form-control-md" id="firstName" title="${msgFirstName}"/>
							   			<form:errors path="billing.firstName" cssClass="error" />
									</div>
									<div class="form-group login-page">
										<label for="lastName"><s:message code="label.generic.lastname" text="Last Name"/> <span>*</span></label>
										<s:message code="NotEmpty.customer.lastName" text="Last name is required" var="msgLastName"/>
							    		<form:input path="billing.lastName" cssClass="span8 required form-control form-control-md" id="lastName" title="${msgLastName}"/>
							    		<form:errors path="billing.lastName" cssClass="error" />
									</div>
									<div class="form-group login-page">
										<label for="country"><s:message code="label.generic.country" text="Country"/> <span>*</span></label>
										<form:select path="billing.country" class="form-control form-control-lg" id="registration_country">
							  				<form:options items="${countryList}" itemValue="isoCode" itemLabel="name"/>
										</form:select>
									</div>
									<div class="form-group login-page">
										<label for="stateProvince"><s:message code="label.generic.stateprovince" text="State / Province"/> <span>*</span></label>
										<s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
										<form:select path="billing.zone" id="customer_zones" class="form-control form-control-lg">
										</form:select>
										<form:input path="billing.stateProvince" cssClass="span8 required form-control form-control-md" id="hidden_zones" title="${msgStateProvince}"/>
									</div>
									<!--
									<div class="form-group">
										<div class="checkbox">
											<label>
												<input type="checkbox"> Sign Up for Newsletter
											</label>
										</div>
									</div>	
									-->								
									<div class="login-title">
										<h3><s:message code="label.register.signin.information" text="Sign-in information"/></h3>
									</div>									
									<div class="form-group login-page">
										<label for="email"><s:message code="label.generic.email" text="Email address"/> <span>*</span></label>
							     		<s:message code="NotEmpty.customer.emailAddress" text="Email address is required" var="msgEmail"/>
							     		<form:input path="emailAddress" cssClass="span8 required email form-control form-control-md" id="emailAddress" title="${msgEmail}"/>
							     		<form:errors path="emailAddress" cssClass="error" />
							     		<form:hidden path="userName" />
									</div>								
									<div class="form-group login-page">
										<label for="password"><s:message code="label.generic.password" text="Password"/> <span>*</span></label>
										<s:message code="message.password.required" text="Password is required" var="msgPassword"/>
							    		<form:password path="password" class="span8 required password form-control form-control-md" id="password" title="${msgPassword}"/>
										<form:errors path="password" cssClass="error" />
									</div>							
									<div class="form-group login-page">
										<label for="repeatPassword"><s:message code="label.generic.repeatpassword" text="Repeat password"/> <span>*</span></label>
										<s:message code="message.password.repeat.required" text="Repeated password is required" var="msgRepeatPassword"/>
							     		<form:password path="checkPassword" class="span8 required checkPassword form-control form-control-md" id="passwordAgain" title="${msgRepeatPassword}"/>
								 		<form:errors path="checkPassword" cssClass="error" />
									</div>
									<button type="submit" class="btn btn-default login-btn"><s:message code="label.register.createaccount" text="Create an account"/></button>
								</form:form>						
							</div>
						</div>
						<div class="col-lg-6 col-md-6 col-sm-4 hidden-xs">
								<h3 class="short_headline"><span><s:message code="label.register.whyregister" text="Why register?" /></span></h3>
								<p>
									<s:message code="label.register.registerreasons" text="Simplify a checkout process by having your information pre-filed, re-order an item from one click and get access to premium information." />
								</p>
						
						</div>
					</div>
				</div>
			</div>
		<!-- contuct-form-area-end -->



		