package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class BiathlonCompetition extends Competition {

    @Min(1)
    @Column(nullable = false)
    private Integer laps;
}