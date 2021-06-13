package Entidades;

import java.util.List;

public class Alumno {
    private int legajo;
    private String nombre;
    private String apellido;
    /*
    * Tenia entendido que solamente se solicitaba un CRUD de una entidad sencilla,
    * y no que manejar atributos tales como "aprobado no aprobado"
    *
    * Entiendo el proyecto en esta primera parte como un sistema de listado de alumnos,
    * no de resolver si esta aprobado o no.
    * En el caso de que se solicite eso, podria poner un atributo booleano de
    * "Aprobado, desaprobado", pero me queria resevear este atributo para las otras
    * entidades para el proyecto en su etapa final.
    */
    public Alumno() {

    }

    public Alumno(int legajo, String nombre, String apellido)
    {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;

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




}
