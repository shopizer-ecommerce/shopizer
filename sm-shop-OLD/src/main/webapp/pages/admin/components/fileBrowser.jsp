<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 

<%@ page session="false" %>


<script src="<c:url value="/resources/js/ckeditor/ckeditor.js" />"></script>



<script language="javascript">
   var imgPrefix = '<sm:contentImage merchantStore="${requestScope.ADMIN_STORE}" imageName="" imageType="IMAGE"/>';

	function selectImage(img) {//ckeditor function
		var image = '<c:url value=""/>' + imgPrefix + img;
		window.opener.CKEDITOR.tools.callFunction(2, image);
		window.close();
	}
</script>
 

								<div class="sm-ui-component">
								<h3><s:message code="label.content.images" text="Images library" /></h3>	
								<br/>
								
								
								
								
				 <!-- Listing grid include -->
				 <c:set value="/admin/content/images/paging.html" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/content/contentImages.html" var="removeUrl" scope="request"/>
				 <c:set value="/admin/content/contentImages.html" var="refreshUrl" scope="request"/>
				 <c:set var="componentTitleKey" value="menu.content-images" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>

            	 <jsp:include page="/pages/admin/components/images-list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			      			     
			      			     
