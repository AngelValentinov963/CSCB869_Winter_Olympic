package nbu.uni.cscb869.winter_olympics.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlalomRunDTO {
    private Long competitionId;
    private Long athleteId;
    private Double firstRunTime;
}