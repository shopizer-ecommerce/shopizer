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
								<h3><s:message code="label.permissions.title" text="Permissions" /></h3>	
								<br/>
								
								
								 <!-- Listing grid include -->
								 <c:set value="/admin/permissions/paging.html" var="pagingUrl" scope="request"/>
								 <c:set value="/admin/permissions/editPermission.html?id=${group.id}" var="editUrl" scope="request"/>
								 <c:set var="entityId" value="groupId" scope="request"/>
								 <c:set var="componentTitleKey" value="label.permissions.title" scope="request"/>
								 <c:set var="gridHeader" value="/pages/admin/profile/permissions-gridHeader.jsp" scope="request"/>
								 <c:set var="canRemoveEntry" value="false" scope="request"/>
				
				            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
								 <!-- End listing grid include -->

			      			     
			      			     
			      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     