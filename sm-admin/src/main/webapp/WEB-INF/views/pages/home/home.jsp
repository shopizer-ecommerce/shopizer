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

<section class="content">
    <!-- Info boxes -->
    <div class="row">
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-aqua"><i class="ion ion-ios-gear-outline"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">CPU Traffic</span>
                    <span class="info-box-number">${systemInfo.systemLoad}<small>%</small></span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-red"><i class="ion ion-ios-gear-outline"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Used Memory</span>
                    <span class="info-box-number">${((systemInfo.freeMemory)-(systemInfo.freeMemory)) / (1024*1024)}</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->

        <!-- fix for small devices only -->
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-green"><i class="ion ion-ios-gear-outline"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Threads Count</span>
                    <span class="info-box-number">${systemInfo.numberOfThread}</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
        <div class="col-md-3 col-sm-6 col-xs-12">
            <div class="info-box">
                <span class="info-box-icon bg-yellow"><i class="ion ion-ios-gear-outline"></i></span>

                <div class="info-box-content">
                    <span class="info-box-text">Total Memory</span>
                    <span class="info-box-number">${(systemInfo.freeMemory) / (1024*1024)}</span>
                </div>
                <!-- /.info-box-content -->
            </div>
            <!-- /.info-box -->
        </div>
        <!-- /.col -->
    </div>

    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><s:message code="label.store.information" text="Store information" /></h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body table-responsive no-padding">
                    <table class="table table-hover">
                        <tr>
                            <th>Store Name </th>
                            <td><c:out value="${store.storename}"/></td>

                        </tr>
                        <c:if test="${not empty store.storeaddress}">
                            <tr>
                                <th>Store Address</th>
                                <td><c:out value="${store.storeaddress}"/></td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty store.storecity}">
                            <tr>
                                <th>City</th>
                                <td><c:out value="${store.storecity}"/></td>
                            </tr>
                        </c:if>
                        <c:choose>

                            <c:when test="${not empty store.zone}">
                                <tr>
                                    <th>Zone</th>
                                    <td><c:out value="${store.zone.code}"/></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${not empty store.storestateprovince}">
                                    <tr>
                                        <th>Zone</th>
                                        <td> <c:out value="${store.storestateprovince}"/></td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                        </tr>
                        <c:if test="${not empty store.storepostalcode}">
                            <tr>
                                <th>Postal Code</th>
                                <td><c:out value="${store.storepostalcode}"/></td>
                            </tr>
                        </c:if>
                        <tr>
                            <th>Country</th>
                            <td><c:out value="${country.name}"/></td>
                        </tr>
                        <c:if test="${not empty store.storephone}">
                            <tr>
                                <th>Phone Number</th>
                                <td><c:out value="${store.storephone}"/></td>
                            </tr>
                        </c:if>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->
        </div>
    </div>

    <sec:authorize access="hasAnyRole('ADMIN', 'SUPERADMIN', 'ADMIN_ORDER')">
      <c:if test="${not empty orderList}">
    <!-- Main row -->
    <div class="row">
        <!-- Left col -->
        <div class="col-md-12">
            <!-- MAP & BOX PANE -->
            <!-- TABLE: LATEST ORDERS -->
            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">Latest Orders</h3>

                    <div class="box-tools pull-right">
                        <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i>
                        </button>
                        <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-times"></i></button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body">
                    <div class="table-responsive">
                        <table class="table no-margin">
                            <thead>
                            <tr>
                                <th>Order ID</th>
                                <th>Date</th>
                                <th>Status</th>
                                <th>Total Price</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${orderList.orders}" var="order">
                            <tr>
                                <td><a href="#">${order.id}</a></td>
                                <td>${order.datePurchased}</td>
                                <td><span class="label label-success">${order.status}</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#00a65a" data-height="20">${order.total}</div>
                                </td>
                            </tr>
                            </c:forEach>
                            <%--<tr>
                                <td><a href="pages/examples/invoice.html">OR1848</a></td>
                                <td>Samsung Smart TV</td>
                                <td><span class="label label-warning">Pending</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="pages/examples/invoice.html">OR7429</a></td>
                                <td>iPhone 6 Plus</td>
                                <td><span class="label label-danger">Delivered</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="pages/examples/invoice.html">OR7429</a></td>
                                <td>Samsung Smart TV</td>
                                <td><span class="label label-info">Processing</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#00c0ef" data-height="20">90,80,-90,70,-61,83,63</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="pages/examples/invoice.html">OR1848</a></td>
                                <td>Samsung Smart TV</td>
                                <td><span class="label label-warning">Pending</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#f39c12" data-height="20">90,80,-90,70,61,-83,68</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="pages/examples/invoice.html">OR7429</a></td>
                                <td>iPhone 6 Plus</td>
                                <td><span class="label label-danger">Delivered</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div>
                                </td>
                            </tr>
                            <tr>
                                <td><a href="pages/examples/invoice.html">OR9842</a></td>
                                <td>Call of Duty IV</td>
                                <td><span class="label label-success">Shipped</span></td>
                                <td>
                                    <div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div>
                                </td>
                            </tr>--%>
                            </tbody>
                        </table>
                    </div>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.box-body -->
                <div class="box-footer clearfix">
                    <a href="javascript:void(0)" class="btn btn-sm btn-info btn-flat pull-left">Place New Order</a>
                    <a href="javascript:void(0)" class="btn btn-sm btn-default btn-flat pull-right">View All Orders</a>
                </div>
                <!-- /.box-footer -->
            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->


        <!-- /.col -->
    </div>
    <!-- /.row -->
      </c:if>
    </sec:authorize>
</section>