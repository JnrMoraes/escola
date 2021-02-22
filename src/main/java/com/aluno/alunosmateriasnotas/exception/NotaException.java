package com.aluno.alunosmateriasnotas.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public NotaException(final String mensgaem, final HttpStatus httpStatus ){
        super(mensgaem);
        this.httpStatus = httpStatus;
    }

}
