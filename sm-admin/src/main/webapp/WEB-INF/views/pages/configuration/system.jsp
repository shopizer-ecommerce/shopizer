<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/22/17
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>


<header:breadcrumb breadcrumb="System Configuration"/>
<section class="content">

    <c:if test="${success eq 'success'}">
        <div class="box-header with-border" >
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;</button>
                <h4><i class="icon fa fa-check"></i></h4>
                <s:message code="message.success" text="Request successfull"/>
            </div>
        </div>
    </c:if>

    <c:url var="saveSystemConfiguration" value="/admin/configuration/saveSystemConfiguration.html"/>
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="menu.system-configurations" text="System configurations" /></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form:form method="POST" modelAttribute="configuration"
                       action="${saveSystemConfiguration}" id="sm-system-config">


            <div class="row table">

                <div class="col-sm-6">
                    <div class="form-group">
                        <label><s:message code="label.store.testmode" text="Test mode" /></label>
                        <div class="form-group">
                            <label class="small">
                                <form:checkbox path="testMode" cssClass="flat-red"/>
                            </label>

                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.store.debugmode" text="Debug mode" /></label>
                        <div class="form-group">
                            <label>
                                <form:checkbox path="debugMode" cssClass="flat-red"/>
                            </label>
                        </div>
                    </div>
                </div>
            </div>

                <div class="row with-border">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.customer.displaycustomersection" text="Display customer section" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displayCustomerSection" cssClass="flat-red"/>
                                </label>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.store.displaycontactussection" text="Display contact us page" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displayContactUs" cssClass="flat-red"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.store.displaystoreaddress" text="Display store address" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displayStoreAddress" cssClass="flat-red"/>
                                </label>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.order.displayagreement" text="Display customer terms and policy agreement in order page" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displayCustomerAgreement" cssClass="flat-red"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.store.displaysearchbox" text="Display search box" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displaySearchBox" cssClass="flat-red"/>
                                </label>

                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.store.allowPurchaseItems" text="Allow purchase items" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="allowPurchaseItems" cssClass="flat-red"/>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.store.displayaddtocartfeatured" text="Allow add to cart on featured items" /></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="displayAddToCartOnFeaturedItems" cssClass="flat-red"/>
                                </label>

                            </div>
                        </div>
                    </div>

        </div>

                <c:forEach var="language" items="${store.languages}"
                           varStatus="counter">
                <c:if test="${(counter.index) % 2 == 0}">
                <div class="row">
                    </c:if>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.configuration.useglobalsearch_suggestions" text="Use global pre-defined search suggestions" /> (${language.code})</label>
                            <form:checkbox path="useDefaultSearchConfig['${language.code}']" cssClass="flat-red" value="on"/>
                        </div>
                    </div>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label"><s:message code="label.configuration.globalsearch_suggestions_path" text="Global pre-defined search suggestions file path" /> (${language.code})</label>
                                <form:input cssClass="form-control" path="defaultSearchConfigPath['${language.code}']" />
                            </div>
                        </div>
                    <c:choose>
                    <c:when test="${(fn:length(store.languages) le 2 ) && (fn:length(store.languages) eq (counter.count))}">
                </div>
                </c:when>
                <c:otherwise>
                <c:if test="${(counter.index ge 1) && (counter.count) % 2 == 0}">

        </div>
        </c:if>
        </c:otherwise>
        </c:choose>
        </c:forEach>

        </form:form>


        <!-- /.row -->
    </div>

    <div class="box-footer">
        <div class="row">
            <div class="col-sm-2 pull-right">

                <button type="button" id="saveSystemConfigurations"
                        class="btn btn-block btn-success pull-right">
                    <s:message code="button.label.submit" text="Submit"/>
                </button>
            </div>

        </div>
    </div>
    <!-- /.box-body -->

    </div>

</section>

<script>

    $(window).on('load', function () {
        $('#saveSystemConfigurations').click(function (e) {
            e.preventDefault();
            $("#sm-system-config").submit();
        });

        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red').iCheck({
                                                        checkboxClass: 'icheckbox_flat-green',
                                                        radioClass: 'iradio_flat-green'
                                                    });

    });
</script>
