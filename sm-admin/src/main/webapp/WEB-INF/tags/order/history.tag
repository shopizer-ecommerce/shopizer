<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="box box-default">
    <div class="box-header with-border">
        <h3 class="box-title "><s:message code="label.order.history" text="History"/></h3>
    </div>
    <div class="row">
        <div class="col-md-12">
        <div class="box box-solid">

            <!-- /.box-header -->
            <div class="box-body">


                <c:forEach items="${order.order.orderHistory}" var="orderHistory" varStatus="counter">
                    <c:if test="${orderHistory.comments!=null}">
                    <dl>
                        <dt><i class="fa fa-clock-o"></i> <fmt:formatDate type="both" dateStyle="long" value="${orderHistory.dateAdded}" /></dt>
                        <dd> ${orderHistory.comments}</dd>

                    </dl>
                    </c:if>
                </c:forEach>
            </div>

            </div>
            <!-- /.box-body -->
        </div>
    </div>
    </div>




