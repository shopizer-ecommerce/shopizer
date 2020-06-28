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


          <div class="control-group">
            <label class="control-label"><s:message code="label.payment.paypal.usepaypal" text="Use PayTM"/></label>
            <div class="controls">
               <jsp:include page="/pages/shop/common/checkout/selectedPayment.jsp" />
            </div>
          </div>
		 
		 <div class="control-group">
		 	<!-- PayPal Logo 
		 	<a href="https://www.paypal.com/webapps/mpp/paypal-popup" title="How PayPal Works" onclick="javascript:window.open('https://www.paypal.com/webapps/mpp/paypal-popup','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img src="https://www.paypalobjects.com/webstatic/mktg/logo/AM_mc_vs_dc_ae.jpg" width="200" border="0" alt="PayPal Acceptance Mark"></a>
		 	-->
		 
		 	<!-- PayTM 
		 	<table border="0" cellpadding="10" cellspacing="0" align="center"><tr><td align="center"></td></tr><tr><td align="center"><a href="https://blog.paytm.com/how-to-use-paytm-5613fda6956b" title="How PayTM Works" onclick="javascript:window.open('https://blog.paytm.com/how-to-use-paytm-5613fda6956b','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img src="https://upload.wikimedia.org/wikipedia/commons/4/42/Paytm_logo.png" border="0" alt="PayPal Logo"></a></td></tr></table> PayPal Logo -->
			
			<table border="0" cellpadding="10" cellspacing="0" align="center"><tr><td align="center"></td></tr><tr><td align="center"><a href="https://blog.paytm.com/how-to-use-paytm-5613fda6956b" title="How PayTM Works" onclick="javascript:window.open('https://blog.paytm.com/how-to-use-paytm-5613fda6956b','WIPaypal','toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=yes, resizable=yes, width=1060, height=700'); return false;"><img src="<c:url value="/resources/img/paytm_logo.jpg"/>"  border="0" alt="PayPal Logo"></a></td></tr></table>
		 </div>