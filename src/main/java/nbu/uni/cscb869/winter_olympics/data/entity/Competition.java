package nbu.uni.cscb869.winter_olympics.data.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Competition extends BaseEntity{
    private int minAge;

    @Enumerated(EnumType.STRING)
    private Gender allowedGender;

    @OneToMany(mappedBy = "competition")
    private List<Participation> participations;

}
