<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Subject" %>
<%@ page import="Model.Bean.SubSubject" %>
<%@ page import="DTO.PostDTO" %>
<%@ page import="static Helper.Util.timeAgo" %><%--
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
    <style>
      .post-title {
        max-width: 300px;
      }
    </style>
</head>
<jsp:include page="header.jsp"/>
<body>
<%
    ArrayList<Subject> listMainjects = (ArrayList<Subject>) request.getAttribute("listMainSubject");
    ArrayList<SubSubject> listSubSubject = (ArrayList<SubSubject>) request.getAttribute("listSubSubject");
    ArrayList<PostDTO> lastestPosts = (ArrayList<PostDTO>) request.getAttribute("lastestPost");
%>
<div class="container my-4">
    <div class="my-3">

    </div>
    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
        <% if (session.getAttribute("user") != null) { %>
        <a href="/post/addpost">
            <button class="btn btn-primary" type="button">Bài viết mới</button>
        </a>
        <% } else { %>
        <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">Đăng
            nhập để đăng bài viết
        </button>
        <% } %>
    </div>
    <div class="row">
        <%
            for (int i = 0; i < listMainjects.size(); i++) {
        %>
        <div class="col-lg-10">
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
                                <i class="fa fa-comments-o fa-2x"></i>
                            </div>
                            <div class="col-md-5">
                                <h5>
                                    <a href="subject/<%=listSubSubject.get(j).getIdSubject()%>/1"><%=listSubSubject.get(j).getSubjectName()%>
                                    </a></h5>
                            </div>
                            <div class="col-md-2 text-center">
                                <span> 2K Posts</span>
                            </div>
                            <div class="col-md-4">
                                <%
                                    boolean check = true;
                                    for (int k = 0; k < lastestPosts.size(); k++) {
                                        if (lastestPosts.get(k).getIdSubSubject().equals(listSubSubject.get(j).getIdSubject())) {
                                %>
                                <div class="post-info">
                                    <a href="/post/<%= lastestPosts.get(k).getIdPost() %>/1" class="text-truncate d-block post-title">
                                        <strong><%= lastestPosts.get(k).getTitle() %></strong>
                                    </a>
                                    <div class="post-author small text-muted">
                                        by <%= lastestPosts.get(k).getUser().getName() %>
                                        on <%= timeAgo(lastestPosts.get(k).getDateCreated()) %>
                                    </div>
                                </div>
                                <%
                                            check = false;
                                            break;
                                        }
                                    }
                                    if (check) {
                                %>
                                <small> No post available </small>
                                <%
                                    }
                                %>
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
</div>

<jsp:include page="footer.jsp"/>

<!-- Forum Info -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

<jsp:include page="alert.jsp"/>
</html>