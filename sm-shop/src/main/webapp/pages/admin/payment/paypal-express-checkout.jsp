<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



                 
                 <div class="control-group">
                        <label class="required"><s:message code="module.payment.paypal-express-checkout.userid" text="Paypal API user name"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['username']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${username!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.payment.paypal.message.identifier" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.paypal-express-checkout.apikey" text="Paypal API password"/></label>
	                        <div class="controls">
									<form:input cssClass="input-large highlight" path="integrationKeys['api']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${api!=null}">
	                        		<span id="apikeyerrors" class="error"><s:message code="module.payment.paypal.message.api" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>

                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.paypal-express-checkout.signature" text="Paypal API signature"/></label>
	                        <div class="controls">
									<form:input cssClass="input-large highlight" path="integrationKeys['signature']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${signature!=null}">
	                        		<span id="apisignatureerrors" class="error"><s:message code="module.payment.paypal.message.signature" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.transactiontype" text="Transaction type"/></label>
	                        <div class="controls">
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZE" />&nbsp;<s:message code="module.payment.transactiontype.preauth" text="Pre-authorization" /><br/>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZECAPTURE" />&nbsp;<s:message code="module.payment.transactiontype.sale" text="Sale" /></br>
	                        </div>
                  </div>        
            
                  
                  