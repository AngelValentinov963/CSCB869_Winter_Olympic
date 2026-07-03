package nbu.uni.cscb869.winter_olympics.dto.BiathlonDTO;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
@NoArgsConstructor
public class BiathlonResultDTO {

    private Long athleteId;
    private String athleteFirstName;

    private String athleteLastName;

    private Double skiingTimeSeconds;
    private Integer totalShots;
    private Integer missedShots;

    private Integer penaltySeconds;
    private Double finalTimeSeconds;

    private Boolean finished;
    private Integer rank;
}