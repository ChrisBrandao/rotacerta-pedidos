package com.rotacerta.pedidos.service;

import com.rotacerta.pedidos.domain.Cliente;
import com.rotacerta.pedidos.domain.Pedido;
import com.rotacerta.pedidos.domain.StatusPedido;
import com.rotacerta.pedidos.repository.ClienteRepository;
import com.rotacerta.pedidos.repository.PedidoRepository;
import com.rotacerta.pedidos.web.dto.CriarPedidoRequest;
import com.rotacerta.pedidos.web.dto.CriarPedidoRequest.ItemRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @BeforeEach
    void setUp() {
        when(clienteRepository.findById(1L))
                .thenReturn(Optional.of(new Cliente("Cliente Teste", "teste@teste.com", "Cabo Frio")));
        when(pedidoRepository.save(any(Pedido.class))).thenAnswer(inv -> inv.getArgument(0));
    }

    @Test
    void deveCriarPedidoComStatusCriado() {
        var request = new CriarPedidoRequest(1L, List.of(
                new ItemRequest("Caixa de papelao", 1, new BigDecimal("10.00"))
        ));

        Pedido pedido = pedidoService.criar(request);

        assertThat(pedido.getStatus()).isEqualTo(StatusPedido.CRIADO);
        assertThat(pedido.getItens()).hasSize(1);
    }

    @Test
    void deveCalcularValorTotalConsiderandoQuantidade() {
        var request = new CriarPedidoRequest(1L, List.of(
                new ItemRequest("Fita adesiva", 3, new BigDecimal("4.50")),
                new ItemRequest("Etiqueta termica (rolo)", 2, new BigDecimal("25.00"))
        ));

        Pedido pedido = pedidoService.criar(request);

        // 3 x 4.50 + 2 x 25.00 = 63.50
        assertThat(pedido.getValorTotal()).isEqualByComparingTo("63.50");
    }
}
