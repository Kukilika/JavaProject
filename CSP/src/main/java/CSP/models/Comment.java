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
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "comment_id"), inverseJoinColumns = @JoinColumn(name = "car_id"))
    private CarArticle carArticle;

    public Comment() {

    }

    public Comment(Long id, User poster, CarArticle carArticle) {
        this.id = id;
        this.poster = poster;
        this.carArticle = carArticle;
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

    public CarArticle getCarArticle() {
        return carArticle;
    }

    public void setCarArticle(CarArticle carArticle) {
        this.carArticle = carArticle;
    }
}
