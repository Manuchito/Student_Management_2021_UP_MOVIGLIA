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

public class NotaServicio {
    NotaDAO notaDAO = new NotaDAOH2Impl();
    AlumnoDAO alumnoDAO = new AlumnoDAOH2Impl();
    CursoDAO cursoDAO = new CursoDAOH2Impl();
    CursadaDAO cursadaDAO = new CursadaDAOH2Impl();

    public List<Nota> listarNotasAlumno(int alumno) throws ServiceLegajoNoExsiteException {
        try{
            return notaDAO.listaNotasAlumno(alumnoDAO.muestraAlumno(alumno));
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("No existe legajo");
        }

    }

    public List<Nota> listarTodasLasNotas(){

        return notaDAO.listarTodasLasNotas();

    }

    public void calificarAlumno(int legajo, int curso, String tipoNota, int nota) throws ServiceLegajoNoExsiteException, ServiceCursoNoExisteException, ServiceInsificientesParcialesAprobadosException, ServiceClaveDuplicadaException {
        try{
            Curso c = cursoDAO.muestraCurso(curso);
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            List<Nota> notasCursoAlumno = notaDAO.listarNotasCursoALumno(a,c);

            List<Nota> notasAprobadas = new ArrayList<>();
            for(Nota n : notasCursoAlumno){
                if(n.getNotaParcial() >= 4){
                    notasAprobadas.add(n);
                }
            }

            if(tipoNota == "FINAL" && notasAprobadas.size() < c.getCantidad_parciales()){
                throw new ServiceInsificientesParcialesAprobadosException("El alumno " + a.getLegajo() + " no tiene suficiente parciales aprobados para calificar nota FINAL");
            }
            else if(tipoNota == "FINAL" && notasAprobadas.size() == c.getCantidad_parciales()){
                notaDAO.crearNota(new Nota(a, c, tipoNota, nota));
                cursadaDAO.borrarCursada(a.getLegajo(),c.getId());
            }
            else {
                notaDAO.crearNota(new Nota(a, c, tipoNota, nota));
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        } catch (DAOClaveDuplicadaException claveDuplicadaException){
            throw new ServiceClaveDuplicadaException("La nota ya existe en sistema");
        }
    }

    public void eliminarNota(int legajo, int id_curso, String tipo_nota) throws ServiceNotaParcialesDependenDeFinalException {

        try {
            Curso c = cursoDAO.muestraCurso(id_curso);
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            List<Nota> notasCursoAlumno = notaDAO.listarNotasCursoALumno(a,c);
            List<Nota> notasAprobadas = new ArrayList<>();
            for(Nota n : notasCursoAlumno){
                if(n.getNotaParcial() >= 4){
                    notasAprobadas.add(n);
                }
            }

            if(!tipo_nota.equals("FINAL") && notasAprobadas.size() > c.getCantidad_parciales()){
                throw new ServiceNotaParcialesDependenDeFinalException("El final de la materia depende de la nota a eliminar");
            }
            else {
                notaDAO.borrarNota(legajo,id_curso,tipo_nota);
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            cursoNoExisteException.printStackTrace();
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            legajoNoExisteException.printStackTrace();
        }
    }

    public void editarNota(int legajo, int curso, String tipoNota, int nota) throws ServiceNotaParcialesDependenDeFinalException, ServiceCursoNoExisteException, ServiceLegajoNoExsiteException, ServiceNumeroNotaIncorrectoException {
        try {

            if(nota > 10 || nota < 1){
                throw new ServiceNumeroNotaIncorrectoException();
            }
            Curso c = cursoDAO.muestraCurso(curso);
            Alumno a = alumnoDAO.muestraAlumno(legajo);
            List<Nota> notasCursoAlumno = notaDAO.listarNotasCursoALumno(a,c);

            List<Nota> notasAprobadas = new ArrayList<>();
            for(Nota n : notasCursoAlumno){
                if(n.getNotaParcial() >= 4){
                    notasAprobadas.add(n);
                }
            }
            if(!tipoNota.equals("FINAL") && notasAprobadas.size() >= c.getCantidad_parciales() && nota < 4){
                System.out.println(tipoNota);
                throw new ServiceNotaParcialesDependenDeFinalException("El final de la materia depende de la nota a eliminar");
            }else {
                Nota n = new Nota(a,c,tipoNota,nota);
                notaDAO.editarNota(n);
            }

        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException();
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException();
        }
    }

    public List<Nota> listarNotasCursoDelAlumno(int legajo, int curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException {
        try {
            return notaDAO.listarNotasCursoALumno(alumnoDAO.muestraAlumno(legajo), cursoDAO.muestraCurso(curso));
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            throw new ServiceCursoNoExisteException("El curso con id " + curso + " no existe.");
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            throw new ServiceLegajoNoExsiteException("El alumno con legajo " + legajo + " no existe.");
        }
    }

    public List<Nota> listarNotasFinales(){
        List<Nota> notas = listarTodasLasNotas();
        List<Nota> notasFinal = new ArrayList<>();
        for(Nota n : notas){
            if(n.getTipoNota().equals("FINAL")){
                notasFinal.add(n);
            }
        }
        return notasFinal;
    }

    public List<Nota> listarNotasAprobadasCursoDelAlumno(int legajo, int curso) throws ServiceCursoNoExisteException, ServiceLegajoNoExsiteException, ServiceNoHayAprobadosException {
        try{
            List<Nota> notasAprobadas = new ArrayList<>();
            List<Nota> notas = notaDAO.listarNotasCursoALumno(alumnoDAO.muestraAlumno(legajo), cursoDAO.muestraCurso(curso));
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
