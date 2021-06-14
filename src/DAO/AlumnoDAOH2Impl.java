package DAO;


import Entidades.Alumno;
import Exceptions.AlumnoNoExiste;
import Exceptions.IntegerVaciaException;
import Exceptions.ClaveDuplicadaException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOH2Impl implements AlumnoDAO {


    public void crearAlumno(Alumno unAlumno) throws ClaveDuplicadaException, NumberFormatException{
        int id = unAlumno.getLegajo();
        String nombre = unAlumno.getNombre();
        String apellido = unAlumno.getApellido();
        String aprobacion = unAlumno.getAprobacion();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            String sql = "INSERT INTO alumnos (id_alumno,nombres,apellidos,aprobacion) VALUES ('" + id + "', '" + nombre + "', '" + apellido + "', '" + aprobacion + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new ClaveDuplicadaException("La clave ( " + id + " ) ya existe");
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

    public void borraAlumno(int id) throws AlumnoNoExiste {
        String sql = "DELETE FROM alumnos WHERE ID_ALUMNO = '" + id + "'";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            if(s.executeUpdate(sql) == 0){
                throw new AlumnoNoExiste("La clave ( "+ id +" ) no existe");
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

    public void actualizaAlumno(Alumno unAlumno) throws AlumnoNoExiste {
        int id = unAlumno.getLegajo();
        String nombre = unAlumno.getNombre();
        String apellido = unAlumno.getApellido();
        String aprobacion = unAlumno.getAprobacion();

        Connection c = DBManager.connect();
        try {
            String sql = "UPDATE alumnos set nombres = '" + nombre + "', apellidos = '" + apellido + "', aprobacion = '" + aprobacion + "'WHERE ID_ALUMNO = '" + id + "'";
            Statement s = c.createStatement();
            if(s.executeUpdate(sql) == 0){
                throw new AlumnoNoExiste("La clave ( "+ id +" ) no existe");
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
                String aprobacion = rs.getString("aprobacion");
                Alumno u = new Alumno(id, nombre, apellido, aprobacion);
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

    public Alumno muestraAlumno(int id) throws IntegerVaciaException {
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
                String aprobacion = rs.getString("aprobacion");
                resultado = new Alumno(id_alumno ,nombre, apellido, aprobacion);
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
                throw new IntegerVaciaException();
            }
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return resultado;
    }

}