package cl.emprz.chiloeapp.clases;


import java.util.ArrayList;

/**
 * Created by elias on 07-02-16.
 */
public class Pyme{

    private int id;
    private String _id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String comuna;
    private String email;
    private String descipcion_corta;
    private String descipcion_larga;
    private int calificacion;
    private String url_imagen;
    private String[] imagenes;

    public Pyme() {
    }

    public Pyme(int id, String nombre, String url_imagen) {
        this.nombre = nombre;
        this.id = id;
        this.url_imagen = url_imagen;
    }

    public Pyme(int id, String nombre, String descipcion_corta, int calificacion, String url_imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descipcion_corta = descipcion_corta;
        this.calificacion = calificacion;
        this.url_imagen = url_imagen;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescipcion_corta() {
        return descipcion_corta;
    }

    public void setDescipcion_corta(String descipcion_corta) {
        this.descipcion_corta = descipcion_corta;
    }

    public String getDescipcion_larga() {
        return descipcion_larga;
    }

    public void setDescipcion_larga(String descipcion_larga) {
        this.descipcion_larga = descipcion_larga;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }
}