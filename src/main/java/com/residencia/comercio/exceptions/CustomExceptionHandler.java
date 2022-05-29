package com.residencia.comercio.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
      List<String> details = new ArrayList<>();
      details.add(ex.getLocalizedMessage());
      HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Erro Interno no Servidor", details);
      
      return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   
    @ExceptionHandler(NoSuchElementFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NoSuchElementFoundException ex, WebRequest request) {
      List<String> details = new ArrayList<>();
      details.add(ex.getLocalizedMessage());
      HttpStatus httpStatus = HttpStatus.NOT_FOUND;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Registro Não Encontrado", details);
      return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
   
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
      List<String> details = new ArrayList<>();
      for(ObjectError error : ex.getBindingResult().getAllErrors()) {
        details.add(error.getDefaultMessage());
      }
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Falha na Validação dos Dados da Requisição", details);
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NotNullException.class)
    public final ResponseEntity<Object> handleNotNullException(NotNullException ex, WebRequest request) {
      List<String> details = new ArrayList<>();
      details.add(ex.getLocalizedMessage());
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "Campo não pode ser nulo", details);
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(CNPJException.class)
    public final ResponseEntity<Object> handleNotNullException(CNPJException ex, WebRequest request) {
      List<String> details = new ArrayList<>();
      details.add(ex.getLocalizedMessage());
      HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
      ErrorResponse error = new ErrorResponse(httpStatus.value(), "CNPJ no formato errado", details);
      return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}