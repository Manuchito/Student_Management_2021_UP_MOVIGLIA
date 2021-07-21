import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Cursada.CursadaDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAOH2Impl;
import DAO.Profesor.ProfesorDAOH2Impl;
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
import java.util.ArrayList;
import java.util.List;

import static Services.ProfesorServicio.print;

public class test {

	public static ArrayList<Alumno> animales = new ArrayList<Alumno>();






	public static void main(String [] args) throws SQLException, DAOClaveDuplicadaException, DAOCursoNoExisteException, DAOLegajoNoExisteException, ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceNotaParcialesDependenDeFinalException, DAOInscripcionDublicadaException, ServiceNoHayAprobadosException {

		TableManager t = new TableManager();
		AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
		CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
		NotaDAOH2Impl notaDAO = new NotaDAOH2Impl();
		AlumnoServicio alumnoServicio = new AlumnoServicio();
		CursoServicio cursoServicio = new CursoServicio();
		NotaServicio notaServicio = new NotaServicio();
		CursadaDAOH2Impl cursadaDAO = new CursadaDAOH2Impl();
		CalificarAlumno_Swing a = new CalificarAlumno_Swing(new PanelManager());
		ProfesorDAOH2Impl profesorDAO = new ProfesorDAOH2Impl();



		System.out.println(cursoServicio.mostrarAlumnosAprobadosConFinal(34).size());

		print();

	}

}
