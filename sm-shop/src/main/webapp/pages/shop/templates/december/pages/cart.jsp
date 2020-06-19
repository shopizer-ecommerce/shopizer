
<%
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script
	src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>

<div class="entry-header-area ptb-40">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="entry-header">
					<h1 class="entry-title">
						<s:message code="label.cart.revieworder" text="Review your order" />
					</h1>
				</div>
			</div>
		</div>
	</div>
</div>

<c:url value="/shop/cart/removeShoppingCartItem.html"
	var="removeShoppingCartItemUrl" />

<div id="store.error" class="alert alert-error alert-danger"
	style="display: none;">
	<s:message code="message.error.shoppingcart.update"
		text="An error occurred while updating the shopping cart" />
</div>


<!-- cart-main-area start -->
<div class="cart-main-area ptb-40">
	<div class="container">


		<!-- Unavailables -->
		<c:if test="${fn:length(cart.unavailables) gt 0}">
						<div class="cart-main-area ptb-40">
							<div class="container">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div id="store.error" class="alert alert-error"
											style="display: block;">
											<s:message code="message.error.shoppingcart.unavailables"
												text="Some of the item in your shopping cart are as of today unavailable for purchase. Those items will be removed from your shopping cart when the order form is displayed. If you are interested in purchasing this item, please send use a message with the item number, we will get back to you as soon as possible with an update on the availability of the item." />
										</div>
										<table style="width:50%;">
											<c:forEach items="${cart.unavailables}" var="unavailable"
												varStatus="itemStatus">
					
												<c:if test="${itemStatus.index eq 0}">
													<thead>
														<tr>
															<th colspan="2" width="70%">&nbsp;</th>
															<th colspan="1" width="30%">&nbsp;</th>
														</tr>
													</thead>
													<tbody>
												</c:if>
					
												<form:form action="${updateShoppingCartItemUrl}"
														id="unavailableCartLineitem_${unavailable.id}">
												<tr>
													<td><c:if test="${unavailable.image!=null}">
															<img width="60" src="<c:url value="${unavailable.image}"/>">
														</c:if></td>
					
													<td style="border-left: none;"><strong>${unavailable.name}</strong>
														<c:if test="${fn:length(unavailable.shoppingCartAttributes)>0}">
															<br />
															<ul>
																<c:forEach items="${unavailable.shoppingCartAttributes}"
																	var="option">
																	<li>${option.optionName}-${option.optionValue}</li>
																</c:forEach>
															</ul>
														</c:if> <br /> <s:message code="label.quantity" text="Quantity" />:
														<c:out value="${unavailable.quantity}" /> <br /> <s:message
															code="label.generic.price" text="Price" />: <c:out
															value="${unavailable.price}" /></td>
													<td>
													
															<div class="cart-del">
																	<a href="#" class="cart-close removeProductIcon"
																		style="clear: both !important;"
																		onclick="javascript:updateUnavailableLineItem('${unavailable.id}','${removeShoppingCartItemUrl}'); return false;">
																		<i class="fa fa-times"></i>
																	</a> <input type="hidden" name="lineItemId" id="lineItemId"
																		value="${unavailable.id}" />
															</div>
													
													</td>
					
												</tr>
												</form:form>
											</c:forEach>
										</table>
					
									</div>
							</div>
					</div>
			</div>
		</c:if>
		<c:choose>
			<c:when test="${not empty cart}">
				<c:choose>
					<c:when test="${not empty cart.shoppingCartItems}">

						<!-- cart-main-area start -->
						<div class="cart-main-area ptb-40">
							<div class="container">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
										<div class="table-content table-responsive">
											<!-- 
											Shopping cart has to be id=mainartTable
							 				-->
											<table id="mainCartTable"
												class="table table-hover table-condensed">
												<c:forEach items="${cart.shoppingCartItems}"
													var="shoppingCartItem" varStatus="itemStatus">
													<c:if test="${itemStatus.index eq 0}">

														<thead>
															<tr>
																<th><s:message code="label.generic.item.title"
																		text="Item" /></th>
																<th><s:message code="label.quantity"
																		text="Quantity" /></th>
																<th><s:message code="label.generic.price"
																		text="Price" /></th>
																<th><s:message code="label.order.total"
																		text="Total" /></th>
																<th></th>
															</tr>
														</thead>
														<tbody>
													</c:if>
													<form:form action="${updateShoppingCartItemUrl}"
														id="shoppingCartLineitem_${shoppingCartItem.id}">
														<tr>
															<td
																data-th="<s:message code="label.generic.item.title" text="Item"/>">
																<div class="row-cart">

																	<div class="col-sm-4 hidden-xs">
																		<c:if test="${shoppingCartItem.image!=null}">
																			<img width="60"
																				src="<c:url value="${shoppingCartItem.image}"/>"
																				class="">
																		</c:if>
																	</div>
																	<div class="col-sm-8">
																		<span class="nomargin"><strong>${shoppingCartItem.name}</strong></span>

																		<c:if
																			test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
																			<p>
																			<ul>
																				<c:forEach
																					items="${shoppingCartItem.shoppingCartAttributes}"
																					var="option">
																					<li>${option.optionName} -
																						${option.optionValue}</li>
																				</c:forEach>
																			</ul>
																			</p>
																		</c:if>
																	</div>
																</div>
															</td>
															<td width="10%"
																data-th="<s:message code="label.quantity" text="Quantity"/>">
																<input type="number" min="1"
																class="input-small quantity text-center"
																value="${shoppingCartItem.quantity}" name="quantity"
																id="${shoppingCartItem.id}"
																<c:if test="${shoppingCartItem.productVirtual==true}">readonly</c:if>>
															</td>

															<td
																data-th="<s:message code="label.generic.price" text="Price"/>"><strong>${shoppingCartItem.price}</strong></td>
															<td
																data-th="<s:message code="label.order.total" text="Total"/>"
																class=""><strong>${shoppingCartItem.subTotal}</strong></td>

															<td width="10%" class="actions" data-th="">
																<div class="cart-del">
																	<a href="#" class="cart-close removeProductIcon"
																		style="clear: both !important;"
																		onclick="javascript:updateLineItem('${shoppingCartItem.id}','${removeShoppingCartItemUrl}'); return false;">
																		<i class="fa fa-times"></i>
																	</a> <input type="hidden" name="lineItemId" id="lineItemId"
																		value="${shoppingCartItem.id}" />
																</div>
															</td>

														</tr>
													</form:form>
												</c:forEach>
											</table>
										</div>

										<div class="row">
											<div class="col-lg-8 col-md-8 col-sm-7 col-xs-12">
												<div class="buttons-cart">
													<a href="#"
														onClick="javascript:updateCart('#mainCartTable');"><s:message
															code="label.order.recalculate" text="Racalculate" /></a> <a
														href="<c:url value="/shop"/>"><s:message
															code="button.label.continue" text="Continue shopping" /></a>
												</div>
												<div class="coupon">
													<h3>
													<s:message
															code="label.order.promocode.title" text="Promotion code" /></h3>
													<p>
													<s:message
															code="label.order.promocode.text" text="Enter your promotion code if you have one." />
													</p>
													<input type="text" id="promoCode" name="promoCode" placeholder="Promo code" />
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-5 col-xs-12">
												<div class="cart_totals">

													<h2>
														<s:message code="label.order.totals" text="Totals" />
													</h2>
													<div class="cart-totals-table">
														<table>
															<tbody>
																<c:forEach items="${cart.totals}" var="total">
																	<tr class="cart-subtotal">
																		<th><s:message code="${total.code}"
																				text="label [${total.code}] not found" />
																				<c:if test="${total.code == 'order.total.discount'}">
																					(<c:out value="${total.text}"/>)
																				</c:if>		
																		</th>
																		<td><span class="amount"><sm:monetary
																					value="${total.value}" /></span></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</div>
												<div class="wc-proceed-to-checkout">
													<a href="<c:url value="/shop/order/checkout.html"/>"><s:message
															code="label.proceed.checkout" text="Proceed to checkout" /></a>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!-- cart-main-area end -->

					</c:when>
					<c:otherwise>
						<h4>
							<s:message code="cart.empty" text="Your Shopping cart is empty" />
						</h4>
					</c:otherwise>
				</c:choose>
			</c:when>
			<c:otherwise>
				<h4>
					<s:message code="cart.empty" text="Your Shopping cart is empty" />
				</h4>
			</c:otherwise>
		</c:choose>
	</div>
</div>
<!-- cart-main-area end -->


<c:if test="${empty cart}">
	<!-- load cart with cookie -->
	<script>
		$(document)
				.ready(
						function() {
							var cartCode = getCartCode();
							if (cartCode != null) {
								console.log('cart code ' + cartCode);
								location.href = '<c:url value="/shop/cart/shoppingCartByCode" />?shoppingCartCode='
										+ cartCode;
							}

						});
	</script>
</c:if>

<script>
	$(document).ready(function() {
		$('.quantity').numeric();
		$("input[type='text']").keypress(function(e) {
			if (e.which == 13) {
				e.preventDefault();
			}
		});
		$('#checkoutButton').click(function(e) {
			location.href = '<c:url value="/shop/order/checkout.html"/>';
		});
	});
	
	var number = document.querySelector(".quantity");
	number.onkeydown = function(e) {
		if(!((e.keyCode > 95 && e.keyCode < 106)
				|| (e.keyCode > 47 && e.keyCode < 58)
				|| e.keyCode == 8)) {
			return false;
		}
	}
</script>
