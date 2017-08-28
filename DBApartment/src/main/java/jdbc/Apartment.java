package jdbc;

import java.sql.*;
import java.util.Random;

public class Apartment {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_apartment?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true\" +\n" +
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
            createDB();
            fillDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //=======
    public static void createDB() {
        try (Statement st = connection.createStatement()) {
            st.execute("DROP TABLE IF EXISTS Apartment");
            st.execute("CREATE TABLE Apartment (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, district VARCHAR(40) NOT NULL," +
                    "rooms INT, price INT NOT NULL, manage VARCHAR (40), area DOUBLE NOT NULL)");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fillDB() {
        final String[] DISTRICTS = {"Севера", "Ветра", "Островов", "Скал", "Шторма", "Дорна"};
        final String[] ADRESES = {"Старков", "Ланистеров", "Кхалиси", "Короля ночи"};
        final double[] AREAS = {20.1, 50.2, 14.2, 50.6, 102.0, 206.5};
        final Random RND = new Random();
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Apartment  VALUES(?, ?, ?, ?, ?, ?)");) {
            connection.setAutoCommit(false);

            for (int i = 1; i <= 50; i++) {
                ps.setInt(1, i);
                ps.setString(2, DISTRICTS[RND.nextInt(DISTRICTS.length)]);
                ps.setInt(3, 1 + RND.nextInt(5));
                ps.setInt(4, RND.nextInt(100000)%100*1000);
                ps.setString(5, ADRESES[RND.nextInt(ADRESES.length)]);
                ps.setDouble(6, AREAS[RND.nextInt(AREAS.length)]);
                ps.executeUpdate();
            }
            connection.commit();
            SearcInDB searcInDB = new SearcInDB();
            searcInDB.menu();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
