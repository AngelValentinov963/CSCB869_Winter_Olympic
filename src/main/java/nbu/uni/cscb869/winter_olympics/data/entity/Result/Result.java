package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import nbu.uni.cscb869.winter_olympics.data.entity.Athlete;
import nbu.uni.cscb869.winter_olympics.data.entity.BaseEntity;
import nbu.uni.cscb869.winter_olympics.data.entity.Competition.Competition;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Result extends BaseEntity {

    @ManyToOne
    private Athlete athlete;

    @ManyToOne
    private Competition competition;

    private Double finalTime;
}