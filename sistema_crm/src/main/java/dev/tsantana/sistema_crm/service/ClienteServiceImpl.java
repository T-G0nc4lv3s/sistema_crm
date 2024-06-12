package dev.tsantana.sistema_crm.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.tsantana.sistema_crm.model.Cliente;
import dev.tsantana.sistema_crm.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Transactional
	@Override
	public Cliente inserirCliente(Cliente cliente) {
		Cliente entidade = clienteRepository.save(cliente);
		return entidade;
	}

	@Transactional(readOnly = true)
	@Override
	public Cliente buscarPorId(Long id) {
		Cliente entidade = clienteRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
		return entidade;
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Cliente> buscarTodos() {
		Iterable<Cliente> lista = clienteRepository.findAll();
		return lista;
	}

	@Transactional
	@Override
	public Cliente atualizarCliente(Long id, Cliente cliente) {
		try {
			Cliente entidade = clienteRepository.getReferenceById(id);
			cliente.setId(entidade.getId());
			BeanUtils.copyProperties(cliente, entidade);
			entidade = clienteRepository.save(entidade);
			return entidade;
		} catch (Exception e) {
			throw new EntityNotFoundException("Cliente não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public void deletarCliente(Long id) {
		if (!clienteRepository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		clienteRepository.deleteById(id);

	}

}
