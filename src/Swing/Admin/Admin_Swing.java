package Swing.Admin;

import DAO.Cursada.CursadaDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursadaServicio;
import Services.CursoServicio;
import Services.NotaServicio;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursadaTableModel;
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
    CursadaServicio servCursada = new CursadaServicio();

    private JScrollPane scrollpaneAlumnos;
    private JScrollPane scrollpaneCursos;
    private JScrollPane scrollpaneNotas;
    private JScrollPane scrollPaneCursadas;

    private JButton buttonCrearAlumno;
    private JButton buttonEliminarAlumno;
    private JButton buttonModificarAlumno;
    private JButton buttonModificarCurso;
    private JButton buttonEliminarCurso;
    private JButton buttonCrearCurso;
    private JButton buttonCrearNota;
    private JButton buttonEliminarNota;
    private JButton buttonModificarNota;
    private JButton buttonCrearCursada;
    private JButton buttonEliminarCursada;
    private JButton buttonModificarCursada;

    private JLabel textAlumnos;
    private JLabel textCursos;
    private JLabel textNotas;
    private JLabel textCursadas;

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
        CursadaTableModel cursadaTableModel = new CursadaTableModel();

        JTable tablaAlumnos = new JTable(alumnoTableModel);
        JTable tablaCursos = new JTable(cursoTableModel);
        JTable tablaNotas = new JTable(notaTableModel);
        JTable tablaCursada = new JTable(cursadaTableModel);

        scrollpaneAlumnos = new JScrollPane (tablaAlumnos);
        scrollpaneCursos = new JScrollPane (tablaCursos);
        scrollpaneNotas = new JScrollPane (tablaNotas);
        scrollPaneCursadas = new JScrollPane(tablaCursada);

        alumnoTableModel.setContenido(servAlumno.listarAlumnos());
        cursoTableModel.setContenido(servCurso.listarCursos());
        notaTableModel.setContenido(servNota.listarTodasLasNotas());
        cursadaTableModel.setContenido(servCursada.mostrarCursadas());

        buttonCrearAlumno = new JButton ("Crear");
        buttonEliminarAlumno = new JButton ("Eliminar");
        buttonModificarAlumno = new JButton ("Modificar");
        buttonModificarCurso = new JButton ("Modificar");
        buttonEliminarCurso = new JButton ("Eliminar");
        buttonCrearCurso = new JButton ("Crear");
        buttonCrearNota = new JButton ("Crear");
        buttonEliminarNota = new JButton ("Eliminar");
        buttonModificarNota = new JButton ("Modificar");
        buttonCrearCursada = new JButton ("Crear");
        buttonEliminarCursada = new JButton ("Eliminar");
        buttonModificarCursada = new JButton ("Modificar");

        textAlumnos = new JLabel ("ALUMNOS");
        textCursos = new JLabel ("CURSOS");
        textNotas = new JLabel ("NOTAS");
        textCursadas = new JLabel ("CURSADAS");

        buttonVolver = new JButton ("Volver");
        buttonActualizarTablas = new JButton ("Actualizar Tablas");

        buttonEliminarAlumno.setEnabled(false);
        buttonEliminarCurso.setEnabled(false);
        buttonEliminarNota.setEnabled(false);
        buttonEliminarCursada.setEnabled(false);

        //adjust size and set layout
        setLayout (null);

        //listeners
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

        tablaNotas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionadaNotas = tablaNotas.getSelectedRow();
                if(filaSeleccionadaNotas != -1){
                    buttonEliminarNota.setEnabled(true);
                }
            }
        });

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
                alumnoTableModel.fireTableDataChanged();
                buttonEliminarAlumno.setEnabled(false);

            }
        });

        buttonCrearNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCrearNota();
            }
        });

        buttonEliminarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionadaNotas = tablaNotas.getSelectedRow();
                Nota c = notaTableModel.getContenido().get(filaSeleccionadaNotas);
                NotaServicio servNota = new NotaServicio();

                try {
                    servNota.eliminarNota(c.getAlumno().getLegajo(), c.getCurso().getId(), c.getTipoNota());
                    buttonEliminarNota.setEnabled(false);
                    notaTableModel.getContenido().remove(filaSeleccionadaNotas);
                    notaTableModel.fireTableDataChanged();
                } catch (ServiceNotaNoExisteException serviceNotaNoExisteException) {
                    serviceNotaNoExisteException.printStackTrace();
                } catch (ServiceNotaParcialesDependenDeFinalException serviceNotaParcialesDependenDeFinalException) {
                    JOptionPane.showMessageDialog(null, "El final de la materia depende de la nota a eliminar",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonModificarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelEditarNota();
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
                servCurso.borrarCurso(c.getId());
                cursoTableModel.getContenido().remove(filaSeleccionadaCursos);
                cursoTableModel.fireTableDataChanged();
            }
        });

        buttonModificarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelEditarCurso();
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

                cursadaTableModel.setContenido(new ArrayList<>());
                cursadaTableModel.setContenido(servCursada.mostrarCursadas());
                cursoTableModel.fireTableDataChanged();
            }
        });

        buttonCrearCursada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCrearCursada();
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere volver al menu principal?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION) {
                    panelManager.mostrarPanelInicioSesion();
                }
            }
        });

        //add components
        add (scrollpaneAlumnos);
        add (scrollpaneCursos);
        add (scrollpaneNotas);
        add (scrollPaneCursadas);

        add (buttonCrearAlumno);
        add (buttonEliminarAlumno);
        add (buttonModificarAlumno);
        add (buttonModificarCurso);
        add (buttonEliminarCurso);
        add (buttonCrearCurso);
        add (buttonCrearNota);
        add (buttonEliminarNota);
        add (buttonModificarNota);

        add (buttonCrearCursada);
        add (buttonEliminarCursada);
        add (buttonModificarCursada);

        add (textAlumnos);
        add (textCursos);
        add (textNotas);
        add (textCursadas);

        add (buttonVolver);
        add (buttonActualizarTablas);
        //set component bounds (only needed by Absolute Positioning)
        scrollpaneAlumnos.setBounds (45, 255, 315, 515);
        scrollpaneCursos.setBounds (395, 255, 315, 515);
        scrollpaneNotas.setBounds (745, 255, 315, 515);
        scrollPaneCursadas.setBounds (1095, 255, 315, 515);

        buttonCrearAlumno.setBounds (155, 70, 100, 25);
        buttonEliminarAlumno.setBounds (155, 130, 100, 25);
        buttonModificarAlumno.setBounds (155, 190, 100, 25);
        buttonModificarCurso.setBounds (500, 190, 100, 25);
        buttonEliminarCurso.setBounds (500, 130, 100, 25);
        buttonCrearCurso.setBounds (500, 70, 100, 25);
        buttonCrearNota.setBounds (850, 70, 100, 25);
        buttonEliminarNota.setBounds (850, 130, 100, 25);
        buttonModificarNota.setBounds (850, 190, 100, 25);
        buttonCrearCursada.setBounds (1200, 70, 100, 25);
        buttonEliminarCursada.setBounds (1200, 130, 100, 25);
        buttonModificarCursada.setBounds (1200, 190, 100, 25);

        textAlumnos.setBounds (175, 25, 60, 25);
        textCursos.setBounds (525, 25, 50, 25);
        textNotas.setBounds (880, 20, 40, 25);
        textCursadas.setBounds (1215, 20, 65, 25);
        textCursadas.setBounds (1215, 20, 65, 25);

        buttonVolver.setBounds (1300, 795, 100, 25);
        buttonActualizarTablas.setBounds (635, 790, 185, 35);
    }


}
