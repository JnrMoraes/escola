package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Aluno;
import com.aluno.alunosmateriasnotas.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Aluno> getAlunos(){
        return alunoRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Aluno> getAlunoId(@PathVariable Long id){
        return alunoRepository.findById(id);
    }


    @RequestMapping(value = "/novo",
            method = RequestMethod.POST)
    public @ResponseBody
    Aluno novoAluno(Aluno aluno){
       alunoRepository.save(aluno);
        return aluno;
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public @ResponseBody Aluno updateAluno(@RequestBody Aluno aluno){
        alunoRepository.save(aluno);
        return aluno;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deletarAluno(@PathVariable Long id){
        alunoRepository.deleteById(id);
    }

}
