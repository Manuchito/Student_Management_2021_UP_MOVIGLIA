package Swing;

import Entidades.Alumno;
import Exceptions.ServiceLegajoNoExsiteException;
import Services.AlumnoServicio;
import Main.PanelManager;

import java.awt.event.*;
import javax.swing.*;

public class EditarAlumno_Swing extends JPanel {
    private PanelManager panelManager;
    private JTextField fieldLegajo;
    private JButton buttonEditar;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JToggleButton toggleBuscar;
    public AlumnoServicio alumnoServicio;


    public EditarAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    //Bloquea los fields de Nombre, Apellido y boton editar, pero deja libre el field de Legajo.
    public void editarOff(){
        fieldLegajo.setEnabled(true);
        fieldNombre.setEnabled(false);
        fieldApellido.setEnabled(false);
        toggleBuscar.setSelected(true);
        buttonEditar.setEnabled(false);
    }

    //Desbloquea los fields de Nombre, Apellido y boton editar, pero bloquea el field de Legajo.
    public void editarOn(){
        fieldLegajo.setEnabled(false);
        fieldNombre.setEnabled(true);
        fieldApellido.setEnabled(true);
        buttonEditar.setEnabled(true);
    }

    public void armarEditarAlumno() {

        //componentes del JFrame
        JTextField fieldLegajo = new JTextField (5);
        JLabel legajoText = new JLabel("Legajo Alumno");
        JButton buttonEditar = new JButton ("Editar");
        JButton buttonCancelar = new JButton("Cancelar");
        JLabel textDescripcion = new JLabel("Ingrese el LEGAJO del alumno a modificar");
        JTextField fieldNombre = new JTextField (5);
        JTextField fieldApellido = new JTextField (5);
        JLabel textAprobado = new JLabel("Estado Alumno");
        String[] jcomp1Items = {"CURSANDO", "APROBADO", "DESAPROBADO"};
        JComboBox comboAprobado = new JComboBox(new String[]{"CURSANDO", "APROBADO", "DESAPROBADO"});
        JLabel textNombre = new JLabel("Nombre Alumno");
        JLabel textApellido = new JLabel("Apellido Alumno");
        JToggleButton toggleBuscar = new JToggleButton ("Buscar Alumno", false);
        setLayout(null); // para poder ubicar libremente los componentes en JFrame

        //servicios a usar
        Alumno a = new Alumno();
        AlumnoServicio alumnoServicio = new AlumnoServicio();

        //bloqueo estos componentes para que sea imposbile editarlos
        fieldNombre.setEnabled(false);
        fieldApellido.setEnabled(false);
        buttonEditar.setEnabled(false);
        comboAprobado.setEnabled(false);

        //Listeners de los botones
        toggleBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleBuscar.isSelected()){
                    try{
                        Alumno a = alumnoServicio.mostrar(Integer.parseInt(fieldLegajo.getText()));
                        fieldNombre.setText(a.getNombre());
                        fieldApellido.setText(a.getApellido());
                        comboAprobado.setSelectedItem(a.getAprobacion());
                        fieldLegajo.setEnabled(false);
                        fieldNombre.setEnabled(true);
                        fieldApellido.setEnabled(true);
                        buttonEditar.setEnabled(true);
                        comboAprobado.setEnabled(true);

                    }catch(NumberFormatException numberFormatException){
                        JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                                "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                        numberFormatException.printStackTrace();
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setEnabled(false);
                        fieldApellido.setEnabled(false);
                        comboAprobado.setEnabled(false);
                        toggleBuscar.setSelected(true);
                        buttonEditar.setEnabled(false);

                    }
                    catch (ServiceLegajoNoExsiteException legajoNoExsite) {
                        JOptionPane.showMessageDialog(null, "El alumno con legajo " + fieldLegajo.getText() + " no existe",
                                "Error tipo missing", JOptionPane.ERROR_MESSAGE);
                        legajoNoExsite.printStackTrace();
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setEnabled(false);
                        fieldApellido.setEnabled(false);
                        comboAprobado.setEnabled(false);
                        toggleBuscar.setSelected(true);
                        buttonEditar.setEnabled(false);

                    }
                }
                else{
                    fieldLegajo.setEnabled(true);
                    fieldNombre.setEnabled(false);
                    fieldApellido.setEnabled(false);
                    comboAprobado.setEnabled(false);
                }

            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldLegajo.setText("");
                fieldApellido.setText("");
                fieldNombre.setText("");
                panelManager.mostrarPanelParcial();
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere editar el alumno " + fieldLegajo.getText() + " ?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION)
                {
                    try {
                        alumnoServicio.editar(Integer.parseInt(fieldLegajo.getText()), fieldNombre.getText(), fieldApellido.getText(), (String)comboAprobado.getSelectedItem());
                        JOptionPane.showMessageDialog(null, "Usted edito con exito al alumno con LEGAJO: " + fieldLegajo.getText(), "Aviso de edición", JOptionPane.INFORMATION_MESSAGE);
                        fieldLegajo.setText("");
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setText("");
                        fieldNombre.setEnabled(false);
                        fieldApellido.setText("");
                        fieldApellido.setEnabled(false);
                        comboAprobado.getEditor().setItem("");
                        comboAprobado.setEnabled(false);
                        toggleBuscar.setSelected(false);
                        buttonEditar.setEnabled(false);
                    } catch (ServiceLegajoNoExsiteException alumnoNoExiste) {
                        JOptionPane.showMessageDialog(null, "El alumno no existe",
                                "Error tipo missing", JOptionPane.ERROR_MESSAGE);

                    } catch (NumberFormatException numberFormatException){
                        JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto", "Error tipo formato", JOptionPane.ERROR_MESSAGE);

                    }
                }
            }
        });


        //agrego componentes al JFrame (Mismo que hacer frame.add(XXX))
        add(fieldLegajo);
        add(legajoText);
        add(buttonEditar);
        add(buttonCancelar);
        add(textDescripcion);
        add(fieldNombre);
        add(fieldApellido);
        add(textNombre);
        add(textApellido);
        add(toggleBuscar);
        add(textAprobado);
        add(comboAprobado);

        //ubico componentes en JFrame
        fieldLegajo.setBounds(160, 70, 115, 25);
        legajoText.setBounds(25, 70, 100, 25);
        buttonEditar.setBounds(165, 290, 105, 30);
        buttonCancelar.setBounds(310, 290, 105, 30);
        textDescripcion.setBounds(140, 0, 240, 50);
        fieldNombre.setBounds(160, 130, 135, 25);
        fieldApellido.setBounds(160, 190, 135, 25);
        comboAprobado.setBounds(160, 250, 135, 25);
        textNombre.setBounds(25, 130, 100, 25);
        textApellido.setBounds(25, 190, 100, 25);
        textAprobado.setBounds(25, 250, 100, 25);
        toggleBuscar.setBounds(350, 70, 125, 25);

    }


}
