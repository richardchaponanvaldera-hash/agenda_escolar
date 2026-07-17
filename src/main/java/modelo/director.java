package modelo;

public class director {
    private int idCurso;
    private int idEstudiante;
    private int idProfessor;
    private int idTurno;
    private int idSeccion;
    private  int idUsuario;
    private int idApoderado;
    private int idAgenda;
    private int idRol;
    private String idGrado;
    private String fecha;
    private String descripcion;
    private String silabo;
    private String nombreCurso;
    private String periodoAcademico;
    private String estudianteNombre;
    private String estudianteApellido;
    private String apoderadoNombre;
    private String apoderadoApellido;
    private String professorNombre;
    private String professorApellido;
    private String dni;
    private String correo;
    private String contrasena;
    private String turno;
    private String seccion;
    private String grado;
    private int dia;
    private String horaInicio;
    private String horaFin;
    private int mes;
    private int year;

    public director() {
    }

    public director(String apoderadoApellido, String apoderadoNombre, String contrasena, String correo, String descripcion, int dia, String dni, String estudianteApellido, String estudianteNombre, String fecha, String grado, String horaFin, String horaInicio, int idAgenda, int idApoderado, int idCurso, int idEstudiante, String idGrado, int idProfessor, int idRol, int idSeccion, int idTurno, int idUsuario, int mes, String nombreCurso, String periodoAcademico, String professorApellido, String professorNombre, String seccion, String silabo, String turno, int year) {
        this.apoderadoApellido = apoderadoApellido;
        this.apoderadoNombre = apoderadoNombre;
        this.contrasena = contrasena;
        this.correo = correo;
        this.descripcion = descripcion;
        this.dia = dia;
        this.dni = dni;
        this.estudianteApellido = estudianteApellido;
        this.estudianteNombre = estudianteNombre;
        this.fecha = fecha;
        this.grado = grado;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.idAgenda = idAgenda;
        this.idApoderado = idApoderado;
        this.idCurso = idCurso;
        this.idEstudiante = idEstudiante;
        this.idGrado = idGrado;
        this.idProfessor = idProfessor;
        this.idRol = idRol;
        this.idSeccion = idSeccion;
        this.idTurno = idTurno;
        this.idUsuario = idUsuario;
        this.mes = mes;
        this.nombreCurso = nombreCurso;
        this.periodoAcademico = periodoAcademico;
        this.professorApellido = professorApellido;
        this.professorNombre = professorNombre;
        this.seccion = seccion;
        this.silabo = silabo;
        this.turno = turno;
        this.year = year;
    }

    public String getApoderadoApellido() {
        return apoderadoApellido;
    }

    public void setApoderadoApellido(String apoderadoApellido) {
        this.apoderadoApellido = apoderadoApellido;
    }

    public String getApoderadoNombre() {
        return apoderadoNombre;
    }

    public void setApoderadoNombre(String apoderadoNombre) {
        this.apoderadoNombre = apoderadoNombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEstudianteApellido() {
        return estudianteApellido;
    }

    public void setEstudianteApellido(String estudianteApellido) {
        this.estudianteApellido = estudianteApellido;
    }

    public String getEstudianteNombre() {
        return estudianteNombre;
    }

    public void setEstudianteNombre(String estudianteNombre) {
        this.estudianteNombre = estudianteNombre;
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

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
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

    public String getIdGrado() {
        return idGrado;
    }

    public void setIdGrado(String idGrado) {
        this.idGrado = idGrado;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
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

    public String getProfessorApellido() {
        return professorApellido;
    }

    public void setProfessorApellido(String professorApellido) {
        this.professorApellido = professorApellido;
    }

    public String getProfessorNombre() {
        return professorNombre;
    }

    public void setProfessorNombre(String professorNombre) {
        this.professorNombre = professorNombre;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
