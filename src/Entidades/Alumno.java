package Entidades;

import java.util.List;

public class Alumno {
    private int legajo;
    private String nombre;
    private String apellido;
    private int limiteCursos;

    private List<Curso> cursos;

    public Alumno() {

    }
    
    public Alumno(int legajo, String nombre, String apellido, int limiteCursos)
    {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.limiteCursos = limiteCursos;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public int getLimiteCursos() {
        return limiteCursos;
    }

    public void setLimiteCursos(int limiteCursos) {
        this.limiteCursos = limiteCursos;
    }
}
