<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Bean.Subject" %>
<%@ page import="Model.Bean.SubSubject" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 12/8/2023
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bài Viết</title>
    <script src="https://cdn.tiny.cloud/1/c4fmgrmgp8fkwyp0lolouja3bmiwpuunavq2k69gc7bcbl9h/tinymce/6/tinymce.min.js" referrerpolicy="origin"></script>
</head>
<body>
<jsp:include page="header.jsp"/>

<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("/home");
    }

    ArrayList<Subject> subjects = (ArrayList<Subject>) request.getAttribute("subjects");
    ArrayList<SubSubject> subSubjects = (ArrayList<SubSubject>) request.getAttribute("subSubjects");
%>
<div class="container mt-5">
    <h2>Add Post</h2>
    <form action="/post/addpost" method="POST">
        <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>
        <div class="mb-3">
            <label for="content" class="form-label">Content</label>
            <textarea class="form-control" id="content" name="content"></textarea>
        </div>

        <div class="mb-3">
            <label for="subject" class="form-label">Subject</label>
            <select class="form-select" id="subject" name="subject" required>
                <option value="">Select a subject</option>
                <% for (Subject subject : subjects) { %>
                <option value="<%= subject.getIdSubject() %>"><%= subject.getSubjectName() %></option>
                <% } %>
            </select>
        </div>
        <div class="mb-3">
            <label for="subsubject" class="form-label">Sub Subject</label>
            <select class="form-select" id="subsubject" name="subsubject" required>
                <option value="">Select a sub subject</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Add Post</button>
    </form>
</div>

<script>
  tinymce.init({
    selector: '#content',
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

<script>
  const subSubjects = [
    <% for (SubSubject subSubject : subSubjects) { %>
    {
      "idSubSubject": "<%= subSubject.getIdSubject() %>",
      "subSubjectName": "<%= subSubject.getSubjectName() %>",
      "idParentSubject": "<%= subSubject.getIdParentSubject() %>"
    }<% if (subSubjects.indexOf(subSubject) < subSubjects.size() - 1) { %>,<% } %>
    <% } %>
  ];
</script>

<script>
  document.addEventListener("DOMContentLoaded", function () {
    const subjectDropdown = document.getElementById("subject");
    const subSubjectDropdown = document.getElementById("subsubject");

    subjectDropdown.addEventListener("change", function () {
      const selectedSubject = subjectDropdown.value;

      const filteredSubSubjects = subSubjects.filter(subSubject => subSubject.idParentSubject === selectedSubject);

      subSubjectDropdown.innerHTML = '<option value="">Select a sub subject</option>';

      filteredSubSubjects.forEach((subSubject) => {
        const option = document.createElement("option");
        option.value = subSubject.idSubSubject;
        option.textContent = subSubject.subSubjectName;
        subSubjectDropdown.appendChild(option);
      });
    });
  });
</script>

</body>
</html>
