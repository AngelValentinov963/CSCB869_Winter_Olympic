package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String name;

    @Min(0)
    private int minAge;

    @NotNull
    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    private Gender allowedGender;

    @OneToMany(
            mappedBy = "competition",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Participation> participations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "olympic_id")
    private Olympic olympic;
}