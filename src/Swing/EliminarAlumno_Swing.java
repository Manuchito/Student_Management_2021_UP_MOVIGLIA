package Swing;

import Exceptions.ServiceLegajoNoExsiteException;
import Services.AlumnoServicio;
import Exceptions.IntegerVaciaException;
import Main.PanelManager;

import java.awt.event.*;
import javax.swing.*;

public class EliminarAlumno_Swing extends JPanel {
    private PanelManager panelManager;

    public EliminarAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }


    public void armarEliminarAlumno() {
        //componentes del JFrame
        JTextField fieldLegajo = new JTextField (5);
        JLabel textLegajo = new JLabel("Legajo Alumno");
        JButton buttonEliminar = new JButton("Eliminar");
        JButton buttonCancelar = new JButton("Cancelar");
        JLabel textDescripcion = new JLabel("Ingrese el LEGAJO del alumno a eliminar");
        setLayout(null); // para poder ubicar libremente los componentes en JFrame

        //listeners de los botones
        buttonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere eliminar el alumno con LEGAJO: " + fieldLegajo.getText() + " ?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (exit == JOptionPane.YES_OPTION) {
                        int legajo_alumno = Integer.parseInt(fieldLegajo.getText());
                        AlumnoServicio alumnoServicio = new AlumnoServicio();
                        alumnoServicio.eliminar(legajo_alumno);
                        JOptionPane.showMessageDialog(null, "Usted elimino al alumno con LEGAJO: " + legajo_alumno, "Aviso de eliminación", JOptionPane.INFORMATION_MESSAGE);
                        panelManager.mostrarPanelParcial();
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                            "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                    numberFormatException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException alumnoNoExiste){
                    JOptionPane.showMessageDialog(null, "El alumno a eliminar no existe",
                            "Error tipo base de datos", JOptionPane.ERROR_MESSAGE);
                    alumnoNoExiste.printStackTrace();
                } catch (IntegerVaciaException integerVaciaException) {
                    JOptionPane.showMessageDialog(null, "El contenido del campo legajo esta vacio",
                            "Error tipo formato", JOptionPane.ERROR_MESSAGE);
                    integerVaciaException.printStackTrace();
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelParcial();

            }
        });


        //agrego componentes al JFrame (Mismo que hacer frame.add(XXX))
        add(fieldLegajo);
        add(textLegajo);
        add(buttonEliminar);
        add(buttonCancelar);
        add(textDescripcion);

        //ubico componentes en JFrame
        fieldLegajo.setBounds(260, 115, 115, 25);
        textLegajo.setBounds(90, 115, 100, 25);
        buttonEliminar.setBounds(230, 210, 105, 30);
        buttonCancelar.setBounds(350, 210, 105, 30);
        textDescripcion.setBounds(125, 25, 235, 40);
    }



}