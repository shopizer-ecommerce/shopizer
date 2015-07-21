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

						    // Disable the submit button to prevent repeated clicks
						    $form.find('button').prop('disabled', true);

						    Stripe.card.createToken($form, stripeResponseHandler);
				  }; 
				  
				  
				  function stripeResponseHandler(status, response) {
					  var $form = $('#checkoutForm');

					  if (response.error) {
					    // Show the errors on the form
					    showResponseErrorMessage(response.error.message);
					    $('#pageContainer').hideLoading();
					    $form.find('button').prop('disabled', false);
					  } else {
					    // response contains id and card, which contains additional card details
					    var token = response.id;
					    // Insert the token into the form so it gets submitted to the server
					    $form.append($('<input type="hidden" name="payment['stripe_token']" />').val(token));
					    // and submit
					    $form.get(0).submit();
					  }
					};
				  
				  
				  
		   </script> 


          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.creditcard.usecredicard" text="Use Credit Card"/></label>
            <div class="controls">
               <jsp:include page="/pages/shop/common/checkout/selectedPayment.jsp" />
            </div>
          </div>
          
          <jsp:include page="/pages/shop/common/checkout/creditCardInformations.jsp" />
		 