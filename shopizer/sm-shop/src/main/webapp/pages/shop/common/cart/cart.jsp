<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>

<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>



<c:url value="/shop/cart/removeShoppingCartItem.html"
	var="removeShoppingCartItemUrl" />



<div id="main-content" class="row-fluid show-grid container">

<div class="span12 common-row">

<h1 class="cart-title"><s:message code="label.cart.revieworder" text="Review your order" /></h1>
<div id="store.error" class="alert alert-error" style="display:none;"><s:message code="message.error.shoppingcart.update" text="An error occurred while updating the shopping cart"/></div>
<br/>
<table id="mainCartTable" class="table table-bordered table-striped">

	<c:if test="${not empty cart}">
	   <c:choose>
	     <c:when test="${not empty cart.shoppingCartItems}">

			<c:forEach items="${cart.shoppingCartItems}" var="shoppingCartItem"
				varStatus="itemStatus">
				<c:if test="${itemStatus.index eq 0}">
					<thead>
						<tr>
							<th colspan="2" width="55%"><s:message code="label.generic.item.title" text="Item"/></th>
							<th colspan="2" width="15%"><s:message code="label.quantity" text="Quantity"/></th>
							<th width="15%"><s:message code="label.generic.price" text="Price"/></th>
							<th width="15%"><s:message code="label.order.total" text="Total"/></th>
						</tr>
					</thead>
					<tbody>
				</c:if>
				<form:form action="${updateShoppingCartItemUrl}"
					id="shoppingCartLineitem_${shoppingCartItem.id}">
					<tr>
						<td width="10%">
							<c:if test="${shoppingCartItem.image!=null}">
								<img width="60" src="<c:url value="${shoppingCartItem.image}"/>">
							</c:if>
						</td>

						<td style="border-left:none;">
								<strong>${shoppingCartItem.name}</strong>
								<c:if test="${fn:length(shoppingCartItem.shoppingCartAttributes)>0}">
									<br/>
									<ul>
										<c:forEach items="${shoppingCartItem.shoppingCartAttributes}" var="option">
										<li>${option.optionName} - ${option.optionValue}</li>
										</c:forEach>
									</ul>
								</c:if>
						
						</td>
						<td>
							<input type="text" class="input-small quantity form-control" placeholder="<s:message code="label.quantity" text="Quantity"/>"
							value="${shoppingCartItem.quantity}" name="quantity" id="${shoppingCartItem.id}" <c:if test="${shoppingCartItem.productVirtual==true}">readonly</c:if>>
						</td>
						<td style="border-left:none;"><button class="close"
								onclick="javascript:updateLineItem('${shoppingCartItem.id}','${removeShoppingCartItemUrl}');">&times;</button>
						</td>

						<td><strong>${shoppingCartItem.price}</strong></td>
						<td><strong>${shoppingCartItem.subTotal}</strong></td>


						<input type="hidden" name="lineItemId" id="lineItemId"
							value="${shoppingCartItem.id}"/>


					</tr>
				</form:form>


			</c:forEach>
			<c:forEach items="${cart.totals}" var="total">
				<tr class="subt">
					<td colspan="2">&nbsp;</td>
					<td colspan="3"><strong><s:message code="${total.code}" text="label [${total.code}] not found"/></strong></td>
					<td><strong><sm:monetary value="${total.value}" /></strong></td>
				</tr>
			</c:forEach>

		</c:when>
		 <c:otherwise>
		   <tr><td><s:message code="cart.empty" text="Your Shopping cart is empty" /></td></tr>
		 </c:otherwise>
	   </c:choose>
	</c:if>


	</tbody>
</table>
<c:if test="${not empty cart}">
	<c:if test="${not empty cart.shoppingCartItems}">
		<div class="pull-right">
			<div class="form-actions">
				<button type="button" class="btn" onClick="javascript:updateCart('#mainCartTable');"><s:message code="label.order.recalculate" text="Racalculate"/></button>
				<button id="checkoutButton" type="submit" class="btn btn-success"><s:message code="label.cart.placeorder" text="Place your order" /></button>
			</div>
		</div>
	</c:if>
</c:if>
</div>
</div>
<c:if test="${empty cart}">
<!-- load cart with cookie -->
<script>
  $(document).ready(function(){
		var cartCode=getCartCode();
		if(cartCode!=null) {
			console.log('cart code ' + cartCode);
			location.href='<c:url value="/shop/cart/shoppingCartByCode.html" />?shoppingCartCode=' + cartCode;
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
   });
</script>


