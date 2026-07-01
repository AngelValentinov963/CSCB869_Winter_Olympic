package nbu.uni.cscb869.winter_olympics.dto.SlalomDTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlalomFirstRunDTO {
    private Long competitionId;
    private Long athleteId;
    private Double firstRunTime;
}