package org.main.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "client")
@RequiredArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    @NotBlank(message = "[Имя] - не может быть пустым")
    @Size(min=2,max = 30,message = "[Имя] - должно быть в диапазоне  от 2 до 30 символов")

    private String first_name;
    @Column(nullable = false)
    @NotBlank(message = "[Фамилия] - не может быть пустым")
    @Size(min=2,max = 30,message = "[Фамилия] - должно быть в диапазоне  от 2 до 30 символов")
    private String last_name;

    @Column(nullable = false)
    @NotBlank(message = "[Фамилия] - не может быть пустым")
    @Size(min=2,max = 30,message = "[Фамилия] - должно быть в диапазоне  от 2 до 30 символов")
    private String patronymic_name;
    private String department;
    @Column(nullable = false)
    private String passport_details;
    @Column(nullable = false)
    private String phone;
//    private Boolean isBroker;
//    private Boolean isInsurance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "credential_id")
    private Credential credential;
//    private String phone;
    @Builder.Default
    private Boolean deleted = false;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;






}
