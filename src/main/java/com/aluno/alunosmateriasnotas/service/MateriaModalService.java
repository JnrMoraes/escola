package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.entity.Aluno;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.MateriaModal;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MateriaModalService implements IMateriaModalService {
    private IMateriaRepository materiaRepository;

    private IAlunoRepository alunoRepository;

    private ModelMapper mapper;

    @Autowired
    public MateriaModalService(IMateriaRepository materiaRepository,
                               IAlunoRepository alunoRepository) {
        this.materiaRepository = materiaRepository;
        this.alunoRepository = alunoRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean associarMateriasEmAluno(MateriaModal materiaModal) {

        try {
            List<Materia> materiaList = new ArrayList<>();

            if (!materiaModal.getMaterias().isEmpty()) {
                for (Long materia : materiaModal.getMaterias()) {
                    if (this.materiaRepository.findById(materia).isPresent())
                        materiaList.add(this.materiaRepository.findById(materia).get());
                }
            }

            Optional<Aluno> alunoOptional = this.alunoRepository.findById(materiaModal.getId());

            if (alunoOptional.isPresent()) {

                Aluno aluno = this.mapper.map(alunoOptional.get(), Aluno.class);

                if (materiaModal.getId() != null) {
                    aluno.setId(materiaModal.getId());
                }
                aluno.setMaterias(materiaList);

                this.alunoRepository.save(aluno);

            }
            return Boolean.TRUE;

        } catch (Exception e) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
