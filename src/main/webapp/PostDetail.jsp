<%@ page import="DTO.PostDetailDTO" %>
<%@ page import="DTO.CommentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <header>
    </header>
    <%
        PostDetailDTO post = (PostDetailDTO) request.getAttribute("post");
    %>
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
            <select name="" id="selectsubject">
                <option value="Everything">Everything</option>
                <option value="Titles">Titles</option>
                <option value="Descriptions">Descriptions</option>
            </select>
            <input type="text" name="q" placeholder="search ...">
            <button><i class="fa fa-search"></i></button>
        </div>
    </div>
    <div class="container">
        <!--Navigation-->
        <div class="navigate">
            <span><a href="">MyForum - Forums</a> >> <a href=""><%=post.getSubsubjectName()%></a> >> <a href="<%=post.getIdPost()%>"><%=post.getTitle()%></a></span>
        </div>

        <!--Topic Section-->
        <div class="topic-container">
            <!--Original thread-->
            <div class="head">
                <div class="authors"></div>
                <div class="content"><h1><%=post.getTitle()%></h1></div>
            </div>

            <div class="body">
                <div class="authors">
                    <div class="username"><a href="">Username</a></div>
                    <div>Role</div>
                    <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt="">
                    <div>Posts: <u>45</u></div>
                    <div>Points: <u>4586</u></div>
                </div>
                <div class="content">
                    Just a random content of a random topic.
                    <br>To see how it looks like.
                    <br><br>
                    Nothing more and nothing less.
                    <br>
                    <hr>
                    Regards username

                </div>
            </div>
        </div>

        <!--Comment Area-->
        <div class="comment-area hide" id="comment-area">
            <textarea name="comment" id="commentarea" placeholder="comment here ... "></textarea>
            <input type="submit" value="submit">
        </div>

        <!--Comments Section-->
        <%
            for(CommentDTO comment : post.getCommentDTOs()) {
        %>
            <div class="comments-container">
                <div class="body">
                    <div class="authors">
                        <div class="username"><a href=""><%=comment.getUser().getName()%></a></div>
                        <div>Role</div>
                        <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt="">
                        <div>Posts: <u>455</u></div>
                        <div>Points: <u>4586</u></div>
                    </div>
                    <div class="content">
                        <%=comment.getMessage()%>

                    </div>
                </div>
            </div>
        <%
            }
        %>
        <!--Reply Area-->
        <div class="comment-area hide" id="reply-area">
            <textarea name="reply" id="replyarea" placeholder="reply here ... "></textarea>
            <input type="submit" value="submit">
        </div>





    </div>
    <footer>
        <span>&copy;  Selmi Abderrahim | All Rights Reserved</span>
    </footer>
<%--    <script src="main.js"></script>--%>
</body>
</html>
