package cl.emprz.chiloeapp.clases;

import com.orm.SugarRecord;

/**
 * Created by elias on 17-06-16.
 */
public class Evento extends SugarRecord {

    private Long id;
    private String _id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String comuna;
    private int valor;
    private String imagen;

    public Evento() {
    }

    public Evento(Long id, String titulo, String descripcion, String fecha, String comuna, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.comuna = comuna;
        this.imagen = imagen;
    }

    public Evento(String titulo, String descripcion, String fecha, String comuna, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.comuna = comuna;
        this.imagen = imagen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String get_Id() {
        return _id;
    }

    public void set_Id(String id) {
        this._id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
