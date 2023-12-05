package Controller;


import Model.BO.AccountBO;
import Model.BO.UserBO;
import Model.Bean.Account;
import Model.Bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@WebServlet(name = "AccountController", urlPatterns = {"/login", "/register", "/logout"})
public class AccountController extends HttpServlet {

  UserBO UserBO = new UserBO();
  AccountBO AccountBO = new AccountBO();

  String redirectURL = "";

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    switch (path) {
      case "/logout":
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        session.removeAttribute("loginStatus");
        resp.sendRedirect("/");
        break;
      default:
        break;
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String path = req.getServletPath();
    redirectURL = req.getParameter("redirect");
    if(redirectURL != null && !redirectURL.isEmpty()){
        redirectURL = redirectURL.replace(".jsp", "");
    }
    System.out.println(redirectURL);
    if (redirectURL == null || redirectURL.isEmpty()) {
      redirectURL = "/";
    }
    switch (path) {
      case "/login":
        login(req, resp);
        break;
      case "/register":
        try {
          register(req, resp);
        } catch (ParseException e) {
          throw new RuntimeException(e);
        }
        break;
      default:
        break;
    }
  }


  private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    HttpSession session = req.getSession();
    String idUser = AccountBO.isValidAccount(username, password);
    if (idUser != null) {
      User user = UserBO.getUserById(idUser);
      session.setAttribute("user", user);
      session.setAttribute("loginStatus", "success");
    } else {
      session.setAttribute("loginStatus", "failed");
    }
    resp.sendRedirect(redirectURL);
  }

  private void register(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException, ParseException {
    String name = req.getParameter("name");
    String username = req.getParameter("username");
    String email = req.getParameter("email");
    String password = req.getParameter("password");
    String phoneNumber = req.getParameter("phone");
    String dobStr = req.getParameter("dateofbirth");
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Date dob = formatter.parse(dobStr);
    User user = new User();
    Account account = new Account();
    user.setName(name);
    user.setEmail(email);
    user.setDob(dob);
    user.setPhoneNumber(phoneNumber);
    user.setIdRole("4ce1e18b-371c-4dd2-a69a-203166338f44");
    account.setPassword(password);
    account.setUsername(username);
    String idUser = UserBO.addUser(user);
    HttpSession session = req.getSession();
    if (idUser != null){
      account.setIduser(idUser);
      AccountBO.addAccount(account);
      session.setAttribute("registrationStatus", "success");
    }else {
      session.setAttribute("registrationStatus", "failed");
    }
    resp.sendRedirect(redirectURL);
  }
}
