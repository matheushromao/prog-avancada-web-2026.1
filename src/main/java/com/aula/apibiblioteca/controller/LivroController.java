package com.aula.apibiblioteca.controller;

import com.aula.apibiblioteca.dto.LivroRequestDto;
import com.aula.apibiblioteca.dto.LivroResponseDto;
import com.aula.apibiblioteca.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<Page<LivroResponseDto>> findALl(
            @PageableDefault(size = 10) Pageable pagination){
        return ResponseEntity.ok(livroService.findAll(pagination));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable Long id, Pageable pagination){
        LivroResponseDto livroResponseDto = livroService.findById(id);
        return ResponseEntity.ok(livroResponseDto);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody @Valid LivroRequestDto livroRequestDto,
                                                 BindingResult result){
        if(result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            for (var error : result.getFieldErrors()){
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        var userCreate = livroService.save(livroRequestDto);
        return ResponseEntity.ok(userCreate);
    }

}
