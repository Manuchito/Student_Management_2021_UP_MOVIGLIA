import DAO.AlumnoDAO;
import DAO.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.*;
import DAO.TableManager;

import java.sql.SQLException;
import java.util.List;

public class test {

	public static void main(String [] args) throws SQLException, DAOClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException, DAOCursoNoExisteException, DAOLegajoNoExisteException {

		TableManager t = new TableManager();
		AlumnoDAOH2Impl x = new AlumnoDAOH2Impl();
		CursoDAOH2Impl ca = new CursoDAOH2Impl();
		List<Alumno> xd = ca.listaAlumnosCurso(ca.muestraCurso(6));
		List<Curso> asd = x.listaCursosAlumno(x.muestraAlumno(1));
		System.out.println(xd);
		System.out.println(asd);

	}

}
