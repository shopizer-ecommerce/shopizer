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
  					
  					
  					<div class="tab-pane active" id="accounts-conf"> 


								<div class="sm-ui-component">
								<h3><s:message code="label.emailconfig.options" text="Email Configuration Options" /></h3>	
								<br/>
								

							<c:url var="saveEmailConfiguration" value="/admin/configuration/saveEmailConfiguration.html"/>
							<form:form method="POST" commandName="configuration" action="${saveEmailConfiguration}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    

                  					
                  					<div class="control-group">
                        				<label><s:message code="label.emailconfig.protocol" text="Protocol"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="protocol" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="protocol" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.host" text="Host"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="host" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="host" cssClass="error" /></span>
	                        		</div>

	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.port" text="Port"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="port" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="port" cssClass="error" /></span>
	                        		</div>
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.username" text="Username"/></label>
                        				<div class="controls">
											<form:input cssClass="input-large" path="username" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="username" cssClass="error" /></span>
	                        		</div>	    
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.password" text="Password"/></label>
                        				<div class="controls">
											<form:password cssClass="input-large" path="password" />
                        				</div>
	                                	<span class="help-inline"><form:errors path="password" cssClass="error" /></span>
	                        		</div>	
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.smtpauth" text="SmtpAuth"/></label>
                        				<div class="controls">
											<form:checkbox cssClass="input-large" path="smtpAuth" />
                        				</div>
                        				<span class="help-inline"><s:message code="label.emailconfig.requiresauthentication" text="Email server requires authentication (should be set to true)"/></span>
	                        		</div> 
	                        		
	                        		<div class="control-group">
                        				<label><s:message code="label.emailconfig.starttls" text="Starttls"/></label>
                        				<div class="controls">
											<form:checkbox cssClass="input-large" path="starttls" />
                        				</div>
                        				<span class="help-inline"><s:message code="label.emailconfig.requiresstarttls" text="Email server requires STARTLS encryption (should be false, check server configurations)"/></span>
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