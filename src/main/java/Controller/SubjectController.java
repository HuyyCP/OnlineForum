package Controller;

import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet({"/subject/*"})
public class SubjectController extends HttpServlet {
    private void changeTo(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo().trim();

        String[] params = path.split("/");
        for (int i = 0; i < params.length; i++) {
            System.out.println(params[i]);
        }

        String IDSubSubject = params[1];
        Integer index = Integer.parseInt(params[2]);

        PostBO postBO = new PostBO();
        req.setAttribute("listPost", postBO.getPostsPaging(IDSubSubject, 10, index));
        req.setAttribute("numPages", (int)Math.ceil((double) postBO.getNumPost(IDSubSubject) / 10));
        SubSubjectBO subSubjectBO = new SubSubjectBO();
        req.setAttribute("subSubject", subSubjectBO.getSubject(IDSubSubject));
        changeTo("/listpost.jsp", req, resp);
    }
}
