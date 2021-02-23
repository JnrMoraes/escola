package com.aluno.alunosmateriasnotas.handler;

import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.exception.MateriaException;
import com.aluno.alunosmateriasnotas.exception.NotaException;
import com.aluno.alunosmateriasnotas.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Map<String, String>>> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();

        exception.getBindingResult().getAllErrors()
                .forEach(erro -> {
                    String campo = ((FieldError) erro).getField();
                    String mensagem = erro.getDefaultMessage();
                    error.put(campo, mensagem);
                });

        Response<Map<String, String>> response = new Response<>();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setData(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);

    }


    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<Response<String>> handlerAlunoException(AlunoException exception) {
        Response<String> response = new Response<>();
        response.setStatusCode(exception.getHttpStatus().value());
        response.setData(exception.getMessage());

        return ResponseEntity.status(exception.getHttpStatus()).body(response);

    }

    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<Response<String>> handlerMateriaException(MateriaException exception) {
        Response<String> response = new Response<>();
        response.setStatusCode(exception.getHttpStatus().value());
        response.setData(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(response);

    }

    @ExceptionHandler(NotaException.class)
    public ResponseEntity<Response<String>> handlerNotaException(NotaException exception) {
        Response<String> response = new Response<>();
        response.setStatusCode(exception.getHttpStatus().value());
        response.setData(exception.getMessage());
        return ResponseEntity.status(exception.getHttpStatus()).body(response);

    }


}
