package Entidades;

public class Parcial {
    private Alumno alumno;
    private Curso curso;
    private String tipoNota;
    private int notaParcial;

    public Parcial(){}

    public Parcial(Alumno alumno, Curso curso, String tipoNota, int notaParcial){
        this.alumno = alumno;
        this.curso = curso;
        this.tipoNota = tipoNota;
        this.notaParcial = notaParcial;
    }

    public int getNotaParcial() {
        return notaParcial;
    }

    public void setNotaParcial(int notaParcial) {
        this.notaParcial = notaParcial;
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
