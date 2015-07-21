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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ taglib uri="/WEB-INF/shopizer-functions.tld" prefix="display" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<!-- TT Typeahead js files -->
<script src="<c:url value="/resources/js/hogan.js" />"></script>
<script src="<c:url value="/resources/templates/exoticamobilia/js/bloodhound.min.js" />"></script>
<script src="<c:url value="/resources/templates/bootstrap3/js/typeahead.bundle.min.js" />"></script>

<script type="text/javascript">
//Search code
$(document).ready(function() { 

    //post search form
   $("#searchButton").click(function(){
   			var searchQuery = $('#searchField').val();
			$('#hiddenQuery').val(searchQuery);
			log('Search string : ' + searchQuery);
	        $('#hiddenSearchForm').submit();
   });

   
   
	
   var searchElements = new Bloodhound({
		datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
		queryTokenizer: Bloodhound.tokenizers.whitespace,
		<c:if test="${requestScope.CONFIGS['useDefaultSearchConfig'][requestScope.LANGUAGE.code]==true}">
		  <c:if test="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]!=null}">
		prefetch: '<c:out value="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]}"/>',
		  </c:if>
	    </c:if>
	    remote: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.html"/>?q=%QUERY'

	});
   
   searchElements.initialize();


	var searchTemplate =  Hogan.compile([
				     '<p class="suggestion-text"><font color="black">{{value}}</font></p>'
	             ].join(''));
	
	
    //full view search
	$('#searchField.typeahead').typeahead({
	    hint: true,
	    highlight: true,
	    minLength: 1
	}, {
		name: 'shopizer-search',
	    displayKey: 'value',
	    source: searchElements.ttAdapter(),
	    templates: {
	    	suggestion: function (data) { return searchTemplate.render(data); }
	    }
	});
    
    //responsive
	$('#responsiveSearchField.typeahead').typeahead({
	    hint: true,
	    highlight: true,
	    minLength: 1
	}, {
		name: 'modal-shopizer-search',
	    displayKey: 'value',
	    source: searchElements.ttAdapter(),
	    templates: {
	    	suggestion: function (data) { return searchTemplate.render(data); }
	    }
	});

});

</script>

