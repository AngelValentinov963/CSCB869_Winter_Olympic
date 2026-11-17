package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlalomCompetition extends Competition {

    @Min(1)
    @Column(name = "finalist_count")
    private Integer finalistCounts;
}