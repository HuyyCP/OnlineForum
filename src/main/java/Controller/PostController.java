package Controller;

import Model.BO.CommentBO;
import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import Model.Bean.Comment;
import Model.Bean.Post;
import Model.Bean.SubSubject;
import Model.Bean.Subject;
import Model.Bean.User;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

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

    private void replyBack(HttpServletResponse resp, Object data) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(data);
        resp.setContentType("application/json");
        resp.getWriter().write(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        User user = (User) req.getSession().getAttribute("user");
        ArrayList<Subject> subjects = new ArrayList<>();
        ArrayList<SubSubject> subSubjects = new ArrayList<>();
        String param = path.substring(path.indexOf("/") + 1);
        System.out.println(param);
        switch (param) {
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
            case "count" : {
                replyBack(resp, postBO.getNumPost());
                break;
            }
            case "best": {
                replyBack(resp, postBO.getBestPost());
                break;
            }
            case "delete": {
                User userDelete = (User) req.getSession().getAttribute("user");
                if (userDelete == null) {
                    resp.sendRedirect(req.getContextPath() + "/home");
                    return;
                }

                String idPostDelete = req.getParameter("idPost");

                if (idPostDelete == null) {
                    resp.sendRedirect(req.getContextPath() + "/home");
                    return;
                }

                Post post = postBO.getPostByID(idPostDelete);

                if (!userDelete.getIdUser().equals(post.getIdUser())) {
                    resp.sendRedirect(req.getContextPath() + "/home");
                    return;
                }

                postBO.deletePost(idPostDelete);
                resp.sendRedirect(req.getContextPath() + "/home");
                break;
            }
            default:
            {
                String[] params = param.split("/");
                String idPost = params[0];
                int pageIndex = Integer.parseInt( params[1]);
                Post post = postBO.getPostByID(idPost);
                req.setAttribute("post", post);
                ArrayList<Comment> comments = user != null ? commentBO.getAllCommentsByUserID(idPost, user.getIdUser(), 10, pageIndex) : commentBO.getAllCommentsByPostID(idPost, 10, pageIndex);
                req.setAttribute("comments", comments);
                req.setAttribute("numPages", (int)Math.ceil(((double)commentBO.getAmountCommentsByPostID(idPost)) / 10));
                HttpSession session = req.getSession();
                session.setAttribute("currentUrl", req.getRequestURI());
                changeTo("/PostDetail.jsp", req, resp);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        switch (path) {
            case "/addpost":
                System.out.println("addpost");
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
                System.out.println(uuid);
                resp.sendRedirect("post/" + uuid + "/1");
                break;
            case "/editpost":
                String idPost = req.getParameter("idPost");
                String titleEdit = req.getParameter("titleEdit");
                String contentEdit = req.getParameter("editContent");
                Post postEdit = postBO.getPostByID(idPost);
                postEdit.setTitle(titleEdit);
                postEdit.setContent(contentEdit);
                postBO.updatePost(postEdit);
                resp.sendRedirect(req.getContextPath() + "/post/" + idPost + "/1");
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/home");
                break;
        }
    }
}
