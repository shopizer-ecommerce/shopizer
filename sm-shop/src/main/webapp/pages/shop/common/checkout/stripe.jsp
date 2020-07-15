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

			<!-- https://stripe.com/docs/tutorials/forms -->

			<script type="text/javascript" src="https://js.stripe.com/v2/"></script>

			<script type="text/javascript">
				  Stripe.setPublishableKey('<c:out value="${requestScope.paymentMethod.informations.integrationKeys['publishableKey']}" escapeXml="false"/>');
			
				  
				  function initStripePayment() {
					  
						    var $form = $('#checkoutForm');
						    
						    try {

						    	// Disable the submit button to prevent repeated clicks
						    	$form.find('button').prop('disabled', true);

						    	Stripe.card.createToken($form, stripeResponseHandler);

						    
						    } catch(err) {
						    	
						    	console.log('Got an error ' + err.message);
						    	hideSMLoading('#pageContainer');
						    	//log(err.message);
						    	showResponseErrorMessage(err.message);
						    }
				  }; 
				  
				  
				  function stripeResponseHandler(status, response) {
					  var $form = $('#checkoutForm');
					  
					  if (response.error) {
					    // Show the errors on the form
					    var orderValidationMessage = getOrderValidationMessage(response.error.code);
					    
					    //log('Validation message ' + orderValidationMessage);
					    
					    if(orderValidationMessage == '') {
					    	orderValidationMessage = error.message;
					    }
					    showResponseErrorMessage(orderValidationMessage);
					    hideSMLoading('#pageContainer');
					    $form.find('button').prop('disabled', false);
					  } else {
					    // response contains id and card, which contains additional card details
					    var token = response.id;
					    // Insert the token into the form so it gets submitted to the server
					    var tokenField = '<input type="hidden" name="payment[\'stripe_token\']" value="' + token +'" /><input type="hidden" name="payment[\'null_creditcard\']" value="null_creditcard"/>';
					    $form.append(tokenField);
					    $('#creditcard_card_number').val('');
					    //log(tokenField);
					    // and submit
					    
					    //$form.get(0).submit();
					    submitForm();
					  }
					};
					<c:if test="">
					</c:if>
				  
				  
				  
		   </script> 


          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.usecredicard" text="Use Credit Card"/></label>
            <div class="controls">
               <jsp:include page="/pages/shop/common/checkout/selectedPayment.jsp" />
            </div>
          </div>
          
          <jsp:include page="/pages/shop/common/checkout/${creditCardInformationsPage}.jsp" />
		 