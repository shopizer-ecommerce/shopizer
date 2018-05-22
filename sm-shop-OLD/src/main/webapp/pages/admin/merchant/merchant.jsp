<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


    <link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
	<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>


<script>


$(document).ready(function() {

	if($("#code").val()=="") {
		$('.btn').addClass('disabled');
	}

	
	<c:choose>
	<c:when test="${store.storestateprovince!=null && store.storestateprovince!=''}">
		$('.zone-list').hide();          
		$('#storestateprovince').show(); 
		$('#storestateprovince').val('<c:out value="${store.storestateprovince}"/>');
	</c:when>
	<c:otherwise>
		$('.zone-list').show();           
		$('#storestateprovince').hide();
		getZones('<c:out value="${store.country.isoCode}" />'); 
	</c:otherwise>
	</c:choose>

	$(".country-list").change(function() {
		getZones($(this).val());
    })


});

$.fn.addItems = function(data) {
    $(".zone-list > option").remove();
        return this.each(function() {
            var list = this;
            $.each(data, function(index, itemData) {
                var option = new Option(itemData.name, itemData.code);
                list.add(option);
            });
     });
};

function getZones(countryCode){

	var url = '<c:url value="/admin/reference/provinces.html"/>?lang=<c:out value="${requestScope.LANGUAGE.code}"/>';	

	$.ajax({
	  type: 'POST',
	  url: url,
	  data: 'countryCode=' + countryCode,
	  dataType: 'json',
	  success: function(response){

			var status = isc.XMLTools.selectObjects(response, "/response/status");
			if(status==0 || status ==9999) {
				
				var data = isc.XMLTools.selectObjects(response, "/response/data");
				if(data && data.length>0) {
					
					$('.zone-list').show();  
					$('#storestateprovince').hide();
					$(".zone-list").addItems(data);
					<c:if test="${store.zone!=null}">
						$('.zone-list').val('<c:out value="${store.zone.code}"/>');
						$('#storestateprovince').val('');
					</c:if>
				} else {
					$('.zone-list').hide();             
					$('#storestateprovince').show();
					<c:if test="${store.storestateprovince!=null}">
						$('#storestateprovince').val('<c:out value="${store.storestateprovince}"/>');
					</c:if>
				}
			} else {
				$('.zone-list').hide();             
				$('#storestateprovince').show();
			}

	  
	  },
	  error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }
	  
	});
}



function validateCode() {
	$('#checkCodeStatus').html('<img src="<c:url value="/resources/img/ajax-loader.gif" />');
	$('#checkCodeStatus').show();
	var storeCode = $("#code").val();
	var id = $("#id").val();
	checkCode(storeCode,id,'<c:url value="/admin/store/checkStoreCode.html" />');
}

function callBackCheckCode(msg,code) {
	
	if(code==0) {
		$('.btn').removeClass('disabled');
	}
	if(code==9999) {

		$('#checkCodeStatus').html('<font color="green"><s:message code="message.code.available" text="This code is available"/></font>');
		$('#checkCodeStatus').show();
		$('.btn').removeClass('disabled');
	}
	if(code==9998) {

		$('#checkCodeStatus').html('<font color="red"><s:message code="message.code.exist" text="This code already exist"/></font>');
		$('#checkCodeStatus').show();
		$('.btn').addClass('disabled');
	}
	
}


</script>


