	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>
<link href="<c:url value="/resources/css/bootstrap/css/datepicker.css" />" rel="stylesheet"></link>
<script src="<c:url value="/resources/js/bootstrap/bootstrap-datepicker.js" />"></script>

<script type="text/javascript">

$(document).ready(function() {

	$('#sortOrder').numeric();

});
	
</script>

<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="customer-section">



								<div class="sm-ui-component">	
								
								
				<h3>
						<s:message code="menu.optin-customers" text="Optin management" />
				</h3>	
				
				
				<h3>
					<c:choose>
						<c:when test="${optin.id!=null && optin.id>0}">
								<s:message code="label.customer.optins.edit" text="Edit optin" /> 
						</c:when>
						<c:otherwise>
								<s:message code="menu.optin-customers-create" text="Create optin" />
						</c:otherwise>
					</c:choose>
					
				</h3>
				<br/>

				<c:url var="optinSave" value="/admin/customers/optins/save.html"/>


				<form:form method="POST" commandName="optin" action="${optinSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
					
					
                  <form:hidden path="id" />
                  
          
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.customer.optin.code" text="Code"/></label>
                        <div class="controls">
                                    <form:input id="sortOrder" path="code"/>
                                    <span class="help-inline"><form:errors path="code" cssClass="error" /></span>

                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.customer.optin.description" text="Description"/></label>
                        <div class="controls">
                                    <form:input id="sortOrder" path="description"/>
                                    <span class="help-inline"><form:errors path="description" cssClass="error" /></span>

                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.customer.optin.startDate" text="Start Date"/></label>
                        <div class="controls">
                                   
                                    <form:input  cssClass="input-large" path="startDate"  class="small" type="date"
									 data-date-format="<%=com.salesmanager.core.business.constants.Constants.DEFAULT_DATE_FORMAT%>" />
									  <script type="text/javascript">
		                                 $('#startDate').datepicker();
		                              </script>
                                    <span class="help-inline"><form:errors path="startDate" cssClass="error" /></span>

                        </div>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="label.customer.optin.endDate" text="End Date"/></label>
                        <div class="controls">
                                    
                                     <form:input  cssClass="input-large" path="endDate"  class="small" type="date"
									 data-date-format="<%=com.salesmanager.core.business.constants.Constants.DEFAULT_DATE_FORMAT%>" />
									  <script type="text/javascript">
		                                 $('#endDate').datepicker();
		                              </script>
                                    <span class="help-inline"><form:errors path="endDate" cssClass="error" /></span>

                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.customer.optin.merchant" text="Store Name"/></label>
                        <div class="controls">
                                    
                                    <form:select cssClass="billing-country-list" path="merchant.code">
		  								<form:options items="${merchants}" itemValue="code" itemLabel="storename"/>
	       							</form:select>
                                 	<span class="help-inline"><form:errors path="merchant.code" cssClass="error" /></span>
	                  

                        </div>
                  </div>
			
			
			      <div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  			

                  		</div>

            	 </div>
 
            	 </form:form>
            	 
            	 
            	 <br/>
            	 

            	 
            	 
	      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     