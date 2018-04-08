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
<script src="<c:url value="/resources/js/adminFunctions.js" />"></script>

<script type="text/javascript">

$(document).ready(function() {

	$('#priceText').numeric({allow:"."});
	$('#maximumWeight').numeric();

	
	$('#priceText').blur(function() {
		$('#help-priceText').html(null);
		$(this).formatCurrency({ roundToDecimalPlace: 2, eventOnDecimalsEntered: true, symbol: ''});
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
		if (String(cents).length > 2) {
			var errorMsg = priceFormatMessage + ' (0.' + cents + ')';
			$('#help-priceText').html(errorMsg);
		}
	});
	
});	

</script>
				

<div class="tabbable">

  					
  					<jsp:include page="/common/adminTabs.jsp" />


  					<div class="tab-content">

    					<div class="tab-pane active" id="shipping-section">
    					
    							<div class="sm-ui-component">
    							<a href="<c:url value="/admin/shipping/weightBased.html"/>"><s:message code="label.generic.back" text="Back" /></a><br/><br/>
								<h3><s:message code="module.shipping.weightBased" text="module.shipping.weightBased" /> - <c:out value="${customRegion.customRegionName}" /></h3>	
								<br/>

								<c:url var="addPrice" value="/admin/shipping/weightBased/addPrice.html"/>
								<form:form method="POST" commandName="customQuote" action="${addPrice}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								
								
								    <div class="control-group">
                        				<label><s:message code="label.shipping.maximumWeight" text="Maximum weight" /></label>
                        				<div class="controls">
											<input type="text" class="span3" name="maximumWeight" id="maximumWeight" value="0">
                        				</div>
	                                	<span class="help-inline"><form:errors path="maximumWeight" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.generic.price" text="Price" /></label>
                        				<div class="controls">
											<input type="text" class="span3" name="priceText" id="priceText">
                        				</div>
	                                	<span id="help-priceText" class="help-inline"><form:errors path="priceText" cssClass="error" /></span>
	                        		</div>
	                        		<input type="hidden" name="region" value="${customRegion.customRegionName}" />
	                        		
	                        		<div class="form-actions">
                  						<div class="pull-right">
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						</div>
            	 					</div>
								
								
								</form:form>


								
								<br/>
								
			      				
			      				
								 <!-- Listing grid include -->
								 <c:set value="/admin/shipping/weightBasedDetails/page.html?region=${customRegion.customRegionName}" var="pagingUrl" scope="request"/>
								 <c:set value="/admin/shipping/weightBased/removePrice.html?region=${customRegion.customRegionName}" var="removeUrl" scope="request"/>
								 <c:set var="entityId" value="maximumWeight" scope="request"/>
								 <c:set value="/admin/shipping/weightBased/edit.html?customRegionName=${customRegion.customRegionName}" var="afterRemoveUrl" scope="request"/>
								 <c:set var="componentTitleKey" value="module.shipping.weightBased" scope="request"/>
								 <c:set var="gridHeader" value="/pages/admin/shipping/weightBasedDetails-gridHeader.jsp" scope="request"/>
								 <c:set var="canRemoveEntry" value="true" scope="request"/>
				
				            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
								 <!-- End listing grid include -->
			      				
			      				<br/><br/>
			      				<c:url var="removeRegion" value="/admin/shipping/weightBased/deleteRegion.html"/>
		                  		<form:form method="POST" enctype="multipart/form-data" commandName="region" action="${removeRegion}">
									<input type="hidden" name="customRegionName" value="${customRegion.customRegionName}" />
			                        <div class="form-actions">
			                            <div class="pull-right">
			                                    <button type="submit" class="btn-danger"><i class="icon-trash icon-large"></i><s:message code="label.generic.remove" text="Remove"/></button>
			                            </div>
			                   		</div>
		
		                   		</form:form>


      					</div>


   					</div>


  				</div>

			</div>