<%-- 
    Document   : addKhuyenMai
    Created on : Aug 1, 2023, 11:52:27 AM
    Author     : THANH NHAN
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/admin/addkhuyenmai" var="action" />
<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center text-info">Thêm khuyến mãi mới</h1>
            <form:form method="post" action="${action}" modelAttribute="promotion" enctype="multipart/form-data">
                <form:errors path="*" element="div" cssClass="alert alert-danger" />
                <form:hidden path="id" />
                <form:hidden path="image" />
                <form:hidden path="active"/>
                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="loaikhuyenmai" id="loaikhuyenmai" placeholder="Loại khuyến mãi..." />
                    <label for="loaikhuyenmai">Loại khuyến mãi</label>
                    <form:errors path="loaikhuyenmai" element="div" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:textarea class="form-control" id="mota" name="text" path="mota" placeholder="Mô tả"></form:textarea>
                        <label for="mota">Mô tả</label>
                    </div>
                    <div class="form-floating mb-3">
                    <form:input type="date" class="form-control" path="ngaybd" id="ngaybd" placeholder="Ngày bắt đầu..." />
                    <label for="ngaybd">Ngày bắt đầu</label>
                     <form:errors path="ngaybd" element="div" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input type="date" class="form-control" path="ngaykt" id="ngaykt" placeholder="Ngày kết thúc..." />
                    <label for="ngaykt">Ngày kết thúc</label>
                     <form:errors path="ngaykt" element="div" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input type="file" class="form-control" path="file" id="file" />
                    <label for="file">Hình ảnh khuyến mãi</label>
                </div>
                <div class="form-floating mb-3">
                    <button class="btn btn-info" type="submit">
                        <c:choose>
                            <c:when test="${promotion.id != null}">Cập nhật khuyến mãi

                            </c:when>
                            <c:otherwise>Thêm khuyến mãi</c:otherwise>
                        </c:choose>
                    </button>
                    <a href="<c:url value="/admin/khuyenmais" />" class="btn btn-secondary btn-success">Quay lại</a>
                </div>
            </form:form>
        </div>
    </div>
</div>
