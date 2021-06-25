package Entidades;

import java.util.List;

public class Curso {
    private int id;
    private String nombre;
    private int cupo;
    private int precio;
    private List<Alumno> alumnos;

    public Curso(){
    }

    public Curso(int id, String nombre, int cupo, int precio){
        this.id = id;
        this.nombre = nombre;
        this.cupo = cupo;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
