package Controller;

import DTO.CommentDTO;
import DTO.PostDetailDTO;
import Model.BO.CommentBO;
import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import Model.Bean.Comment;
import Model.Bean.Post;
import Model.Bean.SubSubject;
import Model.Bean.Subject;
import Model.Bean.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet({"/post/*"})
public class PostController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PostBO postBO;
    private CommentBO commentBO;
    private SubjectBO subjectBO;
    private SubSubjectBO subSubjectBO;
    @Override
    public void init() throws ServletException {
        postBO = new PostBO();
        commentBO = new CommentBO();
        subjectBO = new SubjectBO();
        subSubjectBO = new SubSubjectBO();
    }

    private void changeTo(String destination, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        User user = ((User) req.getSession().getAttribute("user"));
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<SubSubject> subSubjects = new ArrayList<>();
        String idPost = path.substring(path.indexOf("/") + 1);
        System.out.println(idPost);
        switch (idPost) {
            case "addpost":
            {
                if(user != null) {
                    subjects = subjectBO.getAllSubjects();
                    subSubjects = subSubjectBO.getAllSubjects();
                    req.setAttribute("subjects", subjects);
                    req.setAttribute("subSubjects", subSubjects);
                }
                changeTo("/addpost.jsp", req, resp);
                break;
            }
            default:
            {
                PostDetailDTO post = postBO.getPostByID(idPost);
                req.setAttribute("post", post);
                if(user != null) {
                    ArrayList<CommentDTO> usercomments = commentBO.getAllCommentsByUserID(user.getIdUser());
                    req.setAttribute("usercomments", usercomments);
                }
                HttpSession session = req.getSession();
                session.setAttribute("currentUrl", req.getRequestURI());
                changeTo("/PostDetail.jsp", req, resp);
                break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/post/addpost":
                String title = req.getParameter("title");
                String content = req.getParameter("content");
                String idSubSubject = req.getParameter("subsubject");
                User user = ((User) req.getSession().getAttribute("user"));
                Post post = new Post();
                post.setTitle(title);
                post.setIdSubSubject(idSubSubject);
                post.setIdUser(user.getIdUser());
                post.setContent(content);
                String uuid = postBO.addPost(post);
                resp.sendRedirect("post/" + uuid);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/home");
                break;
        }
    }
}
