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
    <%
        User userLogin = (User)session.getAttribute("user");
    %>

    <form action="../user/update/<%=userLogin.getIdUser()%>" method="post">
        <table>
            <tr>
                <td>Tên người dùng</td>
                <td><input type="text" value="<%=userLogin.getName()%>" name="name"></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="email" value="<%=userLogin.getEmail()%>" name="email" readonly></td>
            </tr>
            <tr>
                <td>Ngày sinh</td>
                <td><input type="date" value="<%=userLogin.getDob()%>" name="dateofbirth"></td>
            </tr>
            <tr>
                <td>Số điện thoại</td>
                <td><input type="tel" value="<%=userLogin.getPhoneNumber()%>" name="phonenumber"></td>
            </tr>
        </table>
        <input type="submit" value="Cập nhật">

    </form>
</body>
</html>
