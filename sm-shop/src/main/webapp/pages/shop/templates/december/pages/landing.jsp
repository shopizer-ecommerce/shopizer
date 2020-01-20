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
		<c:choose>
		<c:when test="${requestScope.CONTENT['bannerImage']!=null}">
			<sm:pageContent contentCode="bannerImage"/>
		</c:when>
		<c:otherwise>
		</c:otherwise>
		</c:choose>

		
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
		
		