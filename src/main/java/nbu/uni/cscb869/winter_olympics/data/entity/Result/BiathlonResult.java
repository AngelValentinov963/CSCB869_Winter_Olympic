package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BiathlonResult extends Result {

    private Double skiTime;
    private Integer misses;
    private Integer penaltySeconds;
    private Boolean finished;
}
