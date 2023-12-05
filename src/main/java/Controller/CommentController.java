package Controller;

import Model.BO.CommentBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/comment/*"})
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentBO commentBO;

    @Override
    public void init() {
        commentBO = new CommentBO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

    }
}
