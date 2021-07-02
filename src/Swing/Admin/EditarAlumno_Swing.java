package Swing.Admin;

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


        String[] limiteCursosAlumno = {"1","2","3","4","5","6"};
        JTextField fieldLegajo = new JTextField (5);
        JLabel legajoText = new JLabel("Legajo Alumno");
        JButton buttonEditar = new JButton ("Editar");
        JButton buttonCancelar = new JButton("Cancelar");
        JLabel textDescripcion = new JLabel("Ingrese el LEGAJO del alumno a modificar");
        JTextField fieldNombre = new JTextField (5);
        JTextField fieldApellido = new JTextField (5);
        JLabel textNombre = new JLabel("Nombre Alumno");
        JLabel textApellido = new JLabel("Apellido Alumno");
        JToggleButton toggleBuscar = new JToggleButton ("Buscar Alumno", false);
        JComboBox fieldLimiteCursos = new JComboBox(limiteCursosAlumno);
        JLabel textLimiteCursos = new JLabel("Limite Cursos Alumno");

        setLayout(null); // para poder ubicar libremente los componentes en JFrame

        //servicios a usar
        Alumno a = new Alumno();
        AlumnoServicio alumnoServicio = new AlumnoServicio();

        //bloqueo estos componentes para que sea imposbile editarlos
        fieldNombre.setEnabled(false);
        fieldApellido.setEnabled(false);
        buttonEditar.setEnabled(false);
        fieldLimiteCursos.setEnabled(false);

        //Listeners de los botones
        toggleBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(toggleBuscar.isSelected()){
                    try{
                        Alumno a = alumnoServicio.mostrar(Integer.parseInt(fieldLegajo.getText()));
                        fieldNombre.setText(a.getNombre());
                        fieldApellido.setText(a.getApellido());
                        fieldLimiteCursos.setSelectedIndex(a.getLimiteCursos()-1);
                        fieldLegajo.setEnabled(false);
                        fieldNombre.setEnabled(true);
                        fieldApellido.setEnabled(true);
                        buttonEditar.setEnabled(true);
                        fieldLimiteCursos.setEnabled(true);

                    }catch(NumberFormatException numberFormatException){
                        JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                                "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                        numberFormatException.printStackTrace();
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setEnabled(false);
                        fieldApellido.setEnabled(false);
                        toggleBuscar.setSelected(true);
                        buttonEditar.setEnabled(false);
                        fieldLimiteCursos.setEnabled(false);

                    }
                    catch (ServiceLegajoNoExsiteException legajoNoExsite) {
                        JOptionPane.showMessageDialog(null, "El alumno con legajo " + fieldLegajo.getText() + " no existe",
                                "Error tipo missing", JOptionPane.ERROR_MESSAGE);
                        legajoNoExsite.printStackTrace();
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setEnabled(false);
                        fieldApellido.setEnabled(false);
                        toggleBuscar.setSelected(true);
                        buttonEditar.setEnabled(false);
                        fieldLimiteCursos.setEnabled(false);

                    }
                }
                else{
                    fieldLegajo.setEnabled(true);
                    fieldNombre.setEnabled(false);
                    fieldApellido.setEnabled(false);
                    fieldLimiteCursos.setEnabled(false);
                }

            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldLegajo.setText("");
                fieldApellido.setText("");
                fieldNombre.setText("");
                panelManager.mostrarPanelAdmin();
            }
        });

        buttonEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere editar el alumno " + fieldLegajo.getText() + " ?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION)
                {
                    try {
                        alumnoServicio.editar(Integer.parseInt(fieldLegajo.getText()), fieldNombre.getText(), fieldApellido.getText(), Integer.parseInt(String.valueOf(fieldLimiteCursos.getSelectedItem())));
                        JOptionPane.showMessageDialog(null, "Usted edito con exito al alumno con LEGAJO: " + fieldLegajo.getText(), "Aviso de edición", JOptionPane.INFORMATION_MESSAGE);
                        fieldLegajo.setText("");
                        fieldLegajo.setEnabled(true);
                        fieldNombre.setText("");
                        fieldNombre.setEnabled(false);
                        fieldApellido.setText("");
                        fieldApellido.setEnabled(false);
                        fieldLimiteCursos.setSelectedIndex(0);
                        fieldLimiteCursos.setEnabled(false);
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
        add(fieldLimiteCursos);
        add(textLimiteCursos);



        //ubico componentes en JFrame
        fieldLegajo.setBounds(160, 70, 115, 25);
        legajoText.setBounds(25, 70, 100, 25);
        buttonEditar.setBounds(165, 290, 105, 30);
        buttonCancelar.setBounds(310, 290, 105, 30);
        textDescripcion.setBounds(140, 0, 240, 50);
        fieldNombre.setBounds(160, 130, 135, 25);
        fieldApellido.setBounds(160, 190, 135, 25);
        textNombre.setBounds(25, 130, 100, 25);
        textApellido.setBounds(25, 190, 100, 25);
        toggleBuscar.setBounds(350, 70, 125, 25);
        textLimiteCursos.setBounds(25,250,100,25);
        fieldLimiteCursos.setBounds(160,250,135,25);


    }


}
