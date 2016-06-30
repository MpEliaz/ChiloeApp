package cl.emprz.chiloeapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cl.emprz.chiloeapp.clases.Evento;

/**
 * Created by elias on 17-06-16.
 */
public class SqLiteEventos extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Eventos.db";
    public static final String EVENTOS_TABLE_NAME = "eventos";
    private static final String CREATE_EVENTOS_TABLE = "CREATE TABLE "+EVENTOS_TABLE_NAME+" (id INTEGER PRIMARY KEY AUTOINCREMENT, _id TEXT, titulo TEXT, descripcion TEXT, fecha TEXT, comuna TEXT, valor INTEGER, imagen TEXT)";

    public SqLiteEventos(Context context) {
        super(context, DATABASE_NAME, null, 1);


    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EVENTOS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+EVENTOS_TABLE_NAME);
    }

    public ArrayList<Evento> obtenerTodosLosEventos(){
        ArrayList<Evento> eventos = new ArrayList<Evento>();
        String selectQuery = "SELECT  * FROM " + EVENTOS_TABLE_NAME + " order by id desc";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            // move cursor to first row
            if (cursor.moveToFirst()) {
                do {
                    Evento e = new Evento();
                    //e.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    e.set_Id(cursor.getString(cursor.getColumnIndex("_id")));
                    e.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                    e.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                    e.setComuna(cursor.getString(cursor.getColumnIndex("comuna")));
                    e.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                    e.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));

                    eventos.add(e);
                    Log.i("DB", "noticias obtenidas desde bd");
                    // move to next row
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        return eventos;
    }

    public Evento obtenerEvento(int id) {

        String selectQuery = "SELECT  * FROM " + EVENTOS_TABLE_NAME+" where id_="+id;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                Evento e = new Evento();
                e.setId(cursor.getInt(cursor.getColumnIndex("id")));
                e.set_Id(cursor.getString(cursor.getColumnIndex("_id")));
                e.setTitulo(cursor.getString(cursor.getColumnIndex("titulo")));
                e.setDescripcion(cursor.getString(cursor.getColumnIndex("descripcion")));
                e.setComuna(cursor.getString(cursor.getColumnIndex("comuna")));
                e.setFecha(cursor.getString(cursor.getColumnIndex("fecha")));
                e.setImagen(cursor.getString(cursor.getColumnIndex("imagen")));

                Log.i("DB", "noticias obtenida desde bd");
                return e;
            }
            cursor.close();
        }
        db.close();
        return null;
    }

    public void guardarEventos(ArrayList<Evento> eventos){

        SQLiteDatabase db = this.getWritableDatabase();

        for (Evento e: eventos) {

            ContentValues values = new ContentValues();
            values.put("_id", e.get_Id());
            values.put("titulo", e.getTitulo());
            values.put("descripcion", e.getDescripcion());
            values.put("comuna", e.getComuna());
            values.put("fecha", e.getFecha());
            values.put("imagen", e.getImagen());

            // Inserting Row
            long id = db.insert(EVENTOS_TABLE_NAME, null, values);
            Log.i("DB", "evento guardado con id: "+id);
        }
        Log.i("DB", "Todos mis eventos han sido guardados");
        db.close(); // Closing database connection

    }

    public void eliminarEventos() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Delete All Rows
        db.delete(EVENTOS_TABLE_NAME, null, null);
        db.close();

        Log.i("DB", "Todos los eventos fueron eliminados");
    }
}
