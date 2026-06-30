package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Competition extends BaseEntity{
    private String name;

    private int minAge;

    @Enumerated(EnumType.STRING)
    private Gender allowedGender;

    @OneToMany(mappedBy = "competition")
    private List<Participation> participations;

    @ManyToOne
    private Olympic olympic;
}
