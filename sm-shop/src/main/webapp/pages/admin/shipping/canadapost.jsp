<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



                 
                   <div class="control-group">
                        <label class="required"><s:message code="module.shipping.canadapost.identifier" text="Account identifier"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['account']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${identifier!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.canadapost.message.identifier" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.canadapost.username" text="Username"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['username']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${username!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.canadapost.message.username" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.canadapost.password" text="Password"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['password']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${password!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.canadapost.message.password" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="module.shipping.canadapost.apikey" text="API key"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['apikey']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${apikey!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.shipping.canadapost.message.apikey" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><strong><s:message code="module.shipping.canadapost.services" text="Services"/> - <s:message code="module.shipping.canada" text="Canada"/></strong></label>
	                        <div class="controls">
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.RP" />&nbsp;<s:message code="module.shipping.canadapost.DOM.RP" text="DOM.RP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.EP" />&nbsp;<s:message code="module.shipping.canadapost.DOM.EP" text="DOM.EP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.XP" />&nbsp;<s:message code="module.shipping.canadapost.DOM.XP" text="DOM.XP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.XP.CERT" />&nbsp;<s:message code="module.shipping.canadapost.DOM.XP.CERT" text="DOM.XP.CERT" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.PC" />&nbsp;<s:message code="module.shipping.canadapost.DOM.PC" text="DOM.PC" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.DT" />&nbsp;<s:message code="module.shipping.canadapost.DOM.DT" text="DOM.DT" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-domestic']" value="DOM.LIB" />&nbsp;<s:message code="module.shipping.canadapost.DOM.LIB" text="DOM.LIB" /><br/>
	                        </div>
                  	</div>

                   <div class="control-group">
                        <label class="required"><strong><s:message code="module.shipping.canadapost.services" text="Services"/> - <s:message code="module.shipping.usa" text="United states"/></strong></label>
	                        <div class="controls">
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.EP" />&nbsp;<s:message code="module.shipping.canadapost.USA.EP" text="USA.EP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.PW.ENV" />&nbsp;<s:message code="module.shipping.canadapost.USA.PW.ENV" text="USA.PW.ENV" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.PW.PAK" />&nbsp;<s:message code="module.shipping.canadapost.USA.PW.PAK" text="USA.PW.PAK" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.PW.PARCEL" />&nbsp;<s:message code="module.shipping.canadapost.USA.PW.PARCEL" text="USA.PW.PARCEL" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.SP.AIR" />&nbsp;<s:message code="module.shipping.canadapost.USA.SP.AIR" text="SA.SP.AIR" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.TP" />&nbsp;<s:message code="module.shipping.canadapost.USA.TP" text="USA.TP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.TP.LVM" />&nbsp;<s:message code="module.shipping.canadapost.USA.TP.LVW" text="USA.TP.LVW" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-usa']" value="USA.XP" />&nbsp;<s:message code="module.shipping.canadapost.USA.XP" text="USA.XP" /><br/>
	                        </div>
                  	</div>

                   <div class="control-group">
                        <label class="required"><strong><s:message code="module.shipping.canadapost.services" text="Services"/> - <s:message code="module.shipping.intl" text="International"/></strong></label>
	                        <div class="controls">
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.XP" />&nbsp;<s:message code="module.shipping.canadapost.INT.XP" text="INT.XP" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.IP.AIR" />&nbsp;<s:message code="module.shipping.canadapost.USA.PW.ENV" text="INT.IP.AIR" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.IP.SURF" />&nbsp;<s:message code="module.shipping.canadapost.INT.IP.SURF" text="INT.IP.SURF" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.PW.ENV" />&nbsp;<s:message code="module.shipping.canadapost.INT.PW.ENV" text="INT.PW.ENV" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.PW.PAK" />&nbsp;<s:message code="module.shipping.canadapost.INT.PW.PAK" text="INT.PW.PAK" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.PW.PARCEL" />&nbsp;<s:message code="module.shipping.canadapost.INT.PW.PARCEL" text="INT.PW.PARCEL" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.SP.AIR" />&nbsp;<s:message code="module.shipping.canadapost.INT.SP.AIR" text="INT.SP.AIR" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.SP.SURF" />&nbsp;<s:message code="module.shipping.canadapost.INT.SP.SURF" text="INT.SP.SURF" /><br/>
	                        		<form:checkbox cssClass="input-large highlight" path="integrationOptions['services-intl']" value="INT.TP" />&nbsp;<s:message code="module.shipping.canadapost.INT.TP" text="INT.TP" /><br/>
	                        </div>
                  	</div>                  
            
            
                  
                  