package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.entity.MateriaModal;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.AlunoException;
import com.aluno.alunosmateriasnotas.rest.client.IAlunoRepository;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MateriaModalService implements IMateriaModalService {

    @Autowired
    private IAlunoRepository alunoRepository;
    @Autowired
    private IMateriaRepository materiaRepository;
    @Autowired
    private AlunoService alunoService;

    @Override
    public Boolean associarMateriasEmAluno(MateriaModal materiaModal) {

        try {
            AlunoDto aluno = this.alunoService.consultarAlunoPeloId(materiaModal.getId());

            if (aluno.getId() != null) {
                List<Materia> materiaList = new ArrayList<>();

                if (!materiaModal.getMaterias().isEmpty()) {
                    materiaModal.getMaterias().forEach(materia -> {
                        if (this.materiaRepository.findById(materia).isPresent()) {
                            materiaList.add(this.materiaRepository.findById(materia).get());
                        }
                    });
                    aluno.setMaterias(materiaList);
                    this.alunoService.alterarAluno(aluno);
                }
                return Boolean.TRUE;

            }
            throw new AlunoException(MensagensConstant.ERRO_ALUNO_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            throw new AlunoException(MensagensConstant.ERRO_GENERICO.getValor(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
