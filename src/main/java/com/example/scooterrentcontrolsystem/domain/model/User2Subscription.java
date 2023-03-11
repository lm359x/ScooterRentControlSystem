package com.example.scooterrentcontrolsystem.domain.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user2subscription")
public class User2Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "start_time")
    private LocalDateTime startTime;
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @JoinColumn(name = "user_id")
    @OneToOne
    private User user;
    @JoinColumn(name = "subscription_id")
    @OneToOne
    private Subscription subscription;

    public boolean isValid() {
        return LocalDateTime.now().isBefore(endTime);
    }
}
