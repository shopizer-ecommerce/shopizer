<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		



                 
                 <div class="control-group">
                        <label class="required"><s:message code="module.payment.beanstream.merchantid" text="Merchant id"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large highlight" path="integrationKeys['merchantid']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${merchantid!=null}">
	                        	<span id="identifiererrors" class="error"><s:message code="module.payment.beanstream.message.merchantid" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.beanstream.username" text="Username"/></label>
	                        <div class="controls">
									<form:input cssClass="input-large highlight" path="integrationKeys['username']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${username!=null}">
	                        		<span id="usernameerrors" class="error"><s:message code="module.payment.beanstream.message.username" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>

                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.beanstream.password" text="Password"/></label>
	                        <div class="controls">
									<form:password cssClass="input-large highlight" path="integrationKeys['password']" />
	                        </div>
	                        <span class="help-inline">
	                        	<c:if test="${password!=null}">
	                        		<span id="passworderrors" class="error"><s:message code="module.payment.beanstream.message.password" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
                  </div>
                  
                   <div class="control-group">
                        <label class="required"><s:message code="module.payment.transactiontype" text="Transaction type"/></label>
	                        <div class="controls">
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZE" />&nbsp;<s:message code="module.payment.transactiontype.preauth" text="Pre-authorization" /><br/>
	                        		<form:radiobutton cssClass="input-large highlight" path="integrationKeys['transaction']" value="AUTHORIZECAPTURE" />&nbsp;<s:message code="module.payment.transactiontype.sale" text="Sale" /></br>
	                        </div>
                  </div>        
            
                  
                  