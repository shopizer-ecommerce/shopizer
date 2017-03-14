<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- sidebar menu: : style can be found in sidebar.less -->

<ul class="sidebar-menu">
    <li class="header">MAIN NAVIGATION</li>

    <c:forEach items="${requestScope.MENULIST}" var="menu">
       <%-- <sec:authorize access="hasRole('${menu.role}') and fullyAuthenticated">--%>
            <li class="${not empty activeMenus[menu.code] ? 'active treeview' : 'treeview'} ">
                <a href="<c:url value="${menu.url}" />">  <%--<c:url value="${menu.url}" />--%>
                    <i class="${menu.icon}"></i>
                    <span><spring:message code="menu.${menu.code}" text="${menu.code}"/></span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                     </span>
                </a>

                <c:forEach items="${menu.menus}" var="menus">
                    <ul class="treeview-menu">
                        <li>
                            <a href="<c:url value="${menus.url}" />">
                                <i class="fa fa-circle-o"></i>
                                    <spring:message code="menu.${menus.code}" text="${menus.code}"/>
                            </a>
                        </li>
                    </ul>
                </c:forEach>
            </li>

        <%-- </sec:authorize>--%>
    </c:forEach>

</ul>