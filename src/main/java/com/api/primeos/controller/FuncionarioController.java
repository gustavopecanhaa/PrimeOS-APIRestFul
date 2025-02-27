package com.api.primeos.controller;

import com.api.primeos.dto.FuncionarioDTO;
import com.api.primeos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    // Apenas ADMIN pode cadastrar novos funcionários
    @PostMapping("/cadastrar")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO funcionarioCriado = service.cadastrarFuncionario(funcionarioDTO);
        return new ResponseEntity<>(funcionarioCriado, HttpStatus.CREATED);
    }

    // Permite que apenas ADMIN ou o próprio funcionário (conferindo o id) possa atualizar
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(
            @PathVariable Integer id,
            @Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO atualizado = service.atualizarFuncionario(id, funcionarioDTO);
        return ResponseEntity.ok(atualizado);
    }

    // Apenas ADMIN ou o próprio funcionário pode consultar seu cadastro
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<FuncionarioDTO> buscarPorId(@PathVariable Integer id) {
        FuncionarioDTO funcionario = service.buscarPorId(id);
        return ResponseEntity.ok(funcionario);
    }

    // Apenas ADMIN pode listar todos os funcionários
    @GetMapping("/todos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<FuncionarioDTO>> listarTodos() {
        List<FuncionarioDTO> funcionarios = service.listarTodos();
        return ResponseEntity.ok(funcionarios);
    }

    // Permite que apenas ADMIN ou o próprio funcionário possa excluir seu cadastro
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == principal.id")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
