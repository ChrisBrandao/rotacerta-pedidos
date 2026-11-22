package com.rotacerta.pedidos.web.dto;

import com.rotacerta.pedidos.domain.StatusPedido;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusRequest(@NotNull StatusPedido status) {
}
