package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.MateriaException;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.hibernate.cfg.NotYetImplementedException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService{

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
        throw new NotYetImplementedException();
    }

    @Override
    public MateriaDto consultarMateriaPeloId(Long id) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean alterarMateria(MateriaDto materia) {
        throw new NotYetImplementedException();
    }

    @Override
    public Boolean excluirMateria(Long id) {
        throw new NotYetImplementedException();
    }


}
