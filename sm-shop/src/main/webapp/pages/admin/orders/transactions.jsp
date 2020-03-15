<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
<%@ page session="false" %>				
				

<link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>
<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
<script src="<c:url value="/resources/js/jquery.formatCurrency-1.4.0.js" />"></script>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
<script src="<c:url value="/resources/js/adminFunctions.js" />"></script>

<script src="<c:url value="/resources/js/jquery.showLoading.min.js" />"></script>
<link href="<c:url value="/resources/css/showLoading.css" />" rel="stylesheet">	



<div class="tabbable">

		<jsp:include page="/common/adminTabs.jsp" />
					
		<div class="tab-content">

  		<div class="tab-pane active" id="order-section">

		<div class="sm-ui-component">	


		<h3>
			<div class="control-group">
                      <div class="controls">
                     		 Transactions - <s:message code="label.order.id2" text="Order ID"/> 
                     		 <c:out value="${order.order.id}" /><br>
                       </div>       
                  </div>
             </div>
             
             
        </h3>
		<br/>
	    <br/>
 	    
      </div>
	  </div>
  	  </div>

</div>


     			     