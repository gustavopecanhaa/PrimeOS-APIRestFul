package com.api.primeos.controller;

import com.api.primeos.dto.ServicoDTO;
import com.api.primeos.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // Endpoint para cadastrar um novo serviço (acessível a funcionários autenticados)
    @PostMapping("/cadastrar")
    public ResponseEntity<ServicoDTO> cadastrarServico(@RequestBody @Valid ServicoDTO servicoDTO) {
        ServicoDTO novoServico = servicoService.cadastrarServico(servicoDTO);
        return new ResponseEntity<>(novoServico, HttpStatus.CREATED);
    }

    // Endpoint para atualizar um serviço
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @servicoSecurity.isServicoOwner(#id, principal.id)")
    public ResponseEntity<ServicoDTO> atualizarServico(@PathVariable Integer id,
                                                       @RequestBody @Valid ServicoDTO servicoDTO) {
        ServicoDTO servicoAtualizado = servicoService.atualizarServico(id, servicoDTO);
        return new ResponseEntity<>(servicoAtualizado, HttpStatus.OK);
    }

    // Endpoint para buscar um serviço por ID (consulta aberta a usuários autenticados)
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarServicoPorId(@PathVariable Integer id) {
        ServicoDTO servicoDTO = servicoService.buscarServicoPorId(id);
        return new ResponseEntity<>(servicoDTO, HttpStatus.OK);
    }

    // Endpoint para listar todos os serviços (consulta aberta a usuários autenticados)
    @GetMapping("/todos")
    public ResponseEntity<List<ServicoDTO>> listarTodos() {
        List<ServicoDTO> servicos = servicoService.listarTodos();
        return ResponseEntity.ok(servicos);
    }

    // Endpoint para excluir um serviço
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or @servicoSecurity.isServicoOwner(#id, principal.id)")
    public ResponseEntity<Void> excluirServico(@PathVariable Integer id) {
        servicoService.excluirServico(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
