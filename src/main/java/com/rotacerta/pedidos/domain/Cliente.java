package com.rotacerta.pedidos.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String cidade;

    protected Cliente() {
    }

    public Cliente(String nome, String email, String cidade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getCidade() { return cidade; }
}
