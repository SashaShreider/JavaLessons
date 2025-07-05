package com.springsecurityapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthDTO {
    @Size(min = 2, max = 100, message = "Имя должно ыть от 2 до 100 символов")
    @NotBlank(message = "Поле не должно быть пустое")
    private String username;

    @NotBlank(message = "Вы не ввели пароль")
    private String password;
}
