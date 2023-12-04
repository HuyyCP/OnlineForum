package Helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://103.252.136.81:3306/onlineforum", "cnw", "");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static ResultSet query(String sql, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            return pstmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static boolean execute(String sql, Object... params) {
        try {
            Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

}
