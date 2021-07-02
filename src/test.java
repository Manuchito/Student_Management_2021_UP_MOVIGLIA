import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.*;
import DAO.TableManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import Services.NotaServicio;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, DAOClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException, DAOCursoNoExisteException, DAOLegajoNoExisteException, ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {

		TableManager t = new TableManager();
		AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
		CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
		NotaDAOH2Impl notaDAO = new NotaDAOH2Impl();
		AlumnoServicio alumnoServicio = new AlumnoServicio();
		CursoServicio cursoServicio = new CursoServicio();
		NotaServicio notaServicio = new NotaServicio();

		Alumno a = alumnoDAO.muestraAlumno(1);
		Curso c = cursoDAO.muestraCurso(99);


		System.out.println(alumnoServicio.listarCursosAprobados(1));


	}

}
