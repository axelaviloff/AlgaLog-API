package com.algaworks.algalog.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteService {
	
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean emailExistente = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailExistente) {
			throw new NegocioException("Já existe esse e-mail em uso");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void exluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}

	
	
}
