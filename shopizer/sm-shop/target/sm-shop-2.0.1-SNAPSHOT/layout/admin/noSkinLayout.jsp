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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  
 <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
 <c:set var="lang" scope="request" value="${requestScope.locale.language}"/> 
 
 
 <html xmlns="http://www.w3.org/1999/xhtml"> 
 
 
     <head>
     
     
        	 	<meta charset="utf-8">
    			<title><s:message code="label.storeadministration" text="Store administration" /></title>
    			<meta name="viewport" content="width=device-width, initial-scale=1.0">
    			<meta name="description" content="">
    			<meta name="author" content="">
    			
    			<script src="<c:url value="/resources/js/bootstrap/jquery.js" />"></script>
    			<script src="<c:url value="/resources/js/jquery.friendurl.min.js" />"></script>
 
  
                <jsp:include page="/common/adminLinks.jsp" />
                
                


 	
 	
 	</head>
 
 	<body class="body">

     <p>&nbsp;</p>

<div class="sm">

	<!-- <div class="container">-->
	<div class="span6">
		<!--<div class="row">-->
				<tiles:insertAttribute name="body"/>
		<!--</div>-->
  
  
	</div> <!-- /container --> 
	

  
</div>

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-button.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-modal.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-tab.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-transition.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-alert.js" />"></script>
    
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-dropdown.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-scrollspy.js" />"></script>
    
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-tooltip.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-popover.js" />"></script>
    
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-collapse.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-carousel.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap/bootstrap-typeahead.js" />"></script>
     

    
 
 	</body>
 
 </html>
 
