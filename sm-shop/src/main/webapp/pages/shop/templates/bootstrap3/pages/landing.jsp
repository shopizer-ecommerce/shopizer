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


 
 
<div id="mainContent" class="container nopadding-left nopadding-right">

			<c:if test="${page!=null}">
			<div id="shop" class="row homeTextRow">
          			    <span id="homeText"><c:out value="${page.description}" escapeXml="false"/></span>
			</div>
			</c:if>
			
			

			


<!--
    <div class="slider-content">
      <ul id="pager2" class="container">
      <span class="">•</span><span class="">•</span><span class="">•</span><span class="">•</span><span class="cycle-pager-active">•</span></ul>
  
      <span class="prevControl sliderControl"> <i class="fa fa-angle-left fa-3x "></i></span> <span class="nextControl sliderControl"> <i class="fa fa-angle-right fa-3x "></i></span>
      <div style="overflow: hidden;" class="slider slider-v1" data-cycle-swipe="true" data-cycle-prev=".prevControl" data-cycle-next=".nextControl" data-cycle-loader="wait"><div style="visibility: hidden; position: static; top: 0px; left: 0px; z-index: 100; opacity: 1; display: block;" class="slider-item slider-item-img1 cycle-slide cycle-sentinel">
          
          <img style="visibility: hidden; margin-top: 0px;" src="<c:url value="/resources/templates/bootstrap3/" />slider0.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div>

      <div style="visibility: hidden; position: absolute; top: 0px; left: 0px; z-index: 97; opacity: 1; display: block;" class="slider-item slider-item-img1 cycle-slide">
          
          <img style="margin-top: 0px;" src="home-apralax_files/slider0.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div><div style="visibility: hidden; position: absolute; top: 0px; left: 0px; z-index: 96; opacity: 1; display: block;" class="slider-item slider-item-img1 cycle-slide">
          <div class="sliderInfo">
            <div class="container">
              <div class="col-lg-12 col-md-12 col-sm-12 sliderTextFull ">
                <div class="inner text-center">
                  <div class="topAnima animated">
                    <h1 class="uppercase xlarge">FREE SHIPPING</h1>
                    <h3 class="hidden-xs"> Free Standard Shipping on Orders Over $100 </h3>
                  </div>
                  <a class="btn btn-danger btn-lg bottomAnima animated opacity0">SHOP NOW ON TSHOP <span class="arrowUnicode">►</span></a> </div>
              </div>
            </div>
          </div>
          <img style="margin-top: 0px;" src="<c:url value="/resources/templates/bootstrap3/" />slider1.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div><div style="visibility: hidden; position: absolute; top: 0px; left: 0px; z-index: 95; opacity: 1; display: block;" class="slider-item slider-item-img2  cycle-slide">
          <div class="sliderInfo">
            <div class="container">
              <div class="col-lg-12 col-md-12 col-sm-12 sliderTextFull  ">
                <div class="inner dark maxwidth500 text-center animated topAnima">
                  <div class=" ">
                    <h1 class="uppercase xlarge"> CUSTOM HTML BLOCK</h1>
                    <h3 class="hidden-xs">  Custom Slides to Your Slider  </h3>
                    
                  </div>
                  <a class="btn btn-danger btn-lg">SHOP NOW ON TSHOP <span class="arrowUnicode">►</span></a> </div>
              </div>
            </div>
          </div>
          <img style="margin-top: 0px;" src="<c:url value="/resources/templates/bootstrap3/" />slider3.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div><div style="visibility: hidden; position: absolute; top: 0px; left: 0px; z-index: 94; opacity: 1; display: block;" class="slider-item slider-item-img3  cycle-slide">
          <div class="sliderInfo">
            <div class="container">
              <div class="col-lg-5 col-md-4 col-sm-6 col-xs-8   pull-left sliderText white hidden-xs">
                <div class="inner">
                  <h1>TSHOP JEANS</h1>
                  <h3 class="price "> Free Shipping on $100</h3>
                  <p class="hidden-xs">Lorem ipsum dolor sit amet, 
consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut
 laoreet dolore magna aliquam erat volutpat. </p>
                  <a href="http://demo.tanimdesign.net/template/tshop-v1.1/category.html" class="btn btn-primary">SHOP NOW <span class="arrowUnicode">►</span></a> </div>
              </div>
            </div>
          </div>
          <img style="margin-top: 0px;" src="<c:url value="/resources/templates/bootstrap3/" />slider4.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div><div style="visibility: visible; position: absolute; top: 0px; left: 0px; z-index: 99; opacity: 1; display: block;" class="slider-item slider-item-img3 cycle-slide cycle-slide-active">
          <div class="sliderInfo">
            <div class="container">
              <div class="col-lg-5 col-md-6 col-sm-5 col-xs-5 pull-left sliderText blankstyle transformRight">
                <div class="inner text-right"> <img src="home-apralax_files/color.png" class="img-responsive" alt="img"> </div>
              </div>
              <div class="col-lg-4 col-md-4 col-sm-5 col-xs-7   pull-left sliderText blankstyle color-white">
                <div class="inner">
                  <h1 class="uppercase topAnima animated ">10+ Amazing Color Theme</h1>
                  <p class="bot tomAnima animated opacity0 hidden-xs"> Fully responsive bootstrap Ecommerce Template. Available in 10+ color schemes and easy to set. </p>
                </div>
              </div>
            </div>
          </div>
          <img style="margin-top: 0px;" src="home-apralax_files/6.jpg" class="img-responsive parallaximg sliderImg" alt="img"> </div></div>

    </div>
    -->
    <!--/.slider-content--> 

			
			
			
			
			
			
			
			
			
			
			<br/>
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			
			<c:if test="${requestScope.FEATURED_ITEM!=null || requestScope.SPECIALS!=null}" >
			<div class="row">
					<ul class="nav nav-tabs home" id="product-tab">
						<c:if test="${requestScope.FEATURED_ITEM!=null}" ><li class="active"><a data-toggle="tab" href="#tab1"><s:message code="menu.catalogue-featured" text="Featured items" /></a></li></c:if>
						<c:if test="${requestScope.SPECIALS!=null}" ><li<c:if test="${requestScope.FEATURED_ITEM==null}"> class="active"</c:if>><a data-toggle="tab" href="#tab2"><s:message code="label.product.specials" text="Specials" /></a></li></c:if>
					</ul>							 
					<div class="tab-content">
						<!-- one div by section -->
						<c:if test="${requestScope.FEATURED_ITEM!=null}" >
							<div class="tab-pane fade <c:if test="${requestScope.FEATURED_ITEM!=null}" >active</c:if> in" id="tab1">
										    <!-- Iterate over featuredItems -->
											<c:set var="ITEMS" value="${requestScope.FEATURED_ITEM}" scope="request" />
											<c:set var="FEATURED" value="true" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/bootstrap3/sections/productBox.jsp" />
							</div>
						</c:if>
						<c:if test="${requestScope.SPECIALS!=null}" >
							<div class="tab-pane fade <c:if test="${requestScope.FEATURED_ITEM==null}" >active</c:if> in" id="tab2">
											<!-- Iterate over featuredItems -->
	                         				<c:set var="ITEMS" value="${requestScope.SPECIALS}" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/bootstrap3/sections/productBox.jsp" />
							</div>
						</c:if>
				</div>							
			</div>
			</c:if>
			
		
		
</div>

