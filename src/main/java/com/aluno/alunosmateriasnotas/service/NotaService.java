package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.NotaDto;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.exception.NotaException;
import com.aluno.alunosmateriasnotas.rest.client.INotaRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotaService implements INotaService {

    @Autowired
    private INotaRepository notaRepository;

    private final ModelMapper mapper;

    public NotaService(INotaRepository notaRepository) {
        this.notaRepository = notaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean cadastrarNota(NotaDto nota) {
        try {
            Nota notaEntity = this.mapper.map(nota, Nota.class);
            this.notaRepository.save(notaEntity);
            return Boolean.TRUE;

        } catch (NotaException e) {
            throw new NotaException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public List<NotaDto> consultarNotas() throws AlunoException {
        try {
            return this.mapper.map(this.notaRepository.findAll(),
                    new TypeToken<List<NotaDto>>() {
                    }.getType());

        } catch (NotaException e) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public NotaDto consultarNotaPeloId(Long id) {

        try {
            Optional<Nota> notaOptional = this.notaRepository.findById(id);

            if(notaOptional.isPresent()){
                return this.mapper.map(notaOptional.get(), NotaDto.class);

            }
            throw new NotaException(MensagensConstant.ERRO_NOTA_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            throw new NotaException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean alterarNota(NotaDto nota) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean excluirNota(Long id) {
        throw new NotYetImplementedException();
    }
}
