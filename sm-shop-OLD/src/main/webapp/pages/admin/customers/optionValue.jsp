<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ page session="false" %>		

<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


<script type="text/javascript">

$(document).ready(function() {

	$('#sortOrder').numeric();

});
	
</script>


<div class="tabbable">

		<jsp:include page="/common/adminTabs.jsp" />		
  		<div class="tab-content">
    		<div class="tab-pane active" id="customer-section">
			<div class="sm-ui-component">	

				<h3>
					<c:choose>
						<c:when test="${optionValue.id!=null && optionValue.id>0}">
								<s:message code="label.customer.optionvalue.edit" text="Edit option value" /> 
						</c:when>
						<c:otherwise>
								<s:message code="menu.customer-options-create" text="Create option value" />
						</c:otherwise>
					</c:choose>
				</h3>
				<br/>

				<c:url var="optionSave" value="/admin/customers/options/values/save.html"/>


				<form:form method="POST" enctype="multipart/form-data" commandName="optionValue" action="${optionSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
					
					
					<div class="control-group">
	                        <label class="required"><s:message code="label.customer.optionvalue.code" text="Option value code"/> </label>
	                        <div class="controls">
	                        			<form:input cssClass="highlight" readonly="${optionValue.id>0}" id="code" path="code"/>
	                        			<span class="help-inline"><form:errors path="code" cssClass="error" /></span>
	                        </div>
	
	                 </div>
								
                 <c:forEach items="${optionValue.descriptionsSettoList}" var="description" varStatus="counter">
                  
	                 <div class="control-group">
	                        <label class="required"><s:message code="label.customer.optionvalue.name" text="Option name"/> (<c:out value="${description.language.code}"/>)</label>
	                        <div class="controls">
	                        			<form:input cssClass="highlight x-large" id="name${counter.index}" path="descriptionsList[${counter.index}].name"/>
	                        			<span class="help-inline"><form:errors path="descriptionsList[${counter.index}].name" cssClass="error" /></span>
	                        </div>
	
	                  </div>
	
	                  
	                  <form:hidden path="descriptionsList[${counter.index}].language.code" />
	                  <form:hidden path="descriptionsList[${counter.index}].id" />
                  
                  </c:forEach>
                  
                  <div class="control-group">
                        <label class="required"><s:message code="label.entity.order" text="Order"/></label>
                        <div class="controls">
                                    <form:input id="sortOrder"  path="sortOrder"/>
                                    <span class="help-inline"><form:errors path="sortOrder" cssClass="error" /></span>

                        </div>
                  </div>
                  

                  <form:hidden path="id" />
			
			      <div class="form-actions">
                  		<div class="pull-right">
                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit" text="Submit"/></button>
                  		</div>
            	 </div>
 
            	 </form:form>
            	 
            	 
            	 <br/>

      		</div>
   			</div>
  		</div>
</div>		      			     