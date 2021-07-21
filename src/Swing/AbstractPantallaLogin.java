package Swing;

import Main.PanelManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPantallaLogin extends JPanel {
    private JLabel textUsuario;
    private JLabel textPassword;
    private JTextField fieldUsuario;
    private JButton buttonLogin;
    private JButton buttonVolver;
    private JPasswordField fieldPassword;

    protected PanelManager panelManager;

    public AbstractPantallaLogin(PanelManager panelManager){
        this.panelManager = panelManager;
        armarPantallaLogin();
    }

    public void armarPantallaLogin(){
        textUsuario = new JLabel ("Usuario:");
        textPassword = new JLabel ("Contraseña:");
        fieldUsuario = new JTextField (5);
        buttonLogin = new JButton ("Login");
        buttonVolver = new JButton ("Volver");
        fieldPassword = new JPasswordField (5);

        setLayout (null);

        buttonLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ejecutarAccionOk(fieldUsuario, fieldPassword);
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAccionCanel();
            }
        });

        add(textUsuario);
        add(textPassword);
        add(fieldUsuario);
        add(buttonLogin);
        add(buttonVolver);
        add(fieldPassword);

        textUsuario.setBounds (100, 70, 100, 25);
        textPassword.setBounds (100, 115, 100, 25);
        fieldUsuario.setBounds (200, 70, 100, 25);
        buttonLogin.setBounds (75, 195, 105, 25);
        buttonVolver.setBounds (225, 195, 100, 25);
        fieldPassword.setBounds (200, 115, 100, 25);
    }

    public void clear(){
        fieldUsuario.setText("");
        fieldPassword.setText("");
    }

    public boolean CheckPwAdmin(char[] pwIngresada){
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

    public abstract void ejecutarAccionOk(JTextField user, JPasswordField pw);


    public abstract void ejecutarAccionCanel();

}
