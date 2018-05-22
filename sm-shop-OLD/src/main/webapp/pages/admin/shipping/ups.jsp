<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



                 
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.ups.identifier" text="Access key"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['accessKey']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${accessKey!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.usps.message.identifier" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.ups.userid" text="User id"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['userId']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${userId!=null}">
	                        	<span id="useriderrors" class="error"><s:message code="module.shipping.usps.message.userid" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.ups.password" text="Password"/></label>
	                        <div class="controls">
	                        		<form:password cssClass="input-large highlight" path="integrationKeys['password']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${password!=null}">
	                        	<span id="passworderrors" class="error"><s:message code="module.shipping.usps.message.password" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.shipping.ups.packages" text="Packaging"/></label>
	                        <div class="controls">
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="01" />&nbsp;<s:message code="module.shipping.ups.package.01" text="UPS Letter" /><br/>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="02" />&nbsp;<s:message code="module.shipping.ups.package.02" text="Customer Package" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="03" />&nbsp;<s:message code="module.shipping.ups.package.03" text="UPS Tube" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="04" />&nbsp;<s:message code="module.shipping.ups.package.04" text="UPS Pak" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="21" />&nbsp;<s:message code="module.shipping.ups.package.21" text="UPS Express Box" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="24" />&nbsp;<s:message code="module.shipping.ups.package.24" text="UPS 25kg Box" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="10" />&nbsp;<s:message code="module.shipping.ups.package.10" text="UPS 10kg box" /></br>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['packages']" value="25" />&nbsp;<s:message code="module.shipping.ups.package.25" text="Unknown" /></br>
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${packages!=null}">
	                        		<span id="packageserrors" class="error"><s:message code="module.shipping.ups.message.packages" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>

                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.ups.method" text="UPS Shipping method"/></label>
	                        <div class="controls">
	                        		<!--<form:radiobutton cssClass="input-large highlight" path="integrationOptions['selectservice']" value="yes" />&nbsp;<s:message code="module.shipping.ups.method.select" text="Select a specific UPS shipping method" /><br/>-->
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationOptions['selectservice']" value="no" />&nbsp;<s:message code="module.shipping.ups.method.unselect" text="Let UPS propose all available shipping methods" /></br>
	                        </div>
                  </div>
                  
                  
            
            
                  
                  