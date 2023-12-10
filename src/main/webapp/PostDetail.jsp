<%@ page import="Model.Bean.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Post" %>
<%@ page import="Model.Bean.Comment" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forum</title>
<%--    <link rel="stylesheet" href="../Content/Home/style.css">--%>
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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Post post = (Post) request.getAttribute("post");
        ArrayList<Comment> comments = (ArrayList<Comment>) request.getAttribute("comments");
        User user = (User) session.getAttribute("user");
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
                <li class="breadcrumb-item"><a href="/subject/<%=post.getIdSubSubject()%>/1"><%=post.getSubsubject().getSubjectName()%></a></li>
                <li class="breadcrumb-item active" aria-current="page"><%=post.getTitle()%></li>
            </ol>
        </nav>

        <div class="card mb-4">
            <div class="card-header"><%=post.getUser().getName()%></div>
            <div class="card-body">
                <h5 class="card-title">Topic: <%=post.getTitle()%></h5>
                <div class="d-flex align-items-center mb-3">
                    <div class="flex-shrink-0">
                        <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt=""
                             class="img-fluid rounded-circle" style="width: 60px; height: 60px;">
                    </div>
                    <div class="flex-grow-1 ms-3">
                        <%=post.getUser().getName()%> - <span class="text-muted"><%=post.getUser().getRole().getRoleName()%></span><br>
                        at <%=formatter.format(post.getDateCreated())%>
                    </div>
                </div>
                <p class="card-text"><%=post.getContent()%></p>
            </div>
        </div>


        <% for(Comment comment : comments) { %>
            <div class="card mb-3">
                <div class="card-body">
                    <%if(user != null && user.getIdUser().equals(comment.getUser().getIdUser())) { %>
                        <form method="POST" action="/comment/delete" style="position: absolute; top: 10px; right: 10px;">
                            <input type="hidden" value="<%=comment.getIdcomment()%>" name="idComment">
                            <input type="hidden" value="<%=post.getIdPost()%>" name="idPost">
                            <button class="btn btn-outline-danger btn-fw" type="submit"><i class="fa fa-trash-o" aria-hidden="true"></i></button>
                        </form>
                    <% } %>
                    <div class="d-flex align-items-center mb-3">
                        <div class="flex-shrink-0">
                            <img src="https://cdn.pixabay.com/photo/2015/11/06/13/27/ninja-1027877_960_720.jpg" alt=""
                                 class="img-fluid rounded-circle" style="width: 60px; height: 60px;">
                        </div>
                        <div class="flex-grow-1 ms-3">
                            <%=comment.getUser().getName()%> - <span class="text-muted"><%=comment.getUser().getRole().getRoleName()%></span><br>
                            at <%=formatter.format(comment.getDateComment())%>
                        </div>
                    </div>
                    <p class="card-text"><%=comment.getMessage()%></p>
                </div>
            </div>
        <% } %>

        <nav aria-label="Page navigation">
            <ul class="pagination justify-content-center">
                <%  int numPages =  (int)request.getAttribute("numPages");
                    for (int i = 1; i <= numPages; i++) { %>
                        <li class="page-item"><a class="page-link" href="/post/<%=post.getIdPost()%>/<%=i%>"><%=i%></a></li>
                <%  } %>
            </ul>
        </nav>

        <% if(user != null) { %>
            <form method="POST" action="/comment/create">
                <textarea name="message" id="message" placeholder="Comment here"></textarea>
                <input type="hidden" value="<%=post.getIdPost()%>" name="idPost">
                <input type="hidden" value="<%=user.getIdUser()%>" name="idUser">
                <button class="btn btn-primary mt-2" type="submit" value="" name="submitBtn">Bình luận</button>
            </form>
        <% } else { %>
                <button class="btn btn-outline-success" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">Đăng nhập để bình luận</button>
        <% } %>

    </div>
    <jsp:include page="footer.jsp"/>
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
</body>
</html>
