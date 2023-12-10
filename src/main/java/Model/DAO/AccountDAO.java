package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Account;
import Model.Bean.SubSubject;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    public String isValidAccount(String username, String password) {
        try {
            String query = "SELECT * FROM account WHERE username = ? AND password = ?";
            ResultSet rs = DBHelper.query(query, username, password);
            String res = null;
            if (rs != null) {
                if(rs.next()) {
                    res = rs.getString("iduser");
                }
                rs.close();
            }
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Account getAccount(ResultSet rs) {
        try {
            Account account = new Account();
            account.setIdaccount(rs.getString("idaccount"));
            account.setUsername(rs.getString("username"));
            account.setPassword(rs.getString("password"));
            account.setIduser(rs.getString("iduser"));
            return account;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Account getAccountByID(String idAccount) {
        try {
            String query = "SELECT * FROM account WHERE idaccount = ?";
            ResultSet rs = DBHelper.query(query, idAccount);
            if(rs.next()) {
                return getAccount(rs);
            }
            return null;
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
