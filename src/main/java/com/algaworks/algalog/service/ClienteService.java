package com.algaworks.algalog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.algaworks.algalog.domain.Cliente;
import com.algaworks.algalog.domain.dto.request.ClienteRequest;
import com.algaworks.algalog.domain.dto.response.ClienteResponse;

public interface ClienteService {
	
	public ResponseEntity<List<ClienteResponse>> listar();
	public ResponseEntity<ClienteResponse> buscar(Long clienteId);
	public ResponseEntity<ClienteResponse> adicionar(@Valid ClienteRequest cliente);
	public ResponseEntity<ClienteResponse> atualizar(@Valid Long clienteId, Cliente cliente);
	public ResponseEntity<Void> excluir(Long clienteId);
	

}
