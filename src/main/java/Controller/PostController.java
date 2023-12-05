package Controller;

import DTO.PostDetailDTO;
import Model.BO.CommentBO;
import Model.BO.PostBO;
import Model.Bean.Comment;
import Model.Bean.Post;
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
    @Override
    public void init() throws ServletException {
        postBO = new PostBO();
        commentBO = new CommentBO();
    }

    private void changeTo(String destination, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(destination);
        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        String idPost = path.substring(1);
        PostDetailDTO post = postBO.getPostByID(idPost);
        req.setAttribute("post", post);
        HttpSession session = req.getSession();
        session.setAttribute("currentUrl", req.getRequestURI());
        changeTo("/PostDetail.jsp", req, resp);
    }
}
