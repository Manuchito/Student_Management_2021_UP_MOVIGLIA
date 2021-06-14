package Swing;

import Services.AlumnoServicio;
import Exceptions.ClaveDuplicadaException;
import Main.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CrearAlumno_Swing extends JPanel {
    private PanelManager panelManager;
    private JTextField fieldLegajo;
    private JTextField fieldNombre;
    private JTextField fieldApellido;

    public CrearAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    //setea los textos en "blanco"
    public void clearText(){
        this.fieldLegajo.setText("");
        this.fieldNombre.setText("");
        this.fieldApellido.setText("");
    }

    public void armarCrearAlumno() {

        //componentes del JFrame
        JButton buttonCancelar = new JButton("Cancelar");
        JButton buttonCrear = new JButton("Crear");
        fieldLegajo = new JTextField (5);
        JLabel textLegajo = new JLabel("Legajo Alumno");
        JLabel textNombre = new JLabel("Nombres Alumno");
        JLabel textApellido = new JLabel("Apellido Alumno");
        JLabel textAprobado = new JLabel("Estado Alumno");
        String[] jcomp1Items = {"CURSANDO", "APROBADO", "DESAPROBADO"};
        JComboBox comboAprobado = new JComboBox(new String[]{"CURSANDO", "APROBADO", "DESAPROBADO"});
        fieldNombre = new JTextField (5);
        fieldApellido = new JTextField (5);
        setLayout(null); // para poder ubicar libremente los componentes en JFrame

        //Listeners de los botones
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int legajo = Integer.parseInt(fieldLegajo.getText());
                    AlumnoServicio alumnoServicio = new AlumnoServicio();
                    alumnoServicio.registrar(legajo ,fieldNombre.getText(),fieldApellido.getText(), (String)comboAprobado.getSelectedItem());
                    JOptionPane.showMessageDialog(null, "Se a creado un nuevo alumno", "Aviso de creacion", JOptionPane.INFORMATION_MESSAGE);
                    clearText();
                } catch (ClaveDuplicadaException claveDuplicadaException) {
                    JOptionPane.showMessageDialog(null, "El alumno con legajo " + Integer.parseInt(fieldLegajo.getText()) + " ya existe",
                            "Error alumno repetido", JOptionPane.ERROR_MESSAGE);
                    claveDuplicadaException.printStackTrace();
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                            "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                    numberFormatException.printStackTrace();
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere volver al menu principal?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION)
                {
                    panelManager.mostrarPanelParcial();
                }
            }
        });

        //agrego componentes al JFrame (Mismo que hacer frame.add(XXX))
        add(buttonCancelar);
        add(buttonCrear);
        add(fieldLegajo);
        add(textLegajo);
        add(textNombre);
        add(textApellido);
        add(fieldNombre);
        add(fieldApellido);
        add(comboAprobado);
        add(textAprobado);

        //ubico componentes en JFrame
        buttonCancelar.setBounds(410, 260, 100, 30);
        buttonCrear.setBounds(275, 260, 100, 30);
        fieldLegajo.setBounds(180, 40, 115, 25);
        fieldNombre.setBounds(180, 100, 155, 25);
        fieldApellido.setBounds(180, 160, 155, 25);
        comboAprobado.setBounds(180, 220, 155, 25);
        textLegajo.setBounds(55, 40, 100, 25);
        textNombre.setBounds(55, 100, 100, 25);
        textApellido.setBounds(55, 160, 100, 25);
        textAprobado.setBounds(55, 220, 100, 25);

    }



}
