package com.algaworks.algalog.validation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.algaworks.algalog.domain.exception.NegocioException;
import com.algaworks.algalog.domain.model.Cliente;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestControllerAdvice
public class ApiExceptionHandler {
	
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorForm> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		
		
		List<ErrorForm.Campo> campos = new ArrayList<>();
		
		for (ObjectError error: ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new ErrorForm.Campo(nome, mensagem));
		}
		
		ErrorForm errorForm = new ErrorForm();
		errorForm.setDataHora(LocalDateTime.now());
		errorForm.setTitulo("Um ou mais campos estão inválidos");
		errorForm.setCampos(campos);
		
		return ResponseEntity.badRequest().body(errorForm);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex) {
		
		ErrorForm errorForm = new ErrorForm();
		errorForm.setDataHora(LocalDateTime.now());
		errorForm.setTitulo(ex.getMessage());
		
		return ResponseEntity.badRequest().body(errorForm);
		
	}

	
	
}
