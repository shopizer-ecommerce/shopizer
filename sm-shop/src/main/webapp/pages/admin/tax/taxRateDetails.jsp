<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>	
<script type="text/javascript">
var priceFormatMessage = '<s:message code="message.price.cents" text="Wrong format" />';
</script>

	<script src="<c:url value="/resources/js/jquery.formatCurrency-1.4.0.js" />"></script>
	<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>			
				
<script>


$(document).ready(function() {
	
	$('#code').alphanumeric();
	$('#taxPriority').numeric();
	$('#rateText').numeric({allow:"."});
	getZones('<c:out value="${taxRate.country.isoCode}"/>');
	
	$(".country-list").change(function() {
		getZones($(this).val());
    })
    
    
	$('#rateText').blur(function() {
		$('#help-rateText').html(null);
		$(this).formatCurrency({ roundToDecimalPlace: 3, eventOnDecimalsEntered: true, symbol: ''});
	})
	.keyup(function(e) {
			var e = window.event || e;
			var keyUnicode = e.charCode || e.keyCode;
			if (e !== undefined) {
				switch (keyUnicode) {
					case 16: break; // Shift
					case 17: break; // Ctrl
					case 18: break; // Alt
					case 27: this.value = ''; break; // Esc: clear entry
					case 35: break; // End
					case 36: break; // Home
					case 37: break; // cursor left
					case 38: break; // cursor up
					case 39: break; // cursor right
					case 40: break; // cursor down
					case 78: break; // N (Opera 9.63+ maps the "." from the number key section to the "N" key too!) (See: http://unixpapa.com/js/key.html search for ". Del")
					case 110: break; // . number block (Opera 9.63+ maps the "." from the number block to the "N" key (78) !!!)
					case 190: break; // .
					default: $(this).formatCurrency({ colorize: true, negativeFormat: '-%s%n', roundToDecimalPlace: -1, eventOnDecimalsEntered: true, symbol: ''});
				}
			}
		})
	.bind('decimalsEntered', function(e, cents) {
		if (String(cents).length > 3) {
			var errorMsg = priceFormatMessage + ' (0.' + cents + ')';
			$('#help-rateText').html(errorMsg);
		}
	});
    
    

});

$.fn.addItems = function(data) {
    $(".zone-list > option").remove();
        return this.each(function() {
            var list = this;
            $.each(data, function(index, itemData) {
                var option = new Option(itemData.name, itemData.id);
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
					$('#stateProvince').hide();
					$(".zone-list").addItems(data);
					<c:if test="${taxRate.zone!=null}">
						$('.zone-list').val('<c:out value="${taxRate.zone.id}"/>');
						$('#stateProvince').val('');
					</c:if>
				} else {
					$('.zone-list').hide();             
					$('#stateProvince').show();
					<c:if test="${taxRate.stateProvince!=null}">
						$('#stateProvince').val('<c:out value="${taxRate.stateProvince}"/>');
					</c:if>

				}
			} else {
				$('.zone-list').hide();             
				$('#stateProvince').show();
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
  					
  					 	<div class="tab-content">

    						<div class="tab-pane active" id="taxrates-section">
								<a href="<c:url value="/admin/tax/taxrates/list.html"/>"><s:message code="label.generic.back" text="Back" /></a><br/><br/>
								<div class="sm-ui-component">	
								<h3><s:message code="menu.taxrates.rate" text="Tax rate" /></h3>	
								<br/>

								<c:url var="saveTaxRate" value="/admin/tax/taxrates/update.html"/>

								<form:form method="POST" modelAttribute="taxRate" action="${saveTaxRate}">	
				      				<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
												
				      			  	
				                 
				                    <div class="control-group">
				                        <label><s:message code="label.country" text="Country"/></label>
					                        <div class="controls">
		                        					<form:select path="country.isoCode" cssClass="country-list highlight">
						  								<form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
					       							</form:select>
					                        </div>
				                    </div>

	                 				<div class="control-group">
				                        <label><s:message code="label.storezone" text="Store state / province"/></label>
				                        <div class="controls">
				                        					<form:select cssClass="zone-list " path="zone.id"/>
				                        					<input type="text" class="input-large" value="${taxRate.stateProvince}" id="stateProvince" name="stateProvince" /> 
				                                   			<span class="help-inline"><form:errors path="zone.code" cssClass="error" /></span>
				                        </div>
	                  				</div>
	                  				
	                  				<c:forEach items="${taxRate.descriptions}" var="description" varStatus="counter">

				                        <div class="control-group">
				
				                              <label class="required"><s:message code="menu.taxrates.name" text="Tax name"/> (<c:out value="${description.language.code}"/>)</label>
				                              <div class="controls">
				                                          <form:input cssClass="input-large highlight" id="name${counter.index}" path="descriptions[${counter.index}].name"/>
				                                          <span class="help-inline"><form:errors path="descriptions[${counter.index}].name" cssClass="error" /></span>
				                              </div>
				
				                       </div>
				                       <form:hidden path="descriptions[${counter.index}].id" />
				                       <form:hidden path="descriptions[${counter.index}].language.id" />
				                       <form:hidden path="descriptions[${counter.index}].language.code" />
				                       <form:hidden path="descriptions[${counter.index}].taxRate.id" />
				                       
				                    </c:forEach>
				                    
				                    <div class="control-group">
				                        <label class="required"><s:message code="menu.taxrates.code" text="Code"/></label>
				
				                        <div class="controls">
				                                    <form:input id="code" cssClass="highlight" path="code"/>
				                                    <span class="help-inline"><form:errors path="code" cssClass="error" /></span>
				                        </div>
                  				   </div>
				                    
				                   <div class="control-group">
				                        <label class="required"><s:message code="menu.taxrates.rate" text="Rate"/></label>
				
				                        <div class="controls">
				                                    <form:input id="rateText" cssClass="highlight" path="rateText"/>
				                                    <span id="help-rateText" class="help-inline"><form:errors path="rateText" cssClass="error" /></span>
				                        </div>
                  				   </div>
                  				   
                  				   <div class="control-group">
                        				<label><s:message code="label.tax.compound" text="Compound" /></label>
                        				<div class="controls">
                                    		<form:checkbox id="piggyback" path="piggyback" /><br/>
					       					<span class="help-inline"><form:errors path="piggyback" cssClass="error" /></span>
                        				</div>
                  				   </div>
                  				   
                  				   
                  				   <div class="control-group">
				                        <label class="required"><s:message code="label.entity.order" text="Priority"/></label>
				
				                        <div class="controls">
				                                    <form:input id="taxPriority" cssClass="highlight" path="taxPriority"/>
				                                    <span class="help-inline"><form:errors path="taxPriority" cssClass="error" /></span>
				                        </div>
                  				   </div>
				                    
				                    <div class="control-group">
				                        <label class="required"><s:message code="label.tax.taxclass.name" text="Tax class name"/></label>
					                        <div class="controls">
					                        		<form:select path="taxClass.id" cssClass="highlight">
						  								<form:options items="${taxClasses}" itemValue="id" itemLabel="code"/>
					       							</form:select>
					                        </div>
				                    </div>
				                    


				                  	<form:hidden path="merchantStore.id" value="${requestScope.store.id}" />
				                  	<form:hidden path="id" />
							
							        <div class="form-actions">
				                  		<div class="pull-right">
				                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
				                  		</div>
				            	    </div>
				 
				            	 </form:form>
				            	 

				     
	      			     
      					</div>
      					



   					</div>


  					</div>

				</div>		      			     