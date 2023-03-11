package com.example.scooterrentcontrolsystem.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tariff")
public class Tariff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price_per_minute")
    private Double pricePerMinute;
    @Column(name = "description")
    private String description;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "tariff")
    private List<User> user = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "tariff2model", joinColumns = @JoinColumn(name = "tariff_id"), inverseJoinColumns = @JoinColumn(name = "model_id"))
    @ManyToMany
    private List<ScooterModel> models = new ArrayList<>();
}
