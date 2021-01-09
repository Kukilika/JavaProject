package CSP.models;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "comment_id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_comment",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "comment_id"))
    private User poster;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "car_comment",joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Car car;

    public Comment() {

    }

    public Comment(Long id, User poster, Car car) {
        this.id = id;
        this.poster = poster;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getPoster() {
        return poster;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public Car getCarArticle() {
        return car;
    }

    public void setCarArticle(Car car) {
        this.car = car;
    }
}
