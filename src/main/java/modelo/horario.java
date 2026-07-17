package modelo;

public class horario {
    private int idhorario;
    private int dia;
    private String curso;
    private String horaInicio;
    private String horaFin;

    public horario() {
    }

    public horario(String curso, int dia, String horaFin, String horaInicio, int idhorario) {
        this.curso = curso;
        this.dia = dia;
        this.horaFin = horaFin;
        this.horaInicio = horaInicio;
        this.idhorario = idhorario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
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

    public int getIdhorario() {
        return idhorario;
    }

    public void setIdhorario(int idhorario) {
        this.idhorario = idhorario;
    }
}
