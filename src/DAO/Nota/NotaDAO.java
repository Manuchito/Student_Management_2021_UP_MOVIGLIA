package DAO.Nota;

import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.DAOClaveDuplicadaException;

import java.util.List;

public interface NotaDAO {
    void crearNota(Nota unaNota) throws DAOClaveDuplicadaException;

    void borrarNota(int legajo, int id_curso, String tipo_nota);

    void editarNota(Nota unaNota);

    List<Nota> listarTodasLasNotas();

    List<Nota> listaNotasAlumno(Alumno unAlumno);

    List<Nota> listarNotasCursoALumno(Alumno unAlumno, Curso unCurso);

}
