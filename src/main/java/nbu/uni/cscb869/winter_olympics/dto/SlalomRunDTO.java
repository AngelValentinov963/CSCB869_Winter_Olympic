
package nbu.uni.cscb869.winter_olympics.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlalomRunDTO {

    @NotNull
    private Long competitionId;

    @NotNull
    private Long athleteId;

    @DecimalMin("0.001")
    @Digits(integer = 7, fraction = 3)
    private BigDecimal firstRunTime;

    @Builder.Default
    private Boolean didNotFinish = false;
}
