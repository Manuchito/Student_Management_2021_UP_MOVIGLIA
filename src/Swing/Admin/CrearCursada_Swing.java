package Swing.Admin;

import Entidades.Alumno;
import Entidades.Curso;
import Exceptions.*;
import Main.PanelManager;
import Services.AlumnoServicio;
import Services.CursadaServicio;
import Services.CursoServicio;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CrearCursada_Swing extends JPanel {
    private JComboBox fieldLegajo;
    private JComboBox fieldCurso;
    private JLabel textLegajo;
    private JLabel textCurso;

    AlumnoServicio servAlumno = new AlumnoServicio();
    CursoServicio servCurso = new CursoServicio();
    CursadaServicio servCursada = new CursadaServicio();

    private PanelManager panelManager;
    public CrearCursada_Swing(PanelManager m){
        super();
        this.panelManager = m;
    }

    public void armarCrearCursada(){

        JButton buttonCancelar = new JButton("Cancelar");
        JButton buttonCrear = new JButton("Crear");
        fieldLegajo = new JComboBox();
        fieldCurso = new JComboBox();
        JLabel textLegajo = new JLabel("Legajo Alumno");
        JLabel textCurso = new JLabel("Id Curso");

        fieldLegajo.setMaximumRowCount(6);
        fieldCurso.setMaximumRowCount(6);

        for(Alumno a : servAlumno.listarAlumnos()){
            fieldLegajo.addItem(a.getLegajo());
        }

        for(Curso c : servCurso.listarCursos()){
            fieldCurso.addItem(c.getId());
        }

        setLayout(null);

        

        buttonCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    servCursada.inscribirAlumnoxCurso((Integer) fieldLegajo.getSelectedItem(), (Integer) fieldCurso.getSelectedItem());

                } catch (ServiceLegajoNoExsiteException legajoNoExsite) {
                    JOptionPane.showMessageDialog(null, "El alumno "+ fieldLegajo.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCursoNoExisteException serviceCursoNoExiste) {
                    JOptionPane.showMessageDialog(null, "El curso "+ fieldCurso.getSelectedItem() +" no existe.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceInscripcionRepetidaException serviceInscripcionRepetidaException) {
                    JOptionPane.showMessageDialog(null, "Ya existe la inscripcion del Alumno " + fieldLegajo.getSelectedItem() + " al Curso " + fieldCurso.getSelectedItem(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCupoCompletoException serviceCupoCompletoException) {
                    JOptionPane.showMessageDialog(null, "Inscribir al alumno " + fieldLegajo.getSelectedItem() + " al curso " + fieldCurso.getSelectedItem() +" excede el cupo maximo.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceCapacidadMaximaCursosAlumnoException serviceCapacidadMaximaCursosAlumnoException) {
                    JOptionPane.showMessageDialog(null, "Inscribir al alumno " + fieldLegajo.getSelectedItem() + " a un nuevo curso excede su limite de los mismos.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException numberFormatException){
                    JOptionPane.showMessageDialog(null, "Alguno de los campos incluye letras o caracteres especiales",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceAlmunoYaCursoMateria serviceAlmunoYaCursoMateria) {
                    JOptionPane.showMessageDialog(null, "El Alumno " + fieldLegajo.getSelectedItem() + " ya aprobo con Final el Curso " + fieldCurso.getSelectedItem(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ServiceFinalPendiente serviceFinalPendiente) {
                    JOptionPane.showMessageDialog(null, "El Alumno " + fieldLegajo.getSelectedItem() + " ya tiene un final pendiente del Curso " + fieldCurso.getSelectedItem(),
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        buttonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin();
            }
        });

        add(buttonCrear);
        add(buttonCancelar);
        add(fieldCurso);
        add(fieldLegajo);
        add(textCurso);
        add(textLegajo);

        buttonCancelar.setBounds(325, 180, 100, 30);
        buttonCrear.setBounds(200, 180, 100, 30);

        fieldLegajo.setBounds(175, 40, 115, 25);
        fieldCurso.setBounds(175, 100, 155, 25);

        textLegajo.setBounds(55, 40, 100, 25);
        textCurso.setBounds(55, 100, 100, 25);

    }
}
