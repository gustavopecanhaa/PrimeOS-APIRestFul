package com.api.primeos.dto;

import jakarta.validation.constraints.*;

public record FuncionarioDTO(
        Integer id,

        @NotBlank(message = "O campo 'nome' é obrigatório.")
        @Size(max = 255, message = "O nome não pode ter mais de 255 caracteres.")
        String nome,

        @NotBlank(message = "O campo 'funcao' é obrigatório.")
        @Size(max = 100, message = "A função não pode ter mais de 100 caracteres.")
        String funcao,

        @NotBlank(message = "O campo 'telefone' é obrigatório.")
        String telefone,

        @NotBlank(message = "O campo 'login' é obrigatório.")
        @Size(max = 100, message = "O login não pode ter mais de 100 caracteres.")
        String login,

        @NotBlank(message = "O campo 'senha' é obrigatório.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String senha
) {}
