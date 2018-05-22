<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

	<!-- required common scripts -->
	<script src="<c:url value="/resources/js/shop-functions.js" />"></script>
    <jsp:include page="/resources/js/functions.jsp" />
    <script src="<c:url value="/resources/js/jquery-1.10.2.min.js" />"></script>
    <script src="<c:url value="/resources/js/json2.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-cookie.js" />"></script>
    <script src="<c:url value="/resources/js/shopping-cart.js" />"></script>
    <script src="<c:url value="/resources/js/login.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.showLoading.min.js" />"></script>
    
    <!-- specific css -->
    <link href="<c:url value="/resources/templates/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/templates/bootstrap/css/bootstrap-responsive.min.css" />" rel="stylesheet">
    
    <!-- generic and common css file -->
    <link href="<c:url value="/resources/css/sm.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/showLoading.css" />" rel="stylesheet">
    
    <!-- template css file -->
    <link href="<c:url value="/resources/templates/bootstrap/css/theme.css" />" rel="stylesheet">
    
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-tooltip.js" />"></script>
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-popover.js" />"></script>
    
    <link rel="icon" href="<c:url value="/resources/templates/bootstrap/img/favicon.ico"/> ">

    
    <!-- mini shopping cart template -->
    <script type="text/html" id="miniShoppingCartTemplate">
		{{#shoppingCartItems}}
			<tr id="{{productId}}" class="cart-product">
				<td>
			{{#image}}
					<img width="40" src="{{contextPath}}{{image}}">
			{{/image}}
			{{^image}}
					&nbsp
			{{/image}}
				</td>
				<td>{{quantity}}</td>
				<td>{{name}}</td>
				<td>{{price}}</td>
				<td><button productid="{{productId}}" class="close removeProductIcon" onclick="removeItemFromMinicart('{{id}}')">x</button></td>
			</tr>
		{{/shoppingCartItems}}
	</script>
	
	<c:if test="${requestScope.CONFIGS['google_analytics_url'] != null}">	
	<script type="text/javascript">
	//<![CDATA[ 
		  <!-- google analytics -->
	  	   var _gaq = _gaq || [];
	  	   _gaq.push(['_setAccount', '<c:out value="${requestScope.CONFIGS['google_analytics_url']}"/>']);
	  	   _gaq.push(['_trackPageview']);

	  	   (function() {
	    		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	   		 var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  	   })();
	  	//]]> 
	</script>
	</c:if>
	
	