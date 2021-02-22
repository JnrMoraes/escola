package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.NotaDto;

import java.util.List;

public interface INotaService {

    Boolean cadastrarNota(NotaDto nota);

    List<NotaDto> consultarNotas();

    NotaDto consultarNotaPeloId(Long id);

    Boolean alterarNota(NotaDto nota);

    Boolean excluirNota(Long id);
}
