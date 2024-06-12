package dev.tsantana.sistema_crm.service;

import dev.tsantana.sistema_crm.model.Cliente;

public interface ClienteService {

	Cliente inserirCliente(Cliente cliente);

	Cliente buscarPorId(Long id);

	Iterable<Cliente> buscarTodos();

	Cliente atualizarCliente(Long id, Cliente cliente);

	void deletarCliente(Long id);
}
