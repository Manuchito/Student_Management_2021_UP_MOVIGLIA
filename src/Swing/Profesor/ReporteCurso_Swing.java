package Swing.Profesor;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Exceptions.ServiceNoHayAprobadosException;
import Main.PanelManager;
import Services.CursadaServicio;
import Services.CursoServicio;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursoTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

public class ReporteCurso_Swing extends JPanel {
    private JComboBox fieldCursoBuscar;
    private JLabel textCurso;
    private JLabel textTotal;
    private JLabel textCantidad;
    private JLabel textIdCurso;
    private JLabel textNombre;
    private JLabel textAlumnos;
    private JLabel textAprobadosPendienteFinal;
    private JLabel textPrecio;
    private JLabel textCapacidadMaxima;
    private JLabel textAprobadosFinal;
    private JButton buttonBuscar;
    private JTextField fieldCantidad;
    private JTextField fieldCantidadAlumnos;
    private JTextField fieldCapacidadMaxima;
    private JTextField fieldAprobadosPendienteFinal;
    private JTextField fieldPrecio;
    private JTextField fieldNombre;
    private JTextField fieldCurso;
    private JTextField fieldRecaudacionTotal;
    private JTextField fieldAprobadosFinal;
    private JButton buttonAprobados;
    private JButton buttonVolver;

    private AlumnoTableModel alumnoTableModel;
    private JTable tabla;
    private JScrollPane scrollTable;

    CursoServicio servCurso = new CursoServicio();
    CursadaServicio servCursada = new CursadaServicio();

    private PanelManager panelManager;

