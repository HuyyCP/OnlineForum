<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Thông báo</title>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<script>
  window.onload = function() {
    <%
    session = request.getSession(false);
    String registrationStatus = null;
    if (session != null) {
        registrationStatus = (String) session.getAttribute("registrationStatus");
        session.removeAttribute("registrationStatus");
    }
    String loginStatus = null;
    if (session != null) {
        loginStatus = (String) session.getAttribute("loginStatus");
        session.removeAttribute("loginStatus");
    }
    %>
    var registrationStatus = "<%= registrationStatus %>";

    var loginStatus = "<%= loginStatus %>";
    if (loginStatus === "success") {
      Swal.fire({
        title: 'Thành công!',
        text: 'Bạn đã đăng nhập thành công.',
        icon: 'success',
        confirmButtonText: 'Đóng'
      });
    } else if (loginStatus === "failure") {
      Swal.fire({
        title: 'Thất bại!',
        text: 'Đăng nhập không thành công. Vui lòng thử lại.',
        icon: 'error',
        confirmButtonText: 'Đóng'
      });
    }

    if (registrationStatus === "success") {
      Swal.fire({
        title: 'Thành công!',
        text: 'Bạn đã đăng ký thành công.',
        icon: 'success',
        confirmButtonText: 'Đóng'
      });
    } else if (registrationStatus === "failure") {
      Swal.fire({
        title: 'Thất bại!',
        text: 'Đăng ký không thành công. Vui lòng thử lại.',
        icon: 'error',
        confirmButtonText: 'Đóng'
      });
    }
  }
</script>
</body>
</html>
