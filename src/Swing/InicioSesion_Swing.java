package Swing;

import Main.PanelManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class InicioSesion_Swing extends JPanel {

    private JButton buttonAdministrador;
    private JButton buttonProfesor;
    private JButton buttonSalir;
    private JLabel textDescripcion;


    private PanelManager panelManager;

    public InicioSesion_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarInicioSesion() {
        //construct components
        buttonAdministrador = new JButton ("Administrador");
        buttonProfesor = new JButton ("Profesor");
        buttonSalir = new JButton ("Salir");
        textDescripcion = new JLabel ("Seleccione el usuario a ser utilizado");
        textDescripcion.setFont(new Font("Serif", Font.BOLD, 28));


        //adjust size and set layout
        setLayout (null);

        buttonProfesor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelProfesor();
            }
        });

        buttonAdministrador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelLoginAdmin();
            }
        });

        //add components

        add (buttonAdministrador);
        add (buttonProfesor);
        add (buttonSalir);
        add (textDescripcion);


        //set component bounds (only needed by Absolute Positioning)
        buttonAdministrador.setBounds (185, 225, 135, 55);
        buttonProfesor.setBounds (555, 225, 135, 55);
        buttonSalir.setBounds (390, 400, 100, 25);
        textDescripcion.setBounds (230, 85, 450, 45);
    }

}
