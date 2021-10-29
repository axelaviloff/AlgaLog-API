package com.algaworks.algalog.service.implementation;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algalog.domain.Cliente;
import com.algaworks.algalog.domain.dto.request.ClienteRequest;
import com.algaworks.algalog.domain.dto.response.ClienteResponse;
import com.algaworks.algalog.exception.NegocioException;
import com.algaworks.algalog.repository.ClienteRepository;
import com.algaworks.algalog.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteServiceImp implements ClienteService {
	
	private ClienteRepository clienteRepository;
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<List<ClienteResponse>> listar() {
		List<Cliente> clientes = clienteRepository.findAll();
		
		if (clientes.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<ClienteResponse> clienteResponse = clientes.stream()
				.map(cliente -> modelMapper.map(cliente, ClienteResponse.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok(clienteResponse);
		
	}
	
	public ResponseEntity<ClienteResponse> buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(modelMapper.map(cliente, ClienteResponse.class)))
				.orElse(ResponseEntity.notFound().build());
	}


	public ResponseEntity<ClienteResponse> adicionar(@Valid ClienteRequest cliente) {
		boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailExistente) {
			throw new NegocioException("Já existe esse e-mail em uso");
		}
		
		Cliente clienteResponse = clienteRepository.save(modelMapper.map(cliente, Cliente.class));
		
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest().path("{clienteId}")
				.buildAndExpand(clienteResponse.getId()).toUri();
		
		return ResponseEntity.created(local).body(modelMapper.map(clienteResponse, ClienteResponse.class));
		
	}

	public ResponseEntity<ClienteResponse> atualizar(@Valid Long clienteId, Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		
		return ResponseEntity.ok(modelMapper.map(cliente, ClienteResponse.class));
	}

	public ResponseEntity<Void> excluir(Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}

	
	
}
