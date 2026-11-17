package nbu.uni.cscb869.winter_olympics.data.entity.Result;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.BaseEntity;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;

import java.math.BigDecimal;

@Entity
@Table(name = "result")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public abstract class Result extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "athlete_id", nullable = false)
    private Athlete athlete;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @Column(nullable = false)
    private Boolean finished = false;

    @PositiveOrZero
    @Digits(integer = 7, fraction = 3)
    @Column(name = "final_time", precision = 10, scale = 3)
    private BigDecimal finalTime;
}