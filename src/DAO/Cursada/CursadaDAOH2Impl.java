package DAO.Cursada;

import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import DAO.DBManager;
import Entidades.Alumno;
import Entidades.Cursada;
import Entidades.Curso;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOInscripcionDublicadaException;
import Exceptions.DAOLegajoNoExisteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursadaDAOH2Impl {

    public void inscribirAlumnoxCurso(Alumno unAlumno, Curso unCurso) throws DAOInscripcionDublicadaException {
        int id = unAlumno.getLegajo();
        int id_curso = unCurso.getId();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO cursada (id_alumno,id_curso) VALUES ('" + id + "', '" + id_curso + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new DAOInscripcionDublicadaException("La inscripcion del Alumno " + id + "y con el Curso " + id_curso + " ya existen");
                }
                e.printStackTrace();
                c.rollback();
            } catch (SQLException e1) {
                e.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void borrarCursada(int id, int id_curso) throws DAOLegajoNoExisteException {
        String sql = "DELETE FROM cursada WHERE ID_ALUMNO = '" + id + "' AND ID_CURSO = '" + id_curso + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            c.commit();

        } catch (SQLException e) {
            try {

                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public List<Cursada> listarTodosLasCursadas(){
        List<Cursada> resultado = new ArrayList<>();
        String sql = "SELECT * FROM cursada";
        Connection c = DBManager.connect();

        CursoDAO cursoDAO = new CursoDAOH2Impl();
        AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();

        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id_cursada");
                Alumno alumno = alumnoDAO.muestraAlumno(rs.getInt("ID_ALUMNO"));
                Curso curso = cursoDAO.muestraCurso(rs.getInt("ID_CURSO"));
                Cursada cursada = new Cursada(id, alumno, curso);
                resultado.add(cursada);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            cursoNoExisteException.printStackTrace();
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            legajoNoExisteException.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return resultado;
    }

    public List<Alumno> listaAlumnosCurso(Curso unCurso) throws DAOLegajoNoExisteException {
        List<Alumno> alumnos = new ArrayList<>();
        AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
        String sql = "SELECT ID_ALUMNO  FROM cursada WHERE ID_CURSO = " + unCurso.getId();
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int legajo = rs.getInt("ID_ALUMNO");
                Alumno alumno = alumnoDAO.muestraAlumno(legajo);
                alumnos.add(alumno);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return alumnos;

    }

    public List<Curso> listaCursosAlumno(Alumno unAlumno) throws DAOCursoNoExisteException {
        List<Curso> cursos = new ArrayList<>();
        CursoDAO cursoDAO = new CursoDAOH2Impl();
        String sql = "SELECT ID_CURSO FROM cursada WHERE ID_ALUMNO = " + unAlumno.getLegajo();
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("ID_CURSO");
                Curso curso = cursoDAO.muestraCurso(id); //REVISAR
                cursos.add(curso);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        return cursos;

    }

}
