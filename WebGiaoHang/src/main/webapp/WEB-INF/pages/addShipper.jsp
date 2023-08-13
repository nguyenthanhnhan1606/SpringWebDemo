<%-- 
    Document   : addShipper
    Created on : Aug 9, 2023, 1:55:06 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:url value="/admin/addShipper" var="action" />

<div class="container">
    <div class="row mt-4">
        <div class="col-md-6 offset-md-3">
            <h1 class="text-center text-info">Thêm mới shipper</h1>

            <form:form method="post" action="${action}" modelAttribute="userUpdate" enctype="multipart/form-data" >
                <form:hidden path="id" />
                <form:hidden path="avatar" />
                <form:hidden path="userRole" />
                <form:hidden path="taikhoan" />
                <form:hidden path="matkhau" />
                <form:hidden path="active" />

                <!-- ... -->
                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="ten" id="ten" placeholder="Tên người dùng..." readonly="true"/>
                    <label for="ten">Họ tên</label>
                    <form:errors path="ten" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="date" class="form-control" path="ngaysinh" id="ngaysinh" placeholder="Ngày sinh..." readonly="true" />
                    <label for="ngaysinh">Ngày sinh</label>
                    <form:errors path="ngaysinh" element="div" cssClass="text-danger" />
                </div>

                <div class="form-select mb-3">
                    <label for="gioitinh">Giới tính</label>
                    <form:select path="gioitinh" class="form-control" id="gioitinh" readonly="true" >
                        <option value="" disabled selected>Chọn giới tính...</option>
                        <form:option value="Nam">Nam</form:option>
                        <form:option value="Nữ">Nữ</form:option>
                        <form:option value="Khác">Khác</form:option>
                    </form:select>
                </div>


                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="email" id="email" placeholder="Email người dùng..." readonly="true" />
                    <label for="email">Email </label>
                    <form:errors path="email" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="sdt" id="sdt" placeholder="Số điện thoại người dùng..." readonly="true" />
                    <label for="sdt">Số điện thoại </label>
                    <form:errors path="sdt" element="div" cssClass="text-danger" />
                </div>

                <div class="form-floating mb-3">
                    <form:input type="text" class="form-control" path="cmnd" id="cmnd" placeholder="Số điện thoại người dùng..." readonly="true"/>
                    <label for="sdt">Căn cước công dân </label>
                    <form:errors path="cmnd" element="div" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input type="file" class="form-control" path="file" id="file" readonly="true" />
                    <label for="file">Avatar</label>
                </div>

                <div class="form-floating mb-3">
                    <button class="btn btn-info" type="submit">
                        <c:choose>
                            <c:when test="${user.id != null}">Cập nhật thông tin User</c:when>
                            <c:otherwise>Thêm User</c:otherwise>
                        </c:choose>
                    </button>
                    <a href="<c:url value="/admin/listUser" />" class="btn btn-secondary btn-success">Quay lại</a>
                </div>
            </form:form>

        </div>
    </div>
</div>
