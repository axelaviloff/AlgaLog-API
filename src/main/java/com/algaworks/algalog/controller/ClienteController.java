package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.Cliente;
import com.algaworks.algalog.domain.dto.request.ClienteRequest;
import com.algaworks.algalog.domain.dto.response.ClienteResponse;
import com.algaworks.algalog.service.implementation.ClienteServiceImp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "clientes")
public class ClienteController {
	
	private ClienteServiceImp clienteService;
	
	@GetMapping
	public ResponseEntity<List<ClienteResponse>> listar() {
		return clienteService.listar();
		
	}
	
	@GetMapping(path = "{clienteId}")
	public ResponseEntity<ClienteResponse> buscar(@PathVariable Long clienteId) {
		return clienteService.buscar(clienteId);
				
	}
	
	@PostMapping
	public ResponseEntity<ClienteResponse> adicionar(@Valid @RequestBody ClienteRequest cliente) {
		return clienteService.adicionar(cliente);
	}
	
	@PutMapping(path = "{clienteId}")
	public ResponseEntity<ClienteResponse> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
		return clienteService.atualizar(clienteId, cliente);
		
	}
	
	@DeleteMapping(path = "{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		return clienteService.excluir(clienteId);
		
	}

}
