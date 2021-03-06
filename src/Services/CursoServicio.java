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
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class CursoServicio {
    AlumnoDAO alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAO cursoDAO = new CursoDAOH2Impl();
    NotaDAO parcialDAO = new NotaDAOH2Impl();
    CursadaDAO cursadaDAO = new CursadaDAOH2Impl();
    NotaServicio notaServicio = new NotaServicio();

    public void crearCurso(int id_curso, String nombre, int precio, int cupo_maximo, int cantidad_parciales) throws ServiceCursoYaExisteException {
        try {
            cursoDAO.crearCurso(new Curso(id_curso, nombre, cupo_maximo, precio, cantidad_parciales));
        } catch (DAOClaveDuplicadaException daoClaveDuplicadaException) {
            throw new ServiceCursoYaExisteException("El curso " + id_curso + " ya existe.");
        }
    }

    public void borrarCurso(int id_curso) {
        cursoDAO.borraCurso(id_curso);
    }

    public void editarCurso(int id_curso, String nombre, int precio, int cupo_maximo, int cantidad_parciales) throws ServiceCursoNoExisteException, ServiceCantidadParcialesIncorrecto, ServiceCupoMaximoExcedido {
        try {
            if(cantidad_parciales >= 7 || cantidad_parciales < 0){
                throw new ServiceCantidadParcialesIncorrecto();
            }
            else if(cupo_maximo >= 121){
                throw new ServiceCupoMaximoExcedido();
            }

            cursoDAO.actualizaCurso(new Curso(id_curso, nombre, cupo_maximo, precio, cantidad_parciales));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso " + id_curso + " no existe.");
        }
    }

    public List<Curso> listarCursos() {
        return cursoDAO.listaTodosLosCursos();
    }

    public Curso muestraCurso(int id_curso) throws ServiceCursoNoExisteException {
        try {
            return cursoDAO.muestraCurso(id_curso);
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        }
    }

    public int muestraRecaudacionTotal(int id_curso) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException {
        List<Alumno> alumnosCurso;
        try {
            Curso c = cursoDAO.muestraCurso(id_curso);
            alumnosCurso = cursadaDAO.listaAlumnosCurso(c);
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
            List<Alumno> cursando = cursadaDAO.listaAlumnosCurso(curso);
            for (Alumno a : cursando) {
                List<Nota> notas = parcialDAO.listarNotasCursoALumno(a, curso);
                List<Nota> notasAprobadasAlumno = new ArrayList<>();
                for (Nota n : notas) {
                    if (n.getNotaParcial() >= 4) {
                        notasAprobadasAlumno.add(n);
                    }
                }
                if (notasAprobadasAlumno.size() == curso.getCantidad_parciales()) {
                    aprobados.add(a);
                }
            }
            if (aprobados.size() == 0) {
                throw new ServiceNoHayAprobadosException("El curso " + curso + " no tiene ninguna aprobado actualmente.");
            }
            return aprobados;

        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + id_curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo no existe.");
        }
    }

    public List<Alumno> mostrarAlumnosAprobadosConFinal(int id_curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        List<Alumno> aprobadosConFinal = new ArrayList<>();

        List<Nota> notasFinales = notaServicio.listarNotasFinales();
        System.out.println(notasFinales);
        for (Nota n : notasFinales) {
            if (n.getCurso().getId() == id_curso) {
                aprobadosConFinal.add(n.getAlumno());
            }
        }

        return aprobadosConFinal;
    }

}
