package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Aluno;
import com.aluno.alunosmateriasnotas.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping("/novo")
    public @ResponseBody Aluno novoAluno(Aluno aluno){
       alunoRepository.save(aluno);
        return aluno;
    }
}
