<%-- 
    Document   : order
    Created on : Aug 22, 2023, 2:14:41 PM
    Author     : THANH NHAN
--%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1>Danh Sách Đơn Hàng</h1>
<table class="table table-bordered">
    <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">Mã đơn hàng</th>
            <th scope="col" class="text-center">Hình ảnh</th>
            <th scope="col" class="text-center">Nơi Gửi</th>
            <th scope="col" class="text-center">Nơi Nhận</th>
            <th scope="col" class="text-center">Ngày Tạo</th>
            <th scope="col" class="text-center">Mô Tả</th>
            <th scope="col" class="text-center">Giá Trị ĐH</th>
            <th scope="col" class="text-center">Người gửi</th>
            <th scope="col" class="text-center">Trạng Thái</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${orders}" var="o">
            <tr >
                <td class="text-center">${o.id}</td>
                <td width="80"><img src="${o.image}" alt="Hình ảnh đơn hàng"  class="w-100"></td>
                <td class="text-center">${o.noigui}</td>
                <td class="text-center">${o.noinhan}</td>
                <td class="text-center">${o.ngaytao}</td>
                <td class="text-center">${o.mota}</td>
                <td class="text-center">
                    <fmt:formatNumber value="${o.giatridh}" type="number" pattern="#,##0.0" /><span> </span> VND
                </td>

                <td class="text-center">${o.idUser.ten}</td>
                <td class="text-center">${o.trangthai}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1 justify-content-center">
        <li class="page-item"><a class="page-link" href="<c:url value="/admin/donhang" />">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/admin/donhang" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>