<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>
<script src="<c:url value="/resources/js/jquery.alphanumeric.pack.js" />"></script>


	<script type="text/javascript">

	$(function(){
		$('#order').numeric();
		if($("#code").val()=="") {
			$('.btn').addClass('disabled');
		}
		<c:forEach items="${content.descriptions}" var="description" varStatus="counter">
			$("#name${counter.index}").friendurl({id : 'seUrl${counter.index}'});
		</c:forEach>
	});


	function validateCode() {
		$('#checkCodeStatus').html('<img src="<c:url value="/resources/img/ajax-loader.gif" />');
		$('#checkCodeStatus').show();
		var code = $("#code").val();
		var id = $("#id").val();
		checkCode(code,id,'<c:url value="/admin/content/checkContentCode.html" />');
	}

	function callBackCheckCode(msg,code) {

		if(code==0) {
			$('.btn').removeClass('disabled');
		}
		if(code==9999) {

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

<%@ page session="false" %>
<div class="tabbable">
   <jsp:include page="/common/adminTabs.jsp" />
	<div class="tab-content">
		<div class="tab-pane active" id="catalogue-section">
           <div class="sm-ui-component">


           		<h3>
					<c:choose>
						<c:when test="${content.id!=null && content.id>0}">
								<s:message code="label.content.manage.content" text="Manage content" /> <c:out value="${content.code}"/>
						</c:when>
						<c:otherwise>
								<s:message code="label.content.manage.content" text="Manage content" />
						</c:otherwise>
					</c:choose>

				</h3>


			<!--  Add content page / box -->

			<c:choose>
				<c:when test="${boxes!=null}">
						<c:url var="saveContent" value="/admin/content/boxes/save.html" />
				</c:when>
				<c:otherwise>
						<c:url var="saveContent" value="/admin/content/pages/save.html" />
				</c:otherwise>
			</c:choose>


			<form:form method="POST" enctype="multipart/form-data" modelAttribute="content" action="${saveContent}">

			<form:errors path="*" cssClass="alert alert-error" element="div" />
			<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
					<s:message code="message.success" text="Request successfull" />
			</div>

			<div class="control-group">
                     <label><s:message code="label.entity.visible" text="Visible"/></label>
                     <div class="controls">
                                 <form:checkbox path="visible" />
                     </div>
            </div>

            <div class="control-group">
                     <label><s:message code="label.content.page.displaymainmenu" text="Show this page in main menu"/></label>
                     <div class="controls">
                                 <form:checkbox path="linkToMenu" />
                     </div>
            </div>

            <div class="control-group">
                     <label><s:message code="label.entity.code" text="Code"/></label>
	                        <div class="controls">
	                        		<form:input id="code" cssClass="input-large highlight" path="code" onblur="validateCode()"/>
	                                <span class="help-inline"><div id="checkCodeStatus" style="display:none;"></div><form:errors path="code" cssClass="error" /></span>
	                  </div>
            </div>

            <div class="control-group">
                        <label><s:message code="label.entity.order" text="Sort order"/></label>
                        <div class="controls">
                                    <form:input id="order" cssClass="" path="sortOrder"/>
                                    <span class="help-inline"><form:errors path="sortOrder" cssClass="error" /></span>
                        </div>
             </div>


             <c:if test="${positions!=null}">
             <div class="control-group">
	                     <label><s:message code="label.content.boxes.position" text="Box position"/></label>
	                     <div class="controls">

	                        		  <form:select cssClass="list" path="contentPosition">
					  								<form:options items="${boxPositions}" itemValue="value" itemLabel="key" />
				       				  </form:select>
	                                  <span class="help-inline"><form:errors path="contentPosition" cssClass="error" /></span>
	                     </div>
	         </div>
             </c:if>



                 <c:forEach items="${content.descriptions}" var="description" varStatus="counter">

                 <div class="control-group">

                 		<c:choose>
							<c:when test="${boxes!=null}">
									<label><s:message code="label.content.boxes.name" text="Box name"/> (<c:out value="${description.language.code}"/>)</label>
							</c:when>
							<c:otherwise>
									<label><s:message code="label.content.pages.page.name" text="Page name"/> (<c:out value="${description.language.code}"/>)</label>
							</c:otherwise>
						</c:choose>

                        <div class="controls">
                        			<form:input cssClass="input-large highlight" id="name${counter.index}" path="descriptions[${counter.index}].name"/>
                        			<span class="help-inline"><form:errors path="descriptions[${counter.index}].name" cssClass="error" /></span>
                        </div>

                  </div>

                  <c:if test="${boxes==null}">
                  <div class="control-group">
                        <label><s:message code="label.sefurl" text="SEF Url"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">
                        			<form:input id="seUrl${counter.index}" path="descriptions[${counter.index}].seUrl"/>
                        			<span class="help-inline"><form:errors path="descriptions[${counter.index}].seUrl" cssClass="error" /></span>
                        </div>

                  </div>
                  </c:if>

                  <div class="control-group">
                        <label><s:message code="label.content.pages.page.content" text="Page content"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">



                        <textarea cols="30" id="descriptions[${counter.index}].description" class="ckeditor" name="descriptions[${counter.index}].description">
                        		<c:out value="${content.descriptions[counter.index].description}"/>
                        </textarea>


                        </div>

                        <script type="text/javascript">
						//<![CDATA[

							CKEDITOR.replace('descriptions[${counter.index}].description',
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

                  <c:if test="${boxes==null}">
                  <div class="control-group">
                        <label><s:message code="label.generic.title" text="Metatag title"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">
                        			<form:input path="descriptions[${counter.index}].metatagTitle"/>
                        			<span class="help-inline"><form:errors path="descriptions[${counter.index}].metatagTitle" cssClass="error" /></span>
                        </div>
                  </div>

                  <div class="control-group">
                        <label><s:message code="label.generic.keywords" text="Keywords"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">
                        			<form:input path="descriptions[${counter.index}].metatagKeywords"/>
                        			<span class="help-inline"><form:errors path="descriptions[${counter.index}].metatagKeywords" cssClass="error" /></span>
                        </div>
                  </div>
                  <c:if test="${productGroups!=null}">
                  <div class="control-group">
	                        <label><s:message code="label.product.customGroup.title" text="Product group"/></label>
	                        <div class="controls">
	                        <form:select cssClass="productGrouplist" path="productGroup">
	                                <form:option value="">&nbsp;</form:option>
					  				<form:options items="${productGroups}" itemValue="code" itemLabel="code"/>
				       		</form:select>
	                         <span class="help-inline"><form:errors path="productGroup" cssClass="error" /></span>
	                  		</div>
	             </div>
	             </c:if>

                 <div class="control-group">
                        <label><s:message code="label.content.metatag.description" text="Metatag description"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="controls">
                        			<form:input path="descriptions[${counter.index}].metatagDescription"/>
                        			<span class="help-inline"><form:errors path="descriptions[${counter.index}].metatagDescription" cssClass="error" /></span>
                        </div>
                  </div>
                  </c:if>

                  <form:hidden path="descriptions[${counter.index}].language.code" />
                  <form:hidden path="descriptions[${counter.index}].id" />

            </c:forEach>

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