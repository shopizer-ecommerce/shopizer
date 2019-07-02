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

        <!-- home banner -->
        <!-- slider-area-start -->
		<div class="slider-area">
		<c:choose>
		<c:when test="${requestScope.CONTENT['bannerImage']!=null}">
			<sm:pageContent contentCode="bannerImage"/>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>

		</div>
		<!-- slider-area-end -->
		
		<!-- home page - top category CUSTOM CONTENT in home page -->
		<c:if test="${page!=null}">
			<div class="service-area pt-80">
				<div class="container">
	          		<c:out value="${page.description}" escapeXml="false"/>
				</div>
			</div>
	    </c:if>

	    <c:if test="${requestScope.CONTENT['homeMessage']!=null}">
	    	<div class="service-area pt-80">
				<div class="container">
			    	<sm:pageContent contentCode="homeMessage"/>
			  	</div>
			</div>
		</c:if>

		

		<!-- Offers -->
		<!-- Sale off -->
		<!-- An idea of offer area -->
		<!--
	    <section style="margin-top:50px !important;">
	        <div class="container">
	            <div class="row au-row">
	                <div class="col-md-6">
	                    <div class="au-banner style-9 js-au-banner" style="height: 533px; background-image: url('/static/files/DEFAULT/IMAGE/banner-image-10.jpg');">
	                        <a href="#" class="banner-image"></a>
	                        <div class="banner-content">
	                            <h3 class="title">
	                                today only <span>20</span>
	                            </h3>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <div class="au-banner style-7 banner-medium js-au-banner" style="height: 245px; background-image: url('/static/files/DEFAULT/IMAGE/banner-image-08.jpg');">
	                        <a href="#"></a>
	                        <div class="banner-content text-right">
	                            <h3 class="title">New item</h3>
	                            <h4 class="sub-title">Punch line</h4>
	                            <a href="#" class="banner-btn">shop now</a>
	                        </div>
	                    </div>
	                    <div class="au-banner style-8 banner-medium js-au-banner " style="height: 245px; background-image: url('/static/files/DEFAULT/IMAGE/banner-image-09.jpg');">
	                        <a href="#"></a>
	                        <div class="banner-content">
	                            <h3 class="title">Another item</h3>
	                            <h4 class="sub-title">Another item punch line</h4>
	                            <a href="#" class="banner-btn">shop now</a>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
	    -->
	    <!--  -->


		
		<!-- featured items -->
		<!-- Using ajax -->
		
		
		<!-- service-area-end -->
		<!--new-product-area-start -->
		<c:if test="${requestScope.FEATURED_ITEM!=null}">
		<div class="new-product-area pt-80 pb-20">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="section-title text-center">
							<h2><s:message code="menu.catalogue-featured" text="Featured items" /></h2>
						    <c:if test="${requestScope.CONTENT['featuredItemsText']!=null}">
			    				<sm:pageContent contentCode="featuredItemsText"/>
		        			</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:if>
		
		
		<!-- featured items -->
		<section class="products-grid">
			<div class="container">
				<div id="featuredItemsContainer" class="row products-container"></div>
			</div>
		</section>

		
		
		<!-- PUT BACK LATEST BLOGS -->
		
		

		
		<!-- signup box -->
		<!-- purchase-progress-area-end -->
		<!-- contact-area-start -->
		<!--
		<div class="contact-area ptb-40">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 mar_b-30">
						<div class="contuct-info text-center">
							<h4>Sign up for news & offers!</h4>
							<p>You may safely unsubscribe at any time</p>
						</div>
					</div>
					<div class="col-lg-6 col-md-8 col-sm-12 col-lg-offset-1 col-xs-12">
						<div class="search-box">
							<form action="#">
								<input type="email" placeholder="Enter your email address"/>
								<button><span class="lnr lnr-envelope"></span></button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		-->
		<!-- contact-area-end -->
		
	<!-- load products -->	
	<script>
	       $(document).ready(function() {
	    	   
	    	   $('#featuredItemsContainer').LoadingOverlay("show");
	    	   
				var tpl = $('#productBoxTemplate').text();
			    tpl = tpl.replace("COLUMN-SIZE", "3");
			    $('#productBoxTemplate').text(tpl);

				//get products
				loadFeaturedItems();

          })
		  
		  
		  
		  function loadFeaturedItems() {
		  	$.ajax({
				type: 'GET',
				dataType: "json",
				url: '<c:url value="/"/>services/public/<c:out value="${requestScope.MERCHANT_STORE.code}"/>/products/group/FEATURED_ITEM',
				success: function(productList) {

					//set in slider
					var productsTemplate = Hogan.compile(document.getElementById("productBoxTemplate").innerHTML);
					var productsRendred = productsTemplate.render(productList);
					$('#featuredItemsContainer').append(productsRendred);
					$('#featuredItemsContainer').LoadingOverlay("hide", true);
					//call init bindings
					initBindings();
					setProductRating(productList.products);
				},
				error: function(jqXHR,textStatus,errorThrown) { 
					$(divProductsContainer).hideLoading();
					alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
				}
			});
		  }
	       
	        
	       
          </script>
		  <!--- END -->
		
		