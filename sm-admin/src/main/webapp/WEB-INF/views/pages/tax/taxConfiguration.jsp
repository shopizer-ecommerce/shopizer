<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/23/17
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/22/17
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>


<header:breadcrumb breadcrumb="Tax Configuration"/>
<section class="content">

    <c:if test="${success eq 'success'}">
        <div class="box-header with-border">
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;</button>
                <h4><i class="icon fa fa-check"></i></h4>
                <s:message code="message.success" text="Request successfull"/>
            </div>
        </div>
    </c:if>

    <c:url var="saveTaxConfiguration" value="/admin/tax/taxconfiguration/save.html"/>
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="label.tax.taxconfiguration"
                                             text="Tax basis calculation"/></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form:form method="POST" modelAttribute="taxConfiguration"
                       action="${saveTaxConfiguration}" id="sm-tax-config">


                <div class="row with-border">

                    <div class="col-sm-6">
                        <div class="form-group">

                            <div class="radio radio-info radio-inline">
                                <form:radiobutton id="taxBasisCalculation"
                                                  path="taxBasisCalculation" value="BILLINGADDRESS"
                                                  cssClass="flat-red"/>
                                <label><s:message code="label.shipping.billingaddress"
                                                  text="Billing address"/></label>
                            </div>

                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <div class="radio radio-info radio-inline">
                                <form:radiobutton id="taxBasisCalculation"
                                                  path="taxBasisCalculation" value="STOREADDRESS"
                                                  cssClass="flat-red"/>
                                <label><s:message code="label.tax.storeaddress"
                                                  text="Store address"/></label>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row with-border">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <div class="radio radio-info radio-inline">
                                <form:radiobutton id="taxBasisCalculation"
                                                  path="taxBasisCalculation" value="SHIPPINGADDRESS"
                                                  cssClass="flat-red"/>
                                <label><s:message code="label.shipping.shippingaddress"
                                                  text="Shipping address"/></label>
                            </div>
                        </div>
                    </div>

                </div>
            </form:form>


            <!-- /.row -->
        </div>

        <div class="box-footer">
            <div class="row">
                <div class="col-sm-2 pull-right">

                    <button type="button" id="saveTaxConfigurations"
                            class="btn btn-block btn-success pull-right">
                        <s:message code="button.label.submit" text="Submit"/>
                    </button>
                </div>

            </div>
        </div>
        <!-- /.box-body -->

    </div>

</section>

<script>

    $(window).on('load', function () {
        $('#saveTaxConfigurations').click(function (e) {
            e.preventDefault();
            $("#sm-tax-config").submit();
        });

        //Flat red color scheme for iCheck
        $('input[type="radio"].flat-red').iCheck({
                                                     checkboxClass: 'icheckbox_flat-green',
                                                     radioClass: 'iradio_flat-green'
                                                 });

    });
</script>

