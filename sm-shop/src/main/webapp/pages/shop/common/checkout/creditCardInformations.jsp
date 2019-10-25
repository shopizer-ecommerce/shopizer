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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

		<script src="<c:url value="/resources/js/jquery.creditCardValidator.js" />"></script>
		
		<script>$.ajax({url: "<c:url value="/shop/reference/creditCardDates.html"/>",type: "GET",success: function(data){populateData($('#creditCardYears'), data, '${order.payment['creditcard_card_expirationyear']}');	}})</script>
		<script>$.ajax({url: "<c:url value="/shop/reference/monthsOfYear.html"/>",type: "GET",success: function(data){populateData($('#creditCardDays'),data, '${order.payment['creditcard_card_expirationmonth']}');	}})</script>
		
		<script type="text/javascript">
		
				var img = '<img src="<c:url value="/resources/img/cvv.jpg"/>" width="180">';
				var ccValid = false;
		
		
				$(document).ready(function() {
					    $("#cvvImage").popover({ title: '<s:message code="label.payment.creditcard.cardcvv" text="Card validation number" />', content: img, html: true });
					    var creditCardDiv = '#creditcard_card_number';
					    invalidCreditCardNumber(creditCardDiv);
						$(creditCardDiv).validateCreditCard(function(result) {
							invalidCreditCardNumber(creditCardDiv);
							if(result.card_type!=null) {
									//log('CC type: ' + result.card_type.name
									//			      + '\nLength validation: ' + result.length_valid
									//			      + '\nLuhn validation: + result.luhn_valid');
									if(result.luhn_valid==true && result.length_valid==true) {
										validCreditCardNumber(creditCardDiv, result);
									} else {
										invalidCreditCardNumber(creditCardDiv);
									}
							} else {
								invalidCreditCardNumber(creditCardDiv);
							}
						},
						{ accept: ['visa', 'mastercard', 'amex'] }
						
						);	
		
				})
				
				function invalidCreditCardNumber(div) {
					$('#creditcard_card_image').html('');
					$(div).removeClass("valid");
					$(div).css('background-color', '#FFC');
					ccValid = false;
				}
				
				function validCreditCardNumber(div, creditCard) {
					$(div).addClass("valid");
					$(div).css('background-color', '#FFF');
					$('#creditcard_type').val(creditCard.card_type.name);
					$('#creditcard_card_image').html('<img src="<c:url value="/resources/img/payment/icons/'+ creditCard.card_type.name +'-straight-32px.png" />"/>');
					ccValid = true;
				}
				
				function isCreditCardValid() {
					return ccValid;
				}
				
				function populateData(div, data, defaultValue) {
					$.each(data, function() {
					    div.append($('<option/>').val(this).text(this));
					});
		            if(defaultValue && (defaultValue!=null && defaultValue!='')) {
		            	div.val(defaultValue);
		            }
				}
		
		</script>

		  <div class="payment-method-box" id="creditCardBox">
          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.cardowner" text="Card Holder's Name" /></label>
            <div class="controls">
              <s:message code="NotEmpty.order.creditcard.name" text="Credit card holder's name is required" var="msgCardHolderName"/>
              <input type="text" id="creditcard_card_holder" name="payment['creditcard_card_holder']" class="input-xlarge required form-control form-control-50" title="${msgCardHolderName}" value="${order.payment['creditcard_card_holder']}">
            </div>
          </div>
          <br/>
          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.cardnumber" text="Card number" /></label>
            <s:message code="NotEmpty.order.creditcard.number" text="A valid credit card number is required" var="msgCardNumber"/>
            <div class="controls input-append">
            <!-- added stripe card-number -->
            <input id="creditcard_card_number" class="input-xlarge valid required form-control form-control-50" type="text" name="payment['creditcard_card_number']" autocomplete="off" title="${msgCardNumber}" data-stripe="number">
          	<span id="creditcard_card_image" class="img-add-on"></span>
          	</div>
          </div>
          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.cardexpiry" text="Card expiry year" /></label>
            <div class="controls">
              <div class="row-fluid">
                <div class="span2">
                  <!-- added stripe expiration month -->
                  <select id="creditCardDays" name="payment['creditcard_card_expirationmonth']" class="input-small form-control form-control-25" data-stripe="exp-month"></select>
                </div>
                <br/>
                <div class="span2">
                  <!-- added stripe expiration year -->
                  <select id="creditCardYears" name="payment['creditcard_card_expirationyear']" class="input-small form-control form-control-25" data-stripe="exp-year"></select>
                </div>
              </div>
            </div>
          </div>
          <br/>
          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.cardcvv" text="Card validation number" /></label>
            <div class="controls">
              <div class="row-fluid">
                <div class="span2">
                  <s:message code="NotEmpty.order.creditcard.cvv" text="Credit card validation digit is required" var="msgCardCvv"/>
                  <!-- added stripe cvc -->
                  <input type="text" id="creditcard_card_cvv" name="payment['creditcard_card_cvv']" class="input-small required form-control form-control-25" autocomplete="off" maxlength="3" pattern="\d{3}" title="${msgCardCvv}" data-stripe="cvc">
                </div>
                <div class="span4">
                  <a href="#!" id="cvvImage" rel="popover"><s:message code="label.payment.creditcard.whatiscvv" text="What is a credit card validation number?" /></a>
                </div>
              </div>
            </div>
          </div>
          </div>

		<input type="hidden" name="payment['creditcard_card_type']" id="creditcard_type" value="" />