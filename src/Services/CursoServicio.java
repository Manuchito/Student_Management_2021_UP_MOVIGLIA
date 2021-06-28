package Services;


import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;

import java.util.List;

public class CursoServicio {
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

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
}
