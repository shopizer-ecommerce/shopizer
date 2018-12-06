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

<script type="text/javascript">

$(document).ready(function() {
	
	removeCart();
});

</script>



<c:if test="${requestScope.CONFIGS['google_analytics_url'] != null && confirmation!=null}">


<script type="text/javascript">
//<![CDATA[ 

	
	if(_gaq) {
		
		
	//console.log('GAQ ');

	_gaq.push(['_trackPageview']);
	_gaq.push(['_addTrans', 
		'<c:out value="${order.id}"/>', // order ID - required 
		'<c:out value="${requestScope.MERCHANT_STORE.storename}"/>', //Store Name
	    '<sm:monetary value="${order.total.value}"/>',  // total - required
		'<sm:monetary value="${order.tax.value}"/>',  // tax 
		<c:choose>
		<c:when test="${order.shipping!=null}">
		'<sm:monetary value="${order.shipping.value}"/>', // shipping 
		</c:when>
		<c:otherwise>
		'',
		</c:otherwise>
		</c:choose>
		'<c:out value="${order.customer.billing.city}"/>', // city
		<c:choose>
		<c:when test="${order.customer.billing.zone!=null}">
		'<c:out value="${order.customer.billing.zone}"/>',// state or province 
		</c:when>
		<c:otherwise>
		'<c:out value="${order.customer.billing.zone.stateProvince}"/>',// state or province 
		</c:otherwise>
		</c:choose>
		'<c:out value="${order.customer.billing.country}"/>' // country
		 ]);


	<c:forEach items="${order.products}" var="product" varStatus="status">

	_gaq.push(['_addItem', 
		'<c:out value="${order.id}"/>', // order ID - required 
		'<c:out value="${product.sku}" />', // SKU/code - required 
		'<c:out value="${product.productName}" />', // product name 
		'<c:out value="${product.price}" />', // unit price - required 
		'<c:out value="${product.orderedQuantity}" />' // quantity - required 
		]); 

	</c:forEach>

	_gaq.push(['_trackTrans']); //submits transaction to the Analytics servers 

	}


	//]]> 
</script>

</c:if>


	<div id="main-content" class="container clearfix no-padding-left">
		<h1><s:message code="label.checkout.confirmation" text="Order completed" /></h1>
		<div class="row-fluid">
			<div class="span12">  

          <p class="lead"><c:out value="${ordermessage}" /></p>
          <p class="lead"><c:out value="${ordermessageid}" /></p>
          <p><c:out value="${orderemail}" /></p>
          

          
          <c:if test="${downloads!=null}">
          	<p>
          	<c:choose>
          		<c:when test="${order.orderStatus.value=='processed'}">
          		    <strong><s:message code="label.checkout.downloads.completed" text="label.checkout.downloads.completed"/></strong><br/>
          			<c:forEach items="${downloads}" var="download">
          				<a href="<sm:orderProductDownload productDownload="${download}" orderId="${order.id}"/>"><c:out value="${download.fileName}" /></a>
          			</c:forEach>
          		</c:when>
          		<c:otherwise>
					<s:message code="label.checkout.downloads.processing" text="*** An email with your file(s) download instructions will be sent once the payment for this order will be completed."/>
          		</c:otherwise>
          	</c:choose>
			</p>
          </c:if>
          
          <p class="muted"><s:message code="label.checkout.additionaltext" text="If you have any comments or suggestions for us, please send us an email with your order id. We value your feedback."/></p>
          
          
            </div>
            
            <c:if test="${requestScope.CONTENT['confirmationMessage']!=null}">
			<div class="span12">
				<sm:pageContent contentCode="confirmationMessage"/>
			</div>
			</c:if>
          </div>
          
      </div>