package com.aluno.alunosmateriasnotas.service;

import com.aluno.alunosmateriasnotas.dto.MateriaDto;
import com.aluno.alunosmateriasnotas.entity.Materia;
import com.aluno.alunosmateriasnotas.entity.Nota;
import com.aluno.alunosmateriasnotas.entity.NotaModal;
import com.aluno.alunosmateriasnotas.entity.enums.MensagensConstant;
import com.aluno.alunosmateriasnotas.exception.NotaException;
import com.aluno.alunosmateriasnotas.rest.client.IMateriaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class NotaModalService implements INotaModalService {

    private MateriaService materiaService;

    private INotaService notaService;

    private IMateriaRepository materiaRepository;

    private ModelMapper mapper;

    public NotaModalService() {
        this.mapper = new ModelMapper();
    }

    @Override
    public Boolean associarNotaEmMateria(NotaModal notaModal) {
        try { // ver pq o mapper esta sendo nulo na converção do repository
//            MateriaDto materiaDto = this.mapper.map(this.materiaRepository.findById(notaModal.getId()), MateriaDto.class);
            MateriaDto materiaDto = this.materiaService.consultarMateriaPeloId(notaModal.getId());

            Nota nota = this.mapper.map(this.notaService.consultarNotaPeloId(notaModal.getNota()),
                    Nota.class);

            if (materiaDto.getNota() == null) {
                materiaDto.setNota(nota);
                Materia materia = this.mapper.map(materiaDto, Materia.class);
                this.materiaRepository.save(materia);

                return Boolean.TRUE;
            }
            throw new NotaException(MensagensConstant.ERRO_NOTA_NAO_ENCONTRADA.getValor(),
                    HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            throw new NotaException(MensagensConstant.ERRO_GENERICO.getValor()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
