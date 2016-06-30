package cl.emprz.chiloeapp.clases;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by elias on 03-06-16.
 */
public class PronosticoTiempo {

    int id;
    String texto;
    int imagen;
    Date fecha;

    ArrayList<PronosticoTiempo> pronosticoSemanal;

    public PronosticoTiempo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ArrayList<PronosticoTiempo> getPronosticoSemanal() {
        return pronosticoSemanal;
    }

    public void setPronosticoSemanal(ArrayList<PronosticoTiempo> pronosticoSemanal) {
        this.pronosticoSemanal = pronosticoSemanal;
    }

}
