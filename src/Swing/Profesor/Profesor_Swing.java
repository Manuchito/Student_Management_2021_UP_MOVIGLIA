package Swing.Profesor;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursadaServicio;
import Services.CursoServicio;
import Swing.Admin.Admin_Swing;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursoTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Profesor_Swing extends JPanel {
    AlumnoServicio servAlumno = new AlumnoServicio();
    CursoServicio servCurso = new CursoServicio();
    CursadaServicio servCursada = new CursadaServicio();

    private CursoTableModel cursoTableModel;
    private JTable tablaCursosAlumno;
    private JScrollPane scrollPaneCursosAlumno;

    private AlumnoTableModel alumnoTableModel;
    private JTable tablaAlumnosCurso;
    private JScrollPane scrollPaneAlumnosCurso;

    private PanelManager panelManager;

    public Profesor_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarProfesorSwing() {

        JLabel textAlumno = new JLabel("Legajo Alumno");
        JButton buttonBuscarAlumno = new JButton("Buscar");
        JLabel textCursosAlumno = new JLabel("Cursos del Alumno");
        JLabel textAlumnosCurso = new JLabel("Alumnos del Curso");
        JLabel textCurso = new JLabel("Codigo Curso");
        JTextField fieldAlumno = new JTextField();
        JTextField fieldCurso = new JTextField();
        JButton buttonBuscarCurso = new JButton("Buscar");
        JButton buttonReporteAlumno = new JButton("Reporte Alumno");
        JButton buttonInscribir = new JButton("Inscribir Alumno");
        JButton buttonVolver = new JButton("Volver");
        JButton buttonCalificar = new JButton("Calificar Alumno");
        JButton buttonReporte = new JButton("Reporte Curso");

        alumnoTableModel = new AlumnoTableModel();
        tablaAlumnosCurso = new JTable(alumnoTableModel);
        scrollPaneAlumnosCurso = new JScrollPane(tablaAlumnosCurso);


        buttonBuscarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    servCurso = new CursoServicio();
                    alumnoTableModel.setContenido(servCursada.listarAlumnosDelCurso(Integer.parseInt(fieldCurso.getText())));
                    alumnoTableModel.fireTableDataChanged();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    JOptionPane.showMessageDialog(null, "El curso con legajo " + fieldCurso.getText() + " no existe",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }
            }
        });

        cursoTableModel = new CursoTableModel();
        tablaCursosAlumno = new JTable(cursoTableModel);
        scrollPaneCursosAlumno = new JScrollPane(tablaCursosAlumno);


        buttonBuscarAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    servAlumno = new AlumnoServicio();
                    cursoTableModel.setContenido(servCursada.listarCursosDelAlumno(Integer.parseInt(fieldAlumno.getText())));
                    cursoTableModel.fireTableDataChanged();
                } catch (NumberFormatException numberFormatException) {
                    numberFormatException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    JOptionPane.showMessageDialog(null, "El alumno con legajo " + fieldAlumno.getText() + " no existe",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                }
            }
        });


        buttonInscribir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelInscribirAlumno(new Profesor_Swing(panelManager));
            }
        });

        buttonReporteAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelReporteAlumno();
            }
        });

        buttonCalificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelCalificarAlumno(new Profesor_Swing(panelManager));
            }
        });

        buttonReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelReporteCurso();
            }
        });

        tablaCursosAlumno.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaCursosAlumno.getSelectedRow();
                Curso curso = cursoTableModel.getContenido().get(filaSeleccionada);
                try {
                    alumnoTableModel.setContenido(servCursada.listarAlumnosDelCurso(curso.getId()));
                    fieldCurso.setText(String.valueOf(curso.getId()));
                    alumnoTableModel.fireTableDataChanged();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }
            }
        });

        tablaAlumnosCurso.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tablaAlumnosCurso.getSelectedRow();
                Alumno alumno = alumnoTableModel.getContenido().get(filaSeleccionada);
                try {
                    cursoTableModel.setContenido(servCursada.listarCursosDelAlumno(alumno.getLegajo()));
                    fieldAlumno.setText(String.valueOf(alumno.getLegajo()));
                    cursoTableModel.fireTableDataChanged();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                }
            }
        });

        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int exit = JOptionPane.showConfirmDialog(null, "Esta seguro que quiere volver al menu principal?" , null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (exit == JOptionPane.YES_OPTION){
                    panelManager.mostrarPanelInicioSesion();
                }
            }
        });

        setLayout(null);

        add(scrollPaneCursosAlumno);
        add(scrollPaneAlumnosCurso);

        add(textAlumno);
        add(buttonBuscarAlumno);
        add(textCursosAlumno);
        add(textAlumnosCurso);
        add(textCurso);
        add(fieldAlumno);
        add(fieldCurso);
        add(buttonBuscarCurso);
        add(buttonReporteAlumno);
        add(buttonInscribir);
        add(buttonVolver);
        add(buttonCalificar);
        add(buttonReporte);


        scrollPaneCursosAlumno.setBounds(385, 90, 330, 115);
        scrollPaneAlumnosCurso.setBounds(385, 305, 330, 220);

        textAlumno.setBounds(385, 50, 100, 25);
        buttonBuscarAlumno.setBounds(625, 50, 90, 25);
        textCursosAlumno.setBounds(410, 10, 120, 25);
        textAlumnosCurso.setBounds(410, 225, 140, 25);
        textCurso.setBounds(385, 265, 100, 25);
        fieldAlumno.setBounds(475, 50, 135, 25);
        fieldCurso.setBounds(475, 265, 135, 25);
        buttonBuscarCurso.setBounds(625, 265, 90, 25);
        buttonReporteAlumno.setBounds(100, 80, 140, 45);
        buttonInscribir.setBounds(100, 180, 140, 45);
        buttonVolver.setBounds(110, 485, 120, 40);
        buttonCalificar.setBounds(100, 280, 140, 45);
        buttonReporte.setBounds(100, 380, 140, 45);
    }


}