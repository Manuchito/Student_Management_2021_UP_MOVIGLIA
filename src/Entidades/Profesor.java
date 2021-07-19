package Entidades;

public class Profesor {
    private String nombre;
    private String apellido;
    private String usuario;
    private String pw;

    public Profesor(){

    }

    public Profesor(String usuario, String pw){
        this.usuario = usuario;
        this.pw = pw;
    }

    public Profesor(String nombre, String apellido, String usuario, String pw){
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.pw = pw;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }
}
