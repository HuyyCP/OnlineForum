package Model.DAO;

import Helper.DBHelper;
import Model.Bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class UserDAO {

    Connection conn;

    public UserDAO() {
        conn = DBHelper.getConnection();
    }

    public ArrayList<User> getAllUsers() {
        try {
            String query = "SELECT * " +
                            "FROM user ";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();
            while(rs.next()) {
                User user = new User();
                user.setIdUser(rs.getString("iduser"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setDob(rs.getDate("dateofbirth"));
                user.setPhoneNumber(rs.getString("phonenumber"));
                user.setDateCreated(rs.getDate("datecreate"));
                user.setIdRole(rs.getString("idrole"));
                users.add(user);
            }
            statement.close();
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUser(User user) {
        try {
            String query = "INSERT INTO post (iduser, name, email, dateofbirth, phonenumber, datecreate, idrole) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getIdUser());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setDate(4, (Date)user.getDob());
            statement.setString(5, user.getPhoneNumber());
            statement.setDate(6, (Date)user.getDateCreated());
            statement.setString(7, user.getIdRole());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user) {
        try {
            String query = "UPDATE user " +
                            "SET iduser = ?, name = ?, email = ?, dateofbirth = ?, phonenumber = ?, datecreate = ?, idrole = ?" +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getIdUser());
            statement.setString(2, user.getName());
            statement.setString(3, user.getEmail());
            statement.setDate(4, (Date)user.getDob());
            statement.setString(5, user.getPhoneNumber());
            statement.setDate(6, (Date)user.getDateCreated());
            statement.setString(7, user.getIdRole());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Khong nen xoa user
    public void deleteUser(String idUser) {
        try {
            String query = "DELETE FROM user " +
                            "WHERE iduser = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idUser);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
