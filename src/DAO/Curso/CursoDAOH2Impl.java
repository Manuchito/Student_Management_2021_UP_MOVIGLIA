package DAO.Curso;

import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.DBManager;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOH2Impl implements CursoDAO {



    public void crearCurso(Curso unCurso) throws DAOClaveDuplicadaException {
        int id_curso = unCurso.getId();
        String nombre = unCurso.getNombre();
        int precio = unCurso.getPrecio();
        int cupo_maximo = unCurso.getCupo();
        int cantidad_parciales = unCurso.getCantidad_parciales();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            String sql = "INSERT INTO cursos (id_curso,nombre,precio,cupo_maximo,cantidad_parciales) VALUES ('" + id_curso + "', '" + nombre + "', '" + precio + "','" + cupo_maximo + "' ,'" + cantidad_parciales + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new DAOClaveDuplicadaException();
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

    public void borraCurso(int id) {
        String sql = "DELETE FROM cursos WHERE id_curso = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            s.executeUpdate(sql);
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

    @Override
    public void actualizaCurso(Curso unCurso) {
        int id = unCurso.getId();
        String nombre = unCurso.getNombre();
        int precio = unCurso.getPrecio();
        int capacidad = unCurso.getCupo();
        int cantidad_parciales = unCurso.getCantidad_parciales();

        String sql = "UPDATE cursos set nombre = '" + nombre + "', precio = '" + precio + "', cupo_maximo = '" + nombre + "', cantidad_parciales = '" + cantidad_parciales + "' WHERE curso_id = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            s.executeUpdate(sql);
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

    public List<Curso> listaTodosLosCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM cursos";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id_curso");
                String nombre = rs.getString("nombre");
                int precio = rs.getInt("precio");
                int capacidad = rs.getInt("cupo_maximo");
                int parciales = rs.getInt("cantidad_parciales");
                Curso u = new Curso(id, nombre, precio, capacidad, parciales);
                cursos.add(u);
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

    public Curso muestraCurso(int id_curso) throws DAOCursoNoExisteException {
        Curso curso = null;
        String sql = "SELECT * FROM cursos WHERE id_curso = '" + id_curso + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            if (rs.next()) {
                int id = rs.getInt("id_curso");
                String nombreCurso = rs.getString("nombre");
                int precioCurso = rs.getInt("precio");
                int capacidad = rs.getInt("cupo_maximo");
                int parciales = rs.getInt("cantidad_parciales");
                curso = new Curso(id, nombreCurso, capacidad, precioCurso, parciales);
            }

        } catch (SQLException e) {
            try {
                c.rollback();
                e.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            if(curso == null)
            {
                throw new DAOCursoNoExisteException();
            }
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return curso;
    }

    public List<Alumno> listaAlumnosCurso(Curso unCurso) throws DAOLegajoNoExisteException {
        List<Alumno> alumnos = new ArrayList<>();
        AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
        String sql = "SELECT ID_ALUMNO  FROM alumnoxcurso WHERE ID_CURSO = " + unCurso.getId();
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
}

