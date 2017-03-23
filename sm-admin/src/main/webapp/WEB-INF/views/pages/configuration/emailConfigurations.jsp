<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/22/17
  Time: 7:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>


<header:breadcrumb breadcrumb="Configuration"/>
<section class="content">

    <c:if test="${success eq 'success'}">
        <div class="box-header with-border" >
            <div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert"
                        aria-hidden="true">&times;</button>
                <h4><i class="icon fa fa-check"></i></h4>
                <s:message code="message.success" text="Request successfull"/>
            </div>
        </div>
    </c:if>

    <c:url var="saveEmailConfiguration" value="/admin/configuration/saveEmailConfiguration.html"/>
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="label.emailconfig.options" text="Email Configuration Options" /></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i
                        class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i
                        class="fa fa-remove"></i></button>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form:form method="POST" modelAttribute="configuration"
                       action="${saveEmailConfiguration}" id="sm-email-configuration">

            <div class="row">

                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.emailconfig.protocol"
                                                                text="Protocol"/></label>
                        <form:input cssClass="form-control" path="protocol"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.emailconfig.host"
                                                                text="Host"/></label>
                        <form:input cssClass="form-control" path="host"/>
                    </div>
                </div>
            </div>

            <div class="row">

                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.emailconfig.port"
                                                                text="Port"/></label>
                        <form:input cssClass="form-control" path="port"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.emailconfig.username"
                                                                text="Username"/></label>
                        <form:input cssClass="form-control" path="username"/>
                    </div>
                </div>
            </div>

            <div class="row">

                <div class="col-sm-6">
                    <div class="form-group">
                        <label class="control-label"><s:message code="label.emailconfig.password"
                                                                text="Password"/></label>
                        <form:input cssClass="form-control" path="password"/>
                    </div>
                </div>

            </div>

                <div class="row">

                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.emailconfig.smtpauth"
                                                                    text="SmtpAuth"/></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="smtpAuth" cssClass="flat-red"/>
                                </label>
                                <s:message code="label.emailconfig.requiresauthentication" text="Email server requires authentication (should be set to true)"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="form-group">
                            <label class="control-label"><s:message code="label.emailconfig.starttls" text="Starttls"/></label>
                            <div class="form-group">
                                <label>
                                    <form:checkbox path="starttls" cssClass="flat-red"/>
                                 </label>
                                <s:message code="label.emailconfig.requiresstarttls" text="Email server requires STARTLS encryption (should be false, check server configurations)"/>
                            </div>
                        </div>
                    </div>
                </div>

        </div>

        </form:form>


        <!-- /.row -->
    </div>

    <div class="box-body">
        <div class="row">
            <div class="col-sm-2 pull-right">

                <button type="button" id="saveEmailConfigurations"
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
        $('#saveEmailConfigurations').click(function (e) {
            e.preventDefault();
            $("#sm-email-configuration").submit();
        });

        //Flat red color scheme for iCheck
        $('input[type="checkbox"].flat-red').iCheck({
                                                        checkboxClass: 'icheckbox_flat-green',
                                                        radioClass: 'iradio_flat-green'
                                                    });

    });
</script>
