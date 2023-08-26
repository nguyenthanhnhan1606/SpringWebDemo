<%-- 
    Document   : promotion
    Created on : Aug 1, 2023, 10:40:07 AM
    Author     : THANH NHAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="m-2">Quản lý khuyến mãi</h1>
<table class="table table-bordered table-striped m-2">
    <thead>
        <tr >
            <th class="text-center" scope="col">Hình ảnh</th>
            <th class="text-center" scope="col">Mã khuyến mãi</th>
            <th class="text-center" scope="col">Loại khuyến mãi</th>
            <th class="text-center" scope="col">Mô tả</th>
            <th class="text-center" scope="col">Ngày bắt đầu</th>
            <th class="text-center" scope="col">Ngày kết thúc</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${promotion}" var="p">
            <tr >
                <td width="80"><img src="${p.image}" alt="Hình ảnh khuyến mãi"  class="w-100"></td>
                <td class="text-center">${p.id}</td>
                <td class="text-center">${p.loaikhuyenmai}</td>
                <td class="text-center">${p.mota}</td>
                <td class="text-center">${p.ngaybd}</td>
                <td class="text-center">${p.ngaykt}</td>
                <td>
                    <c:url value="/api/khuyenmais/recycle/${p.id}" var="apiDel" />
                    <a href="<c:url value="/admin/addkhuyenmai/${p.id}"/>" class="btn btn-success">Cập nhật</a>
                    <button class="btn btn-danger" onclick="recycleBin('${apiDel}', ${p.id})">Xóa</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<div>
    <div>
        <a href="<c:url value="/admin/addkhuyenmai" />" class="btn btn-info m-2 ">Thêm khuyến mãi</a>
    </div>
</div>
<c:if test="${counter > 1}">
    <ul class="pagination mt-1 justify-content-center">
        <c:choose>
            <c:when test= "${flagPro == 1}">
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/kmexpires" />">Tất cả</a></li>
                    <c:forEach begin="1" end="${counter}" var="i">
                        <c:url value="/admin/kmexpires" var="pageUrl">
                            <c:param name="page" value="${i}"></c:param>
                        </c:url>
                    <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                <li class="page-item"><a class="page-link" href="<c:url value="/admin/khuyenmais" />">Tất cả</a></li>
                    <c:forEach begin="1" end="${counter}" var="i">
                        <c:url value="/admin/khuyenmais" var="pageUrl">
                            <c:param name="page" value="${i}"></c:param>
                        </c:url>
                    <li class="page-item"><a class="page-link" href="${pageUrl}">${i}</a></li>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

    </ul>
</c:if>
<script src="<c:url value="/js/api.js" />"></script>