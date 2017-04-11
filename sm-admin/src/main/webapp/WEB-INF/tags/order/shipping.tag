<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>


<div class="box box-default">

    <div class="box-header with-border">
        <h3 class="box-title "><s:message code="label.customer.shippinginformation" text="Shipping information"/></h3>
    </div>
    <div class="box-body">
        <div class="row">

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"
                           for="firstName"><s:message code="label.customer.shipping.firstname" text="First Name"/></label>
                    <form:input path="order.delivery.firstName"
                                name="firstName" id="firstName"
                                placeholder="First Name"
                                cssClass="form-control"/>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"
                           for="lastName"><s:message code="label.customer.shipping.lastname" text="Last Name"/></label>
                    <form:input path="order.delivery.lastName"
                                name="lastName" id="lastName"
                                placeholder="Last Name"
                                cssClass="form-control"/>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"
                           for="streetAddress"><s:message code="label.customer.shipping.streetaddress" text="Street Address"/></label>
                    <form:input path="order.delivery.address"
                                name="streetAddress" id="streetAddress"
                                placeholder="Street Address"
                                cssClass="form-control"/>
                </div>
            </div>
        </div>

        <div class="row">

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"
                           for="shippingCity"><s:message code="label.customer.shipping.city" text="City"/></label>
                    <form:input path="order.delivery.city"
                                name="shippingCity" id="shippingCity"
                                placeholder="City"
                                cssClass="form-control"/>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"><s:message
                            code="label.customer.shipping.zone"
                            text="Zone"/></label>

                    <form:select name="countries" id="shippingZone"
                                 cssClass="form-control order-country"
                                 cssStyle="width: 100%;" path="order.delivery.zone.code">
                        <form:options/>
                    </form:select>
                </div>
            </div>

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"><s:message code="label.customer.shipping.country" text="Country"/></label>

                    <form:select name="shippingCountry" id="shipping-country"
                                 cssClass="form-control shipping-country"
                                 cssStyle="width: 100%;" path="order.delivery.country.isoCode">
                        <form:options items="${countries}" itemValue="isoCode" itemLabel="name"/>
                    </form:select>
                </div>
            </div>
        </div>

        <div class="row">

            <div class="col-sm-4">
                <div class="form-group">
                    <label class="control-label"
                           for="postal-code"><s:message code="label.customer.shipping.postalcode" text="Postal Code"/></label>
                    <form:input path="order.delivery.postalCode"
                                name="postal-code" id="postal-code"
                                placeholder="Postal Code"
                                cssClass="form-control"/>
                </div>
            </div>

        </div>
    </div>
</div>



