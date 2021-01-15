package CSP.models;

import javax.persistence.*;

@Entity
@Table(name = "TRANSACTIONS")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @Column(name = "receipt", columnDefinition = "TEXT")
    private String receipt;

    public void createReceipt(String buyer, String seller, String car){
        receipt = buyer + " bought from " + seller + " the car with the following details: " + car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Transaction() {
    }

    public Transaction(Long id, String receipt) {
        this.id = id;
        this.receipt = receipt;
    }
}
