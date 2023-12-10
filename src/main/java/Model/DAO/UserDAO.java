package Model.DAO;

import DTO.BestUserDTO;
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

    public int getNumUser() {
        try {
            String query = "SELECT COUNT(*) as numUser FROM user ";
            ResultSet rs = DBHelper.query(query);
            rs.next();
            return rs.getInt("numUser");
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

    public BestUserDTO getBestUser() {
        try {
            String query = "select user.iduser, user.name, count(distinct post.idpost) as numPost, count(distinct comment.idcomment) as numComment\n" +
                            "from user left join post on user.iduser = post.iduser\n" +
                            "\t\t\t\t\tleft join comment on user.iduser = comment.iduser\n" +
                            "group by user.iduser\n" +
                            "order by (count(distinct post.idpost) + count(distinct comment.idcomment)) desc\n" +
                            "limit 1;";
            ResultSet rs = DBHelper.query(query);
            rs.next();
            BestUserDTO user = new BestUserDTO();
            user.setIdUser(rs.getString("iduser"));
            user.setName(rs.getString("name"));
            user.setNumPost(rs.getInt("numPost"));
            user.setNumComments(rs.getInt("numComment"));
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addUser(User user) {
        String query = "INSERT INTO user (iduser, name, email, dateofbirth, phonenumber, datecreate, idrole) VALUES (?, ?, ?, ?, ?, ?, ?)";
        if (DBHelper.execute(query, user.getIdUser(), user.getName(), user.getEmail(), user.getDob(), user.getPhoneNumber(), user.getDateCreated(), user.getIdRole())) {
            return true;
        }
        return false;
    }

    public void updateUser(User user) {
        String query = "UPDATE user SET name = ?, email = ?, dateofbirth = ?, phonenumber = ? WHERE iduser = ?";
        DBHelper.execute(query, user.getName(), user.getEmail(), user.getDob(), user.getPhoneNumber(), user.getIdUser());
    }

    // Khong nen xoa user
    public void deleteUser(String idUser) {
        String query = "DELETE FROM user WHERE iduser = ?";
        DBHelper.execute(query, idUser);
    }
}
