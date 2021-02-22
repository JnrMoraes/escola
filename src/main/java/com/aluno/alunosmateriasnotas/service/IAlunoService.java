package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;

import java.util.List;

public interface IAlunoService {

    Boolean cadastrarAluno(AlunoDto aluno);

    List<AlunoDto> consultarAlunos();

    AlunoDto consultarAlunoPeloId(Long id);

    Boolean alterarAluno(AlunoDto aluno);

    Boolean excluirAluno(Long id);

//    Boolean cadastrarMateriaEmAluno(Aluno aluno, Materia materia);

}
