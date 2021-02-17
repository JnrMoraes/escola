package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.AlunoRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService implements IAlunoService {

    private final AlunoRepository alunoRepository;
    private final ModelMapper mapper;

    private static final String MENSAGEM_ERRO = "Erro interno";
    private static final String ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean cadastrarAluno(AlunoDto aluno) {
        try {
            Aluno alunoEntity = this.mapper.map(aluno, Aluno.class);
            this.alunoRepository.save(alunoEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new AlunoException(MENSAGEM_ERRO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<AlunoDto> consultarAlunos() {
        try {
            return this.mapper.map(this.alunoRepository.findAll(), new TypeToken<List<AlunoDto>>() {
            }.getType());

        } catch (Exception e) {
            throw new AlunoException(MENSAGEM_ERRO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public AlunoDto consultarAlunoPeloId(Long id) {
        try {
            Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

            if (alunoOptional.isPresent()) {

                return this.mapper.map(alunoOptional.get(), AlunoDto.class);

            }
            throw new AlunoException(ALUNO_NAO_ENCONTRADO,
                    HttpStatus.NOT_FOUND);

        } catch (AlunoException exception) {
            throw exception;

        } catch (Exception exception) {
            throw new AlunoException(MENSAGEM_ERRO,
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public Boolean alterarAluno(AlunoDto aluno) {
        try {

            this.consultarAlunoPeloId(aluno.getId());

            Aluno alunoAtualizado = this.mapper.map(aluno, Aluno.class);

            this.alunoRepository.save(alunoAtualizado);

            return Boolean.TRUE;

        } catch (AlunoException exception) {
            throw exception;

        } catch (Exception exception) {
            throw exception;

        }
    }

    @Override
    public Boolean excluirAluno(Long id) {

        try {
            this.consultarAlunoPeloId(id);
            this.alunoRepository.deleteById(id);
            return Boolean.TRUE;

        } catch (AlunoException e) {
            throw e;

        } catch (Exception e) {
            throw e;

        }
    }

}

