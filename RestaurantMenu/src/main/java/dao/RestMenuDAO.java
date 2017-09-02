package dao;

import entity.RestMenu;

import java.sql.SQLException;

public interface RestMenuDAO {

    //создать
    void add(RestMenu restMenu) throws SQLException;

    //прочитать все
    void viewClients() throws SQLException;

}
