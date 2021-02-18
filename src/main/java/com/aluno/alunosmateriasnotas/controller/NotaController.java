package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.rest.client.INotaRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private INotaRepository notaRepository;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarNota(@RequestBody Nota nota) {
        try {
            this.notaRepository.save(nota);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(true);
        } catch (Exception e) {
            throw e;
        }
    }
    @GetMapping
    public ResponseEntity<Void> buscarTodasNota() {
        throw new NotYetImplementedException();
    }
    @GetMapping("/{id}")
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
