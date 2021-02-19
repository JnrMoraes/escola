package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MateriaService implements IMateriaService{

    @Autowired
    private IMateriaRepository materiaRepository;

    @Override
    public Boolean cadastrarMateria(AlunoDto aluno) {
        return null;
    }

    @Override
    public List<AlunoDto> consultarMaterias() {
        return null;
    }

    @Override
    public AlunoDto consultarMateriaPeloId(Long id) {
        return null;
    }

    @Override
    public Boolean alterarMateria(AlunoDto aluno) {
        return null;
    }

    @Override
    public Boolean excluirMateria(Long id) {
        return null;
    }
}
