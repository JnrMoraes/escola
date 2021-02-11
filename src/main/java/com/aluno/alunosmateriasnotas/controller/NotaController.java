package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.model.entities.Nota;
import com.aluno.alunosmateriasnotas.repositories.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    public @ResponseBody Nota novaNOta(Nota nota){
        notaRepository.save(nota);
    return nota;
    }
}
