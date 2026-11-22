package com.rotacerta.pedidos.repository;

import com.rotacerta.pedidos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
