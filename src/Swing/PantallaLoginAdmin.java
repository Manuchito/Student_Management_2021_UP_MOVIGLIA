package Swing;

import Main.PanelManager;

import javax.swing.*;

public class PantallaLoginAdmin extends AbstractPantallaLogin {

    public PantallaLoginAdmin(PanelManager panelManager){
        super(panelManager);
    }

    @Override
    public void ejecutarAccionOk(JTextField user, JPasswordField pw) {
        if(CheckPwAdmin(pw.getPassword()) && user.getText().equals("admin")){
            panelManager.mostrarPanelAdmin();
        }
        else {
            JOptionPane.showMessageDialog(null, "Error. Usuario y/o Contraseña incorrecto/s",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        clear();
    }

    @Override
    public void ejecutarAccionCanel() {
        panelManager.mostrarPanelInicioSesion();
    }
}
