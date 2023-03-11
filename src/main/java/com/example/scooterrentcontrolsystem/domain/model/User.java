package com.example.scooterrentcontrolsystem.domain.model;

import com.example.scooterrentcontrolsystem.domain.model.enums.Role;
import com.example.scooterrentcontrolsystem.domain.model.enums.UserAccountStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "login")
    private String login;
    @Column(name = "hash_password")
    private String hashPassword;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserAccountStatus status;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JoinColumn(name = "tariff_id")
    @ManyToOne
    private Tariff tariff;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
    private User2Subscription user2Subscription;
}
