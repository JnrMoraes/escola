package com.aluno.alunosmateriasnotas.controller;


import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.dto.NotaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.entity.NotaModal;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import com.aluno.alunosmateriasnotas.service.IMateriaService;
import com.aluno.alunosmateriasnotas.service.INotaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastrarNota")
public class NotaModalController {

    @Autowired
    private INotaService notaService;

    @Autowired
    private IMateriaService materiaService;

    @Autowired
    private IMateriaRepository materiaRepository;

    private ModelMapper mapper;

    public NotaModalController() {
        this.mapper = new ModelMapper();
    }

    @PostMapping
    public ResponseEntity<Boolean> associarNotaEmMateria(@RequestBody NotaModal notaModal){
        MateriaDto materiaDto = this.materiaService.consultarMateriaPeloId(notaModal.getId());

        NotaDto notaDto = this.notaService.consultarNotaPeloId(notaModal.getNota());

    Nota nota = this.mapper.map(notaDto, Nota.class);

    if(materiaDto.getNota() == null){
        materiaDto.setNota(nota);
        Materia materia = this.mapper.map(materiaDto, Materia.class);
        this.materiaRepository.save(materia);

        }
    return ResponseEntity.status(HttpStatus.OK.value())
            .body(Boolean.TRUE);
    }
}
