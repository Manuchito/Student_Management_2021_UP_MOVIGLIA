package Swing;

import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import Services.ParcialServicio;
import Swing.Tablas.CursoTableModel;
import Swing.Tablas.NotaTableModel;

import java.awt.event.*;
import javax.swing.*;

public class CalificarAlumno_Swing extends JPanel {
    private JTextField fieldLegajo;
    private JTextField fieldCurso;
    private JTextField fieldNota;
    private JLabel textAlumno;
    private JLabel textCurso;
    private JLabel textNumeroNota;
    private JComboBox fieldTipoNota;
    private JLabel textParcial;
    private JLabel jcomp11;
    private JButton buttonCalificarAlumno;
    private JButton buttonVolver;
    private JLabel jcomp14;
    private JToggleButton buttonBuscarAlumno;

    private CursoTableModel cursoTableModel;
    private JTable tablaCursosAlumno;
    private JScrollPane scrollPaneCursosAlumno;

    private NotaTableModel parcialTableModel;
    private JTable tablaNotasAlumno;
    private JScrollPane scrollPaneNotasAlumno;

    AlumnoServicio servAlumno = new AlumnoServicio();
    CursoServicio servCurso = new CursoServicio();
    ParcialServicio servParcial = new ParcialServicio();

    private PanelManager panelManager;

    public CalificarAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }
    private void tablaCursosAlumnoMouseClicked(MouseEvent evt){
        CursoTableModel model = new CursoTableModel();
        int selectedRowIndex = tablaCursosAlumno.getSelectedRow();
        fieldCurso.setText(model.getValueAt(selectedRowIndex, 1).toString());

    }
    public void armarCalificarCurso() {
        //construct preComponents




        //construct components
        fieldLegajo = new JTextField (5);
        fieldCurso = new JTextField (5);
        fieldNota = new JTextField (5);
        textAlumno = new JLabel ("Legajo Alumno");
        textCurso = new JLabel ("Id Curso");
        textNumeroNota = new JLabel ("Numero Parcial");
        fieldTipoNota = new JComboBox ();
        fieldTipoNota.addItem("FINAL");
        textParcial = new JLabel ("Nota Parcial");
        jcomp11 = new JLabel ("NOTAS DEL ALUMNO");
        buttonCalificarAlumno = new JButton ("Calificar Alumno");
        buttonVolver = new JButton ("Volver");
        jcomp14 = new JLabel ("CURSOS DEL ALUMNO");
        buttonBuscarAlumno = new JToggleButton ("Buscar Alumno", false);

        //adjust size and set layout
        setLayout (null);


        //listeners
        cursoTableModel = new CursoTableModel();
        tablaCursosAlumno = new JTable(cursoTableModel);
        scrollPaneCursosAlumno = new JScrollPane(tablaCursosAlumno);

        parcialTableModel = new NotaTableModel();
        tablaNotasAlumno = new JTable(parcialTableModel);
        scrollPaneNotasAlumno = new JScrollPane(tablaNotasAlumno);

        buttonBuscarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servAlumno = new AlumnoServicio();
                try {
                    cursoTableModel.setContenido(servAlumno.listarCursosDelAlumno(Integer.parseInt(fieldLegajo.getText())));
                    cursoTableModel.fireTableDataChanged();
                    parcialTableModel.setContenido(servParcial.listarParcialesAlumno(Integer.parseInt(fieldLegajo.getText())));
                    parcialTableModel.fireTableDataChanged();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                }
            }
        });

        buttonCalificarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servParcial = new ParcialServicio();
                try{
                    servParcial.calificarAlumno(Integer.parseInt(fieldLegajo.getText()), Integer.parseInt(fieldCurso.getText()), (String)fieldTipoNota.getSelectedItem(), Integer.parseInt(fieldNota.getText()));
                    parcialTableModel.setContenido(null);
                    parcialTableModel.setContenido(servParcial.listarParcialesAlumno(Integer.parseInt(fieldLegajo.getText())));

                    //parcialTableModel.fireTableDataChanged(); NOT WORKING

                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                }

            }

        });

        tablaCursosAlumno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int filaSeleccionada = tablaCursosAlumno.getSelectedRow();
                Curso curso = cursoTableModel.getContenido().get(filaSeleccionada);
                fieldCurso.setText(String.valueOf(curso.getId()));
                fieldTipoNota.removeAllItems();
                fieldTipoNota.addItem("FINAL");
                for (int i = 1; i <= curso.getCantidad_parciales(); i++) {
                    fieldTipoNota.addItem("Parcial " + i);
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
        add (scrollPaneNotasAlumno);
        add (fieldLegajo);
        add (fieldCurso);
        add (fieldNota);
        add (textAlumno);
        add (textCurso);
        add (textNumeroNota);
        add (fieldTipoNota);
        add (textParcial);
        add (scrollPaneCursosAlumno);
        add (jcomp11);
        add (buttonCalificarAlumno);
        add (buttonVolver);
        add (jcomp14);
        add (buttonBuscarAlumno);

        //set component bounds (only needed by Absolute Positioning)
        scrollPaneNotasAlumno.setBounds (395, 265, 250, 155);
        fieldLegajo.setBounds (175, 65, 100, 25);
        fieldCurso.setBounds (175, 145, 100, 25);
        fieldNota.setBounds (175, 225, 100, 25);
        textAlumno.setBounds (55, 65, 120, 25);
        textCurso.setBounds (55, 145, 125, 25);
        textNumeroNota.setBounds (55, 305, 120, 25);
        fieldTipoNota.setBounds (175, 305, 100, 25);
        textParcial.setBounds (55, 225, 120, 25);
        scrollPaneCursosAlumno.setBounds (395, 65, 250, 155);
        jcomp11.setBounds (460, 235, 120, 30);
        buttonCalificarAlumno.setBounds (210, 395, 130, 25);
        buttonVolver.setBounds (550, 445, 100, 25);
        jcomp14.setBounds (455, 40, 130, 25);
        buttonBuscarAlumno.setBounds (55, 395, 130, 25);
    }


}