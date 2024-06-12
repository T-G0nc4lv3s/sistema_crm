package dev.tsantana.sistema_crm.service;

import dev.tsantana.sistema_crm.model.Endereco;

public interface EnderecoService {

	Endereco inserirEndereco(Long clienteId, String cep, String numero);

	Endereco buscarPorId(Long id);

	Iterable<Endereco> buscarTodos();

	Endereco atualizarEndereco(Long id, Endereco endereco);

	void deletarEndereco(Long id);
}
