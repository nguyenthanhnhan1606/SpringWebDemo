<%-- 
    Document   : chartTanSuat
    Created on : Aug 27, 2023, 2:25:16 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div >
    <div class="container">
        <form>
            <div >
                <div class="form-group">
                    <label>Từ ngày</label>
                    <input  type="date" name="fromDate" class="form-control"  />
                </div>
                <div class="form-group">
                    <label>Đến ngày</label>
                    <input  type="date" name="toDate" class="form-control"  />
                </div>
            </div>

            <div class="mt-3">
                <input type="submit" value="Lọc dữ liệu" class="btn btn-info" />
            </div>
        </form>
        <div class="mt-3" id="chartContainer" style="height: 370px; width: 100%;"></div>

    </div>
    <div>
        <table class="table table-bordered table-striped m-2">
            <thead>
                <tr >
                    <th class="text-center" scope="col">Trạng thái</th>
                    <th class="text-center" scope="col">Số lượng</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${chart}" var="ch">
                    <tr>
                        <td class="text-center">${ch[1]}</td>
                        <td class="text-center">${ch[0]}</td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </div>

</div>

<script>
    window.onload = function () {

        var dataChart = [];
    <c:forEach items="${chart}" var="ch">
        dataChart.push({y: ${ch[0]}, label: "${ch[1]}"});
    </c:forEach>
        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: "THỐNG KÊ TẦN XUẤT"
            },
            data: [{
                    type: "pie",
                    startAngle: 25,
                    toolTipContent: "<b>{label}</b>: {y}",
                    showInLegend: "true",
                    legendText: "{label}",
                    indexLabelFontSize: 16,
                    indexLabel: "{label} - {y}",
                    dataPoints: dataChart
                }]
        });
        chart.render();

    }
</script>


<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>