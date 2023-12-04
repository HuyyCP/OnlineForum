package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Account;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public boolean isValidAccount(String username, String password) {
        try {
            String query = "SELECT * FROM account WHERE username = ? AND password = ?";
            ResultSet rs = DBHelper.query(query, username, password);
            if (rs != null && rs.next()) {
                rs.close();
                return true;
            }
            if (rs != null) rs.close();
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(Account account) {
        String query = "INSERT INTO account (idaccount, username, password, iduser) VALUES (?, ?, ?, ?)";
        DBHelper.execute(query, account.getIdaccount(), account.getUsername(), account.getPassword(), account.getIduser());
    }

    public void updateAccount(Account account) {
        String query = "UPDATE account SET password = ? WHERE idaccount = ?";
        DBHelper.execute(query, account.getPassword(), account.getIdaccount());
    }

    public void deleteAccount(String idAccount) {
        String query = "DELETE FROM account WHERE idaccount = ?";
        DBHelper.execute(query, idAccount);
    }
}
