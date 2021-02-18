package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.rest.client.MateriaRepository;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @PostMapping
    public ResponseEntity<Materia> cadastrarMateria(@Valid @RequestBody Materia materia) {
        try {
            this.materiaRepository.save(materia);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(materia);
        } catch (Exception e) {
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<List<Materia>> buscarTodasMaterias() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.materiaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> buscarMateriaPeloId() {

        throw new NotYetImplementedException();
    }

    @PutMapping
    public ResponseEntity<Void> alterarMateria() {
        throw new NotYetImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMateria() {
        throw new NotYetImplementedException();
    }

}
