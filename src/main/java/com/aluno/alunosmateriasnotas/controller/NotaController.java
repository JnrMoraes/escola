package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.NotaDto;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.rest.client.INotaRepository;
import com.aluno.alunosmateriasnotas.service.INotaService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaController {

    //Remover quando finalizar o service;
    @Autowired
    private INotaRepository notaRepository;

    @Autowired
    private INotaService notaService;

    @PostMapping
    public ResponseEntity<Boolean> cadastrarNota(@RequestBody NotaDto nota) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.notaService.cadastrarNota(nota));
    }

    @GetMapping
    public ResponseEntity<List<NotaDto>> buscarTodasNota() {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.notaService.consultarNotas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaDto> bucarNotaPorId(@PathVariable Long id) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(this.notaService.consultarNotaPeloId(id));

    }

    @PutMapping
    public ResponseEntity<Boolean> alterarNota(@RequestBody Nota nota) {
        try {
            Nota notaAtualizada = this.notaRepository.findById(nota.getId()).get();
            notaAtualizada.setBimetre1(nota.getBimetre1());
            notaAtualizada.setBimetre2(nota.getBimetre2());
            notaAtualizada.setBimetre3(nota.getBimetre3());
            notaAtualizada.setBimetre4(nota.getBimetre4());
            notaAtualizada.setMediaBimentre1(nota.getMediaBimentre1());
            notaAtualizada.setMediaBimentre2(nota.getMediaBimentre2());
            notaAtualizada.setMediaBimentre3(nota.getMediaBimentre3());
            notaAtualizada.setMediaBimentre4(nota.getMediaBimentre4());
            notaAtualizada.setMediaFinal(nota.getMediaFinal());
            this.notaRepository.save(notaAtualizada);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);
        } catch (Exception e) {
            throw e;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletarNota(@PathVariable Long id) {
        try {
            this.notaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(true);

        } catch (Exception e) {
            throw e;

        }
    }
}
