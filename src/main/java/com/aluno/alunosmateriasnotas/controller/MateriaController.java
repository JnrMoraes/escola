package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaRepository materiaRepository;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody Materia materia) {
        try {
            this.materiaRepository.save(materia);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(true);
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
    public ResponseEntity<Optional<Materia>> buscarMateriaPeloId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.materiaRepository.findById(id));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(this.materiaRepository.findById(id));
        }
    }

    @PutMapping
    public ResponseEntity<Boolean> alterarMateria(@RequestBody Materia materia) {
        try {
            Materia materiaAtualizada = this.materiaRepository.findById(materia.getId()).get();
            materiaAtualizada.setNome(materia.getNome());
            this.materiaRepository.save(materiaAtualizada);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(false);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id) {
        try {
            this.materiaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(false);

        }
    }

}
