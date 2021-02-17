package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.service.IAlunoService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private static final String DELETE = "DELETE";
    private static final String UDPATE = "UPDATE";
    @Autowired
    private IAlunoService alunoService;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarAluno(@Valid @RequestBody AlunoDto aluno) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.alunoService.cadastrarAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Response<List<AlunoDto>>> getAlunoS() {
        Response<List<AlunoDto>> response = new Response<>();
        response.setData(this.alunoService.consultarAlunos());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(AlunoController.class)
                .getAlunoS()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<Response<AlunoDto>> alunoById(@PathVariable Long id) {
        Response<AlunoDto> response = new Response<>();
        response.setData(this.alunoService.consultarAlunoPeloId(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(AlunoController.class)
                .alunoById(id)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(AlunoController.class)
                .deletarAluno(id)).withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
                .methodOn(AlunoController.class)
                .deletarAluno(id)).withRel(UDPATE));
        return ResponseEntity.status(response.getStatusCode())
                .body(response);
    }

    @PutMapping
    public ResponseEntity<Boolean> alterarAluno(@RequestBody AlunoDto aluno) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.alunoService.alterarAluno(aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarAluno(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.alunoService.excluirAluno(id));
    }
}


