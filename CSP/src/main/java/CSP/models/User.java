package CSP.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;

@Entity
@Table(name = "USERS")
@SequenceGenerator(name= "seq", initialValue = 3)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "firstName")
    private String firstName;

    @Column(nullable = false, name = "lastName")
    private String lastName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "date")
    private LocalDate date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public Boolean isAdmin(){
        if(this.getRole().getRole().equals("Admin")){
            return true;
        }
        return false;
    }

    public User() {
    }

    public User(String username, String firstName, String lastName, String email, String password, LocalDate date, Role role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.date = date;
        this.role = role;
    }



    public User(Long id, String username, String firstName, String lastName, String email, String password, LocalDate date, Role role) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.date = date;
        this.role = role;
    }

    public User(User user) {

    }

    public User(Optional<User> optionalUser) {
        if(optionalUser.isPresent()){
            this.password = optionalUser.get().getPassword();
            this.username = optionalUser.get().getUsername();
            this.email = optionalUser.get().getEmail();
            this.date = optionalUser.get().getDate();
            this.firstName = optionalUser.get().getFirstName();
            this.lastName = optionalUser.get().getLastName();
            this.role = optionalUser.get().getRole();
            this.id = optionalUser.get().getId();
        }else{

        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
