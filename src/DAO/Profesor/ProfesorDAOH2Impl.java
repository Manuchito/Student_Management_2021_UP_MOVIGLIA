package DAO.Profesor;

import DAO.DBManager;
import Entidades.Alumno;
import Entidades.Profesor;
import Exceptions.DAOClaveDuplicadaException;
import Exceptions.DAOLegajoNoExisteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOH2Impl {

    public void crearProfesor(Profesor unProfesor) throws DAOClaveDuplicadaException {

        String usuario = unProfesor.getUsuario();
        String nombre = unProfesor.getNombre();
        String apellido = unProfesor.getApellido();
        String pw = unProfesor.getPw();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();

            String sql = "INSERT INTO profesores (nombre, apellido, usuario, pw) VALUES ('" + nombre + "', '" + apellido + "', '" + usuario + "', '" + pw + "')";
            s.executeUpdate(sql);
            c.commit();
        } catch (SQLException e) {
            try {
                if(e.getErrorCode() == 23505) {
                    throw new DAOClaveDuplicadaException("La clave ( " + usuario + " ) ya existe");
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

    public void borraAlumno(String usuario){
        String sql = "DELETE FROM profesores WHERE usuario = '" + usuario + "'";
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

    public void editarProfesor(Profesor unProfesor) throws DAOLegajoNoExisteException {

        String usuario = unProfesor.getUsuario();
        String nombre = unProfesor.getNombre();
        String apellido = unProfesor.getApellido();
        String pw = unProfesor.getPw();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "UPDATE profesores set nombres = '" + nombre + "', apellidos = '" + apellido + "', pw = '" + pw + "'WHERE usuario = '" + usuario + "'";
            if(s.executeUpdate(sql) == 0){
                throw new DAOLegajoNoExisteException("La clave ( "+ usuario +" ) no existe");
            }
            c.commit();

        } catch (SQLException e) {
            try {
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

    public List<Profesor> listaTodosLosProfesores(){
        List<Profesor> resultado = new ArrayList<>();
        String sql = "SELECT * FROM profesores";
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);


            while (rs.next()) {
                String usuario = rs.getString("usuario");
                String nombre= rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String pw = rs.getString("pw");
                Profesor p = new Profesor(nombre, apellido, usuario, pw);
                resultado.add(p);

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

}


