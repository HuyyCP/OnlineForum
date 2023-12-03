package Helper;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {

  public static Connection getConnection() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      return  DriverManager.getConnection("jdbc:mysql://localhost:3306/onlineforum", "root", "");
    } catch (Exception e) {
      System.out.println(e);
    }
    return null;
  }
}
