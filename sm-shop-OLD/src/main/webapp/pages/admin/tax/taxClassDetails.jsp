<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				






<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 	<div class="tab-content">

    						<div class="tab-pane active" id="taxrates-section">
								<a href="<c:url value="/admin/tax/taxclass/list.html"/>"><s:message code="label.generic.back" text="Back" /></a><br/><br/>
								<div class="sm-ui-component">	
								<h3><s:message code="label.tax.taxclass.title" text="Tax classes" /></h3>	
								<br/>

								<c:url var="saveTaxClass" value="/admin/tax/taxclass/update.html"/>

								<form:form method="POST" modelAttribute="taxClass" action="${saveTaxClass}">	
				      				<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
												
				      			  	
				                 
				                    <div class="control-group">
				                        <label class="required"><s:message code="label.tax.taxclass" text="Tax class"/></label>
					                        <div class="controls">
					                        		<form:input cssClass="input-large" path="code" />
					                        </div>
				                    </div>
				                    
				                    <div class="control-group">
				                        <label class="required"><s:message code="label.tax.taxclass.name" text="Tax class name"/></label>
					                        <div class="controls">
					                        		<form:input cssClass="input-large" path="title" />
					                        </div>
				                    </div>
				                  
				                  
				                  	<form:hidden path="id" />
				                  	<form:hidden path="merchantStore.id" value="${requestScope.store.id}" />
							
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