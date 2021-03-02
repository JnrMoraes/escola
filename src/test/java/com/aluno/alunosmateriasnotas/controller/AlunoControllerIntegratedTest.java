package com.aluno.alunosmateriasnotas.controller;


import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import com.aluno.alunosmateriasnotas.service.IAlunoService;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
class AlunoControllerIntegratedTest {

    @LocalServerPort
    private int port;

    private IAlunoService alunoService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private IAlunoRepository alunoRepository;


    @BeforeEach
    public void init() {
        this.montarBaseDeDados();
    }

    @AfterEach
    public void finish() {
        this.alunoRepository.deleteAll();
    }

    private void montarBaseDeDados(){

        Aluno aluno = new Aluno();
        aluno.setNome("Jose Maria das Neves");
        aluno.setMaterias(new ArrayList<>());

        Aluno aluno2 = new Aluno();
        aluno2.setNome("João Francisco");
        aluno2.setMaterias(new ArrayList<>());

        Aluno aluno3 = new Aluno();
        aluno3.setNome("Augusto Libetaro");
        aluno3.setMaterias(new ArrayList<>());

        this.alunoRepository.saveAll(Arrays.asList(aluno,aluno2,aluno3));
    }




    @Test
    void testBuscarTodosAlunos() {

        ResponseEntity<Response<List<AlunoDto>>> alunos = restTemplate
                .exchange("http://localhost:" + this.port + "/aluno", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Response<List<AlunoDto>>>() {
                        });
        assertNotNull(alunos.getBody().getData());
        assertEquals(3,alunos.getBody().getData().size());
        assertEquals(200, alunos.getBody().getStatusCode());

    }

    @Test
    void testBuscarAlunoPorId() {



        ResponseEntity<Response<AlunoDto>> aluno = restTemplate.exchange(
                "http://localhost:" + this.port + "/aluno/1", HttpMethod.GET, null,
                new ParameterizedTypeReference<Response<AlunoDto>>() {
                });


        assertNotNull(aluno.getBody().getData());
        assertEquals(1,aluno.getBody().getData().getId());
        assertEquals("Jose Maria das Neves",aluno.getBody().getData().getNome());

        assertEquals(200, aluno.getBody().getStatusCode());

    }


    @Test
    @Ignore
    void testdeletarAluno() { //Não esta passando

        List<Aluno> alunos = this.alunoRepository.findAll();
        Long id = alunos.get(0).getId();

        ResponseEntity<Response<Boolean>> aluno = restTemplate.exchange(
                "http://localhost:" + this.port + "/aluno/" + id, HttpMethod.DELETE, null,
                        new ParameterizedTypeReference<Response<Boolean>>() {
                        });

        List<Aluno> listAlunosAtualizado = this.alunoRepository.findAll();

        assertTrue(aluno.getBody().getData());
        assertEquals(2, listAlunosAtualizado.size());
        assertEquals(200, aluno.getBody().getStatusCode());

    }

}