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
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
 <c:set var="lang" scope="request" value="${requestScope.locale.language}"/> 
 
 
 <html xmlns="http://www.w3.org/1999/xhtml"> 
 
 
     <head>
        	 	<meta charset="utf-8">
        	 	<meta http-equiv="x-ua-compatible" content="ie=edge">
    			<title><c:out value="${requestScope.PAGE_INFORMATION.pageTitle}" /></title>
    			<meta name="viewport" content="width=device-width, initial-scale=1.0">
    			<meta name="description" content="<c:out value="${requestScope.PAGE_INFORMATION.pageDescription}" />">
    			<meta name="author" content="<c:out value="${requestScope.MERCHANT_STORE.storename}"/>">

				<!-- include all header js and css -->
                <jsp:include page="/pages/shop/templates/ambroselar/sections/shopLinks.jsp" />
                
     <script type="text/html" id="productBoxTemplate"> 
			{{#products}}			
                <div itemscope itemtype="http://schema.org/Enumeration" class="col-md-COLUMN-SIZE col-sm-6 col-xs-12 product" item-order="{{sortOrder}}" item-name="{{description.name}}" item-price="{{price}}" data-id="{{id}}">
					<div class="box-style-4 white-bg object-non-visible animated object-visible">		
						{{#description.highlights}}  
    						<div class="ribbon-wrapper-green">
   								<div class="ribbon-green">
   								{{description.highlights}} 
   								</div>
   							</div>
						{{/description.highlights}}
						{{^canBePurchased}}
							<div class="sold-out-wrapper-red">
   								<div class="sold-out-red">
   									<span class="sold-out-text"><s:message code="label.soldout" text="Sold out" /></span> 
   								</div>
   							</div>
						{{/canBePurchased}}
						<!--  *** image  *** -->
						<div class="product-image">
    					{{#image}}                              
							<img class="product-img" src="<c:url value=""/>{{image.imageUrl}}"><a class="overlay" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html"><img class="product-img" src="<c:url value="/"/>{{image.imageUrl}}"></a>
   					 	{{/image}}
    					</div>

						<!--  *** Product Name & Price Starts *** -->
						<div class="caption">
							<div class="product-details">
								<a class="listing-product-name" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html"><h3 itemprop="name">{{description.name}}</h3></a>
									<!--<span class="text-center width-100"><div class="stars" id="productRating_{{id}}"></div></span>-->
								<h4>
									{{#discounted}}<del>{{originalPrice}}</del>&nbsp;<span itemprop="price" class="specialPrice">{{finalPrice}}</span>{{/discounted}}
									{{^discounted}}<span itemprop="price">{{finalPrice}}</span>{{/discounted}}
								</h4>

								<!-- Product Buttons Starts -->
								<div class="clearfix">
									<a class="btn btn-detail pull-left" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html" class="details"><s:message code="button.label.view" text="Details" /></a>
									<c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">
										{{#canBePurchased}}<a class="btn btn-buy pull-right addToCart" href="javascript:void(0);" class="addToCart" productId="{{id}}"><s:message code="button.label.addToCart" text="Add to cart" /></a>{{/canBePurchased}}
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			{{/products}}
    </script>            
    
 	</head>
 
 	<body>
 	
 	     <!--[if lt IE 8]>
            <p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

		<tiles:insertAttribute name="header" ignore="true"/>
	
		<tiles:insertAttribute name="navbar" ignore="true"/>
	
		<tiles:insertAttribute name="body" ignore="true"/>
	
		<tiles:insertAttribute name="footer" ignore="true"/>

	<jsp:include page="/pages/shop/templates/ambroselar/sections/jsLinks.jsp" />

 	</body>
 
 </html>
 
