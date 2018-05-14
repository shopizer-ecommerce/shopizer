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
	 
	       <div style="margin-top: 5px;" class="banner center-block">
			    
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
	               	    	<a href="https://ambroselli.art/shop/category/oil-painting.html/ref=c:100,50" data-p="170.00">
			                	<img data-u="image" src="/resources/img/banner/coogeebeach_web.jpg" />
			                	<div style="position:absolute;top:50px;left:50px;width:300px;height:150px;"></div>
			            	</a>
			            </div>
	     		        <div data-p="170.00">		             
				            <a href="https://ambroselli.art/shop/category/oil-painting.html/ref=c:100,50" data-p="170.00">
				                <img data-u="image" src="/resources/img/banner/compositiondurer_web.jpg" />
				            </a>
				        </div>
	     		        <div data-p="170.00">
				            <a href="https://ambroselli.art/shop/category/oil-painting.html/ref=c:100,50" data-p="170.00">
				                <img data-u="image" src="/resources/img/banner/figtree_web.jpg" />
				            </a>
				        </div>
	     		        <div data-p="170.00">
				            <a href="https://ambroselli.art/shop/category/oil-painting.html/ref=c:100,50" data-fillmode="0" data-p="170.00">
				                <img data-u="image" src="/resources/img/banner/tamarama_web.jpg" />
				            </a>
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

	<c:if test="${page!=null}">
		<div class="row-fluid">
	   		    <div class="span12">
	   			    <span id="homeText"><c:out value="${page.description}" escapeXml="false"/></span>
	   		    </div>
		</div>
		<br/>
	</c:if>
			

	<div class="row text-center" style="margin-top: 5px;" >
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-process">
					<!-- process Icon -->
					<div class="picon">
						<i class="fa fa-truck"></i>
					</div>
					<!-- process Content -->
					<div class="process_content">
						<h3>free shipping</h3>
						<p>Shipping Free In Australia</p>
					</div>
				</div>
			</div>
			<!-- End Col -->
	
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-process">
					<!-- process Icon -->
					<div class="picon">
						<i class="fa fa-money"></i>
					</div>
					<!-- process Content -->
					<div class="process_content">
						<h3>Easy and Simple</h3>
						<p></p>
					</div>
				</div>
			</div>
			<!-- End Col -->
	
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-process">
					<!-- process Icon -->
					<div class="picon">
						<i class="fa fa-headphones "></i>
					</div>
					<!-- process Content -->
					<div class="process_content">
						<h3>Support 24/7</h3>
						<a href="https://ambroselli.art/shop/store/contactus.html"><p>Please contact us</p></a>
					</div>
				</div>
			</div>
			<!-- End Col -->
	
			<div class="col-lg-3 col-md-6 col-sm-6">
				<div class="single-process">
					<!-- process Icon -->
					<div class="picon">
						<i class="fa fa-clock-o"></i>
					</div>
					<!-- process Content -->
					<div class="process_content">
						<h3>Fast delivery</h3>
						<p></p>
					</div>
				</div>
			</div>
			<!-- End Col -->
	</div>
			
			
			
			
			<br/>
			<sm:shopProductGroup groupName="FEATURED_ITEM"/>
			<sm:shopProductGroup groupName="SPECIALS"/>
			
			<c:if test="${requestScope.FEATURED_ITEM!=null || requestScope.SPECIALS!=null}" >
			<div class="row-fluid">
				<div class="span12">
					<ul class="nav nav-tabs home" id="product-tab">
						<c:if test="${requestScope.FEATURED_ITEM!=null}" ><li class="active"><a href="#tab1"><s:message code="menu.catalogue-featured" text="Featured items" /></a></li></c:if>
						<c:if test="${requestScope.SPECIALS!=null}" ><li<c:if test="${requestScope.FEATURED_ITEM==null}"> class="active"</c:if>><a href="#tab2"><s:message code="label.product.specials" text="Specials" /></a></li></c:if>
					</ul>							 
					<div class="tab-content">
						<!-- one div by section -->
						<c:if test="${requestScope.FEATURED_ITEM!=null}" >
						
						<div class="tab-pane active" id="tab1">
									<ul class="thumbnails product-list">
									    <!-- Iterate over featuredItems -->
										<c:set var="ITEMS" value="${requestScope.FEATURED_ITEM}" scope="request" />
										<c:set var="FEATURED" value="true" scope="request" />
	                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
									</ul>
									
						</div>
						</c:if>
						<c:if test="${requestScope.SPECIALS!=null}" >
						<div class="tab-pane <c:if test="${requestScope.FEATURED_ITEM==null}">active</c:if>" id="tab2">
									<ul class="thumbnails product-list">
										<!-- Iterate over featuredItems -->
                         				<c:set var="ITEMS" value="${requestScope.SPECIALS}" scope="request" />
	                         			<jsp:include page="/pages/shop/templates/exoticamobilia/sections/productBox.jsp" />
									</ul>
						</div>
						</c:if>

					</div>							
				</div>
			</div>
			</c:if>

			
		