<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>		



<div class="tabbable">

  					
 					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">
  					
  					
  					<div class="tab-pane active" id="admin-cache">


							<div class="sm-ui-component">
								<h3><s:message code="menu.system-configurations" text="System configurations" /></h3>	
							<br/>
							
							<c:url var="saveSystemConfiguration" value="/admin/configuration/saveSystemConfiguration.html"/>
							<form:form method="POST" modelAttribute="configuration" action="${saveSystemConfiguration}">

      							
      								<form:errors path="*" cssClass="alert alert-error" element="div" />
									<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    

		                        	   
		                        	 <div class="control-group">
	                        				<label><s:message code="label.store.testmode" text="Test mode" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="testMode" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
		                        	   

		                        	 <div class="control-group">
	                        				<label><s:message code="label.store.debugmode" text="Debug mode" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="debugMode" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
		                        	   
		                        	   
		                        	   <div class="control-group">
	                        				<label><s:message code="label.customer.displaycustomersection" text="Display customer section" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayCustomerSection" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   
	                  				  <div class="control-group">
	                        				<label><s:message code="label.store.displaycontactussection" text="Display contact us page" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayContactUs" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.store.displaystoreaddress" text="Display store address" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayStoreAddress" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.order.displayagreement" text="Display customer terms and policy agreement in order page" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayCustomerAgreement" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.store.displaysearchbox" text="Display search box" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displaySearchBox" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.store.displaypagesmenu" text="Display pages menu" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayPagesMenu" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <c:forEach items="${store.languages}" var="language">
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.configuration.useglobalsearch_suggestions" text="Use global pre-defined search suggestions" /> (${language.code})</label>
	                        				<div class="controls">
	                        					<form:checkbox path="useDefaultSearchConfig['${language.code}']" value="on"/> 
	                        					<br/>
	                        					<s:message code="label.configuration.globalsearch_suggestions_path" text="Global pre-defined search suggestions file path" /> (${language.code})<br/>
	                        					<form:input cssClass="input-large" path="defaultSearchConfigPath['${language.code}']" />
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   </c:forEach>
	                  				   
	                  				  <div class="control-group">
	                        				<label><s:message code="label.store.allowPurchaseItems" text="Allow purchase items" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="allowPurchaseItems" /> 
	                                   			<span class="help-inline"></span>
	                        				</div>
	                  				   </div>
	                  				   
	                  				   <div class="control-group">
	                        				<label><s:message code="label.store.displayaddtocartfeatured" text="Allow add to cart on featured items" /></label>
	                        				<div class="controls">
	                        					<form:checkbox path="displayAddToCartOnFeaturedItems" /> 
	                                   			<span class="help-inline"></span>
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