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
<script src="<c:url value="/resources/templates/bootstrap3/js/bloodhound.min.js" />"></script>
<script src="<c:url value="/resources/templates/bootstrap3/js/typeahead.bundle.min.js" />"></script>

<script type="text/javascript">
//Search code
$(document).ready(function() { 

    //post search form
	$("#searchButton").click(function(){
			var searchQuery = $('#searchField').val();
			$('#hiddenQuery').val(searchQuery);
			log('Search string : ' + searchQuery);
	        $('#hiddenSearchForm').submit();
   });


	
   var searchElements = new Bloodhound({
		datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
		queryTokenizer: Bloodhound.tokenizers.whitespace,
		<c:if test="${requestScope.CONFIGS['useDefaultSearchConfig'][requestScope.LANGUAGE.code]==true}">
		  <c:if test="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]!=null}">
		prefetch: '<c:out value="${requestScope.CONFIGS['defaultSearchConfigPath'][requestScope.LANGUAGE.code]}"/>',
		  </c:if>
	    </c:if>
		 remote: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.html"/>?q=%QUERY'

	});
   
   searchElements.initialize();


	
	var templ =  Hogan.compile([
								'<p class="suggestion-text"><font color="black">{{value}}</font></p>'
	                       ].join(''));

	$('input.typeahead').typeahead({
	    hint: true,
	    highlight: true,
	    minLength: 1
	}, {
		name: 'shopizer-search',
	    displayKey: 'value',
	    source: searchElements.ttAdapter(),
	    templates: {
	    	suggestion: function (data) { return templ.render(data); }
	    }
	});


});

</script>

<c:set var="req" value="${request}" />
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<form id="hiddenSearchForm" method="POST" action="<c:url value="/shop/search/search.html"/>">
	<input type="hidden" id="hiddenQuery" name="q">
</form>

		<!-- nav -->
		<div id="header" class="container">
				<div class="row">

				<!-- OPTIONS -->
				<ul id="optionsBar">
					<li>
						<!--<form id="searchForm" class="form-inline" method="post" action="<c:url value="/shop/search/search.html"/>">-->
						<input id="searchField" class="typeahead form-control" name="q" type="text" placeholder="<s:message code="label.search.searchQuery" text="Search query" />" autocomplete="off" spellcheck="false" dir="auto" value="<c:out value="${q}"/>">
						<!--</form>-->
					</li>
					<li>
						<button id="searchButton" class="fa fa-search"></button>
					</li>

					<li>
						<!-- Responsive Buttons -->
						<button class="btn btn-mobile" data-toggle="collapse" data-target=".nav-main-collapse">
							<i class="fa fa-bars"></i>
						</button>
					</li>
				</ul>
				<!-- /OPTIONS -->


				<!-- TOP MENU -->
				<div class="navbar-collapse nav-main-collapse collapse pull-left">
					<nav class="nav-main mega-menu">
						<ul class="nav nav-pills nav-main scroll-menu" id="topMain">
							<li class="<sm:activeLink linkCode="HOME" activeReturnCode="active"/>">
								<a class="dropdown-toggle" href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a>
							</li>

	                        <c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
	    					<li class="<sm:activeLink linkCode="${category.description.seUrl}" activeReturnCode="active"/>">
	    						<a class="dropdown-toggle" href="<c:url value="/shop/category/${category.description.seUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
	    							<span class="name">${category.description.name}</span>
	    						</a>
	    					</li> 
							</c:forEach>
							<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
								<li class="<sm:activeLink linkCode="CONTACT" activeReturnCode="active"/>"><a class="dropdown-toggle" href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
							</c:if>
							
													

						</ul>
					</nav>
				</div>
				<!-- /TOP MENU -->

			</div>
		</div>
		<!-- /HEADER -->

		<!-- End Navbar-->