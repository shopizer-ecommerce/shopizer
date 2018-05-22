<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>



						<address>
							<c:choose>
								<c:when test="${not empty requestScope.address.company}">
									${requestScope.address.company}<br/>
								</c:when>
								<c:otherwise>
									${requestScope.address.firstName}&nbsp;${requestScope.address.lastName}<br />
								</c:otherwise>
							</c:choose>


							<c:if test="${not empty requestScope.address.address}"> ${requestScope.address.address} <br />
							</c:if>
							
							
							<c:if test="${not empty requestScope.address.city}">${requestScope.address.city} <br />
							</c:if>
							<c:choose><c:when test="${not empty requestScope.address.stateProvince}"><c:out value="${requestScope.address.stateProvince}"/></c:when><c:otherwise><script>$.ajax({url: "<c:url value="/shop/reference/zoneName"/>",type: "GET",data: "zoneCode=${requestScope.address.zone}",success: function(data){$('#zone<c:out value="${requestScope.addressType}"/>').html(data)}})</script><span id="zone<c:out value="${requestScope.addressType}"/>"><c:out value="${requestScope.address.zone}"/></span></c:otherwise></c:choose>,
							
							<c:if test="${not empty requestScope.address.country}">
							<span id="country<c:out value="${requestScope.addressType}"/>"><script>$.ajax({url: "<c:url value="/shop/reference/countryName"/>",type: "GET",data: "countryCode=${requestScope.address.country}",success: function(data){$('#country<c:out value="${requestScope.addressType}"/>').html(data)}})</script></span><br/>
							</c:if>
							<c:if test="${not empty requestScope.address.postalCode}"> ${requestScope.address.postalCode}<br />
							</c:if>
							<c:if test="${not empty requestScope.address.phone}">${requestScope.address.phone}</c:if>

						
						</address> 
