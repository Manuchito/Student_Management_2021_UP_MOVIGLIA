package Entidades;

public class Nota {
    private Alumno alumno;
    private Curso curso;
    private String tipoNota;
    private int numeroNota;

    public Nota(){}

    public Nota(Alumno alumno, Curso curso, String tipoNota, int numeroNota){
        this.alumno = alumno;
        this.curso = curso;
        this.tipoNota = tipoNota;
        this.numeroNota = numeroNota;
    }

    public int getNotaParcial() {
        return numeroNota;
    }

    public void setNotaParcial(int notaParcial) {
        this.numeroNota = notaParcial;
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

    public String getTipoNota() {
        return tipoNota;
    }

    public void setTipoNota(String tipoNota) {
        this.tipoNota = tipoNota;
    }
}
