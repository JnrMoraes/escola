package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.controller.AlunoController;
import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CacheConfig(cacheNames = "aluno")
@Service
public class AlunoService implements IAlunoService {

    private IAlunoRepository IAlunoRepository;

    private IMateriaRepository IMateriaRepository;

    private final ModelMapper mapper;

    private static final String MENSAGEM_ERRO = "Erro interno";
    private static final String ALUNO_NAO_ENCONTRADO = "Aluno não encontrado";

    @Autowired
    public AlunoService(IAlunoRepository IAlunoRepository, IMateriaRepository IMateriaRepository) {
        this.IAlunoRepository = IAlunoRepository;
        this.IMateriaRepository = IMateriaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean cadastrarAluno(AlunoDto aluno) {
        try {
            Aluno alunoEntity = this.mapper.map(aluno, Aluno.class);
            this.IAlunoRepository.save(alunoEntity);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new AlunoException(MENSAGEM_ERRO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // utilizando para mais atributos
//    @Caching(evict = {
//            @CacheEvict(value = "aluno", key = "#aluno.id"),
//            @CacheEvict(value = "materia", key = "#aluno.id")
//    }
//    )
    @CacheEvict(key = "#aluno.id")
    @Override
    public Boolean alterarAluno(AlunoDto aluno) {
        try {

            this.consultarAlunoPeloId(aluno.getId());

            Aluno alunoAtualizado = this.mapper.map(aluno, Aluno.class);

            this.IAlunoRepository.save(alunoAtualizado);

            return Boolean.TRUE;

        } catch (AlunoException exception) {
            throw exception;

        } catch (Exception exception) {
            throw exception;

        }
    }

    @CachePut(unless = "#result.size()<3")
    @Override
    public List<AlunoDto> consultarAlunos() {
        try {
            List<AlunoDto> alunoDto = this.mapper.map(this.IAlunoRepository.findAll(),
                    new TypeToken<List<AlunoDto>>() {
                    }.getType());

            alunoDto.forEach(aluno ->
                    aluno.add(WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder
                        .methodOn(AlunoController.class)
                        .buscarAlunoPorId(aluno.getId()))
                        .withSelfRel()));

            return alunoDto;

        } catch (Exception e) {
            throw new AlunoException(MENSAGEM_ERRO,
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Cacheable(key = "#id")
    @Override
    public AlunoDto consultarAlunoPeloId(Long id) {
        try {
            Optional<Aluno> alunoOptional = this.IAlunoRepository.findById(id);

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
    public Boolean excluirAluno(Long id) {

        try {
            this.consultarAlunoPeloId(id);
            this.IAlunoRepository.deleteById(id);
            return Boolean.TRUE;

        } catch (AlunoException e) {
            throw e;

        } catch (Exception e) {
            throw e;

        }
    }

}

