package Model.BO;

import Model.Bean.Account;
import Model.Bean.User;
import Model.DAO.AccountDAO;
import Model.DAO.UserDAO;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class UserBO {

    private UserDAO userDAO;

    public UserBO() {
        userDAO = new UserDAO();
    }

    public ArrayList<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public String addUser(User user) {
        String id = UUID.randomUUID().toString();
        user.setIdUser(id);
        user.setDateCreated(new Date());
        if (userDAO.addUser(user)) {
            return id;
        }
        return null;
    }

    public User getUserById(String id) {
        return userDAO.getUserById(id);
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(String idUser) {
        userDAO.deleteUser(idUser);
    }
}
