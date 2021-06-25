package DAO;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.IntegerVaciaException;
import Exceptions.DAOClaveDuplicadaException;

import java.util.List;

public interface AlumnoDAO {

	void crearAlumno(Alumno unAlumno) throws DAOClaveDuplicadaException, NumberFormatException;

	void borraAlumno(int id_alumno) throws IntegerVaciaException, DAOLegajoNoExisteException;

	void actualizaAlumno(Alumno unAlumno) throws IntegerVaciaException, DAOLegajoNoExisteException;

	Alumno muestraAlumno(int id_alumno) throws IntegerVaciaException, DAOLegajoNoExisteException;

	List<Alumno> listaTodosLosAlumnos();

	void inscribirAlumnoxCurso(Alumno unAlumno, Curso unCurso);

}
