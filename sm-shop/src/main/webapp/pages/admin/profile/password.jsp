<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page session="false" %>				
				



	<script type="text/javascript">
	


	
	
	</script>



<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
				<h3>
						<s:message code="label.generic.password" text="Password" /> <c:out value="${user.adminName}"/>
				</h3>	
				<br/>

				<c:url var="savePassword" value="/admin/users/savePassword.html"/>


				<form:form method="POST" commandName="password" action="${savePassword}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								

                  <div class="control-group">
                        <label><s:message code="label.generic.password" text="Password"/></label>
	                        <div class="controls">
	                        		<input type="password" name="password" id="password" cssClass="input-large highlight" />
	                                <span class="help-inline"><form:errors path="password" cssClass="error" /></span>
	                        </div>
                  </div>
                  
                  <div class="control-group">
                        <label><s:message code="label.generic.newpassword" text="New password"/></label>
	                        <div class="controls">
	                        		<input type="password" name="newPassword" id="newPassword" cssClass="input-large highlight" />
	                                <span class="help-inline"><form:errors path="newPassword" cssClass="error" /></span>
	                        </div>
                  </div>

                  <div class="control-group">
                        <label><s:message code="label.generic.newpassword.repeat" text="Repeat new password"/></label>
	                        <div class="controls">
	                        		<input type="password" name="repeatPassword" id="repeatPassword" cssClass="input-large highlight" />
	                                <span class="help-inline"><form:errors path="repeatPassword" cssClass="error" /></span>
	                        </div>
                  </div>
                  <form:hidden path="user.id"/>
			
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