<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>		


<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>



                 
                 <div class="control-group">
                        <label class="required"><strong><s:message code="module.payment.moneyorder.adress" text="Cheque must be made to this address"/></strong></label>
	                        <div class="controls">
	                        <span class="help-inline">
	                        	<c:if test="${address!=null}">
	                        		<span id="identifiererrors" class="error"><s:message code="module.payment.moneyorder.message.address" text="Field in error"/></span>
	                        	</c:if>
	                        </span>
	                        		<br/>
	                        		
	                        		<textarea cols="30" id="integrationKeys['address']" name="integrationKeys['address']">
                        				<c:out value="${configuration.integrationKeys['address']}"/>
                        			 </textarea>
	                        		
			                        <script type="text/javascript">
									//<![CDATA[
			
										CKEDITOR.replace("integrationKeys['address']",
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

                  </div>
                  
     
            
                  
                  