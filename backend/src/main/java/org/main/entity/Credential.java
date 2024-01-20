package org.main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "info_abt_credit")
@RequiredArgsConstructor
@AllArgsConstructor
public class Credential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "мя не может быть пустым")
    @Size(min=2,max = 100,message = "мя должно быть в диапазоне  от 2 до 100 символов")
    private String login;
    private String password;
    @Column(name = "super")
    private Boolean superU;


}
