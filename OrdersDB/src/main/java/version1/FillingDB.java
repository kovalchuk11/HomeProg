package version1;

import java.sql.*;
import java.util.Scanner;

public class FillingDB {
    OrdersDB od = new OrdersDB();
    Scanner sc = new Scanner(System.in);
    Connection conn = DriverManager.getConnection(od.getDbUrl(), od.getDbUser(), od.getDbPass());
    Statement st = conn.createStatement();

    public FillingDB() throws SQLException {
    }

    protected void addUser() {
        System.out.print("Enter client first name: ");
        String name = sc.nextLine();
        System.out.print("Enter client second name: ");
        String lastName = sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO db_orders.users (firstName, lasName) VALUES(?, ?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } catch (SQLException e) {
            e.printStackTrace();
            menu();
        }
        menu();
    }

    protected void addProduct() {
        System.out.print("Введите марку: ");
        String name = sc.nextLine();
        System.out.print("Введите стоимость: ");
        String price = sc.nextLine();
        int pr = Integer.parseInt(price);
        System.out.print("Какой цвет: ");
        String color = sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO db_orders.products (name, price, colore) VALUES(?, ?, ?)")) {
            ps.setString(1, name);
            ps.setInt(2, pr);
            ps.setString(3, color);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } catch (SQLException e) {
            e.printStackTrace();
            menu();
        }
        menu();
    }

    protected void addOrder() {
        System.out.print("Введите id клиента: ");
        String idUser = sc.nextLine();
        int us = Integer.parseInt(idUser);
        System.out.print("Введите id товара: ");
        String idProd = sc.nextLine();
        int pr = Integer.parseInt(idProd);
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO db_orders.orders (idUser, idProduct) VALUES(?, ?)")) {
            ps.setInt(1, us);
            ps.setInt(2, pr);
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("нет такого юзера или продукта, поробуйте еще раз");
            menu();
        }
        menu();
    }

    protected void search() {
        System.out.println("укажите id елиента, что бы показать его заказы");
        String id = sc.nextLine();
        int idU = Integer.parseInt(id);
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT firstName, name FROM users JOIN orders JOIN products ON orders.idUser = users.idUser AND orders.idProduct = products.idProduct WHERE users.idUser = " + idU)) {
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
            menu();
        }
        menu();
    }

    protected void menu() {
        System.out.println("=========");
        System.out.println("Добавить клиента: 1");
        System.out.println("Добавить продукт: 2");
        System.out.println("Добавить заказ: 3");
        System.out.println("поиск: 4");
        System.out.print("-> ");

        String s = sc.nextLine();
        switch (s) {
            case "1":
                addUser();
                break;
            case "2":
                addProduct();
                break;
            case "3":
                addOrder();
                break;
            case "4":
                search();
                break;
            default:
                return;

        }
    }
}
