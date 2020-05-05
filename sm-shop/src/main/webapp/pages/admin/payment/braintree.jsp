<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



	
  
                 <div class="control-group">
                        <label class="required"><s:message code="module.payment.braintree.merchantId" text="Merchant ID"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-xxlarge highlight" path="integrationKeys['merchant_id']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${merchant_id!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.payment.braintree.message.merchant_id" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.braintree.publicKey" text="Public key"/></label>
	                        <div class="controls">
									<form:input cssClass="input-xxlarge highlight" path="integrationKeys['public_key']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${public_key!=null}">
	                        		<span id="apikeyerrors" class="error"><s:message code="module.payment.braintree.message.public_key" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>

                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.braintree.privateKey" text="Private key"/></label>
	                        <div class="controls">
									<form:input cssClass="input-xxlarge highlight" path="integrationKeys['private_key']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${private_key!=null}">
	                        		<span id="apikeyerrors" class="error"><s:message code="module.payment.braintree.message.private_key" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.payment.braintree.tokenizationKey" text="Tokenization key"/></label>
	                        <div class="controls">
									<form:input cssClass="input-xxlarge highlight" path="integrationKeys['tokenization_key']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${private_key!=null}">
	                        		<span id="apikeyerrors" class="error"><s:message code="module.payment.braintree.message.tokenization_key" text="Field in error"/></span>
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
            
                  
                  