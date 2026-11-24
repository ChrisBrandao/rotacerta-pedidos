package com.rotacerta.pedidos.service;

import com.rotacerta.pedidos.domain.Cliente;
import com.rotacerta.pedidos.domain.ItemPedido;
import com.rotacerta.pedidos.domain.Pedido;
import com.rotacerta.pedidos.domain.StatusPedido;
import com.rotacerta.pedidos.exception.RecursoNaoEncontradoException;
import com.rotacerta.pedidos.repository.ClienteRepository;
import com.rotacerta.pedidos.repository.PedidoRepository;
import com.rotacerta.pedidos.web.dto.CriarPedidoRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    public PedidoService(PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public Pedido criar(CriarPedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente " + request.clienteId() + " não encontrado"));

        Pedido pedido = new Pedido(cliente);
        BigDecimal total = BigDecimal.ZERO;

        for (CriarPedidoRequest.ItemRequest itemReq : request.itens()) {
            ItemPedido item = new ItemPedido(itemReq.descricao(), itemReq.quantidade(), itemReq.precoUnitario());
            pedido.adicionarItem(item);
            total = total.add(item.getPrecoUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())));
        }

        pedido.setValorTotal(total);
        return pedidoRepository.save(pedido);
    }

    @Transactional(readOnly = true)
    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Pedido " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = buscarPorId(id);
        pedido.alterarStatus(novoStatus);
        return pedidoRepository.save(pedido);
    }
}
