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
           
           
           			<c:if test="${product.id!=null && product.id>0}">
						<c:set value="${product.id}" var="productId" scope="request"/>
						<jsp:include page="/pages/admin/products/product-menu.jsp" />
					</c:if>
           
           
				<h3>
					<s:message code="menu.catalogue-products-images" text="Images library" />&nbsp;<s:message code="label.generic.url" text="URL" />
				</h3>
				<br/>
				<strong><c:out value="${product.sku}"/></strong>
				<br/>
				
			<!--  Add content images -->
			<c:url var="saveProductImage" value="/admin/products/images/url/save.html" />
			<form:form method="POST" modelAttribute="productImage" action="${saveProductImage}">
			<form:errors path="*" cssClass="alert alert-error" element="div" />
			<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
					<s:message code="message.success" text="Request successfull" />
			</div>
			
			<div class="control-group" style="margin-top:15px;">
			  <label><s:message code="label.generic.url" text="URL" /></label>
			  <div class="controls">
					<form:input cssClass="highlight" id="productImageUrl" path="productImageUrl"/>
					<input type="hidden" name="id" id="id" value="${product.id}"/>
				</div>
			</div>
			
			<!-- Never a default image -->
			<input type="hidden" name="defaultImage" id="defaultImage" value="false"/>
			<%-- 
			<div class="control-group">
                 <label><s:message code="label.generic.default" text="Default image"/></label>

                 <div class="controls">
                        <form:checkbox path="defaultImage" />
                  </div>
                 **
                 <s:message code="label.product.defaultImage.message" text="When selecting default, this image will be the image displayed in the product page"/>
             </div>
             --%>
             
             <div class="control-group">
                 <label><s:message code="label.generic.imageType" text="Media type"/></label>

                 <div class="controls">
                        <form:select items="${mediaTypes}" path="imageType" />
                  </div>
             </div>

			
			
			<div class="form-actions">

                  		<div class="pull-right">

                  			<button type="submit" class="btn btn-success"><s:message code="button.label.add" text="Add"/></button>
                  			

                  		</div>

            	 </div>
			
			
		  </form:form>
				
				
				
				<br />
				<!-- Listing grid include -->
				<c:set value="/admin/products/images/url/page.html?productId=${product.id}" var="pagingUrl" scope="request" />
				<c:set value="/admin/products/images/remove.html" var="removeUrl" scope="request" />
				<c:set value="/admin/products/images/url/list.html?id=${product.id}" var="refreshUrl" scope="request" />
				<c:set var="componentTitleKey" value="menu.catalogue-products-images" scope="request" />
				<c:set var="canRemoveEntry" value="true" scope="request" />
				<c:set var="gridHeader" value="/pages/admin/products/product-images-url-gridHeader.jsp" scope="request"/> 
				<jsp:include page="/pages/admin/components/list.jsp"></jsp:include>
				<!-- End listing grid include -->
			

		</div>
	   </div>
	</div>
</div>	