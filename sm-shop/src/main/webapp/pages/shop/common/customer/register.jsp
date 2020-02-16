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
	$("input[type='text']").on("change keyup paste", function(){
		isFormValid();
	});
	
	$("input[type='password']").on("change keyup paste", function(){
		isFormValid();
	});

	$("#registration_country").change(function() {
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

	<div id="main-content" class="container clearfix row-fluid">
			<div id="registrationError"  class="alert alert-warning common-row" style="display:none;"></div>
			<div class="span7 col-md-7 no-padding">
				<form:form method="post" action="${register_url}" id="registrationForm" class="form-horizontal" modelAttribute="customer">
					<form:errors path="*" cssClass="alert alert-error alert-danger form-group" element="div" />
					<fieldset>
						<div class="control-group form-group">
							<label class="required control-label" for="FirstNameRegister"><s:message code="label.generic.firstname" text="First Name"/></label>
							<div class="controls">
							   <s:message code="NotEmpty.customer.firstName" text="First name is required" var="msgFirstName"/>
							   <form:input path="billing.firstName" cssClass="span8 required input form-control form-control-md" id="firstName" title="${msgFirstName}"/>
							   <form:errors path="billing.firstName" cssClass="error" />
								
							</div>
						</div>
						<div class="control-group form-group">
							<label class="required control-label" for="LastNameRegister"><s:message code="label.generic.lastname" text="Last Name"/></label>
							<div class="controls">
							    <s:message code="NotEmpty.customer.lastName" text="Last name is required" var="msgLastName"/>
							    <form:input path="billing.lastName" cssClass="span8 required form-control form-control-md" id="lastName" title="${msgLastName}"/>
							    <form:errors path="billing.lastName" cssClass="error" />
								
							</div>
						</div>

						<div class="control-group form-group">
							<label class="required control-label" for="sex"><s:message code="label.generic.genre" text="Genre"/></label>
							<div class="controls">
							 <form:select path="gender" class="form-control form-control-lg">
							    <form:option value="M"><s:message code="label.generic.male" text="Male"/></form:option>
							     <form:option value="F"><s:message code="label.generic.female" text="Female"/></form:option>
							 </form:select>
								<form:errors path="gender" cssClass="error" />
							</div>
						</div>

						<div class="control-group form-group">
							<label class="control-label required"><s:message code="label.generic.country" text="Country"/></label>
							<div class="controls">
							<form:select path="billing.country" class="form-control form-control-lg" id="registration_country">
							  <form:options items="${countryList}" itemValue="isoCode" itemLabel="name"/>
							</form:select>
							</div>
						</div>
						
						
		
						<div class="control-group form-group">
							<label class="control-label required"><s:message code="label.generic.stateprovince" text="State / Province"/></label>
							<div class="controls">
							<s:message code="NotEmpty.customer.billing.stateProvince" text="State / Province is required" var="msgStateProvince"/>
							<form:select path="billing.zone" id="customer_zones" class="form-control form-control-lg">
							</form:select>
							<form:input path="billing.stateProvince" cssClass="span8 required form-control form-control-md" id="hidden_zones" title="${msgStateProvince}"/>
							</div>
						</div>		
						
						
						<div class="control-group form-group">
							<label class="required control-label" for="username"><s:message code="label.generic.username" text="User name" /></label>
							<div class="controls">
								<s:message code="NotEmpty.customer.userName" text="User name is required" var="msgUserName"/>
								<form:input path="userName" cssClass="span8 required userName form-control form-control-md" id="userName" title="${msgUserName}"/>
								<form:errors path="userName" cssClass="error" />
							</div>
						</div>
						
						
						
						<div class="control-group form-group">
							<label class="required control-label" for="email"><s:message code="label.generic.email" text="Email address"/></label>
							<div class="controls">
							     <s:message code="NotEmpty.customer.emailAddress" text="Email address is required" var="msgEmail"/>
							     <form:input path="emailAddress" cssClass="span8 required email form-control form-control-md" id="email" title="${msgEmail}"/>
							     <form:errors path="emailAddress" cssClass="error" />
							</div>
						</div>
						
						<div class="control-group form-group">
							<label class="required control-label" for="password"><s:message code="label.generic.password" text="Password"/></label>
							<div class="controls">
							    <s:message code="message.password.required" text="Password is required" var="msgPassword"/>
							    <form:password path="password" class="span8 required password form-control form-control-md" id="password" title="${msgPassword}"/>
								<form:errors path="password" cssClass="error" />
							</div>
						</div>
						
						<div class="control-group form-group">
							<label class="required control-label" for="passwordAgain"><s:message code="label.generic.repeatpassword" text="Repeat password"/></label>
							<div class="controls">
							     <s:message code="message.password.repeat.required" text="Repeated password is required" var="msgRepeatPassword"/>
							     <form:password path="checkPassword" class="span8 required checkPassword form-control form-control-md" id="passwordAgain" title="${msgRepeatPassword}"/>
								 <form:errors path="checkPassword" cssClass="error" />
							</div>
						</div>						
		
						<div class="control-group form-group">
										<div class="controls">
											
											<script src="https://www.google.com/recaptcha/api.js?hl=<c:out value="${requestScope.LANGUAGE.code}"/>" async defer></script>

											<div class="g-recaptcha" data-sitekey="<c:out value="${recapatcha_public_key}"/>"></div>

										</div>
						</div>

						<div class="form-actions">
							<input id="submitRegistration" type="submit" value="<s:message code="label.generic.register" text="Register"/>" name="register" class="btn btn-large template-btn">
						</div>
					</fieldset>
				</form:form>
				<!-- end registration form--> 
				
			</div>
			<!--close .span7-->
			
			<div id="why-join" class="span4 offset1 col-md-5">
				<h3 class="short_headline"><span><s:message code="label.register.whyregister" text="Why register?" /></span></h3>
				<p>
					<s:message code="label.register.registerreasons" text="Simplify a checkout process by having your information pre-filed, re-order an item from one click and get access to premium information." />
				</p>
			</div>
	</div>
	<!--close .container "main-content" -->