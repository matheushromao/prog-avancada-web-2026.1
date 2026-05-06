package com.aula.apibiblioteca.mapper;

import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.dto.UsuarioResquestDto;
import com.aula.apibiblioteca.model.Usuario;

public class UsuarioMapper {

    public static Usuario ToEntity(UsuarioResquestDto usuarioResquestDto) {
        var usuario = new Usuario();
        usuario.setNome(usuarioResquestDto.nome());
        usuario.setEmail(usuarioResquestDto.email());
        return usuario;
    }

    public static UsuarioResponseDto ToDto(Usuario usuario) {
        return new UsuarioResponseDto(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail());
    }
}
