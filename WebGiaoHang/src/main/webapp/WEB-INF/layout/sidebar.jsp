<%-- 
    Document   : slider
    Created on : Jul 22, 2023, 8:43:49 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <a class="nav-link" href="<c:url value="/admin"/>" >
                    <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                    Trang chủ
                </a>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAccount3"
                   aria-expanded="false" aria-controls="collapseAccount">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-user-doctor"></i></div>
                    Shipper
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseAccount3" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="<c:url value="/admin/shippers"/>">Quản lý shipper</a>
                        <a class="nav-link" href="<c:url value="/admin/listUserRegisterShipper"/>">Duyệt đăng ký shipper</a>
                    </nav>
                </div>
                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAccount"
                   aria-expanded="false" aria-controls="collapseAccount">
                    <div class="sb-nav-link-icon"><i class="fas fa-box"></i></div>
                    Đơn hàng
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseAccount" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="#">Đang chờ đấu giá</a>
                        <a class="nav-link" href="#">Đang chờ nhận shipper</a>
                    </nav>
                </div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAccount1"
                   aria-expanded="false" aria-controls="collapseAccount1">
                    <div class="sb-nav-link-icon"><i class="fas fa-tags"></i></div>
                    Khuyến mãi
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseAccount1" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href=" <c:url value="/admin/khuyenmais?page=1" />" >Quản lý khuyến mãi</a>
                        <a class="nav-link" href="<c:url value="/admin/kmexpires?page=1" />">Khuyến mãi hết hạn</a>
                    </nav>
                </div>

                <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseAccount4"
                   aria-expanded="false" aria-controls="collapseAccount">
                    <div class="sb-nav-link-icon"><i class="fa-solid fa-user-nurse"></i></div>
                    Tài khoản
                    <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                </a>
                <div class="collapse" id="collapseAccount4" aria-labelledby="headingOne"
                     data-bs-parent="#sidenavAccordion">
                    <nav class="sb-sidenav-menu-nested nav">
                        <a class="nav-link" href="/admin/add_yta">Khách Hàng</a>
                        <a class="nav-link" href="/admin/yta">Admin</a>
                    </nav>
                </div>
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name == null}">
                        <a class="nav-link" href="<c:url value="/login" />">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-user"></i></div>
                            Đăng nhập
                        </a>
                    </c:when>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <a class="nav-link" href="<c:url value="/logout" />">
                            <div class="sb-nav-link-icon"><i class="fa-solid fa-user"></i></div>
                            Đăng xuất
                        </a>
                    </c:when>
                </c:choose>

                <!--        <a class="nav-link" href="#">
                          <div class="sb-nav-link-icon"><i class="fa-solid fa-money-bill"></i></div>
                          Hóa dơn
                        </a>
                        <a class="nav-link" href="#">
                          <div class="sb-nav-link-icon"><i class="fa-solid fa-tablets"></i></div>
                          Thuốc
                        </a>-->
            </div>
        </div>
    </nav>
</div>