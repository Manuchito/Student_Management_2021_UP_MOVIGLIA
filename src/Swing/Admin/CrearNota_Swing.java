package Swing.Admin;

import Entidades.Curso;
import Exceptions.ServiceClaveDuplicadaException;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceInsificientesParcialesAprobadosException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.CursoServicio;
import Services.NotaServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class CrearNota_Swing extends JPanel {
    private JLabel textLegajo;
    private JLabel textIdCurso;
    private JLabel textTipoNota;
    private JLabel textNota;
    private JTextField fieldLegajo;
    private JTextField fieldIdCurso;
    private JComboBox fieldNumeroNota;
    private JButton buttonCrear;
    private JButton buttonCancelar;
    private JComboBox fieldTipoNota;


    CursoServicio servCurso = new CursoServicio();

    private PanelManager panelManager;

    public CrearNota_Swing(PanelManager m) {
        super();
        this.panelManager = m;
    }

    public void armarCrearNota() {
        //construct preComponents
        String[] fieldTipoNotaItems = {"FINAL", "PARCIAL 1"};
        String[] fieldNumeroNotasItems = {"1","2","3","4","5","6","7","8","9","10"};

        //construct components
        textLegajo = new JLabel ("Legajo");
        textIdCurso = new JLabel ("Id Curso");
        textTipoNota = new JLabel ("Tipo Nota");
        textNota = new JLabel ("Numero Nota");
        fieldLegajo = new JTextField (5);
        fieldIdCurso = new JTextField (5);
        fieldNumeroNota = new JComboBox (fieldNumeroNotasItems );
        buttonCrear = new JButton ("Crear");
        buttonCancelar = new JButton ("Cancelar");
        fieldTipoNota = new JComboBox (fieldTipoNotaItems);

        NotaServicio servNota = new NotaServicio();
        //adjust size and set layout
        setPreferredSize(new Dimension(526, 347));
        setLayout(null);

        //add components
        add (textLegajo);
        add (textIdCurso);
        add (textTipoNota);
        add (textNota);
        add (fieldLegajo);
        add (fieldIdCurso);
        add (fieldNumeroNota);
        add (buttonCrear);
        add (buttonCancelar);
        add (fieldTipoNota);


        fieldTipoNota.getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Curso c = servCurso.muestraCurso(Integer.parseInt(fieldIdCurso.getText()));
                    fieldTipoNota.removeAllItems();
                    fieldTipoNota.addItem("FINAL");
                    for (int i = 1; i <= c.getCantidad_parciales(); i++) {
                        fieldTipoNota.addItem("Parcial " + i);
                    }
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El Curso "+ fieldIdCurso.getText() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "Antes de ingresar el tipo nota, ingrese un curso primero",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //Puede poner notas sin necesidad de estar cursando en el curso
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    servNota.calificarAlumno(Integer.parseInt((String)fieldLegajo.getText()),Integer.parseInt((String)fieldIdCurso.getText()),(String)fieldTipoNota.getSelectedItem(),Integer.parseInt((String)fieldNumeroNota.getSelectedItem()));
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    JOptionPane.showMessageDialog(null, "El Alumno "+ fieldLegajo.getText() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceClaveDuplicadaException serviceClaveDuplicadaException) {
                    JOptionPane.showMessageDialog(null, "El Alumno "+ fieldLegajo.getText() +" ya esta calificado en el " + fieldTipoNota.getSelectedItem() + " del Curso " + fieldIdCurso.getText(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El Curso "+ fieldIdCurso.getText() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceInsificientesParcialesAprobadosException serviceInsificientesParcialesAprobadosException) {
                    JOptionPane.showMessageDialog(null, "El Alumno"+ fieldLegajo.getText() +" no tiene los fucientes parciales para rendir FINAL.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch(NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                            "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });

        //set component bounds (only needed by Absolute Positioning)
        textLegajo.setBounds (80, 40, 100, 25);
        textIdCurso.setBounds (80, 90, 100, 25);
        textTipoNota.setBounds (80, 140, 100, 25);
        textNota.setBounds (80, 190, 100, 25);
        fieldLegajo.setBounds (215, 40, 100, 25);
        fieldIdCurso.setBounds (215, 90, 100, 25);
        fieldNumeroNota.setBounds (215, 190, 100, 25);
        buttonCrear.setBounds (260, 250, 100, 30);
        buttonCancelar.setBounds (385, 250, 100, 30);
        fieldTipoNota.setBounds (215, 140, 100, 25);
    }
}

