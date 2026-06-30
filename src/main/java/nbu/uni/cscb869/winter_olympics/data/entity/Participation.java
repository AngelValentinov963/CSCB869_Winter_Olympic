package nbu.uni.cscb869.winter_olympics.data.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Participation extends BaseEntity{

    @ManyToOne
    private Athlete athlete;

    @ManyToOne
    private Competition competition;

    // SlalomCompetition fields
    private Double firstRunTime;
    private Double secondRunTime;

    // Biathlon fields
    private Double skiingTime;
    private Integer misses;

    private boolean didNotFinish = false;


}