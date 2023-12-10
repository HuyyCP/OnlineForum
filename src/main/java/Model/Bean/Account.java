package Model.Bean;

public class Account {
    private String idAccount;
    private String username;
    private String password;
    private String idUser;
    private User user;

    public String getIdaccount() {
        return idAccount;
    }

    public void setIdaccount(String idaccount) {
        this.idAccount = idaccount;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIduser() {
        return idUser;
    }

    public void setIduser(String idUser) {
        this.idUser = idUser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
