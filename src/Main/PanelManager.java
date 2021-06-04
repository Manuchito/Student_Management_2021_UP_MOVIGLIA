package Main;

import Swing.CrearAlumno_Swing;
import Swing.EditarAlumno_Swing;
import Swing.EliminarAlumno_Swing;
import Swing.Inicio_Swing;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private JFrame frame;
    private Inicio_Swing parcial;
    private CrearAlumno_Swing crearAlumno;
    private EliminarAlumno_Swing eliminarAlumno;
    private EditarAlumno_Swing editarAlumno;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();


    public PanelManager(){

    }

    public void armarManager(){
        frame = new JFrame();
        frame.setSize(950, 600);
        centerFrame(frame);

        parcial = new Inicio_Swing(this);
        parcial.armarParcialSwing();

        crearAlumno = new CrearAlumno_Swing(this);
        crearAlumno.armarCrearAlumno();

        eliminarAlumno = new EliminarAlumno_Swing(this);
        eliminarAlumno.armarEliminarAlumno();

        editarAlumno = new EditarAlumno_Swing(this);
        editarAlumno.armarEditarAlumno();
    }

    public void mostrarPanelParcial(){
        frame.getContentPane().removeAll();
        frame.setSize(950, 600);
        centerFrame(frame);
        frame.getContentPane().add(parcial);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCreacionAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(575, 340);
        centerFrame(frame);
        frame.getContentPane().add(crearAlumno);

        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelEliminarAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(500, 300);
        centerFrame(frame);
        frame.getContentPane().add(eliminarAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelEditarAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(540, 370);
        centerFrame(frame);
        frame.getContentPane().add(editarAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    //Obtengo de dim el tamaño de la pantalla y seteo el frame f en el medio de la pantalla
    public void centerFrame(JFrame f){
        f.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }



}
