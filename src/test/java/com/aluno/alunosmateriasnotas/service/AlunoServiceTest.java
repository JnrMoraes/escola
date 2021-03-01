package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AlunoServiceTest {

    @Mock
    private IAlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private static Aluno aluno;
    private static Materia materia;
    private static Nota nota;


    @BeforeAll
    public static void init(){
        nota = new Nota();
        nota.setBimetre1(new double[]{7.9, 7.7, 9.8, 6.9});
        nota.setMediaBimentre1(7.9);


        materia = new Materia();
        materia.setId(2L);
        materia.setNome("Portugues");
        materia.setNota(nota);
//
//        List<Materia> materias = new ArrayList<>();
//        materias.add(materia);

        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Jose de Jesus");
//        alunoDto.setMaterias(materias);
    }


    @Test
    void cadastrarAluno() {

    }

    @Test
    void alterarAluno() {
    }

    @Test
    void consultarAlunosComSucesso() {
        List<Aluno> alunoList = new ArrayList<>();
        alunoList.add(aluno);

        Mockito.when(this.alunoRepository.findAll()).thenReturn(alunoList);

        List<AlunoDto> listAlunoDto = this.alunoService.consultarAlunos();

        assertNotNull(listAlunoDto);
        assertEquals("Jose de Jesus", listAlunoDto.get(0).getNome());
        assertEquals(1, listAlunoDto.get(0).getId());
        assertEquals("/aluno/1", listAlunoDto.get(0).getLinks().getRequiredLink("self").getHref());
        assertEquals(1, listAlunoDto.size());

        Mockito.verify(this.alunoRepository, times(1)).findAll();
    }

    @Test
    void consultarAlunoPeloId() {
    }

    @Test
    void excluirAluno() {
    }
}

//cenarios de sucesso

// cenarios de throw classe exception

// cenarios de throw exception - generica ou casos inesperados