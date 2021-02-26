package com.aluno.alunosmateriasnotas.controller;


import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.model.Response;
import com.aluno.alunosmateriasnotas.service.IAlunoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(JUnitPlatform.class)
class AlunoControllerUnitTest {

    @LocalServerPort
    private int port;

    @MockBean
    private IAlunoService alunoService;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void init() {

        MateriaDto materiaDto = new MateriaDto();
        materiaDto.setId(2L);
        materiaDto.setNome("Ciencias");

        Materia materia1 = new Materia();
        materia1.setNome("Portugues");

        Materia materia2 = new Materia();
        materia2.setNome("Matemática");

        List<Materia> materias = new ArrayList<>();
        materias.add(materia1);
        materias.add(materia2);

        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");
        alunoDto.setMaterias(materias);

    }


    @Test
    void testCadastrarAluno() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        Mockito.when(this.alunoService.cadastrarAluno(alunoDto))
                .thenReturn(Boolean.TRUE);

        HttpEntity<AlunoDto> request = new HttpEntity<>(alunoDto);

        ResponseEntity<Response<Boolean>> alunoCriado = restTemplate
                .exchange("http://localhost:" + this.port + "/aluno",HttpMethod.POST, request,
                        new ParameterizedTypeReference<Response<Boolean>>(){
                        });
        assertNotNull(alunoCriado.getBody().getData());
        assertEquals(201, alunoCriado.getBody().getStatusCode());
    }

    @Test
    void testBuscarTodosAlunos() {

        Mockito.when(this.alunoService.consultarAlunos())
                .thenReturn(new ArrayList<AlunoDto>());
        ResponseEntity<Response<List<AlunoDto>>> alunos = restTemplate
                .exchange("http://localhost:" + this.port + "/aluno", HttpMethod.GET, null,
                        new ParameterizedTypeReference<Response<List<AlunoDto>>>() {
                        });
        assertNotNull(alunos.getBody().getData());
        assertEquals(200, alunos.getBody().getStatusCode());

    }

    @Test
    void alterarAluno() {
    }

    @Test
    void deletarAluno() {
    }

}