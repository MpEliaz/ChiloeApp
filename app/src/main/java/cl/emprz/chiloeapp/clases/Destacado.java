package cl.emprz.chiloeapp.clases;

/**
 * Created by elias on 25-11-17.
 */

public class Destacado {

    private int id;
    private String titulo;
    private String sub_titulo;
    private String imagen;

    public Destacado(int id, String titulo, String sub_titulo, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.sub_titulo = sub_titulo;
        this.imagen = imagen;
    }

    public Destacado() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSub_titulo() {
        return sub_titulo;
    }

    public void setSub_titulo(String sub_titulo) {
        this.sub_titulo = sub_titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
