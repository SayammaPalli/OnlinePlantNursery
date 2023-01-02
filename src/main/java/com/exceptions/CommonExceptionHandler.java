package com.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> handleValidationExceptions(MethodArgumentNotValidException exception) {
		Map<String,String> errors = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error) -> {String fieldName = ((FieldError) error).getField();
		String errorMessage = error.getDefaultMessage();
		errors.put(fieldName, errorMessage);
		});
		
		return errors;
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ErrorMessage handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
		return new ErrorMessage("400","The values of few fields are INVALID");
	}
	
	//order
	@ExceptionHandler(OrderIdNotFoundException.class)
	ResponseEntity<?> OrderIdNotFound(OrderIdNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
	}
	
	//planter
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<?> exceptionHandler(ResourceNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsufficientStockException.class)
	ResponseEntity<?> exceptionHandler(InsufficientStockException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	//plant
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PlantIdNotFoundException.class)
	ResponseEntity<?>  handleNullPointerException(PlantIdNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);		
	}
	
	//seed
	@ExceptionHandler(SeedIdNotFoundException.class)
	ResponseEntity<?> SeedIdNotFound(SeedIdNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(OutOfStockException.class)
	ResponseEntity<?> OutOfStock(OutOfStockException oex){
		ErrorMessage errorMessage = new ErrorMessage("400",oex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	//user
	@ExceptionHandler(UserNotFoundException.class)
	ResponseEntity<?> UserNotFound(UserNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}
	
	//user
	@ExceptionHandler(UserAlreadyExists.class)
	ResponseEntity<?> UserAlreadtExists(UserAlreadyExists ex) {
		ErrorMessage errorMessage = new ErrorMessage("400", ex.getMessage());
		return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
	}



}