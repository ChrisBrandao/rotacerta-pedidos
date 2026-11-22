package com.rotacerta.pedidos.web;

import com.rotacerta.pedidos.domain.Pedido;
import com.rotacerta.pedidos.service.PedidoService;
import com.rotacerta.pedidos.web.dto.AtualizarStatusRequest;
import com.rotacerta.pedidos.web.dto.CriarPedidoRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido criar(@RequestBody @Valid CriarPedidoRequest request) {
        return pedidoService.criar(request);
    }

    @GetMapping("/{id}")
    public Pedido buscar(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @GetMapping
    public List<Pedido> listar() {
        return pedidoService.listarTodos();
    }

    @PatchMapping("/{id}/status")
    public Pedido atualizarStatus(@PathVariable Long id, @RequestBody @Valid AtualizarStatusRequest request) {
        return pedidoService.atualizarStatus(id, request.status());
    }
}
