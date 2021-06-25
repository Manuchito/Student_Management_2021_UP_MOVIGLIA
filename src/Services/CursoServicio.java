package Services;


import DAO.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;

import java.util.ArrayList;
import java.util.List;

public class CursoServicio {
    AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

    public Curso cursoConAlumnos(int id_curso) throws ServiceCursoNoExisteException {
        Curso curso = null;
        try {
            curso = cursoDAO.muestraCurso(id_curso);
            curso.setAlumnos(cursoDAO.listaAlumnosCurso(curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            legajoNoExisteException.printStackTrace();
        }
        return curso;
    }
}
