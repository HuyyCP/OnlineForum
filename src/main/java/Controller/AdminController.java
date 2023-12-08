package Controller;

import DTO.SubjectStatDTO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import Model.Bean.Subject;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/admin"})
public class AdminController extends HttpServlet{
    private SubjectBO subjectBO;
    private SubSubjectBO subsubjectBO;

    private void changeTo(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() throws ServletException {
        subjectBO = new SubjectBO();
        subsubjectBO = new SubSubjectBO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<SubjectStatDTO> subjectStats = subjectBO.getSubjectsStat();
        req.setAttribute("subjectStatDTOs", subjectStats);
        ArrayList<Subject> subjects = subjectBO.getAllSubjects();
        req.setAttribute("subjects", subjects);
        changeTo("/admin.jsp", req, resp);
    }
}
