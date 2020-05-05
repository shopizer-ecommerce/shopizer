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



<script type="text/javascript">


$(document).ready(function() {
	
	isFormValid();
	$("input[type='text']").on("change keyup paste", function(){
		isFormValid();
	});
	$("#comment").on("change keyup paste", function(){
		isFormValid();
	});
	
    $("#submitContact").click(function() {
    	sendContact();
    });

});


function sendContact(){
	//$('#pageContainer').showLoading();
	showSMLoading('#pageContainer');
	$(".alert-error").hide();
	$(".alert-success").hide();
	var data = $('#contactForm').serialize();
	//console.log(data);
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/shop/store/${requestScope.MERCHANT_STORE.code}/contact"/>',
	  data: data,
	  cache: false,
	  dataType: 'json',
	  success: function(response){
		  
		    //$('#pageContainer').hideLoading();
		    hideSMLoading('#pageContainer');
		  	if(response.errorMessage!=null && response.errorMessage!='') {
		  		$(".alert-error").show();
				$(".alert-success").hide();
		  		return;
		  	}

			$(".alert-error").hide();
			$(".alert-success").show();
	  },
	  error: function(xhr, textStatus, errorThrown) {
	    	//$('#pageContainer').hideLoading();
	    	hideSMLoading('#pageContainer');
	  		alert('error ' + errorThrown);
	  }

	});
	
}


 


 function isFormValid() {
		var $inputs = $('#contactForm').find(':input');
		var valid = true;
		var firstErrorMessage = null;
		$inputs.each(function() {
			if($(this).hasClass('required')) {				
				var fieldValid = isFieldValid($(this));
				if(!fieldValid) {
					valid = false;
				}
			}
			//if has class email
			if($(this).hasClass('email')) {	
				var emailValid = validateEmail($(this).val());
				//console.log('Email is valid ? ' + emailValid);
				if(!emailValid) {
					valid = false;
				}
			}
		});
		
		//console.log('Form is valid ? ' + valid);
		if(valid==false) {//disable submit button
			$('#submitContact').addClass('btn-disabled');
			$('#submitContact').prop('disabled', true);
		} else {
			$('#submitContact').removeClass('btn-disabled');
			$('#submitContact').prop('disabled', false);
		}
 }
 
 
 function isFieldValid(field) {
		var validateField = true;
		
		var fieldId = field.prop('id');
		var value = field.val();
		
		//console.log('Check id ' + fieldId + ' and value ' + value);
		if(!emptyString(value)) {
			field.css('background-color', '#FFF');
			return true;
		} else {
			field.css('background-color', '#FFC');
			return false;
		} 
 }



