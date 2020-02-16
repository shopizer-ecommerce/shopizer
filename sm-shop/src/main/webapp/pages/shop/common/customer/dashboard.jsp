<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>

<script>




$(document).ready(function() {
	
	
	$('.textAttribute').alphanumeric({ichars:'&=?'});
	
	$('#attributes').on('submit',function (event) {
		$('#attributesBox').showLoading();
		$("#attributesError").hide();
		$("#attributesSuccess").hide();
		var data = $('#attributes').serialize();
		console.log('Saving attributes ' + data);
	    $.ajax({
	        url: '<c:url value="/shop/customer/attributes/save.html"/>',
	        cache: false,
	        type: 'POST',
	        data : data,
	        success: function(result) {
	            $('#attributesBox').hideLoading();
	               var response = result.response;
                   if (response.status==0) {
                        $("#attributesSuccess").show();
                   } else {
                        $("#attributesError").html(response.message);
                        $("#attributesError").show();
                   }
	        },
			error: function(jqXHR,textStatus,errorThrown) { 
					$('#attributesBox').hideLoading();
					alert('Error ' + jqXHR + "-" + textStatus + "-" + errorThrown);
			}
	    });
	    
	    event.preventDefault();
	});
});	
	
</script>


	<div id="main-content" class="container clearfix row-fluid">
			<div class="row-fluid">

			<c:if test="${options!=null && fn:length(options)>0}">
						
						<div class="span-12 col-md-12 no-padding-left">
						<div id="attributesSuccess" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>   
			            <div id="attributesError" class="alert alert-error" style="display:none;"><s:message code="message.error" text="An error occured"/></div>
						<form action="#" id="attributes">
						
						<div id="attributesBox" class="checkout-box">
								<span class="box-title">
									<p class="p-title"><s:message code="label.customer.moredetails" text="More details"/></font></p>
								</span>
						
					
							
							
							<c:forEach items="${options}" var="option" varStatus="status">
								<div class="control-group form-group"> 
			                        <label><c:out value="${option.name}"/></label>
			                        <div class="controls">	       							
											<c:choose>
												<c:when test="${option.type=='Select'}">
													<select id="<c:out value="${option.id}"/>" name="<c:out value="${option.id}"/>">
													<c:forEach items="${option.availableValues}" var="optionValue">
														<option value="${optionValue.id}" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> SELECTED</c:if>>${optionValue.name}</option>
													</c:forEach>
													</select>
												</c:when>
												<c:when test="${option.type=='Radio'}">
													<c:forEach items="${option.availableValues}" var="optionValue">
														<input type="radio" id="<c:out value="${option.id}"/>" name="<c:out value="${option.id}"/>" value="<c:out value="${optionValue.id}"/>" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> checked="checked" </c:if> />
														<c:out value="${optionValue.name}"/>
													</c:forEach>
												</c:when>
												<c:when test="${option.type=='Text'}">
													<input class="input-medium textAttribute form-control form-control-md form-control-50" type="text" id="<c:out value="${option.id}"/>-<c:out value="${option.availableValues[0].id}"/>" name="<c:out value="${option.id}"/>-<c:out value="${option.availableValues[0].id}"/>" class="input-large" value="<c:if test="${option.defaultValue!=null}">${option.defaultValue.name}</c:if>">
												</c:when> 
												<c:when test="${option.type=='Checkbox'}">
													<c:forEach items="${option.availableValues}" var="optionValue">
														<input type="checkbox" id="<c:out value="${option.id}"/>-<c:out value="${optionValue.id}"/>" name="<c:out value="${option.id}"/>-<c:out value="${optionValue.id}"/>" <c:if test="${option.defaultValue!=null && option.defaultValue.id==optionValue.id}"> checked="checked" </c:if>  />
														<c:out value="${optionValue.name}"/>
													</c:forEach>
												</c:when>										
												
												
											</c:choose>				       							
		                                 	<span class="help-inline"></span>
			                       
			                    </div> 
								</div>
							
							</c:forEach>
							<input id="customer" type="hidden" value="<c:out value="${requestScope.CUSTOMER.id}"/>" name="customer">
							
							<div class="form-actions">
		                 			<button type="submit" class="btn btn-large template-btn"><s:message code="button.label.submit2" text="Submit"/></button>
		           	  		 </div>
		
		
		      					
							
						</div>
						</div>
						
						</form>
						</div>
						
				</c:if>


				 <c:if test="${requestScope.CONTENT['DASHBOARD']!=null}">
			     <div class="row-fluid">	
			     	<div class="span-12 col-md-12 common-row">
			     		<c:out value="${requestScope.CONTENT['DASHBOARD']}"/>
			     	</div>
			     </div>
			     </c:if>

			     <div class="row-fluid">
			
			
					   <div class="span6 col-md-6 no-padding-left">
	
							<div class="checkout-box">
								<span class="box-title">
									<p class="p-title"><s:message code="label.customer.myaccount" text="My account"/></p>
								</span>
								<jsp:include page="/pages/shop/common/customer/customerProfileMenu.jsp" />
							</div>
	
	
					   </div>
					   <div class="span6 col-md-6 no-padding-left">
							<div class="checkout-box">
								<span class="box-title">
									<p class="p-title"><s:message code="label.order.pastorders" text="Past orders"/></p>
								</span>
								<jsp:include page="/pages/shop/common/customer/customerOrdersMenu.jsp" />
							</div>
	
					   </div>
				 </div>
			</div>
		<!-- close row-fluid--> 
	</div>
	<!--close .container "main-content" -->