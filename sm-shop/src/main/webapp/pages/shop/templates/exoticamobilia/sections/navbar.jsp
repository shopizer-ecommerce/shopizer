
<%
response.setCharacterEncoding("UTF-8");
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");
response.setDateHeader ("Expires", -1);
%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm"%>
<%@ taglib uri="/WEB-INF/shopizer-functions.tld" prefix="display"%>


<c:set var="req" value="${request}" />

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>



	
	<!-- NAVIGATION MENU -->
	<div class="col-md-8">
		<div class="header-right clearfix">
			<div class="main-navigation animated">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mainNavigation">
							    <span class="sr-only"></span> 
							    <span class="icon-bar"></span>
							    <span class="icon-bar"></span>
							    <span class="icon-bar"></span>
						 </button>

 						<c:if test="${requestScope.CONFIGS['displaySearchBox'] == true}">
	 						<button type="button" class="navbar-toggle searchButton no-desktop">
								    <i class="fa fa-search"></i>
							 </button>
	 
							 <!-- search bar collapsed -->
							 <div id="responsiveSearchFieldGroup" class="no-desktop">
								<input type="text" class="typeahead navbar-toggle"
									placeholder="" id="responsiveSearchField"> 
							 </div>
						 </c:if>
						 
						 
						 
					</div>

	
					<div id="mainNavigation" class="collapse navbar-collapse">
	
						<ul class="nav navbar-nav navbar-right" id="topMain">

							<c:set var="code" value="${category.code}"/>
							<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
								<li
									class="<sm:activeLink linkCode="${category.description.friendlyUrl}" activeReturnCode="active"/> <c:if test="${fn:length(category.children)>0}">dropdown mega-menu</c:if>">
									<a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="<c:if test="${fn:length(category.children)>0}">dropdown-toggle</c:if> active <c:if test="${category.code==code}">currentSelectedLink</c:if>" <c:if test="${fn:length(category.children)>0}">data-toggle="dropdown"</c:if>> <span class="name">${category.description.name}</span></a>
											<c:if test="${fn:length(category.children)>0}">
													<ul class="dropdown-menu">
															<li>
																<div class="row">
																		<c:if test="${requestScope.CONTENT[category.code]!=null}">
																		<div class="col-sm-4 col-md-6">
																			<c:if test="${requestScope.CONTENT[category.code].description!=null}">
																			<c:out value="${requestScope.CONTENT[category.code].description}" escapeXml="false"/>
																			</c:if>
																		</div>
																		</c:if>

																		 <div class="mega-menu-items <c:choose><c:when test="${requestScope.CONTENT[category.code]!=null}">col-sm-8 col-md-6</c:when><c:otherwise>col-sm-12 col-md-12</c:otherwise></c:choose>">
																			<h4><a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${child.id}"/>"><c:out value="${category.description.name}"/></a></h4>
																				<div class="divider"></div>
																				<ul class="menu">
																					<c:forEach items="${category.children}" var="child">
																						<li><a href="<c:url value="/shop/category/${child.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${child.id}"/>"><c:out value="${child.description.name}"/></a></li>		
																					</c:forEach>
																				</ul>
																         </div>
																   </div>
															</li>
														</ul>
													
													<!-- mega-menu end -->
													</c:if>
								</li>
							</c:forEach>
	
						</ul>
					</div>
				</nav>
				<!-- /NAVIGATION MENU -->
			</div>
		</div>
	</div>


  </div>
</header>