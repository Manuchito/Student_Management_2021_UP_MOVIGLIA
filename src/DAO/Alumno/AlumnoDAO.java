package DAO.Alumno;

import Entidades.Alumno;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.DAOClaveDuplicadaException;

import java.util.List;

public interface AlumnoDAO {

	void crearAlumno(Alumno unAlumno) throws DAOClaveDuplicadaException;

	void borraAlumno(int id_alumno);

	void actualizaAlumno(Alumno unAlumno) throws DAOLegajoNoExisteException;

	Alumno muestraAlumno(int id_alumno) throws DAOLegajoNoExisteException;

	List<Alumno> listaTodosLosAlumnos();

}
