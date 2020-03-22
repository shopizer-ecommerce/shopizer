
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

<script type="text/javascript">
//***** Search code *****
$(document).ready(function() { 

    //post search form
   $(".searchButton").click(function(e){
			var searchQuery = $('#searchField').val();
			var q = searchQuery;
			if(q==null || q =='') {
				return;
			}
			$('#hiddenQuery').val(q);
			var uri = '<c:url value="/shop/search/search.html"/>';
			e.preventDefault();//action url will be overriden
	        $('#hiddenSearchForm').attr('action',uri).submit();
   });

   
   
	
   var searchElements = new Bloodhound({
		datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
		queryTokenizer: Bloodhound.tokenizers.whitespace,
		<c:if test="${requestScope.CONFIGS['useDefaultSearchConfig'][requestScope.LANGUAGE.code]==true}">
		  <c:if test="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]!=null}">
		     prefetch: '<c:out value="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]}"/>',
		  </c:if>
	    </c:if>
	    remote: {
    		url: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.json"/>?q=%QUERY',
        	filter: function (parsedResponse) {
            	// parsedResponse is the array returned from your backend
            	//console.log(parsedResponse);
            	return parsedResponse;

            	// do whatever processing you need here
            	//return JSON.parse(parsedResponse);
        	}
    	}
	});
   
   searchElements.initialize();


	var searchTemplate =  Hogan.compile([
				     '<p class="suggestion-text"><font color="black">{{value}}</font></p>'
	             ].join(''));
	
	
    //full view search
	$('#searchField.typeahead').typeahead({
	    hint: true,
	    highlight: true,
	    minLength: 1
	}, {
		name: 'shopizer-search',
	    displayKey: 'value',
	    source: searchElements.ttAdapter(),
	    templates: {
	    	suggestion: function (data) { return searchTemplate.render(data); }
	    }
	});
    
    //responsive
	$('#responsiveSearchField.typeahead').typeahead({
	    hint: true,
	    highlight: true,
	    minLength: 1
	}, {
		name: 'modal-shopizer-search',
	    displayKey: 'value',
	    source: searchElements.ttAdapter(),
	    templates: {
	    	suggestion: function (data) { return searchTemplate.render(data); }
	    }
	});

});

</script>


		<!-- mainmenu-area-start -->
		<div class="mainmenu-area bg-color-1" id="main_h">
			<div class="container">
				<div class="row">
					<div class="col-lg-12 col-md-12 col-sm-12 hidden-xs">
						<div class="mainmenu hidden-xs">
							<nav>
								<ul>
								<c:set var="code" value="${category.code}"/>
								<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
									   <c:if test="${category.visible}">
									   <li class="<sm:activeLink linkCode="${category.description.friendlyUrl}" activeReturnCode="active"/>"><a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>"><c:out value="${category.description.name}"/></a>
										<c:if test="${fn:length(category.children)>0}">
										<ul>
											<c:forEach items="${category.children}" var="child">
											    <c:if test="${child.visible}">
												<li><a href="<c:url value="/shop/category/${child.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${child.id}"/>"><c:out value="${child.description.name}"/></a></li>
												</c:if>
											</c:forEach>
										</ul>
										</c:if>
									   </li>
									   </c:if>
								</c:forEach>
							    <c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
										<c:if test="${content.content.linkToMenu}">
												<li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current">${content.name}</a></li>
										</c:if>
								</c:forEach>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- mobile menu -->
		<div class="mobile-menu-area hidden-sm hidden-md hidden-lg">
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="mobile-menu">
							<nav id="mobile-menu">
								<ul>
								<c:set var="code" value="${category.code}"/>
								<c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
									   <li class="<sm:activeLink linkCode="${category.description.friendlyUrl}" activeReturnCode="active"/>"><a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>"><c:out value="${category.description.name}"/></a>
										<c:if test="${fn:length(category.children)>0}">
										<ul>
											<c:forEach items="${category.children}" var="child">
												<li><a href="<c:url value="/shop/category/${child.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${child.id}"/>"><c:out value="${child.description.name}"/></a></li>		
											</c:forEach>
										</ul>
										</c:if>
									   </li>
								</c:forEach>
								</ul>
							</nav>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- mainmenu-area-end -->
