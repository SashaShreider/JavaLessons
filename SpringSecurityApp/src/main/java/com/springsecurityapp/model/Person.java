package com.springsecurityapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 2, max = 100, message = "Имя должно ыть от 2 до 100 символов")
    @NotBlank(message = "Поле не должно быть пустое")
    private String username;

    @Min(value = 1900, message = "Год рождения > 1900")
    private Integer yearOfBirth;

    @NotBlank(message = "Вы не ввели пароль")
    private String password;

    private String role;
}