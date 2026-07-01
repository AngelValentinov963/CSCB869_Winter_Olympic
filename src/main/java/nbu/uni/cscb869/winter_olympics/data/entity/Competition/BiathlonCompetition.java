package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;

@Getter
@Entity
public class BiathlonCompetition extends Competition {

    private Integer laps;

}
