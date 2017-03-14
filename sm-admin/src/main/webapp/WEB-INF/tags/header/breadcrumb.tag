<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="breadcrumb" required="true" %>


<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            Dashboard
            <small>Version 2.0</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href=""><i class="fa fa-dashboard"></i> Home</a></li>
            <c:if test="${not empty breadcrumb}">
            <li class="active">${breadcrumb}</li>
            </c:if>
        </ol>
    </section>