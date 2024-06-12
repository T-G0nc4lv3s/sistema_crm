package dev.tsantana.sistema_crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.tsantana.sistema_crm.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

	public Optional<Endereco> findByCep(String cep);
}
