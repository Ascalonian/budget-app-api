package com.majicode.budgetapp.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.majicode.budgetapp.api.ApiUtil;
import com.majicode.budgetapp.model.Error;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validExceptionHandler(MethodArgumentNotValidException exception) {
		final Error error = ApiUtil.generateError("Validation Error with request", "One or more fields did not validate");
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
