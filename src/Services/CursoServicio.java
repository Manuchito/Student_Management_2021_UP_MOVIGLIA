package Services;


import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class CursoServicio {
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
    NotaDAOH2Impl parcialDAO = new NotaDAOH2Impl();

    public void crearCurso(int id_curso, String nombre, int precio, int cupo_maximo, int cantidad_parciales) throws ServiceCursoYaExisteException {
        try{
            cursoDAO.crearCurso(new Curso(id_curso,nombre,cupo_maximo,precio,cantidad_parciales));
        } catch (DAOClaveDuplicadaException daoClaveDuplicadaException) {
            throw new ServiceCursoYaExisteException("El curso "+ id_curso + " ya existe.");
        }
    }

    public void borrarCurso(int id_curso){
        cursoDAO.borraCurso(id_curso);
    }

    public void editarCurso(int id_curso, String nombre, int precio, int cupo_maximo, int cantidad_parciales) throws ServiceCursoNoExisteException {
        try {
            cursoDAO.actualizaCurso(new Curso(id_curso,nombre,cupo_maximo,precio,cantidad_parciales));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso "+ id_curso + " no existe.");
        }
    }

    public List<Alumno> listarAlumnosDelCurso(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try {
            return cursoDAO.listaAlumnosCurso(cursoDAO.muestraCurso(id_curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }

    public List<Curso> listarCursos(){
        return cursoDAO.listaTodosLosCursos();
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

    public List<Alumno> mostrarAlumnosAprobadosParaFinal(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException, ServiceNoHayAprobadosException {
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
                if(notasAprobadasAlumno.size() == curso.getCantidad_parciales()){
                    aprobados.add(a);
                }
            }
            if(aprobados.size() == 0){
                throw new ServiceNoHayAprobadosException("El curso "+ curso + " no tiene ninguna aprobado actualmente.");
            }
            return aprobados;

        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }
}
