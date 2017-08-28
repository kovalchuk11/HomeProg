package version1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class OrdersDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_orders?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true\" +\n" +
            "\"&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "12345";

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPass() {
        return DB_PASS;
    }

    private static Connection connection;

    public static void main(String[] args) {
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
//            раскоментировать вызов метода createTables(); при первом запуске
//            createTables();
            addDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS products");
            st.execute("CREATE TABLE products (idProduct INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(40) NOT NULL," +
                    "price INT NOT NULL, colore VARCHAR (40))");
            st.execute("DROP TABLE IF EXISTS users");
            st.execute("CREATE TABLE users (idUser INT NOT NULL AUTO_INCREMENT PRIMARY KEY, firstName VARCHAR(40) NOT NULL," +
                    "lastName VARCHAR (40))");
            st.execute("DROP TABLE IF EXISTS orders");
            st.execute("CREATE TABLE orders (idOrder INT NOT NULL AUTO_INCREMENT PRIMARY KEY, idUser INT NOT NULL," +
                    "idProduct INT NOT NULL)");
            st.execute("ALTER TABLE orders\n" +
                    "ADD CONSTRAINT orders_users_idUser_fk\n" +
                    "FOREIGN KEY (idUser) REFERENCES users (idUser)");
            st.execute("ALTER TABLE orders\n" +
                    "ADD CONSTRAINT orders_products_idProduct_fk\n" +
                    "FOREIGN KEY (idProduct) REFERENCES products (idProduct)");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addDB() throws SQLException {
        FillingDB fillingDB = new FillingDB();
        fillingDB.menu();
    }
}
