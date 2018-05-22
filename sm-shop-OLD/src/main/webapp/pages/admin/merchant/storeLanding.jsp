<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>


<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>


<script>




</script>


<div class="tabbable">


				<jsp:include page="/common/adminTabs.jsp" />
				
				<h3><s:message code="label.storefront.landingpage" text="Landing page information" /></h3>	
				<br/>


				<c:url var="merchant" value="/admin/store/saveLanding.html"/>


				<form:form method="POST" commandName="storeLanding" action="${merchant}">
				
					<form:errors path="*" cssClass="alert alert-error" element="div" />
					<div id="store.success" class="alert alert-success" style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>"><s:message code="message.success" text="Request successfull"/></div>    
				
				
					<c:forEach items="${storeLanding.descriptions}" var="description" varStatus="counter">
					

						<!-- Title -->
		      			<div class="control-group">
		                        <label><s:message code="label.storefront.landingpage.title" text="Title"/> (<c:out value="${description.language.code}"/>)</label>
		                        <div class="controls">
		                        		<form:input id="title${counter.index}" cssClass="input-large highlight" path="descriptions[${counter.index}].title" />
		                                    <span class="help-inline"><form:errors path="descriptions[${counter.index}].title" cssClass="error" /></span>
		                        </div>
		                  </div>
		                  
		                  <!-- Description -->
		                  <div class="control-group">
		                        <label><s:message code="label.storefront.metatags.description" text="Tags description"/> (<c:out value="${description.language.code}"/>)</label>
		                        <div class="controls">
		                        			<form:textarea id="description${counter.index}" cssClass="span6" path="descriptions[${counter.index}].description" rows="5" />
		                                    <span class="help-inline"><form:errors path="descriptions[${counter.index}].description" cssClass="error" /></span>
		                        </div>
		                  </div>
		                  
		                  
		                  
		                  
		                  <!-- Keywords -->
		                  <div class="control-group">
		                        <label><s:message code="label.storefront.metatags.keywords" text="Keywords"/> (<c:out value="${description.language.code}"/>)</label>
		                        <div class="controls">
		                                    <form:textarea id="keywords${counter.index}" cssClass="span6" path="descriptions[${counter.index}].keywords" rows="5"/>
		                                    <span class="help-inline"><form:errors path="descriptions[${counter.index}].keywords" cssClass="error" /></span>
		                        </div>
		                  </div>
		                  
		                  
		                  <!-- Content -->
		                 <div class="control-group">
                        	<label><s:message code="label.storefront.storetext" text="Page content"/> (<c:out value="${description.language.code}"/>)</label>
                        	<div class="controls">
                        

                        
		                        <textarea cols="30" id="descriptions[${counter.index}].homePageContent" class="ckeditor" name="descriptions[${counter.index}].homePageContent">
		                        		<c:out value="${description.homePageContent}" escapeXml="true"/>
		                        </textarea>


                        	</div>
                        
                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('descriptions[${counter.index}].homePageContent',
							{
								skin : 'office2003',
								toolbar : 
								[
									['Source','-','Save','NewPage','Preview'], 
									['Cut','Copy','Paste','PasteText','-','Print'], 
									['Undo','Redo','-','Find','-','SelectAll','RemoveFormat'], '/', 
									['Bold','Italic','Underline','Strike','-','Subscript','Superscript'], 
									['NumberedList','BulletedList','-','Outdent','Indent','Blockquote'], 
									['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'], 
									['Link','Unlink','Anchor'], 
									['Image','Flash','Table','HorizontalRule','SpecialChar','PageBreak'], '/', 
									['Styles','Format','Font','FontSize'], ['TextColor','BGColor'], 
									['Maximize', 'ShowBlocks'] 
								],
								
								filebrowserWindowWidth : '720',
        						filebrowserWindowHeight : '740',
								filebrowserImageBrowseUrl :    '<c:url value="/admin/content/fileBrowser.html"/>'
								

							});

						//]]>
						</script>

                  </div>
		                  
		                  

		                  <form:hidden path="descriptions[${counter.index}].language.code" />
	                  
	                  
	                  </c:forEach>


				      <div class="form-actions">
	                  		<div class="pull-right">
	                  			<button type="submit" class="btn btn-success"><s:message code="button.label.submit2" text="Submit"/></button>
	                  		</div>
	            	 </div>


      					
				</form:form>
				
				
				
				

				
				
				
				


</div>