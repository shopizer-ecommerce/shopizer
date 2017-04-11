<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>





        <div class="box box-default">

            <div class="box-header with-border">
                <h3 class="box-title "> <s:message code="label.customer.billinginformation" text="Billing information"/></h3>
            </div>
            <div class="box-body">
                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="billing-firstName"><s:message code="label.customer.billing.firstname" text="First Name"/></label>
                            <form:input path="order.billing.firstName"
                                        name="billing-firstName" id="billing-firstName"
                                        placeholder="First Name"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="billing-lastName"><s:message code="label.customer.billing.lastname" text="Last Name"/></label>
                            <form:input path="order.billing.lastName"
                                        name="billing-lastName" id="billing-lastName"
                                        placeholder="Last Name"
                                        cssClass="form-control"/>
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="billing-streetAddress"><s:message code="label.customer.billing.streetaddress" text="Street Address"/></label>
                            <form:input path="order.billing.address"
                                        name="billing-streetAddress" id="billing-streetAddress"
                                        placeholder="Street Address"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="billingCity"><s:message code="label.customer.billing.city" text="City"/></label>
                            <form:input path="order.billing.city"
                                        name="billingCity" id="billingCity"
                                        placeholder="City"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"><s:message
                                    code="label.customer.billing.zone"
                                    text="Zone"/></label>

                            <form:select name="billingZone" id="billingZone"
                                         cssClass="form-control order-country"
                                         cssStyle="width: 100%;" path="order.billing.zone.code">
                                <form:options/>
                            </form:select>
                            <form:input  class="form-control" id="billingZoneText" maxlength="100"  name="billingZoneText" path="order.billing.state" />
                        </div>
                    </div>

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.customer.billing.country" text="Country"/></label>

                            <form:select name="billingCountry" id="billing-country"
                                         cssClass="form-control billing-country"
                                         cssStyle="width: 100%;" path="order.billing.country.isoCode">
                                <form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
                            </form:select>
                        </div>
                    </div>
                </div>

                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="billing-postalCode"><s:message code="label.customer.billing.postalcode" text="Postal Code"/></label>
                            <form:input path="order.billing.postalCode"
                                        name="billing-postalCode" id="billing-postalCode"
                                        placeholder="Postal Code"
                                        cssClass="form-control"/>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <div class="box box-default">

            <div class="box-header with-border">
                <h3 class="box-title "> <s:message code="label.order.customer.information" text="Customer Section"/></h3>
            </div>
            <div class="box-body">
                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="customePhone"><s:message code="label.customer.telephone" text="Customer phone"/></label>
                            <form:input path="order.billing.telephone"
                                        name="customePhone" id="customePhone"
                                        placeholder="Phone Number"
                                        cssClass="form-control"/>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="email"><s:message code="label.customer.email" text="Email"/></label>
                            <form:input path="order.customerEmailAddress"
                                        name="email" id="email"
                                        placeholder="Last Name"
                                        cssClass="form-control"/>
                        </div>
                    </div>


                </div>

            </div>
        </div>

        <div class="box box-default">

            <div class="box-header with-border">
                <h3 class="box-title "> <s:message code="label.order.payment.section" text="Payment"/></h3>
            </div>
            <div class="box-body">
                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"
                                   for="customePhone">${order.order.paymentType}</label>
                            <input type="text" readonly="readonly" value="${order.order.paymentModuleCode}" class="form-control">
                        </div>
                    </div>
                    <c:if test="${order.order.paymentType=='CREDITCARD' && order.order.creditCard!=null}">
                        <div class="col-sm-4">
                            <div class="form-group">
                                <label class="control-label"
                                       for="creditCardType"><s:message code="label.order.payment.creditcard.type" text="Credit Card Type"/></label>
                                <input type="text"
                                       name="creditCardType" id="creditCardType" readonly="readonly"
                                       placeholder="Type"
                                       class="form-control" value="${order.order.creditCard.cardType}"/>
                            </div>
                        </div>

                        <div class="col-sm-4">
                            <div class="form-group">
                                <label class="control-label"
                                       for="creditCardNumber"><s:message code="label.order.payment.creditcard.number" text="Credit Card Number"/></label>
                                <input type="text"
                                       name="creditCardNumber" id="creditCardNumber"
                                       placeholder="Number" readonly="readonly"
                                       class="form-control" value="${order.order.creditCard.ccNumber}"/>
                            </div>
                        </div>
                    </c:if>

                </div>

            </div>
        </div>

        <div class="box box-default">

            <div class="box-header with-border">
                <h3 class="box-title "> <s:message code="label.order.status.section" text="Order Status"/></h3>
            </div>
            <div class="box-body">
                <div class="row">

                    <div class="col-sm-4">
                        <div class="form-group">
                            <label class="control-label"><<s:message code="label.order.status.section" text="Order Status"/></label>

                            <form:select name="orderStatus" id="orderStatus"
                                         cssClass="form-control"
                                         cssStyle="width: 100%;" path="order.status">
                                <form:options items="${orderStatusList}"/>
                            </form:select>
                        </div>
                    </div>
                </div>

            </div>
        </div>


<!--common section -->
<input type="hidden" name="isBillingZoneEmpty" id="isBillingZoneEmpty" value="false">
<input type="hidden" name="billingcountryIso" id="billingcountryIso" value="${order.billing.country.isoCode}">
<input type="hidden" name="billingcountryZoneCode" id="billingcountryZoneCode" value="${order.billing.zone.code}">
<input type="hidden" name="billingdefaultText" id="billingZoneText" value="${order.delivery.state}">

<c:if test="${order.billing.state==null || order.billing.state==''}">
    <input type="hidden" name="isBillingZoneEmpty" id="isBillingZoneEmpty" value="true">
</c:if>

<!-- end of common section -->