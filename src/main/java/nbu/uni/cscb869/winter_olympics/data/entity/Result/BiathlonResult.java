package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.Entity;

@Entity
public class BiathlonResult extends Result {

    private Double skiTime;
    private Integer misses;
    private Integer penaltySeconds;
}
