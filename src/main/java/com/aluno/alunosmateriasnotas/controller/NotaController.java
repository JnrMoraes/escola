package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.rest.client.INotaRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Nota>> buscarTodasNota() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.notaRepository.findAll());
        } catch (Exception e) {
            throw e;

        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Nota>> bucarNotaPorId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.notaRepository.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(this.notaRepository.findById(id));

        }
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
