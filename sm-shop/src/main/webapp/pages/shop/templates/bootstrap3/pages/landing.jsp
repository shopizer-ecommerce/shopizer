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
 
         <!-- BEGIN  TOP SLIDER -->
        <div class="topslider">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class=""></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1" class=""></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2" class="active"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="item animated fadeInUp">
                        <img alt="First slide" src="<c:out value="/sm-shop/resources/templates/bootstrap3/img/slide_1.jpg"/>">
                        <div class="caption">
                            <div class=" slider-title animated fadeInDown delay1">GET YOUR DECORATION</div>
                            <div class="hidden-xs delay2 slider-desc animated fadeIn">A slider for your home page</div>
                            <div class="slider-button animated fadeInUp delay3">
                                <a href="javascript:;">PURCHASE</a>
                            </div>
                        </div>
                    </div>
                    <div class="item  animated fadeInUp">
                        <img alt="Second slide" src="<c:out value="/sm-shop/resources/templates/bootstrap3/img/slide_2.jpg"/>">
                        <div class="caption">
                            <div class=" slider-title animated fadeInDown delay1">GET YOUR DECORATION</div>
                            <div class="hidden-xs delay2 slider-desc animated fadeIn">Edit landing.jsp to remove the slider code</div>
                            <div class="slider-button animated fadeInUp delay3">
                                <a href="javascript:;">PURCHASE</a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <!-- END SLIDER -->
 </div>
 
 
 
 
 
 

			<c:if test="${page!=null}">
				<div id="shop" class="">
	          			    <span id="homeText"><c:out value="${page.description}" escapeXml="false"/></span>
				</div>
			</c:if>

			
			<br/>
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			
			<div id="" class="container">
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
		
		
</div>

