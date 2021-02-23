package com.aluno.alunosmateriasnotas.controller;


import com.aluno.alunosmateriasnotas.entity.NotaModal;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.service.INotaModalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
    private INotaModalService notaModalService;

    @PostMapping
    public ResponseEntity<Response<Boolean>> associarNotaEmMateria(@RequestBody NotaModal notaModal){
       Response<Boolean> response = new Response<>();
       response.setData(this.notaModalService.associarNotaEmMateria(notaModal));
       response.setStatusCode(HttpStatus.CREATED.value());
       response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(NotaModalController.class)
       .associarNotaEmMateria(notaModal)).withSelfRel());

    return ResponseEntity.status(HttpStatus.OK.value())
            .body(response);
    }
}
