package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.*;
import nbu.uni.cscb869.winter_olympics.data.entity.Login.User;

import java.time.LocalDate;


@Entity
@Table(name = "athlete")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Athlete extends BaseEntity {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String country;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Past
    private LocalDate birthDate;


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

}