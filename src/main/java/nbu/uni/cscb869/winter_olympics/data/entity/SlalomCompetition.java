package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SlalomCompetition extends Competition {
    // SlalomCompetition specific fields
    private Integer finalistCounts;

}