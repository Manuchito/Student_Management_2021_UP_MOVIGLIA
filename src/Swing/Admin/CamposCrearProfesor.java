package Swing.Admin;

import Main.PanelManager;

import javax.swing.*;

public class CamposCrearProfesor extends CamposPanel {

    private JTextField fieldNombre;
    private JTextField fieldApellido;
    private JTextField fieldUsuario;
    private JTextField fieldPw;


    public CamposCrearProfesor(PanelManager m) {
        super(m);
    }

    @Override
    public void armarFormulario() {

        setLayout(null);
        JLabel textNombre = new JLabel("Nombre Profesor");
        JLabel textApellido = new JLabel("Apellido Profesor");
        JLabel textUsuario = new JLabel("Usuario Profesor");
        JLabel textPw = new JLabel("Contraseña Profesor");

        fieldNombre = new JTextField("");
        fieldApellido = new JTextField("");
        fieldUsuario = new JTextField("");
        fieldPw = new JTextField("");

        add(textNombre);
        add(textApellido);
        add(textUsuario);
        add(textPw);
        add(fieldNombre);
        add(fieldApellido);
        add(fieldUsuario);
        add(fieldPw);

        textNombre.setBounds(55, 40, 100, 25);
        textApellido.setBounds(55, 100, 100, 25);
        textUsuario.setBounds(55, 160, 100, 25);
        textPw.setBounds(55,220,200,25);

        fieldNombre.setBounds(220, 40, 115, 25);
        fieldApellido.setBounds(220, 100, 155, 25);
        fieldUsuario.setBounds(220, 160, 155, 25);
        fieldPw.setBounds(220,220,155,25);



    }
    public void clearText(){
        fieldNombre.setText("");
        fieldApellido.setText("");
        fieldUsuario.setText("");
        fieldPw.setText("");
    }

    public JTextField getFieldNombre() {
        return fieldNombre;
    }

    public JTextField getFieldApellido() {
        return fieldApellido;
    }

    public JTextField getFieldUsuario(){
        return fieldUsuario;
    }

    public JTextField getFieldPw() {
        return fieldPw;
    }

    public void setFieldNombre(JTextField fieldNombre) {
        this.fieldNombre = fieldNombre;
    }

    public void setFieldApellido(JTextField fieldApellido) {
        this.fieldApellido = fieldApellido;
    }

    public void setFieldPw(JTextField fieldPw) {
        this.fieldPw = fieldPw;
    }

    public void setFieldUsuario(JTextField fieldUsuario) {
        this.fieldUsuario = fieldUsuario;
    }
}
