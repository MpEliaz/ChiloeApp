package cl.emprz.chiloeapp.clases;

/**
 * Created by elias on 05-02-16.
 */
public class Categoria {

    private int id;
    private String nombre;
    private int id_imagen;

    public Categoria(int id, String nombre, int id_imagen) {
        this.id = id;
        this.nombre = nombre;
        this.id_imagen = id_imagen;
    }

    public Categoria(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_imagen() {
        return id_imagen;
    }

    public void setId_imagen(int id_imagen) {
        this.id_imagen = id_imagen;
    }
}
