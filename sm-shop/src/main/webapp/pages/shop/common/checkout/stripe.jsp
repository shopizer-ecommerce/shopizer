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

			<script type="text/javascript" src="https://js.stripe.com/v3/"></script>
            <style>
                #card-element {
                  border-radius: 4px 4px 0 0 ;
                  padding: 12px;
                  border: 1px solid rgba(50, 50, 93, 0.1);
                  height: 44px;
                  width: 100%;
                  background: white;
                }
                #card-element .input {
                  border-radius: 6px;
                  margin-bottom: 6px;
                  padding: 12px;
                  border: 1px solid rgba(50, 50, 93, 0.1);
                  height: 44px;
                  font-size: 16px;
                  width: 100%;
                  background: white;
                }
            </style>
			<script type="text/javascript">

                var stripe = Stripe('<c:out value="${requestScope.paymentMethod.informations.integrationKeys[\'publishableKey\']}" escapeXml="false"/>');
                var elements = stripe.elements();

                function initStripePayment() {

                    var $form = $('#checkoutForm');

                    try {

                        // Disable the submit button to prevent repeated clicks
                        //$form.find('button').prop('disabled', true);

                        stripe.createToken(card).then(stripeResponseHandler);


                    } catch (err) {

                        console.log('Got an error ' + err.message);
                        hideSMLoading('#pageContainer');
                        //log(err.message);
                        showResponseErrorMessage(err.message);
                    }
                };


                function stripeResponseHandler(response) {
                    var $form = $('#checkoutForm');

                    if (response.error) {
                        // Show the errors on the form
                        var orderValidationMessage = getOrderValidationMessage(response.error.code);

                        //log('Validation message ' + orderValidationMessage);

                        if (orderValidationMessage == '') {
                            orderValidationMessage = response.error.message;
                        }
                        showResponseErrorMessage(orderValidationMessage);
                        hideSMLoading('#pageContainer');
                        $form.find('button').prop('disabled', false);
                    } else {
                        // response contains id and card, which contains additional card details
                        var token = response.token.id;
                        // Insert the token into the form so it gets submitted to the server
                        var tokenField = '<input type="hidden" name="payment[\'stripe_token\']" value="' + token + '" /><input type="hidden" name="payment[\'null_creditcard\']" value="null_creditcard"/>';
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
              AAAAAAAAAAA Stripe 1  ${cart.getCode()}
            <label class="control-label"><s:message code="label.payment.creditcard.usecredicard" text="Use Credit Card"/></label>
            <div class="controls">
               <jsp:include page="/pages/shop/common/checkout/selectedPayment.jsp" />
            </div>
          </div>
          
          <%--<jsp:include page="/pages/shop/common/checkout/${creditCardInformationsPage}.jsp" />--%>

            <div id="card-element"><!--Stripe.js injects the Card Element--></div>

		          <script>
                      var card ;
                      var style = {
                        base: {
                          color: "#32325d",
                          fontFamily: 'Arial, sans-serif',
                          fontSmoothing: "antialiased",
                          fontSize: "16px",
                          "::placeholder": {
                            color: "#32325d"
                          }
                        },
                        invalid: {
                          fontFamily: 'Arial, sans-serif',
                          color: "#fa755a",
                          iconColor: "#fa755a"
                        }
                      };
                      card = elements.create("card", { style: style });
                      $(document).ready(function () {
                          card.mount("#card-element");
                     });
                  </script>