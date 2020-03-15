<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


<script>


$(document).ready(function() {


	
	<c:choose>
	<c:when test="${origin.state!=null && origin.state!=''}">
		$('.zone-list').hide();          
		$('#stateprovince').show(); 
		$('#stateprovince').val('<c:out value="${origin.state}"/>');
	</c:when>
	<c:otherwise>
		$('.zone-list').show();           
		$('#stateprovince').hide();
		getZones('<c:out value="${origin.country.isoCode}" />'); 
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
	$.ajax({
	  type: 'POST',
	  url: '<c:url value="/admin/reference/provinces.html"/>',
	  data: 'countryCode=' + countryCode,
	  dataType: 'json',
	  success: function(response){

			var status = isc.XMLTools.selectObjects(response, "/response/status");
			if(status==0 || status ==9999) {
				
				var data = isc.XMLTools.selectObjects(response, "/response/data");
				if(data && data.length>0) {
					
					$('.zone-list').show();  
					$('#stateprovince').hide();
					$(".zone-list").addItems(data);
					<c:if test="${origin.zone!=null}">
						$('.zone-list').val('<c:out value="${origin.zone.code}"/>');
						$('#stateprovince').val('');
					</c:if>
				} else {
					$('.zone-list').hide();             
					$('#stateprovince').show();
					<c:if test="${origin.merchantStore!=null}">
						$('#stateprovince').val('<c:out value="${origin.state}"/>');
					</c:if>
				}
			} else {
				$('.zone-list').hide();             
				$('#stateprovince').show();
			}

	  
	  },
	  error: function(xhr, textStatus, errorThrown) {
	  	alert('error ' + errorThrown);
	  }
	  
	});
}



</script>


<div class="tabbable">


				<jsp:include page="/common/adminTabs.jsp" />
				
				<h3><s:message code="label.shipping.origin.title" text="Shipping address origin" /></h3>	
				<br/>
				<p>
					<s:message code="label.shipping.origin.description" text="Used for the calculation of the distance between the origin and the destination of a delivery" />
				</p>

				<c:url var="origin" value="/admin/shipping/origin/post.html"/>


				<form:form method="POST" modelAttribute="origin" action="${origin}">
				
					<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
				
        			<div class="control-group">
	                     <label><s:message code="label.entity.active" text="Active"/></label>
	                     <div class="controls">
	                                 <form:checkbox path="active" />
	
	                     </div>
            		</div>

	                  
	                  <div class="control-group">
	                        <label><s:message code="label.generic.address" text="Address"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="address" />
	                                    <span class="help-inline"><form:errors path="address" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.generic.city" text="City"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="city" />
	                                    <span class="help-inline"><form:errors path="city" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.generic.country" text="Country"/></label>
	                        <div class="controls">
	                        					
	                        					<form:select cssClass="country-list highlight" path="country.isoCode">
					  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
				       							</form:select>
	                                   			<span class="help-inline"><form:errors path="country" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  

	                 <div class="control-group">
	                        <label><s:message code="label.generic.stateprovince" text="State / province"/></label>
	                        <div class="controls">
	                        					<form:select cssClass="zone-list highlight" path="zone.code"/>
	                        					<input type="text" class="input-large highlight" id="stateprovince" name="state" /> 
	                                   			<span class="help-inline"><form:errors path="zone.code" cssClass="error" /></span>
	                        </div>
	                  </div>
	                  
	                  <div class="control-group">
	                        <label><s:message code="label.generic.postalcode" text="Postal code"/></label>
	                        <div class="controls">
	                                    <form:input cssClass="input-large highlight" path="postalCode" />
	                                    <span class="help-inline"><form:errors path="postalCode" cssClass="error" /></span>
	                        </div>
	                  </div>

				      <div class="form-actions">
	                  		<div class="pull-right">
	                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit2" text="Submit"/></button>
	                  		</div>
	            	 </div>


      					
				</form:form>
				
				<c:if test="origin!=null">
			     <br/><br/>
			     <c:url var="removeOrigin" value="/admin/shipping/origin/delete.html"/>
		         <form:form method="POST" enctype="multipart/form-data" modelAttribute="origin" action="${removeOrigin}">
									<input type="hidden" name="id" value="${origin.id}" />
			                        <div class="form-actions">
			                            <div class="pull-right">
			                                    <button type="submit" class="btn-danger"><i class="icon-trash icon-large"></i><s:message code="label.generic.remove" text="Remove"/></button>
			                            </div>
			                   		</div>
		
		         </form:form>				
				</c:if>
				

				
				
				
				


</div>