package Controller;

import java.io.*;

import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "MainController", urlPatterns = {"/home", "/index.jsp", "/listpost", ""})
public class MainController extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getServletPath());
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("IDSubSubject") == null){
            SubjectBO subjectBO = new SubjectBO();
            request.setAttribute("listMainSubject", subjectBO.getAllSubjects());
            SubSubjectBO subSubjectBO = new SubSubjectBO();
            request.setAttribute("listSubSubject", subSubjectBO.getAllSubjects());
            changeTo("/home.jsp", request, response);
        }else{
            PostBO postBO = new PostBO();

            request.setAttribute("listPost", postBO.getAllPostsBySubjectID(request.getParameter("IDSubSubject")));
            SubSubjectBO subSubjectBO = new SubSubjectBO();
            request.setAttribute("subSubject", subSubjectBO.getSubject(request.getParameter("IDSubSubject")));
            changeTo("/listpost.jsp", request, response);
        }
    }

    private void changeTo(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void destroy() {
    }
}