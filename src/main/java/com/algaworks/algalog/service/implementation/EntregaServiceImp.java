package com.algaworks.algalog.service.implementation;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.algaworks.algalog.domain.Cliente;
import com.algaworks.algalog.domain.Entrega;
import com.algaworks.algalog.domain.dto.request.EntregaRequest;
import com.algaworks.algalog.domain.dto.response.EntregaResponse;
import com.algaworks.algalog.domain.enumerable.StatusEntrega;
import com.algaworks.algalog.exception.NegocioException;
import com.algaworks.algalog.repository.ClienteRepository;
import com.algaworks.algalog.repository.EntregaRepository;
import com.algaworks.algalog.service.EntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EntregaServiceImp implements EntregaService{
	
	private EntregaRepository entregaRepository;
	private ClienteRepository clienteRepository;
	private ModelMapper modelMapper;
	
	
	public ResponseEntity<List<EntregaResponse>> listar() {
		List<Entrega> entregas = entregaRepository.findAll();
		
		if (entregas.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<EntregaResponse> entregaResponse = entregas.stream()
				.map(entrega -> modelMapper.map(entrega, EntregaResponse.class)).collect(Collectors.toList());
		
		return ResponseEntity.ok(entregaResponse);
	}
	
	
	public ResponseEntity<EntregaResponse> consultarEntrega(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(modelMapper.map(entrega, EntregaResponse.class)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	public ResponseEntity<EntregaResponse> solicitarEntrega(@Valid EntregaRequest entrega) {
		Entrega e = modelMapper.map(entrega, Entrega.class);
		Optional<Cliente> cliente = clienteRepository.findById(e.getCliente().getId());
		
		if (cliente.isPresent()) {
			e.setCliente(cliente.get());
			e.setStatus(StatusEntrega.PENDENTE);
			e.setDataPedido(LocalDateTime.now());
			entregaRepository.save(e);
		} else {
			throw new NegocioException("Id do cliente inválido");
		}
				
		URI local = ServletUriComponentsBuilder
				.fromCurrentRequest().path("{entregaId}")
				.buildAndExpand(e.getId()).toUri();
		
		return ResponseEntity.created(local).body(modelMapper.map(e, EntregaResponse.class));
	}



	

}
