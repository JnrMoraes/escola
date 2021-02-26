package com.aluno.alunosmateriasnotas.controller;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
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
    public static void init(){
    }

    @Test
    void testBuscarTodosAlunos() {

        Mockito.when(this.alunoService.consultarAlunos())
                .thenReturn(new ArrayList<AlunoDto>());
        ResponseEntity<Response<List<AlunoDto>>> alunos = restTemplate
                .exchange("http://localhost:"+this.port+"/aluno", HttpMethod.GET, null,
                       new ParameterizedTypeReference<Response<List<AlunoDto>>>() {
        });
        assertNotNull(alunos.getBody().getData());
        assertEquals(200, alunos.getBody().getStatusCode());

    }
}