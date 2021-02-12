package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Nota;
import com.aluno.alunosmateriasnotas.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @PostMapping
    @RequestMapping(value = "/nova")
    public @ResponseBody
    Nota novaNota(Nota nota){
        notaRepository.save(nota);
        return nota;
    }

    @RequestMapping(value = "/all")
    public Iterable<Nota> getNotas(){
        return notaRepository.findAll();
    }

    @PutMapping
    @RequestMapping(value = "/alterar")
    public @ResponseBody Nota updateNota(@RequestBody Nota nota) {

            notaRepository.save(nota);
            return nota;
    }

    @DeleteMapping
    @RequestMapping(value = "delete/{id}")
    public void deletarNota(@PathVariable Long id){
        notaRepository.deleteById(id);
    }

}
