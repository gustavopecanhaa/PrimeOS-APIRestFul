package com.api.primeos.service;

import com.api.primeos.dto.FuncionarioDTO;
import com.api.primeos.exception.DuplicateLoginException;
import com.api.primeos.exception.FuncionarioNotFoundException;
import com.api.primeos.model.Funcionario;
import com.api.primeos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Cadastra um novo funcionário com senha criptografada
    @Transactional
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
        // Verifica se o login já existe
        repository.findByLogin(funcionarioDTO.login()).ifPresent(f -> {
            throw new DuplicateLoginException("Login já cadastrado! Escolha outro.");
        });

        // Converte o DTO em entidade
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setFuncao(funcionarioDTO.funcao());
        funcionario.setTelefone(funcionarioDTO.telefone());
        funcionario.setLogin(funcionarioDTO.login());
        // Criptografa a senha
        funcionario.setSenha(passwordEncoder.encode(funcionarioDTO.senha()));

        // Salva a entidade
        Funcionario salvo = repository.save(funcionario);

        // Retorna um DTO de saída (senha não é exposta, portanto, null)
        return new FuncionarioDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getFuncao(),
                salvo.getTelefone(),
                salvo.getLogin(),
                null
        );
    }

    // Atualiza um funcionário existente
    @Transactional
    public FuncionarioDTO atualizarFuncionario(Integer id, FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado"));

        // Se o login for alterado, verifica duplicidade
        if (!funcionario.getLogin().equals(funcionarioDTO.login())) {
            repository.findByLogin(funcionarioDTO.login()).ifPresent(existing -> {
                if (!existing.getId().equals(id)) {
                    throw new DuplicateLoginException("Login já cadastrado! Escolha outro.");
                }
            });
        }

        // Atualiza os dados
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setFuncao(funcionarioDTO.funcao());
        funcionario.setTelefone(funcionarioDTO.telefone());
        funcionario.setLogin(funcionarioDTO.login());

        // Atualiza a senha apenas se uma nova senha for informada (não nula e não em branco)
        if (funcionarioDTO.senha() != null && !funcionarioDTO.senha().isBlank()) {
            funcionario.setSenha(passwordEncoder.encode(funcionarioDTO.senha()));
        }

        Funcionario atualizado = repository.save(funcionario);
        return new FuncionarioDTO(
                atualizado.getId(),
                atualizado.getNome(),
                atualizado.getFuncao(),
                atualizado.getTelefone(),
                atualizado.getLogin(),
                null
        );
    }

    // Buscar funcionário por ID
    public FuncionarioDTO buscarPorId(Integer id) {
        Funcionario funcionario = repository.findById(id)
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado"));
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getFuncao(),
                funcionario.getTelefone(),
                funcionario.getLogin(),
                null
        );
    }

    // Listar todos os funcionários (retornando DTOs)
    public List<FuncionarioDTO> listarTodos() {
        return repository.findAll().stream()
                .map(f -> new FuncionarioDTO(
                        f.getId(),
                        f.getNome(),
                        f.getFuncao(),
                        f.getTelefone(),
                        f.getLogin(),
                        null))
                .toList();
    }

    // Deletar funcionário por ID
    @Transactional
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new FuncionarioNotFoundException("Funcionário não encontrado para exclusão");
        }
        repository.deleteById(id);
    }
}
