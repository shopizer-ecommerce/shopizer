<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>				
				





								<form action="<c:url value="/admin/shipping/addCustomRegion.html"/>" method="POST" class="form-inline">  
			      					<form:errors path="*" cssClass="alert alert-error" element="div" />
			      					1- <label class="required"><s:message code="label.shipping.addregion" text="Add region" /></label>
			      					<input type="text" class="span3" name="region" id="region"><!-- must be unique -->  
			      					<span class="help-inline"><div id="checkCodeStatus" style="display:none;"></div></span>
			      					<span class="help-inline">
	                        			<c:if test="${errorMessage!=null}">
	                        				<span id="identifiererrors" class="error"><c:out value="${errorMessage}"/></span>
	                        			</c:if>
	                        		</span>
			      					<button type="submit" class="btn btn-success"><s:message code="label.shipping.addregion" text="Add region"/></button>
			      				</form>	
								<br/>
								
								<c:url var="addShipping" value="/admin/shipping/addCountryToRegion.html"/> 
		                  		<form:form method="POST" commandName="customRegion" action="${addShipping}" cssClass="form-inline">
				
		                  			 		
		                        			2- <label><s:message code="label.region" text="Region"/></label>
		                        			
		                        					<form:select path="customRegionName">
						  								<form:options items="${customConfiguration.regions}" itemValue="customRegionName" itemLabel="customRegionName"/>
					       							</form:select>
		                        			
		                 			 
		                  			 
		                        			<label><s:message code="label.country" text="Country"/></label>
		                        					
		                        					<form:select path="countries[0]">
						  								<form:options items="${shippingCountries}" itemValue="isoCode" itemLabel="name"/>
					       							</form:select>
			      							<span class="help-inline">
	                        					<c:if test="${errorMessageAssociation!=null}">
	                        						<span id="identifiererrors" class="error"><c:out value="${errorMessage}"/></span>
	                        					</c:if>
	                        				</span>
		                 			
	                        		 
                  							<button type="submit" class="btn btn-success"><s:message code="label.generic.add" text="Add"/></button>

			      				</form:form>	
			      				
			      				
								 <!-- Listing grid include -->
								 <c:set value="/admin/shipping/weightBased/page.html" var="pagingUrl" scope="request"/>
								 <c:set value="/admin/shipping/weightBased/removeCountry.html" var="removeUrl" scope="request"/>
								 <c:set value="/admin/shipping/weightBased/edit.html" var="editUrl" scope="request"/>
								 <c:set value="/admin/shipping/weightBased.html" var="refreshUrl" scope="request"/>
								 <c:set var="entityId" value="regionCode" scope="request"/>
								 <c:set var="groupByEntity" value="region" scope="request"/>
								 <c:set var="componentTitleKey" value="module.shipping.weightBased" scope="request"/>
								 <c:set var="gridHeader" value="/pages/admin/shipping/weightBased-gridHeader.jsp" scope="request"/>
								 <c:set var="canRemoveEntry" value="true" scope="request"/>
				
				            	 <jsp:include page="/pages/admin/components/list.jsp"></jsp:include> 
								 <!-- End listing grid include -->
								 
								<br/><br/>
								<c:url var="addPrice" value="/admin/shipping/weightBased/edit.html"/> 
		                  		<form:form method="GET" commandName="customRegion" action="${addPrice}" cssClass="form-inline">
				
		                  			 		
		                        			3- <label><s:message code="label.region" text="Region"/></label>
		                        			
		                        					<form:select path="customRegionName">
						  								<form:options items="${customConfiguration.regions}" itemValue="customRegionName" itemLabel="customRegionName"/>
					       							</form:select>

		                 			
	                        		 
                  							<button type="submit" class="btn btn-success"><s:message code="label.entity.details" text="Details"/></button>

			      				</form:form>
			      				
			      				

