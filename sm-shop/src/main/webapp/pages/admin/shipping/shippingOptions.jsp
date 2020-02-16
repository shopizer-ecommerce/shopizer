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

	$('#orderTotalFreeShippingText').numeric({allow:"."});
	$('#handlingFeesText').numeric({allow:"."});

	
	$('#orderTotalFreeShippingText').blur(function() {
		$('#help-orderTotalFreeShippingText').html(null);
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
			$('#help-orderTotalFreeShippingText').html(errorMsg);
		}
	});
	
	
	$('#handlingFeesText').blur(function() {
		$('#help-handlingFeesText').html(null);
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
			$('#help-handlingFeesText').html(errorMsg);
		}
	});
	
});	

</script>


<div class="tabbable">

  					
 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">
  					
  					
  					<div class="tab-pane active" id="shipping-options">


								<div class="sm-ui-component">
								<h3><s:message code="label.shipping.options" text="Shipping options" /></h3>	
								<br/>
								

							<c:url var="saveShippingOptions" value="/admin/shipping/saveShippingOptions.html"/>
							<form:form method="POST" modelAttribute="configuration" action="${saveShippingOptions}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								
									<!--
									<div class="control-group">
                        				<label><s:message code="label.shipping.taxonshipping" text="Apply tax on shipping" /></label>
                        				<div class="controls">
                                    		<form:checkbox id="taxOnShipping" path="taxOnShipping" />
                        				</div>
                  					</div>
                  					-->

                  					<div class="control-group well">
                        				<label><s:message code="label.shipping.freeshippingandhandling=" text="Apply free shipping and handling"/></label>
                        				<div class="controls">
                                    		<form:checkbox id="freeShippingEnabled" path="freeShippingEnabled" /><br/>
                                    		<form:radiobutton id="shipFreeType" path="shipFreeType" value="NATIONAL"/>&nbsp;<s:message code="label.shipping.national" text="National" /><br/>			
											<form:radiobutton id="shipFreeType" path="shipFreeType" value="INTERNATIONAL"/>&nbsp;<s:message code="label.generic.all" text="All" /><br/>
											<form:input cssClass="input-large" id="orderTotalFreeShippingText" path="orderTotalFreeShippingText" />&nbsp;<s:message code="label.shipping.freeshippingamount" text="Order total over" />
                        				</div>
                        				<span id="help-orderTotalFreeShippingText" class="help-inline"><form:errors path="orderTotalFreeShippingText" cssClass="error" /></span>
                  					</div>
                  					
                  					<div class="control-group">
                        				<label><s:message code="label.shipping.handlingfees" text="Handling fees"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" id="handlingFeesText" path="handlingFeesText" />
                        				</div>
	                                	<span id="help-handlingFeesText" class="help-inline"><form:errors path="handlingFeesText" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		<div class="control-group">
                        				<div class="controls">
											<form:radiobutton id="shipOptionPriceType" path="shipOptionPriceType" value="ALL"/>&nbsp;<s:message code="label.shipping.allquotes" text="All quotes" /><br/>			
											<form:radiobutton id="shipOptionPriceType" path="shipOptionPriceType" value="LEAST"/>&nbsp;<s:message code="label.shipping.leastexpensivequotes" text="Least expensive" /><br/>
											<form:radiobutton id="shipOptionPriceType" path="shipOptionPriceType" value="HIGHEST"/>&nbsp;<s:message code="label.shipping.moreexpensivequotes" text="Highest price" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="handlingFeesText" cssClass="error" /></span>
	                        		</div>

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