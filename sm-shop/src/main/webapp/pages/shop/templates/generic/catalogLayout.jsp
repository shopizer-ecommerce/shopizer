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
                <jsp:include page="/pages/shop/templates/generic/sections/shopLinks.jsp" />
                
                
                
    <script type="text/html" id="productBoxTemplate">
			{{#products}}
                        <div itemscope itemtype="http://schema.org/Enumeration" class="col-md-COLUMN-SIZE col-sm-6 col-xs-12 product" item-order="{{sortOrder}}" item-name="{{description.name}}" item-price="{{price}}" data-id="{{id}}">
								<div class="thumbnail product-img">
                                    {{#image}}
									<a href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html">
										<img src="<c:url value=""/>{{image.imageUrl}}" alt="" />
									</a>
									{{/image}}
								</div>
								<div class="product-content text-center">
									<a class="listing-product-name" href="<c:url value="/shop/product/" />{{description.friendlyUrl}}.html"><h3 itemprop="name">{{description.name}}</h3></a>
									<!--<span class="text-center width-100"><div class="stars" id="productRating_{{id}}"></div></span>-->
									<h4>
										{{#discounted}}<del>{{originalPrice}}</del>&nbsp;<span itemprop="price" class="specialPrice">{{finalPrice}}</span>{{/discounted}}
										{{^discounted}}<span itemprop="price">{{finalPrice}}</span>{{/discounted}}
									</h4>
									<c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">
									<div class="store-btn">
      									<div class="store-btn-addtocart"><a class="addToCart" href="javascript:void(0)" productId="{{id}}"><s:message code="button.label.addToCart" text="Add to cart"/></a></div>
   									</div>
									</c:if>
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

	<jsp:include page="/pages/shop/templates/generic/sections/jsLinks.jsp" />

 	</body>
 
 </html>
 
