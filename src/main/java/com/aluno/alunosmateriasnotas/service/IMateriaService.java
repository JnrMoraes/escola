package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;

import java.util.List;

public interface IMateriaService {

    Boolean cadastrarMateria(MateriaDto materia);

    List<Materia> consultarMaterias();

    Materia consultarMateriaPeloId(Long id);

    Boolean alterarMateria(MateriaDto materia);

    Boolean excluirMateria(Long id);

}
