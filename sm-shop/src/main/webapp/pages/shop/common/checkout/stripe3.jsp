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
                #card-element3 {
                  border-radius: 4px 4px 0 0 ;
                  padding: 12px;
                  border: 1px solid rgba(50, 50, 93, 0.1);
                  height: 44px;
                  width: 100%;
                  background: white;
                }
                #card-element3 .input {
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

                var stripe3 = Stripe('<c:out value="${requestScope.paymentMethod.informations.integrationKeys[\'publishableKey\']}" escapeXml="false"/>');
                var elements3 = stripe3.elements();

                function initStripePayment3() {
                    generateStripe3PaymentIntent(
                        (response) => {
                            console.log(" RESCEIVED PAYMENT INTENTENTION :  " + response.INTENTSECRET);
                            showSMLoading('#pageContainer');
                            var $form = $('#checkoutForm');
                            try {

                                // Executed PAYMENT INTENT Confirmation with CARD.

                                stripe3
                                    .confirmCardPayment(response.INTENTSECRET, {
                                        payment_method: {
                                            card: card3
                                        },
                                    })
                                    .then(stripeResponseHandler3);

                            } catch (err) {
                                console.log('Got an error ' + err.message);
                                hideSMLoading('#pageContainer');
                                showResponseErrorMessage(err.message);
                            }
                        },
                        (error) => {
                            console.log('Got an error getting PI data' + error);
                            hideSMLoading('#pageContainer');
                            showResponseErrorMessage(error);
                        }
                    );
                };


                function generateStripe3PaymentIntent(successCallback , errorCallback) {
                    var url = '<c:url value="/shop/order/payment/init/"/>stripe3.html';
                    var data = $(checkoutFormId).serialize();
                    $.ajax({
                        type: 'POST',
                        url: url,
                        data: data,
                        cache: false,
                        dataType: 'json',
                        success: function (response) {
                            //$('#submitOrder').enable();
                            hideSMLoading('#pageContainer');
                            var resp = response.response;
                            var status = resp.status;
                            //console.log(status);
                            if (status == 0 || status == 9999) {

                                successCallback(resp);

                            } else if (status == -2) {//validation issues

                                //console.log(resp.validations);
                                var globalMessage = '';
                                for (var i = 0; i < resp.validations.length; i++) {
                                    var fieldName = resp.validations[i].field;
                                    var message = resp.validations[i].message;
                                    //console.log(fieldName +  ' - ' + message);
                                    //query for the field
                                    var f = $(document.getElementById('error-' + fieldName));
                                    if (f) {
                                        f.html(message);
                                    }
                                    globalMessage = globalMessage + message + '<br/>';

                                }

                                errorCallback(globalMessage)

                            } else {
                                //console.log('Wrong status ' + status);
                                errorCallback('<s:message code="error.code.99" text="An error message occured while trying to process the payment (99)"/>');

                            }
                        },
                        error: function (xhr, textStatus, errorThrown) {
                            hideSMLoading('#pageContainer');
                            //alert('error ' + errorThrown);
                            errorCallback(errorThrown);
                        }

                    });
                }


                function stripeResponseHandler3(response) {
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
                        showSMLoading('#pageContainer');
                        // response contains id and card, which contains additional card details
                        var paymentItentId = response.paymentIntent.id;
                        // Insert the token into the form so it gets submitted to the server
                        var tokenField = '<input type="hidden" name="payment[\'stripe_token\']" value="' + paymentItentId + '" /><input type="hidden" name="payment[\'null_creditcard\']" value="null_creditcard"/>';
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
          
          <%--<jsp:include page="/pages/shop/common/checkout/${creditCardInformationsPage}.jsp" />--%>

            <div id="card-element3"><!--Stripe.js injects the Card Element--></div>

		          <script>
                      var card3 ;
                      var style3 = {
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
                      card3 = elements3.create("card", { style: style3 });
                      $(document).ready(function () {
                          card3.mount("#card-element3");
                     });
                  </script>