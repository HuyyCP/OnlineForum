package Controller;

import Model.BO.UserBO;
import Model.Bean.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet({"/user/*", "/user/update/*"})
public class UserController extends HttpServlet {

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


    private UserBO userBO = new UserBO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/user/update": {
                updateProfile(req, resp);
                break;
            }
            case "/user": {
                showProfile(req, resp);
                break;
            }
        }
    }

    private void updateProfile(HttpServletRequest req, HttpServletResponse resp) {
        try {
            User updatedUser = new User();
            String idUser = req.getPathInfo().substring(1);

            updatedUser.setIdUser(idUser);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            updatedUser.setDob(sdf.parse(req.getParameter("dateofbirth")));
            updatedUser.setName(req.getParameter("name"));
            updatedUser.setPhoneNumber(req.getParameter("phonenumber"));
            updatedUser.setEmail(req.getParameter("email"));

            userBO.updateUser(updatedUser);

            req.getSession().setAttribute("user", userBO.getUserById(idUser));
            resp.sendRedirect("../../user/ " + idUser);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showProfile(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        changeTo("/profile.jsp", req, resp);
    }
}
