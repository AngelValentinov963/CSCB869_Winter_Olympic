package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nbu.uni.cscb869.winter_olympics.data.entity.BaseEntity;
import nbu.uni.cscb869.winter_olympics.data.entity.Gender;
import nbu.uni.cscb869.winter_olympics.data.entity.Olympic;
import nbu.uni.cscb869.winter_olympics.data.entity.Participation;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Competition extends BaseEntity {
    private String name;

    private int minAge;

    @Enumerated(EnumType.STRING)
    private Gender allowedGender;

    @OneToMany(mappedBy = "competition",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Participation> participations = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "olympic_id")
    private Olympic olympic;
}
