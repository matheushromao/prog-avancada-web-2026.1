package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioResquestDto(@NotBlank(message = "Nome não pode ser vazio") String nome,
                                 @NotBlank(message = "Email não pode ser vazio") @Email(message = "Email não é válido") String email) {

}
