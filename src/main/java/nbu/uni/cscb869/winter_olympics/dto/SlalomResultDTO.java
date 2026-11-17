
package nbu.uni.cscb869.winter_olympics.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlalomResultDTO {

    private Long athleteId;

    private String athleteFirstName;

    private String athleteLastName;

    private String country;

    private BigDecimal firstRunTime;

    private BigDecimal secondRunTime;

    private BigDecimal totalTime;

    private Integer rank;
}
