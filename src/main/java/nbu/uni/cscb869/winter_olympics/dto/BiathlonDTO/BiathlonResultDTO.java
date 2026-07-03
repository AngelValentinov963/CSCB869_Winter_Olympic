package nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BiathlonResultDTO {
    private Long athleteId;
    private String athleteName;

    private Double skiingTimeSeconds;

    private Integer totalShots;
    private Integer missedShots;

    private Integer laps;
    private Boolean finished;
}
