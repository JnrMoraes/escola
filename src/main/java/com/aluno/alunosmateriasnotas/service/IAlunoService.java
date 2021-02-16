package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.AlunoDto;

import java.util.List;

public interface IAlunoService {

    Boolean cadastrarAluno(AlunoDto aluno);

    List<AlunoDto> buscarAlunoS();

    AlunoDto buscarAlunoPeloId(Long id);

    Boolean alterarAluno(AlunoDto aluno);

    Boolean excluirAluno(Long id);

}
