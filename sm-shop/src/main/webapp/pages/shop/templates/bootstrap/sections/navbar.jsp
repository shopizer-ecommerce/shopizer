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
<%@ taglib uri="/WEB-INF/shopizer-functions.tld" prefix="display" %> 

<!-- TT Typeahead js files -->
<script src="<c:url value="/resources/js/hogan.js" />"></script>
<script src="<c:url value="/resources/js/typeahead.min.js" />"></script>

<script type="text/javascript">

$(document).ready(function() { 

	$('#searchField').typeahead({
		name: 'shopizer-search',
		<c:if test="${requestScope.CONFIGS['useDefaultSearchConfig'][requestScope.LANGUAGE.code]==true}">
		  <c:if test="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]!=null}">
		prefetch: '<c:out value="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]}"/>',
		  </c:if>
	    </c:if>


	    remote: {
    		url: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.json"/>?q=%QUERY',
        	filter: function (parsedResponse) {
            	// parsedResponse is the array returned from your backend
            	console.log(parsedResponse);

            	// do whatever processing you need here
            	return JSON.parse(parsedResponse);
        	}
    	},
		template: [
		'<p class="name">{{name}}</p>',
		'<p class="description">{{description}}</p>'
		].join(''),
		engine: Hogan
		});
	
	
	

});

</script>

<c:set var="req" value="${request}" />
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>


            <!-- Start Navbar-->
            <div id="storeBar" class="row-fluid">

				<div class="span4 pull-left">
						<nav class="logo">
							 <c:choose>
	                		<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
	                			<img class="logoImage" src="<sm:storeLogo/>"/>
	                		</c:when>
	                		<c:otherwise>
	                			<h1>
	                			<a href="<c:url value="/shop/"/>">
	                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
	                			</a>  
	                			</h1>
	                		</c:otherwise>
	                	  </c:choose>  
						</nav>
				</div>
				<div class="span8 pull-right">

						<nav id="menu" class="pull-right">
                    					<ul id="mainMenu">
                    						<!-- request contains url and url contains /shop -->
											<li class="">  
	                    					       <a href="<c:url value="/shop"/>" class="current">          
	                    					            <span class="name"><s:message code="menu.home" text="Home"/></span>     
	                    								<span class="desc"><s:message code="menu.home" text="Home"/></span>                                  
	                    						   </a>                         
	                    					</li>
	
	                    		            
	                    		            <c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
	    										<li class="">
	    											<a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
	    												<span class="name">${category.description.name}</span>
	    												<span class="desc">${category.description.highlights}</span> 
	    											</a>
	    										</li> 
											</c:forEach>
                    		            </ul>
                    		            
                    		            <div id="searchGroup" class="btn-group pull-right">
											<form id="searchForm" class="form-inline" method="post" action="<c:url value="/shop/search/search.html"/>">
												<input id="searchField" class="tt-query" name="q" type="text" placeholder="<s:message code="label.search.searchQuery" text="Search query" />" autocomplete="off" spellcheck="false" dir="auto" value="<c:out value="${q}"/>">
												<button id="searchButton" class="btn" type="submit"><s:message code="label.generic.search" text="Search" /></button>
											</form>
										</div>
                    		            
                    		            
						</nav>


				</div>
            </div>

			<!-- End Navbar-->