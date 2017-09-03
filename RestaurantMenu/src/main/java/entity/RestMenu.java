package entity;


import javax.persistence.*;

@Entity
@Table (name = "MENU")
public class RestMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    private int price;
    private int helf;
    private int discount;

    public RestMenu(){
    }

    public RestMenu(String title, int price, int helf, int discount) {
        this.title = title;
        this.price = price;
        this.helf = helf;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getHelf() {
        return helf;
    }

    public void setHelf(int helf) {
        this.helf = helf;
    }

    public int isDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "RestMenu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", heft=" + helf +
                ", discount=" + discount +
                '}';
    }
}
