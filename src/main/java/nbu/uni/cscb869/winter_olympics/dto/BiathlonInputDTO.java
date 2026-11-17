package nbu.uni.cscb869.winter_olympics.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiathlonInputDTO {

    @NotNull
    private Long athleteId;

    private String athleteName;

    @NotNull
    @DecimalMin("0.001")
    @Digits(integer = 7, fraction = 3)
    private BigDecimal skiingTimeSeconds;

    @NotNull
    @Min(0)
    private Integer missedShots;

    @NotNull
    @Min(1)
    private Integer laps;

    @NotNull
    private Boolean finished;
}