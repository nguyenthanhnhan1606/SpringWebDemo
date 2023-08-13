<%-- 
    Document   : shipper
    Created on : Aug 7, 2023, 10:35:46 PM
    Author     : THANH NHAN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="m-2">Quản lý Shipper</h1>
<table class="table table-bordered table-striped m-2">
    <thead>
        <tr >
            <th class="text-center" scope="col">Hình ảnh</th>
            <th class="text-center" scope="col">Họ tên</th>
            <th class="text-center" scope="col">Số CCCD</th>
            <th class="text-center" scope="col">Giới tính</th>
            <th cla ss="text-center" scope="col">Năm sinh</th>
            <th class="text-center" class="text-center"scope="col">Email</th>
            <th class="text-center" scope="col">Số điện thoại</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${shippers}" var="s">
            <tr>
                <td width="90"><img src="${s.user.avatar}" alt="Hình ảnh khuyến mãi"  class="w-100"></td>
                <td class="text-center">${s.user.ten}</td>
                <td class="text-center">${s.user.cmnd}</td>
                <td class="text-center">${s.user.gioitinh}</td>
                <td class="text-center">${s.user.ngaysinh}</td>
                <td class="text-center">${s.user.email}</td>
                <td class="text-center">${s.user.sdt}</td>
                <td>
                    <c:url value="/api/shippers/recyclebin/${s.id}" var="apiDel" />
                    <a href="<c:url value="/admin/addOrUpdateShipper/${s.user.id}"/>" class="btn btn-success">Cập nhật</a>
                    <button class="btn btn-danger" onclick="recycleBin('${apiDel}', ${s.id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div>
    <div>
        <a href="<c:url value="/admin/listUser" />" class="btn btn-info m-2 ">Thêm shipper</a>
    </div>
</div>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1 justify-content-center">
        <li class="page-item"><a class="page-link" href="<c:url value="/admin/shippers" />">Tất cả</a></li>
            <c:forEach begin="1" end="${counter}" var="i">
                <c:url value="/admin/shippers" var="pageUrl">
                    <c:param name="page" value="${i}"></c:param>
                </c:url>
            <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
            </c:forEach>
    </ul>
</c:if>

<script src="<c:url value="/js/api.js" />"></script>
