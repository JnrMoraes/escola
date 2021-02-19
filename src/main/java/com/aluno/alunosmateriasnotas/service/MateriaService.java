package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.MateriaException;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private IMateriaRepository materiaRepository;

    private final ModelMapper mapper;

    public MateriaService(IMateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean cadastrarMateria(MateriaDto materia) {
        try {
            Materia materiaEntity = this.mapper.map(materia, Materia.class);
            this.materiaRepository.save(materiaEntity);
            return Boolean.TRUE;

        } catch (MateriaException e) {
            throw new MateriaException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Override
    public List<MateriaDto> consultarMaterias() {
        try {

            return this.mapper.map(this.materiaRepository.findAll(),
                    new TypeToken<List<MateriaDto>>() {
                    }.getType());

        } catch (MateriaException e) {
            throw new MateriaException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public MateriaDto consultarMateriaPeloId(Long id) {

        try {
            Optional<Materia> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent()) {
                return this.mapper.map(materiaOptional.get(), MateriaDto.class);
            }
            throw new MateriaException(MensagensConstant.ERRO_MATERIA_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            throw new MateriaException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean alterarMateria(MateriaDto materia) {

        try {
            Materia materiaAtualizada = this.materiaRepository.findById(materia.getId()).get();
            materiaAtualizada.setNome(materia.getNome());
            this.materiaRepository.save(materiaAtualizada);
            return Boolean.TRUE;

        } catch (MateriaException e) {
            throw new MateriaException(MensagensConstant.ERRO_MATERIA_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Boolean excluirMateria(Long id) {
        try {
            this.materiaRepository.deleteById(id);
            return Boolean.TRUE;

        } catch (MateriaException e) {
            throw new MateriaException(MensagensConstant.ERRO_MATERIA_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        }
    }


}
