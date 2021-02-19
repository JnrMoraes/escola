package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IMateriaService {

    Boolean cadastrarMateria(MateriaDto materia);

    List<MateriaDto> consultarMaterias();

    MateriaDto consultarMateriaPeloId(Long id);

    Boolean alterarMateria(MateriaDto materia);

    Boolean excluirMateria(Long id);

}
