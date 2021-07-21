package Services;

import DAO.Alumno.AlumnoDAO;
import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Cursada.CursadaDAO;
import DAO.Cursada.CursadaDAOH2Impl;
import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAO;
import DAO.Nota.NotaDAOH2Impl;
import Entidades.Alumno;
import Entidades.Cursada;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;

import java.util.List;

public class CursadaServicio {
    AlumnoDAO alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAO cursoDAO = new CursoDAOH2Impl();
    NotaDAO notaDAO = new NotaDAOH2Impl();
    CursadaDAO cursadaDAO = new CursadaDAOH2Impl();

    public void inscribirAlumnoxCurso(int legajo, int curso) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceCupoCompletoException, ServiceCapacidadMaximaCursosAlumnoException, ServiceInscripcionRepetidaException, ServiceFinalPendiente, ServiceAlmunoYaCursoMateria {
        try{
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            Curso c = cursoDAO.muestraCurso(curso);
            if(cursadaDAO.listaAlumnosCurso(c).size() >= c.getCupo()){

                throw new ServiceCupoCompletoException("INSCRIBIR AL ALUMNO SUPERARIA LA CAPACIDAD DEL CURSO");
            }
            else if(cursadaDAO.listaCursosAlumno(a).size() >= a.getLimiteCursos()){

                throw new ServiceCapacidadMaximaCursosAlumnoException("INSCRIBIR AL ALUMNO SUPERARIA LA CAPACIDAD DE CURSOS QUE SE PUEDE ANOTAR");
            }
            else if(notaDAO.listarNotasCursoALumno(a,c).stream().anyMatch(nota -> "FINAL".equals(nota.getTipoNota()))){
                for(Nota n : notaDAO.listarNotasCursoALumno(a,c)){
                    if(n.getTipoNota().equals("FINAL") && n.getNotaParcial() >= 4){
                        throw new ServiceAlmunoYaCursoMateria();
                    }
                    else if (n.getTipoNota().equals("FINAL") && n.getNotaParcial() < 4){
                        throw new ServiceFinalPendiente();
                    }
                }
            }
            else if(cursadaDAO.listaCursosAlumno(a).size() < a.getLimiteCursos() && cursadaDAO.listaAlumnosCurso(c).size() < c.getCupo()){
                cursadaDAO.inscribirAlumnoxCurso(a,c);
            }

        } catch (DAOLegajoNoExisteException daoLegajoNoExiste) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        } catch (DAOCursoNoExisteException daoCursoNoExiste){
            throw new ServiceCursoNoExisteException("El curso con id " + legajo + " no existe.");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (DAOInscripcionDublicadaException e) {
            throw new ServiceInscripcionRepetidaException();
        }
    }

    public List<Cursada> mostrarCursadas(){
        return cursadaDAO.listarTodosLasCursadas();
    }

    public List<Curso> listarCursosDelAlumno(int legajo) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        try{
            return cursadaDAO.listaCursosAlumno(alumnoDAO.muestraAlumno(legajo));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + legajo + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }

    public List<Alumno> listarAlumnosDelCurso(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try {
            return cursadaDAO.listaAlumnosCurso(cursoDAO.muestraCurso(id_curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }

    public void borrarCursada(int id_cursada) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceCursadaNoExisteException {
        try {
            Cursada c = cursadaDAO.muestraCursada(id_cursada);
            cursadaDAO.borrarCursada(c.getAlumno().getLegajo() ,c.getCurso().getId());
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno no existe.");
        } catch (DAOCursadaNoExisteException e) {
            throw new ServiceCursadaNoExisteException("La cursada " + id_cursada);
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso no existe");
        }
    }
}
