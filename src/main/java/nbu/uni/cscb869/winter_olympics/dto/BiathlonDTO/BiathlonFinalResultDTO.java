package nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class BiathlonFinalResultDTO {
    private Long athleteId;
    private String athleteName;

    private Double skiingTimeSeconds;
    private Integer totalShots;
    private Integer missedShots;

    private Boolean finished;
    private Integer rank;
}
