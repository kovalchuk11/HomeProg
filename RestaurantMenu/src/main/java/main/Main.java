package main;

import entity.RestMenu;
import service.RestMenuService;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }


    public static void menu(){

        System.out.println("1: add random dish");
        System.out.println("2: add dish");
        System.out.println("3: view dish");
        System.out.print("-> ");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        switch (s) {

            case "1":
                RestMenuService restMenuService1 = new RestMenuService();
                restMenuService1.fillDB(5);
                break;
            case "2":
                RestMenuService restMenuService2 = new RestMenuService();
                restMenuService2.addDish();
                break;
            case "3":
                RestMenuService restMenuService3 = new RestMenuService();
                restMenuService3.viewClients();
                break;
            default:
                return;
        }
    }
}
