package Swing.Profesor;

import Entidades.Alumno;
import Exceptions.ServiceCursoNoExisteException;
import Exceptions.ServiceLegajoNoExsiteException;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursadaServicio;
import Services.CursoServicio;
import Services.NotaServicio;
import Swing.Tablas.CursoTableModel;
import Swing.Tablas.NotaTableModel;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.*;

public class ReporteAlumno_Swing extends JPanel {
    private JLabel textBuscarLegajo;
    private JComboBox fieldBuscarLegajo;
    private JButton buttonBuscar;

    private JLabel textDescNotasAlumno;
    private JLabel textDescCursosAprobados;
    private JLabel textDescCursada;
    private JLabel textLegajo;
    private JLabel textNombre;
    private JLabel textApellido;
    private JLabel textCantidadCursos;
    private JLabel textCantidadAprobados;

    private JTextField fieldLegajo;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JTextField fieldCantidadCursada;
    private JTextField fieldCantidadAprobados;

    private JButton buttonVolver;

    private JScrollPane scrollpaneNotasAlumno;
    private JScrollPane scrollpaneCursosAprobados;
    private JScrollPane scrollpaneCursada;

    private JTable tablaNotasAluno;
    private JTable tablaCursosAprobados;
    private JTable tablaCursada;

    CursoServicio servCurso = new CursoServicio();
    NotaServicio servNota = new NotaServicio();
    AlumnoServicio servAlumno = new AlumnoServicio();
    CursadaServicio servCursada = new CursadaServicio();

    private PanelManager panelManager;

    public ReporteAlumno_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void  armarReporteAlumno() {
        textBuscarLegajo = new JLabel ("Legajo Alumno");
        fieldBuscarLegajo = new JComboBox();

        buttonBuscar = new JButton ("Buscar");
        textDescNotasAlumno = new JLabel ("NOTAS DEL ALUMNO");
        textDescCursosAprobados = new JLabel ("CURSOS APROBADOS");
        textDescCursada = new JLabel ("CURSOS DEL ALUMNO");
        textLegajo = new JLabel ("Legajo");
        textNombre = new JLabel ("Nombre");
        textApellido = new JLabel ("Apellido");
        textCantidadCursos = new JLabel ("Cantidad Cursando");
        textCantidadAprobados = new JLabel ("Cantidad Aprobados");

        fieldLegajo = new JTextField ();
        fieldNombre = new JTextField ();
        fieldApellido = new JTextField ();
        fieldCantidadCursada = new JTextField ();
        fieldCantidadAprobados = new JTextField ();

        buttonVolver = new JButton ("Volver");

        for(Alumno a : servAlumno.listarAlumnos()){
            fieldBuscarLegajo.addItem(a.getLegajo());
        }

        fieldLegajo.setEnabled (false);
        fieldNombre.setEnabled (false);
        fieldApellido.setEnabled (false);
        fieldCantidadCursada.setEnabled (false);
        fieldCantidadAprobados.setEnabled (false);


        setLayout(null);

        NotaTableModel tablaNotasTableModel= new NotaTableModel();
        CursoTableModel tablaCursadaTableModel = new CursoTableModel();
        CursoTableModel tablaCursosAprobadosTableModel = new CursoTableModel();

        tablaCursada = new JTable(tablaCursadaTableModel);
        tablaCursosAprobados = new JTable(tablaCursosAprobadosTableModel);
        tablaNotasAluno = new JTable(tablaNotasTableModel);

        scrollpaneCursada = new JScrollPane(tablaCursada);
        scrollpaneNotasAlumno = new JScrollPane(tablaNotasAluno);
        scrollpaneCursosAprobados = new JScrollPane(tablaCursosAprobados);



        buttonBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    tablaCursadaTableModel.setContenido(servCursada.listarCursosDelAlumno((Integer)fieldBuscarLegajo.getSelectedItem()));
                    tablaCursadaTableModel.fireTableDataChanged();

                    tablaCursosAprobadosTableModel.setContenido(servAlumno.listarCursosAprobados((Integer)fieldBuscarLegajo.getSelectedItem()));
                    tablaCursosAprobadosTableModel.fireTableDataChanged();

                    tablaNotasTableModel.setContenido(servNota.listarNotasAlumno((Integer)fieldBuscarLegajo.getSelectedItem()));
                    tablaNotasTableModel.fireTableDataChanged();

                    Alumno a = servAlumno.mostrar((Integer)fieldBuscarLegajo.getSelectedItem());
                    fieldLegajo.setText(String.valueOf(a.getLegajo()));
                    fieldNombre.setText(a.getNombre());
                    fieldApellido.setText(a.getApellido());
                    fieldCantidadCursada.setText(String.valueOf(servCursada.listarCursosDelAlumno(a.getLegajo()).size()));
                    fieldCantidadAprobados.setText(String.valueOf(servAlumno.listarCursosAprobados(a.getLegajo()).size()));

                } catch (ServiceLegajoNoExsiteException serviceLegajoNoExsiteException) {
                    JOptionPane.showMessageDialog(null, "El alumno "+ fieldBuscarLegajo.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCursoNoExisteException serviceCursoNoExisteException) {
                    serviceCursoNoExisteException.printStackTrace();
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "El legajo no puede contener nu",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        buttonVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelProfesor();
            }
        });


        add(textBuscarLegajo);
        add(fieldBuscarLegajo);
        add(buttonBuscar);
        add(scrollpaneNotasAlumno);
        add(textDescNotasAlumno);
        add(scrollpaneCursosAprobados);
        add(textDescCursosAprobados);
        add(scrollpaneCursada);
        add(textDescCursada);
        add(textLegajo);
        add(textNombre);
        add(textApellido);
        add(textCantidadCursos);
        add(textCantidadAprobados);

        add(fieldLegajo);
        add(fieldNombre);
        add(fieldApellido);
        add(fieldCantidadCursada);
        add(fieldCantidadAprobados);

        add(buttonVolver);

        textBuscarLegajo.setBounds(435, 55, 85, 25);
        fieldBuscarLegajo.setBounds(545, 55, 100, 25);
        buttonBuscar.setBounds(670, 55, 125, 25);
        scrollpaneNotasAlumno.setBounds(40, 200, 250, 210);
        textDescNotasAlumno.setBounds(105, 150, 125, 25);
        scrollpaneCursosAprobados.setBounds(330, 200, 250, 210);
        textDescCursosAprobados.setBounds(390, 150, 130, 25);
        scrollpaneCursada.setBounds(620, 200, 250, 210);
        textDescCursada.setBounds(680, 150, 130, 25);
        textLegajo.setBounds(895, 185, 100, 25);
        textNombre.setBounds(895, 225, 100, 25);
        textApellido.setBounds(895, 265, 100, 25);
        textCantidadCursos.setBounds(895, 345, 110, 25);
        textCantidadAprobados.setBounds(895, 385, 115, 25);

        fieldLegajo.setBounds(1030, 185, 75, 25);
        fieldNombre.setBounds(1030, 225, 105, 25);
        fieldApellido.setBounds(1030, 265, 105, 25);
        fieldCantidadCursada.setBounds(1030, 345, 75, 25);
        fieldCantidadAprobados.setBounds(1030, 385, 75, 25);

        buttonVolver.setBounds(950, 455, 100, 25);
    }
}