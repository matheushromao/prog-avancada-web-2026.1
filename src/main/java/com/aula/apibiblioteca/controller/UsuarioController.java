package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.UsuarioEmailResquestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.dto.UsuarioResquestDto;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//http://localhost:8080/usuarios
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Page<UsuarioResponseDto>> findAll(
            @PageableDefault(size = 10, sort = "nome", direction = Sort.Direction.ASC)
            Pageable pagination) {
        return ResponseEntity.ok(usuarioService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
            UsuarioResponseDto usuarioResponseDto = usuarioService.findbyId(id);
            return ResponseEntity.ok(usuarioResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioResquestDto usuarioResquestDto, BindingResult result) {

        if(result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (var error : result.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(erros);
        }

        var UserCreate = usuarioService.save(usuarioResquestDto);
        return ResponseEntity.status(201).body(UserCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioResquestDto usuarioResquestDto) {
            UsuarioResponseDto usuarioResponseDto = usuarioService.update(id, usuarioResquestDto);
            return ResponseEntity.ok(usuarioResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<UsuarioResponseDto> updateEmail(@PathVariable Long id, @RequestBody @Valid UsuarioEmailResquestDto emailDto) {
            UsuarioResponseDto usuarioResponseDto = usuarioService.updateEmail(id, emailDto);
            return ResponseEntity.ok(usuarioResponseDto);
    }
}
