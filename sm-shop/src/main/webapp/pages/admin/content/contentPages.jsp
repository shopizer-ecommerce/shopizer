<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>
<div class="tabbable">
   <jsp:include page="/common/adminTabs.jsp" />
	<div class="tab-content">
		<div class="tab-pane active" id="catalogue-section">
           <div class="sm-ui-component">
           		
           		<h3>
           		<c:choose>
           			<c:when test="${boxes==true}">
           				<s:message code="label.content.boxes" text="Content boxes" />
           			</c:when>
           			<c:otherwise>
           				<s:message code="label.content.pages" text="Content pages" />
           			</c:otherwise>
           		</c:choose>
				</h3>
				
				

			
				
				
				
				<br />
				
			  <c:choose>
           			<c:when test="${boxes==true}">
           			
           			    <c:set value="/admin/content/page.html?contentType=BOX" var="pagingUrl" scope="request" />
						<c:set value="/admin/content/remove.html" var="removeUrl" scope="request" />
						<c:set value="/admin/content/boxes/list.html" var="refreshUrl" scope="request" />
						<c:set value="/admin/content/boxes/details.html" var="editUrl" scope="request"/>
						<c:set var="componentTitleKey" value="label.content.boxes" scope="request" />
           			</c:when>
           			 <c:otherwise>
           			    <c:set value="/admin/content/page.html?contentType=PAGE" var="pagingUrl" scope="request" />
						<c:set value="/admin/content/remove.html" var="removeUrl" scope="request" />
						<c:set value="/admin/content/pages/list.html" var="refreshUrl" scope="request" />
						<c:set value="/admin/content/pages/details.html" var="editUrl" scope="request"/>
						<c:set var="componentTitleKey" value="label.content.pages" scope="request" />
           			</c:otherwise>

           		</c:choose>
				
				<!-- Listing grid include -->

				<c:set var="entityId" value="id" scope="request"/>
				<c:set var="canRemoveEntry" value="true" scope="request" />
				<c:set var="gridHeader" value="/pages/admin/content/content-gridHeader.jsp" scope="request"/>
				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
				<!-- End listing grid include -->
			

		</div>
	   </div>
	</div>
</div>	