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
						<s:message code="label.product.customgroup.add" text="Add custom group" />
				</h3>

				

				<br/><br/>


				<c:url var="saveProductGrop" value="/admin/products/groups/save.html"/>


				<form:form method="POST" modelAttribute="group" action="${saveProductGrop}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    

					<div class="control-group">
	                        <label class="required"><s:message code="label.product.customgroup.code" text="Custom product group code"/></label>
	                        <div class="controls">
	                                    <form:input id="code" cssClass="highlight" path="code"/>
	                                    <span id="help-price" class="help-inline"><form:errors path="code" cssClass="error" /></span>
	                        </div>
	                 </div>
					

			
			      <div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  			
                  		</div>
            	 </div>
 
            	 </form:form>
            	 
            	 
            	 <br/>
            	 
				 <!-- Listing grid include -->
				 
				 <c:set value="/admin/products/groups/paging.html" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/products/groups/remove.html" var="removeUrl" scope="request"/>
				 <c:set value="/admin/products/groups/update.html" var="updateUrl" scope="request"/>
				 <c:set value="/admin/products/group/edit.html" var="editUrl" scope="request"/>
				 <c:set value="/admin/products/groups/list.html" var="afterRemoveUrl" scope="request"/>
				 <c:set var="entityId" value="code" scope="request"/>
				 <c:set var="componentTitleKey" value="menu.catalogue-products-custom-group" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/products/customProductGroup-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>

            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
            	 
            	 
	      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     