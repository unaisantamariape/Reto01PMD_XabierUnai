package dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Wraps the logic for a SQLite database
 */
public class DataManager extends SQLiteOpenHelper {

    // Database Information
    private static final String DB_NAME = "tareas.db";

    // Database version
    private static final int DB_VERSION = 1;

    // Table Name
    public static final String TABLE_NAME = "Tarea";

    // Table columns
    private static final String ID = "id";
    private static final String NOMBRE = "nombre";
    private static final String DESCRIPCION = "descripción";
    private static final String COSTE = "coste";
    private static final String PRIORIDAD = "prioridad";
    private static final String FECHA = "fecha";
    private static final String REALIZADA = "realizada";


    // Creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOMBRE + " TEXT NOT NULL, " +
            DESCRIPCION + " TEXT, " +
            COSTE + " INTEGER, " +
            PRIORIDAD + " TEXT, " +
            FECHA + " TEXT, " +
            REALIZADA + " INTEGER " +
            ");";

    private final Context context;

    public DataManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int oldVersion, int newVersion) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sQLiteDatabase);
    }

    //------------------------------ Select Nombres Pendientes ------------------------------//
    public ArrayList<String> selectNombresPendientes() {
        String query = " Select "+ NOMBRE + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=0";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<String> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(0).toString());
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Select IDs Pendientes ------------------------------//
    public ArrayList<Integer> selectIdsPendientes() {
        String query = " Select "+ ID + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=0";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<Integer> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getInt(0));
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Select Nombres Realizados ------------------------------//
    public ArrayList<String> selectNombresRealizados() {
        String query = " Select "+ NOMBRE + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=1";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<String> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getString(0).toString());
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Select IDs Realizados ------------------------------//
    public ArrayList<Integer> selectIdsRealizadas() {
        String query = " Select "+ ID + " FROM " + TABLE_NAME + " WHERE "+ REALIZADA+"=1";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        ArrayList<Integer> nombres = new ArrayList<>() ;
        int i = 0;
        while (cursor.moveToNext()) {
            nombres.add(cursor.getInt(0));
            i++;
        }
        cursor.close();
        sQLiteDatabase.close();
        return nombres;
    }

    //------------------------------ Insert ------------------------------//
    public void insert (Tarea tarea) {
        ContentValues values = new ContentValues();

        //values.put(ID, cliente.getId());
        values.put(NOMBRE, tarea.getNombre());
        values.put(DESCRIPCION, tarea.getDescripción());
        values.put(COSTE, tarea.getCoste());
        values.put(PRIORIDAD, tarea.getPrioridad());
        values.put(FECHA, tarea.getFecha());
        values.put(PRIORIDAD, tarea.getPrioridad());
        values.put(REALIZADA, tarea.getRealizada());

        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        sQLiteDatabase.insert(TABLE_NAME, null, values);
        sQLiteDatabase.close();
    }


    //------------------------------ Delete by ID ------------------------------//
    public int deleteById (String id) {
        int ret;
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();

        ret = sQLiteDatabase.delete(TABLE_NAME, ID + "=?", new String[]{id});
        sQLiteDatabase.close();
        return ret;
    }

    //------------------------------ Select by Id ------------------------------//

    public Tarea selectById (int id) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + ID +
                " = " + "'" + id + "'";
        SQLiteDatabase sQLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sQLiteDatabase.rawQuery(query, null);

        Tarea user = new Tarea ();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            user.setId(cursor.getInt(0));
            user.setNombre(cursor.getString(1));
            user.setDescripción(cursor.getString(2));
            user.setCoste(cursor.getInt(3));
            user.setPrioridad(cursor.getString(4));
            user.setFecha(cursor.getString(5));
            user.setRealizada(cursor.getInt(6));
            cursor.close();
        } else {
            user = null;
        }
        sQLiteDatabase.close();
        return user;
    }

}
