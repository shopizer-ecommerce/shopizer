<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>				
				

<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>


	<script type="text/javascript">
	

	
	$(function(){	
		$('.btn').addClass('disabled');
		<c:forEach items="${category.descriptions}" var="description" varStatus="counter">		
			$("#name${counter.index}").friendurl({id : 'seUrl${counter.index}'});
		</c:forEach>
	});
	
	
	function validateCode() {
		$('#checkCodeStatus').html('<img src="<c:url value="/resources/img/ajax-loader.gif" />');
		$('#checkCodeStatus').show();
		var code = $("#code").val();
		checkCode(code,'<c:url value="/admin/categories/checkCategoryCode.html" />');
	}
	
	function callBackCheckCode(msg,code) {
		
		if(code==9999 || code==0) {

			$('#checkCodeStatus').html('<font color="green"><s:message code="message.code.available" text="This code is available"/></font>');
			$('#checkCodeStatus').show();
			$('.btn').removeClass('disabled');
		}
		if(code==9998) {

			$('#checkCodeStatus').html('<font color="red"><s:message code="message.code.exist" text="This code already exist"/></font>');
			$('#checkCodeStatus').show();
			$('.btn').addClass('disabled');
		}
		
	}
	
	
	</script>



<div class="tabbable">

					<jsp:include page="/common/adminTabs.jsp" />
  					
  					 <div class="tab-content">

    					<div class="tab-pane active" id="catalogue-section">



								<div class="sm-ui-component">	
								
								
				<h3>
					<c:choose>
						<c:when test="${permission.id!=null && permission.id>0}">
								<s:message code="label.permission.editpermission" text="Edit permission" /> <c:out value="${category.code}"/>
						</c:when>
						<c:otherwise>
								<s:message code="label.permission.createpermission" text="Create permission" />
						</c:otherwise>
					</c:choose>
					
				</h3>	
				<br/>

				<c:url var="permissionSave" value="/admin/permission/save.html"/>


				<form:form method="POST" modelAttribute="permission" action="${permissionSave}">

      							
      				<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
								
      			  	
                 
                   <div class="control-group">
                        <label class="required"><s:message code="label.permission.name" text="Permission name"/></label>
	                        <div class="controls">
	                        		<form:input cssClass="input-large" path="permissionName" />
	                        </div>
                  </div>
                  
                  
                  <form:hidden path="id" />
			
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