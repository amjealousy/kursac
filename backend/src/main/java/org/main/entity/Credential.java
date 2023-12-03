package org.main.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "info_abt_credit1")
@RequiredArgsConstructor
@AllArgsConstructor
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String full_name;
    private LocalDate date_of_bday;
    private String address;
    @Enumerated(EnumType.STRING)
    @Column(name = "degree")
    private Degree degree;
    private String job;
    private String passport_details;
    private String inn;
    private String phone;

}
