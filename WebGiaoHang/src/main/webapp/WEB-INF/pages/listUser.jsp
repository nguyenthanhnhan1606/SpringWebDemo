<%-- 
    Document   : listShippers
    Created on : Aug 8, 2023, 10:08:06 PM
    Author     : THANH NHAN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="m-2">Danh sách các người dùng</h1>
<table class="table table-bordered table-striped m-2">
    <thead>
        <tr >
            <th class="text-center" scope="col">Hình ảnh</th>
            <th class="text-center" scope="col">Họ tên</th>
            <th class="text-center" scope="col">Số CCCD</th>
            <th class="text-center" scope="col">Giới tính</th>
            <th class="text-center" scope="col">Năm sinh</th>
            <th class="text-center" scope="col">Email</th>
            <th class="text-center" scope="col">Số điện thoại</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${userss}" var="u">
            <tr>
                <td width="90"><img src="${u.avatar}" alt="Hình ảnh khuyến mãi"  class="w-100"></td>
                <td class="text-center">${u.ten}</td>
                <td class="text-center">${u.cmnd}</td>
                <td class="text-center">${u.gioitinh}</td>
                <td class="text-center">${u.ngaysinh}</td>
                <td class="text-center">${u.email}</td>
                <td class="text-center">${u.sdt}</td>
                <td>
                    <c:choose>
                        <c:when test="${flagShipper != 1 }">
                            <a href="<c:url value="/admin/addShipper/${u.id}"/>" class="btn btn-success">Chọn</a>
                        </c:when>
                        <c:otherwise>
                            <c:url value="/api/usertoshipper/${u.id}" var="apiCon" />
                            <c:url value="/api/refuse/${u.id}" var="apiRef"/>
                            <button class="btn btn-success" onclick="confirmShipper('${apiCon}', ${u.id})">Xác nhận</button>
                            <button class="btn btn-danger" onclick="refuseShipper('${apiRef}', ${s.id})">Từ chối</button>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>

</table>
<div class="form-floating  m-2 ">
    <a href="<c:url value="/admin/shippers" />" class="btn btn-secondary btn-success">Quay lại</a>
</div>
<script src="<c:url value="/js/api.js" />"></script>

