package Swing;

import Entidades.Profesor;
import Main.PanelManager;
import Services.ProfesorServicio;

import javax.swing.*;
import java.util.List;

public class PantallaLoginProfesor extends AbstractPantallaLogin{

    ProfesorServicio servProfesor = new ProfesorServicio();

    public PantallaLoginProfesor(PanelManager panelManager) {
        super(panelManager);
    }

    @Override
    public void ejecutarAccionOk(JTextField user, JPasswordField pw) {
        List<Profesor> profesorList = servProfesor.listar();
        for(Profesor p : profesorList){
            if(p.getUsuario().equals(user.getText()) && p.getPw().equals(String.valueOf(pw.getPassword()))){
                panelManager.mostrarPanelProfesor();
            }
        }

    }

    @Override
    public void ejecutarAccionCanel() {
        panelManager.mostrarPanelInicioSesion();
    }

}
