
<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script
	src="<c:url value="/resources/js/jquery.creditCardValidator.js" />"></script>


<script type="text/javascript">
		
				var img = '<img src="<c:url value="/resources/img/cvv.jpg"/>" width="180">';
				var ccValid = false;
				
				function basicCardValidation() {
					if($('#creditcard_card_holder').val()==='') {
						return false;
					}
					if($('#creditcard_card_number').val()==='') {
						return false;
					}
					if($('#creditcard_card_cvv').val()==='') {
						return false;
					}
					return true;
				}
		
		
				$(document).ready(function() {
					    $("#cvvImage").popover({ title: '<s:message code="label.payment.creditcard.cardcvv" text="Card validation number" />', content: img, html: true });
					    var creditCardDiv = '#creditcard_card_number';
					    //set to invalid by default
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
									if(result.card_type.name==='amex') {//cvv 4 digits
										$('#creditcard_card_cvv').attr('maxlength','4');
										$('#creditcard_card_cvv').attr('pattern','\d{4}');
									} else {//cvv 3 digits
										$('#creditcard_card_cvv').attr('maxlength','3');
										$('#creditcard_card_cvv').attr('pattern','\d{3}');
									}
							} else {
								invalidCreditCardNumber(creditCardDiv);
							}
						},
						{ accept: ['visa', 'mastercard', 'amex'] }
						
						);	
		
				})
				
				function invalidCreditCardNumber(div) {
					$('#creditcard_card_image').html('<i class="fa fa-credit-card"></i>');
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


<!-- CREDIT CARD FORM STARTS HERE -->
<div id="creditCardBox" class="panel panel-default credit-card-box-v2">
	<div class="panel-heading display-table">
		<img class="img-responsive pull-right cc-image"
			src="<c:url value="/resources/img/payment/icons/american-express-straight-32px.png"/>">
		<img class="img-responsive pull-right cc-image"
			src="<c:url value="/resources/img/payment/icons/mastercard-straight-32px.png"/>">
		<img class="img-responsive pull-right cc-image"
			src="<c:url value="/resources/img/payment/icons/visa-straight-32px.png"/>">
	</div>
	<div class="panel-body panel-body-creditcard-v2">
		<form role="form" id="payment-form">
			<div class="row">
				<div class="col-md-12">
					<div class="checkout-form-list">
						<label for="cardNumber"><s:message
								code="label.payment.creditcard.cardowner"
								text="Card Holder's Name" /><span class="required">*</span>		
						</label>
						<s:message code="NotEmpty.order.creditcard.name"
							text="Credit card holder's name is required"
							var="msgCardHolderName" />
						<input type="text" id="creditcard_card_holder"
							name="payment['creditcard_card_holder']"
							class="required form-control form-control-100"
							title="${msgCardHolderName}"
							value="${order.payment['creditcard_card_holder']}">
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<div class="checkout-form-list">
						<label class="control-label"><s:message
								code="label.payment.creditcard.cardnumber" text="Card number" /><span class="required">*</span>
						<c:if test="${requestScope.CONFIGS['testMode']==true}">
						<p><strong>For testing payment use card number 5555555555554444 and any cvv 3 digits</strong></p>
						</c:if>
						</label>
						<div class="input-group">
							<s:message code="NotEmpty.order.creditcard.number"
								text="A valid credit card number is required"
								var="msgCardNumber" />
							<input id="creditcard_card_number" type="text"
								class="form-control required"
								name="payment['creditcard_card_number']"
								title="${msgCardNumber}" autocomplete="off" data-stripe="number" required />
							<span id="creditcard_card_image" class="input-group-addon">
								<!--<i class="fa fa-credit-card"></i>-->
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="row">

				<div class="col-md-12">
					<div class="checkout-form-list">
						<label class="control-label"><s:message
								code="label.payment.creditcard.cardexpiry"
								text="Card expiry year" /><span class="required">*</span></label>
						<div class="col-xs-4 no-padding-left">
							<select id="creditCardDays"
								name="payment['creditcard_card_expirationmonth']"
								class="input-large form-control width-75"
								data-stripe="exp-month"></select>
						</div>
						<div class="col-xs-4 no-padding-left">
							<select id="creditCardYears"
								name="payment['creditcard_card_expirationyear']"
								class="input-large form-control width-75" data-stripe="exp-year"></select>
						</div>
					</div>
				</div>
			</div>
			<div class="row margin-top-30">
				<div class="col-md-12">
					<div class="checkout-form-list">
						<label class="control-label"><s:message
								code="label.payment.creditcard.cardcvv"
								text="Card validation number" /><span class="required">*</span></label>
						<s:message code="NotEmpty.order.creditcard.cvv"
							text="Credit card validation digit is required" var="msgCardCvv" />
						<input type="text" id="creditcard_card_cvv"
							name="payment['creditcard_card_cvv']"
							class="required form-control form-control-25"
							autocomplete="off" maxlength="3" pattern="\d{3}"
							title="${msgCardCvv}" data-stripe="cvc">
						<a href="#!" id="cvvImage" rel="popover">
							<label class="control-label">
								<s:message code="label.payment.creditcard.whatiscvv" text="What is a credit card validation number?" />
							</label>
						</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- CREDIT CARD FORM ENDS HERE -->


<input type="hidden" name="payment['creditcard_card_type']"
	id="creditcard_type" value="" />
	
<script>$.ajax({url: "<c:url value="/shop/reference/creditCardDates.html"/>",type: "GET",success: function(data){populateData($('#creditCardYears'), data, '${order.payment['creditcard_card_expirationyear']}');	}})</script>
<script>$.ajax({url: "<c:url value="/shop/reference/monthsOfYear.html"/>",type: "GET",success: function(data){populateData($('#creditCardDays'),data, '${order.payment['creditcard_card_expirationmonth']}');	}})</script>
	