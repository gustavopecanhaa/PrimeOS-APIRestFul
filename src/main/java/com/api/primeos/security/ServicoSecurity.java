package com.api.primeos.security;

import com.api.primeos.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("servicoSecurity")
public class ServicoSecurity {

    @Autowired
    private ServicoRepository servicoRepository;

    public boolean isServicoOwner(Integer servicoId, Integer funcionarioId) {
        return servicoRepository.findById(servicoId)
                .map(servico -> servico.getFuncionario().getId().equals(funcionarioId))
                .orElse(false);
    }
}
