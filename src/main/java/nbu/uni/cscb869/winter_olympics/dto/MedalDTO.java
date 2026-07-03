package nbu.uni.cscb869.winter_olympics.dto;

import lombok.Data;
import nbu.uni.cscb869.winter_olympics.data.entity.MedalType;

@Data
public class MedalDTO {

    private Long athleteId;
    private String athleteName;
    private String country;

    private MedalType medal;
    private String competitionName;
}
