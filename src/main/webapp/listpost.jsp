<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Post" %>
<%@ page import="DTO.PostDTO" %>
<%@ page import="Model.Bean.SubSubject" %><%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 05-Dec-23
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <link rel="stylesheet" href="Content/Home/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap" rel="stylesheet">
</head>

<body>
<jsp:include page="header.jsp" />
<header>
    <!--NavBar Section-->
    <div class="navbar">
        <nav class="navigation hide" id="navigation">
            <span class="close-icon" id="close-icon" onclick="showIconBar()"><i class="fa fa-close"></i></span>
            <ul class="nav-list">
                <li class="nav-item"><a href="forums.html">Forums</a></li>
                <li class="nav-item"><a href="posts.html">Posts</a></li>
                <li class="nav-item"><a href="detail.html">Detail</a></li>
            </ul>
        </nav>
        <a class="bar-icon" id="iconBar" onclick="hideIconBar()"><i class="fa fa-bars"></i></a>
        <div class="brand">My Forum</div>
    </div>
    <!--SearchBox Section-->
    <div class="search-box">
        <div>
            <select name="" id="">
                <option value="Everything">Everything</option>
                <option value="Titles">Titles</option>
                <option value="Descriptions">Descriptions</option>
            </select>
            <input type="text" name="q" placeholder="search ...">
            <button><i class="fa fa-search"></i></button>
        </div>
    </div>
</header>
<div class="container">
    <%
        SubSubject subject = (SubSubject)request.getAttribute("subSubject");
    %>
    <!--Navigation-->
    <div class="navigate">
        <span><a href="MainController">MyForum - Forums</a> >> <%=subject.getSubjectName()%></span>
    </div>
    <!--Display posts table-->
    <div class="posts-table">
        <div class="table-head">
            <div class="status">Status</div>
            <div class="subjects">Subjects</div>
            <div class="replies">Replies/Views</div>
            <div class="last-reply">Last Reply</div>
        </div>
        <div class="table-row">
            <div class="status"><i class="fa fa-fire"></i></div>
            <div class="subjects">
                <a href="">Is learning Python on 2021 worth it?</a>
                <br>
                <span>Started by <b><a href="">User</a></b> .</span>
            </div>
            <div class="replies">
                2 replies <br> 125 views
            </div>

            <div class="last-reply date-create">
                Oct 12 2021
                <br>By <b><a href="">User</a></b>
            </div>
        </div>
        <!--starts here-->
        <%
            ArrayList<PostDTO> listPost = (ArrayList<PostDTO>) request.getAttribute("listPost");
            for (int i = 0; i < listPost.size(); i++) {
        %>
        <div class="table-row">
            <div class="status"><i class="fa fa-fire"></i></div>
            <div class="subjects">
                <a id="idpost" href="/post/<%=listPost.get(i).getIdPost()%>"><%=listPost.get(i).getTitle()%></a>
                <br>
                <span>Started by <b><a href=""><%=listPost.get(i).getMemberName()%></a></b> .</span>
            </div>
            <div class="replies">
                <%=listPost.get(i).getNumComments()%> replies
            </div>

            <div class="last-reply date-create">
                <%=listPost.get(i).getDateCreated()%>
                <br>By <b><a href="">User</a></b>
            </div>
        </div>

        <%
            }
        %>

        <!--ends here-->
    </div>
    <!--Pagination starts-->
    <%
        int numPage = (int) request.getAttribute("numPages");
    %>
    <div class="pagination">
        pages:
        <%
            for (int i = 1; i <= numPage; i++) {
        %>
            <a href="/subject/<%=subject.getIdSubject()%>/<%=i%>"><%=i%></a>
        <%
            }
        %>
    </div>
    <!--pagination ends-->
</div>

<div class="note">
    <span><i class="fa fa-frown-o"></i>&nbsp; 0 Engagement Topic</span>&nbsp;&nbsp;&nbsp;<a href=""><i
        class="fa fa-share-square"></i></a><br>
    <span><i class="fa fa-book"></i>&nbsp; Low Engagement Topic</span>&nbsp;&nbsp;&nbsp;<a href=""><i
        class="fa fa-share-square"></i></a><br>
    <span><i class="fa fa-fire"></i>&nbsp; Popular Topic</span>&nbsp;&nbsp;&nbsp;<a href=""><i
        class="fa fa-share-square"></i></a><br>
    <span><i class="fa fa-rocket"></i>&nbsp; High Engagement Topic</span>&nbsp;&nbsp;&nbsp;<a href=""><i
        class="fa fa-share-square"></i></a><br>
    <span><i class="fa fa-lock"></i>&nbsp; Closed Topic</span>&nbsp;&nbsp;&nbsp;<a href=""><i
        class="fa fa-share-square"></i></a><br>
</div>
<jsp:include page="alert.jsp" />
<footer>
    <span>&copy;  Selmi Abderrahim | All Rights Reserved</span>
</footer>
<script src="main.js"></script>
</body>
</html>
