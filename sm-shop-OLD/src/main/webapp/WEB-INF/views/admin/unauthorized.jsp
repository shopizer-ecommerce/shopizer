<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html>

	<%
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", -1);
	%>

	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

	<%@page contentType="text/html"%>
	<%@page pageEncoding="UTF-8"%>





	<head>
		<meta http-equiv="Pragma" content="no-cache">
			<meta http-equiv="expires" content="0">
				<title><s:message code="label.storeadministration"
						text="Store administration" />
				</title>




<style type=text/css>
#logon {
	margin: 0px auto;
	width: 550px
}


#controls {
	margin-left: -50px;
	margin-top: 30px;
}




</style>




				<link
					href="<c:url value="/resources/css/bootstrap/css/sm-bootstrap.css" />"
					rel="stylesheet" />
				<link
					href="<c:url value="/resources/css/sm-bootstrap-responsive.css" />"
					rel="stylesheet" />
				<link href="<c:url value="/resources/css/shopizer.css" />"
					rel="stylesheet" />


				<style type=text/css>
.sm label {
	color: #EBEBEB;
	font-size: 16px;
}

.sm a {
	color: #EBEBEB;
	font-size: 16px;
}





</style>


	</head>

	<body>

		<div id="tabbable" class="sm">

			<br />
			<br />

			<div id=logon>

				<div class="row">
					<h3>
					<s:message code="message.login.duallogin" text="Dual login not authorized on the same browser"/>
					
					</h3>
				</div>

			</div>
		</div>
	</body>
</html>