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
								<h3><s:message code="label.groups.title" text="Groups" /></h3>	
								<br/>
								
								
								<!-- Listing grid include -->
								 <c:set value="/admin/groups/paging.html" var="pagingUrl" scope="request"/>
								 <c:set value="/admin/groups/editGroup.html" var="editUrl" scope="request"/>
								 <c:set value="/admin/groups/delete.html" var="removeUrl" scope="request"/>
								 <c:set value="/admin/groups/groups.html" var="refreshUrl" scope="request"/>
								 <c:set var="entityId" value="groupId" scope="request"/>
								 <c:set var="expandDetails" value="description" scope="request"/>
								 <c:set var="componentTitleKey" value="label.groups.title" scope="request"/>
								 <c:set var="gridHeader" value="/pages/admin/profile/groups-gridHeader.jsp" scope="request"/>
								 <sec:authorize access="hasRole('ADMIN') and fullyAuthenticated">
								 <c:set var="canRemoveEntry" value="true" scope="request"/>
								 </sec:authorize>
				
				            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
								 <!-- End listing grid include -->
								
								
	
			      			     
			      			     
      					</div>


   					</div>


  					</div>

				</div>		      			     