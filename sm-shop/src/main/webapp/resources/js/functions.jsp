<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 

<%@ page session="false" %>

<script>

function getContextPath() {
   return "${pageContext.request.contextPath}";
}

function getMerchantStore() {
   return "${requestScope.MERCHANT_STORE.id}";
}

function getMerchantStoreCode() {
   return "${requestScope.MERCHANT_STORE.code}";
}

function getLanguageCode() {
   return "${requestScope.LANGUAGE.code}";
}

function supportsCustomerLogin() {
	<c:choose>
	<c:when test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
		return true;
	</c:when>
	<c:otherwise>
	    return false;
	</c:otherwise>
	</c:choose>
}

function getItemLabel(quantity) {
	var labelItem = '<s:message code="label.generic.item" text="item" />';
	if (quantity > 1) {
		labelItem = '<s:message code="label.generic.items" text="items" />';
	}
	return labelItem;
}

//assign rating on a list of products
function setProductRating(productList) {
	jQuery.each( productList, function( i, val ) {
		var pId = '#productRating_' + val.id; 
	    $(pId).raty({ 
			readOnly: true,
			half: true,
			path : '<c:url value="/resources/img/stars/"/>',
			score: val.ratingCount
		});
	 });
}

function getLoginErrorLabel() {
	return '<s:message code="message.username.password" text="Login Failed. Username or Password is incorrect."/>';
}

function getEmptyCartLabel() {
	return '<s:message code="cart.empty" text="Your Shopping cart is empty" />';
}


function getInvalidEmailMessage() {
	return '<s:message code="messages.invalid.email" text="Invalid email address"/>';
}

function getInvalidUserNameMessage() {
	return '<s:message code="registration.username.length.invalid" text="User name must be at least 6 characters long"/>';
}


function getInvalidPasswordMessage() {
	return '<s:message code="message.password.length" text="Password must be at least 6 characters long"/>';
}

function getInvalidCheckPasswordMessage() {
	return '<s:message code="message.password.checkpassword.identical" text="Both password must match"/>';
}

function cartInfoLabel(cart){
	 var labelItem = getItemLabel(cart.quantity);
	 <!-- A configuration is required to display quantity and price -->
	 <c:choose>
	 <c:when test="${requestScope.CONFIGS['displayFullMiniCartInfo'] == true}">
	 $("#cartinfo").html('<span id="cartqty">(' + cart.quantity + ' ' + labelItem + ')</span>&nbsp;<span id="cartprice">' + cart.total + '</span>');
	 </c:when>
	 <c:otherwise>
	 $("#cartinfo").html('<span id="cartqty">(' + cart.quantity + ' ' + labelItem + ')</span>');
	 </c:otherwise>
	 </c:choose>
}

function cartSubTotal(cart) {
	return '<div class="pull-right"><font class="total-box-label"><s:message code="label.subtotal" text="Sub-total" /> : <font class="total-box-price"><strong><span id="checkout-total">' + cart.subTotal + '</span></strong></font></font></div>';
}


function getOrderValidationMessage(messageKey) {
	
	//stripe messages
	var invalid_number 	= '<s:message code="messages.error.creditcard.number" text="invalid_number"/>';
	var error_creditcard 	= '<s:message code="messages.error.creditcard" text="messages.error.creditcard"/>';
	var invalid_expiry_month = '<s:message code="messages.error.creditcard.dateformat" text="invalid_expiry_month"/>';
	var invalid_expiry_year = '<s:message code="messages.error.creditcard.dateformat" text="invalid_expiry_year"/>';
	var invalid_cvc 	= '<s:message code="messages.error.creditcard.cvc" text="invalid_cvc"/>';
	var incorrect_number = '<s:message code="messages.error.creditcard.number" text="invalid_expiry_month"/>';
	var expired_card 	= '<s:message code="message.payment.declined" text="expired_card"/>';
	var incorrect_cvc 	= '<s:message code="messages.error.creditcard.cvc" text="incorrect_cvc"/>';
	var card_declined 	= '<s:message code="message.payment.declined" text="card_declined"/>';
	var processing_error = '<s:message code="message.payment.error" text="processing_error"/>';
	var rate_limit = '<s:message code="message.payment.error" text="rate_limit"/>';
	
	var map = new Object(); // or var map = {};
	map['invalid_number'] = invalid_number;
	map['error_creditcard'] = error_creditcard;
	map['invalid_expiry_month'] = invalid_expiry_month;
	map['invalid_expiry_year'] = invalid_expiry_year;
	map['invalid_cvc'] = invalid_cvc;
	map['incorrect_number'] = incorrect_number;
	map['expired_card'] = expired_card;
	map['incorrect_cvc'] = incorrect_cvc;
	map['card_declined'] = card_declined;
	map['processing_error'] = processing_error;
	map['rate_limit'] = rate_limit;
	
	//log('Got message key ' + messageKey);
	
	var message = map[messageKey];
	
	if(message==null) {
		message = messageKey;
	}
	
	return message;

	
}




</script>