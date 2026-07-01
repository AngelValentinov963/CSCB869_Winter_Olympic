package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SlalomResult extends Result {
    private Double firstRunTime;
    private Double secondRunTime;
}
