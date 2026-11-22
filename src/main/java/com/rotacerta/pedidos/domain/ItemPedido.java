package com.rotacerta.pedidos.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precoUnitario;

    protected ItemPedido() {
    }

    public ItemPedido(String descricao, Integer quantidade, BigDecimal precoUnitario) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public Integer getQuantidade() { return quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
}
