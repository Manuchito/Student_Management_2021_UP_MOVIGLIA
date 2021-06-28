package Services;

import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import DAO.Alumno.AlumnoDAOH2Impl;
import Entidades.Curso;
import Exceptions.*;

import java.util.List;

public class AlumnoServicio {

    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

    private void validad(int legajo, String nombre, String apellido) {
        if (nombre.matches(".*\\d.*") || apellido.matches(".*\\d.*") || nombre.matches("[^A-Za-z0-9]") || apellido.matches("[^A-Za-z0-9]")) {
            throw new NumberFormatException("Nombre y/o Apellido no pueden tener numeros o caracteres especiales en su contenido");
        } else if (nombre.isEmpty() || apellido.isEmpty() || Integer.valueOf(legajo) == null) {
            throw new NumberFormatException("Nombre, id y/o apellido no puede estar vacio");
        }
    }

    public void registrar(int legajo, String nombre, String apellido, String aprobacion) throws ServiceClaveDuplicadaException, NumberFormatException {
        try {
            validad(legajo, nombre, apellido);
            alumnoDAO.crearAlumno(new Alumno(legajo, nombre, apellido, aprobacion));
        } catch (DAOClaveDuplicadaException daoClaveDuplicadaException) {
            throw new ServiceClaveDuplicadaException(daoClaveDuplicadaException.getMessage());
        }


    }

    public void eliminar(int legajo) throws IntegerVaciaException, ServiceLegajoNoExsiteException {
        try {
            if (Integer.valueOf(legajo) == null) {
                throw new IntegerVaciaException("El legajo esta vacio o es nulo");
            }

            alumnoDAO.borraAlumno(legajo);
        } catch (DAOLegajoNoExisteException daoLegajoNoExiste) {
            throw new ServiceLegajoNoExsiteException(daoLegajoNoExiste.getMessage());
        }
    }

    public void editar(int legajo, String nombre, String apellido, String aprobacion) throws ServiceLegajoNoExsiteException, NumberFormatException {

        try {
            validad(legajo, nombre, apellido);
            alumnoDAO.actualizaAlumno(new Alumno(legajo, nombre, apellido, aprobacion));
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

    public List<Alumno> listarAlumnos() throws ServiceLegajoNoExsiteException {
        List<Alumno> alumnos = null;
        try{
            return alumnoDAO.listaTodosLosAlumnos();
        } catch (Exception e) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }


    public void inscribirAlumnoxCurso(int legajo, int curso) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        try{
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            Curso c = cursoDAO.muestraCurso(curso);
            alumnoDAO.inscribirAlumnoxCurso(a,c);
        } catch (DAOLegajoNoExisteException daoLegajoNoExiste) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        } catch (DAOCursoNoExisteException daoCursoNoExiste){
            throw new ServiceCursoNoExisteException("El curso con id " + legajo + " no existe.");
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

}
