package jdbc;


import java.sql.*;
import java.util.Scanner;

public class SearcInDB {
    Apartment apartment = new Apartment();

    protected String search(int count) {

        try (Connection conn = DriverManager.getConnection(apartment.getDbUrl(), apartment.getDbUser(), apartment.getDbPass());
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM apartment WHERE rooms = " + count)) {

            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.print(md.getColumnName(i) + "\t\t");//
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {

                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();
        return null;
    }

    protected void search(double uj, double ed) {
        try (Connection conn = DriverManager.getConnection(apartment.getDbUrl(), apartment.getDbUser(), apartment.getDbPass());
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM apartment WHERE area >= " + uj + "AND area <= " + ed)) {
            ResultSetMetaData md = rs.getMetaData();

            for (int i = 1; i <= md.getColumnCount(); i++) {
                System.out.print(md.getColumnName(i) + "\t\t");//
            }
            System.out.println();

            while (rs.next()) {
                for (int i = 1; i <= md.getColumnCount(); i++) {

                    System.out.print(rs.getString(i) + "\t\t");
                }
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        menu();

    }

    protected void menu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Сортировать по:");
        System.out.println("1: кл-ву комнат");
        System.out.println("2: площади");
        System.out.print("-> ");

        String s = sc.nextLine();
        switch (s) {
            case "1":
                System.out.println("укажи кол-во комнат");
                String b = sc.nextLine();
                int bn = Integer.parseInt(b);
                search(bn);
                break;
            case "2":
                System.out.println("от");
                String bp = sc.nextLine();
                System.out.println("до");
                String br = sc.nextLine();
                double cb = Double.parseDouble(bp);
                double cn = Double.parseDouble(br);
                search(cb, cn);
                break;
            default:
                return;

        }
    }
}
