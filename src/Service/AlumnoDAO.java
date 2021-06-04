package Service;

import Entidades.Alumno;
import Service.Exceptions.AlumnoNoExiste;
import Service.Exceptions.ClaveDuplicadaException;

import java.util.List;

public interface AlumnoDAO {

	void crearAlumno(Alumno unAlumno) throws ClaveDuplicadaException, NumberFormatException;

	void borraAlumno(int id_alumno) throws AlumnoNoExiste;

	void actualizaAlumno(Alumno unAlumno) throws AlumnoNoExiste;

	Alumno muestraAlumno(int id_alumno) throws AlumnoNoExiste;

	List<Alumno> listaTodosLosAlumnos();

}
