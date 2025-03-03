package com.api.primeos.repository;

import com.api.primeos.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

    List<Servico> findByClienteId(Integer clienteId);

    List<Servico> findByFuncionarioId(Integer funcionarioId);

}
