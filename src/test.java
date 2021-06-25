import DAO.AlumnoDAO;
import DAO.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.IntegerVaciaException;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.ClaveNoExisteException;
import DAO.TableManager;

import java.sql.SQLException;

public class test {

	public static void main(String [] args) throws SQLException, DAOClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException {

		TableManager t = new TableManager();
		AlumnoDAO x = new AlumnoDAOH2Impl();
		CursoDAO ca = new CursoDAOH2Impl();
		Curso c = new Curso(6,"xd", 2, 23);
		Alumno a = new Alumno(2, "s", "s", "CURSANDO");

		x.inscribirAlumnoxCurso(a,c);

	}

}
