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


				<div class="page-intro" style="margin-top: 0px;">
					<div class="container">
						<div class="row">
							<div class="col-md-12">
								<ol class="breadcrumb">
								  <c:forEach items="${requestScope.BREADCRUMB.breadCrumbs}" var="breadcrumb" varStatus="count">
									  <li class="active"><c:if test="${count.index==0}"><i class="fa fa-home pr-10"></i></c:if>
									    <a href="${breadcrumb.url}<sm:breadcrumbParam/>">${breadcrumb.label}</a>
									  </li>
								  </c:forEach>
								</ol>
							</div>
						</div>
					</div>
				</div>



