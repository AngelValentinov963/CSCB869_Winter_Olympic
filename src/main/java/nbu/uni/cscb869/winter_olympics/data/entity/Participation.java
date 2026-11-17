package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;

@Entity
@Table(
        name = "participation",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_participation_athlete_competition",
                columnNames = {"athlete_id", "competition_id"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Participation extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    @ManyToOne(optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @Column(name = "did_not_finish", nullable = false)
    private boolean didNotFinish = false;
}