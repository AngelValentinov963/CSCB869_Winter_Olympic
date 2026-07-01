package nbu.uni.cscb869.winter_olympics.dto.SlalomDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlalomSecondRunDTO {
    private Long competitionId;
    private Long athleteId;

    private Double secondRunTime;


}
