<%--
  Created by IntelliJ IDEA.
  User: umesh
  Date: 3/21/17
  Time: 9:40 PM
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
<c:url value="/admin/configuration/saveConfiguration.html" var="saveAccountsConfiguration"/>
    <div class="box box-default">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="label.configuration.options" text="Configuration options" /></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"><i class="fa fa-remove"></i></button>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <form:form method="POST" modelAttribute="configuration" action="${saveAccountsConfiguration}" id="sm-configuration">
                <c:forEach var="merchantConfig" items="${configuration.merchantConfigs}" varStatus="counter">

                    <c:if test="${(counter.index) % 2 == 0}">
                        <div class="row">
                    </c:if>
                        <div class="col-sm-6">
                            <div class="form-group">
                                <label class="control-label"><s:message code="label.configuration.${merchantConfig.key}" text="** Label for [label.configuration.${merchantConfig.key}] not found **" /></label>
                                <form:hidden  path="merchantConfigs[${counter.index}].key" />
                                <form:hidden  path="merchantConfigs[${counter.index}].id" />
                                <form:input  path="merchantConfigs[${counter.index}].value" cssClass="form-control"/>

                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${(fn:length(configuration.merchantConfigs) le 2 ) && (fn:length(configuration.merchantConfigs) eq (counter.count))}">

                        </div>
                            </c:when>
                            <c:otherwise>
                <c:if test="${(counter.index ge 1) && (counter.count) % 2 == 0}">

        </div>
        </c:if>

                            </c:otherwise>
                        </c:choose>



                </c:forEach>
            </form:form>



            <!-- /.row -->
        </div>

    <div class="box-body">
        <div class="row">
            <div class="col-sm-2 pull-right">

                    <button type="button" id="saveConfigurations" class="btn btn-block btn-success pull-right">
                        <s:message code="button.label.submit" text="Submit"/>
                    </button>
                </div>

        </div>
    </div>
        <!-- /.box-body -->

    </div>

</section>

<script>

    $(function() {
        $('#saveConfigurations').click(function(e) {
            e.preventDefault();
            $("#sm-configuration").submit();
        });


    });
</script>