<%-- 
    Document   : login
    Created on : Aug 6, 2023, 8:21:53 PM
    Author     : THANH NHAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Font Awesome Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" rel="stylesheet">

        <title>Login Page</title>
    </head>

    <body style="background-color: lightblue;">
        <div class="container mt-5">
            <div class="row justify-content-center">
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-body">
                            <h2 class="card-title text-center mb-4">Đăng nhập</h2>

                            <c:if test="${param.error !=null}">
                                <div class="alert alert-danger">
                                    Tài khoản hoặc mật khẩu không đúng!!!!
                                </div>
                            </c:if>
                            <c:if test="${param.accessDenied != null}">
                                <div class="alert alert-danger">
                                    Bạn không có quyền đăng nhập vào đây!!!
                                </div>
                            </c:if>
                            <c:url value="/login" var="action" />
                            <form method="post" action="${action}">

                                <div class="form-floating mb-3 mt-3">
                                    <input type="text" class="form-control" id="username" placeholder="Nhập tài khoản..." name="username">
                                    <label for="username">Tên đăng nhập</label>
                                </div>



                                <div class="form-floating mt-3 mb-3">
                                    <input type="password" class="form-control" id="pwd" placeholder="Nhập mật khẩu..." name="password">
                                    <label for="pwd">Mật khẩu</label>
                                </div>

                                <!-- Remember me and Forgot password -->
                                <div class="d-flex justify-content-between align-items-center mb-3">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="rememberMe" checked />
                                        <label class="form-check-label" for="rememberMe"> Nhớ tài khoản </label>
                                    </div>
                                    <a href="#!" class="float-end">Quên mật khẩu?</a>
                                </div>

                                <!-- Sign in button -->
                                <div class="d-grid">
                                    <button type="submit" class="btn btn-primary btn-block mb-3 ">Đăng nhập</button>

                                </div>

                                <!-- Register buttons -->
                                <div class="text-center">
                                    <p class="mb-0">Bạn không có tài khoản? <a href="#!" class="text-decoration-none">Đăng ký</a>
                                        <!--                                    </p>
                                                                            <p class="mb-3">or sign up with:</p>
                                                                            <div class="d-flex justify-content-center">
                                                                                <button type="button" class="btn btn-link btn-floating mx-1">
                                                                                    <i class="fab fa-facebook-f"></i>
                                                                                </button>
                                        
                                                                                <button type="button" class="btn btn-link btn-floating mx-1">
                                                                                    <i class="fab fa-google"></i>
                                                                                </button>
                                        
                                                                                <button type="button" class="btn btn-link btn-floating mx-1">
                                                                                    <i class="fab fa-twitter"></i>
                                                                                </button>
                                        
                                                                                <button type="button" class="btn btn-link btn-floating mx-1">
                                                                                    <i class="fab fa-github"></i>
                                                                                </button>
                                                                            </div>-->
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>

</html>



