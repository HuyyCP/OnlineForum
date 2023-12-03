package Model.BO;

import Model.Bean.User;
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

    public void addUser(User user) {
        user.setIdUser(UUID.randomUUID().toString());
        user.setDateCreated(new Date());
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(String idUser) {
        userDAO.deleteUser(idUser);
    }
}
