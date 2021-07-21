package Swing.Admin;

import Main.PanelManager;

import javax.swing.*;
import java.awt.*;

public class CamposCrearAlumno extends CamposPanel{

    private JTextField fieldLegajo;
    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JComboBox fieldLimiteCursos;

    public CamposCrearAlumno(PanelManager m) {
        super(m);
    }


    public void armarFormulario() {
        setLayout(null);

        JLabel textLegajo = new JLabel("Legajo Alumno");
        JLabel textNombre = new JLabel("Nombres Alumno");
        JLabel textApellido = new JLabel("Apellido Alumno");
        JLabel textLimiteCursos = new JLabel("Limite Cursos Alumno");

        fieldLegajo = new JTextField("");
        fieldNombre = new JTextField("");
        fieldApellido = new JTextField("");
        String[] limiteCursosAlumno = {"1","2","3","4","5","6"};
        fieldLimiteCursos = new JComboBox(limiteCursosAlumno);


        add(fieldLegajo);
        add(textLegajo);
        add(textNombre);
        add(textApellido);
        add(fieldNombre);
        add(fieldApellido);
        add(fieldLimiteCursos);
        add(textLimiteCursos);

        fieldLegajo.setBounds(220, 40, 115, 25);
        fieldNombre.setBounds(220, 100, 155, 25);
        fieldApellido.setBounds(220, 160, 155, 25);
        fieldLimiteCursos.setBounds(220,220,155,25);
        textLegajo.setBounds(55, 40, 100, 25);
        textNombre.setBounds(55, 100, 100, 25);
        textApellido.setBounds(55, 160, 100, 25);
        textLimiteCursos.setBounds(55,220,200,25);

    }

    public void clearText(){
        this.fieldLegajo.setText("");
        this.fieldNombre.setText("");
        this.fieldApellido.setText("");
    }

    public JTextField getFieldApellido() {
        return fieldApellido;
    }

    public JTextField getFieldLegajo() {
        return fieldLegajo;
    }

    public JTextField getFieldNombre() {
        return fieldNombre;
    }

    public JComboBox getFieldLimiteCursos(){
        return fieldLimiteCursos;
    }

    public void setFieldApellido(JTextField fieldApellido) {
        this.fieldApellido = fieldApellido;
    }

    public void setFieldLegajo(JTextField fieldLegajo) {
        this.fieldLegajo = fieldLegajo;
    }

    public void setFieldNombre(JTextField fieldNombre) {
        this.fieldNombre = fieldNombre;
    }

    public void setFieldLimiteCursos(JComboBox fieldLimiteCursos) {
        this.fieldLimiteCursos = fieldLimiteCursos;
    }
}
