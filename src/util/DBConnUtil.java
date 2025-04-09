package util;

import java.sql.*;

public class DBConnUtil {
    public static Connection getConnection(String fileName) {
        try {
            String connString = DBPropertyUtil.getConnectionString(fileName);
            return DriverManager.getConnection(connString);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}