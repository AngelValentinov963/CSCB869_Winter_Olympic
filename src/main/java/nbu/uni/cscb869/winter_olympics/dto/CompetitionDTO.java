package nbu.uni.cscb869.winter_olympics.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetitionDTO {
    private Long id;
    private String name;
    private int minAge;
    private String allowedGender; // Превръщаме Enum в String за лесно показване
}
