package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.mapper.LivroMapper;
import com.aula.apibiblioteca.repository.LivroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Page<LivroResponseDto> findAll(Pageable pagination) {
        return livroRepository.findAll(pagination).map((l) ->  LivroMapper.ToDto(l));
    }

    public LivroResponseDto findById(Long id) {
        var livro = livroRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
        return LivroMapper.ToDto(livro);
    }

    public LivroResponseDto save(LivroRequestDto livroRequestDto) {
        var livro = LivroMapper.toEntity(livroRequestDto);
        return  LivroMapper.ToDto(livroRepository.save(livro));
    }
}
