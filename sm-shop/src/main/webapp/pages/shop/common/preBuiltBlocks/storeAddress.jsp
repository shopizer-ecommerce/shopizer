<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ page session="false" %>



 <address> 
 	<div itemscope itemtype="http://schema.org/Organization"> 
 	<strong><span itemprop="name"><c:out value="${requestScope.MERCHANT_STORE.storename}"/></span></strong><br/>  
 	<div itemprop="address" itemscope itemtype="http://schema.org/PostalAddress"> 
 	<span itemprop="streetAddress"><c:out value="${requestScope.MERCHANT_STORE.storeaddress}"/> <c:out value="${requestScope.MERCHANT_STORE.storecity}"/></span><br/>
 	<span itemprop="addressLocality"><c:choose><c:when test="${not empty requestScope.MERCHANT_STORE.storestateprovince}"><c:out value="${requestScope.MERCHANT_STORE.storestateprovince}"/></c:when><c:otherwise><script>$.ajax({url: "<c:url value="/shop/reference/zoneName"/>",type: "GET",data: "zoneCode=${requestScope.MERCHANT_STORE.zone.code}",success: function(data){$('#storeZoneName').html(data)}})</script><span id="storeZoneName"><c:out value="${requestScope.MERCHANT_STORE.zone.code}"/></span></c:otherwise></c:choose>,
 	<span id="storeCountryName"><script>$.ajax({url: "<c:url value="/shop/reference/countryName"/>",type: "GET",data: "countryCode=${requestScope.MERCHANT_STORE.country.isoCode}",success: function(data){$('#storeCountryName').html(data)}})</script></span></span><br/>
 	<span itemprop="postalCode"><c:out value="${requestScope.MERCHANT_STORE.storepostalcode}"/></span><br/>
 	<abbr title="Phone"><s:message code="label.generic.phone" text="Phone" /></abbr>: <span itemprop="telephone"><c:out value="${requestScope.MERCHANT_STORE.storephone}"/></span>
 	</div>
 	</div>
 </address> 
