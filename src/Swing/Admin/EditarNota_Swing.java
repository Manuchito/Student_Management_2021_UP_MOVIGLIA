package Swing.Admin;

import Entidades.Curso;
import Entidades.Nota;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Exceptions.ServiceNotaParcialesDependenDeFinalException;
import Main.PanelManager;
import Services.CursoServicio;
import Services.NotaServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public class EditarNota_Swing extends JPanel {
    private JLabel textLegajo;
    private JLabel textIdCurso;
    private JLabel textTipoNota;
    private JLabel textNota;
    private JTextField fieldLegajo;
    private JTextField fieldIdCurso;
    private JComboBox fieldNumeroNota;
    private JButton buttonEditar;
    private JButton buttonCancelar;
    private JComboBox fieldTipoNota;
    private JToggleButton toggleBuscarNotas;


    CursoServicio servCurso = new CursoServicio();

    private PanelManager panelManager;

    public EditarNota_Swing(PanelManager m) {
        super();
        this.panelManager = m;
    }

    public void armarEditarNota() {
        //construct preComponents
        String[] fieldNumeroNotasItems = {"1","2","3","4","5","6","7","8","9","10"};

        //construct components
        textLegajo = new JLabel ("Legajo");
        textIdCurso = new JLabel ("Id Curso");
        textTipoNota = new JLabel ("Tipo Nota");
        textNota = new JLabel ("Numero Nota");
        fieldLegajo = new JTextField (5);
        fieldIdCurso = new JTextField (5);
        fieldNumeroNota = new JComboBox (fieldNumeroNotasItems );
        buttonEditar = new JButton ("Editar");
        buttonCancelar = new JButton ("Cancelar");
        fieldTipoNota = new JComboBox ();
        toggleBuscarNotas = new JToggleButton ("Buscar Notas", false);

        NotaServicio servNota = new NotaServicio();
        //adjust size and set layout
        setLayout(null);

        fieldTipoNota.setEnabled(false);
        fieldNumeroNota.setEnabled(false);

        toggleBuscarNotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleBuscarNotas.isSelected()){
                    try {
                        Curso c = servCurso.muestraCurso(Integer.parseInt(fieldIdCurso.getText()));
                        List<Nota> notas= servNota.listarNotasCursoDelAlumno(Integer.parseInt(fieldLegajo.getText()),c.getId());
                        if(notas.size() == 0){
                            JOptionPane.showMessageDialog(null, "El Alumno " + fieldLegajo.getText() + " no tiene notas en el Curso "+ c.getId(),
                                    "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                        }
                        else {
                            fieldTipoNota.removeAllItems();
                            for (Nota n : notas) {
                                fieldTipoNota.addItem(n.getTipoNota());
                            }
                            fieldLegajo.setEnabled(false);
                            fieldIdCurso.setEnabled(false);
                            fieldTipoNota.setEnabled(true);
                            fieldNumeroNota.setEnabled(true);
                        }

                    } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                        serviceCursoNoExisteException.printStackTrace();
                    } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                        serviceLegajoNoExsiteException.printStackTrace();
                    }
                }
                else{
                    fieldIdCurso.setEnabled(true);
                    fieldLegajo.setEnabled(true);
                    fieldNumeroNota.setEnabled(false);
                    fieldTipoNota.setEnabled(false);
                    fieldNumeroNota.setSelectedIndex(0);
                    fieldTipoNota.removeAllItems();
                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    servNota.editarNota(Integer.parseInt(fieldLegajo.getText()),Integer.parseInt(fieldIdCurso.getText()), (String)fieldTipoNota.getSelectedItem(), fieldNumeroNota.getSelectedIndex()+1);
                } catch (ServiceNotaParcialesDependenDeFinalException serviceNotaParcialesDependenDeFinalException) {
                    serviceNotaParcialesDependenDeFinalException.printStackTrace();
                }
            }
        });

        fieldTipoNota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Nota> notas= servNota.listarNotasCursoDelAlumno(Integer.parseInt(fieldLegajo.getText()),Integer.parseInt(fieldIdCurso.getText()));
                    for(Nota n : notas){
                        if(fieldTipoNota.getSelectedItem() == n.getTipoNota()){
                            fieldNumeroNota.setSelectedIndex(n.getNotaParcial()-1);
                        }
                    }
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }

            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });

        //add components
        add (textLegajo);
        add (textIdCurso);
        add (textTipoNota);
        add (textNota);
        add (fieldLegajo);
        add (fieldIdCurso);
        add (fieldNumeroNota);
        add (buttonEditar);
        add (buttonCancelar);
        add (fieldTipoNota);
        add (toggleBuscarNotas);

        //set component bounds (only needed by Absolute Positioning)
        textLegajo.setBounds (80, 40, 100, 25);
        textIdCurso.setBounds (80, 90, 100, 25);
        textTipoNota.setBounds (80, 140, 100, 25);
        textNota.setBounds (80, 190, 100, 25);
        fieldLegajo.setBounds (215, 40, 100, 25);
        fieldIdCurso.setBounds (215, 90, 100, 25);
        fieldNumeroNota.setBounds (215, 190, 100, 25);
        buttonEditar.setBounds (260, 250, 100, 30);
        buttonCancelar.setBounds (385, 250, 100, 30);
        fieldTipoNota.setBounds (215, 140, 100, 25);
        toggleBuscarNotas.setBounds (380, 40, 115, 25);
    }
}

