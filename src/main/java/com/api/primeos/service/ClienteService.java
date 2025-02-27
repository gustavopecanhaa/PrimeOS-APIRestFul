package com.api.primeos.service;

import com.api.primeos.dto.ClienteDTO;
import com.api.primeos.exception.ClienteNotFoundException;
import com.api.primeos.model.Cliente;
import com.api.primeos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    // Cadastra um novo cliente
    @Transactional
    public ClienteDTO cadastrarCliente(ClienteDTO clienteDTO) {
        // Verifica se o nome do cliente já existe
        repository.findByNome(clienteDTO.nome()).ifPresent(existing -> {
            throw new IllegalArgumentException("Nome já cadastrado! Escolha outro.");
        });

        // Converte o DTO para a entidade Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setEndereco(clienteDTO.endereco());
        cliente.setEmail(clienteDTO.email());

        // Salva no banco
        Cliente salvo = repository.save(cliente);

        // Retorna o DTO
        return new ClienteDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getTelefone(),
                salvo.getEndereco(),
                salvo.getEmail()
        );
    }

    // Atualiza os dados de um cliente
    @Transactional
    public ClienteDTO atualizarCliente(Integer id, ClienteDTO clienteDTO) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        // Verifica se o nome foi alterado e, em caso afirmativo, valida se já existe
        if (!cliente.getNome().equals(clienteDTO.nome())) {
            repository.findByNome(clienteDTO.nome()).ifPresent(existing -> {
                throw new IllegalArgumentException("Nome já cadastrado! Escolha outro.");
            });
        }

        // Atualiza os dados
        cliente.setNome(clienteDTO.nome());
        cliente.setTelefone(clienteDTO.telefone());
        cliente.setEndereco(clienteDTO.endereco());
        cliente.setEmail(clienteDTO.email());

        // Salva a entidade atualizada
        Cliente atualizado = repository.save(cliente);
        return new ClienteDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getTelefone(),
                atualizado.getEndereco(),
                atualizado.getEmail()
        );
    }

    // Busca um cliente por ID
    public ClienteDTO buscarPorNome(String nome) {
        Cliente cliente = repository.findByNome(nome)
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));
        return new ClienteDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getEmail()
        );
    }

    // Lista todos os clientes
    public List<ClienteDTO> listarTodos() {
        return repository.findAll().stream()
                .map(cliente -> new ClienteDTO(
                        cliente.getId(),
                        cliente.getNome(),
                        cliente.getTelefone(),
                        cliente.getEndereco(),
                        cliente.getEmail()
                ))
                .toList();
    }

    // Deleta um cliente por ID
    @Transactional
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ClienteNotFoundException("Cliente não encontrado para exclusão");
        }
        repository.deleteById(id);
    }
}
