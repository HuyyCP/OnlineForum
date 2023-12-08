<%@ page import="DTO.PostDetailDTO" %>
<%@ page import="DTO.CommentDTO" %>
<%@ page import="Model.Bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
    <link rel="stylesheet" href="../Content/Home/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Titillium+Web:ital@1&display=swap" rel="stylesheet">
    <script src="https://cdn.tiny.cloud/1/7d8d09n9dap8sawy56988twh75m0v9ld337u9abi70mpc3f3/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>

<body>
<jsp:include page="header.jsp" />
<jsp:include page="alert.jsp" />
    <header>
    </header>
    <%
        PostDetailDTO post = (PostDetailDTO) request.getAttribute("post");
        ArrayList<CommentDTO> usercomments = (ArrayList<CommentDTO>) request.getAttribute("usercomments");
    %>
    <div class="container my-4">
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
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">MyForum - Forums</a></li>
                <li class="breadcrumb-item"><a href="/subject/<%=post.getIdSubSubject()%>/1"><%=post.getSubsubjectName()%></a></li>
                <li class="breadcrumb-item active" aria-current="page"><a href="/post/<%=post.getIdPost()%>"><%=post.getTitle()%></a></li>
            </ol>
        </nav>
<%--    <div class="container">--%>
        <!--Navigation-->
<%--        <div class="navigate">--%>
<%--            <span><a href="/">MyForum - Forums</a> >> <a href="/subject/<%=post.getIdSubSubject()%>/1"><%=post.getSubsubjectName()%></a> >> <a href="/post/<%=post.getIdPost()%>"><%=post.getTitle()%></a></span>--%>
<%--        </div>--%>


        <div class="card mb-4">
            <div class="card-header">Author</div>
            <div class="card-body">
                <h5 class="card-title">Topic: <%=post.getTitle()%></h5>
                <div class="d-flex align-items-center mb-3">
                    <div class="flex-shrink-0">
                        <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt=""
                             class="img-fluid rounded-circle" style="width: 60px; height: 60px;">
                    </div>
                    <div class="flex-grow-1 ms-3">
                        Username - <span class="text-muted">Role</span><br>
                        Posts: 45 | Points: 4586
                    </div>
                </div>
<%--                <p class="card-text">Just a random content of a random topic.</p>--%>
<%--                <button class="btn btn-primary" onclick="showComment()">Comment</button>--%>
            </div>
        </div>
        <!--Topic Section-->
<%--        <div class="topic-container">--%>
<%--            <!--Original thread-->--%>
<%--            <div class="head">--%>
<%--                <div class="authors"></div>--%>
<%--                <div class="content"><span style="font-size:30px"><%=post.getTitle()%></span></div>--%>
<%--            </div>--%>
<%--        </div>--%>

        <%
            User user = (User) session.getAttribute("user");
            if(user != null) {
        %>
                <div name="usercomment" class="comments-container">
        <%
                for(CommentDTO comment : usercomments) {
        %>
                    <div class="card mb-3">
                        <div class="card-body">
                            <div class="d-flex align-items-center mb-3">
                                <div class="flex-shrink-0">
                                    <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt=""
                                         class="img-fluid rounded-circle" style="width: 60px; height: 60px;">
                                </div>
                                <div class="flex-grow-1 ms-3">
                                    <%=comment.getUser().getName()%> - <span class="text-muted">Role></span><br>
                                    Posts: 455 | Points: 4586
                                </div>
                            </div>
                            <p class="card-text"><%=comment.getMessage()%></p>
                            <div style="position: relative; top:5px; right: 5px">
                                <form method="POST" action="/comment/delete">
                                    <input type="hidden" value="<%=comment.getIdcomment()%>" name="idComment">
                                    <input type="hidden" value="<%=post.getIdPost()%>" name="idPost">
                                    <button type="submit"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                                </form>
                            </div>
<%--                            <button class="btn btn-secondary" onclick="showReply()">Reply</button>--%>
                        </div>
                    </div>
<%--                    <div class="body">--%>
<%--                        <div class="authors">--%>
<%--                            <div class="username"><a href=""><%=comment.getUser().getName()%></a></div>--%>
<%--                        </div>--%>
<%--                        <div class="content"><%=comment.getMessage()%></div>--%>
<%--                        <form method="POST" action="/comment/delete">--%>
<%--                            <input type="hidden" value="<%=comment.getIdcomment()%>" name="idComment">--%>
<%--                            <input type="hidden" value="<%=post.getIdPost()%>" name="idPost">--%>
<%--                            <button type="submit"><i class="fa fa-trash-o" aria-hidden="true"></i></button>--%>
<%--                        </form>--%>
<%--                    </div>--%>
        <%
                }
        %>
                </div>
        <%
            }
        %>


        <%
            for(CommentDTO comment : post.getCommentDTOs()) {
        %>
        <div class="card mb-3">
            <div class="card-body">
                <div class="d-flex align-items-center mb-3">
                    <div class="flex-shrink-0">
                        <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt=""
                             class="img-fluid rounded-circle" style="width: 60px; height: 60px;">
                    </div>
                    <div class="flex-grow-1 ms-3">
                        <%=comment.getUser().getName()%> - <span class="text-muted">Role</span><br>
                        Posts: 455 | Points: 4586
                    </div>
                </div>
                <p class="card-text"><%=comment.getMessage()%></p>
<%--                <button class="btn btn-secondary" onclick="showReply()">Reply</button>--%>
            </div>
        </div>
        <%
            }
        %>


        <%

            if(user != null) {
        %>
                <form method="POST" action="/comment/create">
                    <textarea name="message" id="message" placeholder="Comment here"></textarea>
                    <script>
                        tinymce.init({
                            selector: 'textarea#message',
                            plugins: 'ai tinycomments mentions anchor autolink charmap codesample emoticons image link lists media searchreplace table visualblocks wordcount checklist mediaembed casechange export formatpainter pageembed permanentpen footnotes advtemplate advtable advcode editimage tableofcontents mergetags powerpaste tinymcespellchecker autocorrect a11ychecker typography inlinecss',
                            toolbar: 'undo redo | blocks fontfamily fontsize | bold italic underline strikethrough | link image media table mergetags | align lineheight | tinycomments | checklist numlist bullist indent outdent | emoticons charmap | removeformat',
                            tinycomments_mode: 'embedded',
                            tinycomments_author: 'Author name',
                            mergetags_list: [
                                { value: 'First.Name', title: 'First Name' },
                                { value: 'Email', title: 'Email' },
                            ],
                            ai_request: (request, respondWith) => respondWith.string(() => Promise.reject("See docs to implement AI Assistant")),
                        });
                    </script>
                    <input type="hidden" value="<%=post.getIdPost()%>" name="idPost">
                    <input type="hidden" value="<%=user.getIdUser()%>" name="idUser">
                    <button type="submit" value="" name="submitBtn">Bình luận</button>
                </form>
        <%
            } else {
        %>
                <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">Đăng nhập để bình luận</button>

        <%
            }
        %>

    </div>
    <footer>
        <span>&copy;  Selmi Abderrahim | All Rights Reserved</span>
    </footer>
</body>
</html>
