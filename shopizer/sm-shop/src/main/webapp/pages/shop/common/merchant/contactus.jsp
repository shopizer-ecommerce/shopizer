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

<!--Set google map API key -->
<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">
<script type="text/javascript"
      src="https://maps.googleapis.com/maps/api/js?sensor=true">
</script>
</c:if>

<script type="text/javascript">

var RecaptchaOptions = {
	    theme : 'clean'
};


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
	$('#pageContainer').showLoading();
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
		  
		    $('#pageContainer').hideLoading();
		  	if(response.errorMessage!=null && response.errorMessage!='') {
		  		$(".alert-error").show();
				$(".alert-success").hide();
		  		return;
		  	}

			$(".alert-error").hide();
			$(".alert-success").show();
	  },
	  error: function(xhr, textStatus, errorThrown) {
	    	$('#pageContainer').hideLoading();
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
								
                                <form:form action="#" method="POST" id="contactForm" class="form-horizontal" name="contactForm" commandName="contact">
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
											<!--watch the white space in IOS!-->
											<script type="text/javascript"
												src="http://www.google.com/recaptcha/api/challenge?k=<c:out value="${recapatcha_public_key}"/>&hl=${requestScope.LANGUAGE.code}">
												
											</script>
											<noscript>
												<iframe
													src="http://www.google.com/recaptcha/api/noscript?k=<c:out value="${recapatcha_public_key}"/>&hl=${requestScope.LANGUAGE.code}"
													height="300" width="500" frameborder="0"> </iframe>
												<br />
												<form:textarea path="captchaResponseField" readonly="3"
													cols="40" />
												<form:errors path="captchaResponseField" cssClass="error" />
					
												<input type="hidden" name="captchaChallengeField"
													value="manual_challenge">
											</noscript>
										</div>
									</div>

									<div class="form-actions">
										<input id="submitContact" type="button" value="<s:message code="label.generic.send" text="Send"/>" name="register" class="btn btn-large">
									</div>
									</fieldset>
			</form:form>
			

			
            </div>
<!-- END LEFT-SIDE CONTACT FORM AREA -->


<!-- BEGIN RIGHT-SIDE CONTACT FORM AREA -->
              <div class="contact-info span4 offset1 col-md-4">
									<!-- COMPANY ADDRESS -->   
									<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">                                  
                                     <address>  
									 	<div itemscope itemtype="http://schema.org/Organization"> 
										 	<h2 itemprop="name"><c:out value="${requestScope.MERCHANT_STORE.storename}"/></h2><br/>  
										 	<div itemprop="address" itemscope itemtype="http://schema.org/PostalAddress"> 
											 	<span itemprop="streetAddress"><c:out value="${requestScope.MERCHANT_STORE.storeaddress}"/> <c:out value="${requestScope.MERCHANT_STORE.storecity}"/></span><br/>
											 	<span itemprop="addressLocality"><c:choose><c:when test="${not empty requestScope.MERCHANT_STORE.storestateprovince}"><c:out value="${requestScope.MERCHANT_STORE.storestateprovince}"/></c:when><c:otherwise><script>$.ajax({url: "<c:url value="/shop/reference/zoneName"/>",type: "GET",data: "zoneCode=${requestScope.MERCHANT_STORE.zone.code}",success: function(data){$('#storeZoneName').html(data)}})</script><span id="storeZoneName"><c:out value="${requestScope.MERCHANT_STORE.zone.code}"/></span></c:otherwise></c:choose>,
											 	<span id="storeCountryName"><script>$.ajax({url: "<c:url value="/shop/reference/countryName"/>",type: "GET",data: "countryCode=${requestScope.MERCHANT_STORE.country.isoCode}",success: function(data){$('#storeCountryName').html(data)}})</script></span></span><br/>
											 	<span itemprop="postalCode"><c:out value="${requestScope.MERCHANT_STORE.storepostalcode}"/></span><br/>
											 	<abbr title="Phone"><s:message code="label.generic.phone" text="Phone" /></abbr>: <span itemprop="telephone"><c:out value="${requestScope.MERCHANT_STORE.storephone}"/></span>
											 </div>
									 	</div>
									 </address>
									 </c:if>

                     </div>
<!-- END RIGHT-SIDE CONTACT FORM AREA -->
<!-- CUSTOM CONTENT --> 
			<div class="row-fluid common-row">
                                    <c:if test="${content!=null}">
                                    	<br/>
                                        <p>
                                        	<c:out value="${content.description}" escapeXml="false"/>
                                    	</p>
                                    	<br/>
                                    </c:if>
                                    <br/>
									<div class="contactMapCanvas" id="map_canvas" style="width:600px; height:300px"></div>

			</div>
			

<!-- GOOGLE MAP -->  
<c:if test="${requestScope.CONFIGS['displayStoreAddress'] == true}">


<script>

var address = '<c:out value="${requestScope.MERCHANT_STORE.storeaddress}"/> <c:out value="${requestScope.MERCHANT_STORE.storecity}"/> <c:out value="${requestScope.MERCHANT_STORE.zone.code}"/> <c:out value="${requestScope.MERCHANT_STORE.storepostalcode}"/>';

if(address!=null) {
	geocoder = new google.maps.Geocoder();
		var mapOptions = {
  			zoom: 8,
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

</script>

</c:if>
 </div>
