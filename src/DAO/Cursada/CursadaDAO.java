package DAO.Cursada;

import Entidades.Alumno;
import Entidades.Cursada;
import Entidades.Curso;
import Exceptions.DAOCursadaNoExisteException;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOInscripcionDublicadaException;
import Exceptions.DAOLegajoNoExisteException;

import java.util.List;

public interface CursadaDAO {
    void inscribirAlumnoxCurso(Alumno unAlumno, Curso unCurso) throws DAOInscripcionDublicadaException;

    void borrarCursada(int id, int id_curso) throws DAOLegajoNoExisteException;

    Cursada muestraCursada(int id_cursada) throws DAOCursadaNoExisteException, DAOLegajoNoExisteException, DAOCursoNoExisteException;

    List<Cursada> listarTodosLasCursadas();

    List<Alumno> listaAlumnosCurso(Curso unCurso) throws DAOLegajoNoExisteException;

    List<Curso> listaCursosAlumno(Alumno unAlumno) throws DAOCursoNoExisteException;

}


