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
	    remote: {
    		url: '<c:url value="/services/public/search/${requestScope.MERCHANT_STORE.code}/${requestScope.LANGUAGE.code}/autocomplete.json"/>?q=%QUERY',
        	filter: function (parsedResponse) {
            	// parsedResponse is the array returned from your backend
            	console.log(parsedResponse);

            	// do whatever processing you need here
            	return JSON.parse(parsedResponse);
        	}
    	}
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


<section id="navigation" class="navigation">
			<div class="nomargin-right row">
				<div class="col-md-12 col-sm-12 no-padding-right">
						<!-- SEARCH BAR -->
						<ul id="optionsBar" class="">
							<li>
								<input id="searchField" class="typeahead form-control" name="q" type="text" placeholder="<s:message code="label.search.searchQuery" text="Search query" />" autocomplete="off" spellcheck="false" dir="auto" value="<c:out value="${q}"/>">
							</li>
							<li>
								<button id="searchButton" class="fa fa-search"></button>
							</li>
						</ul>
						<!-- /SEARCH BAR -->
						<ul id="smallNavigation" class="pull-left">
							<li>
								<button data-target="#mainNavigation" data-toggle="collapse" class="navbar-toggle" type="button">
				                            <i class="fa fa-bars"></i>
				                </button>
							</li>
						</ul>
	
						<!-- NAVIGATION MENU -->
						<div id="mainNavigation" class="collapse navbar-collapse pull-left">
								<ul class="nav nav-pills nav-main scroll-menu navbar-nav" id="topMain">
									
									<li class="<sm:activeLink linkCode="HOME" activeReturnCode="active"/>">
										<a class="dropdown-toggle" href="<c:url value="/shop"/>"><s:message code="menu.home" text="Home"/></a>
									</li>
		
			                        <c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
			    					<li class="<sm:activeLink linkCode="${category.description.friendlyUrl}" activeReturnCode="active"/>">
			    						<a class="dropdown-toggle" href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>" class="current"> 
			    							<span class="name">${category.description.name}</span>
			    						</a>
			    					</li> 
									</c:forEach>
									<c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
										<li class="<sm:activeLink linkCode="CONTACT" activeReturnCode="active"/>"><a class="dropdown-toggle" href="<c:url value="/shop/store/contactus.html"/>"><s:message code="label.customer.contactus" text="Contact us"/></a></li>
									</c:if>

							   </ul>
					  </div>
					<!-- /NAVIGATION MENU -->
				</div>
			</div>

			<form id="hiddenSearchForm" method="post" action="<c:url value="/shop/search/search.html"/>">
				<input type="hidden" id="hiddenQuery" name="q">
			</form>

</section>