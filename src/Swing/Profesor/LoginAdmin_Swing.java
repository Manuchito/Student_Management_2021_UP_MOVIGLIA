package Swing.Profesor;

import Main.PanelManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginAdmin_Swing extends JPanel {
    private JLabel textUsuario;
    private JLabel textPassword;
    private JTextField fieldUsuario;
    private JButton buttonLogin;
    private JButton buttonVolver;
    private JPasswordField fieldPassword;

    private PanelManager panelManager;

    public boolean CheckPw(char[] pwIngresada){
        char[] pwCorrecta = new char[] {'a','d','m','i','n'};
        if(pwIngresada.length != pwCorrecta.length){
            return false;
        }
        for(int i = 0; i < pwIngresada.length; i++){
            if(pwIngresada[i] != pwCorrecta[i]){
                return false;
            }
        }
        return true;
    }

    public LoginAdmin_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarLoginAdmin() {
        //construct components
        textUsuario = new JLabel ("Usuario:");
        textPassword = new JLabel ("Contraseña:");
        fieldUsuario = new JTextField (5);
        buttonLogin = new JButton ("Login");
        buttonVolver = new JButton ("Volver");
        fieldPassword = new JPasswordField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (393, 265));
        setLayout (null);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(fieldUsuario.getText());
                System.out.println(fieldPassword.getText());
                if(CheckPw(fieldPassword.getPassword()) && fieldUsuario.getText().equals("admin")){
                    panelManager.mostrarPanelAdmin();
                }
                else {

                    JOptionPane.showMessageDialog(null, "Error. Usuario y/o Contraseña incorrecto/s",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelInicioSesion();
            }
        });

        //add components
        add (textUsuario);
        add (textPassword);
        add (fieldUsuario);
        add (buttonLogin);
        add (buttonVolver);
        add (fieldPassword);

        //set component bounds (only needed by Absolute Positioning)
        textUsuario.setBounds (100, 70, 100, 25);
        textPassword.setBounds (100, 115, 100, 25);
        fieldUsuario.setBounds (200, 70, 100, 25);
        buttonLogin.setBounds (75, 195, 105, 25);
        buttonVolver.setBounds (225, 195, 100, 25);
        fieldPassword.setBounds (200, 115, 100, 25);
    }


}
