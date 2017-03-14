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
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header"%>


<header:breadcrumb breadcrumb="Order List"/>

<!-- Main content -->
<section class="content">
    <div class="row">
        <div class="col-xs-12">

            <div class="box">
                <div class="box-header">
                    <h3 class="box-title"><s:message code="label.order.title"/></h3>
                </div>
                <!-- /.box-header -->
                <c:choose>
                    <c:when test="${not empty orderList.orders}">

                        <div class="box-body">
                            <table id="orders" class="table table-bordered table-striped">
                                <thead>
                                <tr>
                                    <th>Order Number</th>
                                    <th>Customer ID</th>
                                    <th>Order Total</th>
                                    <th>Status</th>
                                    <th>Date Placed</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${orderList.orders}" var="order">
                                    <tr>
                                        <td><a href="#">${order.id}</a></td>
                                        <td><a href="#">${order.customerId}</a>
                                        </td>
                                        <td>${order.total}</td>
                                        <td>${order.status}</td>
                                        <td>${order.datePurchased}</td>
                                    </tr>

                                </c:forEach>

                                </tbody>
                                <tfoot>
                                <tr>
                                    <th>Order Number</th>
                                    <th>Customer ID</th>
                                    <th>Order Total</th>
                                    <th>Status</th>
                                    <th>Date Placed</th>
                                </tr>
                                </tfoot>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </c:when>

                <c:otherwise>
                <div class="box-body">
                    <table  class="table table-bordered table-striped">
                        <tr>
                            <td>No Orders</td>
                        </tr>
                    </table>
                </div>
               </c:otherwise>

                </c:choose>


            </div>
            <!-- /.box -->
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
</section>

<script>
    $(window).on('load', function() {
        $("#orders").DataTable();
// your standard jquery code goes here with $ prefix
// best used inside a page with inline code,
// or outside the document ready, enter code here
    });

</script>