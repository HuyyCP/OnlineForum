package Helper;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

  Connection conn = null;

  public Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/OF", "root", "");
    } catch (Exception e) {
      System.out.println(e);
    }
    return conn;
  }
}
