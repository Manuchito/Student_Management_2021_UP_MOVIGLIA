package Main;

import Swing.Admin.*;
import Swing.InicioSesion_Swing;
import Swing.PantallaLoginAdmin;
import Swing.PantallaLoginProfesor;
import Swing.Profesor.*;

import javax.swing.*;
import java.awt.*;

public class PanelManager {
    private JFrame frame;
    private PantallaCrearAlumno pantallaCrearAlumno;
    private InscribirAlumno_Swing inscribirAlumno;
    private Profesor_Swing profesor;
    private CalificarAlumno_Swing calificarAlumno;
    private ReporteCurso_Swing reporteCurso;
    private ReporteAlumno_Swing reporteAlumno;
    private Admin_Swing admin;
    private CrearCurso_Swing crearCurso;
    private InicioSesion_Swing inicioSesion;
    private PantallaLoginAdmin loginAdmin;
    private PantallaLoginProfesor loginProfesor;
    private PantallaCrearProfesor pantallaCrearProfesor;


    private JPanel prevoiusPanel;

    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public PanelManager(){

    }

    public void armarManager(){
        frame = new JFrame();
        centerFrame(frame);

        pantallaCrearAlumno = new PantallaCrearAlumno(this);
        pantallaCrearAlumno.armarPantallaCreacion();

        inscribirAlumno = new InscribirAlumno_Swing(this);
        inscribirAlumno.armarInscribirAlumnoSwing();

        profesor = new Profesor_Swing(this);
        profesor.armarProfesorSwing();

        calificarAlumno = new CalificarAlumno_Swing(this);

        reporteCurso = new ReporteCurso_Swing(this);
        reporteCurso.armarReporteCurso();

        reporteAlumno = new ReporteAlumno_Swing(this);
        reporteAlumno.armarReporteAlumno();

        admin = new Admin_Swing(this);
        admin.armarAdminSwing();

        crearCurso = new CrearCurso_Swing(this);
        crearCurso.armarCrearCurso();

        inicioSesion = new InicioSesion_Swing(this);
        inicioSesion.armarInicioSesion();

        loginAdmin = new PantallaLoginAdmin(this);

        loginProfesor = new PantallaLoginProfesor(this);

        pantallaCrearProfesor = new PantallaCrearProfesor(this);
    }

    public void mostrarPanelCrearProfesor(){
        frame.getContentPane().removeAll();
        frame.setSize(600, 370);
        centerFrame(frame);
        frame.setTitle("Crear Profesor");
        frame.getContentPane().add(pantallaCrearProfesor);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelLoginProfesor(){
        frame.getContentPane().removeAll();
        frame.setSize(410,300);
        centerFrame(frame);
        frame.setTitle("Login Profesor");
        frame.getContentPane().add(loginProfesor);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelLoginAdmin(){
        frame.getContentPane().removeAll();
        frame.setSize(410,300);
        centerFrame(frame);
        frame.setTitle("Login Admin");
        frame.getContentPane().add(loginAdmin);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelInicioSesion(){
        frame.getContentPane().removeAll();
        frame.setSize(875, 550);
        centerFrame(frame);
        frame.setTitle("Inicio");
        frame.getContentPane().add(inicioSesion);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }


    public void mostrarPanelCrearCurso(){
        frame.getContentPane().removeAll();
        frame.setSize(550, 400);
        centerFrame(frame);
        frame.setTitle("Crear Curso");
        frame.getContentPane().add(crearCurso);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelAdmin(){
        frame.getContentPane().removeAll();
        frame.setSize(1850, 870);
        centerFrame(frame);
        frame.setTitle("Administrador");
        frame.getContentPane().add(admin);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelReporteAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(1200, 550);
        centerFrame(frame);
        frame.setTitle("Reporte Alumno");
        frame.getContentPane().add(reporteAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelReporteCurso(){
        frame.getContentPane().removeAll();
        frame.setSize(980, 600);
        centerFrame(frame);
        frame.setTitle("Reporte Curso");
        frame.getContentPane().add(reporteCurso);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCalificarAlumno(JPanel jPanel){
        calificarAlumno.setPreviousPanel(jPanel);
        frame.getContentPane().removeAll();
        frame.setSize(700, 520);
        centerFrame(frame);
        frame.setTitle("Calificar Alumno");
        frame.getContentPane().add(calificarAlumno);
        frame.getContentPane().revalidate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelProfesor(){
        frame.getContentPane().removeAll();
        frame.setSize(800, 600);
        centerFrame(frame);
        frame.setTitle("Inicio ProfesorDAOH2Impl");
        frame.getContentPane().add(profesor);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void mostrarPanelCreacionAlumno(){
        frame.getContentPane().removeAll();
        frame.setSize(600, 370);
        centerFrame(frame);
        frame.setTitle("Crear Alumno");
        frame.getContentPane().add(pantallaCrearAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }


    public void mostrarPanelInscribirAlumno(JPanel jPanel){
        inscribirAlumno.setPreviousPanel(jPanel);
        frame.getContentPane().removeAll();
        frame.setSize(950, 475);
        centerFrame(frame);
        frame.setTitle("Inscribir Alumno");
        frame.getContentPane().add(inscribirAlumno);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
    }

    public void showFrame() {
        frame.setVisible(true);
    }

    //Obtengo de dim el tamaño de la pantalla y seteo el frame en el medio de la pantalla
    public void centerFrame(JFrame f){
        f.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
    }


    public JPanel getPrevoiusPanel() {
        return prevoiusPanel;
    }

    public void setPrevoiusPanel(JPanel prevoiusPanel) {
        this.prevoiusPanel = prevoiusPanel;
    }
}
