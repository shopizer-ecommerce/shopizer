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
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="/WEB-INF/shopizer-tags.tld" prefix="sm" %>
<%@ taglib uri="/WEB-INF/shopizer-functions.tld" prefix="display" %> 
 
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<script src="<c:url value="/resources/js/hogan.js" />"></script>
<script src="<c:url value="/resources/templates/december/js/bloodhound.min.js" />"></script>
<script src="<c:url value="/resources/templates/december/js/typeahead.bundle.min.js" />"></script>


<!-- Customer account menu logged in customer will display in customerAccount placeholder -->
<script type="text/html" id="customerLoggedInAccountTemplate">
          		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"> <i class="fa fa-user mr-5"></i><span class="hidden-xs"><s:message code="label.generic.welcome" text="Welcome" />&nbsp;<span>{{firstName}}</span><i class="fa fa-angle-down ml-5"></i></span> </a>
		          <ul class="dropdown-menu w-150" role="menu">
		            <li><a href="#" onClick="javascript:location.href='<c:url value="/shop/customer/dashboard.html" />';" href="#"><s:message code="label.customer.myaccount" text="My account"/></a></a>
		            </li>
		            <li><a href="#" onClick="javascript:location.href='<c:url value="/shop/customer/logout" />';" href="#"><s:message code="button.label.logout" text="Logout"/></a>
		            </li>
		         </ul>
			 <script type="text/javascript">
                (function($) {
                    $('[data-hover="dropdown"]').dropdownHover()
                })(jQuery);
			</script>
</script>

<!-- Customer account menu not logged in customer will display in customerAccount placeholder -->
<script type="text/html" id="customerNotLoggedInAccountTemplate">
          		  <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"> <i class="fa fa-user mr-5"></i><span class="hidden-xs"><s:message code="label.customer.myaccount" text="My Account"/><i class="fa fa-angle-down ml-5"></i></span> </a>
		          <ul class="dropdown-menu w-150" role="menu">
						<li><a href="#" id="registerLink" onClick="javascript:location.href='<c:url value="/shop/customer/registration.html" />';"><s:message code="label.generic.register" text="Register" /></a></li>
						<li><a href="#" id="registerLink" onClick="javascript:location.href='<c:url value="/shop/customer/customLogon.html" />';"><s:message code="button.label.signin" text="Signin" /></a></li>
		         </ul>
			 <script type="text/javascript">
                (function($) {
                    $('[data-hover="dropdown"]').dropdownHover()
                })(jQuery);
			</script>
</script>

