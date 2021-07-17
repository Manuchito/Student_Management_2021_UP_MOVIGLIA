import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Cursada.CursadaDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;
import DAO.TableManager;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import Services.NotaServicio;
import Swing.Profesor.CalificarAlumno_Swing;
import Swing.Profesor.Profesor_Swing;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class test {

	public static void main(String [] args) throws SQLException, DAOClaveDuplicadaException, ClaveNoExisteException, IntegerVaciaException, DAOCursoNoExisteException, DAOLegajoNoExisteException, ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceNotaParcialesDependenDeFinalException, DAOInscripcionDublicadaException {

		TableManager t = new TableManager();
		AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
		CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
		NotaDAOH2Impl notaDAO = new NotaDAOH2Impl();
		AlumnoServicio alumnoServicio = new AlumnoServicio();
		CursoServicio cursoServicio = new CursoServicio();
		NotaServicio notaServicio = new NotaServicio();
		CursadaDAOH2Impl cursadaDAO = new CursadaDAOH2Impl();
		CalificarAlumno_Swing a = new CalificarAlumno_Swing(new PanelManager());


		notaDAO.borrarNota(11,72,"Parcial 1");

	}

}
