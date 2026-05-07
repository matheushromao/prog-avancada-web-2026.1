package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.UsuarioEmailResquestDto;
import com.aula.apibiblioteca.dto.UsuarioResponseDto;
import com.aula.apibiblioteca.dto.UsuarioResquestDto;
import com.aula.apibiblioteca.model.Usuario;
import com.aula.apibiblioteca.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        try {
            UsuarioResponseDto usuarioResponseDto = usuarioService.findbyId(id);
            return ResponseEntity.ok(usuarioResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioResquestDto usuarioResquestDto, BindingResult result) {

        if(result.hasErrors()) {
            Map<String, String> erros = new HashMap<>();
            for (var error : result.getFieldErrors()) {
                erros.put(error.getField(), error.getDefaultMessage());
            }
            ResponseEntity.badRequest().body(erros);
        }

        var UserCreate = usuarioService.save(usuarioResquestDto);
        return ResponseEntity.status(201).body(UserCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> update(@PathVariable Long id, @RequestBody @Valid UsuarioResquestDto usuarioResquestDto) {
        try{
            UsuarioResponseDto usuarioResponseDto = usuarioService.update(id, usuarioResquestDto);
            return ResponseEntity.ok(usuarioResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}/update-email")
    public ResponseEntity<UsuarioResponseDto> updateEmail(@PathVariable Long id, @RequestBody @Valid UsuarioEmailResquestDto emailDto) {
        try {
            UsuarioResponseDto usuarioResponseDto = usuarioService.updateEmail(id, emailDto);
            return ResponseEntity.ok(usuarioResponseDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