<!-- Mini shopping cart JS template -->
<script type="text/html" id="miniCartTemplate">
							<li>
								{{#code}}
								<table class="table table-hover">
									<thead>
										<tr>
											<!-- not responsive -->
											<th class="no-responsive">&nbsp;</th>
											<!-- responsive -->
											<th class="cart-product"><s:message code="label.generic.name" text="Name"/></th>
											<th class="cart-amount"><s:message code="label.order.total" text="Total"/></th>
											<th>&nbsp;</th>
										</tr>
									</thead>
									
									<tbody>
										{{#shoppingCartItems}}
										<tr id="{{productId}}" class="cart-product">
											<td class="cartImage no-responsive">
											{{#image}}
												<img width="40" src="<c:out value="${pageContext.servletContext.contextPath}" />{{image}}">
											{{/image}}
											{{^image}}
												&nbsp
											{{/image}}
											</td>
											<td class="cart-product">{{quantity}} {{name}}</td>
											<td class="cart-amount">{{price}}</td>
											<td><button productid="{{productId}}" class="close removeProductIcon" onclick="removeItemFromMinicart('{{id}}')">x</button></td>
										</tr>
										{{/shoppingCartItems}}
									</tbody>
								</table>
								<div class="panel-body text-right">	
									    <a href="#" onclick="viewShoppingCartPage();" class="btn btn-group btn-default btn-sm"><s:message code="label.checkout" text="Checkout"/></a>
								</div>
								{{/code}}
								{{^code}}
								<h4 style="text-align: center;color:#666666"><s:message code="label.emptycart" text="Your Shopping cart is empty" /></h4>
								{{/code}}
							</li>
</script>

<!-- mini cart label button template -->
<script type="text/html" id="miniCartSummaryTemplate">
		<!-- empty cart and full summary subTotal & quantity -->
		{{^code}}
		<span class="no-responsive uppercase"><s:message code="label.cart" text="Shopping cart"/></span> (0)
		{{/code}}
		{{#code}}
		<span class="no-responsive uppercase"><s:message code="label.cart" text="Shopping cart"/></span> ({{quantity}})
		{{/code}}
</script>


<!-- Customer account menu logged in customer -->
<script type="text/html" id="customerLoggedInAccountTemplate">
		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user no-desktop"></i> <span class="uppercase"><s:message code="label.generic.welcome" text="Welcome" /> <span class="no-responsive">{{firstName}}</span></span></button>
		<ul class="dropdown-menu dropdown-menu-right dropdown-animation">
				<li>
					<a onClick="javascript:location.href='<c:url value="/shop/customer/dashboard.html" />';" href="#"><i class="fa fa-user"></i><s:message code="label.customer.myaccount" text="My account"/></a>
				</li>
				<li>
					<a onClick="javascript:location.href='<c:url value="/shop/customer/j_spring_security_logout" />';" href="#"><i class="fa fa-power-off"></i><s:message code="button.label.logout" text="Logout"/></a>
				</li>
		</ul>
</script>

<!-- Customer account menu not logged in customer -->
<script type="text/html" id="customerNotLoggedInAccountTemplate">
		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user no-desktop"></i><span class="uppercase"> <s:message code="button.label.signin" text="Signin"/></span></button>
		<!-- Absolutely need to have the following id signinPane -->
        <ul id="signinPane" class="dropdown-menu dropdown-menu-right dropdown-animation">
			<li>
				<div id="loginError" class="alert alert-error bg-danger" style="display:none;"></div>
				<form class="login-form" id="login" method="post" accept-charset="UTF-8">
					<div class="form-group has-feedback">
							<label class="control-label"><s:message code="label.generic.username" text="User name" /></label>
							<input class="form-control" id="signin_userName" type="text" name="userName" />
							<i class="fa fa-user form-control-feedback"></i>
					</div>
					<div class="form-group has-feedback">
							<label class="control-label"><s:message code="label.generic.password" text="Password" /></label>
							<input class="form-control" id="signin_password" type="password" name="password" />
							<i class="fa fa-lock form-control-feedback"></i>
					</div>
					<input id="signin_storeCode" name="storeCode" type="hidden" value="<c:out value="${requestScope.MERCHANT_STORE.code}"/>"/>
					<button id="login-button" type="submit" class="btn btn-group btn-dark btn-sm"><s:message code="button.label.login" text="Login" /></button>
					<span></span>
					<br/>
					<a id="registerLink" onClick="javascript:location.href='<c:url value="/shop/customer/registration.html" />';" href="" role="button" class="" data-toggle="modal"><s:message code="label.register.notyetregistered" text="Not yet registered ?" /></a>
					<br/>
				</form>
			</li>
		</ul>
</script>

		<!-- header-top start (Add "dark" class to .header-top in order to enable dark header-top e.g <div class="header-top dark">) -->
	    <!-- ================ -->
	    <div class="header-top">
            <div class="container">
                <div class="row">
                    <div class="col-xs-2 col-sm-2">
                       <!-- header-top-first start -->
					   <!-- ================ -->
					   <div class="header-top-first clearfix">
					       <!-- social links -->
					       <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null || requestScope.CONFIGS['twitter_handle'] != null || requestScope.CONFIGS['pinterest'] != null}">
						   <ul class="social-links clearfix hidden-xs">
						       <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
							   <li class="twitter"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><i class="fa fa-twitter"></i></a></li>
							   </c:if>
							   <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
							   <li class="facebook"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><i class="fa fa-facebook"></i></a></li>
							   </c:if>
							   <c:if test="${requestScope.CONFIGS['pinterest'] != null}">
							   <li class="pinterest"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['pinterest']}"/>"><i class="fa fa-pinterest"></i></a></li>
							   </c:if>
						   </ul>
						   </c:if>
                           <div class="social-links hidden-lg hidden-md hidden-sm">
							   <div class="btn-group dropdown">
								 <button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-share-alt"></i></button>
									<ul class="dropdown-menu dropdown-animation">
						       <c:if test="${requestScope.CONFIGS['twitter_handle'] != null}">
							   <li class="twitter"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['twitter_handle']}"/>"><i class="fa fa-twitter"></i></a></li>
							   </c:if>
							   <c:if test="${requestScope.CONFIGS['facebook_page_url'] != null}">
							   <li class="facebook"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['facebook_page_url']}"/>"><i class="fa fa-facebook"></i></a></li>
							   </c:if>
							   <c:if test="${requestScope.CONFIGS['pinterest'] != null}">
							   <li class="pinterest"><a target="_blank" href="<c:out value="${requestScope.CONFIGS['pinterest']}"/>"><i class="fa fa-pinterest"></i></a></li>
							   </c:if>
									</ul>
							   </div>
						   </div>
                        </div>
						<!-- header-top-first end -->
                     </div>
                    
                    <div class="col-xs-10 col-sm-10">

							<!-- header-top-second start -->
							<!-- ================ -->
							<div id="header-top-second" class="clearfix">

								<!-- header top dropdowns start -->
								<!-- ================ -->
								<div class="header-top-dropdown">
                                        <!-- search box -->
                                        <div id="searchFieldGroup" class="btn-group dropdown no-responsive">  
					      					<input id="searchField" class="typeahead form-control" name="q" type="text"  />" autocomplete="off" spellcheck="false" dir="auto" value="<c:out value="${q}"/>">
                                        </div>
                                        <div class="btn-group dropdown">
                                            <button type="button" id="searchButton" class="btn dropdown-toggle no-responsive" data-toggle="dropdown"><i class="fa fa-search"></i><span class="uppercase"><s:message code="label.generic.search" text="Search" /></span></button>
                                        </div>
                                        <c:if test="${fn:length(requestScope.MERCHANT_STORE.languages) > 0}">
                                        <!-- switch language -->
                                        <div class="btn-group dropdown">
                                        	 <!-- For this template only french and english supported, if required build a dropdown list with all languages -->
                                       		<button type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-gear no-desktop"></i><span class="no-responsive uppercase"><s:message code="label.generic.language" text="Language"/></span></button>
                                       		<ul class="dropdown-menu dropdown-menu-right dropdown-animation">
                                       			<c:forEach items="${requestScope.MERCHANT_STORE.languages}" var="language">
                                       			<li><a href="<c:url value="/shop?locale=${language.code}"/>"><s:message code="lang.${language.code}" text="${language.code}" /></a></li>
                                       			</c:forEach>
                                       		</ul>
                                        </div> 
                                        </c:if>                                       
                                        <!-- Customer account menu populated by JS -->
                                        <div class="btn-group dropdown" id="customerAccount"></div>
                                        
                                        <!-- Shopping cart menu populated by JS -->
                                        <div id="miniCart" class="btn-group dropdown">
                                        	<button id="open-cart" type="button" class="btn dropdown-toggle" data-toggle="dropdown"><i class="fa fa-shopping-cart no-desktop"></i> <span id="miniCartSummary"></span></button>
                                        	<!-- miniCartDetails id required to add cart content from html template -->
                                        	<ul id="miniCartDetails" class="dropdown-menu dropdown-menu-right dropdown-animation cart"></ul>
                                        </div>
								</div>
								<!--  header top dropdowns end -->

							</div>
							<!-- header-top-second end -->

						</div>
					</div>
				</div>
			<!-- header-top end -->	
			</div>

                <!-- logo -->     
                <header class="header fixed clearfix">
				<div class="container">
					<div class="row">
						<div class="col-md-4">

							<!-- header-left start -->
							<!-- ================ -->
							<div class="header-left clearfix" id="site-branding">
								<c:choose>
									<c:when test="${requestScope.CONTENT['logo']!=null}">
										<sm:pageContent contentCode="logo"/>
									</c:when>
									<c:otherwise>
										<c:choose>
						                		<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
						                			<img class="logoImage" src="<sm:storeLogo/>"/>
						                		</c:when>
						                		<c:otherwise>
						                			<div class="logo">
						                			<h1>
						                			<a class="grey store-name" href="<c:url value="/shop/"/>">
						                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
						                			</a>  
						                			</h1>
						                			</div>
						                		</c:otherwise>
						                </c:choose>
									</c:otherwise>
								</c:choose>
								<!-- logo -->
								<!-- 
								<div class="logo" id="logo">a grey
									<h1 class="logo-text" alt="Entrepôt de meubles exotiques à Montréal"><span class="logo-text-inner">ExotiKA Mobilia</span></h1>
								</div>

								
								<div class="site-slogan">
									Meubles exotiques importés de qualité
								</div>
								-->
								

							</div>
							<!-- header-left end -->

						</div>

															
   <%-- 								
                        </div>
                    </div>
                </div>
            </div>
            <!--/.container -->
   --%>
 
 



			