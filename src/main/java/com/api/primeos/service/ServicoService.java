package com.api.primeos.service;

import com.api.primeos.dto.ServicoDTO;
import com.api.primeos.exception.ClienteNotFoundException;
import com.api.primeos.exception.FuncionarioNotFoundException;
import com.api.primeos.exception.ServicoNotFoundException;
import com.api.primeos.model.Servico;
import com.api.primeos.model.Cliente;
import com.api.primeos.model.Funcionario;
import com.api.primeos.repository.ServicoRepository;
import com.api.primeos.repository.ClienteRepository;
import com.api.primeos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Metodo para cadastrar um novo serviço
    @Transactional
    public ServicoDTO cadastrarServico(ServicoDTO servicoDTO) {
        // Verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(servicoDTO.clienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        // Verifica se o funcionário existe
        Funcionario funcionario = funcionarioRepository.findById(servicoDTO.funcionarioId())
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado"));

        // Criação da nova entidade de Servico
        Servico servico = new Servico();
        servico.setCliente(cliente);
        servico.setFuncionario(funcionario);
        servico.setEquipamento(servicoDTO.equipamento());
        servico.setDescricao(servicoDTO.descricao());
        servico.setPreco(servicoDTO.preco());
        servico.setData(servicoDTO.data());

        // Salvando o serviço no banco
        Servico servicoSalvo = servicoRepository.save(servico);

        // Retorna o DTO do serviço salvo
        return new ServicoDTO(
                servicoSalvo.getId(),
                servicoSalvo.getCliente().getId(),
                servicoSalvo.getFuncionario().getId(),
                servicoSalvo.getEquipamento(),
                servicoSalvo.getDescricao(),
                servicoSalvo.getPreco(),
                servicoSalvo.getData()
        );
    }

    // Metodo para atualizar um serviço existente
    @Transactional
    public ServicoDTO atualizarServico(Integer id, ServicoDTO servicoDTO) {
        // Verifica se o serviço existe no banco
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Serviço não encontrado"));

        // Verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(servicoDTO.clienteId())
                .orElseThrow(() -> new ClienteNotFoundException("Cliente não encontrado"));

        // Verifica se o funcionário existe
        Funcionario funcionario = funcionarioRepository.findById(servicoDTO.funcionarioId())
                .orElseThrow(() -> new FuncionarioNotFoundException("Funcionário não encontrado"));

        // Atualiza os dados do serviço
        servico.setCliente(cliente);
        servico.setFuncionario(funcionario);
        servico.setEquipamento(servicoDTO.equipamento());
        servico.setDescricao(servicoDTO.descricao());
        servico.setPreco(servicoDTO.preco());
        servico.setData(servicoDTO.data());

        // Salvando as alterações no banco
        Servico servicoAtualizado = servicoRepository.save(servico);

        // Retorna o DTO do serviço atualizado
        return new ServicoDTO(
                servicoAtualizado.getId(),
                servicoAtualizado.getCliente().getId(),
                servicoAtualizado.getFuncionario().getId(),
                servicoAtualizado.getEquipamento(),
                servicoAtualizado.getDescricao(),
                servicoAtualizado.getPreco(),
                servicoAtualizado.getData()
        );
    }

    // Metodo para buscar um serviço por ID
    public ServicoDTO buscarServicoPorId(Integer id) {
        // Busca o serviço pelo ID
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Serviço não encontrado"));

        // Retorna o DTO com os dados do serviço encontrado
        return new ServicoDTO(
                servico.getId(),
                servico.getCliente().getId(),
                servico.getFuncionario().getId(),
                servico.getEquipamento(),
                servico.getDescricao(),
                servico.getPreco(),
                servico.getData()
        );
    }

    public List<ServicoDTO> listarTodos() {
        List<Servico> servicos = servicoRepository.findAll();
        return servicos.stream()
                .map(servico -> new ServicoDTO(
                        servico.getId(),
                        servico.getCliente().getId(),
                        servico.getFuncionario().getId(),
                        servico.getEquipamento(),
                        servico.getDescricao(),
                        servico.getPreco(),
                        servico.getData()
                ))
                .toList();
    }

    // Método para excluir um serviço
    @Transactional
    public void excluirServico(Integer id) {
        // Verifica se o serviço existe antes de excluir
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new ServicoNotFoundException("Serviço não encontrado"));

        // Exclui o serviço
        servicoRepository.delete(servico);
    }
}
