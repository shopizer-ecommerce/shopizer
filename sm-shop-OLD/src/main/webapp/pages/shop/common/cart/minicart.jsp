
<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



<div class="cartbox" id="cart-box">
	<div class="box-content clearfix">
		<p id="shoppingcarttitle" class="lbw"><s:message code="label.cart" text="Shopping cart"/></p>
		<br />
		<div id="cartShowLoading" class="loading-indicator" style="width:100%;display:none;"></div>
		<div id="cartMessage" style="width:100%;display:none;"></div>
		<div id="shoppingcart">
			<table style="margin-bottom: 5px" class="table miniCartBox">
				<tbody id="shoppingcartProducts"><!-- products place holder -->
				</tbody>
			</table>
			<div id="total-box" class="total-box"></div><!-- totals place holder -->
			<button class="btn btn-large checkoutButton" style="width: 100%" type="button" onclick="viewShoppingCartPage();"><s:message code="label.checkout" text="Checkout"/></button>
		</div>
	</div>
</div>