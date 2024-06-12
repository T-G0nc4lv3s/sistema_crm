package dev.tsantana.sistema_crm.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dev.tsantana.sistema_crm.model.Endereco;
import dev.tsantana.sistema_crm.service.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoController {

	@Autowired
	private EnderecoService enderecoService;

	@PostMapping
	public ResponseEntity<Endereco> inserirEndereco(Long clienteId, String cep, String numero) {
		Endereco response = enderecoService.inserirEndereco(clienteId, cep, numero);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/clientId}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Endereco>> buscarTodos() {
		List<Endereco> response = (List<Endereco>) enderecoService.buscarTodos();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{enderecoId}")
	public ResponseEntity<Endereco> buscarPorId(@PathVariable Long enderecoId) {
		Endereco response = enderecoService.buscarPorId(enderecoId);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{enderecoId}")
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long enderecoId, @RequestBody Endereco endereco) {
		Endereco response = enderecoService.atualizarEndereco(enderecoId, endereco);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{enderecoId}")
	public ResponseEntity<Void> deletarEndereco(@PathVariable Long enderecoId) {
		enderecoService.deletarEndereco(enderecoId);
		return ResponseEntity.noContent().build();
	}

}
