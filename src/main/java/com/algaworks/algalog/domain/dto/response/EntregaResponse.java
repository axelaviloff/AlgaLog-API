package com.algaworks.algalog.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.algaworks.algalog.domain.enumerable.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaResponse {
	
	private Long id;
	private String nomeCliente;
	private DestinatarioResponse destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private LocalDateTime dataPedido;
	private LocalDateTime dataFinalizacao;
	

}
