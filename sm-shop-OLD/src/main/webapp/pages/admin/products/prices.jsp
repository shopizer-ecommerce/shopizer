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
									
				

				
				<h3><s:message code="label.product.prices" text="Product prices" /></h3> 
				<br/>
				<strong><c:out value="${product.sku}"/></strong>			

				<br/>	
				<a href="<c:url value="/admin/products/price/create.html?productId=${product.id}&availabilityId=${availability.id}"/>"><s:message code="label.product.price.create" text="Create price" /></a>	
				<br/><br/>
								
				 <!-- Listing grid include -->
				 
				 <c:set value="/admin/products/prices/paging.html?productId=${product.id}" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/products/price/remove.html?productId=${product.id}" var="removeUrl" scope="request"/>
				 <c:set value="/admin/products/price/edit.html" var="editUrl" scope="request"/>
				 <c:set value="/admin/products/prices.html?id=${product.id}" var="afterRemoveUrl" scope="request"/>
				 <c:set var="entityId" value="priceId" scope="request"/>
				 <c:set var="appendQueryStringToEdit" value="productId=${product.id}" scope="request"/>
				 <c:set var="componentTitleKey" value="label.product.prices" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/products/prices-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>

            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->
			      			     
			      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     