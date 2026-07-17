package modelo;

public class comunicados {
    private int idcomunicado;
    private String contenido;
    private String fecha;

    public comunicados() {
    }

    public comunicados(String contenido, String fecha, int idcomunicado) {
        this.contenido = contenido;
        this.fecha = fecha;
        this.idcomunicado = idcomunicado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdcomunicado() {
        return idcomunicado;
    }

    public void setIdcomunicado(int idcomunicado) {
        this.idcomunicado = idcomunicado;
    }
}
