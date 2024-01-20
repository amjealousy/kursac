package org.main.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "credit_type")
@RequiredArgsConstructor
@AllArgsConstructor
public class TypeCredit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String condition;
    private Double interest_rate;
    private Integer repayment_period;


}
