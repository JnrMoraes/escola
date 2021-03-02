package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.controller.AlunoController;
import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
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

    private IAlunoRepository alunoRepository;

    private final ModelMapper mapper;

    @Autowired
    public AlunoService(IAlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean cadastrarAluno(AlunoDto aluno) {
        try {

            if(this.alunoRepository.findById(aluno.getId()).isPresent()){
                if(this.alunoRepository.findById(aluno.getId()).equals(aluno.getId()))
                throw new AlunoException(MensagensConstant.ERRO_ALUNO_ENCONTRADO.getValor(),
                        HttpStatus.BAD_REQUEST);
            }

            if(aluno.getId() != null){
                throw new AlunoException(MensagensConstant.ERRO_ID_ALUNO_INFORMADO.getValor(),
                        HttpStatus.BAD_REQUEST);
            }

            return this.cadastrarOuAtualizarAuluno(aluno);

        } catch (AlunoException e) {
            throw e;

        }catch (Exception e) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
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

            this.alunoRepository.save(alunoAtualizado);

            return Boolean.TRUE;

        } catch (AlunoException exception) {
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception exception) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @CachePut(unless = "#result.size()<3")
    @Override
    public List<AlunoDto> consultarAlunos() {
        try {
            List<AlunoDto> alunoDto = this.mapper.map(this.alunoRepository.findAll(),
                    new TypeToken<List<AlunoDto>>() {
                    }.getType());

            alunoDto.forEach(aluno ->
                    aluno.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(
                            AlunoController.class)
                            .buscarAlunoPorId(aluno.getId()))
                            .withSelfRel()));

            return alunoDto;

        } catch (Exception e) {
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Cacheable(key = "#id")
    @Override
    public AlunoDto consultarAlunoPeloId(Long id) {
        try {
            Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

            if (alunoOptional.isPresent()) {

                return this.mapper.map(alunoOptional.get(), AlunoDto.class);

            }
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (AlunoException exception) {
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception exception) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public Boolean excluirAluno(Long id) {

        try {
            this.consultarAlunoPeloId(id);
            this.alunoRepository.deleteById(id);
            return Boolean.TRUE;

        } catch (AlunoException e) {
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    private Boolean cadastrarOuAtualizarAuluno(AlunoDto alunoDto) {
        Aluno aluno = this.mapper.map(alunoDto, Aluno.class);
        this.alunoRepository.save(aluno);
        return Boolean.TRUE;
    }

}

