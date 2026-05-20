package com.aula.apibiblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record LivroRequestDto(Long id, @NotBlank(message = "Título não pode ser vazio") String titulo,
                              @NotBlank(message = "Autor não poder ser vazio") String autor,
                              @NotNull(message = "quantidade obrigatória")
                              @PositiveOrZero(message = "A quantidade não deve ser negativa") int quantidade) {
}
