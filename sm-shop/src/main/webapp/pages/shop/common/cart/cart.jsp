<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<script
	src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>



<c:url value="/shop/cart/removeShoppingCartItem.html"
	var="removeShoppingCartItemUrl" />



<div id="main-content" class="row-fluid show-grid container">

	<div class="span12 common-row">

		<h1 class="cart-title">
			<s:message code="label.cart.revieworder" text="Review your order" />
		</h1>
		<div id="store.error" class="alert alert-error alert-danger"
			style="display: none;">
			<s:message code="message.error.shoppingcart.update"
				text="An error occurred while updating the shopping cart" />
		</div>



		<!-- Unavailables -->
		<c:if test="${fn:length(cart.unavailables) gt 0}">

			<div id="store.error" class="alert alert-error"
				style="display: block;">
				<s:message code="message.error.shoppingcart.unavailables"
					text="Some of the item in your shopping cart are as of today unavailable for purchase. Those items will be removed from your shopping cart when the order form is displayed. If you are interested in purchasing this item, please send use a message with the item number, we will get back to you as soon as possible with an update on the availability of the item." />
			</div>
			<table>
				<c:forEach items="${cart.unavailables}" var="unavailable"
					varStatus="itemStatus">

					<c:if test="${itemStatus.index eq 0}">
						<thead>
							<tr>
								<th colspan="2" width="65%">&nbsp;</th>
								<th colspan="1" width="35%">&nbsp;</th>
							</tr>
						</thead>
						<tbody>
					</c:if>


					<tr>
						<td width="20%"><c:if test="${unavailable.image!=null}">
								<img width="60" src="<c:url value="${unavailable.image}"/>">
							</c:if></td>

						<td style="border-left: none;"><strong>${unavailable.name}</strong>
							<c:if test="${fn:length(unavailable.shoppingCartAttributes)>0}">
								<br />
								<ul>
									<c:forEach items="${unavailable.shoppingCartAttributes}"
										var="option">
										<li>${option.optionName}- ${option.optionValue}</li>
									</c:forEach>
								</ul>
							</c:if> <br />
						<s:message code="label.quantity" text="Quantity" />: <c:out
								value="${unavailable.quantity}" /> <br />
						<s:message code="label.generic.price" text="Price" />: <c:out
								value="${unavailable.price}" /></td>
						<td>&nbsp;</td>




					</tr>
				</c:forEach>
			</table>
			<br />
		</c:if>

		<br />




		<!-- ******* EX SHOPPING CART  **********-->

		<table id="mainCartTable" class="table table-hover table-condensed">
			<c:if test="${not empty cart}">
				<c:choose>
					<c:when test="${not empty cart.shoppingCartItems}">
						<c:forEach items="${cart.shoppingCartItems}"
							var="shoppingCartItem" varStatus="itemStatus">
							<c:if test="${itemStatus.index eq 0}">

								<thead>
									<tr>
										<th><s:message code="label.generic.item.title"
												text="Item" /></th>
										<th><s:message code="label.quantity" text="Quantity" /></th>
										<th><s:message code="label.generic.price" text="Price" /></th>
										<th><s:message code="label.order.total" text="Total" /></th>
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
														src="<c:url value="${shoppingCartItem.image}"/>" class="">
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
															<li>${option.optionName}- ${option.optionValue}</li>
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
										class="input-small quantity form-control text-center"
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
										<button type="button" class="btn btn-danger btn-sm"
											onclick="javascript:updateLineItem('${shoppingCartItem.id}','${removeShoppingCartItemUrl}'); return false;"
											style="margin-top: 0px !important;">
											<i class="fa fa-trash-o"></i>&nbsp;
											<s:message code="label.generic.remove" text="Remove" />
										</button> <input type="hidden" name="lineItemId" id="lineItemId"
										value="${shoppingCartItem.id}" />
									</td>

								</tr>
							</form:form>


						</c:forEach>
						<c:forEach items="${cart.totals}" var="total">
							<tr class="subt" class="hidden-xs">
								<td colspan="2">&nbsp;</td>
								<td><strong><s:message code="${total.code}"
											text="label [${total.code}] not found" /></strong></td>
								<td colspan="2"><strong><sm:monetary
											value="${total.value}" /></strong></td>
							</tr>
						</c:forEach>



						</tbody>

						<tfoot>

							<tr>
								<td colspan="3"><a href="#"
									onClick="javascript:updateCart('#mainCartTable');"
									class="btn btn-regular"><s:message
											code="label.order.recalculate" text="Racalculate" /></a> <a
									href="<c:url value="/shop"/>" class="btn btn-warning"><i
										class="fa fa-angle-left"></i> <s:message
											code="button.label.continue" text="Continue shopping" /></a></td>
								<td colspan="2">
									<button id="checkoutButton" type="submit"
										class="btn btn-success btn-block">
										<s:message code="label.cart.placeorder"
											text="Place your order" />
										<i class="fa fa-angle-right"></i>
									</button>
								</td>
							</tr>
						</tfoot>

					</c:when>
					<c:otherwise>
						<tr>
							<td><s:message code="cart.empty"
									text="Your Shopping cart is empty" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:if>


		</table>


		<!-- ******* EX SHOPPING CART *********** -->




	</div>
</div>
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
  $(document).ready(function(){		
	    $('.quantity').numeric();
	    $("input[type='text']").keypress(function(e){
	        if (e.which == 13){
	        	e.preventDefault();	        	
	        }
	    });
	    $('#checkoutButton').click(function(e) {
	    	location.href='<c:url value="/shop/order/checkout.html"/>';
	    });
	    
	    var number = document.querySelector(".quantity");
	    number.onkeydown = function(e) {
	  	  if(!((e.keyCode > 95 && e.keyCode < 106)
	  			  || (e.keyCode > 47 && e.keyCode < 58)
	  			  || e.keyCode == 8)) {
	  		  return false;
	  	  }
	    }
   });
</script>