package com.aula.apibiblioteca.service;

import com.aula.apibiblioteca.dto.UsuarioEmailResquestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.dto.UsuarioResquestDto;
import com.aula.apibiblioteca.mapper.UsuarioMapper;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<UsuarioResponseDto> findAll(Pageable pagination) {

        return usuarioRepository.findAll(pagination).map((u) -> UsuarioMapper.ToDto(u));
    }


    public UsuarioResponseDto findbyId(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return UsuarioMapper.ToDto(usuario);

    }


    public UsuarioResponseDto save(UsuarioResquestDto usuarioresquestdto) {
        var usuario = UsuarioMapper.ToEntity(usuarioresquestdto);
        return UsuarioMapper.ToDto(usuarioRepository.save(usuario));
    }

    // Update *
    public UsuarioResponseDto update(Long id, UsuarioResquestDto usuarioresquestdto) {
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioTemp.setNome(usuarioresquestdto.nome());
        usuarioTemp.setEmail(usuarioresquestdto.email());

        return UsuarioMapper.ToDto(usuarioRepository.save(usuarioTemp));
    }

    // Delete com where
    public void deleteById(Long id) {
        findbyId(id);
        usuarioRepository.deleteById(id);
    }

    // Update do email
    public UsuarioResponseDto updateEmail(Long id, UsuarioEmailResquestDto emailDto) {
        Usuario usuarioTemp = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuarioTemp.setEmail(emailDto.email());
        return UsuarioMapper.ToDto(usuarioRepository.save(usuarioTemp));
    }
}
