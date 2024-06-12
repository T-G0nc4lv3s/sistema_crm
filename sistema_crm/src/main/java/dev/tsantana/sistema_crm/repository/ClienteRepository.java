package dev.tsantana.sistema_crm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.tsantana.sistema_crm.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
