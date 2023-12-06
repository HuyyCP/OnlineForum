package Controller;

import Model.BO.CommentBO;
import Model.Bean.Comment;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/comment/create",
            "/comment/update",
            "/comment/delete"})
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentBO commentBO;

    @Override
    public void init() {
        commentBO = new CommentBO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/comment/create": {
                addComment(req, resp);
                break;
            }
            case "/comment/update": {
                updateComment(req, resp);
                break;
            }
            case "/comment/delete": {
                deleteComment(req, resp);
                break;
            }
        }

    }

    private void addComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String message = req.getParameter("message");
        String idPost = req.getParameter("idPost");
        String idUser = req.getParameter("idUser");
        Comment comment = new Comment();
        comment.setMessage(message);
        comment.setIdPost(idPost);
        comment.setIdUser(idUser);
        commentBO.addComment(comment);
        resp.sendRedirect("/post/" + idPost);
    }

    private void updateComment(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void deleteComment(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String idComment = req.getParameter("idComment");
        String idPost = req.getParameter("idPost");
        commentBO.deleteComment(idComment);
        resp.sendRedirect("/post/" + idPost);
    }

}
