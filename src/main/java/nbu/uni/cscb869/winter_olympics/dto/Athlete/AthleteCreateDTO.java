package nbu.uni.cscb869.winter_olympics.dto.Athlete;

import lombok.Data;
import nbu.uni.cscb869.winter_olympics.data.entity.Gender;

import java.time.LocalDate;

@Data
public class AthleteCreateDTO {
    private String firstName;
    private String lastName;
    private String country;
    private Gender gender;
    private LocalDate birthDate;
}
