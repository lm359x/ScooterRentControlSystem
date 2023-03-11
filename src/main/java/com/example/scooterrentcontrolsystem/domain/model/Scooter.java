package com.example.scooterrentcontrolsystem.domain.model;

import com.example.scooterrentcontrolsystem.domain.model.enums.ScooterConditionStatus;
import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "scooter")
public class Scooter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private ScooterModel model;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ScooterConditionStatus status;
    @Column(name = "charge")
    private Double charge; // 0.7 = 70%
    @Column(name = "mileage")
    private Double mileage;

    @ManyToOne
    @JoinColumn(name = "rental_point_id")
    private RentalPoint rentalPoint;
}
