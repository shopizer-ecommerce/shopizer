<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>


<div class="control-group">
	<label class="required"><s:message code="module.payment.stripe.secretKey" text="Secret key"/></label>
	<div class="controls">
		<form:input cssClass="input-xxlarge highlight" path="integrationKeys['secretKey']"/>
	</div>
	<span class="help-inline">
		<c:if test="${secretKey!=null}">
			<span id="identifiererrors" class="error"><s:message
					code="module.payment.stripe.message.secretKey"
					text="Field in error"/></span>
		</c:if>
	</span>
</div>

<div class="control-group">
	<label class="required"><s:message code="module.payment.stripe.publishableKey" text="Publishable key"/></label>
	<div class="controls">
		<form:input cssClass="input-xxlarge highlight" path="integrationKeys['publishableKey']"/>
	</div>
	<span class="help-inline">
		<c:if test="${publishableKey!=null}">
			<span id="apikeyerrors" class="error"><s:message
					code="module.payment.stripe.message.publishableKey"
					text="Field in error"/></span>
		</c:if>
	</span>
</div>


<div class="control-group">
	<label class="required"><s:message code="module.payment.transactiontype" text="Transaction type"/></label>
	<div class="controls">
		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZE"/>
		&nbsp;
		<s:message code="module.payment.transactiontype.preauth" text="Pre-authorization"/>
		<br/>
		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZECAPTURE"/>
		&nbsp;
		<s:message code="module.payment.transactiontype.sale" text="Sale"/>
		</br>
	</div>
</div>
            
                  
                  