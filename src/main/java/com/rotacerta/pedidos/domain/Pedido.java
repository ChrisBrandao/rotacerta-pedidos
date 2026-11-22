package com.rotacerta.pedidos.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "pedido_id")
    private List<ItemPedido> itens = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @Column(name = "valor_total", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    protected Pedido() {
    }

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.status = StatusPedido.CRIADO;
        this.valorTotal = BigDecimal.ZERO;
        this.criadoEm = LocalDateTime.now();
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
    }

    public Long getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public List<ItemPedido> getItens() { return itens; }
    public StatusPedido getStatus() { return status; }
    public BigDecimal getValorTotal() { return valorTotal; }
    public LocalDateTime getCriadoEm() { return criadoEm; }

    public void setStatus(StatusPedido status) { this.status = status; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
