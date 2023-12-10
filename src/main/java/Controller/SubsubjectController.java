package Controller;

import DTO.SubjectStatDTO;
import Model.BO.SubSubjectBO;
import Model.BO.SubjectBO;
import Model.Bean.Subject;
import com.google.gson.Gson;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet({"/subsubject/*"})
public class SubsubjectController extends HttpServlet {
    private SubSubjectBO subsubjectBO;
    private SubjectBO subjectBO;

    @Override
    public void init() throws ServletException {
        subjectBO = new SubjectBO();
        subsubjectBO = new SubSubjectBO();
    }

    private void changeTo(String url, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
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
        switch (path) {
            case "/count" : {
                replyBack(resp, subsubjectBO.getNumSubsubject());
                break;
            }
            case "/best" : {
                replyBack(resp, subsubjectBO.getBestSubject());
                break;
            }
            default : {
                String idSubsubject = path.substring(1);
                ArrayList<SubjectStatDTO> subjectStatDTOs = subsubjectBO.getSubSubjectsStat(idSubsubject);
                replyBack(resp, subjectStatDTOs);
            }
        }

    }
}
