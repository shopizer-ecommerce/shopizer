<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>	

<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>			
				
<script>
	
	$(function(){
		$('#boxWidth').numeric();
		$('#boxHeight').numeric();
		$('#boxLength').numeric();
		$('#boxWeight').numeric({allow:"."});
	});
	
</script>


<div class="tabbable">

  					
 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">
  					
  					
  					<div class="tab-pane active" id="shipping-methods">


								<div class="sm-ui-component">
								<h3><s:message code="label.shipping.packaging.title" text="Packaging information" /></h3>	
								<br/>
								

							<c:url var="savePackaging" value="/admin/shipping/saveShippingPackaging.html"/>
							<form:form method="POST" modelAttribute="configuration" action="${savePackaging}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								

									<div class="control-group">
                        				<div class="controls">
                                    		<form:radiobutton id="shipPackageType" path="shipPackageType" value="ITEM"/>&nbsp;<s:message code="label.shipping.packaging.individual" text="Items will be shipped individually" /><br/>			
											<form:radiobutton id="shipPackageType" path="shipPackageType" value="BOX"/>&nbsp;<s:message code="label.shipping.packaging.boxes" text="Items will be combined and shipped in a box" /><br/>
                        				</div>
                  					</div>
                  					<div class="well">
                  					<div class="control-group">
                        				<div class="controls">
                                    		<s:message code="label.store.weightunit" text="Weight units" />&nbsp;<strong><c:out value="${store.weightunitcode}"/></strong><br/>			
											<s:message code="label.store.sizeunits" text="Size units" />&nbsp;<strong><c:out value="${store.seizeunitcode}"/></strong><br/>
                        				</div>
                  					</div>
                  					<div class="control-group">
                        				<label><s:message code="label.shipping.packaging.box.width" text="Box width"/></label>
                        				<div class="controls">
											<form:input cssClass="input-small" id="boxWidth" path="boxWidth" />
                        				</div>
                        				<span class="help-inline"><form:errors path="boxWidth" cssClass="error" /></span>
                  					</div>
                  					<div class="control-group">
                        				<label><s:message code="label.shipping.packaging.box.height" text="Box height"/></label>
                        				<div class="controls">
											<form:input cssClass="input-small" id="boxHeight" path="boxHeight" />
                        				</div>
                        				<span class="help-inline"><form:errors path="boxHeight" cssClass="error" /></span>
                  					</div>

                  					
                  					 <div class="control-group">
                        				<label><s:message code="label.shipping.packaging.box.length" text="Box length"/></label>
                        				<div class="controls">
											<form:input cssClass="input-small" id="boxLength" path="boxLength" />
                        				</div>
                        				<span class="help-inline"><form:errors path="boxLength" cssClass="error" /></span>
                  					</div>
                  					
                  					<div class="control-group">
                        				<label><s:message code="label.shipping.packaging.box.weight" text="Box weight"/></label>
                        				<div class="controls">
											<form:input cssClass="input-small" id="boxWeight" path="boxWeight" />
                        				</div>
                        				<span class="help-inline"><form:errors path="boxWeight" cssClass="error" /></span>
                  					</div>                   					
									</div>

	                        		<div class="form-actions">
                  						<div class="pull-right">
                  							<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  						</div>
            	 					</div>
					                  

            	 			</form:form>
							
							


      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>