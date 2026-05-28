package com.aula.apibiblioteca.dto;

import java.time.LocalDateTime;

public record EmprestimoResponseDto(Long usuarioId,
                                    UsuarioResponseDto usuarioResponseDto,
                                    LivroResponseDto livroResponseDto,
                                    LocalDateTime dataEmprestimo,
                                    LocalDateTime dataDevolucao) {
}
