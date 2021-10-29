package com.algaworks.algalog.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.algaworks.algalog.domain.dto.request.EntregaRequest;
import com.algaworks.algalog.domain.dto.response.EntregaResponse;

public interface EntregaService {
	
	public ResponseEntity<List<EntregaResponse>> listar();
	public ResponseEntity<EntregaResponse> consultarEntrega(Long entregaId);
	public ResponseEntity<EntregaResponse> solicitarEntrega(@Valid EntregaRequest entrega);

}
