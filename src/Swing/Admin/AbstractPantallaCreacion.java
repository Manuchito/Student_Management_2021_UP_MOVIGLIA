package Swing.Admin;

import Main.PanelManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPantallaCreacion extends JPanel {


    protected PanelManager panelManager;
    protected CamposPanel camposPanel;



    public AbstractPantallaCreacion(PanelManager m){
        this.panelManager = m;
        this.setCamposPanel();
        armarPantallaCreacion();
    }


    public void armarPantallaCreacion() {


        JButton buttonCancelar = new JButton("Cancelar");
        JButton buttonCrear = new JButton("Crear");

        setLayout(null);
        add(camposPanel);

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionOk();
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere volver al menu principal?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION)
                {
                    ejecutarAccionCanel();
                }
            }
        });

        add(buttonCancelar);
        add(buttonCrear);


        buttonCancelar.setBounds(425, 280, 100, 30);
        buttonCrear.setBounds(300, 280, 100, 30);
        camposPanel.setBounds(0,0,600, 370);

    }

    public abstract void setCamposPanel();

    public abstract void ejecutarAccionOk();

    public abstract void ejecutarAccionCanel();


}