<div class="tabbable">


				<jsp:include page="/common/adminTabs.jsp" />
				
				<h3><s:message code="label.store.title" text="Merchant store" /></h3>	
				<br/>


				<c:url var="merchant" value="/admin/store/save.html"/>


				<form:form method="POST" commandName="store" action="${merchant}">
				
					<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
				
	      			<div class="control-group">
	                        <label><s:message code="label.storename" text="Name"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="storename" />
	                                    <span class="help-inline"><form:errors path="storename" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storecode" text="Store code"/></label>
	                        <div class="controls">
	                        			<c:choose>
	                        				<c:when test="${store.code=='DEFAULT'}">
	                        					<span class="input-large uneditable-input">${store.code}</span><form:hidden path="code" />
	                        				</c:when>
	                        				<c:otherwise>
	                        					<form:input cssClass="input-large highlight" path="code" onblur="validateCode()"/>
	                        				</c:otherwise>
	                        			</c:choose>
	                        			<span class="help-inline"><div id="checkCodeStatus" style="display:none;"></div><form:errors path="code" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storephone" text="Phone"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="storephone" />
	                                    <span class="help-inline"><form:errors path="storephone" cssClass="error" /></span>
	                        </div>
	
	                  </div>
	                  
	                 <div class="control-group">
	                        <label><s:message code="label.storeemailaddress" text="Email"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="storeEmailAddress" />
	                                    <span class="help-inline"><form:errors path="storeEmailAddress" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storeaddress" text="Address"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large" path="storeaddress" />
	                                    <span class="help-inline"><form:errors path="storeaddress" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storecity" text="City"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="storecity" />
	                                    <span class="help-inline"><form:errors path="storecity" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storecountry" text="Store Country"/></label>
	                        <div class="controls">
	                        					
	                        					<form:select cssClass="country-list highlight" path="country.isoCode">
					  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
				       							</form:select>
	                                   			<span class="help-inline"><form:errors path="country" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  

	                 <div class="control-group">
	                        <label><s:message code="label.storezone" text="Store state / province"/></label>
	                        <div class="controls">
	                        					<form:select cssClass="zone-list highlight" path="zone.code"/>
	                        					<input type="text" class="input-large highlight" id="storestateprovince" name="storestateprovince" value="${store.storestateprovince}" /> 
	                                   			<span class="help-inline"><form:errors path="zone.code" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.storepostalcode" text="Postal code"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="storepostalcode" />
	                                    <span class="help-inline"><form:errors path="storepostalcode" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.supportedlanguages" text="Supported languages"/></label>
	                        <div class="controls">

	                        					<form:checkboxes cssClass="highlight" items="${languages}" itemValue="code" itemLabel="code" path="languages" /> 
	                                   			<span class="help-inline"><form:errors path="languages" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.defaultlanguage" text="Default language"/></label>
	                        <div class="controls">

	                        					<form:select items="${languages}" itemValue="id" itemLabel="code" path="defaultLanguage.id"/> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.currency" text="Currency"/></label>
	                        <div class="controls">

	                        					<form:select items="${currencies}" itemValue="id" itemLabel="code"  path="currency.id"/> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.store.currency.format" text="National currency format"/></label>
	                        <div class="controls">

	                        					<form:checkbox path="currencyFormatNational" /> 
	                                   			<span class="help-inline"><s:message code="label.store.currency.format.help" text="National currency format ex $1,345.79 or International currency format ex USD1,345.79"/></span>
	                        </div>
	                  </div>
	  
	  
	  	               <div class="control-group">
	                        <label><s:message code="label.store.weightunit" text="Weight units"/></label>
	                        <div class="controls">

	                        					<form:select items="${weights}" path="weightunitcode" itemValue="code" itemLabel="name"/> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.store.sizeunit" text="Size units"/></label>
	                        <div class="controls">

	                        					<form:select items="${sizes}" path="seizeunitcode" itemValue="code" itemLabel="name"/> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.store.inbusinesssince" text="Web site operating since"/></label>
	                        <div class="controls">
	                        					<input id="dateBusinessSince" name="dateBusinessSince" value="${store.dateBusinessSince}" class="small" type="text" data-date-format="<%=com.salesmanager.core.business.constants.Constants.DEFAULT_DATE_FORMAT%>" data-datepicker="datepicker"> 
	                                   			 <span class="help-inline"><form:errors path="dateBusinessSince" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.store.useCache" text="Use cache"/></label>
	                        <div class="controls">

	                        					<form:checkbox path="useCache" /> 
	                                   			<span class="help-inline"></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.store.baseurl" text="Store base url"/></label>
	                        <div class="controls">
	                                    <span class="add-on">
											http://
										</span>
										<form:input cssClass="input-medium highlight" path="domainName" />
	                                    <span class="help-inline"><form:errors path="domainName" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <form:hidden path="id" />
	                  <form:hidden path="storeLogo" />
	                  <form:hidden path="storeTemplate" />

				      <div class="form-actions">
	                  		<div class="pull-right">
	                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit2" text="Submit"/></button>
	                  		</div>
	            	 </div>


      					
				</form:form>
				
				
				
				

				
				
				
				


</div>