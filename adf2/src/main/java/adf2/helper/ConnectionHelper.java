package adf2.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class ConnectionHelper {

    private static final String DATABASE_NAME = "adf2_test";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Bangkok&characterEncoding=utf-8";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PWD = "";
    private static Connection connection;
    
    private final static Logger log = Logger.getAnonymousLogger();

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            log.info("Khởi tạo kết nối mới đến db.");
            connection = DriverManager
                    .getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PWD);
        }
        return connection;
    }
}
