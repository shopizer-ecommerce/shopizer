<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-button.js" />"></script>
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-modal.js" />"></script>
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-tab.js" />"></script>
    <!--<script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-transition.js" />"></script>-->
    <!--<script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-alert.js" />"></script>-->
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-dropdown.js" />"></script>
    <!--<script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-scrollspy.js" />"></script>-->
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-tooltip.js" />"></script>
    <!--<script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-popover.js" />"></script>-->
    <script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-collapse.js" />"></script>
    <!--<script src="<c:url value="/resources/templates/bootstrap/js/bootstrap-carousel.js" />"></script>-->


        <script type="text/javascript">

            $('#product-tab a:first').tab('show');
            $('#product-tab a').click(function (e) {
            		e.preventDefault();
            		$(this).tab('show');
            }) 
            
            
        </script>
     

    