<!-- Mini shopping cart JS template -->
<script type="text/html" id="miniCartTemplate">

                          {{#code}}
            			  <li>
              				<div class="cart-items">
                				<ol class="items">
                          {{#shoppingCartItems}}
   
                 			 			<li>
                    					<div class="cart-img">
										{{#image}}
										<a href="#" class="product-image">
											<img class="img-responsive" src="<c:out value="${pageContext.servletContext.contextPath}" />{{image}}">
										</a>
										{{/image}}
										{{^image}}
											&nbsp
										{{/image}}
										</div>
                    					<div class="product-details">
                      					<div class="close-icon"> <button productid="{{productId}}" class="close removeProductIcon" onclick="removeItemFromMinicart('{{id}}')"><i class="fa fa-times-circle"></i></a></div>
                      					<p class="product-name"> <a href="#">{{name}}</a> </p> <strong>{{quantity}}</strong> x <span class="price text-primary">{{price}}</span> </div>
                    					<!-- end product-details -->
                  						</li>
                  						<!-- end item -->

						  				{{/shoppingCartItems}}
               					 </ol>
              				 </div>
            			  </li>
            			  <li>
              				<div class="cart-footer"> 
								<a href="#" class="pull-left"><s:message code="label.order.total" text="Total" />&nbsp;{{total}}</a>
								<a href="#" onclick="viewShoppingCartPage();" class="pull-right"><i class="fa fa-shopping-basket mr-5"></i><s:message code="label.checkout" text="Checkout"/></a> 
							</div>
            			  </li>
                          {{/code}}
						  {{^code}}
						  	<li>
                          		<div class="cart-items">
						  		<ol class="items">

							<h5 style="text-align: center;color:#666666;margin-top:10px;margin-bottom:10px;"><s:message code="label.emptycart" text="Your Shopping cart is empty" /></h5>
						    
						  		</ol>
						  		</div>
						  	</li>
							{{/code}}

</script>

<!-- mini cart label button template -->
<script type="text/html" id="miniCartSummaryTemplate">
		<!-- empty cart and full summary subTotal & quantity -->
		{{^code}}
			0
		{{/code}}
		{{#code}}
           {{quantity}}
		{{/code}}
</script>

<!-- dropdown js and hover -->
<script type="text/javascript">
   ! function($, n, e) {
      var o = $();
      $.fn.dropdownHover = function(e) {
        return "ontouchstart" in document ? this : (o = o.add(this.parent()), this.each(function() {
          function t(e) {
            o.find(":focus").blur(), h.instantlyCloseOthers === !0 && o.removeClass("open"), n.clearTimeout(c), i.addClass("open"), r.trigger(a)
          }
          var r = $(this),
            i = r.parent(),
            d = {
              delay: 100,
              instantlyCloseOthers: !0
            },
            s = {
              delay: $(this).data("delay"),
              instantlyCloseOthers: $(this).data("close-others")
            },
            a = "show.bs.dropdown",
            u = "hide.bs.dropdown",
            h = $.extend(!0, {}, d, e, s),
            c;
          i.hover(function(n) {
            return i.hasClass("open") || r.is(n.target) ? void t(n) : !0
          }, function() {
            c = n.setTimeout(function() {
              i.removeClass("open"), r.trigger(u)
            }, h.delay)
          }), r.hover(function(n) {
            return i.hasClass("open") || i.is(n.target) ? void t(n) : !0
          }), i.find(".dropdown-submenu").each(function() {
            var e = $(this),
              o;
            e.hover(function() {
              n.clearTimeout(o), e.children(".dropdown-menu").show(), e.siblings().children(".dropdown-menu").hide()
            }, function() {
              var t = e.children(".dropdown-menu");
              o = n.setTimeout(function() {
                t.hide()
              }, h.delay)
            })
          })
        }))
      }, $(document).ready(function() {
        $('[data-hover="dropdown"]').dropdownHover()
      })
    }(jQuery, this);
</script>
 
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


  <!--=========-TOP_BAR============-->
  <nav class="topBar">
    <div class="container">
      <c:if test="${requestScope.CONFIGS['displayContactUs']==true}">
      <ul class="list-inline pull-left hidden-sm hidden-xs">
        <li><span class="text-primary"><s:message code="label.store.question" text="Have a question?" /></span>&nbsp;<abbr title="Phone"><s:message code="label.generic.phone" text="Phone" /></abbr>: <span itemprop="telephone"><c:out value="${requestScope.MERCHANT_STORE.storephone}"/></span></li>
        <c:if test="${requestScope.CONFIGS['testMode']==true}"><li><h4>[TEST MODE]</h4></li></c:if>
      </ul>
      </c:if>
      <ul class="topBarNav pull-right">

		<c:if test="${fn:length(requestScope.MERCHANT_STORE.languages) > 1}">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"> <img src="" class="mr-5" alt=""> <span class="hidden-xs"> <s:message code="lang.${requestScope.LANGUAGE.code}" text="${requestScope.LANGUAGE.code}" /> <i class="fa fa-angle-down ml-5"></i></span> </a>
            <ul class="dropdown-menu w-100" role="menu">
            <c:forEach items="${requestScope.MERCHANT_STORE.languages}" var="language">
	            <c:if test="${requestScope.LANGUAGE.code ne language.code}">
					<a href="<c:url value="/shop?locale=${language.code}"/>"><s:message code="lang.${language.code}" text="${language.code}" /></a>
				</c:if>
			</c:forEach>
          </ul>
        </li>
        </c:if>
        <c:if test="${requestScope.CONFIGS['displayCustomerSection'] == true}">
        	<li class="dropdown" id="customerAccount"></li>
        </c:if>
        <c:if test="${requestScope.CONFIGS['allowPurchaseItems'] == true}">	
        	<li class="dropdown">
        		<a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"> <i class="fa fa-shopping-basket mr-5"></i> <span class="hidden-xs">
                                <s:message code="label.cart" text="Shopping cart"/><sup class="text-primary">(<span id="miniCartSummary"></span>)</sup>
                                <i class="fa fa-angle-down ml-5"></i>
                            	</span>
                </a>
                <ul class="dropdown-menu cart w-250" role="menu" id="miniCartTemplate">
                	<span id="miniCartDetails"></span>
                </ul>
        	</li>
        </c:if>
      </ul>
    </div>
  </nav><!--=========-TOP_BAR============-->
    
 <!--=========MIDDLE-TOP_BAR============-->
    
    <div class="middleBar">
    <div class="container">
  <div class="row display-table">
    <div class="col-sm-5 vertical-align text-left">
      <a href="javascript:void(0);"> 
      			    <!-- logo -->
      				<c:choose>
									<c:when test="${requestScope.CONTENT['logo']!=null}">
										<!-- A content logo exist -->
										<sm:pageContent contentCode="logo"/>
									</c:when>
									<c:otherwise>
										<c:choose>
						                		<c:when test="${not empty requestScope.MERCHANT_STORE.storeLogo}">
						                			<!--  use merchant store logo -->
						                			<a class="grey store-name" href="<c:url value="/shop/"/>">
						                			<img class="logoImage" style="max-width:140px;" src="<sm:storeLogo/>"/>
						                			</a>
						                		</c:when>
						                		<c:otherwise>
						                			<!-- Use store name -->
						                			<h1>
						                			<a class="grey store-name" href="<c:url value="/shop/"/>">
						                				<c:out value="${requestScope.MERCHANT_STORE.storename}"/>
						                			</a>  
						                			</h1>
						                		</c:otherwise>
						                </c:choose>
									</c:otherwise>
					</c:choose>
      </a>
    </div>
    <!-- end col -->
    <div class="col-sm-7 vertical-align text-center">

	  <c:if test="${requestScope.CONFIGS['displaySearchBox'] == true}">
      <form>
        <div class="row grid-space-1">
          <div class="col-sm-9">
            <input type="text" name="q" id="searchField" class="form-control input-lg typeahead" placeholder="<s:message code="label.generic.search" text="Search"/>" value="">
         </div>
          <!-- end col -->
          <div class="col-sm-3">
            <input type="submit" class="btn btn-default btn-block btn-lg searchButton" value="<s:message code="label.generic.search" text="Search"/>">
         </div>
          <!-- end col -->
        </div>
        <!-- end row -->
      </form>
      <form id="hiddenSearchForm" method="post" action="<c:url value="/shop/search/search.html"/>">
			<input type="hidden" id="hiddenQuery" name="q">
	  </form>
      </c:if>
    </div>
    <!-- end col -->

    <!-- end col -->
  </div>
  <!-- end  row -->
</div>
</div>
    
    
	<nav class="navbar navbar-main navbar-default" role="navigation" style="opacity: 1;">
          <div class="container">
            <!-- Brand and toggle -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>             
            </div>
        
            <!-- Collect the nav links,  -->
            <div class="collapse navbar-collapse navbar-1" style="margin-top: 5px;">            
              <ul class="nav navbar-nav">
                <li><a href="<c:url value="/shop/"/>" class="dropdown-toggle"><s:message code="menu.home" text="Home"/></a></li>
                <!-- languages -->
                <c:if test="${fn:length(requestScope.MERCHANT_STORE.languages) > 1}">
			    <li class="dropdown" class="langMenu">
			            <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"><s:message code="llabel.generic.language" text="Language"/> <i class="fa fa-angle-down ml-5"></i></a>
			            <ul class="dropdown-menu dropdown-menu-left">
			            <c:forEach items="${requestScope.MERCHANT_STORE.languages}" var="language">
				            <c:if test="${requestScope.LANGUAGE.code ne language.code}">
								<li><a href="<c:url value="/shop?locale=${language.code}"/>"><s:message code="lang.${language.code}" text="${language.code}" /></a></li>
							</c:if>
						</c:forEach>
			           </ul>
			    </li>
		        </c:if>
                <!-- Categories -->
                <!-- mega menu style -->
                <!--<li class="dropdown megaDropMenu">-->
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"><s:message code="menu.catalogue-products" text="Products"/> <i class="fa fa-angle-down ml-5"></i></a>
                  <ul class="dropdown-menu row">
                    <c:set var="code" value="${category.code}"/>
                    <c:forEach items="${requestScope.TOP_CATEGORIES}" var="category">
                    <c:if test="${category.visible}">
                    <!-- mega menu style -->
                    <!--<li class="col-sm-2 col-xs-12" style="margin-bottom:40px;">-->
                    <li>
                      <!-- mega menu style -->
                      <!--<ul class="list-unstyled">-->
                        <li class="<sm:activeLink linkCode="${category.description.friendlyUrl}" activeReturnCode="active"/>"><a href="<c:url value="/shop/category/${category.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${category.id}"/>"><c:out value="${category.description.name}"/></a>
                        <c:if test="${fn:length(category.children)>0}">
								<c:forEach items="${category.children}" var="child">
									<c:if test="${child.visible}">
									<li style="margin-bottom:-10px;"><a href="<c:url value="/shop/category/${child.description.friendlyUrl}.html"/><sm:breadcrumbParam categoryId="${child.id}"/>"><c:out value="${child.description.name}"/></a></li>
									</c:if>		
								</c:forEach>
						</c:if>
						</li>
					  <!-- mega menu style -->
                      <!--</ul>-->
                    </li>
                    </c:if>
                    </c:forEach>

                    
                  </ul>
                </li>
                  
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="false"><s:message code="label.page" text="Page"/> <i class="fa fa-angle-down ml-5"></i></a>
                  <ul class="dropdown-menu dropdown-menu-left">
                    <c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
	                    <c:if test="${not content.content.linkToMenu}">
	                    	<li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current">${content.name}</a></li>
	                    </c:if>
                    </c:forEach>
                  </ul>
                </li>
                <c:forEach items="${requestScope.CONTENT_PAGE}" var="content">
                	<c:if test="${content.content.linkToMenu}">
               			<li><a href="<c:url value="/shop/pages/${content.seUrl}.html"/>" class="current">${content.name}</a></li>
                	</c:if>
                </c:forEach>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div>
        </nav>

