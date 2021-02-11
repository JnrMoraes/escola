package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Materia;
import com.aluno.alunosmateriasnotas.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @PostMapping("/nova")
    public @ResponseBody Materia novaMateria(Materia materia){
        materiaRepository.save(materia);
        return materia;
    }
}
