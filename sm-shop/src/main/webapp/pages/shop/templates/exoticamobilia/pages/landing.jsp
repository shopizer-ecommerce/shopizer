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


 <div id="shop" class="">
 
       <div style="margin-top: 0px;" class="banner center-block">

      <!-- Responsive slider - START -->
    	<div class="responsive-slider" data-spy="responsive-slider" data-autoplay="false">
        <div class="slides" data-group="slides">
      		<ul>
  	    	<li>
              <div class="slide-body" data-group="slide">
	                <img src="<c:out value="/sm-shop/static/DEFAULT/IMAGE/table-blanche-2.jpg"/>" id="slide1">

			                	<div class="bannerTextTitle bannerTextColor" style="width:500px; top:30px; left:20px;float:left;">
			                        <h2 class="bannerTextStyle helvetica-light bannerMarginBottom">
			                        	Entrepot de meubles exotiques
			                        </h2>
			                        <p class="bannerTextParagraphStyle helvetica-light">Bois de rose, Acacia, Suar, Racine de teck, Mango, Recyclés, Métal et bien plus</p>
			                        <a class="white bannerRedBtn helvetica-light" href="/living-room-collections/asana-collection/" title="Shop the Suar Collection">Shop the Suar Collection <i class="fa fa-play"></i></a>
			                	</div>


					</div>
					
	                <!--<div class="caption header center" style="width:100% !important;">-->
	                <!--<div class="caption header" style="width:100% !important;height:60% !important;text-align: center; !important;">-->
	                  <!--<div class="header-white header-caption" style="padding: 30px 0 !important; font-size: 50px;">Mega centre de liquidation</div>-->
	                  <!--
	                  <div class="header-white header-caption" style="text-align: center;">Entrepot de meubles exotiques</div>
	                  <div class="sub-header sub-header-white header-caption" style="text-align: center;">
	                  		
	                  		<div style="width:100%;">
	                  			
	                  			Bois de rose - Acacia
	                  			<br>
	                  			Suar - Racine de teck - Mango
	                  			</br>
	                  			Recycles - Metal - et bien plus...</br>
	                  			
	                  		</div>

	                  </div>
	                  <div class="sub-sub-header sub-header-white header-caption" style="text-align: center;">
	                  <span style="background-color: #FFFF00;color:#000000">30% a 50%</span> de rabais sur tous les articles a prix regulier<br/>
	                  <span style="background-color: #FFFF00;color:#000000">Jusqu'a 70%</span> de rabais sur les articles de fin de ligne
	                  </div>
	                </div>
	                -->
                
	      		</div>
  	        </li>
  	    <%-- 	<li>
              <div class="slide-body" data-group="slide">
                <img src="images/table-brune1.jpg">
              </div>
  	        </li>
  	    	<li>
              <div class="slide-body" data-group="slide">
                <img src="images/table-brune1.jpg">
              </div>
  	        </li>
  	        --%>

  	    	</ul>
        </div>

    	</div>

      <!-- Responsive slider - END -->
     </div>
			
	<!-- banner end -->
</div>

<div class="main">
			<c:if test="${page!=null}">
			<div class="row">
				<div id="shop" class="col-md-12">
	          			    <h1 class="text-center title" id="homeText"><c:out value="${page.description}" escapeXml="false"/></h1>
	          			    <div class="separator"></div>
				</div>
				</div>
			</c:if>

			
			<br/>
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			
			<div id="" class="container">
			<c:if test="${requestScope.FEATURED_ITEM!=null || requestScope.SPECIALS!=null}" >
			<div class="row-exoticamobilia row">
			     <div class="productItem tabs-style-2">
					<ul class="nav nav-tabs" id="product-tab">
						<c:if test="${requestScope.FEATURED_ITEM!=null}" ><li class="active "><a data-toggle="tab" href="#tab1"><s:message code="menu.catalogue-featured" text="Featured items" /></a></li></c:if>
						<c:if test="${requestScope.SPECIALS!=null}" ><li<c:if test="${requestScope.FEATURED_ITEM==null}"> class="active"</c:if>><a data-toggle="tab" href="#tab2"><s:message code="label.product.specials" text="Specials" /></a></li></c:if>
					</ul>
					</div>							
				<div class="tab-content padding-top-clear padding-bottom-clear">	 
						<!-- one div by section -->
						<c:if test="${requestScope.FEATURED_ITEM!=null}" >
							<div class="tab-pane fade <c:if test="${requestScope.FEATURED_ITEM!=null}" >active</c:if> in" id="tab1">
										    <!-- Iterate over featuredItems -->
											<c:set var="ITEMS" value="${requestScope.FEATURED_ITEM}" scope="request" />
											<c:set var="FEATURED" value="true" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
							</div>
						</c:if>
						<c:if test="${requestScope.SPECIALS!=null}" >
							<div class="tab-pane fade <c:if test="${requestScope.FEATURED_ITEM==null}" >active</c:if> in" id="tab2">
											<!-- Iterate over featuredItems -->
	                         				<c:set var="ITEMS" value="${requestScope.SPECIALS}" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
							</div>
						</c:if>	
				</div>					
			</div>
			</c:if>
			</div>
		
		
</div>
</div>
