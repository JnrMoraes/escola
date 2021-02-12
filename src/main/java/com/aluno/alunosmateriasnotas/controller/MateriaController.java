package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Materia;
import com.aluno.alunosmateriasnotas.repositories.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/materia")
public class MateriaController {

    @Autowired
    private MateriaRepository materiaRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Materia> getMateria(@PathVariable Long id){
        return materiaRepository.findById(id);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Iterable<Materia> getMaterias(){
        return materiaRepository.findAll();
    }

    @RequestMapping(value = "/nova", method = RequestMethod.POST)
    public @ResponseBody Materia novaMateria(Materia materia){
        materiaRepository.save(materia);
        return materia;
    }

    @RequestMapping(value = "/alterar", method = RequestMethod.PUT)
    public @ResponseBody Materia updateMateria(@RequestBody Materia materia){
        materiaRepository.save(materia);
        return materia;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
    public void deleteMateria(@PathVariable Long id){
        materiaRepository.deleteById(id);
    }

}


