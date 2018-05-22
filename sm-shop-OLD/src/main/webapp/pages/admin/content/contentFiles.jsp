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
					<s:message code="label.content.files" text="Files library" />
				</h3>
				
				
			<!--  Add content files -->
			<c:url var="saveContentFiles" value="/admin/content/static/saveFiles.html" />
			<form:form method="POST" enctype="multipart/form-data" commandName="contentFiles" action="${saveContentFiles}">
			<form:errors path="*" cssClass="alert alert-error" element="div" />
			<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
					<s:message code="message.success" text="Request successfull" />
			</div>
			
			<div class="control-group" style="margin-top:15px;">
			  <div class="controls">
					<input class="input-file" id="file" name="file" type="file" multiple="multiple">
					<!-- <input class="input-file" id="image1" name="image[1]" type="file"><br /> 
					<input 	class="input-file" id="image2" name="image[2]" type="file"><br />
					<input class="input-file" id="image3" name="image[3]" type="file"><br /> -->
				</div>
			</div>
			
			
			<div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.upload.files" text="Upload files"/></button>
                  			

                  		</div>

            	 </div>
			
			
		  </form:form>
				
				
				
				<br />
				<!-- Listing grid include -->
				<c:set value="/admin/content/static/page.html" var="pagingUrl" scope="request" />
				<c:set value="/admin/content/static/removeFile.html" var="removeUrl" scope="request" />
				<c:set value="/admin/content/static/contentFiles.html" var="refreshUrl" scope="request" />
				<c:set var="componentTitleKey" value="menu.content-files" scope="request" />
				<c:set var="canRemoveEntry" value="true" scope="request" />
				<c:set var="canEdit" value="false" scope="request"/>
				<c:set var="gridHeader" value="/pages/admin/content/contentFiles-gridHeader.jsp" scope="request"/>
				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
				<!-- End listing grid include -->
			

		</div>
	   </div>
	</div>
</div>	