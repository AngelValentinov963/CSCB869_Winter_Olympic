package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.*;
import nbu.uni.cscb869.winter_olympics.data.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name="role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String authority;

    @ManyToMany
    private Set<User> users;

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
