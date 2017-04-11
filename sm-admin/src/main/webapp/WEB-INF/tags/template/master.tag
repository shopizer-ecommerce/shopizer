<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true" %>

<%@ attribute name="pageTitle" required="false" rtexprvalue="true" %>
<%@ attribute name="metaDescription" required="false" %>
<%@ attribute name="metaKeywords" required="false" %>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"  %>

<!DOCTYPE html>
<html lang="${currentLanguage.isocode}">
<head>

    <%--

    <title>
        ${not empty pageTitle ? pageTitle : not empty cmsPage.title ? cmsPage.title : 'Accelerator Title'}
    </title> --%>

        <title>Shopizer | Dashboard</title>

    <%-- Meta Content --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <!-- Tell the browser to be responsive to screen width -->
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">


        <c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
        <input type="hidden" value="${contextPath}" name="adminApplicationContext" id="adminApplicationContext"/>
    <%-- Favourite Icon --%>
    <spring:theme code="img.favIcon" text="/" var="favIconPath"/>
    <link rel="shortcut icon" type="image/x-icon" media="all" href="${originalContextPath}${favIconPath}" />

    <%-- CSS Files Are Loaded First as they can be downloaded in parallel --%>
    <template:styleSheet/>
        <!-- jQuery 2.2.3 -->
        <script src="${contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>

</head>

<body class="hold-transition skin-blue sidebar-mini">


<%-- <tiles:insertAttribute name="body"/>  --%>

<div class="wrapper">

   <header class="main-header">


       <!-- Header Navbar: style can be found in header.less -->
        <template:header/>
   </header>
   <!-- Left side column. contains the logo and sidebar -->
   <aside class="main-sidebar">
       <!-- sidebar: style can be found in sidebar.less -->
       <section class="sidebar">
           <!-- Sidebar user panel -->
           <div class="user-panel">
               <div class="pull-left image">
                   <img src="${contextPath}/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
               </div>
               <div class="pull-left info">
                   <p>Alexander Pierce</p>
                   <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
               </div>
           </div>
           <!-- search form -->
           <form action="#" method="get" class="sidebar-form">
               <div class="input-group">
                   <input type="text" name="q" class="form-control" placeholder="Search...">
                   <span class="input-group-btn">
               <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
               </button>
             </span>
               </div>
           </form>
           <!-- /.search form -->
           <!-- sidebar menu: : style can be found in sidebar.less -->
          <template:menu/>

       </section>
       <!-- /.sidebar -->
   </aside>



       <!-- Main content -->
            <tiles:insertAttribute name="body"/>
       <!-- /.content -->
   </div>
   <!-- /.content-wrapper -->

   <footer class="main-footer">
       <div class="pull-right hidden-xs">
           <b>Version</b> 2.3.8
       </div>
       <strong>Copyright &copy; 2014-2016 <a href="http://almsaeedstudio.com">Almsaeed Studio</a>.</strong> All rights
       reserved.
   </footer>





</div>
<!-- ./wrapper -->










<%-- Inject the page body here --%>
<jsp:doBody/>



<%-- Load JavaScript required by the site --%>
<template:javascript/>



</body>



</html>