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

<style>
/* Uses Bootstrap stylesheets for styling, see linked CSS*/
body {
  background-color: #fff;
}

.panel {
  width: 80%;
  margin: 2em auto;
}

.bootstrap-basic {
  background: white;
}

.panel-body {
  width: 90%;
  margin: 2em auto;
}

.helper-text {
  color: #8A6D3B;
  font-size: 12px;
  margin-top: 5px;
  height: 12px;
  display: block;
}

/* Braintree Hosted Fields styling classes*/
.braintree-hosted-fields-focused { 
  border: 1px solid #0275d8;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(102,175,233,.6);
}


.braintree-hosted-invalid {
  /*border: 1px solid #ff0000 !important;*/
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(100,100,0,.6);
}

.braintree-hosted-fields-focused.focused-invalid {
  border: 1px solid #ff0000 !important;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075),0 0 8px rgba(100,100,0,.6);
}

@media (max-width: 670px) {
  .btn {
    white-space: normal;
  }
}
</style>

<script type="text/javascript">

function populateData(div, data, defaultValue) {
	$.each(data, function() {
	    div.append($('<option/>').val(this).text(this));
	});
    if(defaultValue && (defaultValue!=null && defaultValue!='')) {
    	div.val(defaultValue);
    }
}

</script>

			<script src="<c:url value="/resources/js/jquery.creditCardValidator.js" />"></script>
		

			<script src="https://js.braintreegateway.com/web/3.27.0/js/client.min.js"></script>  
			<script src="https://js.braintreegateway.com/web/3.27.0/js/hosted-fields.min.js"></script>

			
			<!--<div style="display:none;">-->		
				<!-- Bootstrap inspired Braintree Hosted Fields example -->
				<div id="creditCardBoxBraintree" class="panel panel-default bootstrap-basic credit-card-box-v2">
				  <form class="panel-body">
				  <div class="row" style="margin-left:15px !important;margin-right:5px !important;margin-top:15px !important;margin-bottom:15px !important">
				    	<div class="panel-heading display-table">
							<img class="img-responsive pull-right cc-image"
								src="<c:url value="/resources/img/payment/icons/american-express-straight-32px.png"/>">
							<img class="img-responsive pull-right cc-image"
								src="<c:url value="/resources/img/payment/icons/mastercard-straight-32px.png"/>">
							<img class="img-responsive pull-right cc-image"
								src="<c:url value="/resources/img/payment/icons/visa-straight-32px.png"/>">
						</div>
					</div>
				    <div class="row" style="margin-left:5px !important;margin-right:5px !important;margin-top:15px !important;margin-bottom: 0px !important">
				      <div class="form-group col-sm-12">
				        <label for="cardNumber"><s:message
								code="label.payment.creditcard.cardnumber"
								text="Card number" /><span class="required">*</span></label>
						<s:message code="NotEmpty.order.creditcard.cardnumber"
							text="Card number is required"
							var="msgCardNumber" />
				        <!--  Hosted Fields div container -->
				        <div class="form-control" id="creditcard_card_number" name="payment['creditcard_card_number']" autocomplete="off"></div>
				        <span class="helper-text"></span>
				      </div>
				    </div>
				    <div class="row" style="margin-left:5px !important;margin-right:5px !important;margin-top:0px !important;margin-bottom:0px !important">				          
				          <div class="form-group col-sm-6">
				            <!--  Hosted Fields div container -->
				          	<label for="expiryMonth"><s:message
								code="label.payment.creditcard.cardexpiry.month"
								text="Card expiry month" /><span class="required">*</span></label>
				            <!--<select id="expiration-month-select" name="payment['creditcard_card_expirationmonth']" class="input-small form-control form-control-25"></select>-->
				          	<div class="form-control" id="expiration-month" style=""></div>
				          	<span class="helper-text"></span>
				          </div>
				          <div class="form-group col-sm-6">
				            <!--  Hosted Fields div container -->
				            <label for="expiryYear"><s:message
								code="label.payment.creditcard.cardexpiry.year"
								text="Card expiry year" /><span class="required">*</span></label>
				            <!--<select id="expiration-year-select" name="payment['creditcard_card_expirationyear']" class="input-medium form-control form-control"></select>-->
				          	<div class="form-control" id="expiration-year" style=""></div>
				          	<span class="helper-text"></span>
				          </div>
				    </div>
				    <div class="row" style="margin-left:5px !important;margin-right:5px !important;margin-top:0px !important;margin-bottom:0px !important">
				      <div class="form-group col-sm-6">
				        <label class="control-label"><s:message code="label.payment.creditcard.cardcvv" text="Card validation number" /></label>
				        <s:message code="NotEmpty.order.creditcard.cvv" text="Credit card validation digit is required" var="msgCardCvv"/>
				        <!--  Hosted Fields div container -->
				        <div class="form-control" id="cvv" name="payment['creditcard_card_cvv']"></div>
				      </div>
				      <div class="form-group col-sm-6">
				        <label class="control-label"><s:message code="label.generic.postalcode" text="Card owner postal code" /></label>
				        <!--  Hosted Fields div container -->
				        <div class="form-control" id="postal-code"></div>
				      </div>
				    </div>
				
				    
