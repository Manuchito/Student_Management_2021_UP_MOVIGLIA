package Swing.Admin;

import Exceptions.ServiceClaveDuplicadaException;
import Main.PanelManager;
import Services.AlumnoServicio;

import javax.swing.*;

public class PantallaCrearAlumno extends AbstractPantallaCreacion{

    AlumnoServicio servAlumno = new AlumnoServicio();
    public PantallaCrearAlumno(PanelManager m) {
        super(m);
    }

    @Override
    public void setCamposPanel() {
        this.camposPanel = new CamposCrearAlumno(panelManager);
    }

    @Override
    public void ejecutarAccionOk() {
        CamposCrearAlumno camposCrearAlumno = (CamposCrearAlumno) this.camposPanel;
        try {
            servAlumno.registrar(Integer.parseInt(camposCrearAlumno.getFieldLegajo().getText()), camposCrearAlumno.getFieldNombre().getText(), camposCrearAlumno.getFieldApellido().getText(), Integer.parseInt(String.valueOf(camposCrearAlumno.getFieldLimiteCursos().getSelectedItem())));
            JOptionPane.showMessageDialog(null, "Se ha creado el alumno con legajo: " + camposCrearAlumno.getFieldLegajo().getText(),
                    "Error alumno repetido", JOptionPane.INFORMATION_MESSAGE);
            camposCrearAlumno.clearText();
            
        } catch (ServiceClaveDuplicadaException claveDuplicadaException) {
            JOptionPane.showMessageDialog(null, "El alumno con legajo " + camposCrearAlumno.getFieldLegajo().getText() + " ya existe",
                    "Error alumno repetido", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(null, "El contenido de alguno de los campos es incorrecto",
                    "Error tipo formato", JOptionPane.ERROR_MESSAGE);
            numberFormatException.printStackTrace();
        }
    }

    @Override
    public void ejecutarAccionCanel() {
        panelManager.mostrarPanelAdmin();
    }
}