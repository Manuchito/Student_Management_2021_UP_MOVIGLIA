package DAO.Curso;

import Entidades.Curso;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOCursoNoExisteException;

import java.util.List;

public interface CursoDAO {

    void crearCurso(Curso unCurso) throws DAOClaveDuplicadaException;

    void borraCurso(int id);

    void actualizaCurso(Curso unCurso) throws DAOCursoNoExisteException;

    Curso muestraCurso(int id) throws DAOCursoNoExisteException;

    List<Curso> listaTodosLosCursos();
}
