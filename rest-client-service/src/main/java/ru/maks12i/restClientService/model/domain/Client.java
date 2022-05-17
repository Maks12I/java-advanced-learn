package ru.maks12i.restClientService.model.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "table_of_clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "[а-яёА-ЯЁ-]+", message = "Должны быть только русские символы")
    private String firstName;

    @Pattern(regexp = "[а-яёА-ЯЁ-]+", message = "Должны быть только русские символы")
    private String lastName;

    @Pattern(regexp = "([а-яёА-ЯЁ-]+)?", message = "Должны быть только русские символы, либо отчества вовсе может не быть")
    private String thirdName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birthDate;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
             message = "Номер телефона должен быть похож на +79999999999, 8(999)999-99-99 , +7 999 999 99 99")
    private String contactNumber;

    @Email(message = "Почта должна быть похожа на example@example.example")
    private String email;

    @CreatedDate
    private LocalDate signUpDate;

    private LocalDate deleteDate;

}
