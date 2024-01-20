package org.main.entity;

import jakarta.persistence.*;
import lombok.*;
import org.main.utils.Generator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@Setter
@Builder
@Table(name = "card")
@RequiredArgsConstructor
@AllArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private Instant creation_date=Instant.now();
    @Builder.Default
    private Instant closing_date=Instant.now().plus(1826, ChronoUnit.DAYS);
    @Builder.Default
    private String code = Generator.generate(3);
    @Builder.Default
    private String number=Generator.generate(16);
    private Double balance;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", creation_date=" + creation_date +
                ", closing_date=" + closing_date +
                ", code='" + code + '\'' +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", account=" + account.getAcc_number() +
                '}';
    }
}
