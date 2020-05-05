<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ page session="false" %>		



<script type="text/javascript">
	
	function removeImage(id){
			$("#store.error").show();
			$.ajax({
			  type: 'POST',
			  url: '<c:url value="/admin/optionsvalues/removeImage.html"/>',
			  dataType: 'json',
			  data: 'optionId=' + id,
			  success: function(response){
		
					var status = isc.XMLTools.selectObjects(response, "/response/status");
					if(status==0 || status ==9999) {
						
						//remove delete
						$("#imageControlRemove").html('');
						//add field
						$("#imageControl").html('<input class=\"input-file\" id=\"image\" name=\"image\" type=\"file\">');
						$(".alert-success").show();
						
					} else {
						
						//display message
						$(".alert-error").show();
					}
		
			  
			  },
			  error: function(xhr, textStatus, errorThrown) {
			  	alert('error ' + errorThrown);
			  }
			  
			});
	}
	
</script>
		
				


<div class="tabbable">

		<jsp:include page="/common/adminTabs.jsp" />		
  		<div class="tab-content">
    		<div class="tab-pane active" id="catalogue-section">
			<div class="sm-ui-component">	

				<h3>
					<c:choose>
						<c:when test="${optionValue.id!=null && optionValue.id>0}">
								<s:message code="label.product.productoptionvalue.edit.title" text="Edit option value" /> 
						</c:when>
						<c:otherwise>
								<s:message code="label.product.productoptionvalue.create.title" text="Create option value" />
						</c:otherwise>
					</c:choose>
				</h3>
				<br/>

				<c:url var="optionSave" value="/admin/options/saveOptionValue.html"/>


				<form:form method="POST" enctype="multipart/form-data" modelAttribute="optionValue" action="${optionSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
				
				
				 <div class="control-group">
                        <label><s:message code="label.entity.code" text="Option value code"/></label>
                        <div class="controls">
                                    <form:input cssClass="highlight" id="code" path="code"/>
                                    <span class="help-inline"><form:errors path="code" cssClass="error" /></span>
                        </div>
                  </div>
							
                 <c:forEach items="${optionValue.descriptionsSettoList}" var="description" varStatus="counter">
                  
	                 <div class="control-group">
	                        <label class="required"><s:message code="label.product.productoptions.name" text="Option name"/> (<c:out value="${description.language.code}"/>)</label>
	                        <div class="controls">
	                        			<form:input cssClass="highlight" id="name${counter.index}" path="descriptionsList[${counter.index}].name"/>
	                        			<span class="help-inline"><form:errors path="descriptionsList[${counter.index}].name" cssClass="error" /></span>
	                        </div>
	
	                  </div>
	
	                  
	                  <form:hidden path="descriptionsList[${counter.index}].language.code" />
	                  <form:hidden path="descriptionsList[${counter.index}].id" />
                  
                  </c:forEach>
                  

                 <div class="control-group">
                        <label><s:message code="label.product.image" text="Image"/>&nbsp;<c:if test="${optionValue.productOptionValueImage!=null && optionValue.productOptionValueImage!=''}"><span id="imageControlRemove"> - <a href="#" onClick="removeImage('${optionValue.id}')"><s:message code="label.generic.remove" text="Remove"/></a></span></c:if></label>
                        <div class="controls" id="imageControl">
                        		<c:choose>
	                        		<c:when test="${optionValue.productOptionValueImage==null || optionValue.productOptionValueImage==''}">
	                                    <input class="input-file" id="image" name="image" type="file">
	                                </c:when>
	                                <c:otherwise>
	                                	<img src="<c:url value=""/><sm:contentImage imageName="${optionValue.productOptionValueImage}" imageType="PROPERTY"/>" width="80">
	                                </c:otherwise>
                                </c:choose>
                        </div>
                  </div>
                  <form:hidden path="productOptionValueImage" />
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