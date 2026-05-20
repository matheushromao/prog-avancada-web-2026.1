package com.aula.apibiblioteca.mapper;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.model.Livro;

public class LivroMapper {

    public static Livro toEntity(LivroRequestDto livroRequestDto) {
        var livro =  new Livro();
        livro.setTitulo(livroRequestDto.titulo());
        livro.setAutor(livroRequestDto.autor());
        livro.setQuantidade(livroRequestDto.quantidade());
        return livro;
    }

    public static LivroResponseDto ToDto(Livro livro) {
        return new LivroResponseDto(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getQuantidade()
        );
    }
}
