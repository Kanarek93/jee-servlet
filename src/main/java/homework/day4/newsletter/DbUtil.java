package homework.day4.newsletter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/Newsletter?useSSL=false&characterEncoding=utf8";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";


    public static Connection getConnection() throws SQLException {
        // load and register JDBC driver for MySQL
        try {
            //Musiałam to dodać, bo inaczej servlet nie łączył się z bazą danych
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Połączenie niepoprawne");
        }

        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

    }

    public static void main(String[] args) {
        //test połączenia

        try (Connection conn = getConnection()){
            System.out.println("połączenie poprawne");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
