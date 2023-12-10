package Model.BO;

import DTO.BestUserDTO;
import Helper.DBHelper;
import Model.Bean.Account;
import Model.Bean.User;
import Model.DAO.AccountDAO;
import Model.DAO.RoleDAO;
import Model.DAO.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.UUID;

public class UserBO {

    private UserDAO userDAO;
    private RoleDAO roleDAO;
    public UserBO() {
        userDAO = new UserDAO();
        roleDAO = new RoleDAO();
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

    public int getNumUser() {
        return userDAO.getNumUser();
    }

    public User getUserById(String id) {
        User user = userDAO.getUserByID(id);
        user.setRole(roleDAO.getRoleByID(user.getIdRole()));
        return user;
    }

    public BestUserDTO getBestUser() {
        return userDAO.getBestUser();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(String idUser) {
        userDAO.deleteUser(idUser);
    }
}
