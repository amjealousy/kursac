package org.main.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(exclude ="client")
@Builder
@Table(name = "account")
@RequiredArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID acc_number;
    @Builder.Default
    private Instant creation_date=Instant.now();
    @Builder.Default
    private Instant closing_date=Instant.now().plus(1826, ChronoUnit.DAYS);

    private String currencies;

    @Enumerated(EnumType.STRING)
    private TypeAccount type_of_account;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @OneToMany(mappedBy = "account")
    private List<Card> cards;

    @OneToMany(mappedBy = "accountCredit")
    private List<Credit> credits;



}
