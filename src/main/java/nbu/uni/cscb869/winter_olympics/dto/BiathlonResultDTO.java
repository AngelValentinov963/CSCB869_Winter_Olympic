
package nbu.uni.cscb869.winter_olympics.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class BiathlonResultDTO {

    private Long athleteId;
    private String athleteFirstName;
    private String athleteLastName;
    private String country;

    private BigDecimal skiingTimeSeconds;

    private Integer totalShots;
    private Integer missedShots;
    private Integer penaltySeconds;

    private BigDecimal finalTimeSeconds;

    private Boolean finished;
    private Integer rank;
}
