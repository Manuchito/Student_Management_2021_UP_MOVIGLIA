package Swing;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.CursoServicio;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursoTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

public class ReporteCurso_Swing extends JPanel {
    private JTextField fieldCursoBuscar;
    private JLabel textCurso;
    private JLabel textTotal;
    private JLabel textCantidad;
    private JLabel textIdCurso;
    private JLabel textNombre;
    private JLabel textAlumnos;
    private JLabel textAprobados;
    private JLabel textPrecio;
    private JLabel textCapacidadMaxima;
    private JButton buttonBuscar;
    private JTextField fieldCantidad;
    private JTextField fieldCantidadAlumnos;
    private JTextField fieldCapacidadMaxima;
    private JTextField fieldAprobados;
    private JTextField fieldPrecio;
    private JTextField fieldNombre;
    private JTextField fieldCurso;
    private JTextField fieldRecaudacionTotal;
    private JButton buttonAprobados;
    private JButton buttonVolver;

    private AlumnoTableModel alumnoTableModel;
    private JTable tabla;
    private JScrollPane scrollTable;

    CursoServicio servCurso = new CursoServicio();

    private PanelManager panelManager;

    public  ReporteCurso_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarReporteCurso() {
        //construct components
        fieldCursoBuscar = new JTextField(5);
        textCurso = new JLabel("Id Curso:");
        textTotal = new JLabel("Recaudacion Total:");
        textCantidad = new JLabel("Cantidad Examenes:");
        textIdCurso = new JLabel("Id Curso:");
        textNombre = new JLabel("Nombre Curso:");
        textAlumnos = new JLabel("Alumnos anotados:");
        textAprobados = new JLabel("Alumnos aprobados:");
        textPrecio = new JLabel("Precio:");
        textCapacidadMaxima = new JLabel("Capacidad Maxima:");
        buttonBuscar = new JButton("Buscar");
        fieldCantidad = new JTextField(5);
        fieldCantidadAlumnos = new JTextField(5);
        fieldCapacidadMaxima = new JTextField(5);
        fieldAprobados = new JTextField(5);
        fieldPrecio = new JTextField(5);
        fieldNombre = new JTextField(5);
        fieldCurso = new JTextField(5);
        fieldRecaudacionTotal = new JTextField(5);
        buttonVolver = new JButton ("Volver");
        buttonAprobados = new JButton ("Mostrar Aprobados");

        //set components properties
        fieldCantidad.setEnabled(false);
        fieldCantidadAlumnos.setEnabled(false);
        fieldCapacidadMaxima.setEnabled(false);
        fieldAprobados.setEnabled(false);
        fieldPrecio.setEnabled(false);
        fieldNombre.setEnabled(false);
        fieldCurso.setEnabled(false);
        fieldRecaudacionTotal.setEnabled(false);

        //adjust size and set layout
        setLayout(null);

        //listeners
        alumnoTableModel = new AlumnoTableModel();
        tabla = new JTable(alumnoTableModel);
        scrollTable = new JScrollPane(tabla);
        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Curso c = servCurso.muestraCurso(Integer.parseInt(fieldCursoBuscar.getText()));
                    List<Alumno> alumnosCurso= servCurso.listarAlumnosDelCurso(c.getId());
                    alumnoTableModel.setContenido(alumnosCurso);
                    alumnoTableModel.fireTableDataChanged();
                    fieldCurso.setText(String.valueOf(c.getId()));
                    fieldNombre.setText(c.getNombre());
                    fieldPrecio.setText("$" + String.valueOf(c.getPrecio()));
                    fieldCantidad.setText(String.valueOf(c.getCantidad_parciales()));
                    fieldCantidadAlumnos.setText(String.valueOf(alumnosCurso.size()));
                    fieldCapacidadMaxima.setText(String.valueOf(c.getCupo()));
                    fieldAprobados.setText(String.valueOf(servCurso.mostrarAlumnosAprobadosParaFinal(c.getId()).size()));
                    fieldRecaudacionTotal.setText("$" + String.valueOf(servCurso.muestraRecaudacionTotal(c.getId())));

                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }
            }
        });

        buttonAprobados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoTableModel.setContenido(null);
                try {
                    alumnoTableModel.setContenido(servCurso.mostrarAlumnosAprobadosParaFinal(Integer.parseInt(fieldCurso.getText())));
                    alumnoTableModel.fireTableDataChanged();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelProfesor();
            }
        });

        //add components
        add(fieldCursoBuscar);
        add(textCurso);
        add(scrollTable);
        add(textTotal);
        add(textCantidad);
        add(textIdCurso);
        add(textNombre);
        add(textAlumnos);
        add(textAprobados);
        add(textPrecio);
        add(textCapacidadMaxima);
        add(buttonBuscar);
        add(fieldCantidad);
        add(fieldCantidadAlumnos);
        add(fieldCapacidadMaxima);
        add(fieldAprobados);
        add(fieldPrecio);
        add(fieldNombre);
        add(fieldCurso);
        add(fieldRecaudacionTotal);
        add (buttonAprobados);
        add (buttonVolver);

        //set component bounds (only needed by Absolute Positioning)
        fieldCursoBuscar.setBounds(155, 65, 100, 25);
        textCurso.setBounds(55, 65, 100, 25);
        scrollTable.setBounds(55, 110, 600, 310);
        textTotal.setBounds(675, 390, 115, 25);
        textCantidad.setBounds(675, 230, 125, 25);
        textIdCurso.setBounds(675, 110, 100, 25);
        textNombre.setBounds(675, 150, 100, 25);
        textAlumnos.setBounds(675, 270, 110, 25);
        textAprobados.setBounds(675, 350, 160, 25);
        textPrecio.setBounds(675, 190, 100, 25);
        textCapacidadMaxima.setBounds(675, 310, 120, 25);
        buttonBuscar.setBounds(280, 65, 100, 25);
        fieldCantidad.setBounds(810, 230, 100, 25);
        fieldCantidadAlumnos.setBounds(810, 270, 100, 25);
        fieldCapacidadMaxima.setBounds(810, 310, 100, 25);
        fieldAprobados.setBounds(810, 350, 100, 25);
        fieldPrecio.setBounds(810, 190, 100, 25);
        fieldNombre.setBounds(810, 150, 100, 25);
        fieldCurso.setBounds(810, 110, 100, 25);
        fieldRecaudacionTotal.setBounds(810, 390, 100, 25);
        buttonAprobados.setBounds (405, 65, 145, 25);
        buttonVolver.setBounds (810, 445, 100, 25);
    }
}
