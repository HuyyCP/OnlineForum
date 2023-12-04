package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO {

    public String getRoleID(String rolename) {
        try {
            String query = "SELECT idrole FROM role WHERE rolename = ?";
            ResultSet rs = DBHelper.query(query, rolename);
            rs.next();
            return rs.getString("idrole");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addRole(Role role) {
        String query = "INSERT INTO role (idrole, rolename) VALUES (?, ?)";
        DBHelper.execute(query, role.getIdRole(), role.getRoleName());
    }

    public void deleteRole(String idRole) {
        String query = "DELETE FROM role WHERE idrole = ?";
        DBHelper.execute(query, idRole);
    }
}
