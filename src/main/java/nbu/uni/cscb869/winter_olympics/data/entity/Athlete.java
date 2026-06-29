package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Athlete extends BaseEntity{

    private String name;
    private String country;

    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthDate;
}
