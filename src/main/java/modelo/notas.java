package modelo;

public class notas {
    private int idCalificacion;
    private int idEstudiante;
    private int idCurso;
    private String nombreCurso;
    private Double calificacion1;
    private Double calificacion2;
    private Double calificacion3;
    private Double calificacion4;
    private Double promedio;
    private String fecha;
    private String nombre;
    private String apellido;

    public notas(){}

    public notas(String apellido, Double calificacion1, Double calificacion2, Double calificacion3, Double calificacion4, String fecha, int idCalificacion, int idCurso, int idEstudiante, String nombre, String nombreCurso, Double promedio) {
        this.apellido = apellido;
        this.calificacion1 = calificacion1;
        this.calificacion2 = calificacion2;
        this.calificacion3 = calificacion3;
        this.calificacion4 = calificacion4;
        this.fecha = fecha;
        this.idCalificacion = idCalificacion;
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
        this.nombreCurso = nombreCurso;
        this.promedio = promedio;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }
}
