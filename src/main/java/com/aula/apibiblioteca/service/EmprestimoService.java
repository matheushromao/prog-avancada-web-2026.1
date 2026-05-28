package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.EmprestimoRequestDto;
import com.aula.apibiblioteca.dto.EmprestimoResponseDto;
import com.aula.apibiblioteca.mapper.EmprestimoMapper;
import com.aula.apibiblioteca.model.Emprestimo;
import com.aula.apibiblioteca.repository.EmprestimoRepository;
import com.aula.apibiblioteca.repository.LivroRepository;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private EmprestimoMapper mapper;

    public EmprestimoResponseDto emprestar(EmprestimoRequestDto emprestimoRequestDto){

        var usuario = usuarioRepository.findById(emprestimoRequestDto.usuarioId())
                .orElseThrow(()-> new EntityNotFoundException("Usuário não encontrado!"));
        var livro = livroRepository.findById(emprestimoRequestDto.livroId())
                .orElseThrow(()-> new EntityNotFoundException("Livro não encontrado"));

        var emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);

        var dataEmprestimo = LocalDateTime.now();
        var dataDevolucao = dataEmprestimo.plusMonths(1);

        return mapper.toDto(emprestimoRepository.save(emprestimo));
    }

}
