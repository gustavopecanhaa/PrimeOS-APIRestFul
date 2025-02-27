package com.api.primeos.security;

import com.api.primeos.model.Funcionario;
import com.api.primeos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class CustomFuncionarioDetailsService implements UserDetailsService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Funcionario funcionario = funcionarioRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Funcionário não encontrado com login: " + username));
        return new FuncionarioDetails(funcionario);
    }
}
