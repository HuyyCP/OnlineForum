package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {

    Connection conn;

    public RoleDAO() {
        conn = DBHelper.getConnection();
    }

    public String getRoleID(String rolename) {
        try {
            String query = "SELECT idrole " +
                            "FROM role " +
                            "WHERE rolename = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, rolename);
            ResultSet rs = statement.executeQuery();
            rs.next();
            statement.close();
            return rs.getString("idrole");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRole(Role role) {
        try {
            String query = "INSERT INTO role (idrole, rolename) " +
                            "VALUES (?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, role.getIdRole());
            statement.setString(2, role.getRoleName());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteRole(String idRole) {
        try {
            String query = "DELETE FROM role " +
                            "WHERE idrole = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idRole);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
