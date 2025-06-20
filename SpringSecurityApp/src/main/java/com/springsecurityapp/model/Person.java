package com.springsecurityapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Size(min = 2, max = 100, message = "Имя должно ыть от 2 до 100 символов")
    @NotBlank(message = "Поле не должно быть пустое")
    private String username;

    @Column(name = "year_of_birth")
    @Min(value = 1900, message = "Год рождения > 1900")
    private Integer yearOfBirth;

    @NotBlank(message = "Вы не ввели пароль")
    @Column
    private String password;

}