package cl.emprz.chiloeapp.clases;

/**
 * Created by elias on 14-07-16.
 */
public class Imagen {

    int id;
    String _id;
    String nombre;
    String descripcion;
    String url;

    public Imagen(int id, String _id, String nombre, String descripcion, String url) {
        this.id = id;
        this._id = _id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.url = url;
    }

    public Imagen() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
