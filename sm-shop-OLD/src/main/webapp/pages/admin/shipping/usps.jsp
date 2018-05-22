<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



                 
                   <div class="control-group">
                        <label class="required"><s:message code="module.shipping.usps.identifier" text="Account identifier"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['account']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${identifier!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.usps.message.identifier" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.shipping.usps.packages" text="Packaging"/></label>
	                        <div class="controls">
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="Package" />&nbsp;<s:message code="module.shipping.usps.package.08" text="Package" /><br/>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="Envelope" />&nbsp;<s:message code="module.shipping.usps.package.07" text="Envelope" /></br>
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${packages!=null}">
	                        		<span id="packageserrors" class="error"><s:message code="module.shipping.usps.message.packages" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>


                  
            
            
                  
                  