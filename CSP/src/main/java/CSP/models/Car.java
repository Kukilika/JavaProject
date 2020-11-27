package CSP.models;

import javax.persistence.*;

@Entity
@Table(name = "CARS")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "car_id")
    private Long id;

    @Column(name = "carMake")
    private String make;

    @Column(name = "carModel")
    private String model;

    @Column(name = "carDescription")
    private String description;

    @Column(name = "carMileage")
    private Long mileage;

    @Column(name = "carAccident")
    private Boolean accident;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_car",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
    private User owner;

    @Column(name = "carPrice")
    private Long price;

    public Car(String make, String model, String description, Long mileage, Boolean accident, User owner, Long price) {
        this.make = make;
        this.model = model;
        this.description = description;
        this.mileage = mileage;
        this.accident = accident;
        this.owner = owner;
        this.price = price;
    }

    public Car() {
    }

    public Car(Long id, String make, String model, String description, Long mileage, Boolean accident, User owner, Long price) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.description = description;
        this.mileage = mileage;
        this.accident = accident;
        this.owner = owner;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public Boolean getAccident() {
        return accident;
    }

    public void setAccident(Boolean accident) {
        this.accident = accident;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
