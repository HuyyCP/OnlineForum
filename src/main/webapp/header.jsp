<%@ page import="Model.Bean.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Header</title>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    boolean isLogin = false;
    User userLogin = null;
    if (session.getAttribute("user") != null) {
        userLogin = (User)session.getAttribute("user");
        isLogin = true;
    }
%>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid px-5">
        <a class="navbar-brand" href="#">Logo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Trang chủ</a>
                </li>

            </ul>
            <% if (!isLogin) { %>
            <div class="ms-auto">
                <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">Login</button>
                <button class="btn btn-outline-danger ms-2" type="button" data-bs-toggle="modal" data-bs-target="#registerModal">Register</button>
            </div>
            <% } else { %>
            <div class="ms-auto">
                <% if(userLogin.getRole().getRoleName().equals("Admin")) { %>
                    <a href="/admin" class="btn btn-primary">Admin</a>
                <% } %>
                <a href="/user/<%=userLogin.getIdUser()%>" class="btn btn-primary">Profile</a>
                <a href="/logout" class="btn btn-outline-danger">Logout</a>
            </div>
            <% } %>
        </div>
    </div>
</nav>


<!-- Login Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="loginModalLabel">Login</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Login Form -->
                <form action="/login" method="post">

                    <input type="hidden" name="redirect" value="<%= session.getAttribute("currentUrl") %>" />
                    <div class="mb-3">
                        <label for="loginUserName" class="form-label">Email address</label>
                        <input type="text" class="form-control" id="loginUserName" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="loginPassword" class="form-label">Password</label>
                        <input type="password" class="form-control" id="loginPassword" name="password" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Register Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="registerModalLabel">Register</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <!-- Register Form -->
                <form action="/register" method="post">
                    <input type="hidden" name="redirect" value="<%= session.getAttribute("currentUrl") %>" />
                    <div class="mb-3">
                        <label for="userName" class="form-label">Tên người dùng</label>
                        <input type="text" class="form-control" id="userName" name="username" required>
                    </div>
                    <div class="mb-3">
                        <label for="registerName" class="form-label">Họ và tên</label>
                        <input type="text" class="form-control" id="registerName" name="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="registerEmail" class="form-label">Địa chỉ email</label>
                        <input type="email" class="form-control" id="registerEmail" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="phonenumber" class="form-label">Số điện thoại</label>
                        <input type="number" class="form-control" id="phonenumber" name="phone" required>
                    </div>
                    <div class="mb-3">
                        <label for="dateofbirth" class="form-label">Ngày sinh</label>
                        <input type="date" class="form-control" id="dateofbirth" name="dateofbirth" required>
                    </div>
                    <div class="mb-3">
                        <label for="registerPassword" class="form-label">Mật khẩu</label>
                        <input type="password" class="form-control" id="registerPassword" name="password" required>
                    </div>
                    <div class="mb-3">
                        <label for="confirmPassword" class="form-label">Nhập lại mật khẩu</label>
                        <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Register</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</body>
</html>