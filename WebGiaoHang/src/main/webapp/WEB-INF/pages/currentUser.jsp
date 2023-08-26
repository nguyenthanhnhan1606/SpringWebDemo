<%-- 
    Document   : currentUser
    Created on : Aug 22, 2023, 4:02:23 PM
    Author     : THANH NHAN
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">Thông tin chi tiết của bạn</h1>
<div class="row">
    <div class="col-md-4">
        <div class="card">
            <img class="card-img-top" style="object-fit: cover; height: 350px;" src="${user.avatar}" alt="Avatar">
        </div>
    </div>
    <div class="col-md-8">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Họ và tên: ${user.ten}</h5>
                <p class="card-text">Ngày sinh: ${user.ngaysinh}</p>
                <p class="card-text">Giới tính: ${user.gioitinh}</p>
                <p class="card-text">Email: ${user.email}</p>
                <p class="card-text">Số điện thoại: ${user.sdt}</p>
            </div>
        </div>

    </div>
    <div>
        <div>
            <a href="<c:url value="/admin" />" class="btn btn-success btn-block mt-2">Quay lại</a>

        </div>
    </div>

</div>

