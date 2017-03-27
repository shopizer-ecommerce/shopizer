<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/23/17
  Time: 7:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>

<header:breadcrumb breadcrumb="Tax Classes"/>
<c:url value="/admin/tax/taxclass/paging.html" var="taxPagingURL"/>
<section class="content">


    <c:choose>

        <c:when test="${response.status eq 0}">
            <div class="box-header with-border">
                <div class="alert alert-success alert-dismissible">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">&times;</button>
                    <h4><i class="icon fa fa-check"></i></h4>
                        ${response.message}
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <c:if test="${response.status eq 1}">
                <div class="box-header with-border">
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-check"></i></h4>
                            ${response.message}
                    </div>
                </div>
            </c:if>
        </c:otherwise>
    </c:choose>


    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="label.tax.taxclass.title"
                                             text="Tax classes"/></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>

        <!-- /.box-header -->
        <div class="box-body">
            <c:url var="TaxClassURL" value="/admin/tax/taxclass/save.html"/>
            <c:if test="${(not empty taxClass) &&  (not empty taxID)}">
                <c:url var="TaxClassURL" value="/admin/tax/taxclass/update.html"/>
            </c:if>

            <form:form method="POST" modelAttribute="taxClass" action="${TaxClassURL}">

            <c:if test="${(not empty taxClass) &&  (not empty taxID)}">

                <div class="row">
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label" for="code"><s:message code="label.tax.taxclass.id"
                                                                               text="ID"
                                                                               var="taxClass"/></label>
                            <input type="text" value="${taxID}" name="tax id" class="form-control">
                        </div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="code"><s:message code="label.tax.taxclass"
                                                                           text="Tax class"
                                                                           var="taxClassMsg"/></label>
                        <form:input cssClass="form-control" id="code" path="code"
                                    placeholder="${taxClassMsg}"/>

                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="title"><s:message
                                code="label.tax.taxclass.name" text="Tax class name"
                                var="taxClassName"/></label>
                        <form:input cssClass="form-control" path="title" id="title"
                                    placeholder="${taxClassName}"/>
                    </div>
                </div>
            </div>

            <form:hidden path="id"/>
            <form:hidden path="merchantStore.id" value="${requestScope.store.id}"/>

            <div class="box-footer">
                <div class="row">
                    <div class="col-sm-2 pull-right">

                        <button type="submit" id="saveTaxClass"
                                class="btn btn-block btn-success pull-right">
                            <c:choose>
                                <c:when test="${(not empty taxClass) &&  (not empty taxID)}">

                                    <s:message code="button.label.update" text="Update"/>
                                </c:when>
                                <c:otherwise>
                                    <s:message code="button.label.submit" text="Submit"/>
                                </c:otherwise>
                            </c:choose>
                        </button>
                    </div>

                </div>
            </div>


            <!-- /.row -->
        </div>
        </form:form>
    </div>

    <!-- end of search section -->

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><s:message code="label.tax.taxclass.title"/></h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="taxClasses" class="table table-bordered">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Class Code</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                        <tfoot>
                        <tr>
                            <th>ID</th>
                            <th>Class Code</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

<script>
    $(window).on('load', function () {
        //var table12 = $('#orders').DataTable();
        // var table ;
        var table = $('#taxClasses').DataTable({
                                                   serverSide: true,
                                                   //searching: false,

                                                   contentType: "application/json",
                                                   dataType: 'json',
                                                   processing: true,
                                                   ajax: '${taxPagingURL}',

                                                   "columns": [
                                                       {"data": "id", "orderable": true},
                                                       {"data": "code"},
                                                       {"data": "title"},
                                                       {
                                                           mRender: function (data, type, row) {
                                                               return '<a class="btn table-bordered" href="<c:url value="/admin/tax/taxclass/edit.html?id="/>'
                                                                      + row.id + '">' + 'Edit'
                                                                      + '</a>' +
                                                                      '<a class="btn table-bordered" href="<c:url value="/admin/tax/taxclass/remove.html?taxClassId="/>'
                                                                      + row.id + '">' + 'Delete'
                                                                      + '</a>';
                                                           }

                                                       }
                                                   ]
                                               });

    });


</script>