<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				


<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
				<h3>
						<s:message code="label.product.productoptions.title" text="Option management" />
				</h3>	
				
				
				<h3>
					<c:choose>
						<c:when test="${option.id!=null && option.id>0}">
								<s:message code="label.product.productoptions.edit.title" text="Edit option" /> 
						</c:when>
						<c:otherwise>
								<s:message code="label.product.productoptions.create.title" text="Create option" />
						</c:otherwise>
					</c:choose>
					
				</h3>
				<br/>

				<c:url var="optionSave" value="/admin/options/save.html"/>


				<form:form method="POST" commandName="option" action="${optionSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								
                 
                  
                  <div class="control-group">
                        <label><s:message code="label.product.option.code" text="Option code"/></label>
                        <div class="controls">
                                    <form:input cssClass="highlight" id="code" path="code"/>
                                    <span class="help-inline"><form:errors path="code" cssClass="error" /></span>
                        </div>
                  </div>
                 
                 <c:forEach items="${option.descriptionsSettoList}" var="description" varStatus="counter">
                  
                 <div class="control-group">
                        <label class="required"><s:message code="label.product.productoptions.name" text="Option name"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">
                        			<form:input id="name${counter.index}" path="descriptionsList[${counter.index}].name"/>
                        			<span class="help-inline"><form:errors path="descriptionsList[${counter.index}].name" cssClass="error" /></span>
                        </div>

                  </div>

                  
                  <form:hidden path="descriptionsList[${counter.index}].language.code" />
                  <form:hidden path="descriptionsList[${counter.index}].id" />
                  
                  </c:forEach>

      			  
      			 <div class="control-group">
                        <label><s:message code="label.product.productoptions.type" text="Option type"/></label>
                        <div class="controls">
                                   
	                        <div class="controls">			
	                        		<form:select path="productOptionType">
	                        			<s:message code="label.product.productoption.type.text" text="Text" var="vText" />
	                        			<s:message code="label.product.productoption.type.select" text="Select" var="vSelect"/>
	                        			<s:message code="label.product.productoption.type.radio" text="Radio" var="vRadio"/>
	                        			<s:message code="label.product.productoption.type.checkbox" text="Checkbox" var="vCheckbox"/>
	                        			<form:option value="text" label="${vText}" />
	                        			<form:option value="select" label="${vSelect}" />
	                        			<form:option value="radio" label="${vRadio}" />
	                        			<form:option value="checkbox" label="${vCheckbox}" />
				       				</form:select>
	                                <span class="help-inline"><form:errors path="productOptionType" cssClass="error" /></span>
	                        </div>

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