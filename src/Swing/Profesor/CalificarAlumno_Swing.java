package Swing.Profesor;

import Entidades.Alumno;
import Entidades.Curso;
import Entidades.Nota;
import Exceptions.*;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursadaServicio;
import Services.CursoServicio;
import Services.NotaServicio;
import Swing.Tablas.CursoTableModel;
import Swing.Tablas.NotaTableModel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class CalificarAlumno_Swing extends JPanel {
    private JComboBox fieldLegajo;
    private JTextField fieldCurso;
    private JComboBox fieldNota;
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
    private JButton buttonEliminarNota;

    private CursoTableModel cursoTableModel;
    private JTable tablaCursosAlumno;
    private JScrollPane scrollPaneCursosAlumno;

    private NotaTableModel parcialTableModel;
    private JTable tablaNotasAlumno;
    private JScrollPane scrollPaneNotasAlumno;

    AlumnoServicio servAlumno = new AlumnoServicio();
    CursoServicio servCurso = new CursoServicio();
    NotaServicio servParcial = new NotaServicio();
    CursadaServicio servCursada = new CursadaServicio();

    private PanelManager panelManager;

    public CalificarAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarCalificarCurso() {
        //construct components
        String[] numerNotasItems = {"1","2","3","4","5","6","7","8","9","10"};
        fieldLegajo = new JComboBox();
        fieldCurso = new JTextField (5);
        fieldNota = new JComboBox (numerNotasItems);
        textAlumno = new JLabel ("Legajo Alumno");
        textCurso = new JLabel ("Id Curso");
        textNumeroNota = new JLabel ("Numero Parcial");
        fieldTipoNota = new JComboBox();
        textParcial = new JLabel ("Nota Parcial");
        jcomp11 = new JLabel ("NOTAS DEL ALUMNO");
        buttonCalificarAlumno = new JButton ("Calificar Alumno");
        buttonVolver = new JButton ("Volver");
        jcomp14 = new JLabel ("CURSOS DEL ALUMNO");
        buttonBuscarAlumno = new JToggleButton ("Buscar Alumno", false);
        buttonEliminarNota = new JButton("Eliminar Nota");

        fieldCurso.setEnabled(false);
        fieldTipoNota.setEnabled(false);
        fieldNota.setEnabled(false);
        buttonCalificarAlumno.setEnabled(false);
        buttonEliminarNota.setEnabled(false);

        //adjust size and set layout
        setLayout (null);


        //listeners
        cursoTableModel = new CursoTableModel();
        tablaCursosAlumno = new JTable(cursoTableModel);
        scrollPaneCursosAlumno = new JScrollPane(tablaCursosAlumno);

        parcialTableModel = new NotaTableModel();
        tablaNotasAlumno = new JTable(parcialTableModel);
        scrollPaneNotasAlumno = new JScrollPane(tablaNotasAlumno);


        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent)
            {
                int state = itemEvent.getStateChange();

                if (state == ItemEvent.SELECTED) {
                    try {
                        if(buttonBuscarAlumno.isSelected()){
                            cursoTableModel.setContenido(servCursada.listarCursosDelAlumno((Integer)fieldLegajo.getSelectedItem()));
                            cursoTableModel.fireTableDataChanged();
                            parcialTableModel.setContenido(servParcial.listarNotasAlumno((Integer)fieldLegajo.getSelectedItem()));
                            parcialTableModel.fireTableDataChanged();
                            fieldCurso.setText("");
                            fieldCurso.setEnabled(true);
                            fieldTipoNota.setEnabled(true);
                            fieldNota.setEnabled(true);
                            fieldLegajo.setEnabled(false);
                            buttonCalificarAlumno.setEnabled(true);
                            buttonEliminarNota.setEnabled(true);

                        }

                    } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                        JOptionPane.showMessageDialog(null, "El alumno "+ fieldLegajo.getSelectedItem() +" no existe.",
                                "Error tipo missing", JOptionPane.ERROR_MESSAGE);
                    } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                        serviceCursoNoExisteException.printStackTrace();
                    }
                }
                else {

                    fieldLegajo.setEnabled(true);

                    fieldCurso.setEnabled(false);
                    fieldCurso.setText("");

                    fieldNota.setSelectedItem("1");
                    fieldNota.setEnabled(false);

                    fieldTipoNota.setEnabled(false);
                    fieldTipoNota.removeAllItems();

                    buttonCalificarAlumno.setEnabled(false);
                    buttonEliminarNota.setEnabled(false);

                    parcialTableModel.setContenido(new ArrayList<>());
                    cursoTableModel.setContenido(new ArrayList<>());

                    parcialTableModel.fireTableDataChanged();
                    cursoTableModel.fireTableDataChanged();


                }
            }
        };

        buttonBuscarAlumno.addItemListener(itemListener);

        fieldLegajo.getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(fieldLegajo.isEnabled()){
                   fieldLegajo.removeAllItems();
                   for(Alumno a : servAlumno.listarAlumnos()){
                       fieldLegajo.addItem(a.getLegajo());
                   }
               }
            }
        });

        buttonCalificarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servParcial = new NotaServicio();

                try{
                    servParcial.calificarAlumno((Integer)fieldLegajo.getSelectedItem(), Integer.parseInt(fieldCurso.getText()), (String)fieldTipoNota.getSelectedItem(), Integer.parseInt((String)fieldNota.getSelectedItem()));
                    JOptionPane.showMessageDialog(null, "Usted califico con exito al Alumno con LEGAJO: " + fieldLegajo.getSelectedItem() + " Curso con ID: " + fieldCurso.getText() + " en el TIPO NOTA: " + fieldTipoNota.getSelectedItem(), "Aviso de edición", JOptionPane.INFORMATION_MESSAGE);
                    parcialTableModel.setContenido(null);
                    parcialTableModel.setContenido(servParcial.listarNotasCursoDelAlumno((Integer)fieldLegajo.getSelectedItem(), Integer.parseInt(fieldCurso.getText())));
                    cursoTableModel.setContenido(null);
                    cursoTableModel.setContenido(servCursada.listarCursosDelAlumno((Integer)fieldLegajo.getSelectedItem()));
                    cursoTableModel.fireTableDataChanged();
                    parcialTableModel.fireTableDataChanged();

                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    JOptionPane.showMessageDialog(null, "El alumno "+ fieldLegajo.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso "+ fieldCurso.getText() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceInsificientesParcialesAprobadosException serviceInsificientesParcialesAprobadosException) {
                    JOptionPane.showMessageDialog(null, "El Alumno"+ fieldLegajo.getSelectedItem() +" no tiene los fucientes parciales para rendir FINAL.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceClaveDuplicadaException serviceClaveDuplicadaException) {
                    JOptionPane.showMessageDialog(null, "El Alumno "+ fieldLegajo.getSelectedItem() +" ya esta calificado en el " + fieldTipoNota.getSelectedItem() + " del Curso " + fieldCurso.getText(),
                            "Error", JOptionPane.ERROR_MESSAGE);
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

                try {
                    parcialTableModel.setContenido(servParcial.listarNotasCursoDelAlumno((Integer)fieldLegajo.getSelectedItem(), curso.getId()));
                    parcialTableModel.fireTableDataChanged();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso "+ fieldCurso.getText() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
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

        buttonEliminarNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int filaSeleccionada = tablaNotasAlumno.getSelectedRow();
                Nota n = parcialTableModel.getContenido().get(filaSeleccionada);
                try{
                    servParcial.eliminarNota(n.getAlumno().getLegajo(),n.getCurso().getId(),n.getTipoNota());
                    parcialTableModel.getContenido().remove(filaSeleccionada);
                    parcialTableModel.fireTableDataChanged();

                } catch (ServiceNotaNoExisteException serviceNotaNoExisteException) {
                    serviceNotaNoExisteException.printStackTrace();
                } catch (ServiceNotaParcialesDependenDeFinalException serviceNotaParcialesDependenDeFinalException) {
                    serviceNotaParcialesDependenDeFinalException.printStackTrace();
                }

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
        add (buttonEliminarNota);

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
        buttonEliminarNota.setBounds(130, 435,130,25);


    }


}