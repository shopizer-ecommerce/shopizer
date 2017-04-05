<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/26/17
  Time: 8:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>

<header:breadcrumb breadcrumb="Tax Rates"/>

<section class="content">
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="menu.taxrates" text="Tax rates"/></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>
        <div class="box-body">
            <form:form method="POST" modelAttribute="taxRate" action="${saveTaxRate}">
                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.country" text="Country"/></label>
                            <form:select path="country.isoCode" cssClass="orm-control tax-country select2" cssStyle="width: 100%" id="tax-country">
                                <form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.storezone" text="Store state / province"/></label>
                            <form:select cssClass="form-control select2" cssStyle="width: 100%" path="zone.id"/>
                            <input type="text" class="form-control" id="stateProvince" name="stateProvince" />
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label" for="date_added">Date added</label>
                            <div class="input-group date">
                                <span class="input-group-addon"><i
                                        class="fa fa-calendar"></i></span>
                                <input id="date_added" class="form-control" value="03/04/2014"
                                       type="datepicker">
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label" for="date_modified">Date modified</label>
                            <div class="input-group date">
                                <span class="input-group-addon"><i
                                        class="fa fa-calendar"></i></span>
                                <input id="date_modified" class="form-control" value="03/06/2014"
                                       type="datepicker">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <button type="button" id="orderSearch"
                                class="btn btn-block btn-primary btn-sm">
                            Search
                        </button>
                    </div>
                </div>
                <!-- /.row -->
            </form:form>
        </div>
    </div>
</section>
<script>
    $(window).on('load', function () {
        $(".select2").select2();
    });

</script>
