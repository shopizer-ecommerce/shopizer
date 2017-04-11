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
                        <label class="control-label"><s:message code="label.country"
                                                                text="Country"/></label>
                        <form:select path="country.isoCode"
                                     cssClass="orm-control tax-country select2"
                                     cssStyle="width: 100%" id="tax-country">
                            <form:options items="${countries}" itemValue="isoCode"
                                          itemLabel="name"/>
                        </form:select>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.storezone"
                                                                text="Store state / province"/></label>
                        <form:select cssClass="form-control select2 zone-list"
                                     cssStyle="width: 100%" path="zone.id"/>
                        <input type="text" class="form-control" id="stateProvince"
                               name="stateProvince"/>
                    </div>
                </div>

            </div>

            <c:forEach items="${taxRate.descriptions}" var="description" varStatus="counter">
            <c:if test="${(counter.index) % 2 == 0}">
            <div class="row">
                </c:if>


                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="menu.taxrates.name" text="Tax name"/> (<c:out value="${description.language.code}"/>)</label>
                        <div class="input-group date">
                            <form:input cssClass="form-control" id="name${counter.index}"
                                        path="descriptions[${counter.index}].name"/>

                        </div>
                    </div>
                </div>
                <c:choose>
                <c:when test="${(fn:length(taxRate.descriptions) le 2 ) && (fn:length(taxRate.descriptions) eq (counter.count))}">
            </div>
            </c:when>
            <c:otherwise>
            <c:if test="${(counter.index ge 1) && (counter.count) % 2 == 0}">

        </div>
        </c:if>
        </c:otherwise>
        </c:choose>
        <form:hidden path="descriptions[${counter.index}].language.id"/>
        <form:hidden path="descriptions[${counter.index}].language.code"/>
        </c:forEach>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label"><s:message code="menu.taxrates.code" text="Code"/></label>
                    <form:input id="code" cssClass="form-control" path="code"/>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label"><s:message code="menu.taxrates.rate" text="Rate"/></label>
                    <form:input id="rateText" cssClass="form-control" path="rateText"/>
                </div>
            </div>

        </div>
        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label"><s:message code="label.tax.compound" text="Compound" /></label>
                    <form:input id="code" cssClass="form-control" path="piggyback"/>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label"><s:message code="label.entity.order" text="Priority"/></label>
                    <form:input id="taxPriority" cssClass="form-control" path="taxPriority" value="0"/>
                </div>
            </div>

        </div>

        <div class="row">
            <div class="col-sm-6">
                <div class="form-group">
                    <label class="control-label"><s:message code="label.tax.taxclass.name" text="Tax class name"/></label>
                    <form:select path="taxClass.id"
                                 cssClass="orm-control tax-country select2"
                                 cssStyle="width: 100%">
                        <form:options items="${taxClasses}" itemValue="id" itemLabel="code"/>
                    </form:select>
                </div>
            </div>

        </div>

        <form:hidden path="merchantStore.id" value="${requestScope.store.id}" />
    <div class="row">
        <div class="col-sm-4">
            <button type="button" id="orderSearch"
                    class="btn btn-block btn-primary btn-sm right">
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
