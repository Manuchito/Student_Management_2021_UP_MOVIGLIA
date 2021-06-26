package Swing;
import DAO.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.DAOCursoNoExisteException;
import Exceptions.DAOLegajoNoExisteException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Profesor_Swing extends JPanel {
    private PanelManager panelManager;
    CursoServicio serv = new CursoServicio();
    AlumnoServicio servAlumno = new AlumnoServicio();

    public void reiniciarTablaAlumnosCurso(DefaultTableModel modeloTabla, int id_curso){
        modeloTabla.setRowCount(0);

        CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();

        try {
            for(Alumno a : cursoDAO.listaAlumnosCurso(cursoDAO.muestraCurso(id_curso))){
                modeloTabla.addRow(new Object[]{
                        a.getLegajo(),
                        a.getNombre(),
                        a.getApellido(),
                });
            }
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            legajoNoExisteException.printStackTrace();
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            cursoNoExisteException.printStackTrace();
        }
    }

    public void reiniciarTablaCursosAlumno(DefaultTableModel modeloTabla, int legajo){
        modeloTabla.setRowCount(0);
        AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
        try {
            for(Curso c : alumnoDAO.listaCursosAlumno(alumnoDAO.muestraAlumno(legajo))){
                modeloTabla.addRow(new Object[]{
                        c.getId(),
                        c.getNombre(),
                        c.getPrecio(),
                        c.getCupo(),
                });
            }
        } catch (DAOCursoNoExisteException cursoNoExisteException) {
            cursoNoExisteException.printStackTrace();
        } catch (DAOLegajoNoExisteException legajoNoExisteException) {
            legajoNoExisteException.printStackTrace();
        }

    }

    public Profesor_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarProfesorSwing() {
        //construct preComponents


        //construct components
        JLabel textAlumno = new JLabel("Legajo Alumno");
        JButton buttonBuscarAlumno = new JButton("Buscar");
        JLabel textCursosAlumno = new JLabel("Cursos del Alumno");
        JLabel textAlumnosCurso = new JLabel("Alumnos del Curso");
        JLabel textCurso = new JLabel("Codigo Curso");
        JTextField fieldAlumno = new JTextField(5);
        JTextField fieldCurso = new JTextField(5);
        JButton buttonBuscarCurso = new JButton("Buscar");
        JButton buttonCrear = new JButton("Crear Alumno");
        JButton buttonInscribir = new JButton("Inscribir Alumno");
        JButton buttonVolver = new JButton("Volver");
        JButton buttonEditar = new JButton("Editar Curso");
        JButton buttonReporte = new JButton("Reporte Curso");


        JTable tablaAlumnosCurso = new JTable();
        DefaultTableModel modeloTablaAlumnosCurso = new DefaultTableModel();
        JScrollPane scrollPaneAlumnosCurso = new JScrollPane();
        scrollPaneAlumnosCurso.setViewportView(tablaAlumnosCurso);
        modeloTablaAlumnosCurso.setColumnIdentifiers(new Object[]{"LEGAJO", "NOMBRE", "APELLIDO"});
        tablaAlumnosCurso.setModel(modeloTablaAlumnosCurso);
        tablaAlumnosCurso.setEnabled(false);
        tablaAlumnosCurso.setDefaultEditor(Object.class, null);
        modeloTablaAlumnosCurso.setRowCount(0);



        buttonBuscarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarTablaAlumnosCurso(modeloTablaAlumnosCurso, Integer.parseInt(fieldCurso.getText()));
            }
        });

        JTable tablaCursosAlumno = new JTable();
        DefaultTableModel modeloTablaCursosAlumno = new DefaultTableModel();
        JScrollPane scrollPaneCursosAlumno = new JScrollPane();
        scrollPaneCursosAlumno.setViewportView(tablaCursosAlumno);
        modeloTablaCursosAlumno.setColumnIdentifiers(new Object[]{"ID_CURSO", "NOMBRE", "PRECIO", "CUPO"});
        tablaCursosAlumno.setModel(modeloTablaCursosAlumno);
        tablaCursosAlumno.setEnabled(false);
        tablaCursosAlumno.setDefaultEditor(Object.class, null);
        modeloTablaCursosAlumno.setRowCount(0);

        buttonBuscarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    reiniciarTablaCursosAlumno(modeloTablaCursosAlumno, Integer.parseInt(fieldAlumno.getText()));
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                }
            }
        });

        buttonInscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelInscribirAlumno();
            }
        });

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCreacionAlumno();
            }
        });





        //adjust size and set layout
        setPreferredSize (new Dimension (800, 569));
        setLayout (null);

        //add components
        add (scrollPaneAlumnosCurso);
        add (scrollPaneCursosAlumno);
        add (textAlumno);
        add (buttonBuscarAlumno);
        add (textCursosAlumno);
        add (textAlumnosCurso);
        add (textCurso);
        add (fieldAlumno);
        add (fieldCurso);
        add (buttonBuscarCurso);
        add (buttonCrear);
        add (buttonInscribir);
        add (buttonVolver);
        add (buttonEditar);
        add (buttonReporte);

        //set component bounds (only needed by Absolute Positioning)
        scrollPaneAlumnosCurso.setBounds ( 385, 305, 330, 220);
        scrollPaneCursosAlumno.setBounds (385, 90, 330, 115);
        textAlumno.setBounds (385, 50, 100, 25);
        buttonBuscarAlumno.setBounds (625, 50, 90, 25);
        textCursosAlumno.setBounds (410, 10, 120, 25);
        textAlumnosCurso.setBounds (410, 225, 140, 25);
        textCurso.setBounds (385, 265, 100, 25);
        fieldAlumno.setBounds (475, 50, 135, 25);
        fieldCurso.setBounds (475, 265, 135, 25);
        buttonBuscarCurso.setBounds (625, 265, 90, 25);
        buttonCrear.setBounds (100, 80, 140, 45);
        buttonInscribir.setBounds (100, 180, 140, 45);
        buttonVolver.setBounds (110, 485, 120, 40);
        buttonEditar.setBounds (100, 280, 140, 45);
        buttonReporte.setBounds (100, 380, 140, 45);
    }


}