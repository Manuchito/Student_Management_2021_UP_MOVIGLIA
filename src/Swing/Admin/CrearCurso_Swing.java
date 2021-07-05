package Swing.Admin;

import Exceptions.ServiceCursoYaExisteException;
import Main.PanelManager;
import Services.CursoServicio;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.DefaultFormatter;

public class CrearCurso_Swing extends JPanel {
    private JLabel textIdCurso;
    private JLabel textNombre;
    private JLabel textPrecio;
    private JLabel textCupoMaximo;
    private JLabel textCantidadParciales;
    private JTextField fieldIdCurso;
    private JTextField fieldNombre;
    private JSpinner fieldCupoMaximo;
    private JSpinner fieldPrecio;
    private JButton buttonCrear;
    private JButton buttonCancelar;
    private JComboBox fieldCantidadParciales;

    private PanelManager panelManager;

    public CrearCurso_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarCrearCurso() {
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
        fieldPrecio = new JSpinner(new SpinnerNumberModel(0,0,50000,100));
        JSpinner.NumberEditor jsEditorPrecio = (JSpinner.NumberEditor)fieldPrecio.getEditor();
        DefaultFormatter formatterPrecio = (DefaultFormatter) jsEditorPrecio.getTextField().getFormatter();
        formatterPrecio.setAllowsInvalid(false);

        fieldCupoMaximo = new JSpinner(new SpinnerNumberModel(1,1,120,1));
        JSpinner.NumberEditor jsEditorCupoMaximo = (JSpinner.NumberEditor)fieldCupoMaximo.getEditor();
        DefaultFormatter formatterCupoMaximo = (DefaultFormatter) jsEditorCupoMaximo.getTextField().getFormatter();
        formatterCupoMaximo.setAllowsInvalid(false);

        buttonCrear = new JButton ("Crear");
        buttonCancelar = new JButton ("Cancelar");
        fieldCantidadParciales = new JComboBox (fieldCantidadParcialesItems);

        //adjust size and set layout
        setPreferredSize (new Dimension (526, 347));
        setLayout (null);

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    CursoServicio cursoServicio = new CursoServicio();
                    cursoServicio.crearCurso(Integer.parseInt(fieldIdCurso.getText()), fieldNombre.getText(),(Integer) fieldPrecio.getValue(),(Integer) fieldCupoMaximo.getValue(),(fieldCantidadParciales.getSelectedIndex() + 1));
                } catch (ServiceCursoYaExisteException serviceCursoYaExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso a crear ya existe (Id y/o Nombre repetido)",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                            "Error", JOptionPane.ERROR_MESSAGE);
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
        add (textIdCurso);
        add (textNombre);
        add (textPrecio);
        add (textCupoMaximo);
        add (textCantidadParciales);
        add (fieldIdCurso);
        add (fieldNombre);
        add (fieldCupoMaximo);
        add (fieldPrecio);
        add (buttonCrear);
        add (buttonCancelar);
        add (fieldCantidadParciales);

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
        buttonCrear.setBounds (260, 300, 100, 30);
        buttonCancelar.setBounds (385, 300, 100, 30);
        fieldCantidadParciales.setBounds (215, 240, 100, 25);
    }


}
