<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				
<script>
	

	
</script>


<div class="tabbable">

  					
 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">
  					
  					
  					<div class="tab-pane active" id="shipping-config">


								<div class="sm-ui-component">
								<h3><s:message code="label.shipping.title" text="Shipping configuration" /></h3>	
								<br/>


								<c:url var="shippingConfigurationSave" value="/admin/shipping/saveShippingConfiguration.html"/>


								<form:form method="POST" commandName="configuration" action="${shippingConfigurationSave}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								


      			  
					      			  <div class="control-group">
					                        <label><s:message code="label.shipping.mode" text="Shipping mode"/></label>
					                        <div class="controls">
					                                   
						                        <div class="controls">
						                        		 <form:radiobutton id="shipType" path="shipType" value="NATIONAL"/>&nbsp;<s:message code="label.shipping.national" text="National" />			
														 <form:radiobutton id="shipType" path="shipType" value="INTERNATIONAL"/>&nbsp;<s:message code="label.shipping.international" text="International" />
						                                 <span class="help-inline"><form:errors path="shipType" cssClass="error" /></span>
						                        </div>
					
					                        </div>
					                  </div>
					                  
					                  <form:hidden path="shipBaseType"/>
					                  <form:hidden path="shipOptionPriceType"/>
					                  
					                  
					                  <div class="form-actions">
                  						<div class="pull-right">
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						</div>

            	 					</div>
            	 			</form:form>
								
							
							
							<br/>
							

							<!-- Listing grid include -->
				 			<c:set value="/admin/shipping/countries/paging.html" var="pagingUrl" scope="request"/>
				 			<c:set value="/admin/shipping/countries/update.html" var="updateUrl" scope="request"/>
				 			<c:set value="/admin/shipping/shippingConfigs.html" var="refreshUrl" scope="request"/>
				 			<c:set var="entityId" value="code" scope="request"/>
							<c:set var="componentTitleKey" value="label.shipping.countries" scope="request"/>
				 			<c:set var="gridHeader" value="/pages/admin/shipping/shipping-countries-gridHeader.jsp" scope="request"/>
							<c:set var="canRemoveEntry" value="false" scope="request"/>

            	 			<jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 			<!-- End listing grid include -->
							


      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>