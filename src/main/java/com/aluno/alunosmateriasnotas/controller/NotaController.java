package com.aluno.alunosmateriasnotas.controller;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class NotaController {


    @PostMapping
    public ResponseEntity<Void> cadastrarNota() {
        throw new NotYetImplementedException();
    }
    @GetMapping
    public ResponseEntity<Void> buscarTodasNota() {
        throw new NotYetImplementedException();
    }
    @GetMapping
    public ResponseEntity<Void> bucarNotaPorId() {
        throw new NotYetImplementedException();
    }
    @PutMapping
    public ResponseEntity<Void> alterarNota() {
        throw new NotYetImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarNota() {
        throw new NotYetImplementedException();
    }
}
