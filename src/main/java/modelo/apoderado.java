package modelo;

public class apoderado {
    private int idApoderado;
    private int idHijos;
    private int idEstudiante;
    private String nombreEstudiante;
    private String apellidoEstudiante;
    private String grado;
    private int idCurso;
    private String nombreCurso;
    private String peridoAcademico;
    private String diaClase;
    private String fecha;
    private int estado;
    private Double calificacion1;
    private Double calificacion2;
    private Double calificacion3;
    private Double calificacion4;
    private Double promedio;
    private int idHorario;
    private String horaInicio;
    private String horaFin;
    private String descripcion;




    public apoderado() {
    }

    public apoderado(String apellidoEstudiante, Double calificacion1, Double calificacion2, Double calificacion3, Double calificacion4, String descripcion, String diaClase, int estado, String fecha, String grado, String horaFin, String horaInicio, int idApoderado, int idCurso, int idEstudiante, int idHijos, int idHorario, String nombreCurso, String nombreEstudiante, String peridoAcademico, Double promedio) {
        this.apellidoEstudiante = apellidoEstudiante;
        this.calificacion1 = calificacion1;
        this.calificacion2 = calificacion2;
        this.calificacion3 = calificacion3;
        this.calificacion4 = calificacion4;
        this.descripcion = descripcion;
        this.diaClase = diaClase;
        this.estado = estado;
        this.fecha = fecha;
        this.grado = grado;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.idApoderado = idApoderado;
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.idHijos = idHijos;
        this.idHorario = idHorario;
        this.nombreCurso = nombreCurso;
        this.nombreEstudiante = nombreEstudiante;
        this.peridoAcademico = peridoAcademico;
        this.promedio = promedio;
    }

    public String getApellidoEstudiante() {
        return apellidoEstudiante;
    }

    public void setApellidoEstudiante(String apellidoEstudiante) {
        this.apellidoEstudiante = apellidoEstudiante;
    }

    public Double getCalificacion1() {
        return calificacion1;
    }

    public void setCalificacion1(Double calificacion1) {
        this.calificacion1 = calificacion1;
    }

    public Double getCalificacion2() {
        return calificacion2;
    }

    public void setCalificacion2(Double calificacion2) {
        this.calificacion2 = calificacion2;
    }

    public Double getCalificacion3() {
        return calificacion3;
    }

    public void setCalificacion3(Double calificacion3) {
        this.calificacion3 = calificacion3;
    }

    public Double getCalificacion4() {
        return calificacion4;
    }

    public void setCalificacion4(Double calificacion4) {
        this.calificacion4 = calificacion4;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiaClase() {
        return diaClase;
    }

    public void setDiaClase(String diaClase) {
        this.diaClase = diaClase;
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

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getIdApoderado() {
        return idApoderado;
    }

    public void setIdApoderado(int idApoderado) {
        this.idApoderado = idApoderado;
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

    public int getIdHijos() {
        return idHijos;
    }

    public void setIdHijos(int idHijos) {
        this.idHijos = idHijos;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getPeridoAcademico() {
        return peridoAcademico;
    }

    public void setPeridoAcademico(String peridoAcademico) {
        this.peridoAcademico = peridoAcademico;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