</script>


	<div id="main-content" class="container clearfix">
	<h1 class="contact-title"><s:message code="label.customer.contactus" text="Contact us"/></h1>
		<div id="mainContactUsRow" class="row-fluid common-row">
			
			<div class="span6 col-md-7">  
								
                                <form:form action="#" method="POST" id="contactForm" class="form-horizontal" name="contactForm" modelAttribute="contact">
                                    <div id="store.success" class="alert alert-success" style="display:none;"><s:message code="message.email.success" text="Your message has been sent"/></div>   
	                				<div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.email.success" text="An error occurred while sending your message, pleas try again later"/></div>
                                    <form:errors id="contactForm" path="*" cssClass="alert alert-error" element="div" />
                                    <fieldset>
                                    <div class="control-group form-group">
                                        <label for="inputName" class="control-label"><s:message code="label.entity.name" text="Name"/></label>
                                        <div class="controls">
										   <s:message code="NotEmpty.customer.name" text="Name is required" var="msgName"/>
										   <form:input path="name" cssClass="required form-control form-control-md" id="name" title="${msgName}"/>
										   <form:errors path="name" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="control-group form-group">
                                        <label for="inputEmail" class="control-label"><s:message code="label.generic.email" text="Email address"/></label>
                                        <div class="controls">
                                            <form:input path="email" cssClass="required form-control form-control-md" id="email"/>
                                            <form:errors path="email" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="control-group form-group">
                                        <label for="inputEmail" class="control-label"><s:message code="label.generic.subject" text="Subject"/></label>
                                        <div class="controls">
                                            <form:input path="subject" cssClass="required form-control form-control-md" id="subject"/>
                                            <form:errors path="subject" cssClass="error" />
                                        </div>
                                    </div>
                                    <div class="control-group form-group">
                                        <label class="control-label" for="textarea"><s:message code="label.generic.comments" text="Comments"/></label>
                                        <div class="controls">
                                            <form:textarea path="comment" cssClass="span8 required form-control form-control-md" rows="10" id="comment"/>
                                        </div>
                                    </div>
									<div class="control-group form-group">
										<div class="controls">
											
											<script src="https://www.google.com/recaptcha/api.js?hl=<c:out value="${requestScope.LANGUAGE.code}"/>" async defer></script>

											<div class="g-recaptcha" data-sitekey="<c:out value="${recapatcha_public_key}"/>"></div>

										</div>
									</div>

									<div class="form-actions">
										<input id="submitContact" type="button" value="<s:message code="label.generic.send" text="Send"/>" name="register" class="btn btn-default login-btn btn-large">
									</div>
									</fieldset>
			</form:form>
			

<!-- CUSTOM CONTENT --> 
			<div class="row-fluid common-row" style="margin-top:30px;">
					<div class="contactMapCanvas" id="map_canvas" style="width:600px; height:400px"></div>
			</div>


			
            </div>
<!-- END LEFT-SIDE CONTACT FORM AREA -->


<!-- BEGIN RIGHT-SIDE CONTACT FORM AREA -->
              <div class="contact-info span4 offset1 col-md-4">
									<!-- COMPANY ADDRESS -->   
									<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">
										<jsp:include page="/pages/shop/common/preBuiltBlocks/storeAddress.jsp"/>                                  
									 </c:if>
									 <c:if test="${requestScope.CONTENT['contactUsDetails']!=null}">
									 	<br/>
									 	<sm:pageContent contentCode="contactUsDetails"/>
									 </c:if>
									 <c:if test="${content!=null}">
                                        <p>
                                        	<c:out value="${content.description}" escapeXml="false"/>
                                    	</p>
                                    	<br/>
                                    </c:if>

                     </div>

<!-- END RIGHT-SIDE CONTACT FORM AREA -->

			

<!-- GOOGLE MAP -->  
<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">

<script>

var address = '<c:out value="${requestScope.MERCHANT_STORE.storeaddress}"/> <c:out value="${requestScope.MERCHANT_STORE.storecity}"/> <c:out value="${requestScope.MERCHANT_STORE.zone.code}"/> <c:out value="${requestScope.MERCHANT_STORE.storepostalcode}"/>';
function googleInitialize() {

	if(address!=null) {
		geocoder = new google.maps.Geocoder();
			var mapOptions = {
	  			zoom: 10,
	  			mapTypeId: google.maps.MapTypeId.ROADMAP
			}
			map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
	
			geocoder.geocode( { 'address': address}, function(results, status) {
	  			if (status == google.maps.GeocoderStatus.OK) {
	    			map.setCenter(results[0].geometry.location);
	    			var marker = new google.maps.Marker({
	        				map: map,
	        				position: results[0].geometry.location
	    			});
	 			} else {
	    			alert("Geocode was not successful for the following reason: " + status);
	  			}
			});
	}
}

</script>

<!--Set google map API key -->
<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">
<c:if test="${googleMapsKey != ''}">
	<script src="https://maps.googleapis.com/maps/api/js?key=<c:out value="${googleMapsKey}"/>&libraries=places&callback=googleInitialize" async defer></script>
</c:if>
</c:if>

</c:if>
 </div>
 </div>