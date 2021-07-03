package DAO.Alumno;


import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import DAO.DBManager;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOInscripcionDublicadaException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOH2Impl implements AlumnoDAO {


    public void crearAlumno(Alumno unAlumno) throws DAOClaveDuplicadaException, NumberFormatException{
        int id = unAlumno.getLegajo();
        String nombre = unAlumno.getNombre();
        String apellido = unAlumno.getApellido();
        int limiteCursos = unAlumno.getLimiteCursos();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            String sql = "INSERT INTO alumnos (id_alumno,nombres,apellidos,limite_cursos) VALUES ('" + id + "', '" + nombre + "', '" + apellido + "', '" + limiteCursos + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new DAOClaveDuplicadaException("La clave ( " + id + " ) ya existe");
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

    public void borraAlumno(int id){
        String sql = "DELETE FROM alumnos WHERE ID_ALUMNO = '" + id + "'";
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

    public void actualizaAlumno(Alumno unAlumno) throws DAOLegajoNoExisteException {
        int id = unAlumno.getLegajo();
        String nombre = unAlumno.getNombre();
        String apellido = unAlumno.getApellido();
        int limiteCursos = unAlumno.getLimiteCursos();

        Connection c = DBManager.connect();
        try {
            String sql = "UPDATE alumnos set nombres = '" + nombre + "', apellidos = '" + apellido + "', limite_cursos = '" + limiteCursos + "'WHERE ID_ALUMNO = '" + id + "'";
            Statement s = c.createStatement();
            if(s.executeUpdate(sql) == 0){
                throw new DAOLegajoNoExisteException("La clave ( "+ id +" ) no existe");
            }
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
    
    public List<Alumno> listaTodosLosAlumnos(){
    	List<Alumno> resultado = new ArrayList<>();
        String sql = "SELECT * FROM alumnos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            while (rs.next()) {
            	int id = rs.getInt("id_alumno");
                String nombre= rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                int limiteCursos = rs.getInt("limite_cursos");
                Alumno u = new Alumno(id, nombre, apellido, limiteCursos);
                resultado.add(u);

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
        return resultado;
    }

    public Alumno muestraAlumno(int id) throws DAOLegajoNoExisteException {
        Alumno resultado = null;
        String sql = "SELECT * FROM alumnos WHERE ID_ALUMNO = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            
            if (rs.next()) {
                int id_alumno = rs.getInt("id_alumno");
                String nombre = rs.getString("nombres");
                String apellido = rs.getString("apellidos");
                int limiteCursos = rs.getInt("limite_cursos");
                resultado = new Alumno(id_alumno ,nombre, apellido, limiteCursos);
                return resultado;
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(resultado == null)
            {
                throw new DAOLegajoNoExisteException();
            }
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return resultado;
    }

    public List<Curso> listaCursosAlumno(Alumno unAlumno) throws DAOCursoNoExisteException {
        List<Curso> cursos = new ArrayList<>();
        CursoDAO cursoDAO = new CursoDAOH2Impl();
        String sql = "SELECT ID_CURSO FROM alumnoxcurso WHERE ID_ALUMNO = " + unAlumno.getLegajo();
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

    public void inscribirAlumnoxCurso(Alumno unAlumno, Curso unCurso) throws DAOInscripcionDublicadaException {
        int id = unAlumno.getLegajo();
        int id_curso = unCurso.getId();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            String sql = "INSERT INTO alumnoxcurso (id_alumno,id_curso) VALUES ('" + id + "', '" + id_curso + "')";
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
        String sql = "DELETE FROM ALUMNOXCURSO WHERE ID_ALUMNO = '" + id + "' AND ID_CURSO = '" + id_curso + "'";
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
}
