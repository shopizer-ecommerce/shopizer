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
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<header:breadcrumb breadcrumb="Cache Management"/>

<section class="content">

    <!-- Default box -->
    <div class="box">
        <div class="box-header with-border">
            <h3 class="box-title"><s:message code="menu.cache" text="Cache management"/></h3>

            <div class="box-tools pull-right">
                <button type="button" class="btn btn-box-tool" data-widget="collapse"
                        data-toggle="tooltip" title="Collapse">
                    <i class="fa fa-minus"></i></button>
                <button type="button" class="btn btn-box-tool" data-widget="remove"
                        data-toggle="tooltip" title="Remove">
                    <i class="fa fa-times"></i></button>
            </div>
        </div>

        <!-- Message section -->
        <c:choose>
            <c:when test="${status eq 'OK'}">
                <div class="box-header with-border">
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-check"></i></h4>
                        <s:message code="cache.clear.success"
                                   text="Cache cleared successfully !!!"/>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="box-header with-border">
                    <div class="alert alert-danger alert-dismissible">
                        <button type="submit" class="close" data-dismiss="alert"
                                aria-hidden="true">&times;</button>
                        <h4><i class="icon fa fa-ban"></i> Alert!</h4>
                        <s:message code="cache.clear.error" text="Error while clearing cache !!!"/>
                    </div>
                </div>
            </c:otherwise>

        </c:choose>

        <div class="box-body"> <%--box-footer clearfix--%>

            <div class="row">
                <div class="col-xs-12">
                    <!-- interactive chart -->
                    <div class="box box-primary">
                        <div class="box-header with-border">
                            <i class="fa fa-bar-chart-o"></i>

                            <h3 class="box-title"><s:message code="menu.cache.statics"
                                                             text="Cache Statics"/></h3>

                            <div class="box-tools pull-right">
                                Real time
                                <div class="btn-group" id="realtime" data-toggle="btn-toggle">
                                    <button type="button" class="btn btn-default btn-xs active"
                                            data-toggle="on">On
                                    </button>
                                    <button type="button" class="btn btn-default btn-xs"
                                            data-toggle="off">Off
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="box-body">
                            <div id="interactive" style="height: 300px;"></div>
                        </div>
                        <!-- /.box-body-->
                    </div>
                    <!-- /.box -->

                </div>
                <!-- /.col -->
            </div>
            <div class="box-footer clearfix">
                <c:url value="/admin/cache/clear.html" var="clearCacheUrl"/>

                <form:form action="${clearCacheUrl}" method="post">
                    <input type="hidden" id="cacheKey" name="cacheKey" value="${keys}"/>
                    <button type="submit" class="btn btn-block btn-danger btn-sm pull-right">Clear
                        Cache
                    </button>
                </form:form>
            </div>
        </div>

    </div>
    <!-- /.box -->

</section>

<script>
    $(window).on('load', function () {
        /*
         * Flot Interactive Chart
         * -----------------------
         */
        // We use an inline data source in the example, usually data would
        // be fetched from a server
        var data = [], totalPoints = 100;

        function getRandomData() {

            if (data.length > 0)
                data = data.slice(1);

            // Do a random walk
            while (data.length < totalPoints) {

                var prev = data.length > 0 ? data[data.length - 1] : 50,
                    y = prev + Math.random() * 10 - 5;

                if (y < 0) {
                    y = 0;
                } else if (y > 100) {
                    y = 100;
                }

                data.push(y);
            }

            // Zip the generated y values with the x values
            var res = [];
            for (var i = 0; i < data.length; ++i) {
                res.push([i, data[i]]);
            }

            return res;
        }

        var interactive_plot = $.plot("#interactive", [getRandomData()], {
            grid: {
                borderColor: "#f3f3f3",
                borderWidth: 1,
                tickColor: "#f3f3f3"
            },
            series: {
                shadowSize: 0, // Drawing is faster without shadows
                color: "#3c8dbc"
            },
            lines: {
                fill: true, //Converts the line chart to area chart
                color: "#3c8dbc"
            },
            yaxis: {
                min: 0,
                max: 100,
                show: true
            },
            xaxis: {
                show: true
            }
        });

        var updateInterval = 500; //Fetch data ever x milliseconds
        var realtime = "on"; //If == to on then fetch data every x seconds. else stop fetching
        function update() {

            interactive_plot.setData([getRandomData()]);

            // Since the axes don't change, we don't need to call plot.setupGrid()
            interactive_plot.draw();
            if (realtime === "on")
                setTimeout(update, updateInterval);
        }

        //INITIALIZE REALTIME DATA FETCHING
        if (realtime === "on") {
            update();
        }
        //REALTIME TOGGLE
        $("#realtime .btn").click(function () {
            if ($(this).data("toggle") === "on") {
                realtime = "on";
            }
            else {
                realtime = "off";
            }
            update();
        });
    });

</script>