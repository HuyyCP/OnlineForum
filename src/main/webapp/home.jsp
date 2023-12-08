<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Subject" %>
<%@ page import="Model.Bean.SubSubject" %><%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 04-Dec-23
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<jsp:include page="header.jsp"/>
<body>
<%
    ArrayList<Subject> listMainjects = (ArrayList<Subject>) request.getAttribute("listMainSubject");
    ArrayList<SubSubject> listSubSubject = (ArrayList<SubSubject>) request.getAttribute("listSubSubject");
%>
<div class="container my-4">
    <div class="container mt-3">
        <div class="input-group">
            <select class="form-select" id="inputGroupSelect04">
                <option selected>Everything</option>
                <option value="1">Titles</option>
                <option value="2">Descriptions</option>
            </select>
            <input type="text" class="form-control" placeholder="search ...">
            <button class="btn btn-outline-secondary" type="button"><i class="fa fa-search"></i></button>
        </div>
    </div>
    <div class="row">

        <%
            for (int i = 0; i < listMainjects.size(); i++) {
        %>
        <div class="col-lg-8">
            <div class="card mb-3">
                <div class="card-header">
                    <h4><%=listMainjects.get(i).getSubjectName()%>
                    </h4>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <div class="row">
                            <%
                                for (int j = 0; j < listSubSubject.size(); j++) {
                                    if (listMainjects.get(i).getIdSubject().equals(listSubSubject.get(j).getIdParentSubject())) {
                            %>
                            <div class="col-md-1 text-center">
                                <i class="fa fa-car fa-2x"></i>
                            </div>
                            <div class="col-md-7">
                                <h5><a href="subject/<%=listSubSubject.get(j).getIdSubject()%>/1"><%=listSubSubject.get(j).getSubjectName()%></a></h5>
                            </div>
                            <div class="col-md-2 text-center">
                                <span> Posts</span><br>
                                <span>12 Topics</span>
                            </div>
                            <div class="col-md-2">
                                <small><b>Last post:</b> by User<br>on 12 Dec 2020</small>
                            </div>
                            <%
                                    }
                                }
                            %>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
        <%
            }
        %>


    </div>
    <!-- Forum Sections -->


    <!-- New Topic Button -->
    <!-- <div class="d-grid gap-2 d-md-flex justify-content-md-end">
      <button class="btn btn-primary" type="button">New Topic</button>
    </div> -->
</div>

<footer class="bg-dark text-white text-center text-lg-start">
    <div class="container p-4">
        <h5 class="text-uppercase mb-4">MyForum - Stats <i class="fa fa-bar-chart"></i></h5>
        <span>5,369 Posts in 48 Topics by 8,124 Members. Latest post: <a href="#" class="text-white"><b>Random
            post</b></a> on Dec 15 2021 By <a href="#" class="text-white">RandomUser</a></span>
    </div>
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        &copy; Selmi Abderrahim | All Rights Reserved
    </div>
</footer>

<!-- Forum Info -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

<jsp:include page="alert.jsp"/>
</html>