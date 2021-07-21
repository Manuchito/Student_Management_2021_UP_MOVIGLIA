package Swing.Admin;

import Exceptions.ServiceClaveDuplicadaException;
import Main.PanelManager;
import Services.ProfesorServicio;

import javax.swing.*;


public class PantallaCrearProfesor extends AbstractPantallaCreacion {

    ProfesorServicio servProfesor = new ProfesorServicio();

    public PantallaCrearProfesor(PanelManager m) {
        super(m);
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposCrearProfesor(panelManager);
    }

    @Override
    public void ejecutarAccionOk() {
        CamposCrearProfesor camposCrearProfesor = (CamposCrearProfesor) this.camposPanel;
        try {
            servProfesor.registrar(camposCrearProfesor.getFieldNombre().getText(), camposCrearProfesor.getFieldApellido().getText(), camposCrearProfesor.getFieldUsuario().getText(), camposCrearProfesor.getFieldPw().getText());
            JOptionPane.showMessageDialog(null, "Se ha creado el profesor con usuario: " + camposCrearProfesor.getFieldNombre().getText(),
                    "Creacion Profesor", JOptionPane.INFORMATION_MESSAGE);
            camposCrearProfesor.clearText();
        } catch (ServiceClaveDuplicadaException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void ejecutarAccionCanel() {
        panelManager.mostrarPanelAdmin();
    }
}
