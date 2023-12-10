<%@ page import="Model.Bean.User" %><%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 07-Dec-23
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
    <%
        User userLogin = (User)session.getAttribute("user");
    %>

    <!-- Bootstrap form -->
    <div class="container my-4">
        <form action="../user/update/<%=userLogin.getIdUser()%>" method="post" class="m-3">
            <div class="mb-3">
                <label for="name" class="form-label">Tên người dùng</label>
                <input type="text" class="form-control" id="name" value="<%=userLogin.getName()%>" name="name">
            </div>
            <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" value="<%=userLogin.getEmail()%>" name="email" readonly>
            </div>
            <div class="mb-3">
                <label for="dateofbirth" class="form-label">Ngày sinh</label>
                <input type="date" class="form-control" id="dateofbirth" value="<%=userLogin.getDob()%>" name="dateofbirth">
            </div>
            <div class="mb-3">
                <label for="phonenumber" class="form-label">Số điện thoại</label>
                <input type="tel" class="form-control" id="phonenumber" value="<%=userLogin.getPhoneNumber()%>" name="phonenumber">
            </div>
            <button type="submit" class="btn btn-primary">Cập nhật</button>
        </form>
    </div>
<jsp:include page="footer.jsp"/>
</body>
</html>
