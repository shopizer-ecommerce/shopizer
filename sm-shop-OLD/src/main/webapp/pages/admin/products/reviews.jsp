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
								
								

				
				<h3><s:message code="label.product.customer.reviews" text="Customer reviews" /></h3> 
				<br/>
				<strong><c:out value="${product.sku}"/></strong>			

				<br/>	


				 <!-- Listing grid include -->
				 <c:set value="/admin/products/reviews/paging.html?productId=${product.id}" var="pagingUrl" scope="request"/>
				 <c:set value="/admin/products/reviews/remove.html" var="removeUrl" scope="request"/>
				 <c:set value="/admin/products/reviews/paging.html?productId=${product.id}" var="refreshUrl" scope="request"/>
				 <c:set var="entityId" value="reviewId" scope="request"/>
				 <c:set var="componentTitleKey" value="label.product.reviews" scope="request"/>
				 <c:set var="gridHeader" value="/pages/admin/products/reviews-gridHeader.jsp" scope="request"/>
				 <c:set var="canRemoveEntry" value="true" scope="request"/>

            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
				 <!-- End listing grid include -->


			      			     
            	 
            	 
	      			     
      					</div>
      					

      			     
      			     


      			     
      			     
    


   					</div>


  					</div>

				</div>		      			     