package modelo;

public class estudiante {
    private int idCalificacion;
    private int idEstudiante;
    private String nombre;
    private String apellido;
    private double calificacion;
    private String correo;
    private String dni;

    public estudiante() {
    }

    public estudiante(String apellido, double calificacion, String correo, String dni, int idCalificacion, int idEstudiante, String nombre) {
        this.apellido = apellido;
        this.calificacion = calificacion;
        this.correo = correo;
        this.dni = dni;
        this.idCalificacion = idCalificacion;
        this.idEstudiante = idEstudiante;
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public int getIdCalificacion() {
        return idCalificacion;
    }

    public void setIdCalificacion(int idCalificacion) {
        this.idCalificacion = idCalificacion;
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
}
