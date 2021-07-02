package Swing.Admin;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.IntegerVaciaException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import Services.NotaServicio;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursoTableModel;
import Swing.Tablas.NotaTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class Admin_Swing extends JPanel {
    AlumnoServicio servAlumno = new AlumnoServicio();
    CursoServicio servCurso = new CursoServicio();
    NotaServicio servNota = new NotaServicio();

    private JScrollPane scrollpaneAlumnos;
    private JScrollPane scrollpaneCursos;
    private JScrollPane scrollpaneNotas;

    private JButton buttonCrearAlumno;
    private JButton buttonEliminarAlumno;
    private JButton buttonModificarAlumno;
    private JButton buttonModificarCurso;
    private JButton buttonEliminarCurso;
    private JButton buttonCrearCurso;
    private JButton buttonCrearNota;
    private JButton buttonEliminarNota;
    private JButton buttonModificarNota;

    private JLabel textAlumnos;
    private JLabel textCursos;
    private JLabel textNotas;

    private JButton buttonVolver;

    private JButton buttonActualizarTablas;



    private PanelManager panelManager;

    public Admin_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarAdminSwing() {
        //construct components

        AlumnoTableModel alumnoTableModel = new AlumnoTableModel();
        CursoTableModel cursoTableModel = new CursoTableModel();
        NotaTableModel notaTableModel = new NotaTableModel();

        JTable tablaAlumnos = new JTable(alumnoTableModel);
        JTable tablaCursos = new JTable(cursoTableModel);
        JTable tablaNotas = new JTable(notaTableModel);

        scrollpaneAlumnos = new JScrollPane (tablaAlumnos);
        scrollpaneCursos = new JScrollPane (tablaCursos);
        scrollpaneNotas = new JScrollPane (tablaNotas);




        alumnoTableModel.setContenido(servAlumno.listarAlumnos());

        cursoTableModel.setContenido(servCurso.listarCursos());

        notaTableModel.setContenido(servNota.listarTodasLasNotas());

        buttonCrearAlumno = new JButton ("Crear");
        buttonEliminarAlumno = new JButton ("Eliminar");
        buttonModificarAlumno = new JButton ("Modificar");
        buttonModificarCurso = new JButton ("Modificar");
        buttonEliminarCurso = new JButton ("Eliminar");
        buttonCrearCurso = new JButton ("Crear");
        buttonCrearNota = new JButton ("Crear");
        buttonEliminarNota = new JButton ("Eliminar");
        buttonModificarNota = new JButton ("Modificar");
        textAlumnos = new JLabel ("ALUMNOS");
        textCursos = new JLabel ("CURSOS");
        textNotas = new JLabel ("NOTAS");

        buttonVolver = new JButton ("Volver");
        buttonActualizarTablas = new JButton ("Actualizar Tablas");



        buttonEliminarAlumno.setEnabled(false);
        buttonEliminarCurso.setEnabled(false);

        //adjust size and set layout

        setLayout (null);

        //listeners
        buttonCrearAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCreacionAlumno();
            }
        });

        buttonModificarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelEditarAlumno();
            }
        });


        buttonEliminarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaAlumnos.getSelectedRow();
                Alumno a = alumnoTableModel.getContenido().get(filaSeleccionada);

                AlumnoServicio servAlumno = new AlumnoServicio();
                buttonEliminarAlumno.setEnabled(false);
                servAlumno.eliminar(a.getLegajo());
                alumnoTableModel.getContenido().remove(filaSeleccionada);
                buttonEliminarAlumno.setEnabled(false);

            }
        });
        tablaAlumnos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionadaAlumnos = tablaAlumnos.getSelectedRow();
                if(filaSeleccionadaAlumnos != -1){
                    buttonEliminarAlumno.setEnabled(true);
                }
            }
        });

        tablaCursos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionadaCursos = tablaCursos.getSelectedRow();
                if(filaSeleccionadaCursos != -1){
                    buttonEliminarCurso.setEnabled(true);
                }
            }
        });

        buttonCrearCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCrearCurso();
            }
        });

        buttonEliminarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionadaCursos = tablaCursos.getSelectedRow();
                Curso c = cursoTableModel.getContenido().get(filaSeleccionadaCursos);

                CursoServicio servCurso = new CursoServicio();
                buttonEliminarCurso.setEnabled(false);
                cursoTableModel.fireTableDataChanged();
                servCurso.borrarCurso(c.getId());
                cursoTableModel.getContenido().remove(filaSeleccionadaCursos);
            }
        });

        buttonActualizarTablas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoTableModel.setContenido(new ArrayList<>());
                alumnoTableModel.setContenido(servAlumno.listarAlumnos());
                alumnoTableModel.fireTableDataChanged();

                cursoTableModel.setContenido(new ArrayList<>());
                cursoTableModel.setContenido(servCurso.listarCursos());
                cursoTableModel.fireTableDataChanged();

                notaTableModel.setContenido(new ArrayList<>());
                notaTableModel.setContenido(servNota.listarTodasLasNotas());
                notaTableModel.fireTableDataChanged();
            }
        });




        //add components
        add (scrollpaneAlumnos);
        add (scrollpaneCursos);
        add (scrollpaneNotas);
        add (buttonCrearAlumno);
        add (buttonEliminarAlumno);
        add (buttonModificarAlumno);
        add (buttonModificarCurso);
        add (buttonEliminarCurso);
        add (buttonCrearCurso);
        add (buttonCrearNota);
        add (buttonEliminarNota);
        add (buttonModificarNota);
        add (textAlumnos);
        add (textCursos);
        add (textNotas);
        add (buttonVolver);
        add (buttonActualizarTablas);
        //set component bounds (only needed by Absolute Positioning)
        scrollpaneAlumnos.setBounds (45, 255, 315, 515);
        scrollpaneCursos.setBounds (395, 255, 315, 515);
        scrollpaneNotas.setBounds (745, 255, 315, 515);
        buttonCrearAlumno.setBounds (155, 70, 100, 25);
        buttonEliminarAlumno.setBounds (155, 130, 100, 25);
        buttonModificarAlumno.setBounds (155, 190, 100, 25);
        buttonModificarCurso.setBounds (500, 190, 100, 25);
        buttonEliminarCurso.setBounds (500, 130, 100, 25);
        buttonCrearCurso.setBounds (500, 70, 100, 25);
        buttonCrearNota.setBounds (850, 70, 100, 25);
        buttonEliminarNota.setBounds (850, 130, 100, 25);
        buttonModificarNota.setBounds (850, 190, 100, 25);
        textAlumnos.setBounds (175, 25, 60, 25);
        textCursos.setBounds (525, 25, 50, 25);
        textNotas.setBounds (880, 20, 40, 25);
        buttonVolver.setBounds (925, 790, 100, 25);
        buttonActualizarTablas.setBounds (460, 785, 185, 35);
    }


}
