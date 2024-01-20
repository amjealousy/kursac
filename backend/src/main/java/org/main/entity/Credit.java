package org.main.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Table(name = "credit")
@RequiredArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID key_id;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account accountCredit;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeCredit type;

    private Double value;


    public String toString() {
        return "Credit(key_id=" + this.getKey_id() + ", accountCredit=" + this.getAccountCredit().getAcc_number() + ", type=" + this.getType().getName() + ", value=" + this.getValue() + ")";
    }
}
