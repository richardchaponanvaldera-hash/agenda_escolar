package modelo;

public class asistencia {
    private int idAsistencia;
    private int idEstudiante;
    private String nombreCurso;
    private String fecha;
    private int estado;
    private int idCurso;
    private int dia;

    public asistencia() {
    }

    public asistencia(int estado, String fecha, int idAsistencia, int idCurso, int idEstudiante, String nombreCurso, int dia) {
        this.estado = estado;
        this.fecha = fecha;
        this.idAsistencia = idAsistencia;
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.nombreCurso = nombreCurso;
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }
}
