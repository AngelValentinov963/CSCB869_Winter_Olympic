package nbu.uni.cscb869.winter_olympics.data.entity.Competition;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SlalomCompetition extends Competition {
    private Integer finalistCounts;
}