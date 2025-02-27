package com.api.primeos.controller;

import com.api.primeos.dto.ClienteDTO;
import com.api.primeos.exception.ClienteNotFoundException;
import com.api.primeos.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    // Endpoint para cadastrar um novo cliente
    @PostMapping("/cadastrar")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<ClienteDTO> cadastrarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCriado = service.cadastrarCliente(clienteDTO);
        return new ResponseEntity<>(clienteCriado, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um cliente existente
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO atualizado = service.atualizarCliente(id, clienteDTO);
        return ResponseEntity.ok(atualizado);
    }

    // Endpoint para buscar um cliente por ID
    @GetMapping("/{nome}")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<ClienteDTO> buscarPorNome(@PathVariable String nome) {
        ClienteDTO cliente = service.buscarPorNome(nome);
        return ResponseEntity.ok(cliente);
    }

    // Endpoint para listar todos os clientes
    @GetMapping("/todos")
    @PreAuthorize("hasAnyRole('ADMIN', 'FUNCIONARIO')")
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        List<ClienteDTO> clientes = service.listarTodos();
        return ResponseEntity.ok(clientes);
    }

    // Endpoint para deletar um cliente por ID
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarCliente(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Exceção personalizada para tratar quando o cliente não for encontrado
    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
