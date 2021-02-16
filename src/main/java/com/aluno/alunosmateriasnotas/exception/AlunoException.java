package com.aluno.alunosmateriasnotas.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AlunoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;

    public AlunoException(final String mensgaem, final HttpStatus httpStatus ){
        super(mensgaem);
        this.httpStatus = httpStatus;
    }

}
