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

public class CursoServicio {
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
    ParcialDAOH2Impl parcialDAO = new ParcialDAOH2Impl();

    public List<Alumno> listarAlumnosDelCurso(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try {
            return cursoDAO.listaAlumnosCurso(cursoDAO.muestraCurso(id_curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }

    public List<Curso> listarCursos() throws Exception {
        try{
            return cursoDAO.listaTodosLosCursos();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public Curso muestraCurso(int id_curso) throws ServiceCursoNoExisteException {
        try{
            return cursoDAO.muestraCurso(id_curso);
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        }
    }

    public int muestraRecaudacionTotal(int id_curso) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        List<Alumno> alumnosCurso  = new ArrayList<>();
        try{
            Curso c = cursoDAO.muestraCurso(id_curso);
            alumnosCurso = cursoDAO.listaAlumnosCurso(c);
            return alumnosCurso.size() * c.getPrecio();
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }

    public List<Alumno> mostrarAlumnosAprobadosParaFinal(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        List<Alumno> aprobados = new ArrayList<>();
        try {
            Curso curso = cursoDAO.muestraCurso(id_curso);
            List<Alumno> cursando = cursoDAO.listaAlumnosCurso(curso);
            for(Alumno a : cursando){
                List<Nota> notas = parcialDAO.listarNotasCursoALumno(a, curso);
                List<Nota> notasAprobadasAlumno = new ArrayList<>();
                for(Nota n : notas){
                    if(n.getNotaParcial() >= 4){
                        notasAprobadasAlumno.add(n);
                    }
                }
                if(notasAprobadasAlumno.size() >= curso.getCantidad_parciales()){
                    aprobados.add(a);
                }
            }
            return aprobados;

        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }
}
