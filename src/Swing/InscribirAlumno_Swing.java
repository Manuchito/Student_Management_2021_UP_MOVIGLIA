package Swing;

import DAO.Alumno.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursoServicio;
import Swing.Tablas.AlumnoTableModel;
import Swing.Tablas.CursoTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InscribirAlumno_Swing extends JPanel {


    private PanelManager panelManager;

    private CursoTableModel cursoTableModel;
    private JTable tabla;
    private JScrollPane scrollTable;

    private AlumnoTableModel alumnoTableModel;
    private AlumnoServicio servAlumno;
    private CursoServicio servCurso;

    public InscribirAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarInscribirAlumnoSwing() {

        //componentes del JFrame

        JButton buttonMostrarAlumnos = new JButton ("Mosatrar Alumnos");
        JButton buttonMostrarCursos = new JButton ("Mostrar Cursos");
        JLabel textAlumno = new JLabel ("Legajo Alumno");
        JLabel textCurso = new JLabel ("Codigo Curso");
        JTextField fieldAlumno = new JTextField (5);
        JTextField fieldCurso = new JTextField (5);
        JButton buttonInscribirAlumno = new JButton ("Inscribir");
        JButton buttonCancelar = new JButton ("Cancelar");
        JLabel textInscribir = new JLabel ("Inscribir Alumno en Curso");
        DefaultTableModel modeloTabla = new DefaultTableModel();
        setLayout(null);

        AlumnoServicio alumnoServicio = new AlumnoServicio();

        alumnoTableModel = new AlumnoTableModel();
        cursoTableModel = new CursoTableModel();
        tabla = new JTable();
        scrollTable = new JScrollPane(tabla);

        tabla.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tabla.getSelectedRow();
                if(tabla.getModel() instanceof AlumnoTableModel){
                    Alumno alumno = alumnoTableModel.getContenido().get(filaSeleccionada);
                    fieldAlumno.setText(String.valueOf(alumno.getLegajo()));
                }
                else if(tabla.getModel() instanceof CursoTableModel){
                    Curso curso = cursoTableModel.getContenido().get(filaSeleccionada);
                    fieldCurso.setText(String.valueOf(curso.getId()));
                }
            }
        });
        //Listeners de los botones
        buttonMostrarAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servAlumno = new AlumnoServicio();
                tabla.setModel(alumnoTableModel);
                System.out.printf(String.valueOf(tabla.getModel()));

                try {
                    alumnoTableModel.setContenido(servAlumno.listarAlumnos());
                    alumnoTableModel.fireTableDataChanged();
                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    serviceLegajoNoExsiteException.printStackTrace();
                }

            }
        });

        buttonMostrarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                servCurso = new CursoServicio();
                tabla.setModel(cursoTableModel);
                try{
                    cursoTableModel.setContenido(servCurso.listarCursos());
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        buttonInscribirAlumno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    alumnoServicio.inscribirAlumnoxCurso(Integer.parseInt(fieldAlumno.getText()), Integer.parseInt(fieldCurso.getText()));
                } catch (ServiceLegajoNoExsiteException legajoNoExsite) {
                    legajoNoExsite.printStackTrace();
                } catch (ServiceCursoNoExisteException serviceCursoNoExiste) {
                    serviceCursoNoExiste.printStackTrace();
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelProfesor();
            }
        });



        //agrego componentes al JFrame (Mismo que hacer frame.add(XXX))
        add (scrollTable);
        add (buttonMostrarAlumnos);
        add (buttonMostrarCursos);
        add (fieldAlumno);
        add (fieldCurso);
        add (textAlumno);
        add (textCurso);
        add (buttonInscribirAlumno);
        add (buttonCancelar);
        add (textInscribir);

        //ubico componentes en JFrame
        scrollTable.setBounds (375, 90, 490, 325);
        buttonMostrarAlumnos.setBounds (435, 35, 150, 30);
        buttonMostrarCursos.setBounds (655, 35, 150, 30);
        textAlumno.setBounds (50, 150, 100, 25);
        textCurso.setBounds (50, 215, 100, 25);
        fieldAlumno.setBounds (185, 150, 120, 25);
        fieldCurso.setBounds (185, 215, 120, 25);
        buttonInscribirAlumno.setBounds (65, 370, 110, 35);
        buttonCancelar.setBounds (225, 370, 110, 35);
        textInscribir.setBounds (100, 50, 155, 25);
    }


}
