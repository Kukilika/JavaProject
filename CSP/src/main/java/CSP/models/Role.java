package CSP.models;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "role")
@SequenceGenerator(name= "seq", initialValue = 3)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role")
    private String role;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }

    public Role(Optional<Role> role) {
        this.id = role.get().getId();
        this.role = role.get().getRole();
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
