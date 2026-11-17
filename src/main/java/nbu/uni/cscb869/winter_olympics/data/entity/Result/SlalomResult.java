
package nbu.uni.cscb869.winter_olympics.data.entity.Result;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "slalom_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SlalomResult extends Result {

    @DecimalMin("0.001")
    @Digits(integer = 7, fraction = 3)
    @Column(name = "first_run_time", precision = 10, scale = 3)
    private BigDecimal firstRunTime;

    @DecimalMin("0.001")
    @Digits(integer = 7, fraction = 3)
    @Column(name = "second_run_time", precision = 10, scale = 3)
    private BigDecimal secondRunTime;

    @Builder.Default
    @Column(name = "first_run_submitted", nullable = false)
    private boolean firstRunSubmitted = false;

    @Builder.Default
    @Column(name = "second_run_submitted", nullable = false)
    private boolean secondRunSubmitted = false;
}
