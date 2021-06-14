package DAO;

import Entidades.Alumno;
import Exceptions.AlumnoNoExiste;
import Exceptions.IntegerVaciaException;
import Exceptions.ClaveDuplicadaException;

import java.util.List;

public interface AlumnoDAO {

	void crearAlumno(Alumno unAlumno) throws ClaveDuplicadaException, NumberFormatException;

	void borraAlumno(int id_alumno) throws IntegerVaciaException, AlumnoNoExiste;

	void actualizaAlumno(Alumno unAlumno) throws IntegerVaciaException, AlumnoNoExiste;

	Alumno muestraAlumno(int id_alumno) throws IntegerVaciaException;

	List<Alumno> listaTodosLosAlumnos();

}
