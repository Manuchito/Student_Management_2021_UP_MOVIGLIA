package Services;

import DAO.Curso.CursoDAOH2Impl;
import DAO.Nota.NotaDAOH2Impl;
import Entidades.Alumno;
import DAO.Alumno.AlumnoDAOH2Impl;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;
import Exceptions.DAOInscripcionDublicadaException;
import Exceptions.ServiceCapacidadMaximaCursosAlumnoException;
import Exceptions.ServiceCupoCompletoException;
import Exceptions.ServiceInscripcionRepetidaException;

import java.util.ArrayList;
import java.util.List;

public class AlumnoServicio {

    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
    NotaDAOH2Impl notaDAO = new NotaDAOH2Impl();


    public void registrar(int legajo, String nombre, String apellido, int limiteCursos) throws ServiceClaveDuplicadaException{
        try {
            alumnoDAO.crearAlumno(new Alumno(legajo, nombre, apellido, limiteCursos));
        } catch (DAOClaveDuplicadaException daoClaveDuplicadaException) {
            throw new ServiceClaveDuplicadaException(daoClaveDuplicadaException.getMessage());
        }


    }

    public void eliminar(int legajo){
        alumnoDAO.borraAlumno(legajo);
    }

    public void editar(int legajo, String nombre, String apellido, int limiteCursos) throws ServiceLegajoNoExsiteException, NumberFormatException {

        try {
            alumnoDAO.actualizaAlumno(new Alumno(legajo, nombre, apellido, limiteCursos));
        } catch (DAOLegajoNoExisteException daoLegajoNoExiste) {
            throw new ServiceLegajoNoExsiteException(daoLegajoNoExiste.getMessage());
        }


    }

    public Alumno mostrar(int legajo) throws ServiceLegajoNoExsiteException {
        try {
            return alumnoDAO.muestraAlumno(legajo);
        } catch (DAOLegajoNoExisteException daoLegajoNoExiste) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }

    }

    public List<Alumno> listarAlumnos(){
        return alumnoDAO.listaTodosLosAlumnos();
    }


    public void inscribirAlumnoxCurso(int legajo, int curso) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceCupoCompletoException, ServiceCapacidadMaximaCursosAlumnoException, ServiceInscripcionRepetidaException, ServiceFinalPendiente, ServiceAlmunoYaCursoMateria {
        try{
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            Curso c = cursoDAO.muestraCurso(curso);
            if(cursoDAO.listaAlumnosCurso(c).size() >= c.getCupo()){

                throw new ServiceCupoCompletoException("INSCRIBIR AL ALUMNO SUPERARIA LA CAPACIDAD DEL CURSO");
            }
            else if(alumnoDAO.listaCursosAlumno(a).size() >= a.getLimiteCursos()){

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
            else if(alumnoDAO.listaCursosAlumno(a).size() < a.getLimiteCursos() && cursoDAO.listaAlumnosCurso(c).size() < c.getCupo()){
                alumnoDAO.inscribirAlumnoxCurso(a,c);
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

    public List<Curso> listarCursosDelAlumno(int legajo) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        try{
            return alumnoDAO.listaCursosAlumno(alumnoDAO.muestraAlumno(legajo));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + legajo + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }

    public List<Curso> listarCursosAprobados(int legajo) throws ServiceLegajoNoExsiteException {
        try{
            List<Nota> notas = notaDAO.listaNotasAlumno(alumnoDAO.muestraAlumno(legajo));
            List<Curso> cursos_aprobados = new ArrayList<>();
            for(Nota n: notas){
                if(n.getTipoNota().equals("FINAL")){
                    cursos_aprobados.add(n.getCurso());
                }
            }
            return cursos_aprobados;
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }
}
