package modelo;

import java.util.Date;

public class curso {
    private int idCurso;
    private int idProfessor;
    private int idTurno;
    private int idSeccion;
    private String silabo;
    private String nombreCurso;
    private String preriodoAcademico;

    public curso() {
    }

    public curso(int idCurso, int idProfessor, int idSeccion, int idTurno, String nombreCurso, String preriodoAcademico, String silabo) {
        this.idCurso = idCurso;
        this.idProfessor = idProfessor;
        this.idSeccion = idSeccion;
        this.idTurno = idTurno;
        this.nombreCurso = nombreCurso;
        this.preriodoAcademico = preriodoAcademico;
        this.silabo = silabo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(int idTurno) {
        this.idTurno = idTurno;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPreriodoAcademico() {
        return preriodoAcademico;
    }

    public void setPreriodoAcademico(String preriodoAcademico) {
        this.preriodoAcademico = preriodoAcademico;
    }

    public String getSilabo() {
        return silabo;
    }

    public void setSilabo(String silabo) {
        this.silabo = silabo;
    }
}