<%-- 				    <button value="submit" style="margin-bottom:0px !important" id="submit" class="btn btn-success btn-lg center-block">Pay with <span id="card-type">Card</span></button>
				    <br/> --%>
				  </form>	
			
			</div>
			<!--</div>-->
			
			
	<script type="text/javascript">
			var hfInstance = null;
			var client = null;

			$( document ).ready(function() {
				<!-- replace generic payment box -->
				//$( "#creditCardBox" ).replaceWith( $( "#creditCardBoxBraintree" ) );
				
				var $form = $('#checkoutForm');

				$form.find('button').prop('disabled', false);
				
				braintree.client.create({
					  authorization: '<c:out value="${requestScope.paymentMethod.informations.integrationKeys['tokenization_key']}" escapeXml="false"/>'
					}, function (err, clientInstance) {
					  if (err) {
					    console.error(err);
					    return;
					  }
					  client = clientInstance;
					  braintree.hostedFields.create({
					    client: clientInstance,
					    styles: {
					      'input': {
					        'font-size': '14px',
					        'font-family': 'helvetica, tahoma, calibri, sans-serif',
					        'color': '#3a3a3a'
					      },
					      ':focus': {
					        'color': 'black'
					      }
					    },
					    fields: {
						      number: {
							        selector: '#creditcard_card_number',
							        placeholder: ''
							      },
							      cvv: {
							        selector: '#cvv',
							        placeholder: ''
							      },
							      expirationMonth: {
							        selector: '#expiration-month',
							        placeholder: 'MM'
							      },
							      expirationYear: {
							        selector: '#expiration-year',
							        placeholder: 'YY'
							      },
							      postalCode: {
							        selector: '#postal-code',
							        placeholder: ''
							      }
							    }
					  }, function (err, hostedFieldsInstance) {
					    if (err) {
					      console.error(err);
					      return;
					    }
					    
					    hfInstance = hostedFieldsInstance;
					    
					    //$('#expiration-year').val($('#expiration-year-select').val().substring(2, 4));
					    //$('#expiration-month').val($('#expiration-month-select').val());
					    
					    //console.log('CC expiry year ' + $('#expiration-year').val());
					    //console.log('CC expiry month ' + $('#expiration-month').val());
	
					    hostedFieldsInstance.on('validityChange', function (event) {
					      var field = event.fields[event.emittedBy];
					      $form.find('button').prop('disabled', false);
						  //console.log('Validity change ' + event.emittedBy);
					      if (field.isValid) {
					    	  console.log('Field valid ' + event.emittedBy);
					        if (event.emittedBy === 'expirationMonth' || event.emittedBy === 'expirationYear') {
					          console.log('Field type ' + event.emittedBy);
					          if (!event.fields.expirationMonth.isValid || !event.fields.expirationYear.isValid) {
					            return;
					          }
					        } else if (event.emittedBy === 'number') {
					          $('#creditcard_card_number').next('span').text('');
					        } else {
					        	console.log(event.emittedBy);
					        }
					                
					        // Remove any previously applied error or warning classes
					        $(field.container).parents('.form-group').removeClass('has-warning');
					        $(field.container).parents('.form-group').removeClass('has-success');
					        // Apply styling for a valid field
					        $(field.container).parents('.form-group').addClass('has-success');
					      } else if (field.isPotentiallyValid) {
					        // Remove styling  from potentially valid fields
					        $(field.container).parents('.form-group').removeClass('has-warning');
					        $(field.container).parents('.form-group').removeClass('has-success');
					        if (event.emittedBy === 'number') {
					          $('#creditcard_card_number').next('span').text('');
					        }
					        if (event.emittedBy === 'expirationMonth') {
						          $('#expiration-month').next('span').text('');
						    }
					        if (event.emittedBy === 'expirationYear') {
						          $('#expiration-year').next('span').text('');
						    }
					      } else {
					    	  console.log('Field invalid ' + event.emittedBy);
					        // Add styling to invalid fields
					        $(field.container).parents('.form-group').addClass('has-warning');
					        if (event.emittedBy === 'expirationMonth') {
					        	$form.find('button').prop('disabled', true);
					        	$('#expiration-month').next('span').text(getOrderValidationMessage('invalid_expiry_month'));
					        	
					        }
					        if (event.emittedBy === 'expirationYear') {
					        	$form.find('button').prop('disabled', true);
					        	$('#expiration-year').next('span').text(getOrderValidationMessage('invalid_expiry_year'));
					        }
					        // Add helper text for an invalid card number
					        if (event.emittedBy === 'number') {
					        	$form.find('button').prop('disabled', true);
					          $('#creditcard_card_number').next('span').text(getOrderValidationMessage('invalid_number'));
					        }
					      }
					      
					      //$('#expiration-year').val($('#expiration-year-select').val().substring(2, 4));
					      //$('#expiration-month').val($('#expiration-month-select').val());
					      
					      //console.log('CC expiry year ' + $('#expiration-year').val());
					      //console.log('CC expiry month ' + $('#expiration-month').val());
					      
					    });
	
					    hostedFieldsInstance.on('cardTypeChange', function (event) {
					      // Handle a field's change, such as a change in validity or credit card type
					      if (event.cards.length === 1) {
					        $('#card-type').text(event.cards[0].niceType);
					      } else {
					        $('#card-type').text('Card');
					      }
					      
					      //$('#expiration-year').val($('#expiration-year-select').val().substring(2, 4));
					      //$('#expiration-month').val($('#expiration-month-select').val());
					      
					      //console.log('CC expiry year ' + $('#expiration-year').val());
					      //console.log('CC expiry month ' + $('#expiration-month').val());
					      
					    });
					    
/* 					    $('#expiration-year-select').on('change', function() {
					    	  var $option = $(this).find('option:selected');
					    	  var value = $option.val();
					    	  $('#expiration-year').val(value);
						      $('#expiration-month').val($('#expiration-month-select').val());
						      
						      console.log('CC expiry year ' + $('#expiration-year').val());
						      console.log('CC expiry month ' + $('#expiration-month').val());
					    }) */
					    
/* 					    $('#expiration-month-select').on('change', function() {
					    	  var $option = $(this).find('option:selected');
					    	  var value = $option.val();
					    	  $('#expiration-year').val($('#expiration-year-select').val().substring(2, 4));
						      $('#expiration-month').val(value);
						      
						      console.log('CC expiry year ' + $('#expiration-year').val());
						      console.log('CC expiry month ' + $('#expiration-month').val());
					    }) */
	
/* 					    $('#submit').submit(function (event) {
					      event.preventDefault();
					      hostedFieldsInstance.tokenize(function (err, payload) {
					        if (err) {
					          $form.find('button').prop('disabled', false);
					          alert('Error ' + err);
					          log(err);
					          return;
					        }
					        
						    var token = payload.nonce;
						    // Insert the token into the form so it gets submitted to the server
						    var tokenField = '<input type="hidden" name="payment[\'paymentToken\']" value="' + token +'" /><input type="hidden" name="payment[\'null_creditcard\']" value="null_creditcard"/>';
						    $form.append(tokenField);
						    log(tokenField);
						    alert('PaymentToken ' + token);
						    // and submit
						    //$form.get(0).submit();
	
					        // This is where you would submit payload.nonce to your server
					        //alert('Submit your nonce to your server here!');
					      });
					    }); */
					  });
					});
			}); 
			
			function initBraintreePayment(div, data, defaultValue) {

				  var $form = $('#checkoutForm');
				  $form.find('button').prop('disabled', true);
				  //console.log('expiryMonth ' + $('.expirationMonth').val());
				  //	console.log('expiryYear ' + $('.expirationYear').val());
			      hfInstance.tokenize(function (err, payload) {
				        if (err) {
				          hideSMLoading('#pageContainer');
				          console.log(err.message);
				          showResponseErrorMessage(getOrderValidationMessage('error_creditcard'));
				          $form.find('button').prop('disabled', false);
				          log(err);
				          return;
				        }
				        
					    var token = payload.nonce;
					    // Insert the token into the form so it gets submitted to the server
					    var tokenField = '<input type="hidden" name="payment[\'paymentToken\']" value="' + token +'" /><input type="hidden" name="payment[\'null_creditcard\']" value="null_creditcard"/>';
					    $form.append(tokenField);
					    log(tokenField);
					    //alert('PaymentToken ' + token);
					    // and submit
					    hideSMLoading('#pageContainer');
					    $form.submit();
			      });
			      
			}
			

		</script> 


          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.usecredicard" text="Use Credit Card"/></label>
            <div class="controls">
               <jsp:include page="/pages/shop/common/checkout/selectedPayment.jsp" />
            </div>
          </div>
    
   			<script>$.ajax({url: "<c:url value="/shop/reference/creditCardDates.html"/>",type: "GET",success: function(data){populateData($('#expiration-year-select'), data, '${order.payment['creditcard_card_expirationyear']}');	}})</script>
			<script>$.ajax({url: "<c:url value="/shop/reference/monthsOfYear.html"/>",type: "GET",success: function(data){populateData($('#expiration-month-select'),data, '${order.payment['creditcard_card_expirationmonth']}');	}})</script>
		
       
		 