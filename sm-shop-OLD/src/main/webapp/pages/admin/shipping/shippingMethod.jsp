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
  					
  					
  					<div class="tab-pane active" id="shipping-method">


								<div class="sm-ui-component">
								<h3><s:message code="label.shipping.title" text="Shipping configuration" /> - <s:message code="module.shipping.${configuration.moduleCode}" arguments="${requestScope.ADMIN_STORE.storename}" text="No label found - ${configuration.moduleCode}"/></h3>	
								<br/>
								
								
								

							<s:message code="module.shipping.${configuration.moduleCode}.note" text=""/><br/>
							
							<c:url var="saveShippingMethod" value="/admin/shipping/saveShippingMethod.html"/>
							
							<c:if test="${customConfiguration!=null}">
										<c:url var="saveShippingMethod" value="/admin/shipping/save${configuration.moduleCode}ShippingMethod.html"/>
	               
	                    	</c:if>
							
							
							<form:form method="POST" commandName="configuration" action="${saveShippingMethod}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								

									<div class="control-group">
                        				<label><s:message code="label.entity.enabled" text="Module enabled"/></label>
                        				<div class="controls">
                                    		<form:checkbox path="active" />
                        				</div>
                  					</div>
                  					
                  					<div class="controls">
	                        			<label><s:message code="label.generic.environment" text="Environment"/></label>			
	                        			<div class="controls">
	                        			<form:select path="environment">
					  						<form:options items="${environments}" />
				       					</form:select>
				       					</div>
	                                	<span class="help-inline"><form:errors path="environment" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		
	                        		<c:if test="${customConfiguration==null}">
	                        			<jsp:include page="/pages/admin/shipping/${configuration.moduleCode}.jsp"></jsp:include>
	                        		</c:if>

	                        		
	                        		<form:hidden path="moduleCode" />
	                        		<div class="form-actions">
                  						<div class="pull-right">
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						</div>
            	 					</div>
					                  

            	 			</form:form>
            	 			
            	 			
            	 			
            	 		<c:if test="${customConfiguration!=null}">
	                        			<jsp:include page="/pages/admin/shipping/${configuration.moduleCode}.jsp"></jsp:include>
	                    </c:if>	
							
							
		                  <c:if test="${configuration.active && validationError==null}">      
		                  <c:url var="removeModule" value="/admin/shipping/deleteShippingMethod.html"/>
		                  <form:form method="POST" enctype="multipart/form-data" commandName="code" action="${removeModule}">
									<input type="hidden" name="code" value="${configuration.moduleCode}" />
			                        <div class="form-actions">
			                            <div class="pull-right">
			                                    <button type="submit" class="btn-danger"><i class="icon-trash icon-large"></i><s:message code="label.generic.remove" text="Remove"/></button>
			                            </div>
			                   		</div>
		
		                   </form:form>
		                   </c:if>	

      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>