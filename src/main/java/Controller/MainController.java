package Controller;

import java.io.*;

import Model.BO.PostBO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "MainController", urlPatterns = {"/home", "/", "/listpost", ""})
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
            PostBO postBO = new PostBO();
            request.setAttribute("listMainSubject", subjectBO.getAllSubjects());
            SubSubjectBO subSubjectBO = new SubSubjectBO();
            request.setAttribute("listSubSubject", subSubjectBO.getAllSubjects());
            request.setAttribute("lastestPost", postBO.getLastPostForEachSubSubjects());
            HttpSession session = request.getSession();
            session.setAttribute("currentUrl", request.getRequestURI());
            changeTo("/home.jsp", request, response);
        }else{
            PostBO postBO = new PostBO();
            request.setAttribute("listPost", postBO.getAllPostsBySubjectID(request.getParameter("IDSubSubject")));
            SubSubjectBO subSubjectBO = new SubSubjectBO();
            request.setAttribute("subSubject", subSubjectBO.getSubject(request.getParameter("IDSubSubject")));


            HttpSession session = request.getSession();
            session.setAttribute("currentUrl", request.getRequestURI());
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