package nbu.uni.cscb869.winter_olympics.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SlalomResultDTO {
    private Long athleteId;
    private String athleteFirstName;
    private String athleteLastName;
    private String country;
    private Double firstRunTime;
    private Double secondRunTime;
    private Double totalTime;
    private Integer rank;
}