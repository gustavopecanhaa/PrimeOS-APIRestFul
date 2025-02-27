package com.api.primeos.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

import java.time.LocalDateTime;

public record ServicoDTO(
        Integer id,

        @NotNull(message = "O ID do cliente não pode ser nulo")
        Integer clienteId,

        @NotNull(message = "O ID do funcionário não pode ser nulo")
        Integer funcionarioId,

        @NotNull(message = "O equipamento não pode ser nulo")
        @Size(min = 3, max = 255, message = "Equipamento deve ter entre 3 e 255 caracteres")
        String equipamento,

        @NotNull(message = "A descrição não pode ser nula")
        @Size(min = 5, max = 1000, message = "Descrição deve ter entre 5 e 1000 caracteres")
        String descricao,

        @NotNull(message = "O preço não pode ser nulo")
        @Min(value = 0, message = "O preço não pode ser negativo")
        Double preco,

        LocalDateTime data
) {}
