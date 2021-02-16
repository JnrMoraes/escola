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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService implements IAlunoService{

    @Autowired
    private AlunoRepository alunoRepository;

    @Override
    public Boolean cadastrarAluno(AlunoDto aluno) {
        ModelMapper mapper = new ModelMapper();
        Aluno alunoEntity = mapper.map(aluno, Aluno.class);

        this.alunoRepository.save(alunoEntity);
        return true;
    }

    @Override
    public List<AlunoDto> buscarAlunoS() {
         try{
             ModelMapper mapper = new ModelMapper();

             return mapper.map(this.alunoRepository.findAll(), new TypeToken<List<AlunoDto>>() {}.getType());

         } catch (Exception e){
             return new ArrayList<>();
         }
    }

    @Override
    public AlunoDto buscarAlunoPeloId(Long id) {
        try {
            ModelMapper mapper = new ModelMapper();
            Optional<Aluno> alunoOptional = this.alunoRepository.findById(id);

            if(alunoOptional.isPresent()){

                return mapper.map(alunoOptional.get(), AlunoDto.class);

            }
            throw new AlunoException("Aluno não encontrado", HttpStatus.NOT_FOUND);

        } catch (AlunoException exception){
            throw exception;

        } catch (Exception exception){
            throw new AlunoException("Erro interno", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public Boolean alterarAluno(AlunoDto aluno) {
        try{

            this.buscarAlunoPeloId(aluno.getId());
            ModelMapper mapper = new ModelMapper();
            Aluno alunoAtualizado = mapper.map(aluno, Aluno.class);

            this.alunoRepository.save(alunoAtualizado);

            return Boolean.TRUE;

        } catch (AlunoException exception){
            throw exception;

        } catch (Exception exception){
            throw exception;

        }
    }

    @Override
    public Boolean excluirAluno(Long id) {

       try{
           this.buscarAlunoPeloId(id);
           this.alunoRepository.deleteById(id);
           return Boolean.TRUE;

       } catch (AlunoException exception){
           throw exception;

       } catch (Exception exception){
           throw exception;

       }
    }

}

