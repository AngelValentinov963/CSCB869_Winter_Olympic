package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
public class BiatlonCompetition extends Competition{
    private Integer laps;

}
