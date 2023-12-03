package Model.BO;

import Model.Bean.Role;
import Model.DAO.RoleDAO;
import java.util.UUID;

public class RoleBO {

    private RoleDAO roleDAO;

    public RoleBO() {
        roleDAO = new RoleDAO();
    }

    public String getRoleID(String rolename) {
        return roleDAO.getRoleID(rolename);
    }

    public void addRole(Role role) {
        role.setIdRole(UUID.randomUUID().toString());
        roleDAO.addRole(role);
    }

    public void deleteRole(String idRole) {
        roleDAO.deleteRole(idRole);
    }
}
