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
					<s:message code="label.product.searchkeywords" text="Search keywords" />
				</h3>
				
				
			
			<c:url var="addKeyword" value="/admin/products/product/addKeyword.html" />
			<form:form method="POST" enctype="multipart/form-data" commandName="productKeyword" action="${addKeyword}">
				<form:errors path="*" cssClass="alert alert-error" element="div" />
				<div id="store.success" class="alert alert-success"	style="<c:choose><c:when test="${success!=null}">display:block;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
					<s:message code="message.success" text="Request successfull" />
				</div>
				<br/>
				<strong><c:out value="${product.sku}"/></strong>
				<br/><br/>
			
				<div class="control-group">
					<label><s:message code="label.generic.language" text="Language"/></label>
			  		<div class="controls">
	                        		<form:select path="languageCode">
					  					<form:options items="${store.languages}" itemValue="code" itemLabel="code"/>
				       				</form:select>
	                                <span class="help-inline"><form:errors path="languageCode" cssClass="error" /></span>
					</div>
				</div>
			
				<div class="control-group">
                        <label><s:message code="label.generic.keyword" text="Keyword"/></label>
                        <div class="controls">
                                    <form:input id="keyword" cssClass="highlight" path="keyword"/>
                                    <span class="help-inline"><form:errors path="keyword" cssClass="error" /></span>
                        </div>
                  </div>
			

				<input type="hidden" name="productId" value="${product.id}">
				<div class="form-actions">
                  		<div class="pull-right">
                  			<button type="submit" class="btn btn-success"><s:message code="label.generic.add" text="Add"/></button>
                  		</div>
            	 </div>
			
		  </form:form>
				
				
				 <br/>
				 <!-- Listing grid include -->
				 
				 <c:set value="/admin/products/product/keywords/paging.html?id=${product.id}" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/products/product/removeKeyword.html?id=${product.id}" var="removeUrl" scope="request"/>
				 <c:set value="/admin/products/product/keywords.html?id=${product.id}" var="afterRemoveUrl" scope="request"/>
				 <c:set var="entityId" value="code" scope="request"/>
				 <c:set var="componentTitleKey" value="label.product.searchkeywords" scope="request"/>
				 <c:set var="groupByEntity" value="language" scope="request" />
				 <c:set var="gridHeader" value="/pages/admin/products/keywords-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>
				 <c:set var="canEdit" value="false" scope="request"/>

            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			

		</div>
	   </div>
	</div>
</div>		      			     