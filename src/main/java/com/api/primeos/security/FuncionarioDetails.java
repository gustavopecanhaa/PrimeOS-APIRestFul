package com.api.primeos.security;

import com.api.primeos.model.Funcionario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class FuncionarioDetails implements UserDetails {

    private final Funcionario funcionario;

    public FuncionarioDetails(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Integer getId() {
        return funcionario.getId();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Usamos o campo 'funcao' para definir o papel. Ex.: "ADMIN" será mapeado para "ROLE_ADMIN"
        return List.of(new SimpleGrantedAuthority("ROLE_" + funcionario.getFuncao().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return funcionario.getSenha();
    }

    @Override
    public String getUsername() {
        return funcionario.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
