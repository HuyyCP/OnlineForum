package Model.DAO;

import Helper.DBHelper;
import Model.Bean.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    private User getUser(ResultSet rs) {
        try {
            User user = new User();
            user.setIdUser(rs.getString("iduser"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setDob(rs.getDate("dateofbirth"));
            user.setPhoneNumber(rs.getString("phonenumber"));
            user.setDateCreated(rs.getDate("datecreate"));
            user.setIdRole(rs.getString("idrole"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAllUsers() {
        try {
            String query = "SELECT * FROM user ";
            ResultSet rs = DBHelper.query(query);
            ArrayList<User> users = new ArrayList<>();
            while(rs.next()) {
                users.add(getUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User getUserByID(String idUser) {
        try {
            String query = "SELECT * FROM user where iduser = ?";
            ResultSet rs = DBHelper.query(query, idUser);
            rs.next();
            return getUser(rs);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        String query = "INSERT INTO post (iduser, name, email, dateofbirth, phonenumber, datecreate, idrole) VALUES (?, ?, ?, ?, ?, ?, ?)";
        DBHelper.execute(query, user.getIdUser(), user.getName(), user.getEmail(), user.getDob(), user.getPhoneNumber(), user.getDateCreated(), user.getIdRole());
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET name = ?, email = ?, dateofbirth = ?, phonenumber = ?, datecreate = ?, idrole = ? WHERE iduser = ?";
        DBHelper.execute(query, user.getName(), user.getEmail(), user.getDob(), user.getPhoneNumber(), user.getDateCreated(), user.getIdRole(), user.getIdUser());
    }

    // Khong nen xoa user
    public void deleteUser(String idUser) {
        String query = "DELETE FROM user WHERE iduser = ?";
        DBHelper.execute(query, idUser);
    }
}
