package Swing;

import DAO.AlumnoDAOH2Impl;
import DAO.Curso.CursoDAOH2Impl;
import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InscribirAlumno_Swing extends JPanel {


    private PanelManager panelManager;

    public void reiniciarTablaAlumnos(JTable tabla){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new Object[]{"LEGAJO", "NOMBRE", "APELLIDO","APROBACION"});
        tabla.setModel(modeloTabla);
        AlumnoDAOH2Impl alumnoDAO = new AlumnoDAOH2Impl();
        for(Alumno a : alumnoDAO.listaTodosLosAlumnos()){
            modeloTabla.addRow(new Object[]{
                    a.getLegajo(),
                    a.getNombre(),
                    a.getApellido(),
                    a.getAprobacion(),
            });
        }
    }


    public void reiniciarTablaCursos(JTable tabla ){
        DefaultTableModel modeloTabla = new DefaultTableModel();
        modeloTabla.setRowCount(0);
        modeloTabla.setColumnIdentifiers(new Object[]{"ID", "NOMBRE", "PRECIO", "CUPO_MAXIMO"});
        tabla.setModel(modeloTabla);
        CursoDAOH2Impl cursoDAO = new CursoDAOH2Impl();
        for(Curso c : cursoDAO.listaTodosLosCursos()){
            modeloTabla.addRow(new Object[]{
                    c.getId(),
                    c.getNombre(),
                    c.getPrecio(),
                    c.getCupo(),
            });
        }
    }

    public InscribirAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarInscribirAlumnoSwing() {

        //componentes del JFrame
        JTable tabla = new JTable();
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
        JScrollPane scrollPane = new JScrollPane();
        setLayout(null);

        AlumnoServicio alumnoServicio = new AlumnoServicio();

        //Listeners de los botones
        buttonMostrarAlumnos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarTablaAlumnos(tabla);
            }
        });

        buttonMostrarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarTablaCursos(tabla);
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

        //Codigo creacion y display de tabla
        scrollPane.setViewportView(tabla);
        tabla.setEnabled(false);
        tabla.setDefaultEditor(Object.class, null);

        //agrego componentes al JFrame (Mismo que hacer frame.add(XXX))
        add (scrollPane);
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
        scrollPane.setBounds (375, 90, 490, 325);
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
