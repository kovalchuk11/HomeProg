package main;

import dao.RestMenuDAO;
import service.RestMenuService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menu();
    }


    public static void menu(){

        System.out.println("1: add random dish");
        System.out.println("2: add dish");
        System.out.println("3: view dish");
        System.out.println("4: choose dish");
        System.out.print("-> ");

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        switch (s) {

            case "1":
                RestMenuDAO restMenu1 = new RestMenuService();
                restMenu1.fillDB(5);
                break;
            case "2":
                RestMenuDAO restMenu2 = new RestMenuService();
                restMenu2.addDish();
                break;
            case "3":
                RestMenuDAO restMenu3 = new RestMenuService();
                restMenu3.viewMenu();
                break;
            case "4":
                RestMenuDAO restMenu4 = new RestMenuService();
                restMenu4.basket();
                break;
            default:
                return;
        }
    }
}
