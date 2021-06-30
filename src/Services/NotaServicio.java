package Services;

import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Parcial.ParcialDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;

import java.util.ArrayList;
import java.util.List;

public class NotaServicio {
    ParcialDAOH2Impl parcialDAO = new ParcialDAOH2Impl();
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

    public List<Nota> listarNotasAlumno(int alumno) throws ServiceLegajoNoExsiteException {
        try{
            return parcialDAO.listaNotasAlumno(alumnoDAO.muestraAlumno(alumno));
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("No existe legajo");
        }

    }
    public void calificarAlumno(int legajo, int curso, String tipoNota, int nota) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, Exception {
        try{
            Curso c = cursoDAO.muestraCurso(curso);
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            List<Nota> notasCursoAlumno = parcialDAO.listarNotasCursoALumno(a,c);
            List<Nota> notasAprobadas = new ArrayList<>();
            for(Nota n : notasCursoAlumno){
                if(n.getNotaParcial() >= 4){
                    notasAprobadas.add(n);
                }
            }

            if(tipoNota == "FINAL" && notasAprobadas.size() < c.getCantidad_parciales()){
                throw new Exception(); //TODO
            }
            else {
                parcialDAO.crearNota(new Nota(a, c, tipoNota, nota));
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }

    public List<Nota> listarNotasCursoDelAlumno(int legajo, int curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try {
            return parcialDAO.listarNotasCursoALumno(alumnoDAO.muestraAlumno(legajo), cursoDAO.muestraCurso(curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }

    public List<Nota> listarNotasAprobadasCursoDelAlumno(int legajo, int curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try{
            List<Nota> notasAprobadas = new ArrayList<>();
            List<Nota> notas = parcialDAO.listarNotasCursoALumno(alumnoDAO.muestraAlumno(legajo), cursoDAO.muestraCurso(curso));
            for(Nota n : notas){
                if(n.getNotaParcial() >= 4){
                    notasAprobadas.add(n);
                }
            }
            return notasAprobadas;
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }


}