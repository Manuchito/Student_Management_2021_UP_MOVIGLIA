package Swing.Admin;

import Exceptions.ServiceClaveDuplicadaException;
import Services.AlumnoServicio;
import Exceptions.DAOClaveDuplicadaException;
import Main.PanelManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
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

        String[] limiteCursosAlumno = {"1","2","3","4","5","6"};
        JButton buttonCancelar = new JButton("Cancelar");
        JButton buttonCrear = new JButton("Crear");
        fieldLegajo = new JTextField(5);
        JLabel textLegajo = new JLabel("Legajo Alumno");
        JLabel textNombre = new JLabel("Nombres Alumno");
        JLabel textApellido = new JLabel("Apellido Alumno");
        JLabel textLimiteCursos = new JLabel("Limite Cursos Alumno");
        JComboBox fieldLimiteCursos = new JComboBox(limiteCursosAlumno);

        fieldNombre = new JTextField (5);
        fieldApellido = new JTextField (5);
        setLayout(null); // para poder ubicar libremente los componentes en JFrame

        //Listeners de los botones
        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    AlumnoServicio alumnoServicio = new AlumnoServicio();
                    alumnoServicio.registrar(Integer.parseInt(fieldLegajo.getText()) ,fieldNombre.getText(),fieldApellido.getText(), Integer.parseInt(String.valueOf(fieldLimiteCursos.getSelectedItem())));
                    JOptionPane.showMessageDialog(null, "Se a creado un nuevo alumno", "Aviso de creacion", JOptionPane.INFORMATION_MESSAGE);
                    clearText();
                } catch (ServiceClaveDuplicadaException claveDuplicadaException) {
                    JOptionPane.showMessageDialog(null, "El alumno con legajo " + Integer.parseInt(fieldLegajo.getText()) + " ya existe",
                            "Error alumno repetido", JOptionPane.ERROR_MESSAGE);
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
                    panelManager.mostrarPanelAdmin();
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
        add(fieldLimiteCursos);
        add(textLimiteCursos);

        //ubico componentes en JFrame
        buttonCancelar.setBounds(425, 280, 100, 30);
        buttonCrear.setBounds(300, 280, 100, 30);

        fieldLegajo.setBounds(220, 40, 115, 25);
        fieldNombre.setBounds(220, 100, 155, 25);
        fieldApellido.setBounds(220, 160, 155, 25);
        fieldLimiteCursos.setBounds(220,220,155,25);
        textLegajo.setBounds(55, 40, 100, 25);
        textNombre.setBounds(55, 100, 100, 25);
        textApellido.setBounds(55, 160, 100, 25);
        textLimiteCursos.setBounds(55,220,200,25);



    }



}
