package com.example.scooterrentcontrolsystem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scooter_model")
public class ScooterModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "model")
    private String name;
    @Column(name = "scooter_weight")
    private Integer scooterWeight;
    @Column(name = "max_weight_limit")
    private Integer maxWeightLimit;
    @Column(name = "max_speed")
    private Integer maxSpeed;
    @Column(name = "max_range")
    private Integer maxRange;
    @Column(name = "price")
    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    List<Scooter> scooters;
}
