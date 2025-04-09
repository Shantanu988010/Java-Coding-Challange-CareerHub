package util;

import java.io.*;
import java.util.Properties;

public class DBPropertyUtil {
    public static String getConnectionString(String fileName) {
        try (InputStream input = new FileInputStream(fileName)) {
            Properties prop = new Properties();
            prop.load(input);
            return prop.getProperty("db.url") + "?user=" + prop.getProperty("db.user") + "&password=" + prop.getProperty("db.password");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}