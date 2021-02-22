package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.service.IMateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateria(@Valid @RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.materiaService.cadastrarMateria(materia));
    }


    @GetMapping
    public ResponseEntity<Response<List<MateriaDto>>> buscarTodasMaterias() {
        Response<List<MateriaDto>> response = new Response<>();
        response.setData(this.materiaService.consultarMaterias());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
        .methodOn(MateriaController.class)
                .buscarTodasMaterias()).withSelfRel());

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MateriaDto> buscarMateriaPeloId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.materiaService.consultarMateriaPeloId(id));
    }

    @PutMapping
    public ResponseEntity<Boolean> alterarMateria(@RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.materiaService.alterarMateria(materia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarMateria(@PathVariable Long id) {
        try {
            this.materiaService.excluirMateria(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(false);

        }
    }

}
