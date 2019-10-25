<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

        <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900" rel="stylesheet"> 


		<!-- CORE CSS -->
		<link href="<c:url value="/resources/templates/generic/css/bootstrap.min.css" />" rel="stylesheet" type="text/css">

		<!-- more fonts, cursor up -->
        <link href="<c:url value="/resources/templates/generic/css/animate.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/templates/generic/css/slippry.css" />" rel="stylesheet" type="text/css">
        <link href="<c:url value="/resources/templates/generic/css/jquery-ui.min.css" />" rel="stylesheet" type="text/css">
       

		<link href="<c:url value="/resources/templates/generic/css/meanmenu.min.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/magnific-popup.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/magnific-popup.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/owl.carousel.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/linearicons-icon-font.min.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/font-awesome.min.css" />" rel="stylesheet" type="text/css">

		
		<link rel="icon" href="<c:url value="/resources/templates/generic/img/favicon.ico"/>"> 
		
		<!--  Theme -->

		<link href="<c:url value="/resources/templates/generic/css/style.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/template.css" />" rel="stylesheet" type="text/css">
		<link href="<c:url value="/resources/templates/generic/css/responsive.css" />" rel="stylesheet" type="text/css">

		<script src="<c:url value="/resources/templates/generic/js/vendor/modernizr-2.8.3.min.js" />"></script>
		<script src="<c:url value="/resources/templates/generic/js/vendor/jquery-1.12.0.min.js" />"></script>
    
    	<!-- generic and common css file -->
    	<link href="<c:url value="/resources/css/sm.css" />" rel="stylesheet">
    	<link href="<c:url value="/resources/css/showLoading.css" />" rel="stylesheet">
    	
    	
    	<script type="text/javascript">
	    	//show overlay wait
	    	function showTemplateLoading(element) {
	    		$.LoadingOverlay("show");
	    	}
	    	
	    	function hideTemplateLoading(element) {
	    		$.LoadingOverlay("hide", true);
	    	}
    
        </script>
    
    	<!-- ////////////// -->

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
	<!-- Google analytics -->	
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
	
	