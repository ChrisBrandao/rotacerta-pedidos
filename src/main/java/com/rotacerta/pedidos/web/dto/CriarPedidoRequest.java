package com.rotacerta.pedidos.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record CriarPedidoRequest(
        @NotNull Long clienteId,
        @NotEmpty @Valid List<ItemRequest> itens
) {
    public record ItemRequest(
            @NotBlank String descricao,
            @NotNull @Positive Integer quantidade,
            @NotNull @DecimalMin("0.01") BigDecimal precoUnitario
    ) {
    }
}
