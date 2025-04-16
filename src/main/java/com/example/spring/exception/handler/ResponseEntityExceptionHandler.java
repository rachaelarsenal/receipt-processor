package com.example.spring.exception.handler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ResponseEntityExceptionHandler {

	@ExceptionHandler(
		{
			IllegalArgumentException.class,
			MethodArgumentNotValidException.class,
			ConstraintViolationException.class,
			HttpMessageNotReadableException.class
		}
	)
	public ResponseEntity<Map<String, String>> handleException(
		Exception exception) {

		String message = "The receipt is invalid.";

		if (_isLargeLanguageModel) {
			message += " Please verify input.";
		}

		return ResponseEntity.status(
			HttpStatus.BAD_REQUEST
		).body(
			Collections.singletonMap("error", message)
		);
	}

	@Value("${large.language.model:false}")
	private boolean _isLargeLanguageModel;

}
