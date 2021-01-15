package CSP.models;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.Optional;

@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_comment",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "car_comment",joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Car car;

    @Column(name = "description")
    private String description;

    public Comment(Long id, User user, Car car, String description) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.description = description;
    }

    public Comment() {
    }

    public Comment(Optional<Comment> comment) {
        this.id = comment.get().getId();
        this.user = comment.get().getUser();
        this.car = comment.get().getCar();
        this.description = comment.get().getDescription();
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", car=" + car +
                ", description='" + description + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
