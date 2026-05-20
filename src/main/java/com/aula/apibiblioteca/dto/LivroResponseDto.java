package com.aula.apibiblioteca.dto;

public record LivroResponseDto(Long id,
                               String titulo,
                               String autor,
                               int quantidade) {
}
