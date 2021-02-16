package com.aluno.alunosmateriasnotas.handler;

import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.model.ErrorResponse;
import com.aluno.alunosmateriasnotas.model.ErrorResponse.ErrorResponseBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<ErrorResponse> handleAlunoException(AlunoException exception){
        ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(exception.getHttpStatus().value());
        erro.mensagem(exception.getMessage());
        erro.timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(exception.getHttpStatus())
                .body(erro.build());
    }
}
