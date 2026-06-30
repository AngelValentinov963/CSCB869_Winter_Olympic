package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class Olympic extends BaseEntity{
    private String name;
    @Column(name = "event_year")
    private LocalDateTime year;

    @OneToMany(mappedBy = "olympic")
    private Set<Competition> competition;
}
