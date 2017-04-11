<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/28/17
  Time: 8:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>
<%@ taglib prefix="order" tagdir="/WEB-INF/tags/order" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<header:breadcrumb breadcrumb="Order Detail"/>

<c:url var="orderSave" value="/admin/orders/save.html"/>

<section class="content">
    <form:form method="POST" enctype="multipart/form-data" commandName="order" action="${orderSave}">
        <div class="box box-default">
            <form:hidden path="order.id" />
            <form:hidden path="order.customerId" />
            <div class="row">
                <div class="col-lg-12">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#order-info"> Order
                                Information</a></li>
                            <li class=""><a data-toggle="tab" href="#delivery">Delivery Address</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#payment">Payment and Status</a>
                            </li>
                            <li class=""><a data-toggle="tab" href="#order-history"> Order
                                History</a></li>
                            <li class=""><a data-toggle="tab" href="#others"> Others</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="order-info" class="tab-pane active">
                                <div class="panel-body">
                                    <order:info/>
                                </div>
                            </div>
                            <div id="delivery" class="tab-pane">
                            <div class="panel-body">
                                <order:shipping/>
                            </div>
                        </div>
                            <div id="payment" class="tab-pane">
                                <div class="panel-body">
                                    <order:payment/>
                                </div>
                            </div>
                            <div id="order-history" class="tab-pane">
                                <div class="panel-body">
                                   <order:history/>
                                </div>
                            </div>
                            <div id="others" class="tab-pane">
                                <div class="panel-body">
                                <order:other/>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                        <div class="row no-print">
                            <div class="col-xs-12">
                                <a href="invoice-print.html" target="_blank"
                                   class="btn btn-default"><i
                                        class="fa fa-print"></i> Print</a>
                                <button type="button"
                                        class="btn btn-success pull-right"><i
                                        class="fa fa-icon-edit"></i> Update Order
                                </button>
                                <button type="button"
                                        class="btn btn-primary pull-right"
                                        style="margin-right: 5px;">
                                    <i class="fa fa-download"></i> Generate PDF
                                </button>
                            </div>
                        </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>







    </form:form>

</section>