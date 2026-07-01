package nbu.uni.cscb869.winter_olympics.dto;

import nbu.uni.cscb869.winter_olympics.data.entity.MedalType;

public class MedalDTO {

    private Long athleteId;
    private String athleteName;
    private String country;

    private MedalType medal;   // <-- use enum here
    private String competitionName;
}
