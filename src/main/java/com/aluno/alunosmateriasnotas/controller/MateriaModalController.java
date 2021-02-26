package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.entity.MateriaModal;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.service.IMateriaModalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastraMateria")
public class MateriaModalController {

    @Autowired
    private IMateriaModalService materiaModalService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> associarMateriasEmAluno(@RequestBody MateriaModal materiaModal) {
        Response<Boolean> response = new Response<>();
        response.setData(this.materiaModalService.associarMateriasEmAluno(materiaModal));
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaModalController.class )
                .associarMateriasEmAluno(materiaModal)).withSelfRel());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}




