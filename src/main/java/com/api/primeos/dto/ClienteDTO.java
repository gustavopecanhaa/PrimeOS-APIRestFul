package com.api.primeos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        Integer id,

        @NotBlank(message = "Nome é obrigatório.")
        String nome,

        @NotBlank(message = "Telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "Endereço é obrigatório.")
        String endereco,

        @NotBlank(message = "Email é obrigatório.")
        String email
) {}
