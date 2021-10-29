package com.algaworks.algalog.domain.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;

}
