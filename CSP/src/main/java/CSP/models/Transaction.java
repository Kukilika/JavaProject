package CSP.models;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_buyer",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private User buyer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_seller",joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    private User seller;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "transaction_car",joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private CarArticle soldCar;

    public Transaction(User buyer, User seller, CarArticle soldCar) {
        this.buyer = buyer;
        this.seller = seller;
        this.soldCar = soldCar;
    }

    public Transaction() {
    }

    public Transaction(Long id, User buyer, User seller, CarArticle soldCar) {
        this.id = id;
        this.buyer = buyer;
        this.seller = seller;
        this.soldCar = soldCar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public CarArticle getSoldCar() {
        return soldCar;
    }

    public void setSoldCar(CarArticle soldCar) {
        this.soldCar = soldCar;
    }
}
