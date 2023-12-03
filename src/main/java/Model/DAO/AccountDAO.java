package Model.DAO;

import Helper.DBHelper;
import Model.Bean.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    Connection conn;

    public AccountDAO() {
        conn = DBHelper.getConnection();
    }

    public boolean isValidAccount(String username, String password) {
        try {
            String query = "SELECT * " +
                            "FROM account " +
                            "WHERE username = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            int rowsAffected = statement.executeUpdate();
            statement.close();
            if(rowsAffected > 0) {
                return true;
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addAccount(Account account) {
        try {
            String query = "INSERT INTO account (idaccount, username, password, iduser) " +
                            "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, account.getIdaccount());
            statement.setString(2, account.getUsername());
            statement.setString(3, account.getPassword());
            statement.setString(4, account.getIduser());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateAccount(Account account) {
        try {
            String query = "UPDATE account " +
                            "SET password = ? " +
                            "WHERE idaccount = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, account.getPassword());
            statement.setString(2, account.getIdaccount());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAccount(String idAccount) {
        try {
            String query = "DELETE FROM account " +
                            "WHERE idaccount = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, idAccount);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
