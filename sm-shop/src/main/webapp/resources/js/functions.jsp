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




</script>