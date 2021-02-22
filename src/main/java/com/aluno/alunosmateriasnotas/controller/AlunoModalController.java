package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.AlunoModal;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import com.aluno.alunosmateriasnotas.service.AlunoService;
import com.aluno.alunosmateriasnotas.service.MateriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cadastraMateria")
public class AlunoModalController {

    @Autowired
    AlunoService alunoService;

    @Autowired
    IAlunoRepository alunoRepository;
    @Autowired
    MateriaService materiaService;

    @Autowired
    IMateriaRepository materiaRepository;

    public final ModelMapper mapper;

    @Autowired
    public AlunoModalController(AlunoService alunoService, MateriaService materiaService) {
        this.alunoService = alunoService;
        this.materiaService = materiaService;
        this.mapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<Boolean> cadastrarMateriaEmAluno(@RequestBody AlunoModal alunoModal){

        AlunoDto aluno = this.alunoService.consultarAlunoPeloId(alunoModal.getId());
        if (aluno.getId() != null){

            List<Materia> materiaList = new ArrayList<>();

            if (!alunoModal.getMaterias().isEmpty()) {
                alunoModal.getMaterias().forEach( materia ->{
                    if(this.materiaRepository.findById(materia).isPresent()){
                        materiaList.add(this.materiaRepository.findById(materia).get());
                    }
                });
                aluno.setMaterias(materiaList);
                this.alunoService.alterarAluno(aluno);

            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Boolean.TRUE);

        }
        throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                HttpStatus.NOT_FOUND);

    }
}




