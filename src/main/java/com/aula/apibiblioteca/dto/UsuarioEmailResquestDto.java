package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioEmailResquestDto (@NotBlank @Email String email) {
}
