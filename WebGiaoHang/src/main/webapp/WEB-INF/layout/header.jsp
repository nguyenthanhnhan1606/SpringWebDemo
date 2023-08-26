<%--
    Document   : header
    Created on : Jul 22, 2023, 8:21:33 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="sb-topnav navbar navbar-expand navbar-dark text-bg-primary bg-dark "   >
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="<c:url value="/admin"/>"> QLGH- Admin</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i
            class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <div class="container">
        <div class="row">
            <div class="col-lg-8">
                <div class="navbar-nav align-items-center">
                    <form class="form-inline my-2 my-lg-0" id="searchForm">
                        <div class="nav-item d-flex align-items-center">
                            <i class="bx bx-search fs-4 lh-0"></i>
                            <input style="min-width: 500px;" type="text" class="form-control border-0 shadow-none search-bar" name="search" placeholder="Search..." aria-label="Search..."/>
                            <button type="submit" class="btn btn-primary" style="margin-left: -5px;">Tìm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4 me-auto">
        <li class="nav-item">
            <a class="nav-link" href="<c:url value="/admin/current-user"/>">
                <div class="d-flex align-items-center">
                    <span class="text-info me-2" style="white-space: nowrap">Chào, ${user.ten}</span>
                    <img src="<c:url value="${user.avatar}"/>" width="40" class="rounded-circle" alt="Avatar" />
                </div>
            </a>
        </li>
    </ul>




</nav>