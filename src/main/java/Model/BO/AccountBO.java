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
    private UserBO userBO;

    public AccountBO() {
        accountDAO = new AccountDAO();
        userBO = new UserBO();
    }

    public String isValidAccount(String username, String password) {
        return accountDAO.isValidAccount(username, password);
    }

    public Account getAccountByID(String idAccount) {
        Account account = accountDAO.getAccountByID(idAccount);
        account.setUser(userBO.getUserById(account.getIduser()));
        return account;
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
