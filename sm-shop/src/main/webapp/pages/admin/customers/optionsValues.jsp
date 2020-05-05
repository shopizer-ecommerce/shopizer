<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				


<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="customer-section">



								<div class="sm-ui-component">	
								
								
				<h3>
						<s:message code="menu.customer-options" text="Customer options" />
				</h3>	
				<br/>
				
				
				
				<!-- Listing grid include -->
				 <c:set value="/admin/customers/options/values/paging.html" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/customers/options/values/remove.html" var="removeUrl" scope="request"/>
				 <c:set value="/admin/customers/options/values/edit.html" var="editUrl" scope="request"/>
				 <c:set value="/admin/customers/options/values/list.html" var="afterRemoveUrl" scope="request"/>
				 <c:set var="entityId" value="id" scope="request"/>
				 <c:set var="componentTitleKey" value="menu.customer-options-list" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/customers/optionsValues-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>


            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->

			      			     
            	 
            	 
	      			     
      					</div>
      					

   					</div>


  					</div>

				</div>		      			     