<%-- 
    Document   : addShipper
    Created on : Aug 8, 2023, 1:00:11 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/admin/addOrUpdateShipper" var="action" />

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center text-info">Thêm Shipper mới</h1>

            <form:form method="post" action="${action}" modelAttribute="shipper" enctype="multipart/form-data">
                <form:hidden path="id" />
                <form:hidden path="trangthai" />
                <form:hidden path="user.id" />
                <form:hidden path="user.avatar" />
                <form:hidden path="user.userRole" />
                <form:hidden path="user.taikhoan" />
                <form:hidden path="user.matkhau" />
                <!-- ... -->
                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="user.ten" id="user.ten" placeholder="Tên người dùng..." />
                    <label for="ten">Họ tên</label>
                    <form:errors path="user.ten" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="date" class="form-control" path="user.ngaysinh" id="user.ngaysinh" placeholder="Ngày sinh..." />
                    <label for="ngaysinh">Ngày sinh</label>
                    <form:errors path="user.ngaysinh" element="div" cssClass="text-danger" />
                </div>

                <div class="form-select mb-3">
                    <label for="gioitinh">Giới tính</label>
                    <form:select path="user.gioitinh" class="form-control" id="user.gioitinh">
                        <option value="" disabled selected>Chọn giới tính...</option>
                        <form:option value="Nam">Nam</form:option>
                        <form:option value="Nữ">Nữ</form:option>
                        <form:option value="Khác">Khác</form:option>
                    </form:select>
                </div>


                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="user.email" id="user.email" placeholder="Email người dùng..." />
                    <label for="email">Email </label>
                    <form:errors path="user.email" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="user.sdt" id="user.sdt" placeholder="Số điện thoại người dùng..." />
                    <label for="sdt">Số điện thoại </label>
                    <form:errors path="user.sdt" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="user.cmnd" id="user.cmnd" placeholder="Số điện thoại người dùng..." />
                    <label for="sdt">Căn cước công dân </label>
                    <form:errors path="user.cmnd" element="div" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input type="file" class="form-control" path="user.file" id="user.file" />
                    <label for="file">Avatar</label>
                </div>

                <div class="form-floating mb-3">
                    <button class="btn btn-info" type="submit">
                        <c:choose>
                            <c:when test="${shipper.user.id != null}">Cập nhật shipper
                            </c:when>
                            <c:otherwise>Thêm shipper</c:otherwise>
                        </c:choose>
                    </button>
                    <a href="<c:url value="/admin/shippers" />" class="btn btn-secondary btn-success">Quay lại</a>
                </div>
            </form:form>

        </div>
    </div>
</div>
