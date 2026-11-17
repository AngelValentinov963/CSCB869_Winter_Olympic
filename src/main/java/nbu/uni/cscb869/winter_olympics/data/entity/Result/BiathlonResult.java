
package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "biathlon_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BiathlonResult extends Result {

    @NotNull
    @DecimalMin("0.001")
    @Digits(integer = 7, fraction = 3)
    @Column(name = "ski_time", precision = 10, scale = 3)
    private BigDecimal skiTime;

    @Min(1)
    @Column(nullable = true)
    private Integer laps;

    @NotNull
    @Min(0)
    @Column(name = "total_shots", nullable = false)
    private Integer totalShots;

    @NotNull
    @Min(0)
    @Column(name = "misses", nullable = false)
    private Integer misses;

    @NotNull
    @Min(0)
    @Column(name = "penalty_seconds", nullable = false)
    private Integer penaltySeconds;
}
