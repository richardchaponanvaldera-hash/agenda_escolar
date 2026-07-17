package modelo;

public class informacionCurso {
    private int idCurso;
    private String nombreCurso;
    private String silabo;
    private String periodoAcademico;
    private String turno;
    private String seccion;
    private String profesorNombre;

    public informacionCurso() {
    }

    public informacionCurso(int idCurso, String nombreCurso, String periodoAcademico, String profesorNombre, String seccion, String silabo, String turno) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.periodoAcademico = periodoAcademico;
        this.profesorNombre = profesorNombre;
        this.seccion = seccion;
        this.silabo = silabo;
        this.turno = turno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getPeriodoAcademico() {
        return periodoAcademico;
    }

    public void setPeriodoAcademico(String periodoAcademico) {
        this.periodoAcademico = periodoAcademico;
    }

    public String getProfesorNombre() {
        return profesorNombre;
    }

    public void setProfesorNombre(String profesorNombre) {
        this.profesorNombre = profesorNombre;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getSilabo() {
        return silabo;
    }

    public void setSilabo(String silabo) {
        this.silabo = silabo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
