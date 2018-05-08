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

<%-- 	       	<c:if test="${requestScope.CONTENT['banner']!=null}"> --%>
<%-- 				<sm:pageContent contentCode="banner"/> --%>
<%-- 			</c:if> --%>

			<!-- #region Jssor Slider Begin -->
	 	   	<!-- Generator: Jssor Slider Maker -->
		    <!-- Source: https://www.jssor.com -->
<%-- 		    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script> --%>
<%-- 		    <script src="js/jssor.slider-27.1.0.min.js" type="text/javascript"></script> --%>
		    
		    <script src="<c:url value="/resources/js/jquery-1.11.3.min.js" />"></script>
		    <script	src="<c:url value="/resources/js/jssor.slider-27.1.0.min.js" />"></script>
		    
		    
		    <script type="text/javascript">
		        jQuery(document).ready(function ($) {
		
		            var jssor_1_options = {
		              $AutoPlay: 1,
		              $Idle: 2000,
		              $ArrowNavigatorOptions: {
		                $Class: $JssorArrowNavigator$
		              },
		              $BulletNavigatorOptions: {
		                $Class: $JssorBulletNavigator$
		              }
		            };
		
		            var jssor_1_slider = new $JssorSlider$("jssor_1", jssor_1_options);
		
		            /*#region responsive code begin*/
		
		            var MAX_WIDTH = 980;
		
		            function ScaleSlider() {
		                var containerElement = jssor_1_slider.$Elmt.parentNode;
		                var containerWidth = containerElement.clientWidth;
		
		                if (containerWidth) {
		
		                    var expectedWidth = Math.min(MAX_WIDTH || containerWidth, containerWidth);
		
		                    jssor_1_slider.$ScaleWidth(expectedWidth);
		                }
		                else {
		                    window.setTimeout(ScaleSlider, 30);
		                }
		            }
		
		            ScaleSlider();
		
		            $(window).bind("load", ScaleSlider);
		            $(window).bind("resize", ScaleSlider);
		            $(window).bind("orientationchange", ScaleSlider);
		            /*#endregion responsive code end*/
		        });
		    </script>
		    <style>
		        /*jssor slider loading skin spin css*/
		        .jssorl-009-spin img {
		            animation-name: jssorl-009-spin;
		            animation-duration: 1.6s;
		            animation-iteration-count: infinite;
		            animation-timing-function: linear;
		        }
		
		        @keyframes jssorl-009-spin {
		            from { transform: rotate(0deg); }
		            to { transform: rotate(360deg); }
		        }
		
		        /*jssor slider bullet skin 052 css*/
		        .jssorb052 .i {position:absolute;cursor:pointer;}
		        .jssorb052 .i .b {fill:#000;fill-opacity:0.3;}
		        .jssorb052 .i:hover .b {fill-opacity:.7;}
		        .jssorb052 .iav .b {fill-opacity: 1;}
		        .jssorb052 .i.idn {opacity:.3;}
		
		        /*jssor slider arrow skin 053 css*/
		        .jssora053 {display:block;position:absolute;cursor:pointer;}
		        .jssora053 .a {fill:none;stroke:#fff;stroke-width:640;stroke-miterlimit:10;}
		        .jssora053:hover {opacity:.8;}
		        .jssora053.jssora053dn {opacity:.5;}
		        .jssora053.jssora053ds {opacity:.3;pointer-events:none;}
		    </style>
		    <div id="jssor_1" style="position:relative;margin:0 auto;top:0px;left:0px;width:980px;height:380px;overflow:hidden;visibility:hidden;">
		        <!-- Loading Screen -->
		        <div data-u="loading" class="jssorl-009-spin" style="position:absolute;top:0px;left:0px;width:100%;height:100%;text-align:center;background-color:rgba(0,0,0,0.7);">
		            <img style="margin-top:-19px;position:relative;top:50%;width:38px;height:38px;" src="img/spin.svg" />
		        </div>
		        <div data-u="slides" style="cursor:default;position:relative;top:0px;left:0px;width:980px;height:380px;overflow:hidden;">
		            <div data-p="170.00">
		                <img data-u="image" src="<c:url value="/resources/img/banner/tamarama1.jpg" />">
		               
		            </div>
		            <div data-p="170.00">
		                <img data-u="image"  src="<c:url value="/resources/img/banner/clovely1.jpg" />" >
		            </div>
		        </div>
		        <!-- Bullet Navigator -->
		        <div data-u="navigator" class="jssorb052" style="position:absolute;bottom:12px;right:12px;" data-autocenter="1" data-scale="0.5" data-scale-bottom="0.75">
		            <div data-u="prototype" class="i" style="width:16px;height:16px;">
		                <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
		                    <circle class="b" cx="8000" cy="8000" r="5800"></circle>
		                </svg>
		            </div>
		        </div>
		        <!-- Arrow Navigator -->
		        <div data-u="arrowleft" class="jssora053" style="width:55px;height:55px;top:0px;left:25px;" data-autocenter="2" data-scale="0.75" data-scale-left="0.75">
		            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
		                <polyline class="a" points="11040,1920 4960,8000 11040,14080 "></polyline>
		            </svg>
		        </div>
		        <div data-u="arrowright" class="jssora053" style="width:55px;height:55px;top:0px;right:25px;" data-autocenter="2" data-scale="0.75" data-scale-right="0.75">
		            <svg viewbox="0 0 16000 16000" style="position:absolute;top:0;left:0;width:100%;height:100%;">
		                <polyline class="a" points="4960,1920 11040,8000 4960,14080 "></polyline>
		            </svg>
		        </div>
		    </div>
		    <!-- #endregion Jssor Slider End -->

       </div>


</div>
				
			    <c:if test="${requestScope.CONTENT['homeMessage']!=null}">
			    <sm:pageContent contentCode="homeMessage"/>
		        </c:if>

        							

<div class="main">
			<c:if test="${page!=null}">
				<div id="" class="container">
	          			    <c:out value="${page.description}" escapeXml="false"/>
				</div>
			</c:if>

			
			<br/>
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			 
			<div id="" class="container">
			<c:if test="${requestScope.FEATURED_ITEM!=null || requestScope.SPECIALS!=null}" >							
						<!-- one div by section -->
						<c:if test="${requestScope.FEATURED_ITEM!=null}" >
							<h2 class="hTitle"><s:message code="menu.catalogue-featured" text="Featured items" /></h2>
										    <!-- Iterate over featuredItems -->
											<c:set var="ITEMS" value="${requestScope.FEATURED_ITEM}" scope="request" />
											<c:set var="FEATURED" value="true" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
						</c:if>
						<c:if test="${requestScope.SPECIALS!=null}" >
							<h2 class="hTitle"><s:message code="label.product.specials" text="Specials" /></h2>
											<!-- Iterate over featuredItems -->
	                         				<c:set var="ITEMS" value="${requestScope.SPECIALS}" scope="request" />
		                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
						</c:if>	
			</c:if>
			</div>
			
			<div class="container">
				
			</div>
		
		
</div>
</div>