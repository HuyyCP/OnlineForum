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

    public Role getRoleByID(String idRole) {
        try {
            String query = "SELECT * FROM role WHERE idrole = ?";
            ResultSet rs = DBHelper.query(query, idRole);
            if(rs.next()) {
                Role role = new Role();
                role.setIdRole(rs.getString("idrole"));
                role.setRoleName(rs.getString("rolename"));
                return role;
            }
            return null;
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
