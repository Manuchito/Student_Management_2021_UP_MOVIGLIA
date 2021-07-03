package Swing.Admin;

import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceCursoYaExisteException;
import Main.PanelManager;
import Services.CursoServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;

public class EditarCurso_Swing extends JPanel {
    private JLabel textIdCurso;
    private JLabel textNombre;
    private JLabel textPrecio;
    private JLabel textCupoMaximo;
    private JLabel textCantidadParciales;
    private JTextField fieldIdCurso;
    private JTextField fieldNombre;
    private JTextField fieldCupoMaximo;
    private JSpinner fieldPrecio;
    private JButton buttonEditar;
    private JButton buttonCancelar;
    private JComboBox fieldCantidadParciales;
    private JToggleButton toggleBuscarCurso;
    private PanelManager panelManager;

    public EditarCurso_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarEditarCurso() {
        //construct preComponents
        String[] fieldCantidadParcialesItems = {"1", "2", "3", "4", "5", "6"};

        //construct components
        textIdCurso = new JLabel ("Id Curso");
        textNombre = new JLabel ("Nombre");
        textPrecio = new JLabel ("Precio");
        textCupoMaximo = new JLabel ("Cupo Maximo");
        textCantidadParciales = new JLabel ("Cantidad Parciales");
        fieldIdCurso = new JTextField (5);
        fieldNombre = new JTextField (5);
        fieldCupoMaximo = new JTextField (5);
        fieldPrecio = new JSpinner(new SpinnerNumberModel());
        JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)fieldPrecio.getEditor();
        DefaultFormatter formatter = (DefaultFormatter) jsEditor.getTextField().getFormatter();
        formatter.setAllowsInvalid(false);
        buttonEditar = new JButton ("Editar");
        buttonCancelar = new JButton ("Cancelar");
        fieldCantidadParciales = new JComboBox (fieldCantidadParcialesItems);
        toggleBuscarCurso = new JToggleButton ("Buscar Curso", false);

        //adjust size and set layout
        setPreferredSize (new Dimension (526, 347));
        setLayout (null);

        fieldNombre.setEnabled(false);
        fieldCupoMaximo.setEnabled(false);
        fieldCantidadParciales.setEnabled(false);
        fieldPrecio.setEnabled(false);
        buttonEditar.setEnabled(false);

        CursoServicio servCurso = new CursoServicio();

        toggleBuscarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleBuscarCurso.isSelected()){
                    try {
                        Curso c = servCurso.muestraCurso(Integer.parseInt(fieldIdCurso.getText()));
                        fieldNombre.setText(c.getNombre());
                        fieldPrecio.setValue(c.getPrecio());
                        fieldCupoMaximo.setText(String.valueOf(c.getCupo()));
                        fieldCantidadParciales.setSelectedIndex(c.getCantidad_parciales()-1);
                        fieldNombre.setEnabled(true);
                        fieldCupoMaximo.setEnabled(true);
                        fieldCantidadParciales.setEnabled(true);
                        fieldPrecio.setEnabled(true);
                        buttonEditar.setEnabled(true);
                    } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                        JOptionPane.showMessageDialog(null, "El curso con id " + fieldIdCurso.getText() + " no existe",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        toggleBuscarCurso.setSelected(false);
                    }catch(NumberFormatException numberFormatException){
                        JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        toggleBuscarCurso.setSelected(false);
                    }

                }
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere editar el curso " + fieldIdCurso.getText() + " ?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION){
                    try {
                        servCurso.editarCurso(Integer.parseInt(fieldIdCurso.getText()),fieldNombre.getText(),Integer.parseInt(fieldCupoMaximo.getText()),(Integer)fieldPrecio.getValue(),(fieldCantidadParciales.getSelectedIndex()+1));
                    } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                        JOptionPane.showMessageDialog(null, "El Curso "+ fieldIdCurso.getText() + " no existe",
                                "Error tipo missing", JOptionPane.ERROR_MESSAGE);
                    }catch (NumberFormatException numberFormatException){
                        JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto", "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldIdCurso.setText("");
                fieldNombre.setText("");
                fieldCantidadParciales.setSelectedIndex(0);
                fieldPrecio.setValue(0);
                fieldCupoMaximo.setText("");
                fieldNombre.setEnabled(false);
                fieldCupoMaximo.setEnabled(false);
                fieldCantidadParciales.setEnabled(false);
                fieldPrecio.setEnabled(false);
                buttonEditar.setEnabled(false);
                toggleBuscarCurso.setSelected(false);

                panelManager.mostrarPanelAdmin();
            }
        });

        //add components
        add (textIdCurso);
        add (textNombre);
        add (textPrecio);
        add (textCupoMaximo);
        add (textCantidadParciales);
        add (fieldIdCurso);
        add (fieldNombre);
        add (fieldCupoMaximo);
        add (fieldPrecio);
        add (buttonEditar);
        add (buttonCancelar);
        add (fieldCantidadParciales);
        add (toggleBuscarCurso);

        //set component bounds (only needed by Absolute Positioning)
        textIdCurso.setBounds (80, 40, 100, 25);
        textNombre.setBounds (80, 90, 100, 25);
        textPrecio.setBounds (80, 140, 100, 25);
        textCupoMaximo.setBounds (80, 190, 100, 25);
        textCantidadParciales.setBounds (80, 240, 110, 25);
        fieldIdCurso.setBounds (215, 40, 100, 25);
        fieldNombre.setBounds (215, 90, 120, 25);
        fieldCupoMaximo.setBounds (215, 190, 100, 25);
        fieldPrecio.setBounds (215, 140, 100, 25);
        buttonEditar.setBounds (260, 300, 100, 30);
        buttonCancelar.setBounds (385, 300, 100, 30);
        fieldCantidadParciales.setBounds (215, 240, 100, 25);
        toggleBuscarCurso.setBounds (375, 40, 120, 25);
    }


}

