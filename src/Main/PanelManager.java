package Main;

import Swing.Admin.*;
import Swing.InicioSesion_Swing;
import Swing.Profesor.*;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private JFrame frame;
    private CrearAlumno_Swing crearAlumno;
    private EditarAlumno_Swing editarAlumno;
    private InscribirAlumno_Swing inscribirAlumno;
    private Profesor_Swing profesor;
    private CalificarAlumno_Swing calificarAlumno;
    private ReporteCurso_Swing reporteCurso;
    private ReporteAlumno_Swing reporteAlumno;
    private Admin_Swing admin;
    private CrearCurso_Swing crearCurso;
    private EditarCurso_Swing editarCurso;
    private CrearNota_Swing crearNota;
    private EditarNota_Swing editarNota;
    private InicioSesion_Swing inicioSesion;
    private LoginAdmin_Swing loginAdmin;
    private CrearCursada_Swing crearCursada;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public PanelManager(){

    }

    public void armarManager(){
        frame = new JFrame();
        frame.setSize(950, 600);
        centerFrame(frame);

        crearAlumno = new CrearAlumno_Swing(this);
        crearAlumno.armarCrearAlumno();

        editarAlumno = new EditarAlumno_Swing(this);
        editarAlumno.armarEditarAlumno();

        inscribirAlumno = new InscribirAlumno_Swing(this);
        inscribirAlumno.armarInscribirAlumnoSwing();

        profesor = new Profesor_Swing(this);
        profesor.armarProfesorSwing();

        calificarAlumno = new CalificarAlumno_Swing(this);
        calificarAlumno.armarCalificarCurso();

        reporteCurso = new ReporteCurso_Swing(this);
        reporteCurso.armarReporteCurso();

        reporteAlumno = new ReporteAlumno_Swing(this);
        reporteAlumno.armarReporteAlumno();

        admin = new Admin_Swing(this);
        admin.armarAdminSwing();

        crearCurso = new CrearCurso_Swing(this);
        crearCurso.armarCrearCurso();

        editarCurso = new EditarCurso_Swing(this);
        editarCurso.armarEditarCurso();

        crearNota = new CrearNota_Swing(this);
        crearNota.armarCrearNota();

        editarNota = new EditarNota_Swing(this);
        editarNota.armarEditarNota();

        inicioSesion = new InicioSesion_Swing(this);
        inicioSesion.armarInicioSesion();

        loginAdmin = new LoginAdmin_Swing(this);
        loginAdmin.armarLoginAdmin();

        crearCursada = new CrearCursada_Swing(this);
        crearCursada.armarCrearCursada();
    }

    public void mostrarPanelCrearCursada(){
        frame.getContentPane().removeAll();
        frame.setSize(475, 270);
        centerFrame(frame);
        frame.setTitle("AAAAAAAAAAAAAAA");
        frame.getContentPane().add(crearCursada);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelLoginAdmin(){
        frame.getContentPane().removeAll();
        frame.setSize(410,300);
        centerFrame(frame);
        frame.setTitle("AAAAAAAAAAAAAAA");
        frame.getContentPane().add(loginAdmin);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelInicioSesion(){
        frame.getContentPane().removeAll();
        frame.setSize(875, 550);
        centerFrame(frame);
        frame.getContentPane().add(inicioSesion);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelEditarNota(){
        frame.getContentPane().removeAll();
        frame.setSize(550, 350);
        centerFrame(frame);
        frame.getContentPane().add(editarNota);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCrearNota(){
        frame.getContentPane().removeAll();
        frame.setSize(550, 350);
        centerFrame(frame);
        frame.getContentPane().add(crearNota);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelEditarCurso(){
        frame.getContentPane().removeAll();
        frame.setSize(550, 400);
        centerFrame(frame);
        frame.getContentPane().add(editarCurso);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCrearCurso(){
        frame.getContentPane().removeAll();
        frame.setSize(550, 400);
        centerFrame(frame);
        frame.getContentPane().add(crearCurso);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelAdmin(){
        frame.getContentPane().removeAll();
        frame.setSize(1500, 870);
        centerFrame(frame);
        frame.getContentPane().add(admin);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelReporteAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(1200, 550);
        centerFrame(frame);
        frame.getContentPane().add(reporteAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelReporteCurso(){
        frame.getContentPane().removeAll();
        frame.setSize(980, 600);
        centerFrame(frame);
        frame.getContentPane().add(reporteCurso);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCalificarAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(700, 520);
        centerFrame(frame);
        frame.getContentPane().add(calificarAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelProfesor(){
        frame.getContentPane().removeAll();
        frame.setSize(800, 600);
        centerFrame(frame);
        frame.getContentPane().add(profesor);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }


    public void mostrarPanelCreacionAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(600, 370);
        centerFrame(frame);
        frame.getContentPane().add(crearAlumno);
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

    public void mostrarPanelInscribirAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(950, 475);
        centerFrame(frame);
        frame.getContentPane().add(inscribirAlumno);
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
