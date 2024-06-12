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

import dev.tsantana.sistema_crm.model.Cliente;
import dev.tsantana.sistema_crm.service.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@PostMapping
	public ResponseEntity<Cliente> inserirCliente(@RequestBody Cliente cliente) {
		Cliente response = clienteService.inserirCliente(cliente);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/clientId}").buildAndExpand(response.getId())
				.toUri();
		return ResponseEntity.created(uri).body(response);
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodos() {
		List<Cliente> response = (List<Cliente>) clienteService.buscarTodos();
		return ResponseEntity.ok(response);
	}

	@GetMapping(value = "/{clienteId}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long clienteId) {
		Cliente response = clienteService.buscarPorId(clienteId);
		return ResponseEntity.ok(response);
	}

	@PutMapping(value = "/{clienteId}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		Cliente response = clienteService.atualizarCliente(clienteId, cliente);
		return ResponseEntity.ok(response);
	}

	@DeleteMapping(value = "/{clienteId}")
	public ResponseEntity<Void> deletarCliente(@PathVariable Long clienteId) {
		clienteService.deletarCliente(clienteId);
		return ResponseEntity.noContent().build();
	}
}
