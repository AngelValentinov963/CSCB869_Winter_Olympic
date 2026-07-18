package nbu.uni.cscb869.winter_olympics.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BiathlonInputDTO {
    private Long athleteId;
    private String athleteName;

    private Double skiingTimeSeconds;

    private Integer missedShots;

    private Integer laps;
    private Boolean finished;
}
