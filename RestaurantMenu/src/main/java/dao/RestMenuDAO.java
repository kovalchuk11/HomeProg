package dao;

import entity.RestMenu;

import java.sql.SQLException;

public interface RestMenuDAO {

    //создать
    void add(RestMenu restMenu) throws SQLException;
    void addDish();
    void fillDB(int n);
    void basket();


    //прочитать
    void viewMenu();


}
