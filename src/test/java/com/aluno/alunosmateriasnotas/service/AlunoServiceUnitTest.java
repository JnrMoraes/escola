package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class AlunoServiceUnitTest {

    @Mock
    private IAlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private static Aluno aluno;
    private static Materia materia;
    private static Nota nota;


    @BeforeAll
    public static void init(){

        aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Jose de Jesus");

    }

    //cenarios de sucesso

    @Test
    void cadastrarAluno() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setNome("Jose de Jesus");

        aluno.setId(null);

        Mockito.when(this.alunoRepository.save(aluno)).thenReturn(Optional.of(aluno).get());

        Boolean sucesso = this.alunoService.cadastrarAluno(alunoDto);

        assertTrue(sucesso);
        Mockito.verify(this.alunoRepository,times(1)).save(aluno);
        aluno.setId(1L);

    }

    @Test
    void alterarAluno() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        Mockito.when(this.alunoRepository.findById(1L)).thenReturn( Optional.of(aluno));
        Mockito.when(this.alunoRepository.save(aluno)).thenReturn(aluno);

        Boolean sucesso = this.alunoService.alterarAluno(alunoDto);

        assertTrue(sucesso);

        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(1)).save(aluno);

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
        Mockito.when(this.alunoRepository.findById(1L)).thenReturn( Optional.of(aluno));
        AlunoDto alunoDto = this.alunoService.consultarAlunoPeloId(1L);

        assertNotNull(alunoDto);
        assertEquals("Jose de Jesus", alunoDto.getNome());
        assertEquals(1, alunoDto.getId());

        Mockito.verify(this.alunoRepository, times(1)).findById(1L);

    }

    @Test
    void excluirAluno() {
        Mockito.when(this.alunoRepository.findById(1L)).thenReturn( Optional.of(aluno));
        Boolean sucesso = this.alunoService.excluirAluno(1L);

        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(1)).deleteById(1L);

    }

    // cenarios de throw classe exception
    @Test
    void alterarAlunoException() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        Mockito.when(this.alunoRepository.findById(1L)).thenReturn( Optional.empty());

        AlunoException alunoException;

        alunoException = assertThrows(AlunoException.class, ()->{
            this.alunoService.alterarAluno(alunoDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, alunoException.getHttpStatus());
        assertEquals(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(), alunoException.getMessage());

        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(0)).save(aluno);

    }

    @Test
    void excluirAlunoException() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        Mockito.when(this.alunoRepository.findById(1L)).thenReturn( Optional.empty());

        AlunoException alunoException;

        alunoException = assertThrows(AlunoException.class, ()->{
            this.alunoService.alterarAluno(alunoDto);
        });

        assertEquals(HttpStatus.NOT_FOUND, alunoException.getHttpStatus());
        assertEquals(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(), alunoException.getMessage());

        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(0)).deleteById(1L);

    }

    @Test
    void cadastrarAlunoExceptionGenerica() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        AlunoException alunoException;

        alunoException = assertThrows(AlunoException.class, ()->{
            this.alunoService.cadastrarAluno(alunoDto);
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, alunoException.getHttpStatus());
        assertEquals(MensagensConstant.ERRO_GENERICO.getValor(), alunoException.getMessage());

//        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(0)).save(aluno);

    }
    @Test
    void cadastrarAlunoExceptionAlunoDtoTemUmId() {
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

        AlunoException alunoException;

        alunoException = assertThrows(AlunoException.class, ()->{
            this.alunoService.cadastrarAluno(alunoDto);
        });

        assertEquals(MensagensConstant.ERRO_ID_ALUNO_INFORMADO.getValor(), alunoException.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, alunoException.getHttpStatus());

        Mockito.verify(this.alunoRepository,times(0)).findById(1L);
        Mockito.verify(this.alunoRepository,times(0)).save(aluno);

    }

    @Test
    void cadastrarAlunoExceptionQuandoAlunoDtoTemUmIdExistente() { // ainda não estou conseguindo comparar o id no test
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Jose de Jesus");

        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(1L);
        alunoDto.setNome("Jose de Jesus");

//        Mockito.when(this.alunoRepository.find(aluno.getId())).thenReturn(Optional.of(aluno));

        AlunoException alunoException;

        alunoException = assertThrows(AlunoException.class, ()->{
            this.alunoService.cadastrarAluno(alunoDto);
        });


        assertEquals(MensagensConstant.ERRO_ALUNO_ENCONTRADO.getValor(), alunoException.getMessage());
        assertEquals(HttpStatus.BAD_REQUEST, alunoException.getHttpStatus());

        Mockito.verify(this.alunoRepository,times(1)).findById(1L);
        Mockito.verify(this.alunoRepository,times(0)).save(aluno);

    }

}



// cenarios de throw exception - generica ou casos inesperados