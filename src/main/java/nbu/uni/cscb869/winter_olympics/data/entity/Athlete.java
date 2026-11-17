package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @NotBlank
    @Column(name = "last_name", nullable = false, length = 80)
    private String lastName;

    @NotBlank
    @Column(nullable = false, length = 80)
    private String country;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Gender gender;

    @NotNull
    @Past
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotBlank
    @Column(name = "keycloak_id", unique = true, nullable = false, length = 100)
    private String keycloakId;
}