    public  ReporteCurso_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarReporteCurso() {
        //construct components
        fieldCursoBuscar = new JComboBox();
        textCurso = new JLabel("Id Curso:");
        textTotal = new JLabel("Recaudacion Total:");
        textCantidad = new JLabel("Cantidad Examenes:");
        textIdCurso = new JLabel("Id Curso:");
        textNombre = new JLabel("Nombre Curso:");
        textAlumnos = new JLabel("Alumnos anotados:");
        textAprobadosPendienteFinal = new JLabel("Aprobados Cursada:");
        textPrecio = new JLabel("Precio:");
        textCapacidadMaxima = new JLabel("Capacidad Maxima:");
        textAprobadosFinal = new JLabel("Aprobados final:");
        buttonBuscar = new JButton("Buscar");

        fieldCantidad = new JTextField();
        fieldCantidadAlumnos = new JTextField();
        fieldCapacidadMaxima = new JTextField();
        fieldAprobadosPendienteFinal = new JTextField();
        fieldPrecio = new JTextField();
        fieldNombre = new JTextField();
        fieldCurso = new JTextField();
        fieldRecaudacionTotal = new JTextField();
        fieldAprobadosFinal = new JTextField();

        buttonVolver = new JButton ("Volver");
        buttonAprobados = new JButton ("Mostrar Aprobados");


        for(Curso a : servCurso.listarCursos()){
            fieldCursoBuscar.addItem(a.getId());
        }

        fieldCantidad.setEnabled(false);
        fieldCantidadAlumnos.setEnabled(false);
        fieldCapacidadMaxima.setEnabled(false);
        fieldAprobadosPendienteFinal.setEnabled(false);
        fieldPrecio.setEnabled(false);
        fieldNombre.setEnabled(false);
        fieldCurso.setEnabled(false);
        fieldRecaudacionTotal.setEnabled(false);
        fieldAprobadosFinal.setEnabled(false);

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
                    Curso c = servCurso.muestraCurso((Integer)fieldCursoBuscar.getSelectedItem());
                    List<Alumno> alumnosCurso= servCursada.listarAlumnosDelCurso(c.getId());
                    alumnoTableModel.setContenido(alumnosCurso);
                    alumnoTableModel.fireTableDataChanged();
                    fieldCurso.setText(String.valueOf(c.getId()));
                    fieldNombre.setText(c.getNombre());
                    fieldPrecio.setText("$" + String.valueOf(c.getPrecio()));
                    fieldCantidad.setText(String.valueOf(c.getCantidad_parciales()));
                    fieldCantidadAlumnos.setText(String.valueOf(alumnosCurso.size()));
                    fieldCapacidadMaxima.setText(String.valueOf(c.getCupo()));
                    fieldRecaudacionTotal.setText("$" + String.valueOf(servCurso.muestraRecaudacionTotal(c.getId())));
                    fieldAprobadosFinal.setText(String.valueOf(servCurso.mostrarAlumnosAprobadosConFinal(c.getId()).size()));
                    fieldAprobadosPendienteFinal.setText(String.valueOf(servCurso.mostrarAlumnosAprobadosParaFinal(c.getId()).size()));

                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso "+ fieldCursoBuscar.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                } catch (ServiceNoHayAprobadosException serviceNoHayAprobadosException) {
                    fieldAprobadosPendienteFinal.setText("0");
                }
            }
        });

        buttonAprobados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alumnoTableModel.setContenido(new ArrayList<>());
                try {
                    alumnoTableModel.setContenido(servCurso.mostrarAlumnosAprobadosParaFinal((Integer)fieldCursoBuscar.getSelectedItem()));
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso "+ fieldCursoBuscar.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                } catch (ServiceNoHayAprobadosException serviceNoHayAprobadosException){
                    JOptionPane.showMessageDialog(null, "El Curso "+ fieldCurso.getText() + " acutalmente no tiene ningun aprobado para rendir final.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
                alumnoTableModel.fireTableDataChanged();
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
        add(textAprobadosPendienteFinal);
        add(textPrecio);
        add(textCapacidadMaxima);
        add(buttonBuscar);
        add(fieldCantidad);
        add(fieldCantidadAlumnos);
        add(fieldCapacidadMaxima);
        add(fieldAprobadosPendienteFinal);
        add(fieldPrecio);
        add(fieldNombre);
        add(fieldCurso);
        add(fieldRecaudacionTotal);
        add(buttonAprobados);
        add(buttonVolver);
        add(fieldAprobadosFinal);
        add(textAprobadosFinal);

        //set component bounds (only needed by Absolute Positioning)
        fieldCursoBuscar.setBounds(155, 65, 100, 25);
        textCurso.setBounds(55, 65, 100, 25);
        scrollTable.setBounds(55, 110, 600, 310);
        textTotal.setBounds(675, 430, 115, 25);
        textCantidad.setBounds(675, 230, 125, 25);
        textIdCurso.setBounds(675, 110, 100, 25);
        textNombre.setBounds(675, 150, 100, 25);
        textAlumnos.setBounds(675, 270, 110, 25);
        textAprobadosPendienteFinal.setBounds(675, 350, 160, 25);
        textPrecio.setBounds(675, 190, 100, 25);
        textCapacidadMaxima.setBounds(675, 310, 120, 25);
        textAprobadosFinal.setBounds(675, 390, 115, 25);

        buttonBuscar.setBounds(280, 65, 100, 25);

        fieldCantidad.setBounds(810, 230, 100, 25);
        fieldCantidadAlumnos.setBounds(810, 270, 100, 25);
        fieldCapacidadMaxima.setBounds(810, 310, 100, 25);
        fieldAprobadosPendienteFinal.setBounds(810, 350, 100, 25);
        fieldPrecio.setBounds(810, 190, 100, 25);
        fieldNombre.setBounds(810, 150, 100, 25);
        fieldCurso.setBounds(810, 110, 100, 25);
        fieldAprobadosFinal.setBounds(810, 390, 100, 25);
        fieldRecaudacionTotal.setBounds(810, 430, 100, 25);

        buttonAprobados.setBounds (405, 65, 145, 25);
        buttonVolver.setBounds (810, 485, 100, 25);
    }
}
