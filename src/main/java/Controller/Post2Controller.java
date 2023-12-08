package Controller;

import DTO.CommentDTO;
import DTO.PostDetailDTO;
import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import Model.Bean.Post;
import Model.Bean.SubSubject;
import Model.Bean.Subject;
import Model.Bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/addpost", "/editpost", "/deletepost"})
public class Post2Controller extends HttpServlet {
  private PostBO postBO;
  private SubjectBO subjectBO;
  private SubSubjectBO subSubjectBO;
  @Override
  public void init() throws ServletException {
    postBO = new PostBO();
    subjectBO = new SubjectBO();
    subSubjectBO = new SubSubjectBO();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    ArrayList<Subject> subjects = new ArrayList<>();
    ArrayList<SubSubject> subSubjects = new ArrayList<>();
    User user = ((User) req.getSession().getAttribute("user"));
    if(user != null) {
      subjects = subjectBO.getAllSubjects();
      subSubjects = subSubjectBO.getAllSubjects();
    }

    req.setAttribute("subjects", subjects);
    req.setAttribute("subSubjects", subSubjects);
    req.setAttribute("currentUrl", req.getRequestURI());
    System.out.println(path);
    switch (path) {
      case "/addpost":
        req.getRequestDispatcher("/addpost.jsp").forward(req, resp);
        break;
      case "/editpost":
        String idPost = req.getParameter("idPost");
        PostDetailDTO post = postBO.getPostByID(idPost);
        req.setAttribute("post", post);
        req.getRequestDispatcher("/EditPost.jsp").forward(req, resp);
        break;
      case "/deletepost":
        String idPost2 = req.getParameter("idPost");
        postBO.deletePost(idPost2);
        resp.sendRedirect(req.getContextPath() + "/home");
        break;
      default:
        resp.sendRedirect(req.getContextPath() + "/home");
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    switch (path) {
      case "/addpost":
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String idSubSubject = req.getParameter("subsubject");
        User user = ((User) req.getSession().getAttribute("user"));
        Post post = new Post();
        post.setTitle(title);
        post.setIdSubSubject(idSubSubject);
        post.setIdUser(user.getIdUser());
        post.setContent(content);
//        System.out.println(content);
        String uuid = postBO.addPost(post);
        resp.sendRedirect("post/" + uuid);
        break;
      default:
        resp.sendRedirect(req.getContextPath() + "/home");
        break;
    }
  }

}
