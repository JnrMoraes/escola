package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.service.IAlunoService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private IAlunoService alunoService;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarAluno(@RequestBody AlunoDto aluno) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.alunoService.cadastrarAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoDto>> getAlunoS() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(alunoService.buscarAlunoS());
    }

    @GetMapping("/{id}")
    public @ResponseBody
    ResponseEntity<AlunoDto> alunoById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.alunoService.buscarAlunoPeloId(id));
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


