package DAO.Parcial;

import DAO.Curso.CursoDAO;
import DAO.Curso.CursoDAOH2Impl;
import DAO.DBManager;
import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Parcial;
import Exceptions.DAOCursoNoExisteException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ParcialDAOH2Impl {
    public void crearParcial(Parcial unParcial){
        int legajo = unParcial.getAlumno().getLegajo();
        int id_curso = unParcial.getCurso().getId();
        String tipoNota = unParcial.getTipoNota();
        int notaParcial = unParcial.getNotaParcial();

        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            String sql = "INSERT INTO parcial (id_alumno, id_curso, tipo_nota, nota) VALUES ('" + legajo + "', '" + id_curso + "', '" + tipoNota + "', '" + notaParcial + "')";
            s.executeUpdate(sql);
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



    public List<Parcial> listaParcialesAlumno(Alumno unAlumno){
        List<Parcial> parciales = new ArrayList<>();
        CursoDAO cursoDAO = new CursoDAOH2Impl();

        String sql = "SELECT * FROM PARCIAL WHERE ID_ALUMNO = " + unAlumno.getLegajo();
        Connection c = DBManager.connect();
        try {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int legajo = rs.getInt("ID_ALUMNO");
                Curso curso = cursoDAO.muestraCurso(rs.getInt("ID_CURSO"));
                String tipoNota = rs.getString("TIPO_NOTA");
                int nota = rs.getInt("NOTA");

                Parcial p = new Parcial(unAlumno, curso, tipoNota, nota);
                parciales.add(p);

            }
        } catch (SQLException e) {
            try {
                c.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            cursoNoExisteException.printStackTrace();
        } finally {
            try {
                c.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return parciales;

    }
}
