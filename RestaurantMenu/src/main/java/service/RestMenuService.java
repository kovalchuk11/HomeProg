package service;

import dao.RestMenuDAO;
import entity.RestMenu;
import main.Main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RestMenuService implements RestMenuDAO {
    static EntityManagerFactory emf;
    static EntityManager em;

    @Override
    public void fillDB(int n) {

        for (int i = 0; i < n; i++) {
            final String[] TITLE = {"Борщ", "Макароны", "Мясо", "Суп", "Картошка"};
            final int[] PRICE = {10, 20, 30, 40};
            final int[] HELF = {200, 100, 500, 50, 100};
            final int[] DISCOUN = {0, 1};
            final Random RND = new Random();
            String title = TITLE[RND.nextInt(TITLE.length)];
            int price = PRICE[RND.nextInt(PRICE.length)];
            int helf = HELF[RND.nextInt(HELF.length)];
            int discount = DISCOUN[RND.nextInt(DISCOUN.length)];

            RestMenuDAO rms = new RestMenuService();
            RestMenu c = new RestMenu(title, price, helf, discount);
            try {
                rms.add(c);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        Main.menu();


    }


    @Override
    public void add(RestMenu restMenu) throws SQLException {
        emf = Persistence.createEntityManagerFactory("JPAMenu");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(restMenu);
            em.getTransaction().commit();
            em.close();
            emf.close();

        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

    }

    @Override
    public void viewMenu() {
        emf = Persistence.createEntityManagerFactory("JPAMenu");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        String search = "SELECT c FROM RestMenu c WHERE discount = 0 AND price >= 10 AND price <= 39";
        Query query = em.createQuery(search, RestMenu.class);
        List<RestMenu> list = (List<RestMenu>) query.getResultList();

        for (RestMenu c : list) {
            System.out.println(c);
        }
        em.close();
        emf.close();
        Main.menu();
    }

    @Override
    public void addDish() {
        Scanner sc = new Scanner(System.in);

        System.out.println("название блюда: ");
        String title = sc.nextLine();
        System.out.println("цена: ");
        String price = sc.nextLine();
        int sprice = Integer.parseInt(price);
        System.out.println("вес(гр): ");
        String helf = sc.nextLine();
        int shelf = Integer.parseInt(helf);
        System.out.println("скидка (0 или 1): ");
        String discount = sc.nextLine();
        int sdiscount = Integer.parseInt(discount);

        RestMenuDAO rms = new RestMenuService();
        RestMenu c = new RestMenu(title, sprice, shelf, sdiscount);
        try {
            rms.add(c);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Main.menu();
    }

    public void basket() {
        List<RestMenu> selectedDish = new ArrayList<>();
        int vol = 0;
        while (vol <= 1000) {
            Scanner sn = new Scanner(System.in);
            System.out.println("id блюда: ");
            String id = sn.nextLine();
            int sid = Integer.parseInt(id);
            emf = Persistence.createEntityManagerFactory("JPAMenu");
            em = emf.createEntityManager();
            em.getTransaction().begin();
            String search = "SELECT c FROM RestMenu c WHERE id =" + sid;
            Query query = em.createQuery(search, RestMenu.class);
            List<RestMenu> list1 = (List<RestMenu>) query.getResultList();
            vol += list1.get(0).getHelf();
            if(vol <= 1000)
                selectedDish.add(list1.get(0));
            else System.out.println("вес привысил 1кг");
            System.out.println(vol);
            System.out.println("добавить еще? (1 или 2): ");

            String ab = sn.nextLine();
            if(ab.equals("1")){
            }else if(ab.equals("2"))
                break;
            else {
                break;
            }
        }

        for (RestMenu c : selectedDish) {
            System.out.println(c);
        }
        em.close();
        emf.close();
        Main.menu();
    }
}

