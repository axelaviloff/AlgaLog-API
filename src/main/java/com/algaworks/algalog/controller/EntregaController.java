package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.dto.request.EntregaRequest;
import com.algaworks.algalog.domain.dto.response.EntregaResponse;
import com.algaworks.algalog.service.implementation.EntregaServiceImp;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path = "entregas")
public class EntregaController {
	
	private EntregaServiceImp entregaService;
	
	@GetMapping
	public ResponseEntity<List<EntregaResponse>> listar() {
		return entregaService.listar();
		
	}
	
	@GetMapping(path = "{entregaId}")
	public ResponseEntity<EntregaResponse> consultarEntrega(@PathVariable Long entregaId) {
		return entregaService.consultarEntrega(entregaId);
		
	}
	
	
	@PostMapping
	public ResponseEntity<EntregaResponse> solicitarEntrega(@Valid @RequestBody EntregaRequest entrega) {
		return entregaService.solicitarEntrega(entrega);
				
		
	}

}
