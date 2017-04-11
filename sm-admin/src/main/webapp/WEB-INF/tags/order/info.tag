<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>




<div class="box-body">
    <div class="box-header with-border header-grey-bkg">
        <h3 class="box-title">Order Information</h3>
    </div>
    <div class="row">

        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label">Customer</label>
                <div class="input-group date">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-pencil"></i></span>
                    <a class="form-control a-sm-link"
                       href="<c:url value="/admin/customers/customer.html?id=${customer.id}"/>">${customer.nick}</a>

                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label" for="order_id">Order
                    ID</label>
                <form:input path="order.id" name="order_id"
                            id="order_id" placeholder="Order ID"
                            cssClass="form-control"/>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label" for="order-status">Order
                    status</label>
                <form:input path="order.status.value"
                            cssClass="form-control"
                            placeholder="Status"
                            id="order-status"/>
            </div>
        </div>

    </div>

    <div class="row">
        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label"><s:message
                        code="label.country"
                        text="Country"/></label>

                <select name="countries" id="countries"
                        class="form-control order-country"
                        style="width: 100%;" id="order-country">
                    <c:forEach var="country"
                               items="${countries}">
                        <c:choose>
                            <c:when test="${store.country.isoCode eq country.isoCode}">
                                <option value="${country.isoCode}"
                                        selected="selected"
                                        class="form-control">${country.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${country.isoCode}"
                                        class="form-control">${country.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label"
                       for="datePurchased">Order Date</label>
                <div class="input-group date">
                                                        <span class="input-group-addon"><i
                                                                class="fa fa-calendar"></i></span>
                    <form:input path="datePurchased"
                                cssClass="form-control"
                                id="order-date"
                                data-date-format="yyyy-MM-dd"/>
                </div>
            </div>
        </div>
        <div class="col-sm-4">
            <div class="form-group">
                <label class="control-label"
                       for="order-amount">Amount</label>
                <input id="order-amount" name="amount" value=""
                       placeholder="Amount"
                       class="form-control" type="text">
            </div>
        </div>
    </div>
    <div class="box box-default">

        <div class="box-header with-border">
            <h3 class="box-title ">Common</h3>
        </div>
        <div class="box-body">
            <div class="row">

                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label"
                               for="store_name">Store
                            Name</label>
                        <input type="text" name="store_name"
                               id="store_name"
                               placeholder="Store Name"
                               class="form-control"
                               value="${store.storename}"/>
                    </div>
                </div>
                <div class="col-sm-4">
                    <div class="form-group">
                        <label class="control-label"
                               for="order_id">Channel</label>
                        <form:input path="order.channel"
                                    name="channel" id="channel"
                                    placeholder="Channel"
                                    cssClass="form-control"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="box-header with-border header-grey-bkg">
        <h3 class="box-title ">Product Information</h3>
    </div>
    <div class="row">
        <div class="col-xs-12 table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Product</th>
                    <th>Qty</th>
                    <th>Product Code</th>
                    <th>Base Price</th>
                    <th>Subtotal</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${order.order.orderProducts}" var="orderProduct" varStatus="counter">
                    <tr>
                        <c:set var="total" value="${orderProduct.oneTimeCharge * orderProduct.productQuantity }" />
                        <td><a href="<c:url value="/admin/products/viewEditProduct.html?sku=${orderProduct.sku}"/>">${orderProduct.productName}</a></td>
                        <td>${orderProduct.productQuantity}</td>
                        <td>${orderProduct.sku}</td>
                        <td>${orderProduct.oneTimeCharge}</td>
                        <td>${total}</td>

                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- /.col -->
    </div>
    <!-- /.row -->
    <div class="box-header with-border header-grey-bkg">
        <h3 class="box-title">Order Total</h3>
    </div>
    <div class="row">
        <!-- accepted payments column -->

        <!-- /.col -->
        <div class="col-xs-4 pull-right">


            <div class="table-responsive">
                <table class="table">
                    <c:forEach items="${order.order.orderTotal}" var="orderTotal" varStatus="counter">
                        <tr>
                            <th><s:message code="${orderTotal.orderTotalCode}" text="${orderTotal.orderTotalCode}"/>:</th>
                            <td>${orderTotal.value}<%--<sm:monetary value="${orderTotal.value}" currency="${order.order.currency}"/>--%></td>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
        <!-- /.col -->
    </div>


</div>