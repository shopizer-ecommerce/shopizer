<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>

    <!-- specific js files -->
    <!-- do only change the list of js files -->
    <!-- ////////////// -->

    
    
    <script src="<c:url value="/resources/templates/december/js/bootstrap.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/owl.carousel.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/jquery.meanmenu.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/jquery-ui.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/slippry.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/wow.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/jquery.scrolly.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/loadingoverlay.min.js" />"></script>
    
    
    <script src="<c:url value="/resources/templates/december/js/jquery.magnific-popup.min.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/plugins.js" />"></script>
    <script src="<c:url value="/resources/templates/december/js/starr.js" />"></script>

    
    
    
    <script src="<c:url value="/resources/templates/december/js/main.js" />"></script>
  
    
    
    <!-- required common scripts for shopizer -->
    <script src="<c:url value="/resources/js/jquery.raty.min.js" />"></script>
	<script src="<c:url value="/resources/js/shop-functions.js" />"></script>
	<script src="<c:url value="/resources/js/shop-customer.js" />"></script>
    <jsp:include page="/resources/js/functions.jsp" />
    <script src="<c:url value="/resources/js/json2.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-cookie.js" />"></script>
    <script src="<c:url value="/resources/js/shop-minicart.js" />"></script>
    <script src="<c:url value="/resources/js/shop-account.js" />"></script>
    <script src="<c:url value="/resources/js/login.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.showLoading.min.js" />"></script>
    
    <c:if test="${requestScope.CONTENT['heroSlider']!=null}">
		  <script defer src="/resources/js/jquery.flexslider.js"></script>
		  <script type="text/javascript">
		    $(window).load(function(){
		      $('.flexslider').flexslider({
		        animation: "slide",
		        start: function(slider){
		          $('body').removeClass('loading');
		        }
		      });
		    });
		  </script>
	</c:if>


	<!-- ////////////// -->


     

   