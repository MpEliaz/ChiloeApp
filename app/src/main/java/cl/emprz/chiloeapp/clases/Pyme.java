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
    //private ArrayList<Imagen> imagenes;
    private Double latitud;
    private Double longitud;
    private int tipo_pyme;
    private String web;
    private int cantidadComentarios;

    public Pyme() {
    }

    public Pyme(int id, String nombre, String url_imagen) {
        this.nombre = nombre;
        this.id = id;
        this.url_imagen = url_imagen;
    }

    public Pyme(int id, String nombre, String comuna, String descipcion_corta, int calificacion, String url_imagen) {
        this.id = id;
        this.nombre = nombre;
        this.comuna = comuna;
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

/*    public ArrayList<Imagen> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<Imagen> imagenes) {
        this.imagenes = imagenes;
    }*/

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public int getTipo_pyme() {
        return tipo_pyme;
    }

    public void setTipo_pyme(int tipo_pyme) {
        this.tipo_pyme = tipo_pyme;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getCantidadComentarios() {
        return cantidadComentarios;
    }

    public void setCantidadComentarios(int cantidadComentarios) {
        this.cantidadComentarios = cantidadComentarios;
    }
}
