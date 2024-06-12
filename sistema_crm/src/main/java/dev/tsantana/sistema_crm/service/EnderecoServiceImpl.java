package dev.tsantana.sistema_crm.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import dev.tsantana.sistema_crm.model.Cliente;
import dev.tsantana.sistema_crm.model.Endereco;
import dev.tsantana.sistema_crm.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ViaCepService viaCepService;

	@Transactional
	@Override
	public Endereco inserirEndereco(Long clienteId, String cep, String numero) {
		Cliente cliente = clienteService.buscarPorId(clienteId);
		Endereco endereco = viaCepService.buscarPorCep(cep);
		endereco.setNumero(numero);
		endereco.setCliente(cliente);
		endereco = enderecoRepository.save(endereco);
		return endereco;
	}

	@Transactional(readOnly = true)
	@Override
	public Endereco buscarPorId(Long id) {
		Endereco entidade = enderecoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
		return entidade;
	}

	@Transactional(readOnly = true)
	@Override
	public Iterable<Endereco> buscarTodos() {
		List<Endereco> lista = enderecoRepository.findAll();
		return lista;
	}

	@Transactional
	@Override
	public Endereco atualizarEndereco(Long id, Endereco endereco) {
		try {
			Endereco entidade = enderecoRepository.getReferenceById(id);
			endereco.setId(entidade.getId());
			BeanUtils.copyProperties(endereco, entidade);
			entidade = enderecoRepository.save(entidade);
			return entidade;
		} catch (Exception e) {
			throw new EntityNotFoundException("Endereço não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public void deletarEndereco(Long id) {
		if (!enderecoRepository.existsById(id)) {
			throw new EntityNotFoundException();
		}
		enderecoRepository.deleteById(id);
	}

}
