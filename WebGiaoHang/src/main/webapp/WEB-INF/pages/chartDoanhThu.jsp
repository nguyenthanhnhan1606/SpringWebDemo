<%-- 
    Document   : chartDoanhThu
    Created on : Aug 27, 2023, 2:25:03 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div >
    <div class="container">
        <form>
            <div class="form-group">
                <div class="form-group">
                    <label for="nam">Chọn năm:</label>
                    <input type="number" id="nam" name="nam" class="form-control" />
                </div>
            </div>
            <div class="mt-3">
                <button type="submit" class="btn btn-info">Lọc dữ liệu</button>
            </div>
        </form>
        <div class="mt-3" id="chartContainer" style="height: 370px; width: 100%;"></div>

    </div>
    <div>
        <table class="table table-bordered table-striped m-2">
            <thead>
                <tr >
                    <th class="text-center" scope="col">Quý</th>
                    <th class="text-center" scope="col">Doanh thu</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${chartDt}" var="ch">
                    <tr>
                        <td class="text-center">${ch[0]}</td>
                        <td class="text-center">
                            <fmt:formatNumber value="${(ch[1]) * 0.1}" type="number" pattern="#,##0.0" /><span> </span> VND
                        </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
    </div>

</div>
<script>
    window.onload = function () {
        var dataChart = [];
    <c:forEach items="${chartDt}" var="ch">
        dataChart.push({y: ${ch[1]*0.1}, label: "${ch[0]}"});
    </c:forEach>
        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            title: {
                text: "Doanh thu"
            },
            axisY: {
                title: "VNĐ"
            },
            data: [{
                    type: "column",
                    showInLegend: true,
                    legendMarkerColor: "grey",
                    legendText: "Quý",
                    dataPoints: dataChart
                }]
        });
        chart.render();

    }
</script>
<div id="chartContainer" style="height: 370px; width: 100%;"></div>
<script src="https://cdn.canvasjs.com/canvasjs.min.js"></script>

