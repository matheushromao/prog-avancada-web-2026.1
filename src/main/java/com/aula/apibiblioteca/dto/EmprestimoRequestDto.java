package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.NotNull;

public record EmprestimoRequestDto(@NotNull(message = "Usuário não pode ser vazio!") Long usuarioId,
                                   @NotNull(message = "Livro não pode ser vazio!") Long livroId) {
}
