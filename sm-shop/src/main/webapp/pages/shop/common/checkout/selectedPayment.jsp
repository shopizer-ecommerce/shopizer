<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>  

		  <!--
		  <span style="margin-left:2px;">
		  <input type="radio" onClick="setPaymentModule('${requestScope.paymentMethod.paymentMethodCode}');" name="paymentMethodType" value="<c:out value="${requestScope.paymentMethod.paymentType}"/>" <c:choose><c:when test="${requestScope.selectedPaymentMethod!=null && (requestScope.selectedPaymentMethod==requestScope.paymentMethod.paymentType)}"> checked</c:when><c:otherwise><c:if test="${requestScope.selectedPaymentMethod==null && requestScope.paymentMethod.defaultSelected==true}"> checked</c:if></c:otherwise></c:choose>/>
		  </span>
		  -->
		  
		  
		  
		  
		  
		  
		  
		  