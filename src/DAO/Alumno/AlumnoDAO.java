package DAO.Alumno;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.IntegerVaciaException;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOInscripcionDublicadaException;

import java.util.List;

public interface AlumnoDAO {

	void crearAlumno(Alumno unAlumno) throws DAOClaveDuplicadaException;

	void borraAlumno(int id_alumno);

	void actualizaAlumno(Alumno unAlumno) throws DAOLegajoNoExisteException;

	Alumno muestraAlumno(int id_alumno) throws DAOLegajoNoExisteException;

	List<Alumno> listaTodosLosAlumnos();

}
