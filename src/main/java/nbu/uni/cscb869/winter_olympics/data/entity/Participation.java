package nbu.uni.cscb869.winter_olympics.data.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
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

    // Calculated field logic
    public Double getTotalTime() {
        if (competition instanceof SlalomCompetition) {
            return (firstRunTime != null ? firstRunTime : 0) + (secondRunTime != null ? secondRunTime : 0);
        } else if (competition instanceof Biathlon) {
            return skiingTime + (misses * ((Biathlon) competition).getPenaltySecondsPerMiss());
        }
        return 0.0;
    }
}