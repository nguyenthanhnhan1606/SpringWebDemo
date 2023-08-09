<%-- 
    Document   : listUserRegisterShipper
    Created on : Aug 9, 2023, 3:37:19 PM
    Author     : THANH NHAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="m-2">Danh sách các người dùng bạn muốn chọn đề làm shipper</h1>
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
        <c:forEach items="${userList}" var="u">
            <tr>
                <td width="90"><img src="${u.avatar}" alt="Hình ảnh khuyến mãi"  class="w-100"></td>
                <td class="text-center">${u.ten}</td>
                <td class="text-center">${u.cmnd}</td>
                <td class="text-center">${u.gioitinh}</td>
                <td class="text-center">${u.ngaysinh}</td>
                <td class="text-center">${u.email}</td>
                <td class="text-center">${u.sdt}</td>
                <td>
                    <c:url value="/api/shippers/delete/${u.id}" var="apiDel" />
                    <a href="<c:url value="/admin/addShipper/${u.id}"/>" class="btn btn-success">Chọn</a>
                </td>
            </tr>
        </c:forEach>
    </tbody>

</table>
<div class="form-floating  m-2 ">
    <a href="<c:url value="/admin/shippers" />" class="btn btn-secondary btn-success">Quay lại</a>
</div>

