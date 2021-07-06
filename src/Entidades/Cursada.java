package Entidades;

public class Cursada {
    private int id_cursada;
    private Alumno alumno;
    private Curso curso;

    public Cursada(int id, Alumno alumno, Curso curso){
        this.id_cursada = id;
        this.alumno = alumno;
        this.curso = curso;
    }

    public Cursada(int cursada){

    }

    public int getCursada() {
        return id_cursada;
    }

    public void setCursada(int id_cursada) {
        this.id_cursada = id_cursada;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
