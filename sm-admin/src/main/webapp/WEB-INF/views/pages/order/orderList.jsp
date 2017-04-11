<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/9/17
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>


<header:breadcrumb breadcrumb="Order List"/>


<section class="content">

    <!-- search  section -->

    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title">Search</h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="order_id">Order ID</label>
                        <input id="order_id" name="order_id" value="" placeholder="Order ID"
                               class="form-control" type="text">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="status">Order status</label>
                        <input id="status" name="status" value="" placeholder="Status"
                               class="form-control" type="text">
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="customer">Customer</label>
                        <input id="customer" name="customer" value="" placeholder="Customer"
                               class="form-control" type="text">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="date_added">Date added</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            <input id="date_added" class="form-control" value="03/04/2014"
                                   type="datepicker">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="date_modified">Date modified</label>
                        <div class="input-group date">
                            <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                            <input id="date_modified" class="form-control" value="03/06/2014"
                                   type="datepicker">
                        </div>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label" for="amount">Amount</label>
                        <input id="amount" name="amount" value="" placeholder="Amount"
                               class="form-control" type="text">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-4">
                    <button type="button" id="orderSearch" class="btn btn-block btn-primary btn-sm">
                        Search
                    </button>
                </div>
            </div>
            <!-- /.row -->
        </div>

    </div>

    <!-- end of search section -->

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><s:message code="label.order.title"/></h3>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <table id="orders" class="table table-bordered table-hover">
                        <thead>
                        <tr>
                            <th>Order Number</th>
                            <th>Customer ID</th>
                            <th>Order Total</th>
                            <th>Status</th>
                            <th>Date Placed</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>

                        </tbody>
                        <tfoot>
                        <tr>
                            <th>Order Number</th>
                            <th>Customer ID</th>
                            <th>Order Total</th>
                            <th>Status</th>
                            <th>Date Placed</th>
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
<c:url value="/admin/orders.html" var="ordersURL"/>
    <!-- /.row -->
</section>

<script>
    $(window).on('load', function () {
        //var table12 = $('#orders').DataTable();
        var table ;
        $('#orders').DataTable({
                                               serverSide: true,
                                               //searching: false,
                                               contentType: "application/json",
                                               dataType: 'json',
                                               processing: true,
                                               ajax: '${ordersURL}',

                                               "aoColumnDefs": [
                                                   {
                                                       "aTargets": [0], // Column to target
                                                       "mRender": function (data, type, full) {

                                                           return '<a href="<c:url value="/admin/order/"/>' + data
                                                                  + '">' + data + '</a>';
                                                       }
                                                   }
                                               ],

                                               "columns": [
                                                   {"data": "orderNumber", "orderable": true},
                                                   {"data": "customerId"},
                                                   {"data": "orderTotal"},
                                                   {"data": "status", "orderable": true},
                                                   {"data": "date", "orderable": true},
                                                   {

                                                       mRender: function (data, type, row) {
                                                           return '<a class="btn table-bordered" href="<c:url value="/admin/order/editOrder.html?id="/>'
                                                                  + row.orderNumber + '">' + 'Edit'
                                                                  + '</a>' +
                                                                  '<a class="btn table-bordered" href="<c:url value="/admin/order/deleteOrder.html?id="/>'
                                                                  + row.orderNumber + '">' + 'Delete'
                                                                  + '</a>';
                                                       }

                                                   }

                                               ]
                                           });

        $('#date_added').datepicker({
                                        autoclose: true
                                    });

        $('#date_modified').datepicker({
                                           autoclose: true
                                       });

        $('#orderSearch').on('click', function () {

            table.columns(0).search($("#order_id").val()).column(1).search($("#status").val())
                .draw();
            //table.search( $(this).val() ).search("umesh").draw();
        });

    });


</script>