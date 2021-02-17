package com.aluno.alunosmateriasnotas.handler;

import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.model.ErrorMapResponse;
import com.aluno.alunosmateriasnotas.model.ErrorMapResponse.ErrorMapResponseBuilder;
import com.aluno.alunosmateriasnotas.model.ErrorResponse;
import com.aluno.alunosmateriasnotas.model.ErrorResponse.ErrorResponseBuilder;
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
    public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> error = new HashMap<>();

        exception.getBindingResult().getAllErrors()
                .forEach(erro -> {
                    String campo = ((FieldError) erro).getField();
                    String mensagem = erro.getDefaultMessage();
                    error.put(campo, mensagem);
                });

//        for (ObjectError erro : exception.getBindingResult().getAllErrors()) {
//            String campo = ((FieldError) erro).getField();
//            String mensagem = erro.getDefaultMessage();
//            error.put(campo, mensagem);
//    }

        ErrorMapResponseBuilder erroMap = ErrorMapResponse.builder();
        erroMap.erros(error)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(erroMap.build());
    }


    @ExceptionHandler(AlunoException.class)
    public ResponseEntity<ErrorResponse> handlerAlunoException(AlunoException exception) {
        ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(exception.getHttpStatus().value());
        erro.mensagem(exception.getMessage());
        erro.timeStamp(System.currentTimeMillis());
        return ResponseEntity.status(exception.getHttpStatus())
                .body(erro.build());
    }
}
