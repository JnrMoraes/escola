package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;

import java.util.List;

public interface IMateriaService {

    Boolean cadastrarMateria(AlunoDto aluno);

    List<AlunoDto> consultarMaterias();

    AlunoDto consultarMateriaPeloId(Long id);

    Boolean alterarMateria(AlunoDto aluno);

    Boolean excluirMateria(Long id);

}
