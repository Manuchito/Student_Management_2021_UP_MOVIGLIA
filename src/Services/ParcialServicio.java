package Services;

import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Parcial.ParcialDAOH2Impl;
import Entidades.Parcial;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;

import java.util.List;

public class ParcialServicio {
    ParcialDAOH2Impl parcialDAO = new ParcialDAOH2Impl();
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

    public List<Parcial> listarParcialesAlumno(int alumno) throws ServiceLegajoNoExsiteException {
        try{
            return parcialDAO.listaParcialesAlumno(alumnoDAO.muestraAlumno(alumno));
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("No existe legajo");
        }

    }

    public void calificarAlumno(int legajo, int curso, String tipoNota, int nota) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        try{
            parcialDAO.crearParcial(new Parcial(alumnoDAO.muestraAlumno(legajo), cursoDAO.muestraCurso(curso), tipoNota, nota));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }
}
