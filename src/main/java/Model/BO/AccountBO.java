package Model.BO;

import Helper.DBHelper;
import Model.Bean.Account;
import Model.DAO.AccountDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class AccountBO {

    private AccountDAO accountDAO;

    public AccountBO() {
        accountDAO = new AccountDAO();
    }

    public boolean isValidAccount(String username, String password) {
        return accountDAO.isValidAccount(username, password);
    }

    public void addAccount(Account account) {
        account.setIdaccount(UUID.randomUUID().toString());
        accountDAO.addAccount(account);
    }

    public void updateAccount(String idAccount, String password) {
        Account account = new Account();
        account.setIdaccount(idAccount);
        account.setPassword(password);
        accountDAO.updateAccount(account);
    }

    public void deleteAccount(String idAccount) {
        accountDAO.deleteAccount(idAccount);
    }
}
