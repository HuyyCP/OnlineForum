<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Post" %>
<%@ page import="DTO.PostDTO" %>
<%@ page import="Model.Bean.SubSubject" %>
<%@ page import="DTO.CommentDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<jsp:include page="header.jsp"/>
<body>
<%
    SubSubject subject = (SubSubject) request.getAttribute("subSubject");
    ArrayList<CommentDTO> lastComment = (ArrayList<CommentDTO>) request.getAttribute("lastComments");
%>
<main class="container my-4">
    <div class="row">
        <div class="col-md-8">
            <div class="mb-3">
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
            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                <% if (session.getAttribute("user") != null) { %>
                <a href="/post/addpost">
                    <button class="btn btn-primary" type="button">Bài viết mới</button>
                </a>
                <% } else { %>
                <button class="btn btn-outline-success" type="button" data-bs-toggle="modal"
                        data-bs-target="#loginModal">Đăng nhập để đăng bài viết
                </button>
                <% } %>
            </div>

            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active"
                        aria-current="/subject/<%=subject.getIdSubject()%>/1"><%=subject.getSubjectName()%>
                    </li>
                </ol>
            </nav>

            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Status</th>
                        <th scope="col">Subjects</th>
                        <th scope="col">Replies/Views</th>
                        <th scope="col">Last Reply</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Repeat for each post -->
                    <%
                        ArrayList<PostDTO> listPost = (ArrayList<PostDTO>) request.getAttribute("listPost");
                        for (int i = 0; i < listPost.size(); i++) {
                    %>
                    <tr>
                        <td><i class="fa fa-fire"></i></td>
                        <td><a href="/post/<%=listPost.get(i).getIdPost()%>"><%=listPost.get(i).getTitle()%>
                        </a><br><small>Started by <a
                                href="#"><%=listPost.get(i).getMemberName()%>
                        </a></small></td>
                        <td><%=listPost.get(i).getNumComments()%> replies</td>
                        <%
                            boolean check = true;
                            for (int j = 0; j < lastComment.size(); j++) {
                                if (lastComment.get(j).getIdPost().equals(listPost.get(i).getIdPost())) {
                        %>
                        <td><%=lastComment.get(j).getDateComment()%><br>By <a
                                href="#"><%=lastComment.get(j).getUser().getName()%>
                        </a></td>
                        <%
                                    check = false;
                                }
                            }

                            if (check) {
                        %>
                        <td>No comment available</td>
                        <%
                            }


                        %>

                    </tr>
                    <%
                        }
                    %>
                    <!-- More rows here -->
                    </tbody>
                </table>
            </div>

            <nav aria-label="Page navigation">
                <ul class="pagination justify-content-center">
                    <%
                        int numPages = (int) request.getAttribute("numPages");

                        for (int i = 1; i <= (int) numPages; i++) {
                    %>
                    <li class="page-item"><a class="page-link" href="/subject/<%=subject.getIdSubject()%>/<%=i%>"><%=i%>
                        ></a></li>
                    <%
                        }
                    %>
                </ul>
            </nav>
        </div>

        <%--        <div class="col-md-4">--%>
        <%--            <div class="mb-4">--%>
        <%--                <h5>Popular Topics</h5>--%>
        <%--                <ul class="list-group">--%>
        <%--                    <!-- Repeat for each popular topic -->--%>
        <%--                    <li class="list-group-item">--%>
        <%--                        <a href="#">Topic Title</a>--%>
        <%--                    </li>--%>
        <%--                    <!-- More items here -->--%>
        <%--                </ul>--%>
        <%--            </div>--%>
        <%--        </div>--%>
    </div>
</main>

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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>

<jsp:include page="alert.jsp"/>

</html>