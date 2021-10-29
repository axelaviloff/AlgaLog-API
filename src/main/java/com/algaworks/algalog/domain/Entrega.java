package com.algaworks.algalog.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.algaworks.algalog.domain.enumerable.StatusEntrega;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	@Embedded
	private Destinatario destinatario;
	
	@NotNull
	private BigDecimal taxa;
	
	@Enumerated(EnumType.STRING)
	@JsonProperty(access = Access.READ_ONLY)
	private StatusEntrega status;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataPedido;
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime dataFinalizacao;

		
	
	
}
