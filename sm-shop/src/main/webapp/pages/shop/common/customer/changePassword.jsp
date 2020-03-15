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
<script src="<c:url value="/resources/js/shop-customer.js" />"></script>

<script type="text/javascript">

$(document).ready(function() {
	

	isFormValid();
	//bind fields to be validated
	$("input[type='text']").on("change keyup paste", function(){
		resetGlobalErrors();
		isFormValid();
	});
	
	$("input[type='password']").on("change keyup paste", function(){
		resetGlobalErrors();
		isFormValid();
	});
	
	$("#submitChangePassword").click(function(e) {
		e.preventDefault();//do not submit form
		resetGlobalErrors();
		if(isFormValid()) {
			$('#changePasswordForm').submit();
		}
    });

	
});

function isFormValid() {
	
	if($('.alert-error').is(":visible")) {
		return true;
	}
	
	if($('.alert-success').is(":visible")) {
		return true;
	}
	
	var msg = isCustomerFormValid($('#changePasswordForm'));

	if(msg!=null) {//disable submit button
		$('#submitChangePassword').addClass('btn-disabled');
		$('#submitChangePassword').prop('disabled', true);
		$('#formError').html(msg);
		$('#formError').show();
		return false;
	} else {
		$('#submitChangePassword').removeClass('btn-disabled');
		$('#submitChangePassword').prop('disabled', false);
		$('#formError').hide();
		return true;
	}
}

function resetGlobalErrors() {
	$('.alert-error').hide();
}


</script>

	<div id="main-content" class="container clearfix row-fluid">
			<div class="span12 col-md-12 no-padding common-row">
			
			<div class="span8 col-md-8 no-padding">
				<div class="checkout-box">
					<span class="box-title">
						<p class="p-title">
							<s:message code="label.generic.changepassword" text="Change password"/>
						</p>
					</span>
				<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>
				<c:url var="changePassword" value="/shop/customer/changePassword.html"/>
				<div id="formError"  class="alert alert-warning" style="display:none;"></div>
				<form:form method="post" action="${changePassword}" id="changePasswordForm" modelAttribute="password">
					   <form:errors path="*" cssClass="alert alert-error" element="div" />
						<div class="control-group form-group">
							<label class="required control-label" for="currentPassword"><s:message code="label.customer.currentpassword" text="Current password"/></label>
							<div class="controls">
							   <s:message code="currentpassword.not.empty" text="Current password is required " var="msgCurrentPassword"/>
							   <form:password path="currentPassword" cssClass="span3 input-large required form-control form-control-md" id="currentPassword" title="${msgCurrentPassword}"/>
							   <form:errors path="currentPassword" cssClass="error" />
								
							</div>
						 </div>
						 <div class="control-group form-group">
							<label class="required control-label" for="password"><s:message code="label.customer.newpassword" text="New password"/></label>
							<div class="controls">
							   <s:message code="newpassword.not.empty" text="New password is required" var="msgPassword"/>
							   <form:password path="password" cssClass="span3 required password form-control form-control-md" id="password" title="${msgPassword}"/>
							   <form:errors path="currentPassword" cssClass="error" />
								
							</div>
						 </div>
						 <div class="control-group form-group">
							<label class="required control-label" for="repeatPassword"><s:message code="label.customer.repeatpassword" text="Repeat password"/></label>
							<div class="controls">
							   <s:message code="label.customer.repeatpassword" text="Current password is required" var="msgRepeatPassword"/>
							   <form:password path="checkPassword" cssClass="span3 required checkPassword form-control form-control-md" id="checkPassword" title="${msgRepeatPassword}"/>
							   <form:errors path="currentPassword" cssClass="error" />
								
							</div>
						 </div>
						
						<div class="form-actions">
							<input id="submitChangePassword" class="btn btn-large btn-disabled template-btn" type="submit" name="changePassword" value="<s:message code="menu.change-password" text="Change password"/>" disabled="">
						</div>
						
						
				</form:form>


			</div>
			</div>
			<div class="span4 col-md-4">
			 	<jsp:include page="/pages/shop/common/customer/customerProfileMenu.jsp" />
			 	<jsp:include page="/pages/shop/common/customer/customerOrdersMenu.jsp" />
			 </div>
			</div>
			<!--close .span12-->
	</div>
	<!--close .container "main-content" -->